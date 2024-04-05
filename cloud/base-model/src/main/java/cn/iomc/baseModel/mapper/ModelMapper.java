package cn.iomc.baseModel.mapper;

import cn.iomc.baseModel.entity.ModelEntity;
import cn.iomc.common.mapper.SpiceBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author jolysong
* @description 针对表【t_model(模型表)】的数据库操作Mapper
* @createDate 2024-03-28 16:39:06
* @Entity cn.iomc.baseModel.entity.ModelEntity
*/
@Mapper
public interface ModelMapper extends SpiceBaseMapper<ModelEntity> {

    /**
     * 查询模型数量
     *
     * @param mainConfigCode
     * @return
     */
    int queryModelCount(@Param("mainConfigCode") Long mainConfigCode);
}




