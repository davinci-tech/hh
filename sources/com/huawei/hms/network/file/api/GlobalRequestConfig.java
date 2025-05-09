package com.huawei.hms.network.file.api;

import android.text.TextUtils;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.httpclient.HttpClient;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes4.dex */
public final class GlobalRequestConfig extends RequestConfig {
    private static final long serialVersionUID = 3404424470299275251L;
    private final transient HostnameVerifier hostnameVerifier;
    private final transient HttpClient httpClient;
    private boolean oldInterfaceFlag;
    private String options;
    private final List<String> quicHints;
    private final transient SSLSocketFactory sslSocketFactory;
    private final int threadPoolSize;
    private final transient X509TrustManager trustManager;

    public static final class Builder extends RequestConfig.Builder<Builder> {
        private HostnameVerifier hostnameVerifier;
        private HttpClient httpClient;
        private boolean oldInterfaceFlag;
        private String options;
        private List<String> quicHints;
        private SSLSocketFactory sslSocketFactory;
        private int threadPoolSize;
        private X509TrustManager trustManager;

        public Builder trustManager(X509TrustManager x509TrustManager) {
            this.trustManager = x509TrustManager;
            return this;
        }

        public Builder threadPoolSize(int i) {
            this.threadPoolSize = Utils.getCheckRangeResult("threadPoolSize", i, true, true);
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
            return this;
        }

        public Builder quicHints(List<String> list) {
            if (!Utils.isEmpty(list)) {
                this.quicHints.addAll(list);
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.huawei.hms.network.file.api.RequestConfig.Builder
        public Builder options(String str) {
            this.options = str;
            return this;
        }

        @Deprecated
        public Builder oldInterfaceFlag(boolean z) {
            this.oldInterfaceFlag = z;
            return this;
        }

        @Deprecated
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        @Override // com.huawei.hms.network.file.api.RequestConfig.Builder
        public GlobalRequestConfig build() {
            return new GlobalRequestConfig(this);
        }

        public Builder(GlobalRequestConfig globalRequestConfig) {
            super(globalRequestConfig);
            this.threadPoolSize = globalRequestConfig.threadPoolSize;
            this.sslSocketFactory = globalRequestConfig.sslSocketFactory;
            this.hostnameVerifier = globalRequestConfig.hostnameVerifier;
            this.trustManager = globalRequestConfig.trustManager;
            this.httpClient = globalRequestConfig.httpClient;
            this.oldInterfaceFlag = globalRequestConfig.oldInterfaceFlag;
            this.quicHints = new ArrayList();
            if (!Utils.isEmpty(globalRequestConfig.quicHints)) {
                this.quicHints.addAll(globalRequestConfig.quicHints);
            }
            this.options = globalRequestConfig.options;
        }

        public Builder() {
            this.threadPoolSize = -100;
            this.quicHints = new ArrayList();
        }
    }

    public X509TrustManager getTrustManager() {
        return this.trustManager;
    }

    public int getThreadPoolSize() {
        return this.threadPoolSize;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public List<String> getQuicHints() {
        return this.quicHints;
    }

    @Override // com.huawei.hms.network.file.api.RequestConfig
    public String getOptions() {
        return this.options;
    }

    @Deprecated
    public boolean getOldInterfaceFlag() {
        return this.oldInterfaceFlag;
    }

    @Deprecated
    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public GlobalRequestConfig genMergedRequestConfig(RequestConfig requestConfig) {
        Builder builder = new Builder(this);
        if (requestConfig.getCallTimeoutMillis() != -100) {
            builder.callTimeoutMillis(requestConfig.getCallTimeoutMillis());
        }
        if (requestConfig.getConnectTimeoutMillis() != -100) {
            builder.connectTimeoutMillis(requestConfig.getConnectTimeoutMillis());
        }
        if (requestConfig.getPingIntervalMillis() != -100) {
            builder.pingIntervalMillis(requestConfig.getPingIntervalMillis());
        }
        if (requestConfig.getReadTimeoutMillis() != -100) {
            builder.readTimeoutMillis(requestConfig.getReadTimeoutMillis());
        }
        if (requestConfig.getRetryTimes() != -100) {
            builder.retryTimes(requestConfig.getRetryTimes());
        }
        if (requestConfig.getWriteTimeoutMillis() != -100) {
            builder.writeTimeoutMillis(requestConfig.getWriteTimeoutMillis());
        }
        if (!TextUtils.isEmpty(requestConfig.getOptions())) {
            builder.options(requestConfig.getOptions());
        }
        return builder.build();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
    }

    public GlobalRequestConfig(Builder builder) {
        super(builder);
        this.threadPoolSize = builder.threadPoolSize;
        this.sslSocketFactory = builder.sslSocketFactory;
        this.hostnameVerifier = builder.hostnameVerifier;
        this.trustManager = builder.trustManager;
        this.quicHints = builder.quicHints;
        this.httpClient = builder.httpClient;
        this.oldInterfaceFlag = builder.oldInterfaceFlag;
        this.options = builder.options;
    }
}
