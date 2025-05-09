package com.huawei.hwlogsmodel.impl.writer.str;

import java.io.File;

/* loaded from: classes.dex */
public interface IStrWriter {
    void clearLogCache();

    boolean write(File file, String str, boolean z);
}
