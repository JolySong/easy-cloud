package cn.iomc.baseModel.service.impl;

import cn.iomc.baseModel.DTO.QueryDTO;
import cn.iomc.baseModel.entity.DataSource;
import cn.iomc.baseModel.mapper.DataSourceMapper;
import cn.iomc.baseModel.service.DataSourceService;
import cn.iomc.common.result.Result;
import cn.iomc.common.utils.SnowFlakeIdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author jolysong
* @description 针对表【t_data_source(数据源表)】的数据库操作Service实现
* @createDate 2024-03-28 03:45:40
*/
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Autowired
    private SnowFlakeIdUtil snowFlakeIdUtil;

    /**
     * 添加数据源
     *
     * @param dataSource
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addDatasource(DataSource dataSource) {
        DataSource cnt = dataSourceMapper.selectOne(
                new LambdaQueryWrapper<DataSource>()
                        .eq(DataSource::getName, dataSource.getName())
                        .eq(DataSource::getDelFlag, 0));

        if (null != cnt) {
            return Result.ERROR().message("数据源名称不能重复！");
        }

        dataSource.setDsCode(snowFlakeIdUtil.nextId() + "");

        int insert = dataSourceMapper.insert(dataSource);

        if (insert < 1) {
            return Result.ERROR().message("数据库操作失败，请检查！");
        }
        return Result.OK().message("添加成功！");
    }

    /**
     * 删除数据源
     *
     * @param dataSource
     * @return
     */
    @Override
    public Result delDatasource(DataSource dataSource) {
        int delete = dataSourceMapper.delete(
                new LambdaQueryWrapper<DataSource>()
                        .eq(DataSource::getDsCode, dataSource.getDsCode()));
        if (delete < 1) {
            return Result.ERROR().message("数据库操作失败，请检查！");
        }
        return Result.OK().message("删除成功！");
    }

    /**
     * 修改数据源
     *
     * @param dataSource
     * @return
     */
    @Override
    public Result updateDatasource(DataSource dataSource) {
        DataSource cnt = dataSourceMapper.selectOne(
                new LambdaQueryWrapper<DataSource>()
                        .eq(DataSource::getName, dataSource.getName())
                        .eq(DataSource::getDelFlag, 0));
        if (null != cnt && !cnt.getDsCode().equals(dataSource.getDsCode())) {
            return Result.ERROR().message("数据源名称不能重复！");
        }

        int update = dataSourceMapper.update(dataSource,
                new LambdaQueryWrapper<DataSource>()
                        .eq(DataSource::getDsCode, dataSource.getDsCode()));
        if (update < 1) {
            return Result.ERROR().message("数据库操作失败，请检查！");
        }
        return Result.OK().message("更新成功！");
    }

    /**
     * 查询数据源
     *
     * @param query
     * @return
     */
    @Override
    public Result queryDsList(QueryDTO query) {
        Page page = new Page(query.getPageNumber(), query.getPageSize());
        QueryWrapper<DataSource> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getKeyword())) {
            wrapper.like("NAME", query.getKeyword());
        }
        if (null != query.getDbType()) {
            wrapper.eq("DB_TYPE", query.getDbType());
        }

        dataSourceMapper.selectPage(page, wrapper);
        return Result.OK().data(page);
    }
}




