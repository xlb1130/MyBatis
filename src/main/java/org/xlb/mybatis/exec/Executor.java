package org.xlb.mybatis.exec;

import org.xlb.mybatis.jdbc.JdbcConnectionFactory;
import org.xlb.mybatis.mapping.MappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 09:28
 * @Description:
 */
public class Executor {
    private Connection connection = JdbcConnectionFactory.getConnection();

    public <T> List<T> query(MappedStatement st) throws SQLException {
        return new ArrayList<T>();
    }

    public  List<Map<String,String>> queryList(MappedStatement st) throws SQLException {
        //attr应该为对T使用反射获取其所有属性的集合 此处先简化为一个
        PreparedStatement stmt = st.getPreparedStatement(connection);
        List<Map<String,String>> result = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        Map<String,String> hashMap = new HashMap<>();
        while(rs.next()){
            //正常情况为 根据方法返回对象获取需要获取的列
            //for(String colName : st.getColList()){
            //    hashMap.put(colName,rs.getString(colName).toString());
            //}
            //这里暂时返回所有行数据
            for(int i=1;i<rs.getMetaData().getColumnCount();i++){
                hashMap.put(rs.getMetaData().getColumnName(i),
                        rs.getString(i));
            }
            result.add(hashMap);
        }
        return result;
    }
}
