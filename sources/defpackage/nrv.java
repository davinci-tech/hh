package defpackage;

import android.os.SystemClock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.gson.JsonSyntaxException;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.lang.reflect.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class nrv {
    public static <T> T b(String str, Class<T> cls) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Gson gson = new Gson();
        T t = null;
        if (!b(str)) {
            return null;
        }
        try {
            t = (T) gson.fromJson(CommonUtil.z(str), (Class) cls);
        } catch (JsonSyntaxException unused) {
            LogUtil.a("LogEngine_t_JsonUtil", "parse Json Exception");
        }
        LogUtil.c("LogEngine_t_JsonUtil", "fromJson(String json, Class<T> classOfT)   time= ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        return t;
    }

    public static <T> T c(String str, Type type) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        T t = (T) new Gson().fromJson(CommonUtil.z(str), type);
        LogUtil.c("LogEngine_t_JsonUtil", "fromJson(String json, Type typeOfT)   time= ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        return t;
    }

    public static String a(Object obj) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String json = new Gson().toJson(obj);
        LogUtil.c("LogEngine_t_JsonUtil", "toJson(Object object)   time= ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        return json;
    }

    public static String e(Object obj, Type type) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        String json = gsonBuilder.create().toJson(obj, type);
        LogUtil.c("LogEngine_t_JsonUtil", "toJson(Object fromJson, Type typeOfSrc)   time= ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        return json;
    }

    public static String b(Object obj) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        String json = gsonBuilder.create().toJson(obj);
        LogUtil.c("LogEngine_t_JsonUtil", "toJson(Object fromJson, Type typeOfSrc)   time= ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        return json;
    }

    public static <T> T e(String str, Class<T> cls) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        T t = (T) new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(CommonUtil.z(str), (Class) cls);
        LogUtil.c("LogEngine_t_JsonUtil", "fromJsonEnableComplexMapKeySerialization(String json, Class<T> classOfT)   time= ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        return t;
    }

    private static boolean b(String str) {
        try {
            try {
                new JSONObject(str);
                return true;
            } catch (JSONException unused) {
                new JSONArray(str);
                return true;
            }
        } catch (JSONException unused2) {
            return false;
        }
    }
}
