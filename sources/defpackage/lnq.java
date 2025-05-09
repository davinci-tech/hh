package defpackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnq {
    private static final String b = "RequestAuthInfo";
    private String e;
    private String f;
    private int g;
    private String h = null;
    private String c = null;

    /* renamed from: a, reason: collision with root package name */
    private String f14786a = null;
    private lnk d = null;
    private String j = null;
    private String i = null;

    public void c(int i) {
        this.g = i;
    }

    public void a(String str) {
        this.f = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.f14786a = str;
    }

    public JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(d());
        if (loq.b.booleanValue()) {
            loq.c(b, "Build RequestAuthInfo JsonObjArray result:" + jSONArray.toString());
        }
        return jSONArray;
    }

    public JSONObject d() {
        try {
            String str = b;
            loq.c(str, "Build RequestAuthInfo JsonObj start");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ReqSN", this.g);
            jSONObject.put("ReqName", this.f);
            jSONObject.put("AuthType", this.e);
            String str2 = this.c;
            if (str2 != null) {
                jSONObject.put("Identity", str2);
            }
            String str3 = this.h;
            if (str3 != null) {
                jSONObject.put("MSISDN", str3);
            }
            String str4 = this.f14786a;
            if (str4 != null) {
                jSONObject.put("AuthToken", str4);
            }
            lnk lnkVar = this.d;
            if (lnkVar != null) {
                jSONObject.put("DeviceID", lnkVar.d());
            }
            String str5 = this.j;
            if (str5 != null) {
                jSONObject.put("TerminalType", str5);
            }
            String str6 = this.i;
            if (str6 != null) {
                jSONObject.put("TerminalVersion", str6);
            }
            if (loq.b.booleanValue()) {
                loq.c(str, "Build RequestAuthInfo JsonObj result:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(b, "Build RequestAuthInfo JsonObj occured JSONException");
            return null;
        }
    }
}
