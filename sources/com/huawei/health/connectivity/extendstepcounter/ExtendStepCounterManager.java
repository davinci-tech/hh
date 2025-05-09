package com.huawei.health.connectivity.extendstepcounter;

import android.content.Context;
import com.huawei.health.connectivity.standstepcounter.StandStepCounterManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ExtendStepCounterAdapter;
import health.compact.a.MockStepCounter;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class ExtendStepCounterManager {
    private Context b;
    private StandStepCounterManager c;
    private boolean e = false;
    private ExtendStepCounter d = null;

    public ExtendStepCounterManager(Context context) {
        this.b = null;
        this.c = null;
        if (context == null) {
            LogUtil.h("Step_ExtentdStepCounterManager", "ExtendStepCounterManager context is null");
            this.b = BaseApplication.getContext();
        }
        this.b = context;
        this.c = new StandStepCounterManager(context);
    }

    public ExtendStepCounter c() {
        if (this.e) {
            if (this.c.e(1) != null) {
                this.d = new ExtendStepCounterAdapter();
            }
            return this.d;
        }
        int e = e();
        if (e == 100) {
            this.d = new MotionStepCounter(this.b);
        } else if (e == 101) {
            this.d = new MidwareStepCounter(this.b);
        } else if (this.c.e(1) != null) {
            this.d = new ExtendStepCounterAdapter();
        }
        return this.d;
    }

    public ExtendStepCounter b() {
        MockStepCounter mockStepCounter = new MockStepCounter();
        this.d = mockStepCounter;
        return mockStepCounter;
    }

    public int e() {
        if (StepCounterSupport.g(this.b)) {
            return 101;
        }
        if (StepCounterSupport.d(this.b) != 1) {
            return 0;
        }
        LogUtil.c("Step_ExtentdStepCounterManager", "getSupportType Motion");
        return 100;
    }

    public void d() {
        this.e = true;
    }
}
