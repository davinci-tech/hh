package com.huawei.hms.framework.network.restclient.proxy.old2new;

import com.huawei.hms.framework.network.restclient.hwhttp.Interceptor;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyRequest;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyResponse;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import java.io.IOException;

/* loaded from: classes9.dex */
public class ProxyChain implements Interceptor.Chain {
    private final HttpClient httpClient;
    private final Interceptor.Chain proxy;

    public ProxyChain(HttpClient httpClient, Interceptor.Chain chain) {
        this.proxy = chain;
        this.httpClient = httpClient;
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Interceptor.Chain
    public Request request() {
        return ProxyRequest.request2Old(this.proxy.request());
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Interceptor.Chain
    public Response proceed(Request request) throws IOException {
        return ProxyResponse.response2Old(this.proxy.proceed(ProxyRequest.request2New(this.httpClient, request)));
    }
}
