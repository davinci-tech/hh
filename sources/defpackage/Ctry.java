package defpackage;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: try, reason: invalid class name */
/* loaded from: classes7.dex */
public class Ctry {
    public static String e(String str, String str2, String str3, Object obj) {
        JSONObject jSONObject;
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject(str);
            } else {
                jSONObject = new JSONObject();
            }
            jSONObject.put(str3, obj);
            return jSONObject.toString();
        } catch (JSONException unused) {
            tov.c("ClientJsonUtil", str2 + " catch JSONException");
            return "";
        }
    }

    public static boolean a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            tov.c("ClientJsonUtil", str2 + " jsonString is empty");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(str3)) {
                return jSONObject.getBoolean(str3);
            }
            tov.c("ClientJsonUtil", str2 + " input json not has key");
            return false;
        } catch (JSONException unused) {
            tov.c("ClientJsonUtil", str2 + " catch JSONException");
            return false;
        }
    }

    public static String d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            tov.c("ClientJsonUtil", str2 + " jsonString is empty");
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(str3)) {
                return jSONObject.getString(str3);
            }
            tov.c("ClientJsonUtil", str2 + " input json not has key");
            return "";
        } catch (JSONException unused) {
            tov.c("ClientJsonUtil", str2 + " catch JSONException");
            return "";
        }
    }

    public static int d(String str, String str2, String str3, int i) {
        if (TextUtils.isEmpty(str)) {
            tov.c("ClientJsonUtil", str2 + " jsonString is empty");
            return i;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(str3)) {
                return jSONObject.getInt(str3);
            }
            tov.c("ClientJsonUtil", str2 + " input json not has key");
            return i;
        } catch (JSONException unused) {
            tov.c("ClientJsonUtil", str2 + " catch JSONException");
            return i;
        }
    }

    public static long e(String str, String str2, String str3, long j) {
        if (TextUtils.isEmpty(str)) {
            tov.c("ClientJsonUtil", str2 + " jsonString is empty");
            return j;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(str3)) {
                return jSONObject.getLong(str3);
            }
            tov.c("ClientJsonUtil", str2 + " input json not has key");
            return j;
        } catch (JSONException unused) {
            tov.c("ClientJsonUtil", str2 + " catch JSONException");
            return j;
        }
    }
}
