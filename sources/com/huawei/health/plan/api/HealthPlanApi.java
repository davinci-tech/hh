package com.huawei.health.plan.api;

import com.huawei.basefitnessadvice.callback.UiCallback;
import defpackage.eps;
import defpackage.epz;
import java.util.List;

/* loaded from: classes8.dex */
public interface HealthPlanApi {
    void createPlan(eps epsVar, UiCallback<Object> uiCallback);

    List<Object> getSchedulesByCalendar(epz epzVar, boolean z);
}
