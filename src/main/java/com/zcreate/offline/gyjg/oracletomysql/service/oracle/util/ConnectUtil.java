package com.zcreate.offline.gyjg.oracletomysql.service.oracle.util;

import oracle.jdbc.driver.OracleDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Properties;

/**
 * @ClassName: ConnectMysqlUtil
 * @Author: majun
 * @CreateDate: 2018/12/29 15:36
 * @Version: 1.0
 * @Description: TODO
 */

@Component
public class ConnectUtil {
//    oracle.user = "zckj";
//    oracle.password = "ZCKJ2018";
//    oracle.driver = "jdbc:oracle:thin:@10.1.20.169:1521:GYJG";

    @Value("${oracle.user}")
    private  String user;
    @Value("${oracle.password}")
    private String password;
    @Value("${oracle.driver}")
    private String driver;

    public Connection getConnect(String user, String password, String url) throws SQLException {
        Connection connection = null;
        Driver driver = new OracleDriver();
        DriverManager.deregisterDriver(driver);

        //第三种方式:利用系统参数  需在idea中配置program arguments为下面的参数
        //-Djdbc.drivers = oracle.jdbc.OracleDriver



        //第二步：获取连接
        //第一种方式：利用DriverManager（常用）
        //connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "你的oracle数据库用户名", "用户名密码");

        //第二种方式：直接使用Driver
        Properties pro = new Properties();
//        pro.put("user", "zckj");
//        pro.put("password", "ZCKJ2018");
//        connection = driver.connect("jdbc:oracle:thin:@172.16.17.82:1521:gyjg", pro);

        pro.put("user", user);
        pro.put("password", password);
        connection = driver.connect(url, pro);


        return connection;
    }


    public Connection getConnect() throws SQLException {
        return getConnect(user, password, driver);
    }

    public static void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
