package com.huawei.agconnect.https;

import com.huawei.agconnect.https.Adapter;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes2.dex */
public abstract class Method<HttpsRequest> {
    HttpsRequest httpsRequest;

    abstract Request.Builder create();

    public static class Post<HttpsRequest> extends Method {
        Adapter.Factory factory;

        public Request.Builder createBody(Request.Builder builder, RequestBody requestBody) {
            builder.post(requestBody);
            return builder;
        }

        @Override // com.huawei.agconnect.https.Method
        public Request.Builder create() {
            Request.Builder a2 = f.a(this.httpsRequest).a();
            try {
                if (this.factory.requestBodyAdapter() != null) {
                    return createBody(a2, (RequestBody) this.factory.requestBodyAdapter().adapter(this.httpsRequest));
                }
                throw new IllegalArgumentException("RequestBodyAdapter should not be null.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void init(HttpsRequest httpsrequest, Adapter.Factory factory) {
            this.httpsRequest = httpsrequest;
            this.factory = factory;
        }

        public Post(HttpsRequest httpsrequest, Adapter.Factory factory) {
            init(httpsrequest, factory);
        }
    }

    /* loaded from: classes8.dex */
    public static final class a<HttpsRequest> extends Method {

        /* renamed from: a, reason: collision with root package name */
        Adapter.Factory f1790a;

        @Override // com.huawei.agconnect.https.Method
        public Request.Builder create() {
            Request.Builder a2 = f.a(this.httpsRequest).a();
            Adapter.Factory factory = this.f1790a;
            if (factory == null || factory.requestBodyAdapter() == null) {
                a2.delete();
            } else {
                try {
                    a2.delete((RequestBody) this.f1790a.requestBodyAdapter().adapter(this.httpsRequest));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return a2;
        }

        public a(HttpsRequest httpsrequest, Adapter.Factory factory) {
            this.httpsRequest = httpsrequest;
            this.f1790a = factory;
        }

        public a(HttpsRequest httpsrequest) {
            this.httpsRequest = httpsrequest;
        }
    }

    public static class Get<HttpsRequest> extends Method {
        @Override // com.huawei.agconnect.https.Method
        public Request.Builder create() {
            return f.a(this.httpsRequest).a();
        }

        public Get(HttpsRequest httpsrequest) {
            this.httpsRequest = httpsrequest;
        }
    }

    public static class Put<HttpsRequest> extends Post {
        @Override // com.huawei.agconnect.https.Method.Post
        public Request.Builder createBody(Request.Builder builder, RequestBody requestBody) {
            builder.put(requestBody);
            return builder;
        }

        public Put(HttpsRequest httpsrequest, Adapter.Factory factory) {
            super(httpsrequest, factory);
        }
    }
}
