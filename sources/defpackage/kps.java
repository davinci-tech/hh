package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.lang.reflect.Type;

/* loaded from: classes5.dex */
public class kps {
    public static <T> T e(String str, Class<T> cls) {
        T t;
        long currentTimeMillis = System.currentTimeMillis();
        Gson gson = new Gson();
        try {
            t = (T) gson.fromJson(CommonUtil.z(str), (Class) cls);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("LogEngine_t_JsonUtil", "JsonSyntaxException");
            t = (T) gson.fromJson(CommonUtil.z("{}"), (Class) cls);
        }
        LogUtil.c("LogEngine_t_JsonUtil", "fromJson(String json, Class<T> classOfT)   time= ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return t;
    }

    public static <T> T e(String str, Type type) {
        T t;
        long currentTimeMillis = System.currentTimeMillis();
        Gson gson = new Gson();
        try {
            t = (T) gson.fromJson(CommonUtil.z(str), type);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("LogEngine_t_JsonUtil", "JsonSyntaxException");
            t = (T) gson.fromJson(CommonUtil.z("{}"), type);
        }
        LogUtil.c("LogEngine_t_JsonUtil", "fromJson(String json, Type typeOfT)   time= ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return t;
    }

    public static String e(Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        String json = new Gson().toJson(obj);
        LogUtil.c("LogEngine_t_JsonUtil", "toJson(Object object)   time= ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return json;
    }

    public static String a(Object obj, Type type) {
        long currentTimeMillis = System.currentTimeMillis();
        String json = new Gson().toJson(obj, type);
        LogUtil.c("LogEngine_t_JsonUtil", "toJson(Object fromJson, Type typeOfSrc)   time= ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return json;
    }
}
