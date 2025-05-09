package com.huawei.hms.iap.util;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b {
    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new JSONObject(str).optString(str2);
        } catch (JSONException unused) {
            HMSLog.e("IapJsonUtil", "getStringValue JSONException");
            return "";
        }
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new JSONObject(str).optBoolean(str2);
        } catch (JSONException unused) {
            HMSLog.e("IapJsonUtil", "getBooleanValue JSONException");
            return false;
        }
    }

    public static <V> String a(String str, String str2, V v) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put(str2, v);
            return jSONObject.toString();
        } catch (JSONException unused) {
            HMSLog.e("IapJsonUtil", "appendJsonParam, JSONException");
            return str;
        }
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, jSONObject2.opt(next));
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            HMSLog.e("IapJsonUtil", "appendJsonParam exception");
            return str;
        }
    }
}
