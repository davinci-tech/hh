package defpackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lno {
    private static final String b = "RequestAuthSecondInfo";
    private String d;
    private String e;
    private String f;
    private int i;
    private int j;

    /* renamed from: a, reason: collision with root package name */
    private lnk f14784a = null;
    private String g = null;
    private String c = null;

    public void c(int i) {
        this.j = i;
    }

    public void e(String str) {
        this.f = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(a());
        return jSONArray;
    }

    public JSONObject a() {
        try {
            String str = b;
            loq.c(str, "Build RequestAuthSecondInfo JsonObj start");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ReqSN", this.j);
            jSONObject.put("AuthType", this.d);
            jSONObject.put("ReqName", this.f);
            jSONObject.put("Payload", this.e);
            lnk lnkVar = this.f14784a;
            if (lnkVar != null) {
                jSONObject.put("DeviceID", lnkVar.d());
            }
            String str2 = this.g;
            if (str2 != null) {
                jSONObject.put("Sessionid", str2);
            }
            String str3 = this.c;
            if (str3 != null) {
                jSONObject.put("MSISDN", str3);
            }
            jSONObject.put("Timer", this.i);
            if (loq.b.booleanValue()) {
                loq.c(str, "Build RequestAuthSecondInfo JsonObj result:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(b, "Build RequestAuthSecondInfo JsonObj occured JSONException");
            return null;
        }
    }
}
