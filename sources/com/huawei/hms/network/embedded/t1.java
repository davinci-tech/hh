package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.y1;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class t1 extends InputStream {
    public static final String h = "DecryptInputStream";
    public static final int i = 28;
    public static final int j = 8220;

    /* renamed from: a, reason: collision with root package name */
    public InputStream f5493a;
    public a b;
    public String c;
    public int d;
    public int e;
    public byte[] f = new byte[8192];
    public byte[] g = new byte[j];

    @Override // java.io.InputStream
    public int read() throws IOException {
        return 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int i2 = this.d - this.e;
        if (i2 <= 0) {
            s();
            if (bArr.length > 8192) {
                byte[] bArr2 = this.f;
                System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
                return this.f.length;
            }
            i2 = this.d - this.e;
            if (i2 <= 0) {
                return -1;
            }
        }
        if (i2 >= bArr.length) {
            i2 = bArr.length;
        }
        System.arraycopy(this.f, this.e, bArr, 0, i2);
        this.e += i2;
        return i2;
    }

    public boolean isDecryptable() {
        try {
            s();
            return true;
        } catch (IOException unused) {
            Logger.w(h, "Decrypt failed");
            return false;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5493a.close();
    }

    private void s() throws IOException {
        int read = this.f5493a.read(this.g);
        if (read == -1) {
            this.d = 0;
        } else {
            a(read, this.g);
            this.d = this.f.length;
        }
        this.e = 0;
    }

    private void a(int i2, byte[] bArr) throws IOException {
        if (i2 != 8220) {
            bArr = Arrays.copyOfRange(bArr, 0, i2);
        }
        try {
            this.f = y1.decryptBody(bArr);
        } catch (y1.a e) {
            Logger.w(h, "Decrypt body failed, the requested cache files are deleted");
            this.b.d(this.c);
            close();
            throw a(e);
        } catch (y1.b e2) {
            Logger.w(h, "WorkKey is invalid, all requested cache files are deleted");
            this.b.s();
            close();
            throw a(e2);
        }
    }

    private IOException a(IOException iOException) {
        if (iOException == null) {
            return new IOException("DecryptInputStream Exception");
        }
        IOException iOException2 = new IOException(iOException.getMessage());
        iOException2.initCause(iOException);
        return iOException2;
    }

    public t1(a aVar, String str, InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("delegrate == null");
        }
        this.f5493a = inputStream;
        this.b = aVar;
        this.c = str;
    }
}
