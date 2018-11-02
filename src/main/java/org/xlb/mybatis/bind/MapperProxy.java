package org.xlb.mybatis.bind;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.xlb.mybatis.session.SqlSession;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 11:23
 * @Description:
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

  private static final long serialVersionUID = -6424540398559729838L;
  private final SqlSession sqlSession;
  private final Class<T> mapperInterface;

  public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
    this.sqlSession = sqlSession;
    this.mapperInterface = mapperInterface;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    boolean hasString = false;
    boolean hasMap = false;
    boolean hasOtherObj = false;
    Map<String,String> param = new HashMap<>();
    Object retObj = new Object();
    Object o;
    // 参数处理 后续优化
    for(int i=0; i<args.length; i++){
      o = args[i];
      if(o instanceof String){
        param.put(method.getParameters()[i].getName(),o.toString());
        hasString = true;
        retObj = param;
      } else if(o instanceof Map){
        param = (Map<String, String>) o;
        hasMap = true;
        retObj = param;
      }else{
        hasOtherObj = true;
        retObj = o;
      }
    }
    int flag = 0;
    if(hasString) flag++;
    if(hasMap) flag++;
    if(hasOtherObj) flag++;
    if(flag > 1)
      throw new Exception("错误的参数格式");

    String name = mapperInterface.getName()+":"+method.getName();
    return sqlSession.queryList(name,retObj);
  }

}
