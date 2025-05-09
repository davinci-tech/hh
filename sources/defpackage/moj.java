package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class moj {
    public static <T> T e(String str, Class<T> cls) {
        if (str == null || str.isEmpty()) {
            str = "{}";
        }
        try {
            return (T) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("JsonUtil", "JsonSyntaxException");
            return (T) new Gson().fromJson(CommonUtil.z("{}"), (Class) cls);
        }
    }

    public static <T> T b(String str, Type type) {
        if (str == null || str.isEmpty()) {
            str = "{}";
        }
        try {
            return (T) new Gson().fromJson(CommonUtil.z(str), type);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("JsonUtil", "JsonSyntaxException");
            return (T) new Gson().fromJson(CommonUtil.z("{}"), type);
        }
    }

    public static <T> List<T> b(String str, Class<T[]> cls) {
        if (str == null || str.trim().isEmpty()) {
            str = "[]";
        }
        try {
            Object[] objArr = (Object[]) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
            return objArr == null ? Collections.emptyList() : Arrays.asList(objArr);
        } catch (JsonParseException e) {
            LogUtil.b("JsonUtil", "getListFromJson exception: ", ExceptionUtils.d(e));
            return Collections.emptyList();
        }
    }

    public static String e(Object obj) {
        return new Gson().toJson(obj);
    }

    public static Map<String, String> a(String str) {
        if (str == null || str.isEmpty()) {
            str = "{}";
        }
        try {
            return (Map) new Gson().fromJson(CommonUtil.z(str), new TypeToken<Map<String, String>>() { // from class: moj.1
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("JsonUtil", "JsonSyntaxException");
            return Collections.emptyMap();
        }
    }
}
