package com.huawei.hms.network.httpclient.websocket;

import com.huawei.hms.network.httpclient.Request;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public abstract class WebSocket {
    public abstract void cancel();

    public abstract boolean close(int i, @Nonnull String str);

    public abstract boolean enableDynamicPing(int i);

    public abstract long queueSize();

    public abstract Request request();

    @Deprecated
    public abstract void resetPingInterval(long j);

    public abstract boolean send(String str);

    public abstract boolean send(byte[] bArr);
}
