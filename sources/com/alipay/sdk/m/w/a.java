package com.alipay.sdk.m.w;

import android.content.Context;
import android.os.SystemClock;
import android.util.Pair;
import defpackage.kl;
import defpackage.lv;
import defpackage.ma;
import defpackage.md;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<Integer, Pair<Long, ?>> f877a;
    public static ExecutorService b = Executors.newFixedThreadPool(16);

    /* renamed from: com.alipay.sdk.m.w.a$a, reason: collision with other inner class name */
    public interface InterfaceC0014a<T, R> {
        R a(T t);
    }

    public static void b(int i, Object obj) {
        synchronized (a.class) {
            if (f877a == null) {
                f877a = new ConcurrentHashMap<>();
            }
            f877a.put(Integer.valueOf(i), new Pair<>(Long.valueOf(SystemClock.elapsedRealtime()), obj));
        }
    }

    public static Pair<Boolean, ?> a(int i, TimeUnit timeUnit, long j) {
        ConcurrentHashMap<Integer, Pair<Long, ?>> concurrentHashMap = f877a;
        if (concurrentHashMap == null) {
            return new Pair<>(false, null);
        }
        Pair<Long, ?> pair = concurrentHashMap.get(Integer.valueOf(i));
        if (pair == null) {
            return new Pair<>(false, null);
        }
        Long l = (Long) pair.first;
        Object obj = pair.second;
        if (l != null && SystemClock.elapsedRealtime() - l.longValue() <= TimeUnit.MILLISECONDS.convert(j, timeUnit)) {
            return new Pair<>(true, obj);
        }
        return new Pair<>(false, null);
    }

    public static Context e(Context context) {
        if (context == null) {
            return null;
        }
        return context.getApplicationContext();
    }

    public static <T> T b(int i, long j, TimeUnit timeUnit, InterfaceC0014a<Object, Boolean> interfaceC0014a, Callable<T> callable, boolean z, long j2, TimeUnit timeUnit2, lv lvVar, boolean z2) {
        T call;
        try {
            Pair<Boolean, ?> a2 = a(i, timeUnit, j);
            if (((Boolean) a2.first).booleanValue() && interfaceC0014a.a(a2.second).booleanValue()) {
                ma.d("getC", i + " got " + a2.second);
                return (T) a2.second;
            }
            if (z2 && md.g()) {
                kl.c(lvVar, "biz", "ch_get_main", "" + i);
                ma.d("getC", i + " skip");
                call = null;
            } else {
                if (z) {
                    call = b.submit(callable).get(j2, timeUnit2);
                } else {
                    call = callable.call();
                }
                b(i, call);
            }
            ma.d("getC", i + " new " + call);
            return call;
        } catch (Throwable th) {
            ma.a("CDT", "ch_get_e|" + i, th);
            kl.e(lvVar, "biz", "ch_get_e|" + i, th);
            ma.d("getC", i + " err");
            return null;
        }
    }
}
