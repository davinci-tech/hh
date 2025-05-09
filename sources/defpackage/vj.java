package defpackage;

import android.text.TextUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class vj {
    public static JSONObject a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            HiAdLog.w("JsonOpUtil", "create json obj err");
            return null;
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
            HiAdLog.w("JsonOpUtil", "put string " + e.getClass().getSimpleName());
        }
    }

    public static void b(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            HiAdLog.w("JsonOpUtil", "put obj " + e.getClass().getSimpleName());
        }
    }

    public static void c(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
        } catch (JSONException e) {
            HiAdLog.w("JsonOpUtil", "put long " + e.getClass().getSimpleName());
        }
    }

    public static void b(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
        } catch (JSONException e) {
            HiAdLog.w("JsonOpUtil", "put int " + e.getClass().getSimpleName());
        }
    }

    public static void c(JSONObject jSONObject, String str, double d) {
        try {
            jSONObject.put(str, d);
        } catch (JSONException e) {
            HiAdLog.w("JsonOpUtil", "put double " + e.getClass().getSimpleName());
        }
    }

    public static JSONArray e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONArray(str);
        } catch (JSONException unused) {
            HiAdLog.w("JsonOpUtil", "create json array err");
            return null;
        }
    }
}
