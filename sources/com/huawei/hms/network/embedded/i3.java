package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.t7;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import java.util.Random;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class i3 extends WebSocket implements k4 {
    public static final String e = "OkHttpWebSocketProxy";

    /* renamed from: a, reason: collision with root package name */
    public ua f5302a;
    public Request b;
    public q7 c;
    public p3 d;

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean send(byte[] bArr) {
        eb e2 = eb.e(bArr);
        this.d.onSend();
        return this.f5302a.b(e2);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean send(String str) {
        return this.f5302a.b(str);
    }

    public void resetPingIntervalOnReadPong(long j) {
        this.f5302a.a(j);
        Logger.v(e, "resetPingIntervalOnReadPong " + j);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public void resetPingInterval(long j) {
        Request request = this.b;
        if (request != null) {
            int enableDynamicPing = ((h1.d) request).getNetConfig().enableDynamicPing();
            if (enableDynamicPing != 0) {
                Logger.w(e, "Cannot reset pinginterval,dynamicPing is enable " + enableDynamicPing);
            } else {
                this.f5302a.a(j);
                Logger.v(e, "resetPingInterval " + j);
            }
        }
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public Request request() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public long queueSize() {
        return this.f5302a.a();
    }

    public p3 getWebSocketListenerAdapter() {
        return this.d;
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean enableDynamicPing(int i) {
        return this.d.enableDynamicPing(i);
    }

    @Override // com.huawei.hms.network.embedded.k4
    public void connect() {
        this.f5302a.a(this.c);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean close(int i, @Nullable String str) {
        return this.f5302a.a(i, str);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public void cancel() {
        this.f5302a.cancel();
    }

    public i3(t7.a aVar, h1.d dVar, WebSocket webSocket, q7 q7Var) {
        if (!(webSocket instanceof j4)) {
            throw new ClassCastException("websocket can not cast to WebSocketImpl");
        }
        this.d = new p3(webSocket, ((j4) webSocket).getWebSocketListener(), dVar);
        this.f5302a = new ua(aVar.a(), this.d, new Random(), q7Var.u());
        this.b = dVar;
        this.c = q7Var;
    }
}
