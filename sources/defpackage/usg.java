package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.io.outputstream.CipherOutputStream;
import net.lingala.zip4j.model.ZipParameters;

/* loaded from: classes7.dex */
class usg extends CipherOutputStream<urm> {
    public usg(usf usfVar, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException {
        super(usfVar, zipParameters, cArr, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public urm initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException {
        urm urmVar = new urm(cArr, b(zipParameters), z);
        writeHeaders(urmVar.b());
        return urmVar;
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
        super.write(bArr, i, i2);
    }

    private long b(ZipParameters zipParameters) {
        if (zipParameters.u()) {
            return (utd.d(zipParameters.n()) & 65535) << 16;
        }
        return zipParameters.g();
    }
}
