package com.huawei.basefitnessadvice.model.intplan;

import com.huawei.basefitnessadvice.model.SuggestedReading;

/* loaded from: classes.dex */
public interface IntDayPlan {
    int getDayOrder();

    int getDayStatus();

    Goal getGoal(String str);

    IntAction getInPlanAction(int i);

    int getInPlanActionCnt();

    IntAction getOutPlanAction(int i);

    int getOutPlanActionCnt();

    int getPunchFlag();

    long getPunchTime();

    RecordData getRecordData(int i);

    int getRecordDataCnt();

    StatInfo getStat(String str);

    SuggestedReading getSuggestReading();
}
