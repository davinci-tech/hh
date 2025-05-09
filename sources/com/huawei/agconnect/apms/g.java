package com.huawei.agconnect.apms;

import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes2.dex */
public class g extends Response.Builder {
    public Response.Builder abc;

    public g(Response.Builder builder) {
        this.abc = builder;
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder addHeader(String str, String str2) {
        return this.abc.addHeader(str, str2);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder body(ResponseBody responseBody) {
        return this.abc.body(responseBody);
    }

    @Override // okhttp3.Response.Builder
    public Response build() {
        return this.abc.build();
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder cacheResponse(Response response) {
        return this.abc.cacheResponse(response);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder code(int i) {
        return this.abc.code(i);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder handshake(Handshake handshake) {
        return this.abc.handshake(handshake);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder header(String str, String str2) {
        return this.abc.header(str, str2);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder headers(Headers headers) {
        return this.abc.headers(headers);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder message(String str) {
        return this.abc.message(str);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder networkResponse(Response response) {
        return this.abc.networkResponse(response);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder priorResponse(Response response) {
        return this.abc.priorResponse(response);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder protocol(Protocol protocol) {
        return this.abc.protocol(protocol);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder removeHeader(String str) {
        return this.abc.removeHeader(str);
    }

    @Override // okhttp3.Response.Builder
    public Response.Builder request(Request request) {
        return this.abc.request(request);
    }
}
