package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.f1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.q7;
import com.huawei.hms.network.embedded.t7;
import com.huawei.hms.network.embedded.u0;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class j3 implements d1 {
    public static final String g = "OkReqTsk";

    /* renamed from: a, reason: collision with root package name */
    public q7 f5322a;
    public t6 b;
    public h1.d c;
    public boolean d;
    public volatile boolean e;
    public z2 f;

    @Override // com.huawei.hms.network.embedded.d1
    public void setRcEventListener(z2 z2Var) {
        this.f = z2Var;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public h1.d request() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public boolean isExecuted() {
        boolean z;
        synchronized (this) {
            z = this.d;
        }
        return z;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public boolean isCanceled() {
        t6 t6Var;
        return this.e || ((t6Var = this.b) != null && t6Var.isCanceled());
    }

    @Override // com.huawei.hms.network.embedded.d1
    public RequestFinishedInfo getRequestFinishedInfo() {
        synchronized (this) {
            g3 listener = g3.getFactory().getListener(this.b);
            if (listener == null) {
                return null;
            }
            return listener.getRequestFinishedInfo();
        }
    }

    @Override // com.huawei.hms.network.embedded.d1
    public e3 getConnectionInfo() {
        g3 listener = g3.getFactory().getListener(this.b);
        if (listener != null) {
            return listener.getConnectionInfo();
        }
        return null;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public h1.f<ResponseBody> execute(h1.d dVar, WebSocket webSocket) throws IOException {
        a4 a4Var;
        synchronized (this) {
            if (this.d) {
                throw new IllegalStateException("Already executed.");
            }
            this.d = true;
        }
        if (this.e && webSocket == null) {
            throw t0.a("Canceled");
        }
        this.c = dVar;
        t7.a aVar = new t7.a();
        String method = dVar.getMethod();
        if (dVar.getBody() != null) {
            if ("GET".equals(method)) {
                method = "POST";
            } else if (!j9.b(method)) {
                throw new ProtocolException(method + " does not support writing");
            }
            a4Var = new a4(dVar.getBody());
        } else {
            a4Var = null;
        }
        Headers of = Headers.of(dVar.getHeaders());
        int size = of.size();
        j7.a aVar2 = new j7.a();
        for (int i = 0; i < size; i++) {
            aVar2.a(of.name(i), of.value(i));
        }
        aVar.c(dVar.getUrl()).a(method, a4Var).a(aVar2.a()).a(dVar.getNetConfig().enableConcurrentConnect());
        q7 a2 = a(dVar);
        if (dVar.getNetConfig().enableSiteDetect()) {
            Logger.i(g, "the request has used the okhttp! SiteDetect");
            this.b = new n3(a2, aVar.a(dVar.getTag()).a());
        } else if (dVar.getNetConfig().enableConnectEmptyBody() || dVar.getNetConfig().enableInnerConnectEmptyBody()) {
            Logger.i(g, "the request has used the okhttp! OnlyConnectCall");
            this.b = new m3(a2, aVar.a());
        } else {
            if (webSocket != null) {
                Logger.i(g, "the request has used the okhttp! WebSocket");
                if (!(webSocket instanceof h1.i)) {
                    throw new ClassCastException("websocket can not cast to SafeApi.SafeWebSocket");
                }
                WebSocket webSocket2 = ((h1.i) webSocket).getWebSocket();
                if (!(webSocket2 instanceof j4)) {
                    throw new ClassCastException("websocket can not cast to WebSocketImpl");
                }
                j4 j4Var = (j4) webSocket2;
                i3 i3Var = new i3(aVar, dVar, j4Var, c(dVar));
                i3Var.connect();
                j4Var.setProxy(i3Var);
                i3Var.getWebSocketListenerAdapter().predictorModel();
                return i3Var.getWebSocketListenerAdapter().getResponse();
            }
            Logger.i(g, "the request has used the okhttp! ");
            this.b = a2.a(aVar.a());
            g3 listener = g3.getFactory().getListener(this.b);
            if (listener != null) {
                listener.setRCEventListener(this.f);
            }
        }
        if (this.e) {
            throw t0.a("Canceled");
        }
        return a(this.b.execute());
    }

    @Override // com.huawei.hms.network.embedded.d1
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public d1 m634clone() {
        return new j3(this.f5322a);
    }

    @Override // com.huawei.hms.network.embedded.d1
    public void cancel() {
        this.e = true;
        t6 t6Var = this.b;
        if (t6Var != null) {
            t6Var.cancel();
        }
    }

    private q7 c(h1.d dVar) {
        int pingInterval = dVar.getNetConfig().getPingInterval();
        Logger.d(g, "enableDynamicPing:" + dVar.getNetConfig().enableDynamicPing());
        if (dVar.getNetConfig().enableDynamicPing() == 2) {
            pingInterval = 120000;
        }
        return b(dVar).d(pingInterval, TimeUnit.MILLISECONDS).a(o3.getWebSocketEventFactory()).a();
    }

    private q7.c b(h1.d dVar) {
        return this.f5322a.t().b(dVar.getNetConfig().getConnectTimeout(), TimeUnit.MILLISECONDS).e(dVar.getNetConfig().getReadTimeout(), TimeUnit.MILLISECONDS).d(dVar.getNetConfig().getPingInterval(), TimeUnit.MILLISECONDS).f(dVar.getNetConfig().getWriteTimeout(), TimeUnit.MILLISECONDS).c(dVar.getNetConfig().getConcurrentConnectDelay(), TimeUnit.MILLISECONDS);
    }

    private Map<String, List<String>> a(j7 j7Var) {
        Headers.Builder builder = new Headers.Builder();
        int d = j7Var.d();
        for (int i = 0; i < d; i++) {
            builder.addLenient(j7Var.a(i), j7Var.b(i));
        }
        return builder.build().toMultimap();
    }

    private q7 a(h1.d dVar) {
        return b(dVar).a();
    }

    private h1.f<ResponseBody> a(v7 v7Var) {
        w7 s = v7Var.s();
        String a2 = v7Var.y().a("Content-Type");
        f1 f1Var = null;
        o7 b = a2 != null ? o7.b(a2) : null;
        if (s != null) {
            f1.b charSet = new f1.b().inputStream(s.s()).contentLength(s.v()).charSet(b != null ? b.a() : null);
            if (a2 == null) {
                a2 = "";
            }
            f1Var = charSet.contentType(a2).build();
        }
        u0.b bVar = new u0.b();
        if (f1Var != null) {
            bVar.body(new h1.g(f1Var));
        }
        bVar.headers(a(v7Var.y())).code(v7Var.w()).message(v7Var.B()).url(v7Var.H().k().toString());
        return new h1.f<>(bVar.build());
    }

    public j3(q7 q7Var) {
        this.f5322a = q7Var;
    }
}
