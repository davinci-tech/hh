package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnv {
    private static final String e = "RequesteSIMProfileInfo";

    /* renamed from: a, reason: collision with root package name */
    private String f14791a;
    private String b;
    private String c;
    private String f;
    private String g;
    private String i = null;
    private lnk d = null;

    public JSONObject e() {
        try {
            String str = e;
            loq.c(str, "Build RequesteSIMProfileInfo JsonObj start");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Request", this.f);
            jSONObject.put("Mainid", this.g);
            jSONObject.put("Idtype", this.f14791a);
            String str2 = this.c;
            if (str2 != null) {
                jSONObject.put("EID", str2);
            }
            String str3 = this.b;
            if (str3 != null) {
                jSONObject.put("ICCID", str3);
            }
            String str4 = this.i;
            if (str4 != null) {
                jSONObject.put("IMSI", str4);
            }
            lnk lnkVar = this.d;
            if (lnkVar != null) {
                jSONObject.put("DeviceID", lnkVar.d());
            }
            if (loq.b.booleanValue()) {
                loq.c(str, "Build RequesteSIMProfileInfo JsonObj result:" + jSONObject.toString());
            }
            return jSONObject;
        } catch (JSONException unused) {
            loq.b(e, "Build RequesteSIMProfileInfo JsonObj occured JSONException");
            return null;
        }
    }
}
