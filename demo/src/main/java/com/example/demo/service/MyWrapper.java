package com.example.demo.service;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
public class MyWrapper{
     Map<Method,Integer> map;

    public MyWrapper() {
        map = new HashMap<>();
    }

    public MyWrapper eq(Method m,Integer num) {
        map.put(m,num);
        return this;
    }
}
