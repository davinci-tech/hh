package defpackage;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class lb {

    /* renamed from: a, reason: collision with root package name */
    public static String f14738a = "DESede/CBC/PKCS5Padding";

    public static byte[] a(String str, byte[] bArr, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance(f14738a);
            cipher.init(1, secretKeySpec, new IvParameterSpec(kt.c(cipher, str2)));
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] c(String str, byte[] bArr, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance(f14738a);
            cipher.init(2, secretKeySpec, new IvParameterSpec(kt.c(cipher, str2)));
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String c(String str, String str2, String str3) {
        try {
            return new String(c(str, ku.d(str2), str3));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String e(String str, String str2, String str3) {
        try {
            return ku.c(a(str, str2.getBytes(), str3));
        } catch (Exception unused) {
            return null;
        }
    }
}
