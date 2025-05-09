package com.huawei.hms.iapfull;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class c1 implements Executor {

    /* renamed from: a, reason: collision with root package name */
    private Handler f4695a = new Handler(Looper.getMainLooper());

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f4695a.post(runnable);
    }
}
