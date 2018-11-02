package org.xlb.mybatis.mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 10:11
 * @Description:
 */
public final class MappedStatement {

  private final String sql;
  private final String sqlType;
  private final String paramType;
  private final String resultType;
  private Object param;
  private List<String> colList;
  private List<String> paramList;

  public MappedStatement(String sql,String sqlType,String paramType,String resultType){
    this.sql = sql;
    this.sqlType = sqlType;
    this.paramType = paramType;
    this.resultType = resultType;
  }

  public String getSql() {
    return sql;
  }

  public Object getParam() {
    return param;
  }

  public void setParam(Object param) {
    this.param = param;
  }

  public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {

    String sqlStr = this.sql;
    paramList = new ArrayList<>();
    if(this.param instanceof Map){
      for(String key : ((Map<String,String>)this.param).keySet()){
        //保存所有参数名   #{}替换时使用  目前只写了${}替换
        paramList.add(key);
        sqlStr = sqlStr.replace("${"+key+"}","'"+(((Map<String,String>) this.param).get(key))+"'");
      }
    }else{
      //通过反射获取Bean对象的所有属性
    }
    PreparedStatement pstmt = connection.prepareStatement(sqlStr);
    //通过反射获取param参数替换 #{} 的实现暂时未写
    //pstmt.setString(1,"00007028-e261-4d2a-9148-2e28d6bf96fb");
    return pstmt;
  }

  public List<String> getColList() {
    return colList;
  }

  public void setColList(List<String> colList) {
    this.colList = colList;
  }
}
