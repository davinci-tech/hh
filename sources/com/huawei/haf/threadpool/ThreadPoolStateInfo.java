package com.huawei.haf.threadpool;

import com.huawei.haf.threadpool.ThreadPoolManager;

/* loaded from: classes.dex */
public final class ThreadPoolStateInfo {

    /* renamed from: a, reason: collision with root package name */
    private long f2162a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private long g;
    private int h;
    private long i;
    private int j;
    private long k;
    private int l;
    private int m;
    private long n;
    private long o;
    private long t;

    void a(ThreadPoolManager.HealthThreadPoolExecutor healthThreadPoolExecutor, int i) {
        this.h = healthThreadPoolExecutor.getMaximumPoolSize();
        this.e = healthThreadPoolExecutor.getCorePoolSize();
        this.f = healthThreadPoolExecutor.getPoolSize();
        this.b = healthThreadPoolExecutor.getLargestPoolSize();
        this.d = i;
        this.l = healthThreadPoolExecutor.d();
        this.m = healthThreadPoolExecutor.c();
        this.c = healthThreadPoolExecutor.e();
        this.j = healthThreadPoolExecutor.a();
    }

    void d(long j, long j2, long j3, long j4) {
        this.o = j;
        this.i = j2;
        this.g = j3;
        this.n = j4;
    }

    void e(long j, long j2, long j3) {
        this.k = j;
        this.t = j2;
        this.f2162a = j3;
    }

    public int i() {
        return this.h;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public int f() {
        return this.f;
    }

    public int d() {
        return this.d;
    }

    public int k() {
        return this.l;
    }

    public int m() {
        return this.m;
    }

    public int c() {
        return this.c;
    }

    public int g() {
        return this.j;
    }

    public long o() {
        return this.o;
    }

    public long h() {
        return this.i;
    }

    public long j() {
        return this.g;
    }

    public long l() {
        return this.n;
    }

    public long n() {
        return this.k;
    }

    public long q() {
        return this.t;
    }

    public long e() {
        return this.f2162a;
    }
}
