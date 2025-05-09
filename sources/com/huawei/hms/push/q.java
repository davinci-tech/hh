package com.huawei.hms.push;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class q {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f5684a = new Object();
    private static ThreadPoolExecutor b = new ThreadPoolExecutor(1, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static ThreadPoolExecutor a() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (f5684a) {
            threadPoolExecutor = b;
        }
        return threadPoolExecutor;
    }
}
