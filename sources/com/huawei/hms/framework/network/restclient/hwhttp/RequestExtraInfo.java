package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;

/* loaded from: classes4.dex */
public class RequestExtraInfo {
    private RequestFinishedInfo requestFinishedInfo;

    protected RequestExtraInfo() {
    }

    public RequestFinishedInfo getRequestFinishedInfo() {
        return this.requestFinishedInfo;
    }

    protected void setRequestFinishedInfo(RequestFinishedInfo requestFinishedInfo) {
        this.requestFinishedInfo = requestFinishedInfo;
    }
}
