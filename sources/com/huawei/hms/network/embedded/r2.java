package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes9.dex */
public abstract class r2 extends RequestFinishedInfo {
    public static final String f = "BaseRequestFinishedInfo";

    /* renamed from: a, reason: collision with root package name */
    public String f5450a;
    public String b;
    public Exception c;
    public Response d;
    public String e;

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public long getPingInterval() {
        return 0L;
    }

    public void setUrl(String str) {
        this.b = str;
        try {
            this.f5450a = new URL(str).getHost();
        } catch (MalformedURLException unused) {
            Logger.i(f, "fail to get hostname from url");
        }
    }

    public void setResponse(Response response) {
        this.d = response;
    }

    public void setNetMsgId(String str) {
        this.e = str;
    }

    public void setException(Exception exc) {
        this.c = exc;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getUrl() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public Response getResponse() {
        return this.d;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getNetMsgId() {
        return this.e;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getHost() {
        return this.f5450a;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public Exception getException() {
        return this.c;
    }
}
