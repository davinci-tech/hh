package defpackage;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class loa {
    private static final String d = "ResponseInfo";
    lnx c;

    /* renamed from: a, reason: collision with root package name */
    lny f14796a = null;
    lnz e = null;

    public lnx d() {
        return this.c;
    }

    public lny c() {
        return this.f14796a;
    }

    public lnz b() {
        return this.e;
    }

    private void c(String str, String str2) {
        if ("DevAuth".equals(str)) {
            lnx lnxVar = new lnx();
            this.c = lnxVar;
            lnxVar.a(str2);
            if (loq.b.booleanValue()) {
                loq.c(d, "Parse SingleInfo to jsonObj result:" + str2);
                return;
            }
            return;
        }
        if ("GetDevServInfo".equals(str)) {
            lny lnyVar = new lny();
            this.f14796a = lnyVar;
            lnyVar.c(str2);
            if (loq.b.booleanValue()) {
                loq.c(d, "Parse ResponseGetDevServInfo to jsonObj result:" + str2);
                return;
            }
            return;
        }
        if ("ServiceProvisionRequest".equals(str)) {
            lnz lnzVar = new lnz();
            this.e = lnzVar;
            lnzVar.b(str2);
            if (loq.b.booleanValue()) {
                loq.c(d, "Parse ResponseServiceProvisionInfo to jsonObj result:" + str2);
            }
        }
    }

    public void c(String str) {
        JSONArray jSONArray;
        if (lop.c(str)) {
            try {
                lnx lnxVar = new lnx();
                this.c = lnxVar;
                lnxVar.b(Integer.valueOf(str).intValue());
                return;
            } catch (NumberFormatException unused) {
                loq.c(d, "parseResponseInfo NumberFormatException");
            }
        }
        try {
            if (TextUtils.isEmpty(str)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(str);
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    c(jSONObject.optString("ReqName"), jSONObject.toString());
                }
            }
            if (loq.b.booleanValue()) {
                loq.c(d, "Parse ResponseAuthFirstInfo to jsonObj result:" + jSONArray.toString());
            }
        } catch (JSONException unused2) {
            loq.c(d, "ResponseInfo-ResponseAuthFirstInfo  JSONException");
        }
    }
}
