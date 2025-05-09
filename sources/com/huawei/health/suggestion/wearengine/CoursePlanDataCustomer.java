package com.huawei.health.suggestion.wearengine;

import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.cbe;
import java.util.Map;

/* loaded from: classes4.dex */
public interface CoursePlanDataCustomer {
    public static final String TAG = "Suggestion_CoursePlanDataCustomer";

    int distributeBusinessType();

    int finishPlanBusinessType();

    byte[] getCourseData(FitWorkout fitWorkout, int i) throws cbe;

    byte[] getPlanBasicData(IntPlan intPlan, Map<String, FitWorkout> map) throws cbe;

    int handleShakeBusinessType();
}
