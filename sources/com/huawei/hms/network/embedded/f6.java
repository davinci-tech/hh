package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.util.TypeUtils;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public class f6 extends SubmitAdapter.Factory {

    /* renamed from: a, reason: collision with root package name */
    public Executor f5244a;

    public class b<T> extends Submit<T> {
        public static final String d = "ExecutorSubmit";

        /* renamed from: a, reason: collision with root package name */
        public Submit<T> f5246a;
        public Executor b;

        @Override // com.huawei.hms.network.httpclient.Submit
        public Request request() throws IOException {
            return this.f5246a.request();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public boolean isExecuted() {
            return this.f5246a.isExecuted();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public boolean isCanceled() {
            return this.f5246a.isCanceled();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public RequestFinishedInfo getRequestFinishedInfo() {
            return this.f5246a.getRequestFinishedInfo();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public Response<T> execute() throws IOException {
            return this.f5246a.execute();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public void enqueue(Callback<T> callback) {
            this.f5246a.enqueue(new a(callback));
        }

        public class a extends Callback<T> {

            /* renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Callback f5247a;

            /* renamed from: com.huawei.hms.network.embedded.f6$b$a$a, reason: collision with other inner class name */
            public class RunnableC0140a implements Runnable {

                /* renamed from: a, reason: collision with root package name */
                public final /* synthetic */ Submit f5248a;
                public final /* synthetic */ Response b;

                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.isCanceled()) {
                        a.this.f5247a.onFailure(this.f5248a, t0.a("Canceled"));
                        return;
                    }
                    try {
                        a.this.f5247a.onResponse(this.f5248a, this.b);
                    } catch (IOException unused) {
                        Logger.w(b.d, "ExecutorSubmit callback onResponse catch IOException");
                    }
                }

                public RunnableC0140a(Submit submit, Response response) {
                    this.f5248a = submit;
                    this.b = response;
                }
            }

            /* renamed from: com.huawei.hms.network.embedded.f6$b$a$b, reason: collision with other inner class name */
            public class RunnableC0141b implements Runnable {

                /* renamed from: a, reason: collision with root package name */
                public final /* synthetic */ Submit f5249a;
                public final /* synthetic */ Throwable b;

                @Override // java.lang.Runnable
                public void run() {
                    a.this.f5247a.onFailure(this.f5249a, this.b);
                }

                public RunnableC0141b(Submit submit, Throwable th) {
                    this.f5249a = submit;
                    this.b = th;
                }
            }

            @Override // com.huawei.hms.network.httpclient.Callback
            public void onResponse(Submit<T> submit, Response<T> response) {
                b.this.b.execute(new RunnableC0140a(submit, response));
            }

            @Override // com.huawei.hms.network.httpclient.Callback
            public void onFailure(Submit<T> submit, Throwable th) {
                b.this.b.execute(new RunnableC0141b(submit, th));
            }

            public a(Callback callback) {
                this.f5247a = callback;
            }
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        /* renamed from: clone */
        public Submit mo631clone() {
            return f6.this.new b(this.f5246a, this.b);
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public void cancel() {
            this.f5246a.cancel();
        }

        public b(Submit<T> submit, Executor executor) {
            this.f5246a = submit;
            this.b = executor;
        }
    }

    public class a extends SubmitAdapter<Object, Object> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f5245a;

        @Override // com.huawei.hms.network.restclient.SubmitAdapter
        public Type responseType() {
            return this.f5245a;
        }

        @Override // com.huawei.hms.network.restclient.SubmitAdapter
        /* renamed from: adapt */
        public Object adapt2(Submit<Object> submit) {
            f6 f6Var = f6.this;
            return f6Var.new b(submit, f6Var.f5244a);
        }

        public a(Type type) {
            this.f5245a = type;
        }
    }

    @Override // com.huawei.hms.network.restclient.SubmitAdapter.Factory
    public SubmitAdapter<?, ?> get(Type type, Annotation[] annotationArr, RestClient restClient) {
        return new a(TypeUtils.getSubmitResponseType(type));
    }

    public f6(Executor executor) {
        this.f5244a = executor;
    }
}
