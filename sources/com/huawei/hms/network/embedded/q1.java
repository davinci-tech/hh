package com.huawei.hms.network.embedded;

import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class q1 extends InputStream {
    public static final String h = "CacheInputStream";

    /* renamed from: a, reason: collision with root package name */
    public final InputStream f5427a;
    public final o1 b;
    public int f;
    public byte[] c = new byte[8192];
    public int d = 0;
    public int e = 0;
    public int g = 0;

    @Override // java.io.InputStream
    public int read() throws IOException {
        return 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        if (this.b == null) {
            return this.f5427a.read(bArr, 0, bArr.length);
        }
        int read = this.f5427a.read(bArr, 0, bArr.length);
        this.f = read;
        if (read == -1) {
            int i = this.d;
            if (i > 0) {
                write(y1.encryptBody(Arrays.copyOfRange(this.c, 0, i)));
                this.d = 0;
            }
            return this.f;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, read);
        int i2 = this.g + this.f;
        this.g = i2;
        if (i2 > 16777216) {
            this.b.abort();
            this.g = 0;
        }
        int i3 = this.d;
        int i4 = 8192 - i3;
        this.e = i4;
        int i5 = this.f;
        if (i5 < i4) {
            System.arraycopy(copyOfRange, 0, this.c, i3, i5);
            this.d += this.f;
        } else {
            System.arraycopy(copyOfRange, 0, this.c, i3, i4);
            write(y1.encryptBody(this.c));
            int i6 = this.f;
            int i7 = this.e;
            int i8 = i6 - i7;
            System.arraycopy(copyOfRange, i7, this.c, 0, i8);
            this.d = i8;
        }
        return this.f;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5427a.close();
        o1 o1Var = this.b;
        if (o1Var != null) {
            o1Var.close();
        }
    }

    private void write(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        try {
            this.b.write(bArr);
        } catch (IOException e) {
            this.b.abort();
            throw e;
        }
    }

    public q1(ResponseBody responseBody, o1 o1Var) {
        if (responseBody == null || responseBody.getInputStream() == null) {
            throw new IllegalArgumentException("ResponseBody delegrate == null");
        }
        this.f5427a = responseBody.getInputStream();
        this.b = o1Var;
    }
}
