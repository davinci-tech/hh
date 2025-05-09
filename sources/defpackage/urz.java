package defpackage;

import java.io.IOException;
import java.util.zip.Deflater;
import net.lingala.zip4j.io.outputstream.CipherOutputStream;
import net.lingala.zip4j.io.outputstream.CompressedOutputStream;
import net.lingala.zip4j.model.enums.CompressionLevel;

/* loaded from: classes7.dex */
class urz extends CompressedOutputStream {
    private byte[] c;
    protected Deflater d;

    public urz(CipherOutputStream<?> cipherOutputStream, CompressionLevel compressionLevel, int i) {
        super(cipherOutputStream);
        this.d = new Deflater(compressionLevel.getLevel(), true);
        this.c = new byte[i];
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.d.setInput(bArr, i, i2);
        while (!this.d.needsInput()) {
            c();
        }
    }

    private void c() throws IOException {
        Deflater deflater = this.d;
        byte[] bArr = this.c;
        int deflate = deflater.deflate(bArr, 0, bArr.length);
        if (deflate > 0) {
            super.write(this.c, 0, deflate);
        }
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream
    public void closeEntry() throws IOException {
        if (!this.d.finished()) {
            this.d.finish();
            while (!this.d.finished()) {
                c();
            }
        }
        this.d.end();
        super.closeEntry();
    }
}
