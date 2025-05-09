package com.huawei.hms.framework.network.restclient.proxy;

import com.huawei.hms.framework.network.restclient.Response;

/* loaded from: classes9.dex */
public class ProxyTypeResponse {
    public static <R> Response<R> response2Old(com.huawei.hms.network.httpclient.Response<R> response) {
        return new Response<>(response);
    }

    public static <T> com.huawei.hms.network.httpclient.Response<T> response2New(Response<T> response) {
        return response.getRawResponse();
    }
}
