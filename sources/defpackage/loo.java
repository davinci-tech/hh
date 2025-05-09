package defpackage;

import android.text.TextUtils;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public class loo {

    /* renamed from: a, reason: collision with root package name */
    private static int f14813a = 4;
    private static int c = 7;
    private static final Charset d = Charset.forName("UTF-8");

    public static String b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return null;
        }
        return e(b(e(e(str, f14813a), str3), c), str2);
    }

    private static String e(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() < i) {
            return null;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            int i3 = i2 - i;
            if (i3 < 0) {
                charArray2[(charArray.length - i) + i2] = charArray[i2];
            } else {
                charArray2[i3] = charArray[i2];
            }
        }
        return new String(charArray2);
    }

    private static String b(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() < i) {
            return null;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            int i3 = i2 + i;
            if (i3 >= charArray.length) {
                charArray2[i3 - charArray.length] = charArray[i2];
            } else {
                charArray2[i3] = charArray[i2];
            }
        }
        return String.valueOf(charArray2);
    }

    private static String e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Charset charset = d;
        byte[] bytes = str2.getBytes(charset);
        byte[] bytes2 = str.getBytes(charset);
        int length = bytes2.length;
        for (int i = 0; i < length; i++) {
            for (byte b : bytes) {
                bytes2[i] = (byte) (b ^ bytes2[i]);
            }
        }
        return new String(bytes2, d);
    }
}
