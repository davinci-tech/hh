package defpackage;

import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.exception.ZipException;

/* loaded from: classes10.dex */
public class urg implements Decrypter {
    private uri c = new uri();

    public urg(char[] cArr, long j, long j2, byte[] bArr, boolean z) throws ZipException {
        b(bArr, cArr, j2, j, z);
    }

    @Override // net.lingala.zip4j.crypto.Decrypter
    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (i < 0 || i2 < 0) {
            throw new ZipException("one of the input parameters were null in standard decrypt data");
        }
        for (int i3 = i; i3 < i + i2; i3++) {
            byte b = bArr[i3];
            byte b2 = (byte) (((b & 255) ^ this.c.b()) & 255);
            this.c.e(b2);
            bArr[i3] = b2;
        }
        return i2;
    }

    private void b(byte[] bArr, char[] cArr, long j, long j2, boolean z) throws ZipException {
        byte b;
        if (cArr == null || cArr.length <= 0) {
            throw new ZipException("Wrong password!", ZipException.Type.WRONG_PASSWORD);
        }
        this.c.c(cArr, z);
        int i = 0;
        byte b2 = bArr[0];
        while (i < 12) {
            i++;
            if (i == 12 && (b = (byte) (this.c.b() ^ b2)) != ((byte) (j2 >> 24)) && b != ((byte) (j >> 8))) {
                throw new ZipException("Wrong password!", ZipException.Type.WRONG_PASSWORD);
            }
            uri uriVar = this.c;
            uriVar.e((byte) (uriVar.b() ^ b2));
            if (i != 12) {
                b2 = bArr[i];
            }
        }
    }
}
