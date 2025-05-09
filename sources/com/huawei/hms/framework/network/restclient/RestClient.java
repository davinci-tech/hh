package com.huawei.hms.framework.network.restclient;

import android.content.Context;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.SubmitAdapter;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.url.HttpUrl;
import com.huawei.hms.framework.network.restclient.proxy.ProxyConverter;
import com.huawei.hms.framework.network.restclient.proxy.ProxySubmitAdapter;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import com.huawei.hms.network.restclient.internal.IRestClientBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Deprecated
/* loaded from: classes4.dex */
public class RestClient {
    private static final String TAG = "RestClient";
    private String baseUrl;
    private List<Converter.Factory> converterFactoryList;
    private Executor executor;
    private HttpClient httpClient;
    private com.huawei.hms.network.restclient.RestClient proxyClient;
    private List<SubmitAdapter.Factory> submitAdapterFactoryList;

    RestClient(Builder builder, com.huawei.hms.network.restclient.RestClient restClient) {
        this.executor = builder.executor;
        this.httpClient = builder.httpClient;
        this.baseUrl = builder.baseUrl;
        this.converterFactoryList = builder.converterFactoryList;
        this.submitAdapterFactoryList = builder.submitAdapterFactoryList;
        this.proxyClient = restClient;
    }

    public RestClient(com.huawei.hms.network.restclient.RestClient restClient) {
        this.proxyClient = restClient;
    }

    public <T> T create(Class<T> cls) {
        return (T) this.proxyClient.create(cls);
    }

    public Builder newBuilder() {
        return new Builder(this.proxyClient.newBuilder());
    }

    public com.huawei.hms.network.restclient.RestClient getProxyClient() {
        return this.proxyClient;
    }

    public Executor getCallbackExecutor() {
        return this.executor;
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public List<Converter.Factory> getConverterFactories() {
        return this.converterFactoryList;
    }

    public List<SubmitAdapter.Factory> getSubmitAdapterFactories() {
        return this.submitAdapterFactoryList;
    }

    @Deprecated
    public static final class Builder {
        private String baseUrl;
        private Executor executor;
        private HttpClient httpClient;
        private IRestClientBuilder proxyBuilder;
        private List<Converter.Factory> converterFactoryList = new ArrayList();
        private List<SubmitAdapter.Factory> submitAdapterFactoryList = new ArrayList();

        public Builder() {
            RestClient.Builder builder = new RestClient.Builder();
            this.proxyBuilder = builder;
            builder.addConverterFactory((Converter.Factory) new ProxyConverter.ProxyConverterFactory(defaultConvertAdapterFactory()));
        }

        Builder(IRestClientBuilder iRestClientBuilder) {
            this.proxyBuilder = iRestClientBuilder;
        }

        @Deprecated
        public Builder(Context context) {
            HttpClientGlobalInstance.getInstance().init(context);
            RestClient.Builder builder = new RestClient.Builder();
            this.proxyBuilder = builder;
            builder.addConverterFactory((Converter.Factory) new ProxyConverter.ProxyConverterFactory(defaultConvertAdapterFactory()));
        }

        public Builder baseUrl(HttpUrl httpUrl) {
            CheckParamUtils.checkNotNull(httpUrl, "baseUrl == null");
            this.baseUrl = httpUrl.getUrl();
            this.proxyBuilder.baseUrl(httpUrl.getUrl());
            return this;
        }

        public Builder baseUrl(String str) {
            this.baseUrl = str;
            this.proxyBuilder.baseUrl(str);
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
            if (httpClient == null) {
                return this;
            }
            this.httpClient = httpClient;
            this.proxyBuilder.httpClient(httpClient.getProxyHttpClient());
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            CheckParamUtils.checkNotNull(factory, "factory == null");
            this.converterFactoryList.add(factory);
            this.proxyBuilder.disableDefaultToStringConverterFactory();
            this.proxyBuilder.addConverterFactory(new ProxyConverter.ProxyConverterFactory(factory));
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            CheckParamUtils.checkNotNull(factory, "factory == null");
            this.proxyBuilder.addConverterFactory(factory);
            return this;
        }

        public Builder addSubmitAdapterFactory(SubmitAdapter.Factory factory) {
            CheckParamUtils.checkNotNull(factory, "factory == null");
            this.submitAdapterFactoryList.add(factory);
            this.proxyBuilder.addSubmitAdapterFactory(new ProxySubmitAdapter.ProxySubmitAdapterFactory(factory));
            return this;
        }

        public Builder addSubmitAdapterFactory(SubmitAdapter.Factory factory) {
            CheckParamUtils.checkNotNull(factory, "factory == null");
            this.proxyBuilder.addSubmitAdapterFactory(factory);
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            Executor executor2 = (Executor) CheckParamUtils.checkNotNull(executor, "executor == null");
            this.executor = executor2;
            this.proxyBuilder.callbackExecutor(executor2);
            return this;
        }

        public Builder validateEagerly(boolean z) {
            this.proxyBuilder.validateEagerly(z);
            return this;
        }

        public RestClient build() {
            this.proxyBuilder.addSubmitAdapterFactory(new ProxySubmitAdapter.ProxySubmitAdapterFactory(defaultSubmitAdapterFactory()));
            return new RestClient(this, this.proxyBuilder.build());
        }

        private SubmitAdapter.Factory defaultSubmitAdapterFactory() {
            Executor executor = this.executor;
            if (executor != null) {
                return new ExecutorSubmitAdapterFactory(executor);
            }
            return DefaultSubmitAdapterFactory.INSTANCE;
        }

        private Converter.Factory defaultConvertAdapterFactory() {
            return DefaultConvertAdapterFactory.INSTANCE;
        }
    }
}
