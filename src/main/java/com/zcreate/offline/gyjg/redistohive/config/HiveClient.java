package com.zcreate.offline.gyjg.redistohive.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName HiveClient
 * @Author mjlft
 * @Date 2018/11/28 21:12
 * @Version 1.0
 * @Description TODO
 */

public class HiveClient {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    // 默认就是10000端口，ip地址使用hive服务器的、
//    线上
    private static String url = "jdbc:hive2://10.197.236.15:10000/gyjg";
    //测试
//    private static String url = "jdbc:hive2://10.1.80.5:10000/gyjg";
    // hive连接的用户名和密码，默认就算是下面这两个
    private static String user = "hive";
    private static String password = "hive";

    // 加载驱动、创建连接
    public static Connection getConnection() throws Exception {
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

    //关闭连接
    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}