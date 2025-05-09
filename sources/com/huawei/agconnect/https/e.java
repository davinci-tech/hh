package com.huawei.agconnect.https;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public interface e {

    /* renamed from: a, reason: collision with root package name */
    public static final int f1806a;
    public static final int b;
    public static final int c;
    public static final e d;

    Executor a();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f1806a = availableProcessors;
        b = (availableProcessors * 2) + 1;
        c = availableProcessors + 1;
        d = new e() { // from class: com.huawei.agconnect.https.e.1
            final Executor e = new ThreadPoolExecutor(c, b, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.huawei.agconnect.https.e.1.1
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "AGC-Https-Thread");
                }
            });

            @Override // com.huawei.agconnect.https.e
            public Executor a() {
                return this.e;
            }
        };
    }
}
