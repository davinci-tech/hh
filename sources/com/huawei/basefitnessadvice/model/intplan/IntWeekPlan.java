package com.huawei.basefitnessadvice.model.intplan;

/* loaded from: classes.dex */
public interface IntWeekPlan {
    IntDayPlan getDayByIdx(int i);

    IntDayPlan getDayByOrder(int i);

    int getDayCount();

    String getWeekDesc();

    int getWeekOrder();

    String getWeekPeriod();
}
