package defpackage;

import android.content.Context;
import android.text.TextUtils;
import defpackage.kl;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class lt {
    public static jq b() {
        try {
            try {
                return jy.c("NP", System.currentTimeMillis(), new jw(lw.c().e()), (short) kl.c.d(lw.c().d()), new kc());
            } catch (Exception unused) {
                return null;
            }
        } catch (Exception unused2) {
            return jy.a();
        }
    }

    public static HashMap<String, String> b(lv lvVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            jq b = b();
            JSONObject jSONObject = new JSONObject();
            Context a2 = lvVar != null ? lvVar.a() : null;
            if (a2 == null) {
                a2 = lw.c().d().getApplicationContext();
            }
            String a3 = md.a(lvVar, a2);
            String a4 = me.a(lvVar, a2);
            jSONObject.put("ap_q", b != null ? b.d() : "");
            jSONObject.put("ap_link_token", lvVar != null ? lvVar.b : "");
            jSONObject.put("u_pd", String.valueOf(md.f()));
            jSONObject.put("u_lk", String.valueOf(md.a(md.a())));
            jSONObject.put("u_pi", String.valueOf(lvVar != null ? lvVar.f : "_"));
            jSONObject.put("u_fu", a3);
            jSONObject.put("u_oi", a4);
            hashMap.put("ap_req", jSONObject.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(b != null ? b.d() : "");
            sb.append("|");
            sb.append(a3);
            kl.a(lvVar, "biz", "ap_q", sb.toString());
        } catch (Exception e) {
            kl.e(lvVar, "biz", "APMEx1", e);
        }
        return hashMap;
    }

    public static JSONObject e(lv lvVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("ap_resp");
        try {
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            return new JSONObject(optString);
        } catch (JSONException e) {
            kl.e(lvVar, "biz", "APMEx2", e);
            return null;
        }
    }

    public static void b(lv lvVar, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        try {
            jSONObject.putOpt("ap_args", jSONObject2);
        } catch (JSONException e) {
            kl.e(lvVar, "biz", "APMEx2", e);
        }
    }

    public static void c(lv lvVar, HashMap<String, String> hashMap) {
        JSONObject e = kr.a().e();
        if (hashMap == null || e == null) {
            return;
        }
        kl.a(lvVar, "biz", "ap_r", e.optString("ap_r"));
        hashMap.putAll(md.e(e));
    }
}
