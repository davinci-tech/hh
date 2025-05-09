package com.huawei.maps.offlinedata.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.json.JsonSanitizer;
import java.lang.reflect.Type;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final Gson f6527a = b().create();

    private static Gson a() {
        return f6527a;
    }

    private static GsonBuilder b() {
        return new GsonBuilder().enableComplexMapKeySerialization().serializeSpecialFloatingPointValues().disableHtmlEscaping().setLenient();
    }

    public static String a(Object obj) {
        try {
            return a().toJson(obj);
        } catch (Exception unused) {
            return "";
        }
    }

    public static <T> T a(String str, Class<T> cls) {
        try {
            return (T) a().fromJson(b(str), (Class) cls);
        } catch (Exception e) {
            g.c("GsonUtil", "parseSanitizeJson e: " + e.getMessage());
            return null;
        }
    }

    public static <T> T a(String str, Type type) {
        try {
            return (T) a().fromJson(b(str), type);
        } catch (Exception e) {
            g.c("GsonUtil", "parseSanitizeJson e: " + e.getMessage());
            return null;
        }
    }

    private static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : String.valueOf(JsonSanitizer.sanitize(str));
    }

    public static JsonObject a(String str) {
        return JsonParser.parseString(str).getAsJsonObject();
    }
}
