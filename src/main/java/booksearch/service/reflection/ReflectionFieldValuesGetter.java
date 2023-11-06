package booksearch.service.reflection;

import booksearch.model.entity.interfaces.Entity;
import booksearch.model.pair.Pair;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ReflectionFieldValuesGetter {

    public Map<String,String> receiveFieldValues(Object obj){
        Map<String, String> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    String value = field.get(obj).toString();
                    if(field.getType().equals(String.class)){
                        value = String.format("'%s'",value);
                    }
                }
                map.put(field.getName(), field.get(obj).toString());
            } catch(IllegalAccessException illegalAccessException){}
        }
        return map;
    }


}
