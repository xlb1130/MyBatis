package org.xlb.mybatis.config;

import javafx.beans.property.Property;
import org.xlb.mybatis.mapping.MappedStatement;

import java.io.InputStream;
/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 08:34
 * @Description:
 */
public class XMLConfigBuilder {

    /**
     * Configuration对象实例
     */
    private static Configuration config;
    /**
     * parse标记
     */
    private boolean parsed = false;

    public XMLConfigBuilder(InputStream inputStream, String environment, Property properties) {
        config = new Configuration();
    }

    public Configuration parse() throws Exception {
        if(parsed)
            throw new Exception("XMLConfigBuilder 只能被解析一次");
        parsed = true;
        parseConfiguration();
        return config;
    }

    /**
     * xml文件解析
     */
    private void parseConfiguration(){
        mapperElement();
    }

    /**
     * 递归调用parse方法解析xml
     * 这里是重点
     * 暂时先写入静态
     */
    private void mapperElement() {
        MappedStatement statement = new MappedStatement("select * from pro_channel_info where del_flag = ${del_flag}");
        config.setMappedStatement("org.xlb.mybatis.TestMapper:select",statement);
    }

}
