package defpackage;

import android.text.TextUtils;
import com.huawei.hwidauth.datatype.DeviceInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class kqj {
    public static String d(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("deviceType", "0");
                jSONObject2.put(DeviceInfo.TAG_DEVICE_ID, str);
                jSONArray.put(jSONObject2);
            }
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("deviceType", "6");
                jSONObject3.put(DeviceInfo.TAG_DEVICE_ID, str2);
                jSONArray.put(jSONObject3);
            }
            if (!TextUtils.isEmpty(str3)) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("deviceType", "8");
                jSONObject4.put(DeviceInfo.TAG_DEVICE_ID, str3);
                jSONArray.put(jSONObject4);
            }
            if (!TextUtils.isEmpty(str4)) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("deviceType", "9");
                jSONObject5.put(DeviceInfo.TAG_DEVICE_ID, str4);
                jSONArray.put(jSONObject5);
            }
            jSONObject.put("deviceInfo", jSONArray);
        } catch (JSONException unused) {
            ksy.c("DeviceUtil", "getDeviceInfo JSONException", true);
        }
        return jSONObject.toString();
    }
}
