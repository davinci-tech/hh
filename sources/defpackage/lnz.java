package defpackage;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnz {

    /* renamed from: a, reason: collision with root package name */
    private static final String f14795a = "ResponseServiceProvisionInfo";
    private String b;
    private int c;
    private int e;
    private String h = null;
    private String i = null;
    private lob d = null;

    public int a() {
        return this.e;
    }

    public String e() {
        return this.h;
    }

    public String c() {
        return this.i;
    }

    public lob d() {
        return this.d;
    }

    public void b(String str) {
        JSONObject jSONObject;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.c = jSONObject.optInt("RespSN");
            this.b = jSONObject.optString("ReqName");
            this.e = jSONObject.optInt("ResultCode");
            this.h = jSONObject.optString("WSAddress");
            this.i = jSONObject.optString("WSUrlDataPart");
            lob lobVar = new lob();
            JSONObject optJSONObject = jSONObject.optJSONObject("MultiSIMServiceRequestResponse");
            if (optJSONObject != null) {
                lobVar.b(optJSONObject.toString());
            }
            this.d = lobVar;
            if (loq.b.booleanValue()) {
                loq.c(f14795a, "Parse ResponseMultiSIMService to jsonObj result:" + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(f14795a, "Parse ResponseMultiSIMService to jsonObj occured JSONException");
        }
    }
}
