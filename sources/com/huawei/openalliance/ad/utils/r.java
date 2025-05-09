package com.huawei.openalliance.ad.utils;

import android.util.Log;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class r<V> implements Callable<V> {

    /* renamed from: a, reason: collision with root package name */
    private Callable<V> f7717a;

    @Override // java.util.concurrent.Callable
    public V call() {
        Callable<V> callable = this.f7717a;
        if (callable != null) {
            try {
                try {
                    return callable.call();
                } finally {
                    this.f7717a = null;
                }
            } catch (Throwable unused) {
                Log.e("SafeCallable", "Exception in CallableWrapper");
            }
        }
        return null;
    }

    public r(Callable<V> callable) {
        this.f7717a = callable;
    }
}
