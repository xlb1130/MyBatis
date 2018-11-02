package org.xlb.mybatis.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 09:37
 * @Description:
 */
public class JdbcConnectionFactory {
    private static final Logger log = LoggerFactory.getLogger(JdbcConnectionFactory.class);
    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    //数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/irrigation";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "root";

    public static Connection getConnection(){
        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("没有找到jdbc驱动包");
        } catch (SQLException e) {
            log.error("数据库连接创建失败");
            e.printStackTrace();
        }
        return null;
    }
}
