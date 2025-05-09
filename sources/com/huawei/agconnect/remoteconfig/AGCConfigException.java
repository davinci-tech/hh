package com.huawei.agconnect.remoteconfig;

import com.huawei.agconnect.exception.AGCException;

/* loaded from: classes2.dex */
public class AGCConfigException extends AGCException {
    public static final int DATA_NOT_MODIFIED = 204091393;
    public static final int FETCH_THROTTLED = 1;
    public static final int RCS_CONFIG_EMPTY = 204079108;
    public static final int UNKNOWN_ERROR = 204079105;
    private long throttleEndTimeMillis;

    public long getThrottleEndTimeMillis() {
        return this.throttleEndTimeMillis;
    }

    public AGCConfigException(String str, int i, long j) {
        super(str, i);
        this.throttleEndTimeMillis = j;
    }

    public AGCConfigException(String str, int i) {
        super(str, i);
        this.throttleEndTimeMillis = 0L;
    }
}
