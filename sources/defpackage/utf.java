package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import net.lingala.zip4j.exception.ZipException;

/* loaded from: classes7.dex */
public class utf {
    private final byte[] d = new byte[2];
    private final byte[] e = new byte[4];
    private final byte[] b = new byte[8];

    public long c(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.b);
        return d(this.b, 0);
    }

    public long b(RandomAccessFile randomAccessFile, int i) throws IOException {
        c(this.b);
        randomAccessFile.readFully(this.b, 0, i);
        return d(this.b, 0);
    }

    public long d(InputStream inputStream) throws IOException {
        byte[] bArr = this.b;
        d(inputStream, bArr, bArr.length);
        return d(this.b, 0);
    }

    public long b(InputStream inputStream, int i) throws IOException {
        c(this.b);
        d(inputStream, this.b, i);
        return d(this.b, 0);
    }

    public long d(byte[] bArr, int i) {
        if (bArr.length - i < 8) {
            c(this.b);
        }
        System.arraycopy(bArr, i, this.b, 0, Math.min(bArr.length - i, 8));
        byte[] bArr2 = this.b;
        long j = bArr2[7] & 255;
        long j2 = bArr2[6] & 255;
        long j3 = bArr2[5] & 255;
        long j4 = bArr2[4] & 255;
        long j5 = bArr2[3] & 255;
        return (bArr2[0] & 255) | (((((((((((((j << 8) | j2) << 8) | j3) << 8) | j4) << 8) | j5) << 8) | (bArr2[2] & 255)) << 8) | (bArr2[1] & 255)) << 8);
    }

    public int d(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.e);
        return a(this.e);
    }

    public int b(InputStream inputStream) throws IOException {
        d(inputStream, this.e, 4);
        return a(this.e);
    }

    public int a(byte[] bArr) {
        return e(bArr, 0);
    }

    public int e(byte[] bArr, int i) {
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        return ((((bArr[i + 3] & 255) << 8) | (bArr[i + 2] & 255)) << 16) | (b & 255) | ((b2 & 255) << 8);
    }

    public int e(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.d);
        return c(this.d, 0);
    }

    public int a(InputStream inputStream) throws IOException {
        byte[] bArr = this.d;
        d(inputStream, bArr, bArr.length);
        return c(this.d, 0);
    }

    public int c(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public void c(OutputStream outputStream, int i) throws IOException {
        d(this.d, 0, i);
        outputStream.write(this.d);
    }

    public void d(byte[] bArr, int i, int i2) {
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i] = (byte) (i2 & 255);
    }

    public void d(OutputStream outputStream, int i) throws IOException {
        a(this.e, 0, i);
        outputStream.write(this.e);
    }

    public void a(byte[] bArr, int i, int i2) {
        bArr[i + 3] = (byte) (i2 >>> 24);
        bArr[i + 2] = (byte) (i2 >>> 16);
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i] = (byte) (i2 & 255);
    }

    public void d(OutputStream outputStream, long j) throws IOException {
        e(this.b, 0, j);
        outputStream.write(this.b);
    }

    public void e(byte[] bArr, int i, long j) {
        bArr[i + 7] = (byte) (j >>> 56);
        bArr[i + 6] = (byte) (j >>> 48);
        bArr[i + 5] = (byte) (j >>> 40);
        bArr[i + 4] = (byte) (j >>> 32);
        bArr[i + 3] = (byte) (j >>> 24);
        bArr[i + 2] = (byte) (j >>> 16);
        bArr[i + 1] = (byte) (j >>> 8);
        bArr[i] = (byte) (j & 255);
    }

    private void d(InputStream inputStream, byte[] bArr, int i) throws IOException {
        if (utd.d(inputStream, bArr, 0, i) != i) {
            throw new ZipException("Could not fill buffer");
        }
    }

    private void c(byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
    }
}
