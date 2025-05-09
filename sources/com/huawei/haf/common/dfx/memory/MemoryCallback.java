package com.huawei.haf.common.dfx.memory;

/* loaded from: classes.dex */
public interface MemoryCallback {
    void check(boolean z);

    MemoryMonitorConfig getMonitorConfig();

    boolean isAutoCheck();

    int minKillDexThresholdSize();

    long minKillTimeInterval();

    void reportDexOverThresholdSize(long j);

    void reportLeak(String str, String str2, String str3, long j);
}
