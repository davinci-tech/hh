package com.huawei.hms.network.restclient;

import com.huawei.hms.network.NetworkKitProvider;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.SubmitAdapter;
import com.huawei.hms.network.restclient.internal.IRestClientBuilder;
import java.util.concurrent.Executor;
import javax.annotation.CheckReturnValue;

/* loaded from: classes.dex */
public abstract class RestClient {
    public abstract <T> T create(Class<T> cls);

    public abstract IRestClientBuilder newBuilder();

    public static final class Builder extends IRestClientBuilder {
        IRestClientBuilder delegateBuilder = NetworkKitProvider.getEnableProvider().createRestClientBuilder();

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public Builder validateEagerly(boolean z) {
            this.delegateBuilder.validateEagerly(z);
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public Builder submitFactory(Submit.Factory factory) {
            this.delegateBuilder.submitFactory(factory);
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public Builder httpClient(HttpClient httpClient) {
            this.delegateBuilder.httpClient(httpClient);
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public IRestClientBuilder disableDefaultToStringConverterFactory() {
            this.delegateBuilder.disableDefaultToStringConverterFactory();
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public Builder callbackExecutor(Executor executor) {
            this.delegateBuilder.callbackExecutor(executor);
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public RestClient build() {
            return this.delegateBuilder.build();
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public Builder baseUrl(String str) {
            this.delegateBuilder.baseUrl(str);
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public Builder addSubmitAdapterFactory(SubmitAdapter.Factory factory) {
            this.delegateBuilder.addSubmitAdapterFactory(factory);
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        @CheckReturnValue
        public Builder addConverterFactory(Converter.Factory factory) {
            this.delegateBuilder.addConverterFactory(factory);
            return this;
        }
    }
}
