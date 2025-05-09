package defpackage;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes7.dex */
public class usf extends OutputStream {
    private OutputStream e;
    private long d = 0;
    private boolean b = false;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public usf(OutputStream outputStream) {
        this.e = outputStream;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.b) {
            throw new IllegalStateException("ZipEntryOutputStream is closed");
        }
        this.e.write(bArr, i, i2);
        this.d += i2;
    }

    public void c() throws IOException {
        this.b = true;
    }

    public long b() {
        return this.d;
    }
}
