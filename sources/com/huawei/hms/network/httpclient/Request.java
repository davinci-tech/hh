package com.huawei.hms.network.httpclient;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class Request {

    /* loaded from: classes4.dex */
    public static abstract class Builder {
        public abstract Builder addHeader(String str, String str2);

        public abstract Request build();

        public abstract Builder method(String str);

        public abstract Builder options(String str);

        public abstract Builder removeHeader(String str);

        public abstract Builder requestBody(RequestBody requestBody);

        public abstract Builder tag(Object obj);

        public abstract Builder url(String str);
    }

    public abstract RequestBody getBody();

    public abstract Map<String, List<String>> getHeaders();

    public abstract String getMethod();

    public abstract String getOptions();

    public abstract Object getTag();

    public abstract String getUrl();

    public abstract Builder newBuilder();
}
