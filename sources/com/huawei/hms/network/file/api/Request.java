package com.huawei.hms.network.file.api;

import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.b.a;
import com.huawei.hms.network.file.core.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class Request {
    static final int MAX_BYTES = 1073741824;
    protected final List<String> backupUrls;
    public final RequestConfig config;
    protected final Converter converter;
    protected final Map<String, String> headers;
    public long id;
    public final String name;
    protected final Map<String, String> params;
    protected final Map<String, String> reportInfos;
    protected int speedLimit;
    protected final String url;

    public abstract Builder newBuilder();

    public static abstract class Builder<T extends Builder> {
        private List<String> backupUrls;
        private RequestConfig config;
        public Converter converter;
        private Map<String, String> headers;
        private long id;
        private String name;
        private Map<String, String> params;
        private Map<String, String> reportInfos;
        private int speedLimit;
        private String url;

        public abstract Request build();

        public T url(String str) {
            this.url = str;
            return this;
        }

        public T speedLimit(int i) {
            if (i <= 0 || i > 1073741824) {
                i = 0;
            }
            this.speedLimit = i;
            return this;
        }

        public T reportInfos(Map<String, String> map) {
            this.reportInfos = Request.cloneMaps(map);
            return this;
        }

        public T params(Map<String, String> map) {
            this.params = Request.cloneMaps(map);
            return this;
        }

        public T name(String str) {
            this.name = str;
            return this;
        }

        public T id(long j) {
            this.id = j;
            return this;
        }

        public T headers(Map<String, String> map) {
            this.headers = Request.cloneMaps(map);
            return this;
        }

        public T converter(Converter converter) {
            this.converter = converter;
            return this;
        }

        public T config(RequestConfig requestConfig) {
            this.config = requestConfig;
            return this;
        }

        public T backupUrls(List<String> list) {
            if (list != null) {
                this.backupUrls.addAll(list);
            }
            return this;
        }

        public Builder(Request request) {
            this();
            if (request != null) {
                this.id = request.id;
                this.url = request.url;
                ArrayList arrayList = new ArrayList();
                this.backupUrls = arrayList;
                arrayList.addAll(request.backupUrls);
                this.config = request.config;
                this.headers = Request.cloneMaps(request.headers);
                this.params = Request.cloneMaps(request.params);
                this.reportInfos = Request.cloneMaps(request.reportInfos);
                this.name = request.name;
                this.converter = request.converter;
                this.speedLimit = request.speedLimit;
            }
        }

        public Builder() {
            this.backupUrls = new ArrayList();
            this.headers = new HashMap();
            this.params = new HashMap();
            this.reportInfos = new HashMap();
            this.name = "defaultName";
            this.id = Utils.getLongId();
            this.converter = new a();
            this.config = new RequestConfig.RequestConfBuilder().build();
        }
    }

    public String toString() {
        return "Request{config=" + this.config + ", name='" + this.name + "', id=" + this.id + ", speedLimit=" + this.speedLimit + '}';
    }

    public String getUrl() {
        return this.url;
    }

    public int getSpeedLimit() {
        return this.speedLimit;
    }

    public Map<String, String> getReportInfos() {
        return Collections.unmodifiableMap(this.reportInfos);
    }

    public Map<String, String> getParams() {
        return Collections.unmodifiableMap(this.params);
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    public Converter getConverter() {
        return this.converter;
    }

    public RequestConfig getConfig() {
        return this.config;
    }

    public List<String> getBackupUrls() {
        return Collections.unmodifiableList(this.backupUrls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> cloneMaps(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        return hashMap;
    }

    public Request(Builder builder) {
        this.id = builder.id;
        this.url = builder.url;
        ArrayList arrayList = new ArrayList();
        this.backupUrls = arrayList;
        arrayList.addAll(builder.backupUrls);
        this.config = builder.config;
        this.headers = cloneMaps(builder.headers);
        this.params = cloneMaps(builder.params);
        this.reportInfos = cloneMaps(builder.reportInfos);
        this.name = builder.name;
        this.converter = builder.converter;
        this.speedLimit = builder.speedLimit;
    }
}
