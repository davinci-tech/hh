package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.io.outputstream.CipherOutputStream;
import net.lingala.zip4j.model.ZipParameters;

/* loaded from: classes7.dex */
class usb extends CipherOutputStream<uqz> {
    private int b;
    private byte[] c;

    public usb(usf usfVar, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException {
        super(usfVar, zipParameters, cArr, z);
        this.c = new byte[16];
        this.b = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public uqz initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException {
        uqz uqzVar = new uqz(cArr, zipParameters.e(), z);
        d(uqzVar);
        return uqzVar;
    }

    private void d(uqz uqzVar) throws IOException {
        writeHeaders(uqzVar.e());
        writeHeaders(uqzVar.b());
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = this.b;
        int i5 = 16 - i4;
        if (i2 >= i5) {
            System.arraycopy(bArr, i, this.c, i4, i5);
            byte[] bArr2 = this.c;
            super.write(bArr2, 0, bArr2.length);
            int i6 = 16 - this.b;
            int i7 = i2 - i6;
            this.b = 0;
            if (i7 != 0 && (i3 = i7 % 16) != 0) {
                System.arraycopy(bArr, (i7 + i6) - i3, this.c, 0, i3);
                this.b = i3;
                i7 -= i3;
            }
            super.write(bArr, i6, i7);
            return;
        }
        System.arraycopy(bArr, i, this.c, i4, i2);
        this.b += i2;
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    public void closeEntry() throws IOException {
        int i = this.b;
        if (i != 0) {
            super.write(this.c, 0, i);
            this.b = 0;
        }
        writeHeaders(getEncrypter().a());
        super.closeEntry();
    }
}
