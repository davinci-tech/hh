package defpackage;

import com.huawei.hwidauth.b.c;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public class kqt {
    private static String e() {
        EncryptUtil.setBouncycastleFlag(true);
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(8);
        return c.a(generateSecureRandomStr.getBytes(Charset.defaultCharset()), generateSecureRandomStr.length());
    }

    private static SecretKeySpec c(byte[] bArr, int i) {
        if (i <= 0 || i > bArr.length) {
            i = bArr.length;
        }
        if (i > 16) {
            i = 16;
        }
        byte[] bArr2 = new byte[16];
        for (int i2 = 0; i2 < 16; i2++) {
            bArr2[i2] = 0;
        }
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = bArr[i3];
        }
        return new SecretKeySpec(bArr2, 0, 16, "AES/CBC/PKCS5Padding");
    }

    public static String b(byte[] bArr, byte[] bArr2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        String e = e();
        return e + ":" + c.a(kqv.d(bArr, c.a(e), c(bArr2, 0)));
    }
}
