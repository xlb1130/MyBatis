package org.xlb.mybatis.session;

import org.xlb.mybatis.config.Configuration;
import org.xlb.mybatis.exec.Executor;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 08:29
 * @Description:
 */
public class SqlSessionFactory {

    private final Configuration config;

    public SqlSessionFactory(Configuration config) {
        this.config = config;
    }

    public SqlSession openSession(){
        return openSessionFromDataSource();
    }

    private SqlSession openSessionFromDataSource() {
        return new SqlSession(config, new Executor());
    }
}
