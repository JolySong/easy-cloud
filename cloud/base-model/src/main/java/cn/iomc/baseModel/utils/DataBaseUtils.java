package cn.iomc.baseModel.utils;

import cn.iomc.common.entity.DataSourceEntity;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author song
 * @Date 2024/3/28 15:03
 * @Version 1.0
 */
public class DataBaseUtils {

    /**
     * mysql默认驱动
     */
    public static final String MYSQL_DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * oracle默认驱动
     */
    public static final String ORACLE_DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";

    /**
     * sqlServer默认驱动
     */
    public static final String SQLSERVER_DEFAULT_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     *  数据库连接测试
     *
     * @param drive
     * @param url
     * @param username
     * @param passwd
     * @throws ClassNotFoundException
     * @throws RuntimeException
     * @throws SQLException
     */
    public static void connectionTest(String drive, String url,
                                      String username, String passwd)
            throws ClassNotFoundException, RuntimeException, SQLException {
        // 定义连接
        Connection connection = null;
        try {
            if (null == url || 10 > url.length() || !drive.contains(url.split(":")[1])) {
                throw new Exception();
            }
            // 加载驱动
            Class.forName(drive);
        } catch (Exception exception) {
            throw new ClassNotFoundException("驱动或url异常,请检查");
        }
        try {
            connection = DriverManager.getConnection(url, username, passwd);
        } catch (Exception e) {
            throw new RuntimeException("连接异常，请检查url, 用户名, 密码 是否正确");
        } finally {
            if (null != connection) {
                connection.close();
            }
        }
    }

    /**
     * 设置数据库默认驱动
     * @param dataSourceEntity
     */
    public static void defaultDriverSet(DataSourceEntity dataSourceEntity) {

        if (StringUtils.isNotBlank(dataSourceEntity.getDbDrive())) {
            return;
        }

        switch (dataSourceEntity.getDbType()) {
            case 1: {
                dataSourceEntity.setDbDrive(ORACLE_DEFAULT_DRIVER);
                break;
            }
            case 2: {
                dataSourceEntity.setDbDrive(SQLSERVER_DEFAULT_DRIVER);
                break;
            }
            default: {
                dataSourceEntity.setDbDrive(MYSQL_DEFAULT_DRIVER);
            }
        }
    }
}