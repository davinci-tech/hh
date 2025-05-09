package com.huawei.devicesdk.strategy;

import defpackage.bmh;
import defpackage.bmw;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public abstract class CommandTimerTask extends TimerTask {
    private static final String TAG = "DEVMGR_CommandTimerTask";
    private String mActionName;
    private int mMaxReTryTime;
    private AtomicBoolean mHasGetAck = new AtomicBoolean(false);
    private int mCurrentResendCount = 0;

    public abstract void doTaskAction();

    public abstract void doTimeoutAction();

    public CommandTimerTask(String str, int i) {
        this.mActionName = str;
        this.mMaxReTryTime = i;
    }

    public String getActionName() {
        return this.mActionName;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (this.mHasGetAck.get()) {
            LogUtil.c(TAG, "Has get ack and return. action:", this.mActionName);
            return;
        }
        int i = this.mCurrentResendCount + 1;
        this.mCurrentResendCount = i;
        if (i >= this.mMaxReTryTime) {
            bmw.e(100042, bmh.b(this.mActionName), null, String.valueOf(this.mMaxReTryTime));
            ReleaseLogUtil.b(TAG, "shake failed timeout. action: ", this.mActionName);
            doTimeoutAction();
            cancel();
            return;
        }
        doTaskAction();
    }

    @Override // java.util.TimerTask
    public boolean cancel() {
        this.mHasGetAck.set(true);
        return super.cancel();
    }
}
