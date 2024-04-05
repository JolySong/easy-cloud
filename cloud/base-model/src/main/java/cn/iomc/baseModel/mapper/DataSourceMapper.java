package cn.iomc.baseModel.mapper;

import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.mapper.SpiceBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author jolysong
* @description 针对表【t_data_source(数据源表)】的数据库操作Mapper
* @createDate 2024-03-28 03:45:40
* @Entity baseModel.entity.DataSourceEntity
*/
@Mapper
public interface DataSourceMapper extends SpiceBaseMapper<DataSourceEntity> {

}




