package com.huawei.agconnect.config.impl;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.agconnect.config.ConfigReader;
import com.huawei.agconnect.config.IDecrypt;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public class e implements IDecrypt {

    /* renamed from: a, reason: collision with root package name */
    private final ConfigReader f1707a;
    private SecretKey b;
    private boolean c = false;

    @Override // com.huawei.agconnect.config.IDecrypt
    public String decrypt(String str, String str2) {
        String str3;
        if (!this.c) {
            a();
        }
        if (this.b != null) {
            if (a(str)) {
                try {
                    return new String(j.a(this.b, Hex.decodeHexString(b(str))), "UTF-8");
                } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException unused) {
                    str3 = "UnsupportedEncodingException||GeneralSecurityException||IllegalArgumentException";
                }
            }
            return str2;
        }
        str3 = "mKey is null, return default value";
        Log.e("AGC_Mark", str3);
        return str2;
    }

    private String b(String str) {
        try {
            Matcher matcher = Pattern.compile("^\\[!([A-Fa-f0-9]*)]").matcher(str);
            return matcher.find() ? matcher.group(1) : "";
        } catch (IllegalStateException | IndexOutOfBoundsException unused) {
            Log.e("AGC_Mark", "getRawString exception");
            return "";
        }
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^\\[!([A-Fa-f0-9]*)]", str);
    }

    private void a() {
        try {
            this.b = j.a(new d(this.f1707a.getString("/code/code1", null), this.f1707a.getString("/code/code2", null), this.f1707a.getString("/code/code3", null), this.f1707a.getString("/code/code4", null), "PBKDF2WithHmacSHA1", 10000));
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException unused) {
            Log.e("AGC_Mark", "Exception when reading the 'K&I' for 'Config'.");
            this.b = null;
        }
        this.c = true;
    }

    public e(ConfigReader configReader) {
        this.f1707a = configReader;
    }
}
