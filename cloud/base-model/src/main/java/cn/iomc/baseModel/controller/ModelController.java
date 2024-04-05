package cn.iomc.baseModel.controller;

import cn.iomc.baseModel.dto.QueryDTO;
import cn.iomc.baseModel.entity.ModelEntity;
import cn.iomc.baseModel.service.ModelService;
import cn.iomc.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author song
 * @Date 2024/3/28 03:49
 * @Version 1.0
 */

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    /**
     * 新增数据源
     *
     * @param modelEntity
     * @return
     */
    @PostMapping("/model/operation/addModel")
    public Result addModel(@RequestBody @Valid ModelEntity modelEntity) {
        if (null == modelEntity) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return modelService.addModel(modelEntity);
    }

    /**
     * 删除数据源
     *
     * @param modelEntity
     * @return
     */
    @DeleteMapping("/model/operation/delModel")
    public Result delModel(@RequestBody ModelEntity modelEntity) {
        if (null == modelEntity
                || null == modelEntity.getConfigCode()
                || null == modelEntity.getMainConfigCode()) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return modelService.delModel(modelEntity);
    }

    /**
     * 修改数据源
     *
     * @param modelEntity
     * @return
     */
    @PutMapping("/model/operation/editModel")
    public Result editModel(@RequestBody @Valid ModelEntity modelEntity) {
        if (null == modelEntity
                || null == modelEntity.getConfigCode()) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return modelService.updateModel(modelEntity);
    }


    /**
     * 分页查询模型列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/model/queryModelList")
    public Result queryModelList(@RequestBody QueryDTO dto) {
        return null;
    }


}
