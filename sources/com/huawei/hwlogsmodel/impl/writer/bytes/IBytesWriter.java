package com.huawei.hwlogsmodel.impl.writer.bytes;

import java.io.File;

/* loaded from: classes5.dex */
public interface IBytesWriter {
    void clearLogCache();

    void flush(File file, boolean z);

    boolean write(File file, byte[] bArr, boolean z);
}
