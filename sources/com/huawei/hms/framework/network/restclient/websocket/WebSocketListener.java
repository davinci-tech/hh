package com.huawei.hms.framework.network.restclient.websocket;

import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.ByteString;

@Deprecated
/* loaded from: classes9.dex */
public abstract class WebSocketListener {
    public void onClosed(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
    }

    public void onMessage(WebSocket webSocket, ByteString byteString) {
    }

    public void onMessage(WebSocket webSocket, String str) {
    }

    public void onOpen(WebSocket webSocket, Response response) {
    }
}
