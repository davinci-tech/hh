package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class trk {
    public static boolean c(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null) {
            tov.c("JsonUtil", "getBoolean jsonObject is null");
            return z;
        }
        if (TextUtils.isEmpty(str)) {
            tov.c("JsonUtil", "getBoolean name is empty");
            return z;
        }
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException unused) {
            tov.c("JsonUtil", "getBoolean catch JSONException");
            return z;
        }
    }

    public static int a(JSONObject jSONObject, String str, int i) {
        if (jSONObject == null) {
            tov.c("JsonUtil", "getInt jsonObject is null");
            return i;
        }
        if (TextUtils.isEmpty(str)) {
            tov.c("JsonUtil", "getInt name is empty");
            return i;
        }
        try {
            return jSONObject.getInt(str);
        } catch (JSONException unused) {
            tov.c("JsonUtil", "getInt catch JSONException");
            return i;
        }
    }

    public static String b(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            tov.c("JsonUtil", "getString jsonObject is null");
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            tov.c("JsonUtil", "getString name is empty");
            return "";
        }
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
            tov.c("JsonUtil", "getString input json not has name");
            return "";
        } catch (JSONException unused) {
            tov.c("JsonUtil", "getString catch JSONException");
            return "";
        }
    }

    public static JSONObject d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            tov.c("JsonUtil", "getJSONObject jsonString is null");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            tov.c("JsonUtil", "getJSONObject name is null");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(b(str));
            if (jSONObject.has(str2)) {
                return jSONObject.getJSONObject(str2);
            }
            tov.c("JsonUtil", "getString input json not has name");
            return null;
        } catch (JSONException unused) {
            tov.c("JsonUtil", "getString catch JSONException");
            return null;
        }
    }

    public static JSONObject e(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            tov.c("JsonUtil", "getJSONObject jsonArray is null");
            return null;
        }
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException unused) {
            tov.c("JsonUtil", "getJSONObject catch JSONException");
            return null;
        }
    }

    public static JSONArray b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            tov.c("JsonUtil", "getJSONArray jsonString is empty");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            tov.c("JsonUtil", "getJSONArray name is empty");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(b(str));
            if (jSONObject.has(str2)) {
                return jSONObject.getJSONArray(str2);
            }
            tov.c("JsonUtil", "getJSONArray input json not has name");
            return null;
        } catch (JSONException unused) {
            tov.c("JsonUtil", "getJSONArray catch JSONException");
            return null;
        }
    }

    public static <T> T b(String str, Class<T> cls) {
        Gson gson;
        T t = null;
        if (str == null) {
            tos.e("JsonUtil", "fromJson --> json is null");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            gson = new Gson();
        } catch (JsonSyntaxException unused) {
            tos.e("JsonUtil", "fromJson --> JsonSyntaxException.");
        }
        if (!e(str)) {
            return null;
        }
        t = (T) gson.fromJson(b(str), (Class) cls);
        tos.b("JsonUtil", "fromJson(String json, Class<T> classOfT), time = " + (System.currentTimeMillis() - currentTimeMillis));
        return t;
    }

    public static boolean e(String str) {
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

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new StringBuffer(JsonSanitizer.sanitize(str)).toString();
    }
}
