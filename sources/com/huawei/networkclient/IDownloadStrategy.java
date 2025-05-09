package com.huawei.networkclient;

import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.util.Map;

/* loaded from: classes5.dex */
public interface IDownloadStrategy<Output> extends IRequest {
    Map<String, String> getHeaders();

    ProgressListener<Output> getListener();

    String getRequestBody();

    int getRequestMethod();

    void handleException(Throwable th, int i);

    void handleResponseBody(ResponseBody responseBody, int i);
}
