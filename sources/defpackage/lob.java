package defpackage;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lob {
    private static final String c = "ResponseMultiSIMService";

    /* renamed from: a, reason: collision with root package name */
    private String f14797a;
    private int b;
    private String e;

    public String a() {
        return this.f14797a;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.b;
    }

    public void b(String str) {
        JSONObject jSONObject;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.f14797a = jSONObject.optString("ManageUrl");
            this.e = jSONObject.optString("PostData");
            this.b = jSONObject.optInt("Timer2");
            if (loq.b.booleanValue()) {
                loq.c(c, "Parse ResponseInfo to jsonObj result:" + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(c, "Parse ResponseInfo to jsonObj occured JSONException");
        }
    }
}
