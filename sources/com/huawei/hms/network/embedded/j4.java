package com.huawei.hms.network.embedded;

import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;

/* loaded from: classes9.dex */
public class j4 extends WebSocket {

    /* renamed from: a, reason: collision with root package name */
    public WebSocket f5323a;
    public Request b;
    public WebSocketListener c;

    public void setProxy(WebSocket webSocket) {
        this.f5323a = webSocket;
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean send(byte[] bArr) {
        WebSocket webSocket = this.f5323a;
        if (webSocket == null) {
            return false;
        }
        return webSocket.send(bArr);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean send(String str) {
        WebSocket webSocket = this.f5323a;
        if (webSocket == null) {
            return false;
        }
        return webSocket.send(str);
    }

    public void resetPingIntervalOnReadPong(long j) {
        WebSocket webSocket = this.f5323a;
        if (webSocket == null || !(webSocket instanceof i3)) {
            return;
        }
        ((i3) webSocket).resetPingIntervalOnReadPong(j);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public void resetPingInterval(long j) {
        WebSocket webSocket = this.f5323a;
        if (webSocket != null) {
            webSocket.resetPingInterval(j);
        }
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public Request request() {
        WebSocket webSocket = this.f5323a;
        if (webSocket == null) {
            return null;
        }
        return webSocket.request();
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public long queueSize() {
        WebSocket webSocket = this.f5323a;
        if (webSocket == null) {
            return 0L;
        }
        return webSocket.queueSize();
    }

    public WebSocketListener getWebSocketListener() {
        return this.c;
    }

    public Request getRequest() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean enableDynamicPing(int i) {
        WebSocket webSocket = this.f5323a;
        if (webSocket != null) {
            return webSocket.enableDynamicPing(i);
        }
        return false;
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public boolean close(int i, String str) {
        WebSocket webSocket = this.f5323a;
        if (webSocket == null) {
            return false;
        }
        return webSocket.close(i, str);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
    public void cancel() {
        WebSocket webSocket = this.f5323a;
        if (webSocket != null) {
            webSocket.cancel();
        }
    }

    public j4(Request request, WebSocketListener webSocketListener) {
        this.b = request;
        this.c = webSocketListener;
    }
}
