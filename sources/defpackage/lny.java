package defpackage;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lny {
    private static final String c = "ResponseGetDevServInfo";

    /* renamed from: a, reason: collision with root package name */
    private String f14794a;
    private String d;
    private String f;
    private int h;
    private int i;
    private int j;
    private lnj b = null;
    private loc g = null;
    private String e = null;

    public String b() {
        return this.f14794a;
    }

    public String a() {
        return this.d;
    }

    public int c() {
        return this.j;
    }

    public lnj d() {
        return this.b;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        JSONObject jSONObject;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.h = jSONObject.optInt("RespSN");
            this.f = jSONObject.optString("ReqName");
            this.j = jSONObject.optInt("ResultCode");
            this.f14794a = jSONObject.optString("ManageUrl");
            this.d = jSONObject.optString("PostData");
            lnj lnjVar = new lnj();
            JSONObject optJSONObject = jSONObject.optJSONObject("MultiSIMServiceInfo");
            if (optJSONObject != null) {
                lnjVar.d(optJSONObject.toString());
            }
            this.b = lnjVar;
            loc locVar = new loc();
            JSONObject optJSONObject2 = jSONObject.optJSONObject("eSIMInfo");
            if (optJSONObject2 != null) {
                locVar.c(optJSONObject2.toString());
            }
            this.b = lnjVar;
            this.e = jSONObject.optString("OldTimeStamp");
            this.i = jSONObject.optInt("Timer1");
            if (loq.b.booleanValue()) {
                loq.c(c, "Parse ResponseInfo to jsonObj result:" + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(c, "ResponseGetDevServInfo-parseResponseInfo JSONException");
        }
    }
}
