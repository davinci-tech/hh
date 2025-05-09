package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class w0 {
    private static void d(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("publicKey");
            String optString2 = jSONObject.optString("publicKeyOM");
            String optString3 = jSONObject.optString("pubkey_version");
            String str3 = System.currentTimeMillis() + "";
            String optString4 = jSONObject.optString(HwExerciseConstants.JSON_NAME_TIME_INTERVAL);
            d.b(q0.i(), "Privacy_MY", "public_key_oper", AesGcmKS.encrypt("HiAnalytics_Sdk_Public_Sp_Key", optString));
            d.b(q0.i(), "Privacy_MY", "public_key_maint", AesGcmKS.encrypt("HiAnalytics_Sdk_Public_Sp_Key", optString2));
            d.b(q0.i(), "Privacy_MY", "public_key_time_interval", optString4);
            d.b(q0.i(), "Privacy_MY", "public_key_version", optString3);
            d.b(q0.i(), "Privacy_MY", "public_key_time_last", str3);
            q0.e(optString);
            q0.d(optString2);
            q0.g(optString3);
            q0.c(str3);
            q0.f(optString4);
        } catch (JSONException e) {
            v.e("GetPublicKey", "get pubKey parse json JSONException :" + e.getMessage());
        }
    }

    public static String c(String str, String str2) {
        String o;
        String c = q0.c();
        if (TextUtils.isEmpty(c)) {
            c = d.a(q0.i(), "Privacy_MY", "public_key_version", "");
            q0.g(c);
        }
        if (!"2.0".equals(c)) {
            a(str, str2);
            return null;
        }
        if ("maint".equals(str2)) {
            o = q0.n();
            if (TextUtils.isEmpty(o)) {
                o = AesGcmKS.decrypt("HiAnalytics_Sdk_Public_Sp_Key", d.a(q0.i(), "Privacy_MY", "public_key_maint", ""));
                q0.d(o);
            }
        } else {
            o = q0.o();
            if (TextUtils.isEmpty(o)) {
                o = AesGcmKS.decrypt("HiAnalytics_Sdk_Public_Sp_Key", d.a(q0.i(), "Privacy_MY", "public_key_oper", ""));
                q0.e(o);
            }
        }
        if (!TextUtils.isEmpty(o) && !a()) {
            return o;
        }
        a(str, str2);
        return null;
    }

    public static void b(String str, String str2) {
        n0 n0Var;
        String str3;
        String replace = "{url}/getPublicKey?keytype=4".replace("{url}", a1.f(str, str2));
        String f = q0.f();
        HashMap hashMap = new HashMap();
        hashMap.put("App-Id", f);
        try {
            n0Var = w.a(replace, new byte[0], hashMap);
        } catch (Exception e) {
            v.e("GetPublicKey", "get pubKey response Exception :" + e.getMessage());
            n0Var = null;
        }
        if (n0Var == null) {
            str3 = "get pubKey response is null";
        } else if (n0Var.b() == 200) {
            if (TextUtils.isEmpty(n0Var.a())) {
                return;
            }
            d(n0Var.a(), str2);
            return;
        } else {
            str3 = "get pubKey fail HttpCode :" + n0Var.b();
        }
        v.e("GetPublicKey", str3);
    }

    public static boolean a() {
        String a2 = q0.a();
        if (TextUtils.isEmpty(a2)) {
            a2 = d.a(q0.i(), "Privacy_MY", "public_key_time_interval", "");
            q0.f(a2);
        }
        String m = q0.m();
        if (TextUtils.isEmpty(m)) {
            m = d.a(q0.i(), "Privacy_MY", "public_key_time_last", "");
            q0.c(m);
        }
        if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(m)) {
            try {
                return System.currentTimeMillis() - Long.parseLong(m) > ((long) Integer.parseInt(a2));
            } catch (NumberFormatException e) {
                v.e("GetPublicKey", "checkCachePubKey NumberFormatException :" + e.getMessage());
            }
        }
        return true;
    }

    private static void a(String str, String str2) {
        b0.a().a(new j1(str, str2));
    }
}
