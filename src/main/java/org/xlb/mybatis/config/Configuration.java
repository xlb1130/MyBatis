package org.xlb.mybatis.config;

import org.xlb.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 08:34
 * @Description:
 */
public class Configuration {

    protected final Map<String,MappedStatement> statementMap= new HashMap<>();

    public void setMappedStatement(String name,MappedStatement mappedStatement) {
        this.statementMap.put(name,mappedStatement);
    }

    public MappedStatement getMappedStatement(String name) {
        return statementMap.get(name);
    }
}
