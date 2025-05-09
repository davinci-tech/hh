package defpackage;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnx {
    private static final String d = "ResponseAuthFirstInfo";
    private String b;
    private int f;
    private int h;
    private int i;
    private String e = null;
    private String c = null;
    private String g = null;

    /* renamed from: a, reason: collision with root package name */
    private String f14793a = null;
    private String j = null;

    public int a() {
        return this.i;
    }

    public int e() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public void e(String str) {
        JSONArray jSONArray;
        if (lop.c(str)) {
            try {
                int intValue = Integer.valueOf(str).intValue();
                this.h = intValue;
                if (408 == intValue || 99 == intValue || 98 == intValue) {
                    this.h = 1008;
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                loq.c(d, "parseResponseAuthInfoArray NumberFormatException");
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
                a(jSONObject.toString());
            }
            if (loq.b.booleanValue()) {
                loq.c(d, "Parse response authinfo Array json string = " + jSONArray.toString());
            }
        } catch (JSONException unused2) {
            loq.c(d, "ResponseAuthFirstInfo-parseResponseAuthInfoArray  JSONException");
        }
    }

    public void a(String str) {
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
                loq.c(d, "parseResponseAuthFirstInfo NumberFormatException");
            }
        }
        try {
            if (str == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            this.i = jSONObject.optInt("RespSN");
            this.b = jSONObject.optString("ReqName");
            this.h = jSONObject.optInt("ResultCode");
            this.e = jSONObject.optString("Payload");
            this.c = jSONObject.optString("AuthToken");
            this.g = jSONObject.optString("RSPServerAddress");
            this.f14793a = jSONObject.optString("MSISDN");
            this.j = jSONObject.optString("Sessionid");
            this.f = jSONObject.optInt("SMSAuthExpireTime");
            if (loq.b.booleanValue()) {
                loq.c(d, "Parse ResponseAuthFirstInfo result:" + jSONObject.toString());
            }
        } catch (JSONException unused2) {
            loq.c(d, "ResponseAuthFirstInfo-parseResponseAuthFirstInfo JSONException");
        }
    }
}
