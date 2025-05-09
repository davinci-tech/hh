package com.huawei.hms.network.embedded;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* loaded from: classes9.dex */
public class b implements Closeable {
    public static final byte f = 13;
    public static final byte g = 10;

    /* renamed from: a, reason: collision with root package name */
    public final InputStream f5165a;
    public final Charset b;
    public byte[] c;
    public int d;
    public int e;

    public String t() throws IOException {
        int i;
        byte[] bArr;
        int i2;
        synchronized (this.f5165a) {
            if (this.c == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.d >= this.e) {
                u();
            }
            for (int i3 = this.d; i3 != this.e; i3++) {
                byte[] bArr2 = this.c;
                if (bArr2[i3] == 10) {
                    int i4 = this.d;
                    if (i3 != i4) {
                        i2 = i3 - 1;
                        if (bArr2[i2] == 13) {
                            String str = new String(bArr2, i4, i2 - i4, this.b.name());
                            this.d = i3 + 1;
                            return str;
                        }
                    }
                    i2 = i3;
                    String str2 = new String(bArr2, i4, i2 - i4, this.b.name());
                    this.d = i3 + 1;
                    return str2;
                }
            }
            a aVar = new a((this.e - this.d) + 80);
            loop1: while (true) {
                byte[] bArr3 = this.c;
                int i5 = this.d;
                aVar.write(bArr3, i5, this.e - i5);
                this.e = -1;
                u();
                i = this.d;
                while (i != this.e) {
                    bArr = this.c;
                    if (bArr[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            int i6 = this.d;
            if (i != i6) {
                aVar.write(bArr, i6, i - i6);
            }
            this.d = i + 1;
            return aVar.toString();
        }
    }

    public boolean s() {
        return this.e == -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.f5165a) {
            if (this.c != null) {
                this.c = null;
                this.f5165a.close();
            }
        }
    }

    private void u() throws IOException {
        InputStream inputStream = this.f5165a;
        byte[] bArr = this.c;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.d = 0;
        this.e = read;
    }

    public class a extends ByteArrayOutputStream {
        /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
        
            if (((java.io.ByteArrayOutputStream) r5).buf[r0] == 13) goto L13;
         */
        @Override // java.io.ByteArrayOutputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String toString() {
            /*
                r5 = this;
                int r0 = r5.count
                if (r0 <= 0) goto Lf
                byte[] r1 = r5.buf
                int r0 = r0 + (-1)
                r1 = r1[r0]
                r2 = 13
                if (r1 != r2) goto Lf
                goto L11
            Lf:
                int r0 = r5.count
            L11:
                java.lang.String r1 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L24
                byte[] r2 = r5.buf     // Catch: java.io.UnsupportedEncodingException -> L24
                com.huawei.hms.network.embedded.b r3 = com.huawei.hms.network.embedded.b.this     // Catch: java.io.UnsupportedEncodingException -> L24
                java.nio.charset.Charset r3 = com.huawei.hms.network.embedded.b.a(r3)     // Catch: java.io.UnsupportedEncodingException -> L24
                java.lang.String r3 = r3.name()     // Catch: java.io.UnsupportedEncodingException -> L24
                r4 = 0
                r1.<init>(r2, r4, r0, r3)     // Catch: java.io.UnsupportedEncodingException -> L24
                return r1
            L24:
                r0 = move-exception
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.b.a.toString():java.lang.String");
        }

        public a(int i) {
            super(i);
        }
    }

    public b(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public b(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(c.f5195a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f5165a = inputStream;
        this.b = charset;
        this.c = new byte[i];
    }
}
