package org.xlb.mybatis;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Jack Xie
 * @Date: 2018/11/2/002 10:43
 * @Description:
 */
public interface TestMapper {
    List<Map<String,String>> select(Map<String,String> param);
}
