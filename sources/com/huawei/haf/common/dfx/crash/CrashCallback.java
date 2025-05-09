package com.huawei.haf.common.dfx.crash;

/* loaded from: classes.dex */
public interface CrashCallback {
    void handleCrash(Thread thread, Throwable th);

    boolean isAllowRethrow();

    boolean isAllowSelfKill();
}
