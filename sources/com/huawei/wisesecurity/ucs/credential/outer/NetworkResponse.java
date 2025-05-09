package com.huawei.wisesecurity.ucs.credential.outer;

import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class NetworkResponse {
    private static final int HTTP_CODE_OK = 200;
    private String body;
    private int code;
    private Map<String, List<String>> headers;

    public void setHeaders(Map<String, List<String>> map) {
        this.headers = map;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public boolean isSuccessful() {
        return this.code == 200;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public int getCode() {
        return this.code;
    }

    public String getBody() {
        return this.body;
    }
}
