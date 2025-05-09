package com.huawei.health.connectivity.standstepcounter;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.StandStepCounter;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class StandStepCounterManager {
    private static StandStepCounter c;
    private Context d;

    public StandStepCounterManager(Context context) {
        this.d = null;
        if (context == null) {
            LogUtil.h("Step_StandStepCounterManager", "StandStepCounterManager context is null.");
            this.d = BaseApplication.getContext();
        }
        this.d = context;
    }

    public StandStepCounter e(int i) {
        StandStepCounter standStepCounter;
        int d;
        synchronized (StandStepCounterManager.class) {
            if (c == null && ((d = StepCounterSupport.d(this.d)) == 1 || d == 2)) {
                c = new StandStepCounter(this.d);
            }
            standStepCounter = c;
        }
        return standStepCounter;
    }

    public StandStepCounter c() {
        StandStepCounter standStepCounter;
        synchronized (StandStepCounterManager.class) {
            if (c == null) {
                c = new StandStepCounter(this.d, true);
            }
            standStepCounter = c;
        }
        return standStepCounter;
    }
}
