package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnu {
    private static final String e = "RequestServiceProvisionInfo";
    private String b;
    private String c;
    private int f;
    private String g;
    private String d = "ServiceProvisionRequest";

    /* renamed from: a, reason: collision with root package name */
    private lnt f14790a = null;
    private lnv i = null;

    public void a(int i) {
        this.f = i;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void a(String str) {
        this.g = str;
    }

    public void b(lnt lntVar) {
        this.f14790a = lntVar;
    }

    public JSONObject d() {
        try {
            String str = e;
            loq.c(str, "Build RequestServiceProvisionInfo JsonObj start");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ReqSN", this.f);
            jSONObject.put("ReqName", this.d);
            jSONObject.put("PrimaryIDType", this.b);
            jSONObject.put("PrimaryID", this.c);
            jSONObject.put("Services", this.g);
            lnt lntVar = this.f14790a;
            if (lntVar != null) {
                jSONObject.put("MultiSIMServiceRequest", lntVar.d());
            }
            lnv lnvVar = this.i;
            if (lnvVar != null) {
                jSONObject.put("eSIMProfileRequest", lnvVar.e());
            }
            if (loq.b.booleanValue()) {
                loq.c(str, "Build RequestServiceProvisionInfo JsonObj result:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(e, "Build RequestServiceProvisionInfo JsonObj occured JSONException");
            return null;
        }
    }
}
