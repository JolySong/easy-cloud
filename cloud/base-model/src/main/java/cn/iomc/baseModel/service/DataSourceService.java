
package cn.iomc.baseModel.service;

import cn.iomc.baseModel.dto.QueryDTO;
import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.result.Result;

/**
* @author jolysong
* @description 针对表【t_data_source(数据源表)】的数据库操作Service
* @createDate 2024-03-28 03:45:40
*/
public interface DataSourceService {


    /**
     * 添加数据源
     *
     * @param dataSourceEntity
     * @return
     */
    Result addDatasource(DataSourceEntity dataSourceEntity);

    /**
     * 删除数据源
     *
     * @param dto
     * @return
     */
    Result delDatasource(QueryDTO dto);

    /**
     * 修改数据源
     *
     * @param dataSourceEntity
     * @return
     */
    Result updateDatasource(DataSourceEntity dataSourceEntity);

    /**
     * 分页查询数据源
     *
     * @param query
     * @return
     */
    Result queryDsList(QueryDTO query);
}
