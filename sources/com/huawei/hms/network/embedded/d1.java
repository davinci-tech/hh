package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import java.io.IOException;

/* loaded from: classes9.dex */
public interface d1 {

    public interface a {
        String getChannel();

        boolean isAvailable();

        d1 newTask();
    }

    void cancel();

    d1 clone();

    h1.f<ResponseBody> execute(h1.d dVar, WebSocket webSocket) throws IOException;

    e3 getConnectionInfo();

    RequestFinishedInfo getRequestFinishedInfo();

    boolean isCanceled();

    boolean isExecuted();

    h1.d request();

    void setRcEventListener(z2 z2Var);
}
