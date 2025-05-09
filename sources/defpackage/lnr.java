package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnr {
    private static final String e = "RequestGetDevServInfo";

    /* renamed from: a, reason: collision with root package name */
    private String f14787a;
    private String b;
    private String c;
    private int g;
    private String j;
    private String h = null;
    private String i = null;
    private String d = null;

    public void d(int i) {
        this.g = i;
    }

    public void d(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void a(String str) {
        this.f14787a = str;
    }

    public void f(String str) {
        this.j = str;
    }

    public void j(String str) {
        this.h = str;
    }

    public void e(String str) {
        this.i = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public JSONObject c() {
        try {
            String str = e;
            loq.c(str, "Build RequestGetDevServInfo JsonObj start");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ReqSN", this.g);
            jSONObject.put("ReqName", this.b);
            jSONObject.put("PrimaryIDType", this.c);
            jSONObject.put("PrimaryID", this.f14787a);
            jSONObject.put("Services", this.j);
            String str2 = this.h;
            if (str2 != null) {
                jSONObject.put("SecondaryIDType", str2);
            }
            String str3 = this.i;
            if (str3 != null) {
                jSONObject.put("SecondaryID", str3);
            }
            String str4 = this.d;
            if (str4 != null) {
                jSONObject.put("OldTimeStamp", str4);
            }
            if (loq.b.booleanValue()) {
                loq.c(str, "Build RequestGetDevServInfo JsonObj result:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(e, "Build RequestGetDevServInfo JsonObj occured JSONException");
            return null;
        }
    }
}
