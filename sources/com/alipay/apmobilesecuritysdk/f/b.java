package com.alipay.apmobilesecuritysdk.f;

import java.util.LinkedList;

/* loaded from: classes7.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public static b f845a = new b();
    public Thread b = null;
    public LinkedList<Runnable> c = new LinkedList<>();

    public final void a(Runnable runnable) {
        synchronized (this) {
            this.c.add(runnable);
            if (this.b == null) {
                Thread thread = new Thread(new c(this));
                this.b = thread;
                thread.start();
            }
        }
    }

    public static /* synthetic */ Thread b(b bVar) {
        bVar.b = null;
        return null;
    }

    public static b a() {
        return f845a;
    }
}
