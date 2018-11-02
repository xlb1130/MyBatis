package org.xlb.mybatis.session;

import javafx.beans.property.Property;
import org.xlb.mybatis.config.Configuration;
import org.xlb.mybatis.config.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 08:31
 * @Description:
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        return build(inputStream, null, null);
    }

    public SqlSessionFactory build(InputStream inputStream, String environment, Property properties) throws Exception {

        try {
            //创建xml解析器解析inputStream
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
            // 创建 session 工厂
            return build(parser.parse());
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("SqlSessionFactory 创建失败");
        } finally{
            //关闭流等资源
        }
    }

    public SqlSessionFactory build(Configuration config){
        return new SqlSessionFactory(config);
    }
}
