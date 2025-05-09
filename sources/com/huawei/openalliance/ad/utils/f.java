package com.huawei.openalliance.ad.utils;

import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public abstract class f {
    private static boolean a() {
        return true;
    }

    private static String d(String str, byte[] bArr) {
        if (!TextUtils.isEmpty(str) && b(bArr) && a()) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                String a2 = a(str);
                String b = b(str);
                if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(b)) {
                    ho.b("Aes128", "ivParameter or encrypedWord is null");
                    return "";
                }
                cipher.init(2, secretKeySpec, c(an.a(a2)));
                return new String(cipher.doFinal(an.a(b)), "UTF-8");
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                ho.d("Aes128", "GCM decrypt data exception: " + e.getMessage());
            }
        }
        return "";
    }

    private static AlgorithmParameterSpec c(byte[] bArr) {
        return new GCMParameterSpec(128, bArr);
    }

    private static String c(String str, byte[] bArr) {
        byte[] a2;
        byte[] a3;
        if (TextUtils.isEmpty(str) || !b(bArr) || !a() || (a3 = a(str, bArr, (a2 = cp.a(12)))) == null || a3.length == 0) {
            return "";
        }
        return an.a(a2) + an.a(a3);
    }

    private static boolean b(byte[] bArr) {
        return bArr != null && bArr.length >= 16;
    }

    public static String b(String str, byte[] bArr) {
        if (cz.b(str) || str.length() < 32 || bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return a() ? d(str, bArr) : "";
        } catch (Exception e) {
            e = e;
            ho.c("Aes128", "fail to decrypt: " + e.getClass().getSimpleName());
            ho.a("Aes128", "input: %s", dl.a(an.a(bArr)));
            ho.a(3, e);
            return "";
        } catch (Throwable th) {
            e = th;
            ho.c("Aes128", "fail to decrypt: " + e.getClass().getSimpleName());
            ho.a("Aes128", "input: %s", dl.a(an.a(bArr)));
            ho.a(3, e);
            return "";
        }
    }

    private static String b(String str) {
        return (TextUtils.isEmpty(str) || str.length() < 24) ? "" : str.substring(24);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bg.a(bArr)) {
            return new byte[0];
        }
        if (b(bArr2) && a(bArr3) && a()) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(1, secretKeySpec, c(bArr3));
                return cipher.doFinal(bArr);
            } catch (GeneralSecurityException e) {
                ho.d("Aes128", "GCM encrypt data error" + e.getMessage());
            }
        } else {
            ho.b("Aes128", "gcm encrypt param is not right");
        }
        return new byte[0];
    }

    private static byte[] a(String str, byte[] bArr, byte[] bArr2) {
        if (!TextUtils.isEmpty(str) && b(bArr) && a(bArr2) && a()) {
            try {
                return a(str.getBytes("UTF-8"), bArr, bArr2);
            } catch (UnsupportedEncodingException e) {
                ho.d("Aes128", "GCM encrypt data error" + e.getMessage());
            }
        } else {
            ho.b("Aes128", "gcm encrypt param is not right");
        }
        return new byte[0];
    }

    private static boolean a(byte[] bArr) {
        return bArr != null && bArr.length >= 12;
    }

    public static String a(String str, byte[] bArr) {
        StringBuilder sb;
        if (cz.b(str) || bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return a() ? c(str, bArr) : "";
        } catch (Exception e) {
            e = e;
            sb = new StringBuilder("fail to cipher: ");
            sb.append(e.getClass().getSimpleName());
            ho.c("Aes128", sb.toString());
            ho.a(3, e);
            return "";
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("fail to cipher: ");
            sb.append(e.getClass().getSimpleName());
            ho.c("Aes128", sb.toString());
            ho.a(3, e);
            return "";
        }
    }

    private static String a(String str) {
        if (!TextUtils.isEmpty(str) && str.length() >= 24) {
            return str.substring(0, 24);
        }
        ho.b("Aes128", "IV is invalid.");
        return "";
    }
}
