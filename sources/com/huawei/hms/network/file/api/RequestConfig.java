package com.huawei.hms.network.file.api;

import com.huawei.hms.network.embedded.x2;
import com.huawei.hms.network.file.core.util.Utils;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class RequestConfig implements Serializable {
    public static final int DEFAULT_NUM_VALUE = -100;
    private static final long serialVersionUID = 6967070153936588059L;
    private final long callTimeoutMillis;
    private final long connectTimeoutMillis;
    private String options;
    private final long pingIntervalMillis;
    private final long readTimeoutMillis;
    private final int retryTimes;
    private final long writeTimeoutMillis;

    public static class Builder<T> {
        private long callTimeoutMillis;
        private long connectTimeoutMillis;
        private String options;
        private long pingIntervalMillis;
        private long readTimeoutMillis;
        private int retryTimes;
        private long writeTimeoutMillis;

        /* JADX WARN: Multi-variable type inference failed */
        public T writeTimeoutMillis(long j) {
            this.writeTimeoutMillis = Utils.getCheckRangeResult(x2.WRITE_TIMEMEOUT, (int) j, false, true);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T retryTimes(int i) {
            this.retryTimes = Utils.getCheckRangeResult("retry_time", i, true, true);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T readTimeoutMillis(long j) {
            this.readTimeoutMillis = Utils.getCheckRangeResult("read_timeout", (int) j, false, false);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T pingIntervalMillis(long j) {
            this.pingIntervalMillis = Utils.getCheckRangeResult("ping_interval", (int) j, false, false);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T options(String str) {
            this.options = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T connectTimeoutMillis(long j) {
            this.connectTimeoutMillis = Utils.getCheckRangeResult(x2.CONNECT_TIMEOUT, (int) j, true, true);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public T callTimeoutMillis(long j) {
            this.callTimeoutMillis = Utils.getCheckRangeResult("call_timeout", (int) j, false, true);
            return this;
        }

        public RequestConfig build() {
            return new RequestConfig(this);
        }

        public Builder(RequestConfig requestConfig) {
            this.callTimeoutMillis = requestConfig.callTimeoutMillis;
            this.connectTimeoutMillis = requestConfig.connectTimeoutMillis;
            this.pingIntervalMillis = requestConfig.pingIntervalMillis;
            this.readTimeoutMillis = requestConfig.readTimeoutMillis;
            this.retryTimes = requestConfig.retryTimes;
            this.writeTimeoutMillis = requestConfig.writeTimeoutMillis;
            this.options = requestConfig.options;
        }

        public Builder() {
            this.callTimeoutMillis = -100L;
            this.connectTimeoutMillis = -100L;
            this.pingIntervalMillis = -100L;
            this.readTimeoutMillis = -100L;
            this.retryTimes = -100;
            this.writeTimeoutMillis = -100L;
        }
    }

    public String toString() {
        return "RequestConfig{callTimeoutMillis=" + this.callTimeoutMillis + ", connectTimeoutMillis=" + this.connectTimeoutMillis + ", pingIntervalMillis=" + this.pingIntervalMillis + ", readTimeoutMillis=" + this.readTimeoutMillis + ", retryTimes=" + this.retryTimes + ", writeTimeoutMillis=" + this.writeTimeoutMillis + '}';
    }

    public long getWriteTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    public long getReadTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public long getPingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public String getOptions() {
        return this.options;
    }

    public long getConnectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    public long getCallTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    public static class RequestConfBuilder extends Builder<RequestConfBuilder> {
        @Override // com.huawei.hms.network.file.api.RequestConfig.Builder
        public RequestConfig build() {
            return new RequestConfig(this);
        }
    }

    public RequestConfig(Builder builder) {
        this.callTimeoutMillis = builder.callTimeoutMillis;
        this.connectTimeoutMillis = builder.connectTimeoutMillis;
        this.pingIntervalMillis = builder.pingIntervalMillis;
        this.readTimeoutMillis = builder.readTimeoutMillis;
        this.retryTimes = builder.retryTimes;
        this.writeTimeoutMillis = builder.writeTimeoutMillis;
        this.options = builder.options;
    }
}
