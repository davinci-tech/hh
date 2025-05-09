package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public final class kz implements ThreadFactory {
    private static final int k;
    private static final int l;
    private static final int m;

    /* renamed from: a, reason: collision with root package name */
    private final AtomicLong f1270a;
    private final ThreadFactory b;
    private final Thread.UncaughtExceptionHandler c;
    private final String d;
    private final Integer e;
    private final Boolean f;
    private final int g;
    private final int h;
    private final BlockingQueue<Runnable> i;
    private final int j;

    /* synthetic */ kz(a aVar, byte b) {
        this(aVar);
    }

    private kz(a aVar) {
        if (aVar.f1272a == null) {
            this.b = Executors.defaultThreadFactory();
        } else {
            this.b = aVar.f1272a;
        }
        int i = aVar.f;
        this.g = i;
        int i2 = m;
        this.h = i2;
        if (i2 < i) {
            throw new NullPointerException("maxPoolSize must > corePoolSize!");
        }
        this.j = aVar.h;
        if (aVar.i == null) {
            this.i = new LinkedBlockingQueue(256);
        } else {
            this.i = aVar.i;
        }
        if (TextUtils.isEmpty(aVar.c)) {
            this.d = "amap-threadpool";
        } else {
            this.d = aVar.c;
        }
        this.e = aVar.d;
        this.f = aVar.e;
        this.c = aVar.b;
        this.f1270a = new AtomicLong();
    }

    private ThreadFactory g() {
        return this.b;
    }

    private String h() {
        return this.d;
    }

    private Boolean i() {
        return this.f;
    }

    private Integer j() {
        return this.e;
    }

    public final int a() {
        return this.g;
    }

    public final int b() {
        return this.h;
    }

    public final BlockingQueue<Runnable> c() {
        return this.i;
    }

    public final int d() {
        return this.j;
    }

    private Thread.UncaughtExceptionHandler k() {
        return this.c;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(final Runnable runnable) {
        new Runnable() { // from class: com.amap.api.col.3sl.kz.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    runnable.run();
                } catch (Throwable unused) {
                }
            }
        };
        Thread newThread = g().newThread(runnable);
        if (h() != null) {
            newThread.setName(String.format(h() + "-%d", Long.valueOf(this.f1270a.incrementAndGet())));
        }
        if (k() != null) {
            newThread.setUncaughtExceptionHandler(k());
        }
        if (j() != null) {
            newThread.setPriority(j().intValue());
        }
        if (i() != null) {
            newThread.setDaemon(i().booleanValue());
        }
        return newThread;
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        k = availableProcessors;
        l = Math.max(2, Math.min(availableProcessors - 1, 4));
        m = (availableProcessors * 2) + 1;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private ThreadFactory f1272a;
        private Thread.UncaughtExceptionHandler b;
        private String c;
        private Integer d;
        private Boolean e;
        private int f = kz.l;
        private int g = kz.m;
        private int h = 30;
        private BlockingQueue<Runnable> i;

        public final a a(String str) {
            if (str == null) {
                throw new NullPointerException("Naming pattern must not be null!");
            }
            this.c = str;
            return this;
        }

        public final a a() {
            this.f = 1;
            return this;
        }

        public final a a(int i) {
            if (this.f <= 0) {
                throw new NullPointerException("corePoolSize  must > 0!");
            }
            this.g = i;
            return this;
        }

        public final a a(BlockingQueue<Runnable> blockingQueue) {
            this.i = blockingQueue;
            return this;
        }

        private void c() {
            this.f1272a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }

        public final kz b() {
            kz kzVar = new kz(this, (byte) 0);
            c();
            return kzVar;
        }
    }
}
