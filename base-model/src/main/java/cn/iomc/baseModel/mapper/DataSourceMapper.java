package cn.iomc.baseModel.mapper;

import cn.iomc.baseModel.entity.DataSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author jolysong
* @description 针对表【t_data_source(数据源表)】的数据库操作Mapper
* @createDate 2024-03-28 03:45:40
* @Entity baseModel.entity.DataSource
*/
@Mapper
public interface DataSourceMapper extends BaseMapper<DataSource> {

}




