package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.l;
import com.huawei.hianalytics.visual.t;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class q implements Application.ActivityLifecycleCallbacks {
    public static final Map<Integer, JSONObject> e = new HashMap();
    public WeakReference<Activity> b;
    public WeakReference<Activity> c;

    /* renamed from: a, reason: collision with root package name */
    public final AtomicLong f3945a = new AtomicLong(0);
    public String d = "";

    public final void a(long j, long j2, Activity activity) {
        Intent intent;
        if (j + j2 > 1) {
            return;
        }
        if (j != 0 || j2 != 1) {
            if (j == 1 && j2 == 0) {
                t tVar = t.a.f3951a;
                tVar.getClass();
                if (b.a().b()) {
                    return;
                }
                n0.a(h0.b(activity), tVar.b);
                if (!b.a().a(EventType.APP_END)) {
                    try {
                        tVar.b.put("$event_duration", n0.a(tVar.d, SystemClock.elapsedRealtime()));
                        b.a().a("$AppExit", tVar.b);
                    } catch (JSONException e2) {
                        HiLog.w("AppEnterExitManager", "handlerEndAppMessage JSONException e :" + e2.getMessage());
                    } catch (Exception e3) {
                        HiLog.w("AppEnterExitManager", "handlerEndAppMessage Exception, ex: " + e3.getMessage());
                    }
                }
                b.a().d();
                return;
            }
            return;
        }
        t tVar2 = t.a.f3951a;
        tVar2.getClass();
        try {
            if (!l.a.f3934a.a(activity)) {
                HiLog.w("AppEnterExitManager", "update jump params failed");
            }
        } catch (Exception unused) {
            HiLog.w("AppEnterExitManager", "failed to put jump params");
        }
        if (activity == null || (intent = activity.getIntent()) == null || !intent.hasExtra("$sessionid") || !intent.hasExtra("$sessiontime")) {
            k0 k0Var = k0.b;
            k0Var.getClass();
            if (!b.a().b()) {
                k0Var.f3932a = new j0(UUID.randomUUID().toString().replaceAll(Constants.LINK, ""));
            }
        }
        tVar2.c = h0.b(activity);
        tVar2.d = SystemClock.elapsedRealtime();
        try {
            tVar2.c.put("$resume_from_background", tVar2.f3950a.get());
            if (!b.a().b()) {
                if (!b.a().a(EventType.APP_START)) {
                    b.a().a("$AppStart", tVar2.c);
                }
            }
        } catch (Exception e4) {
            HiLog.w("AppEnterExitManager", "fail to report app enter event, ex: " + e4.getMessage());
        }
        tVar2.f3950a.set(true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.c = new WeakReference<>(activity);
        if (b.a().b()) {
            return;
        }
        for (w wVar : v.c.a()) {
            wVar.onActivityCreated(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (b.a().b()) {
            return;
        }
        for (w wVar : v.c.a()) {
            wVar.onActivityDestroyed(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        WeakReference<Activity> weakReference = this.b;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.d = activity.getClass().getCanonicalName();
        if (b.a().b()) {
            e.remove(Integer.valueOf(activity.hashCode()));
            return;
        }
        for (w wVar : v.c.a()) {
            wVar.onActivityPaused(activity);
        }
        e.remove(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostCreated(Activity activity, Bundle bundle) {
        if (b.a().b()) {
            return;
        }
        for (w wVar : v.c.a()) {
            wVar.onActivityPostCreated(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.b = new WeakReference<>(activity);
        this.c = new WeakReference<>(activity);
        try {
            JSONObject b = h0.b(activity);
            String canonicalName = activity.getClass().getCanonicalName();
            b.put("$current_page", canonicalName);
            b.put("$last_page", h0.f3921a);
            h0.f3921a = canonicalName;
            b.put("activity_onResume_timestamp", SystemClock.elapsedRealtime());
            e.put(Integer.valueOf(activity.hashCode()), b);
        } catch (Exception unused) {
            HiLog.w("ActivityLifecycleListener", "fail to save property when activity oResume");
        }
        if (b.a().b()) {
            return;
        }
        for (w wVar : v.c.a()) {
            wVar.onActivityResumed(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        long j;
        long j2 = this.f3945a.get();
        if (j2 < 0) {
            this.f3945a.set(0L);
            j = 0;
        } else {
            j = j2;
        }
        a(j, this.f3945a.incrementAndGet(), activity);
        if (b.a().b()) {
            return;
        }
        for (w wVar : v.c.a()) {
            wVar.onActivityStarted(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        long j;
        long j2 = this.f3945a.get();
        if (j2 == 0) {
            this.f3945a.set(1L);
            j = 1;
        } else {
            j = j2;
        }
        long decrementAndGet = this.f3945a.decrementAndGet();
        if (!b.a().b()) {
            for (w wVar : v.c.a()) {
                wVar.onActivityStopped(activity);
            }
        }
        a(j, decrementAndGet, activity);
    }
}
