package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.embedded.d1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.network.inner.api.DnsNetworkService;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.RequestContext;
import java.io.IOException;

/* loaded from: classes9.dex */
public class g1 extends Interceptor {
    public static final String h = "RetryInterceptor";
    public static final int i = 429;

    /* renamed from: a, reason: collision with root package name */
    public int f5256a = 0;
    public long b = 0;
    public volatile boolean c;
    public d1 d;
    public final a1 e;
    public final v3 f;
    public final DnsNetworkService g;

    public boolean isCanceled() {
        d1 d1Var;
        return this.c || ((d1Var = this.d) != null && d1Var.isCanceled());
    }

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response<ResponseBody> proceed;
        h1.d dVar;
        if (!(chain instanceof h1.b)) {
            throw new ClassCastException("the classType has error!,please use SafeApi.SafeChain");
        }
        h1.b bVar = (h1.b) chain;
        z2 rCEventListener = bVar.getRCEventListener();
        rCEventListener.cpApplicationInterceptorReqEnd();
        Request request = chain.request();
        this.f.setRequest(request);
        this.g.beginEachRequest(this.f);
        while (!isCanceled()) {
            d1.a factory = this.e.getFactory(request);
            this.d = factory.newTask();
            a(this.f, request, factory.getChannel());
            try {
                if (this.f5256a == 0) {
                    this.b = i1.requestDiscreteControl(request);
                }
                Logger.v(h, "waitingTime: " + this.b);
                rCEventListener.retryInterceptorStart(request, this.d, this.b);
                b(request);
                proceed = bVar.proceed(this.f, this.d);
                a(proceed);
                rCEventListener.retryInterceptorEnd(proceed, this.e);
                dVar = (h1.d) request;
            } catch (IOException e) {
                rCEventListener.retryInterceptorFailed(e);
                a(e);
                if (a(request, this.e)) {
                    Logger.w(h, "intercept IOException, retry " + this.f5256a + ", code = " + i4.getErrorCodeFromException(e), e);
                    this.b = i1.enableRetryIntervalBackoff(request, this.f5256a);
                    this.f5256a = this.f5256a + 1;
                } else {
                    Logger.w(h, "intercept IOException end");
                    a(request);
                    rCEventListener.cpApplicationInterceptorResStart();
                    throw e;
                }
            }
            if (!a(request, this.e) || !dVar.getNetConfig().enableTrafficControlWith429() || proceed.getCode() != 429) {
                rCEventListener.cpApplicationInterceptorResStart();
                return proceed;
            }
            this.b = i1.enableTrafficControlWith429(request, proceed);
            this.f5256a++;
        }
        throw t0.a("Canceled");
    }

    public d1 getRequestTask() {
        return this.d;
    }

    public void cancel() {
        this.c = true;
        d1 d1Var = this.d;
        if (d1Var != null) {
            d1Var.cancel();
        }
    }

    private void b(Request request) {
        this.f.setThrowable(null);
        this.f.setResponse(null);
    }

    private boolean a(Request request, a1 a1Var) {
        if (!(request instanceof h1.d)) {
            return false;
        }
        h1.d dVar = (h1.d) request;
        if (dVar.getNetConfig().getRetryTimeOnConnectionFailure() <= 0 || this.f5256a >= dVar.getNetConfig().getRetryTimeOnConnectionFailure()) {
            return false;
        }
        return a1Var.disableWeakNetworkRetry() || NetworkUtil.getCurrentNetworkType() != -1;
    }

    private void a(IOException iOException) {
        this.f.setThrowable(iOException);
        this.f.setCancel(isCanceled());
        this.g.endEachRequest(this.f);
    }

    private void a(Response<ResponseBody> response) {
        this.f.setResponse(response);
        this.g.endEachRequest(this.f);
    }

    private void a(Request request) {
        d1 d1Var = this.d;
        if (!(d1Var instanceof j2) || d1Var.isCanceled()) {
            return;
        }
        Logger.i(h, "Cronet request fail, fallback okhttp");
        d3 d3Var = new d3(request.getUrl());
        g2.getInstance().updateQuicHints(d3Var.getHost(), d3Var.getPort(), false);
        PreConnectManager.getInstance().connect(d3Var.getHost(), new PreConnectManager.ConnectCallBack());
    }

    private void a(v3 v3Var, Request request, String str) {
        v3Var.setRequest(request);
        v3Var.setChannel(str);
        v3Var.setResponse(null);
        v3Var.setThrowable(null);
        v3Var.setRequestFinishedInfo(null);
    }

    public g1(RequestContext requestContext, a1 a1Var) {
        this.f = (v3) requestContext;
        this.e = a1Var;
        NetworkService service = NetworkKitInnerImpl.getInstance().getService("dns");
        if (!(service instanceof DnsNetworkService)) {
            throw new IllegalStateException("DNS service is not available");
        }
        this.g = (DnsNetworkService) service;
    }
}
