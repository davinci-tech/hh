package com.huawei.hms.framework.network.restclient.hwhttp.trans;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public interface Binary {
    long getLength();

    void onWriteBinary(OutputStream outputStream) throws IOException;
}
