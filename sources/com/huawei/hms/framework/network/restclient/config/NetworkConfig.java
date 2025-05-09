package com.huawei.hms.framework.network.restclient.config;

import com.huawei.hms.framework.common.Logger;

/* loaded from: classes9.dex */
public class NetworkConfig extends BaseConfig {
    private static final String TAG = "NetworkConfig";

    public NetworkConfig(String str) {
        super(str);
    }

    public boolean enableConnectEmptyBody() {
        return getBoolean("core_connect_empty_body", false);
    }

    public boolean enablePlaintextUrlPath() {
        return getBoolean("core_enable_plaintext_url_path", false);
    }

    public boolean enableConcurrentConnect() {
        return getBoolean("core_enable_concurrent_connect", true);
    }

    public int getConnectTimeout() {
        int i = getInt("core_connect_timeout", 10000);
        if (i >= 1000) {
            return i;
        }
        Logger.w("NetworkConfig", "restclient use composite concurrency policy to create connect, so connect timeout must larger than %d ms, no effect", 1000);
        return 10000;
    }

    public int getConnectionAttemptDelay() {
        int i = getInt("core_concurrent_connect_delay", 500);
        if (i >= 100 && i <= 2000) {
            return i;
        }
        Logger.w("NetworkConfig", "connectionAttemptDelay %d ms is out of range ( %d ms ~ %d ms), no effect", Integer.valueOf(i), 100, 2000);
        return 500;
    }

    public int getPingInterval() {
        int i = getInt("core_ping_interval", 0);
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public int getCallTimeout() {
        int i = getInt("core_call_timeout", 0);
        if (i <= 20) {
            return 0;
        }
        return i;
    }

    public int getRetryTimeOnConnectionFailure() {
        int i = getInt("core_retry_time", 1);
        if (i < 0) {
            return 1;
        }
        return i;
    }

    public int getReadTimeout() {
        int i = getInt("core_read_timeout", 10000);
        if (i <= 0) {
            return 10000;
        }
        return i;
    }

    public int getWriteTimeout() {
        int i = getInt("core_write_timeout", 10000);
        if (i <= 0) {
            return 10000;
        }
        return i;
    }

    public boolean disableWeakNetworkRetry() {
        return getBoolean("core_disable_weaknetwork_retry", true);
    }
}
