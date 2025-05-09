package com.huawei.agconnect.apms;

import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

/* loaded from: classes8.dex */
public class cba extends Response.Builder {
    public Response.Builder abc;

    public cba(Response.Builder builder) {
        this.abc = builder;
    }

    public Response.Builder addHeader(String str, String str2) {
        return this.abc.addHeader(str, str2);
    }

    public Response.Builder body(ResponseBody responseBody) {
        return this.abc.body(responseBody);
    }

    public Response build() {
        return this.abc.build();
    }

    public Response.Builder cacheResponse(Response response) {
        return this.abc.cacheResponse(response);
    }

    public Response.Builder code(int i) {
        return this.abc.code(i);
    }

    public Response.Builder handshake(Handshake handshake) {
        return this.abc.handshake(handshake);
    }

    public Response.Builder header(String str, String str2) {
        return this.abc.header(str, str2);
    }

    public Response.Builder headers(Headers headers) {
        return this.abc.headers(headers);
    }

    public Response.Builder message(String str) {
        return this.abc.message(str);
    }

    public Response.Builder networkResponse(Response response) {
        return this.abc.networkResponse(response);
    }

    public Response.Builder priorResponse(Response response) {
        return this.abc.priorResponse(response);
    }

    public Response.Builder protocol(Protocol protocol) {
        return this.abc.protocol(protocol);
    }

    public Response.Builder removeHeader(String str) {
        return this.abc.removeHeader(str);
    }

    public Response.Builder request(Request request) {
        return this.abc.request(request);
    }
}
