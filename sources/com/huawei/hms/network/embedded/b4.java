package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.StringUtils;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes9.dex */
public class b4 extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    public cb f5170a;

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f5170a.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(StringUtils.getBytes(String.valueOf(i)));
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.f5170a.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5170a.close();
    }

    public b4(cb cbVar) {
        this.f5170a = cbVar;
    }
}
