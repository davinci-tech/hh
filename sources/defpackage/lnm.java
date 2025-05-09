package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnm {
    private static final String c = "PairedDeviceList";

    /* renamed from: a, reason: collision with root package name */
    private String f14782a;
    private String f;
    private String g;
    private String h;
    private String i;
    private int l;
    private String n;
    private String o;
    private lnk d = null;
    private String j = null;
    private String e = null;
    private String b = null;

    public String a() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public String c() {
        return this.n;
    }

    public String e() {
        return this.f14782a;
    }

    public void b(String str) {
        this.f14782a = str;
    }

    public void c(String str) {
        JSONObject jSONObject;
        try {
            if (str == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.h = jSONObject.optString("DevType");
            this.g = jSONObject.optString("ICCID");
            this.i = jSONObject.optString("IMSI");
            this.n = jSONObject.optString("MSISDN");
            this.f = jSONObject.optString("RSPServerAddress");
            this.o = jSONObject.optString("Status");
            this.f14782a = jSONObject.optString("DeviceName");
            this.e = jSONObject.optString("ActivationCode");
            this.b = jSONObject.optString("ConfirmationCode");
            this.l = jSONObject.optInt("MAXReTryTimes");
            this.j = jSONObject.optString("EID");
            lnk lnkVar = new lnk();
            JSONObject optJSONObject = jSONObject.optJSONObject("DeviceID");
            if (optJSONObject != null) {
                lnkVar.e(optJSONObject.toString());
            }
            this.d = lnkVar;
            if (loq.b.booleanValue()) {
                loq.c(c, "PairedDeviceList to json string = " + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(c, "Parse response information occured JSONException");
        }
    }

    public String b() {
        return this.j;
    }
}
