package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class f2 extends InputStream {
    public static final String e = "CronetInputStream";
    public static final int f = 32768;
    public static final int g = 1;
    public static final int h = 2;

    /* renamed from: a, reason: collision with root package name */
    public final j2 f5240a;
    public boolean b;
    public ByteBuffer c;
    public IOException d;

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.f5240a.setReadStatus(1);
            if (i2 < 0 || i < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return 0;
            }
            s();
            if (!t()) {
                return -1;
            }
            int min = Math.min(this.c.limit() - this.c.position(), i2);
            this.c.get(bArr, i, min);
            return min;
        } catch (Throwable th) {
            Logger.e(e, "cronet stream read error:", th);
            this.f5240a.setReadStatus(2);
            throw th;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        try {
            this.f5240a.setReadStatus(1);
            s();
            if (t()) {
                return this.c.get() & 255;
            }
            return -1;
        } catch (Throwable th) {
            Logger.e(e, "cronet stream read error:", th);
            this.f5240a.setReadStatus(2);
            throw th;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5240a.disconnect();
    }

    public void a(IOException iOException) {
        this.d = iOException;
        this.b = true;
        this.c = null;
    }

    private boolean t() {
        ByteBuffer byteBuffer = this.c;
        return byteBuffer != null && byteBuffer.hasRemaining();
    }

    private void s() throws IOException {
        if (this.b) {
            IOException iOException = this.d;
            if (iOException != null) {
                throw iOException;
            }
        } else {
            if (t()) {
                return;
            }
            if (this.c == null) {
                this.c = ByteBuffer.allocateDirect(32768);
            }
            this.c.clear();
            this.f5240a.a(this.c);
            IOException iOException2 = this.d;
            if (iOException2 != null) {
                throw iOException2;
            }
            ByteBuffer byteBuffer = this.c;
            if (byteBuffer != null) {
                byteBuffer.flip();
            }
        }
    }

    public f2(j2 j2Var) {
        this.f5240a = j2Var;
    }
}
