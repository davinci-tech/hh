package com.huawei.hms.framework.network.restclient.proxy.new2old;

import com.huawei.hms.framework.network.restclient.hwhttp.trans.ByteString;
import com.huawei.hms.framework.network.restclient.proxy.old2new.ProxyWebSocket;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;

/* loaded from: classes9.dex */
public class ProxyWebSocketListener extends WebSocketListener {
    private final com.huawei.hms.framework.network.restclient.websocket.WebSocketListener webSocketListener;

    public ProxyWebSocketListener(com.huawei.hms.framework.network.restclient.websocket.WebSocketListener webSocketListener) {
        this.webSocketListener = webSocketListener;
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
    public void onOpen(WebSocket webSocket, Response<ResponseBody> response) {
        this.webSocketListener.onOpen(new ProxyWebSocket(webSocket), ProxyResponse.response2Old(response));
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
    public void onMessage(WebSocket webSocket, String str) {
        this.webSocketListener.onMessage(new ProxyWebSocket(webSocket), str);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
    public void onMessage(WebSocket webSocket, byte[] bArr) {
        this.webSocketListener.onMessage(new ProxyWebSocket(webSocket), ByteString.of(bArr));
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
    public void onClosing(WebSocket webSocket, int i, String str) {
        this.webSocketListener.onClosing(new ProxyWebSocket(webSocket), i, str);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
    public void onClosed(WebSocket webSocket, int i, String str) {
        this.webSocketListener.onClosed(new ProxyWebSocket(webSocket), i, str);
    }

    @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
    public void onFailure(WebSocket webSocket, Throwable th, Response<ResponseBody> response) {
        this.webSocketListener.onFailure(new ProxyWebSocket(webSocket), th, ProxyResponse.response2Old(response));
    }
}
