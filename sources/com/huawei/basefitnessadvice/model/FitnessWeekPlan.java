package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class FitnessWeekPlan {

    @SerializedName("dayPlanList")
    private List<FitnessDayPlan> dayPlanList;

    @SerializedName("dailyPlanList")
    private List<FitnessDayPlan> mDailyPlanList;

    @SerializedName("weekDesc")
    private String weekDesc;

    @SerializedName("weekOrder")
    private int weekOrder;

    @SerializedName("weekPeriod")
    private String weekPeriod;

    public int acquireWeekOrder() {
        return this.weekOrder;
    }

    public void saveWeekOrder(int i) {
        this.weekOrder = i;
    }

    public String acquireWeekPeriod() {
        return this.weekPeriod;
    }

    public void saveWeekPeriod(String str) {
        this.weekPeriod = str;
    }

    public String acquireWeekDesc() {
        return this.weekDesc;
    }

    public void saveWeekDesc(String str) {
        this.weekDesc = str;
    }

    public List<FitnessDayPlan> acquireWeekList() {
        List<FitnessDayPlan> list = this.dayPlanList;
        return list == null ? this.mDailyPlanList : list;
    }

    public void saveWeekList(List<FitnessDayPlan> list) {
        this.dayPlanList = list;
    }

    public List<FitnessDayPlan> acquireDailyPlanList() {
        return this.mDailyPlanList;
    }

    public void saveDailyPlanList(List<FitnessDayPlan> list) {
        this.mDailyPlanList = list;
    }

    public String toString() {
        return "FitnessWeekPlan{weekDesc='" + this.weekDesc + "', weekOrder=" + this.weekOrder + ", weekPeriod='" + this.weekPeriod + "', dayPlanList=" + this.dayPlanList + "', mDailyPlanList=" + this.mDailyPlanList + '}';
    }
}
