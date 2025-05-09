package com.huawei.profile.scheduler.thread;

import com.huawei.profile.utils.SensitiveUtil;
import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public final class SdkRunnable implements Runnable {
    private static final String TAG = "SdkRunnable";
    private final String name;
    private final Runnable runnable;

    public SdkRunnable(String str, Runnable runnable) {
        this.name = str;
        this.runnable = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.runnable == null) {
            return;
        }
        String name = Thread.currentThread().getName();
        if (this.name != null) {
            Thread.currentThread().setName(this.name);
        }
        try {
            try {
                this.runnable.run();
            } catch (RuntimeException e) {
                DsLog.et(TAG, SensitiveUtil.getMessage(e), new Object[0]);
            }
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
