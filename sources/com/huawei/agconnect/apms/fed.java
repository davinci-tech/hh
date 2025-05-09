package com.huawei.agconnect.apms;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.net.URL;

/* loaded from: classes8.dex */
public class fed extends Request.Builder {
    public Request.Builder abc;

    public fed(Request.Builder builder) {
        this.abc = builder;
    }

    public Request.Builder addHeader(String str, String str2) {
        return this.abc.addHeader(str, str2);
    }

    public Request build() {
        return this.abc.build();
    }

    public Request.Builder cacheControl(CacheControl cacheControl) {
        return this.abc.cacheControl(cacheControl);
    }

    public Request.Builder delete(RequestBody requestBody) {
        return this.abc.delete(requestBody);
    }

    public Request.Builder get() {
        return this.abc.get();
    }

    public Request.Builder head() {
        return this.abc.head();
    }

    public Request.Builder header(String str, String str2) {
        return this.abc.header(str, str2);
    }

    public Request.Builder headers(Headers headers) {
        return this.abc.headers(headers);
    }

    public Request.Builder method(String str, RequestBody requestBody) {
        return this.abc.method(str, requestBody);
    }

    public Request.Builder patch(RequestBody requestBody) {
        return this.abc.patch(requestBody);
    }

    public Request.Builder post(RequestBody requestBody) {
        return this.abc.post(requestBody);
    }

    public Request.Builder put(RequestBody requestBody) {
        return this.abc.put(requestBody);
    }

    public Request.Builder removeHeader(String str) {
        return this.abc.removeHeader(str);
    }

    public Request.Builder tag(Object obj) {
        return this.abc.tag(obj);
    }

    public Request.Builder url(HttpUrl httpUrl) {
        return this.abc.url(httpUrl);
    }

    public Request.Builder delete() {
        return this.abc.delete();
    }

    public Request.Builder url(String str) {
        return this.abc.url(str);
    }

    public Request.Builder url(URL url) {
        return this.abc.url(url);
    }
}
