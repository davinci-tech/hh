package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import java.io.IOException;

/* loaded from: classes9.dex */
public class v0 extends Submit<ResponseBody> {
    public static final String c = "BuildInSubmit";

    /* renamed from: a, reason: collision with root package name */
    public final p2 f5534a;
    public boolean b;

    @Override // com.huawei.hms.network.httpclient.Submit
    public h1.d request() {
        return this.f5534a.request();
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public boolean isExecuted() {
        boolean z;
        synchronized (this) {
            z = this.b;
        }
        return z;
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public boolean isCanceled() {
        return this.f5534a.isCanceled();
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public RequestFinishedInfo getRequestFinishedInfo() {
        return this.f5534a.getFinishedInfo();
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public Response<ResponseBody> execute() throws IOException {
        synchronized (this) {
            if (this.b) {
                throw new IllegalStateException("Already Executed");
            }
            this.b = true;
        }
        return this.f5534a.execute();
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public void enqueue(Callback<ResponseBody> callback) {
        if (callback == null) {
            throw new NullPointerException("callback cannot be null");
        }
        synchronized (this) {
            if (this.b) {
                throw new IllegalStateException("Already Executed");
            }
            this.b = true;
        }
        e1.getInstance().execute(new a(new h1.a(callback)));
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public Callback<ResponseBody> f5535a;

        @Override // java.lang.Runnable
        public void run() {
            AutoCloseable autoCloseable = null;
            try {
                Response<ResponseBody> execute = v0.this.f5534a.execute();
                if (v0.this.isCanceled()) {
                    throw t0.a("Canceled");
                }
                this.f5535a.onResponse(get(), execute);
            } catch (IOException e) {
                if (0 != 0) {
                    try {
                        autoCloseable.close();
                    } catch (IOException unused) {
                        Logger.w(v0.c, "close response catch IOException", e);
                    }
                }
                if (0 != 0) {
                    Logger.w(v0.c, "catch Exception", e);
                } else {
                    this.f5535a.onFailure(get(), e);
                }
            }
        }

        public Submit<ResponseBody> get() {
            return v0.this;
        }

        public a(Callback<ResponseBody> callback) {
            this.f5535a = callback;
        }
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    /* renamed from: clone */
    public Submit<ResponseBody> mo631clone() {
        return new v0(this.f5534a.getClient(), this.f5534a.request(), this.f5534a.getWebSocket());
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public void cancel() {
        this.f5534a.cancel();
    }

    public v0(a1 a1Var, h1.d dVar, WebSocket webSocket) {
        this.f5534a = new p2(this, a1Var, dVar, webSocket);
    }
}
