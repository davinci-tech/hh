package com.huawei.hms.framework.network.restclient.hwhttp.trans;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class CounterOutputStream extends OutputStream {
    private final com.huawei.hms.network.base.common.trans.CounterOutputStream proxyStream = new com.huawei.hms.network.base.common.trans.CounterOutputStream();

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.proxyStream.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.proxyStream.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.proxyStream.write(bArr, i, i2);
    }

    public long getLenth() {
        return this.proxyStream.getLenth();
    }
}
