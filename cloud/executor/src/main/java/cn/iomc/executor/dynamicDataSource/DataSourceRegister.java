package cn.iomc.executor.dynamicDataSource;

import cn.iomc.common.constant.Constants;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始化默认数据源
 *
 * @Author song
 * @Date 2024/4/3 09:27
 * @Version 1.0
 */
@Configuration
public class DataSourceRegister {

    @Value(value = "${spring.datasource.username}")
    private String username;

    @Value(value = "${spring.datasource.password}")
    private String password;

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value(value = "${spring.datasource.driver-class-name}")
    private String driver;


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
        dataSource.setMaximumPoolSize(20);
        // 空闲时保持最小连接数
        dataSource.setMinimumIdle(5);
        // 空闲连接存活时间ms
        dataSource.setIdleTimeout(30000);
        // 连接超时时间ms
        dataSource.setConnectionTimeout(6000);

        return dataSource;
    }

    /**
     * 默认数据源初始化
     *
     * @return
     * @throws IOException
     */
    @Bean(name = Constants.MASTER)
    public DataSource defaultDataSource() {
        return initDataSource(username, password, url, driver);
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(Constants.MASTER, defaultDataSource());

        // 设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源信息备份在defineTargetDataSources中
        dynamicDataSource.setDefineTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    /**
     *
     * 动态数据源模板
     * @param dataSource
     * @return
     * */
    @Bean(name = "dynamicJdbcTemplate")
    public NamedParameterJdbcTemplate dynamicJdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}