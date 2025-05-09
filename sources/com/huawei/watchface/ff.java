package com.huawei.watchface;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes7.dex */
public class ff implements Comparable<Runnable>, Runnable {

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicLong f11041a = new AtomicLong();
    private final long b = f11041a.getAndIncrement();
    private final Runnable c;
    private int d;

    public ff(Runnable runnable, int i) {
        this.c = runnable;
        this.d = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.run();
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(Runnable runnable) {
        ff ffVar;
        if (runnable instanceof ff) {
            ffVar = (ff) runnable;
        } else {
            if (runnable instanceof fe) {
                fe feVar = (fe) runnable;
                if (feVar.f11040a instanceof ff) {
                    ffVar = (ff) feVar.f11040a;
                }
            }
            ffVar = null;
        }
        if (ffVar != null) {
            int i = this.d;
            int i2 = ffVar.d;
            if (i != i2) {
                return i2 - i;
            }
            if (ffVar.c != this.c) {
                return this.b < ffVar.b ? -1 : 1;
            }
        }
        return 0;
    }
}
