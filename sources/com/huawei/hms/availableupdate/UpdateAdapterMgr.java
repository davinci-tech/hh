package com.huawei.hms.availableupdate;

import android.app.Activity;
import com.huawei.hms.support.log.HMSLog;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class UpdateAdapterMgr {
    public static final UpdateAdapterMgr INST = new UpdateAdapterMgr();

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Activity> f4418a;

    private Activity a() {
        Activity activity;
        WeakReference<Activity> weakReference = this.f4418a;
        if (weakReference == null || (activity = weakReference.get()) == null || activity.isFinishing()) {
            return null;
        }
        return activity;
    }

    public boolean needStartUpdateActivity() {
        Activity a2 = a();
        if (a2 == null) {
            return true;
        }
        if (a2.isTaskRoot()) {
            return false;
        }
        a2.finish();
        HMSLog.i("UpdateAdapterMgr", " finish old activity.");
        return true;
    }

    public void onActivityCreate(Activity activity) {
        HMSLog.i("UpdateAdapterMgr", "onActivityCreate");
        Activity a2 = a();
        if (a2 != null) {
            a2.finish();
            HMSLog.i("UpdateAdapterMgr", "finish old activity.");
        }
        this.f4418a = new WeakReference<>(activity);
    }

    public void onActivityDestroy(Activity activity) {
        HMSLog.i("UpdateAdapterMgr", "onActivityDestroy");
        Activity a2 = a();
        if (activity == null || !activity.equals(a2)) {
            return;
        }
        HMSLog.i("UpdateAdapterMgr", "reset");
        this.f4418a = null;
    }
}
