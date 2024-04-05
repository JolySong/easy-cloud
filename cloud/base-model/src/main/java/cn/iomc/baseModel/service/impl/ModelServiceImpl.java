package cn.iomc.baseModel.service.impl;

import cn.iomc.baseModel.dto.QueryDTO;
import cn.iomc.baseModel.entity.ModelEntity;
import cn.iomc.baseModel.entity.ModelExtendEntity;
import cn.iomc.baseModel.mapper.ModelExtendMapper;
import cn.iomc.baseModel.mapper.ModelMapper;
import cn.iomc.baseModel.service.ModelService;
import cn.iomc.common.result.Result;
import cn.iomc.common.utils.SnowFlakeIdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author jolysong
* @description 针对表【t_model(模型表)】的数据库操作Service实现
* @createDate 2024-03-28 16:39:06
*/
@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelExtendMapper modelExtendMapper;

    /**
     * 添加模型
     *
     * @param modelEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addModel(ModelEntity modelEntity) {

        Long cnt = modelMapper.selectCount(
                new LambdaQueryWrapper<ModelEntity>()
                        .eq(ModelEntity::getModelName, modelEntity.getModelName()));

        if (null != cnt && !cnt.equals(0L)) {
            return Result.ERROR().message("模型名称不允许重复！");
        }

        // 全新模型
        if (null == modelEntity.getMainConfigCode()) {
            modelEntity.setMainConfigCode(SnowFlakeIdUtil.nextId());
        }
        modelEntity.setConfigCode(SnowFlakeIdUtil.nextId());
        int i = modelMapper.queryModelCount(null);
        modelEntity.setState(0);
        modelEntity.setSort(i+1);
        modelMapper.insert(modelEntity);

        List<ModelExtendEntity> modelExtendEntities = modelEntity.getModelExtendEntities();
        if (!modelExtendEntities.isEmpty()) {
            modelExtendEntities.forEach(it -> it.setConfigCode(modelEntity.getConfigCode()));

            modelExtendMapper.insertBatchSomeColumn(modelExtendEntities);
        }

        return Result.OK();
    }

    /**
     * 删除模型
     *
     * @param modelEntity
     * @return
     */
    @Override
    public Result delModel(ModelEntity modelEntity) {

        int i = modelMapper.queryModelCount(modelEntity.getMainConfigCode());
        if (0 < i) {
            return Result.ERROR().message("该模型存在发布的版本，不允许删除！");
        }


        return null;
    }

    /**
     * 修改模型
     *
     * @param modelEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateModel(ModelEntity modelEntity) {

        modelMapper.update(modelEntity,
                new LambdaQueryWrapper<ModelEntity>()
                        .eq(ModelEntity::getConfigCode, modelEntity.getConfigCode()));

        List<ModelExtendEntity> modelExtendEntities = modelEntity.getModelExtendEntities();
        if (!modelExtendEntities.isEmpty()) {
            modelExtendEntities.forEach(it -> it.setConfigCode(modelEntity.getConfigCode()));

            modelExtendMapper.insertBatchSomeColumn(modelExtendEntities);
        }

        return Result.OK().message("编辑成功");
    }

    /**
     * 分页查询模型列表
     *
     * @param query
     * @return
     */
    @Override
    public Result queryModelList(QueryDTO query) {
        return null;
    }

    /**
     * 发布模型
     *
     * @param modelEntity
     * @return
     */
    @Override
    public Result publishModel(ModelEntity modelEntity) {
        return null;
    }
}




