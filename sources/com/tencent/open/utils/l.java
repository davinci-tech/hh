package com.tencent.open.utils;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public final class l {
    private static Handler c;
    private static HandlerThread d;
    private static Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    public static final Executor f11381a = c();

    private static Executor c() {
        return new ThreadPoolExecutor(0, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static void a(Runnable runnable) {
        try {
            f11381a.execute(runnable);
        } catch (RejectedExecutionException unused) {
        }
    }

    public static Handler a() {
        if (c == null) {
            synchronized (l.class) {
                HandlerThread handlerThread = new HandlerThread("SDK_SUB");
                d = handlerThread;
                handlerThread.start();
                c = new Handler(d.getLooper());
            }
        }
        return c;
    }

    public static void b(Runnable runnable) {
        a().post(runnable);
    }

    public static Executor b() {
        return new a();
    }

    static class a implements Executor {

        /* renamed from: a, reason: collision with root package name */
        final Queue<Runnable> f11382a;
        Runnable b;

        private a() {
            this.f11382a = new LinkedList();
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable runnable) {
            synchronized (this) {
                this.f11382a.offer(new Runnable() { // from class: com.tencent.open.utils.l.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            runnable.run();
                        } finally {
                            a.this.a();
                        }
                    }
                });
                if (this.b == null) {
                    a();
                }
            }
        }

        protected void a() {
            synchronized (this) {
                Runnable poll = this.f11382a.poll();
                this.b = poll;
                if (poll != null) {
                    l.f11381a.execute(this.b);
                }
            }
        }
    }
}
