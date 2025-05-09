package com.huawei.hihealth.util;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import health.compact.a.util.LogUtil;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class HiJsonUtil {

    /* renamed from: a, reason: collision with root package name */
    private static GsonBuilder f4142a;
    private static GsonBuilder e;

    private HiJsonUtil() {
    }

    public static <T> T e(String str, Class<T> cls) {
        try {
            return (T) b().fromJson(d(str), (Class) cls);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("HiJsonUtil", "JsonSyntaxException");
            return (T) b().fromJson(d("{}"), (Class) cls);
        }
    }

    public static <T> T b(String str, Type type) {
        try {
            return (T) b().fromJson(d(str), type);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("HiJsonUtil", "JsonSyntaxException");
            return (T) b().fromJson(d("{}"), type);
        }
    }

    public static <T> T a(String str, Class<T> cls) {
        return (T) a().fromJson(d(str), (Class) cls);
    }

    public static String e(Object obj) {
        return b().toJson(obj);
    }

    public static String d(Object obj, Type type) {
        return b().toJson(obj, type);
    }

    private static String d(String str) {
        return TextUtils.isEmpty(str) ? "" : new StringBuffer(JsonSanitizer.sanitize(str)).toString();
    }

    private static Gson b() {
        GsonBuilder gsonBuilder = f4142a;
        if (gsonBuilder == null) {
            gsonBuilder = b(false);
            f4142a = gsonBuilder;
        }
        return gsonBuilder.create();
    }

    private static Gson a() {
        GsonBuilder gsonBuilder = e;
        if (gsonBuilder == null) {
            gsonBuilder = b(true);
            e = gsonBuilder;
        }
        return gsonBuilder.create();
    }

    private static GsonBuilder b(boolean z) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (z) {
            gsonBuilder.enableComplexMapKeySerialization();
        }
        if (Build.VERSION.SDK_INT > 28) {
            gsonBuilder.registerTypeAdapterFactory(new ContentValuesTypeAdapterFactory());
        }
        return gsonBuilder;
    }
}
