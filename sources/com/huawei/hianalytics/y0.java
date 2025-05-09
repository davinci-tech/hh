package com.huawei.hianalytics;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public class y0 implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public static y0 f3964a;

    /* renamed from: a, reason: collision with other field name */
    public static ConcurrentHashMap<String, d1> f109a;

    /* renamed from: a, reason: collision with other field name */
    public Handler f110a;

    /* renamed from: a, reason: collision with other field name */
    public final Set<Integer> f111a;

    /* renamed from: a, reason: collision with other field name */
    public final AtomicLong f112a;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        ConcurrentHashMap<String, d1> concurrentHashMap = f109a;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0 || activity == null || this.f111a.contains(Integer.valueOf(activity.hashCode()))) {
            return;
        }
        this.f111a.add(Integer.valueOf(activity.hashCode()));
        this.f112a.incrementAndGet();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        ConcurrentHashMap<String, d1> concurrentHashMap = f109a;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0 || activity == null || !this.f111a.contains(Integer.valueOf(activity.hashCode())) || this.f112a.decrementAndGet() > 0) {
            return;
        }
        this.f110a.sendEmptyMessage(1);
    }

    /* renamed from: a, reason: collision with other method in class */
    public final void m597a() {
        HandlerThread handlerThread = new HandlerThread("HA_BACKGROUND_REPORT");
        handlerThread.start();
        this.f110a = new a(handlerThread.getLooper());
    }

    public class a extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                y0.this.getClass();
                for (d1 d1Var : y0.f109a.values()) {
                    if (d1Var != null) {
                        d1Var.onReport(0);
                        d1Var.onReport(1);
                    }
                }
            }
        }

        public a(Looper looper) {
            super(looper);
        }
    }

    public static y0 a() {
        if (f3964a == null) {
            f3964a = new y0();
        }
        return f3964a;
    }

    public y0() {
        m597a();
        this.f111a = new HashSet();
        this.f112a = new AtomicLong(0L);
        f109a = new ConcurrentHashMap<>();
    }
}
