package com.huawei.hms.mlsdk.asr.engine.utils;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static JSONArray getJsonArray(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getJSONArray(str);
            }
            return null;
        } catch (JSONException unused) {
            SmartLogger.e(TAG, "GetJsonArray JSONException fieldName=" + str);
            return null;
        }
    }

    public static String getJsonStringField(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null) {
            return "";
        }
        try {
            return jSONObject.has(str) ? jSONObject.getString(str) : "";
        } catch (JSONException unused) {
            SmartLogger.e(TAG, "getJsonFiled failed.JSONException");
            return "";
        }
    }

    public static JSONObject parseJson(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            SmartLogger.e(TAG, "parseJson failed.JSONException");
            return null;
        }
    }

    public static JSONArray getJsonArray(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException unused) {
            SmartLogger.e(TAG, "parseJson failed.JSONException");
            return null;
        }
    }
}
