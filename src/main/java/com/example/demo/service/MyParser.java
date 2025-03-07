package com.example.demo.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyParser {
    private MyWrapper myWrapper;

    public MyParser (MyWrapper myWrapper){
        this.myWrapper = myWrapper;
    }

    public void parse(Object t, List<Integer> list) throws InvocationTargetException, IllegalAccessException {
        Map<Method,Integer> map=myWrapper.getMap();
        for (Map.Entry<Method, Integer> entry : map.entrySet()) {
            Method method = entry.getKey();
            Integer num = entry.getValue();
            method.invoke(t,list.get(num));
        }
    }
}
