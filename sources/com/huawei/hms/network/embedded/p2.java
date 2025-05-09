package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.inner.api.InterceptorNetworkService;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class p2 {
    public static final String h = "InterceptorExecutor";

    /* renamed from: a, reason: collision with root package name */
    public final a1 f5408a;
    public final g1 b;
    public final WebSocket c;
    public final z2 d;
    public h1.d e;
    public final w3 f;
    public RequestFinishedInfo g;
    public v3 requestContext = new v3();

    public h1.d request() {
        return this.e;
    }

    public boolean isCanceled() {
        return this.b.isCanceled();
    }

    public WebSocket getWebSocket() {
        return this.c;
    }

    public RequestFinishedInfo getFinishedInfo() {
        return this.g;
    }

    public a1 getClient() {
        return this.f5408a;
    }

    public Response<ResponseBody> execute() throws IOException {
        int callTimeout = this.e.getNetConfig().getCallTimeout();
        int connectTimeout = this.e.getNetConfig().getConnectTimeout();
        int readTimeout = this.e.getNetConfig().getReadTimeout();
        int writeTimeout = this.e.getNetConfig().getWriteTimeout();
        if (callTimeout != 0 && (callTimeout <= connectTimeout + readTimeout || callTimeout <= connectTimeout + writeTimeout)) {
            Logger.e(h, "callTimeout should be bigger than connectTimeout + readTimeout/writeTimeout. [callTimeout : " + callTimeout + ", connectTimeout : " + connectTimeout + ", readTimeout : " + readTimeout + ", writeTimeout : " + writeTimeout + "].");
        }
        this.d.callStart();
        this.e = g4.getInstance().traceRequestNetworkKitInEvent(request());
        this.f.enter();
        String channel = this.f5408a.getFactory(this.e).getChannel();
        this.requestContext.setChannel(channel);
        this.requestContext.setRequest(request());
        this.f5408a.getPolicyExecutor().beginRequest(this.requestContext);
        if ((this.f5408a.getTrustManager() == null || this.f5408a.getSslSocketFactory() == null) && !"type_cronet".equals(channel)) {
            throw t0.d("The trustManager or sslSocketFactory of httpClient is null");
        }
        this.d.acquireRequestStart();
        this.d.acquireRequestEnd(request());
        this.requestContext.setConnectTimeout(this.e.getNetConfig().getConnectTimeout());
        ArrayList arrayList = new ArrayList(this.f5408a.getInterceptors());
        this.d.recordCpApplicationInterceptorNums(arrayList.size());
        ArrayList arrayList2 = new ArrayList(this.f5408a.getNetworkInterceptors());
        this.d.recordCpNetworkInterceptorNums(arrayList2.size());
        for (NetworkService networkService : NetworkKitInnerImpl.getInstance().getAll()) {
            if (networkService instanceof InterceptorNetworkService) {
                InterceptorNetworkService interceptorNetworkService = (InterceptorNetworkService) networkService;
                boolean isNetworkInterceptor = interceptorNetworkService.isNetworkInterceptor();
                Interceptor interceptor = interceptorNetworkService.getInterceptor();
                if (isNetworkInterceptor) {
                    arrayList2.add(interceptor);
                } else {
                    arrayList.add(interceptor);
                }
            }
        }
        arrayList.add(this.b);
        if (this.c == null) {
            arrayList.add(new r1(this.f5408a.getCache()));
            arrayList.add(new t3());
        }
        arrayList.addAll(arrayList2);
        arrayList.add(new w0(this.c));
        try {
            Response<ResponseBody> proceed = new h1.b(new b1(this.f5408a, this.requestContext, arrayList, this.d, 0, null)).proceed(request());
            a((p2) proceed);
            this.requestContext.setResponse(proceed);
            this.f5408a.getPolicyExecutor().endRequest(this.requestContext);
            this.d.callEnd(proceed);
            this.f.exit();
            Logger.i(h, "response code:" + proceed.getCode());
            return proceed;
        } catch (Throwable th) {
            IOException a2 = a(th);
            a((p2) a2);
            this.requestContext.setThrowable(a2);
            this.f5408a.getPolicyExecutor().endRequest(this.requestContext);
            this.d.callFailed(a2);
            Logger.w(h, "request fail:", a2);
            throw a2;
        }
    }

    public void cancel() {
        this.d.cancel();
        this.b.cancel();
    }

    public IOException a(@Nullable IOException iOException) {
        return !this.f.exit() ? iOException : t0.c("Timeout", iOException);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void a(T t) {
        if (this.b.getRequestTask() == null) {
            this.g = null;
        } else {
            RequestFinishedInfo requestFinishedInfo = this.b.getRequestTask().getRequestFinishedInfo();
            this.g = requestFinishedInfo;
            if (requestFinishedInfo instanceof r2) {
                if (t instanceof Response) {
                    ((r2) requestFinishedInfo).setResponse((Response) t);
                } else if (t instanceof Exception) {
                    ((r2) requestFinishedInfo).setException((Exception) t);
                }
                ((r2) this.g).setNetMsgId(g4.getInstance().getNetMsgIdFromRequest(this.e));
            }
        }
        this.requestContext.setRequestFinishedInfo(this.g);
    }

    public class a extends w3 {
        @Override // com.huawei.hms.network.embedded.w3
        public void timedOut() {
            p2.this.d.cancel();
            p2.this.b.cancel();
        }

        public a() {
        }
    }

    private IOException a(Throwable th) {
        if (th instanceof IOException) {
            return a((IOException) th);
        }
        IOException b = t0.b(th.getMessage(), th);
        this.f.exit();
        HianalyticsHelper.getInstance().reportException(th, CrashHianalyticsData.EVENT_ID_CRASH);
        return b;
    }

    public p2(Submit<ResponseBody> submit, a1 a1Var, h1.d dVar, WebSocket webSocket) {
        this.f5408a = a1Var;
        this.e = dVar;
        this.c = webSocket;
        z2 create = webSocket == null ? a1Var.getEventListenerFactory().create(submit) : z2.NONE;
        this.d = create;
        create.acquireClient(a1Var);
        this.b = new g1(this.requestContext, a1Var);
        a aVar = new a();
        this.f = aVar;
        aVar.timeout(dVar.getNetConfig().getCallTimeout(), TimeUnit.MILLISECONDS);
    }
}
