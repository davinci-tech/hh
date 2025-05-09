package com.huawei.haf.common.dfx.block;

/* loaded from: classes.dex */
public interface MonitorCallback {
    void begin(Thread thread, String str, long j);

    boolean check(Thread thread, long j, long j2);

    void end(Thread thread, Throwable th, long j, long j2);

    long getBeginTime();

    long monitorTimeInterval();
}
