package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnn {
    private static final String b = "PrimaryDevice";

    /* renamed from: a, reason: collision with root package name */
    private lnk f14783a = null;
    private String c;
    private String d;
    private String e;

    public String d() {
        return this.d;
    }

    public void b(String str) {
        JSONObject jSONObject;
        try {
            if (str == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.d = jSONObject.optString("MSISDN");
            this.e = jSONObject.optString("IMSI");
            lnk lnkVar = new lnk();
            JSONObject optJSONObject = jSONObject.optJSONObject("DeviceID");
            if (optJSONObject != null) {
                lnkVar.e(optJSONObject.toString());
            }
            this.f14783a = lnkVar;
            this.c = jSONObject.optString("ServStatus");
            if (loq.b.booleanValue()) {
                loq.c(b, "PrimaryDevice to json string = " + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(b, "Parse response information occured JSONException");
        }
    }
}
