package com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr;

import com.huawei.profile.client.profile.ProfileClient;

/* loaded from: classes5.dex */
public abstract class BaseTwsTask {
    private String mWaitMessage = null;
    private long mWaitTimeout = 0;

    public abstract String getTaskName();

    public boolean isTypeGet() {
        return false;
    }

    public abstract boolean onExecutor(ProfileClient profileClient);

    public abstract boolean onWaitFor(String str, Object obj);

    public String getWaitMessage() {
        return this.mWaitMessage;
    }

    public long getWaitTimeout() {
        return this.mWaitTimeout;
    }

    public void setWaitMessage(String str, long j) {
        this.mWaitMessage = str;
        this.mWaitTimeout = j;
    }
}
