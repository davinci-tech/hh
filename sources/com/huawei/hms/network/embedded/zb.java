package com.huawei.hms.network.embedded;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes9.dex */
public interface zb extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    void close() throws IOException;

    long read(bb bbVar, long j) throws IOException;

    ac timeout();
}
