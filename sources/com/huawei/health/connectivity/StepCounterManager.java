package com.huawei.health.connectivity;

import android.content.Context;
import com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter;
import com.huawei.health.connectivity.extendstepcounter.ExtendStepCounterManager;
import com.huawei.health.connectivity.standstepcounter.StandStepCounterManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.StandStepCounter;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class StepCounterManager {
    private StandStepCounterManager b;
    private ExtendStepCounterManager d;
    private Context e;
    private StandStepCounter c = null;

    /* renamed from: a, reason: collision with root package name */
    private ExtendStepCounter f2209a = null;

    public StepCounterManager(Context context) {
        this.d = null;
        this.b = null;
        this.e = context;
        this.d = new ExtendStepCounterManager(context);
        this.b = new StandStepCounterManager(this.e);
        LogUtil.a("Step_StepCounterManager", "StepCounterManager() deviceType = ", Integer.valueOf(StepCounterSupport.d(this.e)));
    }

    public StandStepCounter b() {
        return d(false);
    }

    public StandStepCounter d(boolean z) {
        if (z) {
            if (this.c == null) {
                this.c = this.b.c();
            }
        } else if (this.c == null) {
            this.c = this.b.e(0);
        }
        if (this.c == null) {
            LogUtil.h("Step_StepCounterManager", "mStandStepCounter is null.");
        }
        return this.c;
    }

    public ExtendStepCounter d() {
        LogUtil.a("Step_StepCounterManager", "initExtendStepCounter = ", Integer.valueOf(this.d.e()));
        ExtendStepCounter c = this.d.c();
        this.f2209a = c;
        return c;
    }

    public ExtendStepCounter a(boolean z) {
        if (z) {
            this.f2209a = this.d.b();
        } else {
            this.f2209a = d();
        }
        return this.f2209a;
    }

    public void e() {
        this.d.d();
        this.f2209a = null;
    }
}
