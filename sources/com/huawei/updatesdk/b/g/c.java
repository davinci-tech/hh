package com.huawei.updatesdk.b.g;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadPoolExecutor f10843a = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a("UpdateSDK-ServerTask"));
    public static final ThreadPoolExecutor b = new ThreadPoolExecutor(3, 8, 5, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a("UpdateSDK-CheckTask"));

    static class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f10844a = new AtomicInteger();
        private final String b;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.b + "#" + this.f10844a.incrementAndGet());
        }

        a(String str) {
            this.b = str;
        }
    }
}
