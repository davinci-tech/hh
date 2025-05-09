package com.huawei.hms.framework.network.restclient.proxy.old2new;

import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.ByteString;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyRequest;
import com.huawei.hms.framework.network.restclient.websocket.WebSocket;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public class ProxyWebSocket implements WebSocket {
    private final com.huawei.hms.network.httpclient.websocket.WebSocket webSocket;

    public ProxyWebSocket(com.huawei.hms.network.httpclient.websocket.WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override // com.huawei.hms.framework.network.restclient.websocket.WebSocket
    public Request request() {
        return ProxyRequest.request2Old(this.webSocket.request());
    }

    @Override // com.huawei.hms.framework.network.restclient.websocket.WebSocket
    public long queueSize() {
        return this.webSocket.queueSize();
    }

    @Override // com.huawei.hms.framework.network.restclient.websocket.WebSocket
    public boolean send(String str) {
        return this.webSocket.send(str);
    }

    @Override // com.huawei.hms.framework.network.restclient.websocket.WebSocket
    public boolean send(ByteString byteString) {
        return this.webSocket.send(byteString.toByteArray());
    }

    @Override // com.huawei.hms.framework.network.restclient.websocket.WebSocket
    public boolean close(int i, @Nonnull String str) {
        return this.webSocket.close(i, str);
    }

    @Override // com.huawei.hms.framework.network.restclient.websocket.WebSocket
    public void cancel() {
        this.webSocket.cancel();
    }
}
