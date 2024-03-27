package cn.iomc.baseModel.service;

import cn.iomc.baseModel.DTO.QueryDTO;
import cn.iomc.baseModel.entity.DataSource;
import cn.iomc.common.result.Result;

/**
* @author jolysong
* @description 针对表【t_data_source(数据源表)】的数据库操作Service
* @createDate 2024-03-28 03:45:40
*/
public interface DataSourceService {


    /**
     * 添加数据源
     * @param dataSource
     * @return
     */
    Result addDatasource(DataSource dataSource);

    /**
     * 删除数据源
     * @param dataSource
     * @return
     */
    Result delDatasource(DataSource dataSource);

    /**
     * 修改数据源
     * @param dataSource
     * @return
     */
    Result updateDatasource(DataSource dataSource);

    /**
     * 查询数据源
     * @param query
     * @return
     */
    Result queryDsList(QueryDTO query);
}
