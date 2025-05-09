package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/* loaded from: classes5.dex */
public class kar {
    private static final BitSet e = new BitSet(256);

    static {
        for (int i = 33; i <= 60; i++) {
            e.set(i);
        }
        for (int i2 = 62; i2 <= 126; i2++) {
            e.set(i2);
        }
        BitSet bitSet = e;
        bitSet.set(9);
        bitSet.set(32);
    }

    public static String d(String str) {
        if (str == null) {
            LogUtil.a("ContactSync", 1, "QuotedPrintableCodec", "the parameter 'enCodeStr' is null in encode(String,String)");
            return "";
        }
        try {
            return e(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            LogUtil.h("ContactSync", 1, "QuotedPrintableCodec", e2.getMessage());
            return "";
        }
    }

    public static String e(String str, String str2) throws UnsupportedEncodingException {
        if (str == null) {
            LogUtil.h("ContactSync", 1, "QuotedPrintableCodec", "the parameter 'encodeString' is null in encode(String,String)");
            return "";
        }
        return new String(b(str.getBytes(str2)), StandardCharsets.US_ASCII);
    }

    public static byte[] b(byte[] bArr) {
        return a(e, bArr);
    }

    public static byte[] a(BitSet bitSet, byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("ContactSync", 1, "QuotedPrintableCodec", "encodeQuotedPrintable: bytes is null.");
            return new byte[0];
        }
        if (bitSet == null) {
            bitSet = e;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = b < 0 ? b + 256 : b;
            if (bitSet.get(i2)) {
                byteArrayOutputStream.write(i2);
            } else {
                byteArrayOutputStream.write(61);
                char upperCase = Character.toUpperCase(Character.forDigit((b >> 4) & 15, 16));
                char upperCase2 = Character.toUpperCase(Character.forDigit(b & BaseType.Obj, 16));
                byteArrayOutputStream.write(upperCase);
                byteArrayOutputStream.write(upperCase2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
