package defpackage;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public class lnh {
    private static final String b = "MultiSimUtils";

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(String.format("%02X ", Integer.valueOf(b2 & 255)));
        }
        return sb.toString();
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(bArr2, "HmacSHA1"));
            byte[] doFinal = mac.doFinal(bArr);
            loq.c(b, "byteHMAC.length = " + doFinal.length);
            return doFinal;
        } catch (InvalidKeyException unused) {
            loq.c(b, "InvalidKeyException");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            loq.c(b, "NoSuchAlgorithmException");
            return null;
        }
    }
}
