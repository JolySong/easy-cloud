package cn.iomc.baseModel.service;

import cn.iomc.baseModel.dto.QueryDTO;
import cn.iomc.baseModel.entity.ModelEntity;
import cn.iomc.common.result.Result;

/**
* @author jolysong
* @description 针对表【t_model(模型表)】的数据库操作Service
* @createDate 2024-03-28 16:39:06
*/
public interface ModelService {

    /**
     * 添加模型
     *
     * @param modelEntity
     * @return
     */
    Result addModel(ModelEntity modelEntity);

    /**
     * 删除模型
     *
     * @param modelEntity
     * @return
     */
    Result delModel(ModelEntity modelEntity);

    /**
     * 修改模型
     *
     * @param modelEntity
     * @return
     */
    Result updateModel(ModelEntity modelEntity);

    /**
     * 分页查询模型列表
     *
     * @param query
     * @return
     */
    Result queryModelList(QueryDTO query);

    /**
     * 发布模型
     *
     * @param modelEntity
     * @return
     */
    Result publishModel(ModelEntity modelEntity);
}
