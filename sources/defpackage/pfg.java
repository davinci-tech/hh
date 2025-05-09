package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pfg {
    public static String d(JSONObject jSONObject, String str) {
        if (jSONObject != null && !jSONObject.isNull(str)) {
            try {
                return !jSONObject.isNull(str) ? jSONObject.getString(str) : "";
            } catch (JSONException e) {
                LogUtil.b("ConfiguredPage_ParseUtil", "getJsonValue meet json exception: ", e.getMessage());
            }
        }
        return "";
    }

    public static int a(JSONObject jSONObject, String str) {
        if (jSONObject != null && !jSONObject.isNull(str)) {
            try {
                if (jSONObject.isNull(str)) {
                    return 0;
                }
                return jSONObject.getInt(str);
            } catch (JSONException e) {
                LogUtil.b("ConfiguredPage_ParseUtil", "getJsonValue meet json exception: ", e.getMessage());
            }
        }
        return 0;
    }
}
