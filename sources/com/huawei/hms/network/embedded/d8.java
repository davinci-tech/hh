package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public abstract class d8 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final String f5219a;

    public abstract void b();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f5219a);
        try {
            b();
        } finally {
            Thread.currentThread().setName(name);
        }
    }

    public d8(String str, Object... objArr) {
        this.f5219a = f8.a(str, objArr);
    }
}
