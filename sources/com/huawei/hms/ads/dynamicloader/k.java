package com.huawei.hms.ads.dynamicloader;

import com.huawei.hms.ads.uiengineloader.af;

/* loaded from: classes4.dex */
public final class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4313a = "SafeRunnable";
    private Runnable b;

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable = this.b;
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable th) {
                af.d(f4313a, "exception in task run: " + th.getClass().getSimpleName());
            }
        }
    }

    public k(Runnable runnable) {
        this.b = runnable;
    }
}
