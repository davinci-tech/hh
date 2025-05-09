package com.huawei.health.plan.model.data.intelligentplan;

import com.huawei.basefitnessadvice.callback.UiCallback;
import defpackage.mnw;
import defpackage.mny;
import defpackage.mof;
import defpackage.mog;
import java.util.List;

/* loaded from: classes3.dex */
public interface IntelligentPlanDataApi {
    void getAchievementForecast(int i, int i2, int i3, int i4, UiCallback<mog> uiCallback);

    void getAllPlans(int i, UiCallback<mnw> uiCallback);

    void getCoachAdvice(String str, boolean z, UiCallback<String> uiCallback);

    void getCurrentReportIndex(UiCallback<Integer> uiCallback);

    void getTrainingReport(String str, int i, UiCallback<mny> uiCallback);

    void postFeedback(mof mofVar, UiCallback<String> uiCallback);

    void updatePlanDate(String str, List<Integer> list, List<Integer> list2, UiCallback<String> uiCallback);
}
