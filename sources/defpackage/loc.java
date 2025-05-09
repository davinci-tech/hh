package defpackage;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class loc {
    private static final String b = "ResponseeSIMProfileInfo";

    /* renamed from: a, reason: collision with root package name */
    private String f14798a;
    private String c;
    private String d;
    private String e;

    public void c(String str) {
        JSONObject jSONObject;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.e = jSONObject.optString("AC_Format");
            this.f14798a = jSONObject.optString("SM-DP+Address");
            this.d = jSONObject.optString("AC_Token");
            this.c = jSONObject.optString("SM-DP+OID");
            if (loq.b.booleanValue()) {
                loq.c(b, "Parse ResponseInfo to jsonObj result:" + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(b, "ResponseeSIMProfileInfo-parseResponseInfo JSONException");
        }
    }
}
