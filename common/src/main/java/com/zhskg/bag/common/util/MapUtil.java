package com.zhskg.bag.common.util;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author jean
 * @date 2018/10/7
 * desc:
 */
public class MapUtil {


    /**
     * 对象转map 只转一层
     * @param obj
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> convertToMap(Object obj)
            throws IllegalAccessException {

        HashMap<String, Object> map = Maps.newHashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            boolean accessFlag = field.isAccessible();
            field.setAccessible(true);
            Object o = field.get(obj);
            if (o != null) {
                map.put(varName, o.toString());
            }
            field.setAccessible(accessFlag);
        }
        return map;
    }
}
