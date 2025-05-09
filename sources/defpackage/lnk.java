package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnk {
    private static final String c = "DeviceID";

    /* renamed from: a, reason: collision with root package name */
    private String f14780a = null;
    private String b = null;
    private String d = null;

    public void b(String str) {
        this.f14780a = str;
    }

    public void a(String str) {
        this.d = str;
    }

    public JSONObject d() {
        try {
            String str = c;
            loq.c(str, "Build DeviceID json Object start");
            JSONObject jSONObject = new JSONObject();
            String str2 = this.f14780a;
            if (str2 != null) {
                jSONObject.put("IMEI", str2);
            }
            String str3 = this.b;
            if (str3 != null) {
                jSONObject.put("MEID", str3);
            }
            String str4 = this.d;
            if (str4 != null) {
                jSONObject.put("SN", str4);
            }
            if (loq.b.booleanValue()) {
                loq.c(str, "Build DeviceID json Object end, json-string:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(c, "Build DeviceID json occured JSONException.");
            return null;
        }
    }

    void e(String str) {
        JSONObject jSONObject;
        try {
            if (str == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.f14780a = jSONObject.optString("IMEI");
            this.b = jSONObject.optString("MEID");
            this.d = jSONObject.optString("SN");
            if (loq.b.booleanValue()) {
                loq.c(c, "Parse response json info, jsonObj.toString() = " + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(c, "Parse response json info occured JSONException");
        }
    }
}
