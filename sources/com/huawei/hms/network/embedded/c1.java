package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.config.NetworkConfig;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class c1 extends Request {
    public static final String g = "RequestImpl";

    /* renamed from: a, reason: collision with root package name */
    public String f5197a;
    public d3 b;
    public Headers c;
    public h1.e d;
    public NetworkConfig e;
    public Object f;

    public static final class b extends Request.Builder {
        public d3 b;
        public h1.e d;
        public Object f;

        /* renamed from: a, reason: collision with root package name */
        public String f5198a = "GET";
        public Headers.Builder c = new Headers.Builder();
        public NetworkConfig e = new NetworkConfig(null);

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b url(String str) {
            this.b = new d3(str);
            return this;
        }

        public b url(d3 d3Var) {
            this.b = d3Var;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b tag(Object obj) {
            this.f = obj;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b requestBody(RequestBody requestBody) {
            if (requestBody == null || (requestBody instanceof h1.e)) {
                this.d = (h1.e) requestBody;
            } else {
                this.d = new h1.e(requestBody);
            }
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b removeHeader(String str) {
            this.c.removeAll(str);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b options(String str) {
            if (TextUtils.isEmpty(str)) {
                Logger.w(c1.g, "Request Builder options == null");
                return this;
            }
            this.e.setOption(str);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b method(String str) {
            this.f5198a = str;
            return this;
        }

        public b headers(Headers.Builder builder) {
            this.c = builder;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public Request build() {
            return new h1.d(new c1(this));
        }

        @Override // com.huawei.hms.network.httpclient.Request.Builder
        public b addHeader(String str, String str2) {
            this.c.add(str, str2);
            return this;
        }
    }

    public String toString() {
        return super.toString();
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public b newBuilder() {
        b bVar = new b();
        bVar.f5198a = this.f5197a;
        bVar.b = this.b;
        bVar.c = this.c.newBuilder();
        bVar.d = this.d;
        bVar.e = new NetworkConfig(this.e.configObj.toString());
        bVar.f = this.f;
        return bVar;
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public String getUrl() {
        return this.b.getUrl();
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public Object getTag() {
        return this.f;
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public String getOptions() {
        return this.e.toString();
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public String getMethod() {
        return this.f5197a;
    }

    public d3 getHttpUrl() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public Map<String, List<String>> getHeaders() {
        return this.c.toMultimap();
    }

    @Override // com.huawei.hms.network.httpclient.Request
    public RequestBody getBody() {
        return this.d;
    }

    public c1(b bVar) {
        this.f5197a = bVar.f5198a;
        this.b = bVar.b;
        this.c = bVar.c.build();
        this.d = bVar.d;
        this.e = bVar.e;
        this.f = bVar.f;
    }
}
