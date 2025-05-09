package com.huawei.health.manager.common;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdl;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public abstract class SingleDayBaseManager {
    private static final String TAG = "Step_SingleDayBaseManager";
    protected Context mContext;
    protected CustomClock mEventClock;
    protected boolean mIsSupportExtendStep;
    private final Object mLockBaseMgr;

    public SingleDayBaseManager(Context context) {
        this.mContext = null;
        this.mIsSupportExtendStep = false;
        Object obj = new Object();
        this.mLockBaseMgr = obj;
        synchronized (obj) {
            CustomClock customClock = new CustomClock();
            this.mEventClock = customClock;
            customClock.b(System.currentTimeMillis());
            context = context == null ? BaseApplication.getContext() : context;
            this.mContext = context;
            this.mIsSupportExtendStep = StepCounterSupport.f(context);
        }
    }

    protected void checkDataConsistency(long j, long j2) throws Exception {
        if (!jdl.d(j, j2)) {
            LogUtil.h(TAG, "checkDataConsistency not today timestampBase=", Long.valueOf(j), " timestampArg=", Long.valueOf(j2));
            throw new Exception("not today record");
        }
        if (jdl.t(j2) == j) {
            return;
        }
        LogUtil.h(TAG, "checkDataConsistency is same day,but timeZone changed!!! ", "timestampBase=", Long.valueOf(j), " timestampArg=", Long.valueOf(j2));
        throw new Exception("same day,but timeZone changed!!!");
    }

    public void reduceClass() {
        synchronized (this.mLockBaseMgr) {
            LogUtil.h(TAG, "reduceClass");
            this.mIsSupportExtendStep = false;
        }
    }

    public static class CustomClock {
        private long d = System.currentTimeMillis();

        CustomClock() {
        }

        public void b(long j) {
            this.d = j;
        }

        public long c() {
            return this.d;
        }
    }
}
