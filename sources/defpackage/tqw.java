package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.x2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class tqw {
    public static String c(String str) {
        JSONObject e = e(str);
        if (e == null) {
            tos.d("RequestJsonParser", "getRequestType requestHeaderObject is null");
            return "";
        }
        if (!e.has(x2.REQUEST_TYPE)) {
            tos.d("RequestJsonParser", "getRequestType requestHeaderObject not has request_type");
            return "";
        }
        try {
            return e.getString(x2.REQUEST_TYPE);
        } catch (JSONException unused) {
            tos.d("RequestJsonParser", "getRequestType JSONException");
            return "";
        }
    }

    public static String[] a(String str) {
        JSONObject b = b(str);
        String[] strArr = new String[0];
        if (b == null) {
            tos.d("RequestJsonParser", "getRequestBodyPermission requestBodyObject is null");
            return strArr;
        }
        if (!b.has("permissions")) {
            tos.d("RequestJsonParser", "getRequestBodyPermission requestBodyObject not has permissions");
            return strArr;
        }
        try {
            JSONArray jSONArray = b.getJSONArray("permissions");
            strArr = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                strArr[i] = jSONArray.getString(i);
            }
        } catch (JSONException unused) {
            tos.e("RequestJsonParser", "getRequestBodyPermission JSONException");
        }
        return strArr;
    }

    public static String d(String str) {
        JSONObject b = b(str);
        if (b == null) {
            tos.d("RequestJsonParser", "getRequestBodyPackageName requestBodyObject is null");
            return "";
        }
        if (!b.has("package_name")) {
            tos.d("RequestJsonParser", "getRequestBodyPackageName requestBodyObject not has package_name");
            return "";
        }
        try {
            return b.getString("package_name");
        } catch (JSONException unused) {
            tos.e("RequestJsonParser", "getRequestBodyPackageName JSONException");
            return "";
        }
    }

    private static JSONObject e(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.d("RequestJsonParser", "getRequestHeader requestJson is empty");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("request_header")) {
                tos.d("RequestJsonParser", "getRequestHeader not has request_header");
                return null;
            }
            return jSONObject.getJSONObject("request_header");
        } catch (JSONException unused) {
            tos.e("RequestJsonParser", "getRequestHeader JSONException");
            return null;
        }
    }

    private static JSONObject b(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.d("RequestJsonParser", "getRequestBody requestJson is empty");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("request_body")) {
                tos.d("RequestJsonParser", "getRequestBody not has request_body");
                return null;
            }
            return jSONObject.getJSONObject("request_body");
        } catch (JSONException unused) {
            tos.e("RequestJsonParser", "getRequestBody JSONException");
            return null;
        }
    }
}
