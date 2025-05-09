package com.huawei.hms.network.embedded;

import com.huawei.operation.utils.Constants;
import java.io.IOException;

/* loaded from: classes9.dex */
public abstract class gb implements yb {

    /* renamed from: a, reason: collision with root package name */
    public final yb f5269a;

    @Override // com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) throws IOException {
        this.f5269a.write(bbVar, j);
    }

    public String toString() {
        return getClass().getSimpleName() + Constants.LEFT_BRACKET_ONLY + this.f5269a.toString() + Constants.RIGHT_BRACKET_ONLY;
    }

    @Override // com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.f5269a.timeout();
    }

    @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
    public void flush() throws IOException {
        this.f5269a.flush();
    }

    public final yb e() {
        return this.f5269a;
    }

    @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        this.f5269a.close();
    }

    public gb(yb ybVar) {
        if (ybVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5269a = ybVar;
    }
}
