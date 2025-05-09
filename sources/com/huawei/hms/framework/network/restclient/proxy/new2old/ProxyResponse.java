package com.huawei.hms.framework.network.restclient.proxy.new2old;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ResponseImp;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes9.dex */
public class ProxyResponse {
    private static final String TAG = "ProxyResponse";

    public static Response response2Old(com.huawei.hms.network.httpclient.Response<ResponseBody> response) {
        if (response == null) {
            return null;
        }
        Response.Builder message = new Response.Builder().code(response.getCode()).message(response.getMessage());
        if (response.getHeaders() != null) {
            message.headers(Headers.of(Headers.toArray(response.getHeaders())));
        }
        try {
            message.url(new URL(response.getUrl()));
        } catch (MalformedURLException unused) {
            Logger.w(TAG, "MalformedURLException");
        }
        ResponseBody body = response.getBody() != null ? response.getBody() : response.getErrorBody();
        if (body != null) {
            message.body(ProxyResponseBody.response2Old(new ResponseBodyImp(body)));
        }
        return message.build();
    }

    public static <T> com.huawei.hms.network.httpclient.Response<T> response2New(Response response) {
        if (response == null) {
            return null;
        }
        ResponseImp.Builder builder = new ResponseImp.Builder();
        builder.code(response.getCode()).message(response.getMessage());
        if (response.getUrl() != null) {
            builder.url(response.getUrl().toString());
        }
        if (response.getHeaders() != null) {
            builder.headers(response.getHeaders().toMultimap());
        }
        builder.body((ResponseImp.Builder) ProxyResponseBody.response2New(response.getBody()));
        return builder.build();
    }
}
