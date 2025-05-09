package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;
import com.huawei.hms.framework.network.restclient.websocket.WebSocket;
import java.io.IOException;

/* loaded from: classes9.dex */
public interface RequestTask {

    public interface Factory {
        RequestTask newTask();
    }

    void cancel();

    RequestTask clone();

    Response execute(Request request, WebSocket webSocket) throws IOException;

    RequestFinishedInfo getRequestFinishedInfo();

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
