package com.huawei.hms.network.embedded;

import com.huawei.operation.utils.Constants;
import java.io.IOException;

/* loaded from: classes9.dex */
public abstract class hb implements zb {

    /* renamed from: a, reason: collision with root package name */
    public final zb f5297a;

    public String toString() {
        return getClass().getSimpleName() + Constants.LEFT_BRACKET_ONLY + this.f5297a.toString() + Constants.RIGHT_BRACKET_ONLY;
    }

    @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.f5297a.timeout();
    }

    @Override // com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j) throws IOException {
        return this.f5297a.read(bbVar, j);
    }

    @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    public void close() throws IOException {
        this.f5297a.close();
    }

    public final zb b() {
        return this.f5297a;
    }

    public hb(zb zbVar) {
        if (zbVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5297a = zbVar;
    }
}
