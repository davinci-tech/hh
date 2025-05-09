package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.inner.api.RequestContext;

/* loaded from: classes9.dex */
public class v3 extends RequestContext {

    /* renamed from: a, reason: collision with root package name */
    public Request f5537a;
    public Response<ResponseBody> b;
    public Throwable c;
    public boolean d = false;
    public RequestFinishedInfo e;
    public String f;
    public int g;

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public Throwable throwable() {
        return this.c;
    }

    public void setThrowable(Throwable th) {
        this.c = th;
    }

    public void setResponse(Response<ResponseBody> response) {
        if (response == null || (response instanceof h1.f)) {
            this.b = response;
        } else {
            this.b = new h1.f(response);
        }
    }

    public void setRequestFinishedInfo(RequestFinishedInfo requestFinishedInfo) {
        this.e = requestFinishedInfo;
    }

    public void setRequest(Request request) {
        if (request == null || (request instanceof h1.d)) {
            this.f5537a = request;
        } else {
            this.f5537a = new h1.d(request);
        }
    }

    public void setConnectTimeout(int i) {
        this.g = i;
    }

    public void setChannel(String str) {
        this.f = str;
    }

    public void setCancel(boolean z) {
        this.d = z;
    }

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public Response<ResponseBody> response() {
        return this.b;
    }

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public RequestFinishedInfo requestFinishedInfo() {
        return this.e;
    }

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public Request request() {
        return this.f5537a;
    }

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public boolean isCancel() {
        return this.d;
    }

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public int getConnectTimeout() {
        return this.g;
    }

    @Override // com.huawei.hms.network.inner.api.RequestContext
    public String getChannel() {
        return this.f;
    }
}
