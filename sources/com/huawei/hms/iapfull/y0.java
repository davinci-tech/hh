package com.huawei.hms.iapfull;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.secure.android.common.util.SafeBase64;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import org.eclipse.californium.elements.util.JceNames;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class y0 {
    public static void b(String str, String str2) {
        Log.i("iap_full", str + " " + str2);
    }

    public static PrivateKey b(String str) {
        String str2;
        try {
            try {
                return KeyFactory.getInstance(JceNames.RSA).generatePrivate(new PKCS8EncodedKeySpec(SafeBase64.decode(str, 0)));
            } catch (GeneralSecurityException unused) {
                str2 = "load Key Exception";
                a("EncryptUtil", str2);
                return null;
            }
        } catch (IllegalArgumentException unused2) {
            str2 = "base64 decode IllegalArgumentException";
        }
    }

    public static void a(String str, String str2) {
        Log.e("iap_full", str + " " + str2);
    }

    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "getCountryCode reservedInfor is null";
        } else {
            try {
                return new JSONObject(new JSONObject(str).optString("accountInfo")).optString("countryCode");
            } catch (JSONException unused) {
                str2 = "getCountryCode JSONException";
            }
        }
        a("AccountInfoUtils", str2);
        return null;
    }
}
