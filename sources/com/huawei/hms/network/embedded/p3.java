package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.f1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.u0;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/* loaded from: classes9.dex */
public class p3 extends a8 {
    public static final String n = "WebSocketListenerAdapter";
    public static final String o = "connect_failed";
    public static final String p = "connect_closed";
    public static final String q = "WebSocket_PredictorModel";

    /* renamed from: a, reason: collision with root package name */
    public WebSocketListener f5409a;
    public WebSocket b;
    public volatile h1.f<ResponseBody> d;
    public Throwable e;
    public h1.d f;
    public l3 g;
    public q3 h;
    public r3 i;
    public int j;
    public LinkedList<Long> k;
    public int l;
    public CountDownLatch c = new CountDownLatch(1);
    public final ExecutorService m = ExecutorsUtils.newSingleThreadExecutor(q);

    public void predictorModel() {
        this.m.execute(new a());
    }

    public void onSend() {
        this.i.counting(1);
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onReadPong(long j, LinkedList<Long> linkedList) {
        super.onReadPong(j, linkedList);
        if (this.l == 0 || !this.i.aiPingEnable()) {
            return;
        }
        this.k.clear();
        this.k.addAll(linkedList);
        try {
            int pingResult = this.i.setPingResult(true, this.j, linkedList);
            if (pingResult != 0) {
                ((j4) this.b).resetPingIntervalOnReadPong(pingResult);
            } else {
                Logger.d(n, "PingResult ping is:" + pingResult);
            }
        } catch (Throwable unused) {
            Logger.w(n, "PingResult error on onReadPong");
        }
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onOpen(z7 z7Var, v7 v7Var) {
        a(z7Var);
        this.g.getMetricsTime().setCallEndTime();
        this.g.getMetricsRealTime().setCallEndTime();
        this.h.setOnOpenTime(System.currentTimeMillis());
        this.i.setOnOpenTime(System.currentTimeMillis());
        this.d = a(v7Var);
        this.f5409a.onOpen(this.b, this.d);
        CountDownLatch countDownLatch = this.c;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onMessage(z7 z7Var, String str) {
        this.f5409a.onMessage(this.b, str);
        this.i.counting(2);
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onMessage(z7 z7Var, eb ebVar) {
        this.f5409a.onMessage(this.b, ebVar.m());
        this.i.counting(2);
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onFailure(z7 z7Var, Throwable th, v7 v7Var) {
        this.e = th;
        a(z7Var);
        this.g.getMetricsRealTime().setRequestBodyEndTime();
        this.g.getMetricsTime().setRequestBodyEndTime();
        this.g.getMetricsTime().setCallEndTime();
        this.g.getMetricsRealTime().setCallEndTime();
        if (th instanceof Exception) {
            Exception exc = (Exception) th;
            this.g.setException(exc);
            this.h.reportData(exc, o);
        } else {
            Exception exc2 = new Exception(th);
            this.g.setException(exc2);
            this.h.reportData(exc2, o);
        }
        this.d = v7Var == null ? null : a(v7Var);
        this.f5409a.onFailure(this.b, th, this.d);
        CountDownLatch countDownLatch = this.c;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
        if (this.l == 0 || this.k.size() <= 0) {
            return;
        }
        try {
            this.i.setPingResult(false, this.j, this.k);
        } catch (Throwable unused) {
            Logger.w(n, "PingResult error on onFailure");
        }
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onClosing(z7 z7Var, int i, String str) {
        this.f5409a.onClosing(this.b, i, str);
    }

    @Override // com.huawei.hms.network.embedded.a8
    public void onClosed(z7 z7Var, int i, String str) {
        a(z7Var);
        this.g.getMetricsRealTime().setRequestBodyEndTime();
        this.g.getMetricsTime().setRequestBodyEndTime();
        this.h.reportData(Integer.valueOf(i), p);
        this.f5409a.onClosed(this.b, i, str);
        Logger.v(n, "Closed " + str);
        CountDownLatch countDownLatch = this.c;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public h1.f<ResponseBody> getResponse() throws IOException {
        try {
            this.c.await();
        } catch (InterruptedException e) {
            Logger.w(n, "InterruptedException ", e);
        }
        if (this.d == null) {
            Throwable th = this.e;
            if (th instanceof IOException) {
                throw ((IOException) th);
            }
        }
        return this.d == null ? new h1.f<>(new u0.b().build()) : this.d;
    }

    public CountDownLatch getCountDownLatch() {
        return this.c;
    }

    public boolean enableDynamicPing(int i) {
        StringBuilder sb;
        if (this.j <= 0) {
            sb = new StringBuilder("canot predictor model ,pingInterval is:");
            sb.append(this.j);
        } else {
            r3 r3Var = this.i;
            r1 = r3Var != null ? r3Var.setActionType(i) : false;
            sb = new StringBuilder("enable dynamic ping model predictor:");
            sb.append(r1);
        }
        Logger.i(n, sb.toString());
        return r1;
    }

    private void a(z7 z7Var) {
        if (z7Var instanceof ua) {
            ua uaVar = (ua) z7Var;
            this.h.setPingPongDelayList(uaVar.d());
            g3 listener = o3.getWebSocketEventFactory().getListener(uaVar.c());
            if (listener == null || !(listener instanceof o3)) {
                return;
            }
            l3 webSocketRequestFinishedInfo = ((o3) listener).getWebSocketRequestFinishedInfo();
            this.g = webSocketRequestFinishedInfo;
            if (webSocketRequestFinishedInfo == null) {
                Logger.i(n, "webSocketRequestFinishedInfo is null");
                this.g = new l3();
            }
            this.g.getMetricsTime().setPingInterval(this.f.getNetConfig().getPingInterval());
            this.h.setRequestFinishedInfo(this.g);
            this.i.setRequestFinishedInfo(this.g);
        }
    }

    private void a() {
        this.h = new q3(this.g, this.f);
        String str = this.f.getNetConfig().getMap("core_metrics_data").get("trace_id");
        int enableDynamicPing = this.f.getNetConfig().enableDynamicPing();
        this.l = enableDynamicPing;
        r3 r3Var = new r3(enableDynamicPing, this.j, str);
        this.i = r3Var;
        this.h.setPingIntervalManager(r3Var);
        this.k = new LinkedList<>();
        Logger.d(n, "init actionType is:" + this.l);
    }

    private Map<String, List<String>> a(j7 j7Var) {
        Headers.Builder builder = new Headers.Builder();
        int d = j7Var.d();
        for (int i = 0; i < d; i++) {
            builder.add(j7Var.a(i), j7Var.b(i));
        }
        return builder.build().toMultimap();
    }

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            StringBuilder sb;
            int i;
            StringBuilder sb2;
            if (p3.this.j > 0) {
                if (p3.this.l == 1 || p3.this.l == 2) {
                    boolean predictorModel = p3.this.i != null ? p3.this.i.predictorModel() : false;
                    sb2 = new StringBuilder("webSocket ping model predictor:");
                    sb2.append(predictorModel);
                    Logger.i(p3.n, sb2.toString());
                }
                sb = new StringBuilder("canot predictor model ,actionType is:");
                i = p3.this.l;
            } else {
                sb = new StringBuilder("canot predictor model ,pingInterval is:");
                i = p3.this.j;
            }
            sb.append(i);
            sb2 = sb;
            Logger.i(p3.n, sb2.toString());
        }

        public a() {
        }
    }

    private h1.f<ResponseBody> a(v7 v7Var) {
        w7 s = v7Var.s();
        String a2 = v7Var.y().a("Content-Type");
        f1 f1Var = null;
        o7 b = a2 != null ? o7.b(a2) : null;
        if (s != null) {
            f1Var = new f1.b().inputStream(s.s()).contentLength(s.v()).charSet(b != null ? b.a() : null).contentType(b != null ? b.c() : "").build();
        }
        u0.b bVar = new u0.b();
        if (f1Var != null) {
            bVar.body(new h1.g(f1Var));
        }
        bVar.headers(a(v7Var.y())).code(v7Var.w()).message(v7Var.B()).url(v7Var.H().k().toString());
        return new h1.f<>(bVar.build());
    }

    public p3(WebSocket webSocket, WebSocketListener webSocketListener, h1.d dVar) {
        this.f5409a = webSocketListener;
        this.b = webSocket;
        this.f = dVar;
        this.j = dVar.getNetConfig().getPingInterval();
        a();
    }
}
