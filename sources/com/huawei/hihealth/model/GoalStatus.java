package com.huawei.hihealth.model;

import java.util.List;

/* loaded from: classes.dex */
public class GoalStatus {
    private List<Goal> goalAchieve;
    private int periodEndDay;
    private int periodStartDay;

    public GoalStatus(int i, int i2, List<Goal> list) {
        this.periodStartDay = i;
        this.periodEndDay = i2;
        this.goalAchieve = list;
    }

    public int getPeriodStartDay() {
        return this.periodStartDay;
    }

    public void setPeriodStartDay(int i) {
        this.periodStartDay = i;
    }

    public int getPeriodEndDay() {
        return this.periodEndDay;
    }

    public void setPeriodEndDay(int i) {
        this.periodEndDay = i;
    }

    public List<Goal> getGoalAchieve() {
        return this.goalAchieve;
    }

    public void setGoalAchieve(List<Goal> list) {
        this.goalAchieve = list;
    }
}
