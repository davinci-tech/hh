package com.huawei.watchface.mvp.model.helper;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.huawei.watchface.utils.HwLog;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public final class GsonHelper {
    private static final String TAG = "GsonHelper";
    private static volatile Gson gson = new Gson();

    private GsonHelper() {
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str) || cls == null) {
            return null;
        }
        try {
            return (T) new Gson().fromJson(str, (Class) cls);
        } catch (Exception e) {
            HwLog.e(cls.getName(), "GsonHelper fromJson Class Exception: " + HwLog.printException(e));
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls, Gson gson2) {
        if (TextUtils.isEmpty(str) || cls == null) {
            return null;
        }
        if (gson2 == null) {
            try {
                gson2 = new Gson();
            } catch (Exception e) {
                HwLog.e(cls.getName(), "GsonHelper gson fromJson Class Exception: " + HwLog.printException(e));
                return null;
            }
        }
        return (T) gson2.fromJson(str, (Class) cls);
    }

    public static <T> T fromJsonToArray(String str, Type type) {
        if (TextUtils.isEmpty(str) || type == null) {
            return null;
        }
        try {
            if (gson == null) {
                gson = new Gson();
            }
            return (T) gson.fromJson(str, type);
        } catch (Exception e) {
            HwLog.e(TAG, "fromJsonToArray -- GsonHelper gson fromJson Class Exception: " + HwLog.printException(e));
            return null;
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (gson != null) {
                return gson.toJson(obj);
            }
            return null;
        } catch (Exception e) {
            HwLog.e(TAG, "GsonHelper toJson Class Exception: " + HwLog.printException(e));
            return null;
        }
    }

    public static Gson getGson() {
        return new GsonBuilder().registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() { // from class: com.huawei.watchface.mvp.model.helper.GsonHelper.1
            @Override // com.google.gson.JsonDeserializer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public List<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                if (jsonElement.isJsonArray()) {
                    HwLog.i(GsonHelper.TAG, "GsonHelper getGson isJsonArray");
                    JsonArray asJsonArray = jsonElement.getAsJsonArray();
                    if (type instanceof ParameterizedType) {
                        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < asJsonArray.size(); i++) {
                            arrayList.add(jsonDeserializationContext.deserialize(asJsonArray.get(i), type2));
                        }
                        return arrayList;
                    }
                    HwLog.i(GsonHelper.TAG, "getGson() invalid type.");
                    return Collections.EMPTY_LIST;
                }
                HwLog.i(GsonHelper.TAG, "GsonHelper getGson !isJsonArray");
                return Collections.EMPTY_LIST;
            }
        }).create();
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) fromJson(str, type, (Gson) null);
    }

    public static <T> T fromJson(String str, Type type, Gson gson2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (gson2 == null) {
            try {
                gson2 = buildGson();
            } catch (AssertionError e) {
                HwLog.e(TAG, "fromJson AssertionError:" + HwLog.printException((Error) e));
                return null;
            } catch (Exception e2) {
                HwLog.e(TAG, "fromJson Exception:" + HwLog.printException(e2));
                return null;
            }
        }
        return (T) gson2.fromJson(str, type);
    }

    private static Gson buildGson() {
        return new Gson();
    }
}
