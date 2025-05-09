package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnt {
    private static final String c = "RequestMultiSIMServiceInfo";
    private String b;
    private String d;
    private String e;
    private String g = null;
    private String f = null;
    private String i = null;

    /* renamed from: a, reason: collision with root package name */
    private lnk f14789a = null;

    public void e(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void a(String str) {
        this.b = str;
    }

    public void j(String str) {
        this.g = str;
    }

    public void b(String str) {
        this.f = str;
    }

    public void d(String str) {
        this.i = str;
    }

    public void b(lnk lnkVar) {
        this.f14789a = lnkVar;
    }

    public JSONObject d() {
        try {
            String str = c;
            loq.c(str, "Build RequestMultiSIMServiceInfo JsonObj start");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Request", this.e);
            jSONObject.put("PrimaryIDType", this.d);
            jSONObject.put("PrimaryID", this.b);
            String str2 = this.g;
            if (str2 != null) {
                jSONObject.put("SecondaryIDType", str2);
            }
            String str3 = this.f;
            if (str3 != null) {
                jSONObject.put("SecondaryID", str3);
            }
            String str4 = this.i;
            if (str4 != null) {
                jSONObject.put("SecondaryDeviceName", str4);
            }
            lnk lnkVar = this.f14789a;
            if (lnkVar != null) {
                jSONObject.put("DeviceID", lnkVar.d());
            }
            if (loq.b.booleanValue()) {
                loq.c(str, "Build RequestMultiSIMServiceInfo JsonObj result:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(c, "Build RequestMultiSIMServiceInfo JsonObj occured JSONException");
            return null;
        }
    }
}
