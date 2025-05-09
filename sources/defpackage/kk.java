package defpackage;

import com.alipay.sdk.m.l0.b;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okio.Utf8;

/* loaded from: classes7.dex */
public class kk {
    public static String b(String str) {
        try {
            return new String(e(e(), d(str)));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String e(String str) {
        byte[] bArr;
        try {
            bArr = d(e(), str.getBytes());
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr != null) {
            return c(bArr);
        }
        return null;
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(c()));
        return cipher.doFinal(bArr2);
    }

    public static byte[] c() {
        try {
            byte[] b = b.b("IUQSvE6r1TfFPdPEjfklLw==".getBytes("UTF-8"), 2);
            if (b != null) {
                return ko.d(b);
            }
        } catch (Exception unused) {
        }
        return new byte[16];
    }

    public static byte[] e() throws Exception {
        return ko.d(new byte[]{PublicSuffixDatabase.i, 83, -50, -89, -84, -114, 80, 99, 10, Utf8.REPLACEMENT_BYTE, 22, -65, -11, 30, 101, -118});
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(c()));
        return cipher.doFinal(bArr2);
    }

    public static byte[] d(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            a(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    public static void a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & BaseType.Obj));
    }
}
