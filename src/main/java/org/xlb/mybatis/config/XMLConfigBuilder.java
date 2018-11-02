package org.xlb.mybatis.config;

import java.io.InputStream;

import javafx.beans.property.Property;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xlb.mybatis.mapping.MappedStatement;
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
    /**
     * 配置文件输入流
     */
    private InputStream inputStream;

    public XMLConfigBuilder(InputStream inputStream, String environment, Property properties) {
    	this.inputStream = inputStream;
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
     * @throws Exception 
     */
    private void parseConfiguration() throws Exception{
    	// 创建saxReader对象  
        SAXReader reader = new SAXReader();  
       // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(inputStream); 
        inputStream.close();
       // 获取根节点元素对象  
        Element root = document.getRootElement();  
        if("mappers".equals(root.getName()))
        	mapperElement(root);
        else
        	throw new Exception("错误的xml文件格式");
    }

    /**
     * 递归调用parse方法解析xml
     * 这里是重点
     * 暂时先写入静态
     */
    private void mapperElement(Element root) {
    	String nameSpace = root.attributeValue("namespace");
    	 MappedStatement statement;
    	for(Object node : root.elements()){
    		if (node == null) continue;
    		statement = new MappedStatement(((Element)node).getText(),
    				((Element)node).getName(),
    				((Element)node).attributeValue("parameterType"),
    				((Element)node).attributeValue("resultType"));
    		config.setMappedStatement(nameSpace+":"+((Element)node).attributeValue("id"),statement);
    	}        
    }

}
