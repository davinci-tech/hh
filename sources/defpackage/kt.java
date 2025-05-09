package defpackage;

import android.text.TextUtils;
import java.security.SecureRandom;
import javax.crypto.Cipher;

/* loaded from: classes7.dex */
public class kt {
    public static byte[] c(Cipher cipher, String str) {
        SecureRandom secureRandom = new SecureRandom();
        int blockSize = cipher.getBlockSize();
        if (TextUtils.isEmpty(str)) {
            str = String.valueOf(secureRandom.nextDouble());
        }
        int i = blockSize * 2;
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[blockSize];
        secureRandom.nextBytes(bArr2);
        for (int i2 = 1; i2 < i; i2++) {
            byte codePointAt = (byte) (str.codePointAt(i2 % str.length()) & 127);
            bArr[i2] = codePointAt;
            if (i2 >= blockSize) {
                bArr[i2] = (byte) (bArr[0] & codePointAt);
            }
        }
        System.arraycopy(bArr, blockSize, bArr2, 0, blockSize);
        return bArr2;
    }
}
