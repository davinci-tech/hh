package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.common.Logger;

/* loaded from: classes.dex */
public class ClientConfiguration {
    public static final int CALL_TIMEOUT = 0;
    public static final int CONNECTION_ATTEMPT_DELAY_MAX = 2000;
    public static final int CONNECTION_ATTEMPT_DELAY_MIN = 100;
    public static final int CONNECT_TIMEOUT = 10000;
    public static final int INTERVAL = 500;
    public static final int MIN_CALL_TIMEOUT = 20;
    public static final int MIN_CONNECT_TIMEOUT = 1000;
    public static final int PING_TIME = 0;
    public static final int READ_TIMEOUT = 10000;
    public static final int RETRY_TIME = 1;
    private static final String TAG = "ClientConfiguration";
    public static final int WRITE_TIMEOUT = 10000;
    private int callTimeout;
    private int connectTimeout;
    private int connectionAttemptDelay;
    private int pingInterval;
    private int readTimeout;
    private int retryTimeOnConnectionFailure;
    private int writeTimeout;

    private ClientConfiguration(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.retryTimeOnConnectionFailure = builder.retryTimeOnConnectionFailure;
        this.callTimeout = builder.callTimeout;
        this.pingInterval = builder.pingInterval;
        this.writeTimeout = builder.writeTimeout;
        this.connectionAttemptDelay = builder.connectionAttemptDelay;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public int getRetryTimeOnConnectionFailure() {
        return this.retryTimeOnConnectionFailure;
    }

    public int getCallTimeout() {
        return this.callTimeout;
    }

    public int getPingInterval() {
        return this.pingInterval;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public int getConnectionAttemptDelay() {
        return this.connectionAttemptDelay;
    }

    public Builder newBuilder() {
        return new Builder().connectTimeout(this.connectTimeout).retryTimeOnConnectionFailure(this.retryTimeOnConnectionFailure).readTimeout(this.readTimeout).callTimeout(this.callTimeout).pingInterval(this.pingInterval).writeTimeout(this.writeTimeout).connectionAttemptDelay(this.connectionAttemptDelay);
    }

    public static final class Builder {
        private int connectTimeout = 10000;
        private int readTimeout = 10000;
        private int retryTimeOnConnectionFailure = 1;
        private int callTimeout = 0;
        private int pingInterval = 0;
        private int writeTimeout = 10000;
        private int connectionAttemptDelay = 500;

        public Builder connectTimeout(int i) {
            if (i < 1000) {
                Logger.w(ClientConfiguration.TAG, "restclient use composite concurrency policy to create connect, so connect timeout must larger than %d ms, no effect", 1000);
            } else if (i <= this.connectionAttemptDelay) {
                Logger.w(ClientConfiguration.TAG, "connectTimeout %d ms is less than or equal to connectionAttemptDelay %d ms, no effect", Integer.valueOf(i), Integer.valueOf(this.connectionAttemptDelay));
            } else {
                this.connectTimeout = i;
            }
            return this;
        }

        public Builder readTimeout(int i) {
            if (i <= 0) {
                Logger.w(ClientConfiguration.TAG, "readTimeout must be greater than 0, no effect");
            } else {
                this.readTimeout = i;
            }
            return this;
        }

        public Builder retryTimeOnConnectionFailure(int i) {
            if (i < 0) {
                Logger.w(ClientConfiguration.TAG, "retryTimeOnConnectionFailure must be greater than or equal to 0, no effect");
            } else {
                this.retryTimeOnConnectionFailure = i;
            }
            return this;
        }

        public Builder callTimeout(int i) {
            if (i <= 20 && i != 0) {
                Logger.w(ClientConfiguration.TAG, "callTimeout must be greater than %d, no effect", 20);
            } else {
                this.callTimeout = i;
            }
            return this;
        }

        public Builder pingInterval(int i) {
            if (i < 0) {
                Logger.w(ClientConfiguration.TAG, "pingTimeout must not less than 0, no effect");
            } else {
                this.pingInterval = i;
            }
            return this;
        }

        public Builder writeTimeout(int i) {
            if (i <= 0) {
                Logger.w(ClientConfiguration.TAG, "writeTimeout must be greater than 0, no effect");
            } else {
                this.writeTimeout = i;
            }
            return this;
        }

        public Builder connectionAttemptDelay(int i) {
            if (i < 100 || i > 2000) {
                Logger.w(ClientConfiguration.TAG, "connectionAttemptDelay %d ms is out of range ( %d ms ~ %d ms), no effect", Integer.valueOf(i), 100, 2000);
            } else if (i >= this.connectTimeout) {
                Logger.w(ClientConfiguration.TAG, "connectionAttemptDelay %d ms is greater than or equal to connectTimeout %d ms, no effect", Integer.valueOf(i), Integer.valueOf(this.connectTimeout));
            } else {
                this.connectionAttemptDelay = i;
            }
            return this;
        }

        public ClientConfiguration build() {
            return new ClientConfiguration(this);
        }

        public Builder copyBuilder() {
            return new Builder().connectTimeout(this.connectTimeout).retryTimeOnConnectionFailure(this.retryTimeOnConnectionFailure).readTimeout(this.readTimeout).callTimeout(this.callTimeout).pingInterval(this.pingInterval).writeTimeout(this.writeTimeout).connectionAttemptDelay(this.connectionAttemptDelay);
        }
    }
}
