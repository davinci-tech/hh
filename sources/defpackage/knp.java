package defpackage;

import java.util.Locale;

/* loaded from: classes9.dex */
public final class knp {
    public static byte[] e(String str) {
        if (str == null) {
            return new byte[0];
        }
        int length = str.length();
        if (length % 2 != 0) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        for (int i = 0; i < length; i++) {
            char charAt = upperCase.charAt(i);
            if (('0' > charAt || charAt > '9') && ('A' > charAt || charAt > 'F')) {
                return new byte[0];
            }
        }
        int i2 = length / 2;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + 1;
            bArr2[0] = (byte) upperCase.charAt(i3);
            i3 += 2;
            bArr2[1] = (byte) upperCase.charAt(i5);
            for (int i6 = 0; i6 < 2; i6++) {
                byte b = bArr2[i6];
                if (65 <= b && b <= 70) {
                    bArr2[i6] = (byte) (b - 55);
                } else {
                    bArr2[i6] = (byte) (b - 48);
                }
            }
            bArr[i4] = (byte) ((bArr2[0] << 4) | bArr2[1]);
        }
        return bArr;
    }
}
