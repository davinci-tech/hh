package com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public abstract class SendSwitchTask implements Runnable {
    private String mTaskTag;
    private int mTaskType;

    public abstract boolean isWillRunTask();

    public SendSwitchTask(String str, int i) {
        this.mTaskTag = str;
        this.mTaskType = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SendSwitchTask)) {
            return false;
        }
        SendSwitchTask sendSwitchTask = (SendSwitchTask) obj;
        if (TextUtils.isEmpty(sendSwitchTask.mTaskTag)) {
            return false;
        }
        return sendSwitchTask.mTaskTag.equals(this.mTaskTag);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String getTaskTag() {
        return this.mTaskTag;
    }

    public int getTaskType() {
        return this.mTaskType;
    }
}
