package com.huawei.hms.framework.network.restclient.proxy.old2new;

import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyResponse;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;

/* loaded from: classes.dex */
public class ProxyInterceptor extends Interceptor {
    private HttpClient httpClient;
    private final com.huawei.hms.framework.network.restclient.hwhttp.Interceptor proxy;

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ProxyInterceptor(com.huawei.hms.framework.network.restclient.hwhttp.Interceptor interceptor) {
        this.proxy = interceptor;
    }

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        return ProxyResponse.response2New(this.proxy.intercept(new ProxyChain(this.httpClient, chain)));
    }
}
