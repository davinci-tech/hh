package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.inner.api.RequestContext;
import java.io.IOException;
import java.util.List;

/* loaded from: classes9.dex */
public class b1 extends Interceptor.Chain {

    /* renamed from: a, reason: collision with root package name */
    public final int f5168a;
    public HttpClient b;
    public List<Interceptor> c;
    public z2 d;
    public d1 e;
    public RequestContext f;

    @Override // com.huawei.hms.network.httpclient.Interceptor.Chain
    public RequestFinishedInfo requestFinishedInfo() {
        d1 d1Var = this.e;
        if (d1Var != null) {
            return d1Var.getRequestFinishedInfo();
        }
        return null;
    }

    @Override // com.huawei.hms.network.httpclient.Interceptor.Chain
    public h1.d request() {
        return (h1.d) this.f.request();
    }

    @Override // com.huawei.hms.network.httpclient.Interceptor.Chain
    public Response<ResponseBody> proceed(Request request) throws IOException {
        ((v3) this.f).setRequest(request);
        return a(this.f, this.e);
    }

    public d1 getRequestTask() {
        return this.e;
    }

    public z2 getRCEventListener() {
        return this.d;
    }

    public HttpClient getClient() {
        return this.b;
    }

    public h1.f<ResponseBody> a(RequestContext requestContext, d1 d1Var) throws IOException {
        if (this.f5168a >= this.c.size()) {
            throw new AssertionError();
        }
        h1.b bVar = new h1.b(new b1(this.b, requestContext, this.c, this.d, this.f5168a + 1, d1Var));
        Interceptor interceptor = this.c.get(this.f5168a);
        Response<ResponseBody> intercept = interceptor.intercept(bVar);
        if (this.f5168a == 0) {
            this.d.cpApplicationInterceptorResEnd();
            g4.getInstance().traceResponseNetworkKitOutEvent(this.d);
        }
        if (intercept != null) {
            return intercept instanceof h1.f ? (h1.f) intercept : new h1.f<>(intercept);
        }
        throw new NullPointerException("interceptor " + interceptor + " returned null");
    }

    public b1(HttpClient httpClient, RequestContext requestContext, List<Interceptor> list, z2 z2Var, int i, d1 d1Var) {
        this.b = httpClient;
        this.f = requestContext;
        this.c = list;
        this.f5168a = i;
        this.d = z2Var;
        this.e = d1Var;
    }
}
