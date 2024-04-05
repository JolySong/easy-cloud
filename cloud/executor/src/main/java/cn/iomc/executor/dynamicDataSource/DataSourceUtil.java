package cn.iomc.executor.dynamicDataSource;

/**
 * @Author song
 * @Date 2024/4/3 11:00
 * @Version 1.0
 */

import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.utils.LogUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;

/**
 * 动态数据源工具类
 */
@Component
public class DataSourceUtil {

    /**
     * 连接池最大连接数
     */
    @Value("${spring.datasource.hikari.maximum-pool-size:600000}")
    private Integer maximumPoolSize;

    /**
     * 空闲时保持最小连接数
     */
    @Value("${spring.datasource.hikari.minimum-idle:600}")
    private Integer minimumIdle;

    /**
     * 空闲连接存活时间
     */
    @Value("${spring.datasource.hikari.idle-timeout:30000}")
    private Integer idleTimeout;

    /**
     * 连接超时时间
     */
    @Value("${spring.datasource.hikari.connection-timeout:30000}")
    private Integer connectionTimeout;

    @Resource
    DynamicDataSource dynamicDataSource;


    /**
     * @Description: 根据传递的数据源信息测试数据库连接
     * @Author zhangyu
     */
    public HikariDataSource createDataSourceConnection(DataSourceEntity ds) {
        HikariDataSource dataSource =
                initDataSource(ds.getDbUsername(), ds.getDbPassword(), ds.getDbUrl(), ds.getDbDrive());
        try {
            dataSource.getConnection();
            LogUtil.info("数据源连接成功");
            addDefineDynamicDataSource(dataSource, ds.getDsCode()+"");
            return dataSource;
        } catch (SQLException throwables) {
            LogUtil.error("数据源连接失败, 请检查: ", ds.toString());
            throw new RuntimeException("数据源连接失败, 请检查: " + ds.toString());
        }
    }

    /**
     * @Description: 将新增的数据源加入到备份数据源map中
     * @Author zhangyu
     */
    public void addDefineDynamicDataSource(HikariDataSource dataSource, String dataSourceName){
        Map<Object, Object> defineTargetDataSources = dynamicDataSource.getDefineTargetDataSources();
        defineTargetDataSources.put(dataSourceName, dataSource);
        dynamicDataSource.setTargetDataSources(defineTargetDataSources);
        dynamicDataSource.afterPropertiesSet();
    }


    /**
     * 初始化连接池参数
     *
     * @param username
     * @param password
     * @param url
     * @param driverClass
     * @return
     */
    private HikariDataSource initDataSource(String username,
                                            String password,
                                            String url,
                                            String driverClass) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driverClass);

        // 连接池最大连接数
        dataSource.setMaximumPoolSize(maximumPoolSize);
        // 空闲时保持最小连接数
        dataSource.setMinimumIdle(minimumIdle);
        // 空闲连接存活时间
        dataSource.setIdleTimeout(idleTimeout);
        // 连接超时时间
        dataSource.setConnectionTimeout(connectionTimeout);

        return dataSource;
    }


    /**
     * 数据源是否存在
     *
     * @param dsCode
     * @return
     */
    public boolean exist(String dsCode) {
        Map<Object, Object> defineTargetDataSources = dynamicDataSource.getDefineTargetDataSources();
        return defineTargetDataSources.containsKey(dsCode);
    }
}