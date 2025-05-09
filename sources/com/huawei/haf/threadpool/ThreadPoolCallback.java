package com.huawei.haf.threadpool;

import com.huawei.haf.common.dfx.block.ThreadMonitorTask;

/* loaded from: classes.dex */
public interface ThreadPoolCallback {
    ThreadMonitorTask createBlockMonitorTask(Thread thread);

    boolean isMonitorTask();

    long minStatisticsTimeInterval();

    long statisticsTimeInterval();

    void threadPoolStateInfo(String str, ThreadPoolStateInfo threadPoolStateInfo, long j);
}
