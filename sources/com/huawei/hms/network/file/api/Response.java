package com.huawei.hms.network.file.api;

import com.huawei.hms.network.file.api.Request;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class Response<R extends Request, C, T extends Closeable> {
    int code;
    C content;
    Map<String, Object> extraInfo;
    String message;
    T rawResponse;
    private R request;
    Map<String, List<String>> responseHeader;

    public Map<String, List<String>> getResponseHeader() {
        return this.responseHeader;
    }

    public R getRequest() {
        return this.request;
    }

    public T getRawResponse() {
        return this.rawResponse;
    }

    public String getMessage() {
        return this.message;
    }

    public Map<String, Object> getExtraInfo() {
        return this.extraInfo;
    }

    public C getContent() {
        return this.content;
    }

    public int getCode() {
        return this.code;
    }

    private void initHeader() {
        T t = this.rawResponse;
        if (t == null || !(t instanceof com.huawei.hms.network.httpclient.Response)) {
            return;
        }
        Map<String, List<String>> headers = ((com.huawei.hms.network.httpclient.Response) t).getHeaders();
        this.responseHeader = new HashMap();
        if (headers == null || headers.isEmpty()) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            this.responseHeader.put(entry.getKey().toLowerCase(Locale.ROOT), entry.getValue());
        }
    }

    public Response(int i, String str, R r, T t, C c) {
        this.extraInfo = new HashMap();
        this.code = i;
        this.message = str;
        this.request = r;
        this.rawResponse = t;
        this.content = c;
        initHeader();
    }

    public Response(int i, R r, T t, C c) {
        this(i, "", r, t, c);
    }
}
