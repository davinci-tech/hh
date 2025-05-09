package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.x2;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class iqs {
    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestType requestJson is empty");
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(x2.REQUEST_TYPE)) {
                ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestType not has request_type");
                return "";
            }
            return jSONObject.getString(x2.REQUEST_TYPE);
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "getRequestType JSONException: ", e.getMessage());
            return "";
        }
    }

    public static int[] d(String str) {
        JSONObject j = j(str);
        int[] iArr = new int[0];
        if (j == null) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestScopeWriteTypes requestDataObject is null");
            return iArr;
        }
        if (!j.has("request_write_permissions")) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestScopeWriteTypes requestDataObject not has permissions");
            return iArr;
        }
        try {
            JSONArray jSONArray = j.getJSONArray("request_write_permissions");
            iArr = new int[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                iArr[i] = jSONArray.getInt(i);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "getRequestScopeWriteTypes JSONException: ", e.getMessage());
        }
        return iArr;
    }

    public static int[] c(String str) {
        JSONObject j = j(str);
        int[] iArr = new int[0];
        if (j == null) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestScopeReadTypes requestDataObject is null");
            return iArr;
        }
        if (!j.has("request_read_permissions")) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestScopeReadTypes requestDataObject not has permissions");
            return iArr;
        }
        try {
            JSONArray jSONArray = j.getJSONArray("request_read_permissions");
            iArr = new int[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                iArr[i] = jSONArray.getInt(i);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "getRequestScopeReadTypes JSONException: ", e.getMessage());
        }
        return iArr;
    }

    public static String a(String str) {
        JSONObject j = j(str);
        if (j == null) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestPackageName requestJson is null");
            return "";
        }
        if (!j.has("request_package_name")) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestPackageName requestJson not package_name");
            return "";
        }
        try {
            return j.getString("request_package_name");
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "getRequestPackageName JSONException: ", e.getMessage());
            return "";
        }
    }

    public static int e(String str) {
        JSONObject j = j(str);
        if (j == null) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestUid requestJson is null");
            return -1;
        }
        if (!j.has("request_uid")) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestUid requestJson not package_name");
            return -1;
        }
        try {
            return j.getInt("request_uid");
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "getRequestUid JSONException: ", e.getMessage());
            return -1;
        }
    }

    public static JSONObject b(String str, int i, int[] iArr, int[] iArr2) {
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 : iArr) {
            jSONArray.put(i2);
        }
        for (int i3 : iArr2) {
            jSONArray2.put(i3);
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("request_uid", i);
            jSONObject2.put("request_package_name", str);
            jSONObject2.put("request_read_permissions", jSONArray2);
            jSONObject2.put("request_write_permissions", jSONArray);
            jSONObject.put(x2.REQUEST_TYPE, "request_auth");
            jSONObject.put("request_auth_data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "bradgeAuthDataToJson JSONException: ", e.getMessage());
            return jSONObject;
        }
    }

    public static JSONObject a(String str, int i, int[] iArr, int[] iArr2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("target_package_name", ioz.d);
        jSONObject.put("target_activity_name", "com.huawei.hihealthservice.hihealthkit.BridgeServiceActivity");
        jSONObject.put("request_data", b(str, i, iArr, iArr2));
        return jSONObject;
    }

    private static JSONObject j(String str) {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestAuthData requestJson is empty");
            return jSONObject;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            if (!jSONObject2.has("request_auth_data")) {
                ReleaseLogUtil.d("HiH_BridgeJsonUtil", "getRequestAuthData not has request_auth_data");
                return jSONObject;
            }
            return jSONObject2.getJSONObject("request_auth_data");
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_BridgeJsonUtil", "getRequestAuthData JSONException: ", e.getMessage());
            return jSONObject;
        }
    }
}
