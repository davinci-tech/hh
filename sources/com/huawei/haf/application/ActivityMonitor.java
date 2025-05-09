package com.huawei.haf.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.common.os.MemoryUtils;
import com.huawei.haf.common.utils.ViewUtils;
import com.huawei.haf.handler.HandlerExecutor;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class ActivityMonitor extends ActivityStatusMonitor implements Application.ActivityLifecycleCallbacks, Callable<Activity[]> {

    /* renamed from: a, reason: collision with root package name */
    private final ActivityLeakCheck f2059a;
    private int b;
    private final ArrayList<Activity> c;
    private long e;
    private Activity g;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    ActivityMonitor(Application application) {
        super(false);
        this.c = new ArrayList<>();
        this.f2059a = new ActivityLeakCheck("HAF_ActivityMonitor");
        this.e = this.d;
        application.registerActivityLifecycleCallbacks(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.haf.intent.action.CLEAN_ALL_ACTIVITY");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(this, intentFilter);
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor
    Activity vY_() {
        return this.g;
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor
    int d() {
        return this.b;
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor
    void c() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.haf.intent.action.CLEAN_ALL_ACTIVITY");
        LocalBroadcastManager.getInstance(BaseApplication.e()).sendBroadcast(intent);
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor
    boolean a(long j) {
        return !this.c.isEmpty() || System.currentTimeMillis() - this.e <= j;
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor
    Activity[] vX_() {
        try {
            return (Activity[]) HandlerExecutor.d(this);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor
    int a(String str) {
        Activity[] vX_ = vX_();
        if (vX_ == null || vX_.length <= 0) {
            return -1;
        }
        for (int i = 0; i < vX_.length; i++) {
            if (str.equals(vX_[i].getClass().getName())) {
                return (vX_.length - 1) - i;
            }
        }
        return -1;
    }

    @Override // com.huawei.haf.application.ActivityStatusMonitor, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if ("com.huawei.haf.intent.action.CLEAN_ALL_ACTIVITY".equals(intent.getAction())) {
            LogUtil.c("HAF_ActivityMonitor", "finish all activity by app self");
            g();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.c.add(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.b++;
        if (j()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.d("HAF_ActivityMonitor", "onActivityStarted : ", Long.valueOf(currentTimeMillis));
        b(true, currentTimeMillis);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.g = activity;
        this.c.remove(activity);
        this.c.add(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i = this.b - 1;
        this.b = i;
        if (i == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.d("HAF_ActivityMonitor", "onActivityStopped : ", Long.valueOf(currentTimeMillis));
            b(false, currentTimeMillis);
            this.d = currentTimeMillis;
            this.g = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Activity activity2;
        this.c.remove(activity);
        if (this.g == activity) {
            if (this.c.isEmpty()) {
                activity2 = null;
            } else {
                activity2 = this.c.get(r0.size() - 1);
            }
            this.g = activity2;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.f2059a.vQ_(activity, currentTimeMillis, this.c.isEmpty());
        ViewUtils.xQ_(activity);
        ViewUtils.xO_(activity);
        if (this.c.isEmpty()) {
            this.e = currentTimeMillis;
            this.d = currentTimeMillis;
            MemoryUtils.b();
        }
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: vS_, reason: merged with bridge method [inline-methods] */
    public Activity[] call() throws Exception {
        return vR_();
    }

    private Activity[] vR_() {
        ArrayList<Activity> arrayList = this.c;
        return (Activity[]) arrayList.toArray(new Activity[arrayList.size()]);
    }

    private void g() {
        if (this.c.isEmpty()) {
            return;
        }
        Activity[] vR_ = vR_();
        for (int length = vR_.length - 1; length >= 0; length--) {
            vR_[length].finish();
        }
    }

    private void b(boolean z, long j) {
        c(z, j);
        this.f2059a.b(z);
    }
}
