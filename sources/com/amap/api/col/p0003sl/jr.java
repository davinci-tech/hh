package com.amap.api.col.p0003sl;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public final class jr implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f1227a = Charset.forName("US-ASCII");
    private final InputStream b;
    private final Charset c;
    private byte[] d;
    private int e;
    private int f;

    public jr(InputStream inputStream, Charset charset) {
        this(inputStream, charset, (byte) 0);
    }

    private jr(InputStream inputStream, Charset charset, byte b) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (!charset.equals(f1227a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.b = inputStream;
        this.c = charset;
        this.d = new byte[8192];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this.b) {
            if (this.d != null) {
                this.d = null;
                this.b.close();
            }
        }
    }

    public final String a() throws IOException {
        int i;
        byte[] bArr;
        int i2;
        synchronized (this.b) {
            if (this.d == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.e >= this.f) {
                b();
            }
            for (int i3 = this.e; i3 != this.f; i3++) {
                byte[] bArr2 = this.d;
                if (bArr2[i3] == 10) {
                    int i4 = this.e;
                    if (i3 != i4) {
                        i2 = i3 - 1;
                        if (bArr2[i2] == 13) {
                            String str = new String(bArr2, i4, i2 - i4, this.c.name());
                            this.e = i3 + 1;
                            return str;
                        }
                    }
                    i2 = i3;
                    String str2 = new String(bArr2, i4, i2 - i4, this.c.name());
                    this.e = i3 + 1;
                    return str2;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.f - this.e) + 80) { // from class: com.amap.api.col.3sl.jr.1
                @Override // java.io.ByteArrayOutputStream
                public final String toString() {
                    try {
                        return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, jr.this.c.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byte[] bArr3 = this.d;
                int i5 = this.e;
                byteArrayOutputStream.write(bArr3, i5, this.f - i5);
                this.f = -1;
                b();
                i = this.e;
                while (i != this.f) {
                    bArr = this.d;
                    if (bArr[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            int i6 = this.e;
            if (i != i6) {
                byteArrayOutputStream.write(bArr, i6, i - i6);
            }
            this.e = i + 1;
            return byteArrayOutputStream.toString();
        }
    }

    private void b() throws IOException {
        InputStream inputStream = this.b;
        byte[] bArr = this.d;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.e = 0;
        this.f = read;
    }
}
