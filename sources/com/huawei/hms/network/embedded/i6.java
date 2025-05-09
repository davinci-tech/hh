package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.u0;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import java.io.IOException;

/* loaded from: classes9.dex */
public class i6<T> extends Submit<T> {
    public static final String g = "RealSubmit";

    /* renamed from: a, reason: collision with root package name */
    public final k6<T, ?> f5306a;
    public final Object[] b;
    public Submit.Factory c;
    public h1.h<T> d;
    public volatile boolean e;
    public boolean f;

    @Override // com.huawei.hms.network.httpclient.Submit
    public Request request() throws IOException {
        Request request;
        synchronized (this) {
            if (this.d == null) {
                this.d = a();
            }
            request = this.d.request();
        }
        return request;
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public boolean isExecuted() {
        boolean z;
        synchronized (this) {
            z = this.f;
        }
        return z;
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public boolean isCanceled() {
        h1.h<T> hVar;
        return this.e || ((hVar = this.d) != null && hVar.isCanceled());
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public RequestFinishedInfo getRequestFinishedInfo() {
        h1.h<T> hVar = this.d;
        if (hVar == null) {
            return null;
        }
        return hVar.getRequestFinishedInfo();
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public Response<T> execute() throws IOException {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        if (this.e) {
            throw t0.a("Canceled");
        }
        if (this.d == null) {
            this.d = a();
        }
        return a(this.d.execute());
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public void enqueue(Callback<T> callback) {
        if (callback == null) {
            throw new NullPointerException("callback cannot be null");
        }
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        try {
            if (this.e) {
                throw t0.a("Canceled");
            }
            if (this.d == null) {
                this.d = a();
            }
            this.d.enqueue(new a(callback));
        } catch (Exception e) {
            callback.onFailure(this, e);
        }
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    /* renamed from: clone */
    public Submit<T> mo631clone() {
        return new i6(this.c, this.f5306a, this.b);
    }

    @Override // com.huawei.hms.network.httpclient.Submit
    public void cancel() {
        this.e = true;
        h1.h<T> hVar = this.d;
        if (hVar != null) {
            hVar.cancel();
        }
    }

    public class a extends Callback<T> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Callback f5307a;

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onResponse(Submit<T> submit, Response<T> response) {
            try {
                this.f5307a.onResponse(submit, i6.this.a(response));
            } catch (Exception e) {
                this.f5307a.onFailure(submit, e);
            }
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onFailure(Submit<T> submit, Throwable th) {
            this.f5307a.onFailure(submit, th);
        }

        public a(Callback callback) {
            this.f5307a = callback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response<T> a(Response<ResponseBody> response) throws IOException {
        u0.b bVar = new u0.b();
        bVar.headers(response.getHeaders());
        bVar.code(response.getCode());
        bVar.message(response.getMessage());
        bVar.url(response.getUrl());
        bVar.errorBody(response.getErrorBody());
        if (response.getBody() != null) {
            k6<T, ?> k6Var = this.f5306a;
            bVar.body(k6Var != null ? k6Var.a(response.getBody()) : null);
        }
        return bVar.build();
    }

    private h1.h<T> a() throws IOException {
        Submit.Factory factory = this.c;
        Submit<ResponseBody> newSubmit = factory.newSubmit(this.f5306a.a(factory, this.b));
        if (newSubmit != null) {
            return newSubmit instanceof h1.h ? (h1.h) newSubmit : new h1.h<>(newSubmit);
        }
        throw new IOException("create submit error");
    }

    public i6(Submit.Factory factory, k6<T, ?> k6Var, Object[] objArr) {
        this.c = factory;
        this.f5306a = k6Var;
        this.b = objArr;
    }
}
