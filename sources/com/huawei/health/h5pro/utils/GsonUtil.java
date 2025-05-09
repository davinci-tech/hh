package com.huawei.health.h5pro.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class GsonUtil {
    public static GsonBuilder e;

    public static <T> String toJson(T t) {
        if (t != null) {
            try {
                return new Gson().toJson(t);
            } catch (JsonIOException unused) {
                LogUtil.e("H5pro_GsonUtil", "toJson: JsonIOException");
            }
        }
        return "";
    }

    public static Map<String, Object> parseMapJson(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return (Map) getGson().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.huawei.health.h5pro.utils.GsonUtil.1
                }.getType());
            } catch (JsonSyntaxException unused) {
                LogUtil.e("H5pro_GsonUtil", "parseMapJson: JsonSyntaxException");
            }
        }
        return new HashMap();
    }

    public static <T> T parseJson(String str, Class<T> cls, Gson gson) {
        if (TextUtils.isEmpty(str) || gson == null) {
            return null;
        }
        try {
            return (T) gson.fromJson(str, (Class) cls);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("H5pro_GsonUtil", "parseJson: JsonSyntaxException " + cls.getSimpleName());
            return null;
        }
    }

    public static <T> T parseJson(String str, Class<T> cls) {
        return (T) parseJson(str, cls, new Gson());
    }

    public static <T> T parseContainsMapJson(String str, Class<T> cls) {
        return (T) parseJson(str, cls, getGson());
    }

    public static Object jsonElementParse(JsonElement jsonElement) {
        if (jsonElement.isJsonArray()) {
            ArrayList arrayList = new ArrayList();
            Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
            while (it.hasNext()) {
                arrayList.add(jsonElementParse(it.next()));
            }
            return arrayList;
        }
        if (jsonElement.isJsonObject()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                hashMap.put(entry.getKey(), jsonElementParse(entry.getValue()));
            }
            return hashMap;
        }
        if (!jsonElement.isJsonPrimitive()) {
            return null;
        }
        JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
        if (asJsonPrimitive.isBoolean()) {
            return Boolean.valueOf(asJsonPrimitive.getAsBoolean());
        }
        if (asJsonPrimitive.isString()) {
            return asJsonPrimitive.getAsString();
        }
        if (!asJsonPrimitive.isNumber()) {
            return null;
        }
        Number asNumber = asJsonPrimitive.getAsNumber();
        return Math.ceil(asNumber.doubleValue()) == ((double) asNumber.intValue()) ? Integer.valueOf(asNumber.intValue()) : Math.ceil(asNumber.doubleValue()) == ((double) asNumber.longValue()) ? Long.valueOf(asNumber.longValue()) : Double.valueOf(asNumber.doubleValue());
    }

    public static Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>() { // from class: com.huawei.health.h5pro.utils.GsonUtil.2
        }.getType(), new JsonDeserializer<Map<String, Object>>() { // from class: com.huawei.health.h5pro.utils.GsonUtil.3
            @Override // com.google.gson.JsonDeserializer
            public Map<String, Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                Object jsonElementParse = GsonUtil.jsonElementParse(jsonElement);
                return jsonElementParse instanceof Map ? (Map) jsonElementParse : Collections.emptyMap();
            }
        }).create();
    }

    public static Gson getContentValueGson() {
        GsonBuilder gsonBuilder = e;
        if (gsonBuilder == null) {
            gsonBuilder = createContentValueGsonBuilder();
            e = gsonBuilder;
        }
        return gsonBuilder.create();
    }

    public static GsonBuilder createContentValueGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new ContentValuesTypeAdapterFactory());
        return gsonBuilder;
    }

    public static <T> String contentValueToJson(T t) {
        if (t != null) {
            try {
                return getContentValueGson().toJson(t);
            } catch (JsonIOException | IllegalArgumentException unused) {
                LogUtil.e("H5pro_GsonUtil", "toJson: JsonIOException");
            }
        }
        return "";
    }
}
