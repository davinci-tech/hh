package com.huawei.health.h5pro.load.expression;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ExpressionFunctionRegistry {
    public static final Map<String, Object> e;

    public static void register(String str, Object obj) {
        e.put(str, obj);
    }

    public static Object get(String str) {
        return e.get(str);
    }

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        e = concurrentHashMap;
        concurrentHashMap.put("TimeFunctions", new TimeFunctions());
        concurrentHashMap.put("RouteFunctions", new RouteFunctions());
    }
}
