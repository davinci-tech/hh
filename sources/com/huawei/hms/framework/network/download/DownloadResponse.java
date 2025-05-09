package com.huawei.hms.framework.network.download;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class DownloadResponse {
    private int status = 200;
    private Map<String, List<String>> headers = new LinkedHashMap();

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, List<String>> map) {
        this.headers = map;
    }
}
