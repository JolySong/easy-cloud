package cn.iomc.executor.controller;

import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.result.Result;
import cn.iomc.executor.dynamicDataSource.DataSourceContextHolder;
import cn.iomc.executor.dynamicDataSource.DataSourceUtil;
import cn.iomc.executor.mapper.DataSourceMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author song
 * @Date 2024/4/3 11:26
 * @Version 1.0
 */


@RestController
public class TestDyController {

    @Resource
    DataSourceUtil dataSourceUtil;

    @Resource
    DataSourceMapper dataSourceMapper;

    @GetMapping("/t")
    public Result test() throws SQLException {
        // 在主库中查询
        DataSourceEntity ds = dataSourceMapper.selectOne(
                new LambdaQueryWrapper<DataSourceEntity>()
                        .eq(DataSourceEntity::getDsCode, 530457199486529536L));

        Connection connection =
                dataSourceUtil.createDataSourceConnection(ds).getConnection();

        DataSourceContextHolder.clear();
        DataSourceContextHolder.set(ds.getDsCode()+"");

        CallableStatement callableStatement = connection.prepareCall("select * from user_info");
        ResultSet resultSet = callableStatement.executeQuery();


        return Result.OK();

    }

}
