package com.huawei.health.device.wifi.entity.utils;

import android.text.TextUtils;
import defpackage.cpw;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class JsonParser {

    public enum JsonType {
        JSON_TYPE_OBJECT,
        JSON_TYPE_ARRAY,
        JSON_TYPE_ERROR
    }

    public static Map<String, Object> c(String str) {
        HashMap hashMap = new HashMap(16);
        String b = b(str);
        if (!TextUtils.isEmpty(b) && d(b) == JsonType.JSON_TYPE_OBJECT) {
            try {
                JSONObject jSONObject = new JSONObject(b.trim());
                JSONArray names = jSONObject.names();
                if (names != null && names.length() > 0) {
                    for (int i = 0; i < names.length(); i++) {
                        hashMap.put(names.getString(i), e(jSONObject.get(names.getString(i))));
                    }
                }
            } catch (JSONException e) {
                cpw.e(true, "JsonParser", "Error Json Parse: ", e.getMessage());
            }
        }
        return hashMap;
    }

    private static Object e(Object obj) {
        Object obj2 = new Object();
        if (obj != null) {
            if (obj instanceof JSONObject) {
                return c(obj.toString());
            }
            return obj instanceof JSONArray ? a(obj.toString()) : obj;
        }
        cpw.e(false, "JsonParser", "fromJson json is null.");
        return obj2;
    }

    private static List<Object> a(String str) {
        String b;
        ArrayList arrayList = new ArrayList(16);
        if (!TextUtils.isEmpty(str) && (b = b(str)) != null && d(b) == JsonType.JSON_TYPE_ARRAY) {
            try {
                JSONArray jSONArray = new JSONArray(b.trim());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(e(jSONArray.get(i)));
                }
            } catch (JSONException e) {
                cpw.e(true, "JsonParser", "Error Json Parse: ", e.getMessage());
            }
        }
        return arrayList;
    }

    public static JsonType d(String str) {
        String b = b(str);
        cpw.c(true, "JsonParser", "getJsonType jsonStr: ", b);
        if (TextUtils.isEmpty(b)) {
            return JsonType.JSON_TYPE_ERROR;
        }
        try {
            new JSONObject(b);
            return JsonType.JSON_TYPE_OBJECT;
        } catch (JSONException e) {
            cpw.e(true, "JsonParser", "Not is JsonObject Type: ", e.getMessage());
            try {
                new JSONArray(b);
                return JsonType.JSON_TYPE_ARRAY;
            } catch (JSONException e2) {
                cpw.e(true, "JsonParser", "Not is JsonArray Type: ", e2.getMessage());
                return JsonType.JSON_TYPE_ERROR;
            }
        }
    }

    private static String b(String str) {
        if (str == null) {
            cpw.c(false, "JsonParser", " json == null !!!");
            return null;
        }
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        if (normalize.contains("{") && normalize.contains("[")) {
            if (normalize.indexOf("{") > normalize.indexOf("[")) {
                return normalize.substring(normalize.indexOf("["), normalize.lastIndexOf("]") + 1);
            }
            return normalize.substring(normalize.indexOf("{"), normalize.lastIndexOf("}") + 1);
        }
        if (!normalize.contains("{") && normalize.contains("[")) {
            return normalize.substring(normalize.indexOf("["), normalize.lastIndexOf("]") + 1);
        }
        if (normalize.contains("[") || !normalize.contains("{")) {
            return null;
        }
        return normalize.substring(normalize.indexOf("{"), normalize.lastIndexOf("}") + 1);
    }

    public static JSONObject b(Map<?, ?> map) {
        Object c;
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (map.containsKey(entry.getKey()) && (c = c(entry.getValue())) != null) {
                        jSONObject.put(entry.getKey().toString(), c);
                    }
                }
            } catch (JSONException e) {
                cpw.e(true, "JsonParser", "Error Map Package Into Json: ", e.getMessage());
            }
        } else {
            cpw.e(true, "JsonParser", "toJsonObject jsonMap is null.");
        }
        return jSONObject;
    }

    private static JSONArray d(List<?> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object c = c(list.get(i));
                    if (c != null) {
                        jSONArray.put(i, c);
                    }
                } catch (JSONException e) {
                    cpw.e(true, "JsonParser", "Error List Package Into Json: ", e.getMessage());
                }
            }
        } else {
            cpw.e(true, "JsonParser", "toJsonArray jsonList is null.");
        }
        return jSONArray;
    }

    private static Object c(Object obj) {
        if (obj instanceof Map) {
            return b((Map<?, ?>) obj);
        }
        return obj instanceof List ? d((List<?>) obj) : obj;
    }
}
