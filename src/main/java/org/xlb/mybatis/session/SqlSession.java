package org.xlb.mybatis.session;

import org.testng.annotations.ITestOrConfiguration;
import org.xlb.mybatis.bind.MapperProxy;
import org.xlb.mybatis.config.Configuration;
import org.xlb.mybatis.exec.Executor;
import org.xlb.mybatis.mapping.MappedStatement;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 08:29
 * @Description:
 */
public class SqlSession {
    private final Configuration config;

    private final Executor executor;

    public SqlSession(Configuration config, Executor executor) {
        this.config = config;
        this.executor = executor;
    }

    public <T> List<T> query(String name, Object param) throws SQLException {
        MappedStatement st = config.getMappedStatement(name);
        st.setParam(param);
        return executor.query(st);
    }

    public List<Map<String,String>> queryList(String name, Object param) throws SQLException {
        System.out.println(name);
        MappedStatement st = config.getMappedStatement(name);
        st.setParam(param);
        return executor.queryList(st);
    }

    public Configuration getConfiguration() {
        return config;
    }

    public <T> T getMapper(Class<T> interfaces){
        return (T) Proxy.newProxyInstance(
                interfaces.getClassLoader(),
                new Class[]{interfaces},
                new MapperProxy<>(this,interfaces));
    }

}
