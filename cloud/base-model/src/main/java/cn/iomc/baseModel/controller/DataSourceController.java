package cn.iomc.baseModel.controller;

import cn.iomc.baseModel.dto.QueryDTO;
import cn.iomc.baseModel.service.DataSourceService;
import cn.iomc.baseModel.utils.DataBaseUtils;
import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author song
 * @Date 2024/3/28 03:49
 * @Version 1.0
 */

@RestController
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    /**
     * 新增数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @PostMapping("/datasource/operation/addDatasource")
    public Result addDatasource(@RequestBody @Valid DataSourceEntity dataSourceEntity) {
        if (null == dataSourceEntity) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return dataSourceService.addDatasource(dataSourceEntity);
    }

    /**
     * 删除数据源
     *
     * @param dto
     * @return
     */
    @DeleteMapping("/datasource/operation/delDatasource")
    public Result delDatasource(@RequestBody QueryDTO dto) {
        if (null == dto
                || StringUtils.isBlank(dto.getCodes())) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return dataSourceService.delDatasource(dto);
    }

    /**
     * 修改数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @PutMapping("/datasource/operation/editDatasource")
    public Result editDatasource(@RequestBody @Valid DataSourceEntity dataSourceEntity) {
        if (null == dataSourceEntity
                || null == dataSourceEntity.getDsCode()) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return dataSourceService.updateDatasource(dataSourceEntity);
    }


    /**
     * 分页查询数据源列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/datasource/queryDatasourceList")
    public Result queryDatasourceList(@RequestBody QueryDTO dto) {
        return dataSourceService.queryDsList(dto);
    }


    /**
     * 测试数据源连接
     *
     * @param dataSourceDto
     * @return:
     */
    @PostMapping(value ="/datasource/operation/connect", produces = "application/json;charset=UTF-8")
    public Result connect(@RequestBody @Valid DataSourceEntity dataSourceDto) {
        try {
            DataBaseUtils.connectionTest(dataSourceDto.getDbDrive(),
                    dataSourceDto.getDbUrl(),
                    dataSourceDto.getDbUsername(),
                    dataSourceDto.getDbPassword());
            return Result.OK();
        } catch (Exception e) {
            return Result.ERROR().message(e.getMessage());
        }
    }
}
