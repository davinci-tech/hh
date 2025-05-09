package com.huawei.hms.network.base.common.trans;

import java.io.OutputStream;

/* loaded from: classes4.dex */
public interface Binary {
    long getLength();

    void onWriteBinary(OutputStream outputStream);
}
