package com.huawei.picture.security.base.activity;

/* loaded from: classes9.dex */
public interface ActivityProtectHandler {
    boolean customInterceptProcessing(Thread thread, Throwable th, boolean z);

    void handleInterceptedCrash(Throwable th, boolean z);
}
