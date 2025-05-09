package defpackage;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* loaded from: classes7.dex */
public class urd {
    public static byte[] a(byte[] bArr, char[] cArr, AesKeyStrength aesKeyStrength, boolean z) throws ZipException {
        ure ureVar = new ure(new urh("HmacSHA1", "ISO-8859-1", bArr, 1000));
        int keyLength = aesKeyStrength.getKeyLength();
        int macLength = aesKeyStrength.getMacLength();
        int i = keyLength + macLength + 2;
        byte[] c = ureVar.c(cArr, i, z);
        if (c == null || c.length != i) {
            throw new ZipException(String.format("Derived Key invalid for Key Length [%d] MAC Length [%d]", Integer.valueOf(keyLength), Integer.valueOf(macLength)));
        }
        return c;
    }

    public static byte[] b(byte[] bArr, AesKeyStrength aesKeyStrength) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, aesKeyStrength.getKeyLength() + aesKeyStrength.getMacLength(), bArr2, 0, 2);
        return bArr2;
    }

    public static urf e(byte[] bArr, AesKeyStrength aesKeyStrength) {
        int macLength = aesKeyStrength.getMacLength();
        byte[] bArr2 = new byte[macLength];
        System.arraycopy(bArr, aesKeyStrength.getKeyLength(), bArr2, 0, macLength);
        urf urfVar = new urf("HmacSHA1");
        urfVar.init(bArr2);
        return urfVar;
    }

    public static urj a(byte[] bArr, AesKeyStrength aesKeyStrength) throws ZipException {
        int keyLength = aesKeyStrength.getKeyLength();
        byte[] bArr2 = new byte[keyLength];
        System.arraycopy(bArr, 0, bArr2, 0, keyLength);
        return new urj(bArr2);
    }

    public static void e(byte[] bArr, int i) {
        bArr[0] = (byte) i;
        bArr[1] = (byte) (i >> 8);
        bArr[2] = (byte) (i >> 16);
        bArr[3] = (byte) (i >> 24);
        for (int i2 = 4; i2 <= 15; i2++) {
            bArr[i2] = 0;
        }
    }
}
