package com.huawei.hms.network.httpclient;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public abstract class RequestBody {
    public long contentLength() throws IOException {
        return -1L;
    }

    public abstract String contentType();

    public boolean endOfStream() {
        return false;
    }

    public boolean isDuplex() {
        return false;
    }

    public abstract void writeTo(OutputStream outputStream) throws IOException;
}
