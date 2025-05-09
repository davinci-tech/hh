package com.huawei.watchface;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes7.dex */
public class fe<V> extends FutureTask<V> implements Comparable<Runnable> {

    /* renamed from: a, reason: collision with root package name */
    public Runnable f11040a;

    public fe(Callable<V> callable) {
        super(callable);
    }

    public fe(Runnable runnable, V v) {
        super(runnable, v);
        this.f11040a = runnable;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(Runnable runnable) {
        Runnable runnable2 = this.f11040a;
        ff ffVar = runnable2 instanceof ff ? (ff) runnable2 : null;
        if (ffVar != null) {
            return ffVar.compareTo(runnable);
        }
        return 0;
    }
}
