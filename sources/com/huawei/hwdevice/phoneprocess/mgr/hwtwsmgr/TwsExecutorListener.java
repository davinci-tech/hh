package com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr;

/* loaded from: classes5.dex */
public interface TwsExecutorListener {
    void onAllTaskFinish();

    BaseTwsTask onGetNextTask();

    void onTaskBegin(BaseTwsTask baseTwsTask);

    void onTaskFinish(BaseTwsTask baseTwsTask);

    void onTaskTimeout(BaseTwsTask baseTwsTask);
}
