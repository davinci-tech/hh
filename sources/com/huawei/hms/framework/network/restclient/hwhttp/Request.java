package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;
import com.huawei.hms.framework.network.restclient.hwhttp.ClientConfiguration;
import com.huawei.hms.framework.network.restclient.hwhttp.url.HttpUrl;
import java.util.Collections;
import java.util.Map;

@Deprecated
/* loaded from: classes.dex */
public class Request {
    public static final String HTTP_METHOD_GET = "GET";
    private ClientConfiguration clientConfiguration;
    private boolean clientConfigurationModified;
    private boolean concurrentConnectEnabled;
    private Headers headers;
    private String method;
    private boolean onlyConnect;
    private final Map<String, String> recordParamMap;
    private RequestBody requestBody;
    private RequestExtraInfo requestExtraInfo;
    private RequestFinishedInfo requestFinishedInfo;
    private HttpUrl url;

    public void release() {
    }

    private Request(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = builder.headers.build();
        this.requestBody = builder.requestBody;
        this.concurrentConnectEnabled = builder.concurrentConnectEnabled;
        this.onlyConnect = builder.onlyConnect;
        this.clientConfiguration = builder.clientConfigurationBuilder.build();
        this.clientConfigurationModified = builder.clientConfigurationModified;
        this.recordParamMap = builder.recordParamMap;
        this.requestExtraInfo = builder.requestExtraInfo;
    }

    public void setRequestFinishedInfo(RequestFinishedInfo requestFinishedInfo) {
        this.requestExtraInfo.setRequestFinishedInfo(requestFinishedInfo);
        this.requestFinishedInfo = requestFinishedInfo;
    }

    public String getMethod() {
        return this.method;
    }

    public HttpUrl getUrl() {
        return this.url;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public RequestBody getBody() {
        return this.requestBody;
    }

    public int getConnectTimeout() {
        return this.clientConfiguration.getConnectTimeout();
    }

    public int getReadTimeout() {
        return this.clientConfiguration.getReadTimeout();
    }

    public int getRetryTimeOnConnectionFailure() {
        return this.clientConfiguration.getRetryTimeOnConnectionFailure();
    }

    public boolean getConcurrentConnectEnabled() {
        return this.concurrentConnectEnabled;
    }

    public int getConnectionAttemptDelay() {
        return this.clientConfiguration.getConnectionAttemptDelay();
    }

    public int getCallTimeout() {
        return this.clientConfiguration.getCallTimeout();
    }

    public int getPingInterval() {
        return this.clientConfiguration.getPingInterval();
    }

    public int getWriteTimeout() {
        return this.clientConfiguration.getWriteTimeout();
    }

    public ClientConfiguration getClientConfiguration() {
        return this.clientConfiguration;
    }

    public boolean isClientConfigurationModified() {
        return this.clientConfigurationModified;
    }

    public boolean isOnlyConnect() {
        return this.onlyConnect;
    }

    public RequestExtraInfo getRequestExtraInfo() {
        return this.requestExtraInfo;
    }

    public Map<String, String> getRecordParamMap() {
        return this.recordParamMap;
    }

    public RequestFinishedInfo getRequestFinishedInfo() {
        return this.requestFinishedInfo;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.method = this.method;
        builder.url = this.url;
        builder.headers = this.headers.newBuilder();
        builder.requestBody = this.requestBody;
        builder.concurrentConnectEnabled = this.concurrentConnectEnabled;
        builder.onlyConnect = this.onlyConnect;
        builder.clientConfigurationBuilder = this.clientConfiguration.newBuilder();
        builder.clientConfigurationModified = this.clientConfigurationModified;
        builder.recordParamMap = this.recordParamMap;
        builder.requestExtraInfo = this.requestExtraInfo;
        return builder;
    }

    public String toString() {
        return super.toString();
    }

    @Deprecated
    /* loaded from: classes4.dex */
    public static final class Builder {
        private boolean onlyConnect;
        private Map<String, String> recordParamMap;
        private RequestBody requestBody;
        private HttpUrl url;
        private ClientConfiguration.Builder clientConfigurationBuilder = new ClientConfiguration.Builder();
        private boolean clientConfigurationModified = false;
        private boolean concurrentConnectEnabled = true;
        private String method = "GET";
        private Headers.Builder headers = new Headers.Builder();
        private RequestExtraInfo requestExtraInfo = new RequestExtraInfo();

        public Builder method(String str) {
            this.method = str;
            return this;
        }

        public Builder url(HttpUrl httpUrl) {
            this.url = httpUrl;
            return this;
        }

        public Builder url(String str) {
            this.url = new HttpUrl(str);
            return this;
        }

        public Builder headers(Headers.Builder builder) {
            this.headers = builder;
            return this;
        }

        public Builder requestBody(RequestBody requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder connectTimeout(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.connectTimeout(i);
            return this;
        }

        public Builder readTimeout(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.readTimeout(i);
            return this;
        }

        public Builder callTimeout(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.callTimeout(i);
            return this;
        }

        public Builder pingInterval(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.pingInterval(i);
            return this;
        }

        public Builder writeTimeout(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.writeTimeout(i);
            return this;
        }

        public Builder retryTimeOnConnectionFailure(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.retryTimeOnConnectionFailure(i);
            return this;
        }

        public Builder connectionAttemptDelay(int i) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder.connectionAttemptDelay(i);
            return this;
        }

        public Builder concurrentConnectEnabled(Boolean bool) {
            this.concurrentConnectEnabled = bool.booleanValue();
            return this;
        }

        public Builder onlyConnect(boolean z) {
            this.onlyConnect = z;
            return this;
        }

        public Builder clientConfiguration(ClientConfiguration clientConfiguration) {
            this.clientConfigurationModified = true;
            this.clientConfigurationBuilder = clientConfiguration.newBuilder();
            return this;
        }

        Builder clientConfigurationModified(boolean z) {
            this.clientConfigurationModified = z;
            return this;
        }

        public Builder recordParamMap(Map<String, String> map) {
            if (map != null) {
                this.recordParamMap = Collections.unmodifiableMap(map);
            }
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
