package com.huawei.haf.handler;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
public final class HandlerExecutor implements Executor {

    /* renamed from: a, reason: collision with root package name */
    private static final Handler f2117a = new Handler(Looper.getMainLooper());
    private final Handler b;

    public HandlerExecutor() {
        this(f2117a);
    }

    public HandlerExecutor(Handler handler) {
        this.b = handler == null ? f2117a : handler;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (this.b.post(runnable)) {
            return;
        }
        throw new RejectedExecutionException(this.b + " is shutting down");
    }

    public static Handler yF_() {
        return f2117a;
    }

    public static void e(Runnable runnable) {
        if (b()) {
            runnable.run();
        } else {
            d(runnable, 0L);
        }
    }

    public static void a(Runnable runnable) {
        d(runnable, 0L);
    }

    public static void d(Runnable runnable, long j) {
        f2117a.postDelayed(runnable, j);
    }

    public static boolean b() {
        return c();
    }

    public static boolean c() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static <T> T d(Callable<T> callable) throws Exception {
        if (b()) {
            return callable.call();
        }
        FutureTask futureTask = new FutureTask(callable);
        a(futureTask);
        return (T) futureTask.get();
    }
}
