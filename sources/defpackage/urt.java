package defpackage;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes10.dex */
public class urt extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private InputStream f17520a;
    private long c;
    private long e = 0;
    private byte[] b = new byte[1];

    public urt(InputStream inputStream, long j) {
        this.f17520a = inputStream;
        this.c = j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.b) == -1) {
            return -1;
        }
        return this.b[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.c;
        if (j != -1) {
            long j2 = this.e;
            if (j2 >= j) {
                return -1;
            }
            long j3 = j - j2;
            if (i2 > j3) {
                i2 = (int) j3;
            }
        }
        int read = this.f17520a.read(bArr, i, i2);
        if (read > 0) {
            this.e += read;
        }
        return read;
    }

    public int a(byte[] bArr) throws IOException {
        int read = this.f17520a.read(bArr);
        if (read == -1) {
            throw new IOException("Unexpected EOF reached when trying to read stream");
        }
        if (read == bArr.length || (read = c(bArr, read)) == bArr.length) {
            return read;
        }
        throw new IOException("Cannot read fully into byte buffer");
    }

    private int c(byte[] bArr, int i) throws IOException {
        int length = bArr.length - i;
        int i2 = 0;
        for (int i3 = 0; i < bArr.length && i2 != -1 && i3 < 15; i3++) {
            i2 += this.f17520a.read(bArr, i, length);
            if (i2 > 0) {
                i += i2;
                length -= i2;
            }
        }
        return i;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f17520a.close();
    }

    public long a() {
        return this.e;
    }
}
