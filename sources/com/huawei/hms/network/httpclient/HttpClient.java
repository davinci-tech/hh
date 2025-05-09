package com.huawei.hms.network.httpclient;

import com.huawei.hms.network.NetworkKitProvider;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.httpclient.internal.IHttpClientBuilder;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;
import java.net.Proxy;
import java.net.ProxySelector;
import javax.annotation.CheckReturnValue;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public abstract class HttpClient extends Submit.Factory {
    public abstract IHttpClientBuilder newBuilder();

    public abstract Request.Builder newRequest();

    @Override // com.huawei.hms.network.httpclient.Submit.Factory
    public abstract Submit<ResponseBody> newSubmit(Request request);

    public abstract WebSocket newWebSocket(Request request, WebSocketListener webSocketListener);

    public static final class Builder extends IHttpClientBuilder {
        IHttpClientBuilder delegateBuilder = NetworkKitProvider.getEnableProvider().createHttpClientBuilder();

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder writeTimeout(int i) {
            this.delegateBuilder.writeTimeout(i);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            this.delegateBuilder.sslSocketFactory(sSLSocketFactory, x509TrustManager);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder retryTimeOnConnectionFailure(int i) {
            this.delegateBuilder.retryTimeOnConnectionFailure(i);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder readTimeout(int i) {
            this.delegateBuilder.readTimeout(i);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public Builder proxySelector(ProxySelector proxySelector) {
            this.delegateBuilder.proxySelector(proxySelector);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder proxy(Proxy proxy) {
            this.delegateBuilder.proxy(proxy);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder pingInterval(int i) {
            this.delegateBuilder.pingInterval(i);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder options(String str) {
            this.delegateBuilder.options(str);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.delegateBuilder.hostnameVerifier(hostnameVerifier);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder enableQuic(boolean z) {
            this.delegateBuilder.enableQuic(z);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder connectTimeout(int i) {
            this.delegateBuilder.connectTimeout(i);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder callTimeout(int i) {
            this.delegateBuilder.callTimeout(i);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder cache(String str, long j) {
            this.delegateBuilder.cache(str, j);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public HttpClient build() {
            return this.delegateBuilder.build();
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder addNetworkInterceptor(Interceptor interceptor) {
            this.delegateBuilder.addNetworkInterceptor(interceptor);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        @CheckReturnValue
        public Builder addInterceptor(Interceptor interceptor) {
            this.delegateBuilder.addInterceptor(interceptor);
            return this;
        }
    }
}
