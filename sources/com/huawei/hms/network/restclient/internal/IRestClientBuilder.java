package com.huawei.hms.network.restclient.internal;

import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class IRestClientBuilder {
    public abstract IRestClientBuilder addConverterFactory(Converter.Factory factory);

    public abstract IRestClientBuilder addSubmitAdapterFactory(SubmitAdapter.Factory factory);

    public abstract IRestClientBuilder baseUrl(String str);

    public abstract RestClient build();

    public abstract IRestClientBuilder callbackExecutor(Executor executor);

    public abstract IRestClientBuilder disableDefaultToStringConverterFactory();

    public abstract IRestClientBuilder httpClient(HttpClient httpClient);

    public abstract IRestClientBuilder submitFactory(Submit.Factory factory);

    public abstract IRestClientBuilder validateEagerly(boolean z);
}
