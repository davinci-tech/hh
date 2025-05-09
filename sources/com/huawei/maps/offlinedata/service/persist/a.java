package com.huawei.maps.offlinedata.service.persist;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final ThreadPoolExecutor f6509a;

    private a() {
        this.f6509a = new ThreadPoolExecutor(1, 50, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(400), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static a a() {
        return C0169a.f6510a;
    }

    public ThreadPoolExecutor b() {
        return this.f6509a;
    }

    /* renamed from: com.huawei.maps.offlinedata.service.persist.a$a, reason: collision with other inner class name */
    static class C0169a {

        /* renamed from: a, reason: collision with root package name */
        private static final a f6510a = new a();
    }
}
