package cn.iomc.baseModel.service.impl;

import cn.iomc.baseModel.dto.QueryDTO;
import cn.iomc.baseModel.mapper.DataSourceMapper;
import cn.iomc.baseModel.service.DataSourceService;
import cn.iomc.baseModel.utils.DataBaseUtils;
import cn.iomc.common.constant.DynamicTableNameEnum;
import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.result.Result;
import cn.iomc.common.utils.CommonUtil;
import cn.iomc.common.utils.RequestDataHelper;
import cn.iomc.common.utils.SnowFlakeIdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
* @author jolysong
* @description 针对表【t_data_source(数据源表)】的数据库操作Service实现
* @createDate 2024-03-28 03:45:40
*/
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;


    /**
     * 添加数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addDatasource(DataSourceEntity dataSourceEntity) {
        DataSourceEntity cnt = dataSourceMapper.selectOne(
                new LambdaQueryWrapper<DataSourceEntity>()
                        .eq(DataSourceEntity::getName, dataSourceEntity.getName())
                        .eq(DataSourceEntity::getDelFlag, 0));

        if (null != cnt) {
            return Result.ERROR().message("数据源名称不能重复！");
        }

        // 生成id
        dataSourceEntity.setDsCode(SnowFlakeIdUtil.nextId());
        DataBaseUtils.defaultDriverSet(dataSourceEntity);

        int insert = dataSourceMapper.insert(dataSourceEntity);

        if (insert < 1) {
            return Result.ERROR().message("数据库操作失败，请检查！");
        }
        return Result.OK().message("添加成功！");
    }

    /**
     * 删除数据源
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delDatasource(QueryDTO dto) {

        try {
            List<String> codes = CommonUtil.stringToListByComma(dto.getCodes());

            List<DataSourceEntity> dataSources = dataSourceMapper.selectList(
                    new LambdaQueryWrapper<DataSourceEntity>()
                            .in(DataSourceEntity::getDsCode, codes));
            dataSources.forEach(it-> it.setDelFlag(1));

            int delete = dataSourceMapper.delete(
                    new LambdaQueryWrapper<DataSourceEntity>()
                            .in(DataSourceEntity::getDsCode, codes));

            // 传递动态表名所需参数
            RequestDataHelper.setRequestData(new HashMap<String, Object>() {{
                put(DynamicTableNameEnum.DYNAMIC_TABLE_OPERATION_DEL.getParam(),
                        DynamicTableNameEnum.DYNAMIC_TABLE_OPERATION_DEL.getValue());
            }});
            dataSourceMapper.insertBatchSomeColumn(dataSources);

            if (delete < 1) {
                return Result.ERROR().message("数据库操作失败，请检查！");
            }
            return Result.OK().message("删除成功！");
        } finally {
            RequestDataHelper.clean();
        }
    }

    /**
     * 修改数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @Override
    public Result updateDatasource(DataSourceEntity dataSourceEntity) {
        DataSourceEntity cnt = dataSourceMapper.selectOne(
                new LambdaQueryWrapper<DataSourceEntity>()
                        .eq(DataSourceEntity::getName, dataSourceEntity.getName())
                        .eq(DataSourceEntity::getDelFlag, 0));
        if (null != cnt && !cnt.getDsCode().equals(dataSourceEntity.getDsCode())) {
            return Result.ERROR().message("数据源名称不能重复！");
        }

        DataBaseUtils.defaultDriverSet(dataSourceEntity);
        int update = dataSourceMapper.update(dataSourceEntity,
                new LambdaQueryWrapper<DataSourceEntity>()
                        .eq(DataSourceEntity::getDsCode, dataSourceEntity.getDsCode()));
        if (update < 1) {
            return Result.ERROR().message("数据库操作失败，请检查！");
        }
        return Result.OK().message("更新成功！");
    }

    /**
     * 分页查询数据源
     *
     * @param query
     * @return
     */
    @Override
    public Result queryDsList(QueryDTO query) {
        Page page = new Page(query.getPageNumber(), query.getPageSize());
        QueryWrapper<DataSourceEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getKeyword())) {
            wrapper.like("NAME", query.getKeyword());
        }
        if (null != query.getDbType()) {
            wrapper.eq("DB_TYPE", query.getDbType());
        }
        wrapper.orderByDesc("CREATE_TIME");

        return Result.OK().data(dataSourceMapper.selectPage(page, wrapper));
    }
}




