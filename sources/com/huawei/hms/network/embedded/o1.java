package com.huawei.hms.network.embedded;

import java.io.IOException;

/* loaded from: classes9.dex */
public interface o1 {
    void abort();

    void close() throws IOException;

    void write(byte[] bArr) throws IOException;
}
