package org.xlb.mybatis;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xlb.mybatis.session.SqlSession;
import org.xlb.mybatis.session.SqlSessionFactory;
import org.xlb.mybatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 11:43
 * @Description:
 */
public class MybatisTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setup() throws Exception {
        InputStream inputStream = new FileInputStream(new File("E:\\test.txt"));
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void shouldFailForWrongNamespace() throws Exception {
        SqlSession session= sqlSessionFactory.openSession();
        TestMapper daoMapper = session.getMapper(TestMapper.class);
        Map<String,String> param = new HashMap<>();
        param.put("del_flag","0");
        List<Map<String,String>> retList= daoMapper.select(param);
        for(Map<String,String> map : retList){
            System.out.println(map);
        }
    }
}
