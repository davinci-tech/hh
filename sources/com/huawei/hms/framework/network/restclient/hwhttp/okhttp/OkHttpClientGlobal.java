package com.huawei.hms.framework.network.restclient.hwhttp.okhttp;

import com.huawei.hms.framework.network.restclient.hwhttp.HttpClientGlobalInstance;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class OkHttpClientGlobal {
    private static final int DEFAULT_CONNECTION_KEEPALIVE_DURATION = 5;
    private static final int DEFAULT_CONNECTION_SIZE = 8;
    private static final TimeUnit DEFAULT_CONNECTION_TIMEUNIT = TimeUnit.MINUTES;
    private static final int MAX_HTTP2_REQUEST_PER_HOST = 32;
    private static final int MAX_REQUEST_SIZE = 200;
    private static OkHttpClientGlobal instance;
    private long connectionKeepAliveDuration;
    private TimeUnit connectionTimeUnit;
    private int maxIdleConnections;

    private OkHttpClientGlobal() {
        this(8, 5L, DEFAULT_CONNECTION_TIMEUNIT);
    }

    private OkHttpClientGlobal(int i, long j, TimeUnit timeUnit) {
        this.maxIdleConnections = i;
        this.connectionKeepAliveDuration = j;
        this.connectionTimeUnit = timeUnit;
    }

    public static OkHttpClientGlobal getInstance() {
        OkHttpClientGlobal okHttpClientGlobal;
        synchronized (OkHttpClientGlobal.class) {
            if (instance == null) {
                instance = new OkHttpClientGlobal();
            }
            okHttpClientGlobal = instance;
        }
        return okHttpClientGlobal;
    }

    public static void init(int i, long j, TimeUnit timeUnit) {
        synchronized (OkHttpClientGlobal.class) {
            if (instance == null) {
                instance = new OkHttpClientGlobal(i, j, timeUnit);
                HttpClientGlobalInstance.getInstance().initConnectionPool(i, j, timeUnit);
            }
        }
    }

    public void evictAll() {
        synchronized (this) {
            HttpClientGlobalInstance.getInstance().evictAll();
        }
    }

    public int getMaxIdleConnections() {
        return this.maxIdleConnections;
    }

    public long getConnectionKeepAliveDuration() {
        return this.connectionKeepAliveDuration;
    }

    public TimeUnit getConnectionTimeUnit() {
        return this.connectionTimeUnit;
    }
}
