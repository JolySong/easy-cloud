package cn.iomc.executor.service.impl;

import cn.iomc.common.constant.Constants;
import cn.iomc.common.dto.ValidTaskDTO;
import cn.iomc.common.entity.ValidTaskEntity;
import cn.iomc.common.utils.LogUtil;
import cn.iomc.executor.dynamicDataSource.DataSourceContextHolder;
import cn.iomc.executor.dynamicDataSource.DataSourceUtil;
import cn.iomc.executor.mapper.ValidTaskMapper;
import cn.iomc.executor.service.RuleExecutor;
import cn.iomc.executor.utils.RuleExecutorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author song
 * @Date 2024/4/4 17:53
 * @Version 1.0
 */
@Component
public abstract class RuleExecutorImpl implements RuleExecutor {


    @Autowired
    ValidTaskMapper validTaskMapper;

    /**
     * 动态数据源工具类
     */
    @Resource
    DataSourceUtil dataSourceUtil;

    /**
     * 规则执行工具类
     */
    @Resource
    RuleExecutorUtil ruleExecutorUtil;

    /**
     * 动态数据源模版
     */
    @Resource
    @Qualifier("dynamicJdbcTemplate")
    NamedParameterJdbcTemplate dynamicJdbcTemplate;


    /**
     * 执行
     *
     * @param task
     */
    @Override
    public void exec(ValidTaskDTO task) {

        try {
            String totalSql = task.getTotalSql();

            // 处理数据源
            Long dsCode = task.getDsCode();
            if (!dataSourceUtil.exist(dsCode + "")) {
                dataSourceUtil.createDataSourceConnection(task.getDataSource());
            }

            // 执行sql
            DataSourceContextHolder.clear();
            DataSourceContextHolder.set(dsCode + "");

            String[] primaryFieldList = task.getPrimaryField().split(Constants.STR_COMMA);
            String[] checkFieldList = task.getCheckField().split(Constants.STR_COMMA);

            // 处理规则的业务日期
            totalSql = RuleExecutorUtil.handleExecTime(task, totalSql);

            // 执行查询
            List<Map<String, Object>> results =
                    dynamicJdbcTemplate.queryForList(totalSql, new HashMap<>(0));

            // 处理执行结果
            if (null == results || results.isEmpty()) {
                task.setExecState(Constants.RULE_EXEC_NO_DATA);
                return;
            }

            validResult(results, primaryFieldList, checkFieldList, task);
            task.setExecState(Constants.RULE_EXEC_SUCCESS);
        } catch (Exception e) {
            LogUtil.error("规则执行出现异常 "
                    + e.getMessage() + " -----> "
                    + Arrays.toString(e.getStackTrace()));
            task.setExecState(Constants.RULE_EXEC_EXCEPTION);
            RuleExecutorUtil.handleExceptionRule(task, e.getMessage());
        } finally {

            // 更新任务状态
            ValidTaskEntity validTaskEntity = new ValidTaskEntity();
            validTaskEntity.setValidCode(task.getValidCode());
            validTaskEntity.setExecState(task.getExecState());

//            validTaskMapper.update(validTaskEntity,
//                    new LambdaQueryWrapper<ValidTaskEntity>()
//                            .eq(ValidTaskEntity::getValidCode, validTaskEntity.getValidCode()));
        }
    }


    /**
     * 校验规则，生成明细
     *
     * @param results
     * @param primaryFieldList
     * @param checkFieldList
     * @param task
     */
    public abstract void validResult(List<Map<String, Object>> results,
                                     String[] primaryFieldList,
                                     String[] checkFieldList,
                                     ValidTaskDTO task);

    /**
     * 获取任务类型
     */
    @Override
    public int getTaskType() {
        return 99;
    }
}
