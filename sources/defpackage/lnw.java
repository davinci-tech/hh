package defpackage;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnw {
    private static final String c = "ResponseAuthSecondInfo";

    /* renamed from: a, reason: collision with root package name */
    private int f14792a;
    private String b;
    private String d;
    private String e;
    private int h;

    public int d() {
        return this.f14792a;
    }

    public int b() {
        return this.h;
    }

    public String c() {
        return this.d;
    }

    public String e() {
        return this.b;
    }

    public String a() {
        return this.e;
    }

    public void b(String str) {
        JSONArray jSONArray;
        if (lop.c(str)) {
            try {
                int intValue = Integer.valueOf(str).intValue();
                this.h = intValue;
                if (408 == intValue) {
                    this.h = 1008;
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                loq.c(c, "parseResponseAuthInfoArray NumberFormatException");
            }
        }
        try {
            if (TextUtils.isEmpty(str)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(str);
            }
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            if (jSONObject != null) {
                d(jSONObject.toString());
            }
            if (loq.b.booleanValue()) {
                loq.c(c, "Parse ResponseAuthInfoArray to jsonObj result:" + jSONArray.toString());
            }
        } catch (JSONException unused2) {
            loq.c(c, "ResponseAuthSecondInfo-parseResponseAuthInfoArray JSONException");
        }
    }

    public void d(String str) {
        JSONObject jSONObject;
        if (lop.c(str)) {
            try {
                int intValue = Integer.valueOf(str).intValue();
                this.h = intValue;
                if (408 == intValue) {
                    this.h = 1008;
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                loq.c(c, "parseResponseAuthInfo NumberFormatException");
            }
        }
        try {
            if (str == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.f14792a = jSONObject.optInt("RespSN");
            this.b = jSONObject.optString("ReqName");
            this.d = jSONObject.optString("AuthToken");
            this.h = jSONObject.optInt("ResultCode");
            this.e = jSONObject.optString("MSISDN");
            if (loq.b.booleanValue()) {
                loq.c(c, "Parse ResponseAuthInfo to jsonObj result:" + jSONObject.toString());
            }
        } catch (JSONException unused2) {
            loq.c(c, "ResponseAuthSecondInfo-parseResponseAuthInfo  JSONException");
        }
    }
}
