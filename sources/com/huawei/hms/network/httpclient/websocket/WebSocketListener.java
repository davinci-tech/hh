package com.huawei.hms.network.httpclient.websocket;

import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;

/* loaded from: classes4.dex */
public abstract class WebSocketListener {
    public void onClosed(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response<ResponseBody> response) {
    }

    public void onMessage(WebSocket webSocket, String str) {
    }

    public void onMessage(WebSocket webSocket, byte[] bArr) {
    }

    public void onOpen(WebSocket webSocket, Response<ResponseBody> response) {
    }
}
