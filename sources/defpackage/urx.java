package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport;

/* loaded from: classes7.dex */
public class urx extends OutputStream implements OutputStreamWithSplitZipSupport {

    /* renamed from: a, reason: collision with root package name */
    private long f17523a = 0;
    private OutputStream d;

    public urx(OutputStream outputStream) {
        this.d = outputStream;
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
        this.d.write(bArr, i, i2);
        this.f17523a += i2;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public int getCurrentSplitFileCounter() {
        if (e()) {
            return ((usa) this.d).getCurrentSplitFileCounter();
        }
        return 0;
    }

    public long a() throws IOException {
        OutputStream outputStream = this.d;
        if (outputStream instanceof usa) {
            return ((usa) outputStream).getFilePointer();
        }
        return this.f17523a;
    }

    public long d() {
        if (e()) {
            return ((usa) this.d).d();
        }
        return 0L;
    }

    public boolean e() {
        OutputStream outputStream = this.d;
        return (outputStream instanceof usa) && ((usa) outputStream).b();
    }

    public long c() throws IOException {
        OutputStream outputStream = this.d;
        if (outputStream instanceof usa) {
            return ((usa) outputStream).getFilePointer();
        }
        return this.f17523a;
    }

    public boolean d(int i) throws ZipException {
        if (e()) {
            return ((usa) this.d).e(i);
        }
        return false;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public long getFilePointer() throws IOException {
        OutputStream outputStream = this.d;
        if (outputStream instanceof usa) {
            return ((usa) outputStream).getFilePointer();
        }
        return this.f17523a;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.d.close();
    }
}
