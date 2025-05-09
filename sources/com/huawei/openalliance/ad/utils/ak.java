package com.huawei.openalliance.ad.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* loaded from: classes5.dex */
public class ak {

    /* renamed from: a, reason: collision with root package name */
    private Handler f7582a;

    public void a(String str) {
        Handler handler = this.f7582a;
        if (handler == null || str == null) {
            return;
        }
        handler.removeCallbacksAndMessages(str);
    }

    public void a(Runnable runnable, String str, long j) {
        if (this.f7582a == null || runnable == null) {
            return;
        }
        if (j < 0) {
            j = 0;
        }
        df dfVar = new df(runnable);
        if (j == 0 && a()) {
            dfVar.run();
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis() + j;
        try {
            Message obtain = Message.obtain(this.f7582a, dfVar);
            obtain.setAsynchronous(true);
            obtain.obj = str;
            this.f7582a.sendMessageAtTime(obtain, uptimeMillis);
        } catch (Throwable unused) {
            this.f7582a.postAtTime(dfVar, str, uptimeMillis);
        }
    }

    public void a(Runnable runnable, String str) {
        if (this.f7582a == null || runnable == null) {
            return;
        }
        df dfVar = new df(runnable);
        if (a()) {
            dfVar.run();
            return;
        }
        try {
            Message obtain = Message.obtain(this.f7582a, dfVar);
            obtain.setAsynchronous(true);
            obtain.obj = str;
            this.f7582a.sendMessageAtFrontOfQueue(obtain);
        } catch (Throwable unused) {
            this.f7582a.postAtFrontOfQueue(dfVar);
        }
    }

    public void a(Runnable runnable) {
        a(runnable, null, 0L);
    }

    private boolean a() {
        Looper looper;
        Handler handler = this.f7582a;
        if (handler == null || (looper = handler.getLooper()) == null) {
            return false;
        }
        return Thread.currentThread() == looper.getThread();
    }

    public ak(Handler handler) {
        this.f7582a = handler;
    }
}
