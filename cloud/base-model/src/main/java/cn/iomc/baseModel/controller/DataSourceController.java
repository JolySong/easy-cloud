package cn.iomc.baseModel.controller;

import cn.iomc.baseModel.DTO.QueryDTO;
import cn.iomc.baseModel.entity.DataSource;
import cn.iomc.baseModel.service.DataSourceService;
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
     * @param dataSource
     * @return
     */
    @PostMapping("/addDatasource")
    public Result addDatasource(@RequestBody @Valid DataSource dataSource) {
        if (null == dataSource) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return dataSourceService.addDatasource(dataSource);
    }

    /**
     * 新增数据源
     *
     * @param dataSource
     * @return
     */
    @DeleteMapping("/delDatasource")
    public Result delDatasource(@RequestBody DataSource dataSource) {
        if (null == dataSource
                || StringUtils.isBlank(dataSource.getDsCode())) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return dataSourceService.delDatasource(dataSource);
    }

    /**
     * 修改数据源
     *
     * @param dataSource
     * @return
     */
    @PutMapping("/editDatasource")
    public Result editDatasource(@RequestBody @Valid DataSource dataSource) {
        if (null == dataSource
                || StringUtils.isBlank(dataSource.getDsCode())) {
            return Result.ERROR().message("参数异常，请检查！");
        }

        return dataSourceService.updateDatasource(dataSource);
    }


    /**
     * 分页查询数据源列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryDatasourceList")
    public Result queryDatasourceList(@RequestBody QueryDTO dto) {
        return dataSourceService.queryDsList(dto);
    }
}
