package cn.iomc.executor.service.impl;

import cn.iomc.common.constant.Constants;
import cn.iomc.common.dto.ValidTaskDTO;
import cn.iomc.common.entity.ModelRuleNull;
import cn.iomc.common.entity.QcResultDetailsEntity;
import cn.iomc.executor.utils.RuleExecutorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 空值检测
 *
 * @Author song
 * @Date 2024/4/3 18:48
 * @Version 1.0
 */
@Component
public class NullRuleExecutorImpl extends RuleExecutorImpl {


    /**
     * 校验每一条，生成明细
     *
     * @param results
     * @param primaryFieldList
     * @param checkFieldList
     * @param task
     */
    @Override
    public void validResult(List<Map<String, Object>> results,
                             String[] primaryFieldList,
                             String[] checkFieldList,
                             ValidTaskDTO task){

        // 结果集
        List<QcResultDetailsEntity> qcResultList = new ArrayList<>();

        ModelRuleNull modelRuleNull = task.getModelRuleNull();

        // 空格是否算空值 0 是 1 否
        // 0.不等于null
        // 1.不等于null 并且不等于空格
        Integer checkSpace = modelRuleNull.getCheckSpace();

        // 字段都不能为空
        AtomicInteger notNullState = new AtomicInteger(modelRuleNull.getNotNullState());

        for (Map<String, Object> result : results) {

            AtomicInteger cnt = new AtomicInteger(0);
            // 遍历检查字段
            for (String checkField : checkFieldList) {

                Object checkFieldValue = result.get(checkField);
                // 如果检查字段为null
                if (null == checkFieldValue) {
                    continue;
                }

                if (null == checkSpace || checkSpace.equals(0)) {
                    cnt.incrementAndGet();
                    continue;
                }
                // 如果不是空格
                if (StringUtils.isNotBlank(checkFieldValue.toString())) {
                    cnt.incrementAndGet();
                }
            }

            boolean flag = Integer.valueOf(1).equals(notNullState.get()) && cnt.get() == checkFieldList.length;
            // 不能同时为空 并且通过的次数相等检查的字段

            // 其中一个字段不为空 并且通过的次数>0
            if (Integer.valueOf(0).equals(notNullState.get()) && cnt.get() > 0) {
                flag = true;
            }

            // 0正常 1异常
            Integer resultState = flag ? 0 : 1;
            RuleExecutorUtil.addResult(task, qcResultList, result, resultState);
        }

        // 异步交给mq队列处理结果值
        super.ruleExecutorUtil.handleResult(qcResultList);
    }

    /**
     * 获取任务类型
     */
    @Override
    public int getTaskType() {
        return Constants.RULE_TYPE_NULL;
    }
}
