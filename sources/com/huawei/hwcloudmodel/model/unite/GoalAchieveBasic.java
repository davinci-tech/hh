package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class GoalAchieveBasic {

    @SerializedName("activeGoalStateStat")
    private int activeGoalStateStat;

    @SerializedName("activeGoalValueStat")
    private int activeGoalValueStat;

    @SerializedName("activeIsRingStat")
    private int activeIsRingStat;

    @SerializedName("calorieGoalStateStat")
    private int calorieGoalStateStat;

    @SerializedName("calorieGoalValueStat")
    private int calorieGoalValueStat;

    @SerializedName("calorieIsRingStat")
    private int calorieIsRingStat;

    @SerializedName("durationGoalStateStat")
    private int durationGoalStateStat;

    @SerializedName("durationGoalValueStat")
    private int durationGoalValueStat;

    @SerializedName("durationIsRingStat")
    private int durationIsRingStat;

    @SerializedName("generateTime")
    private long generateTime;

    @SerializedName("stepGoalStateStat")
    private int stepGoalStateStat;

    @SerializedName("stepGoalValueStat")
    private int stepGoalValueStat;

    @SerializedName("stepIsRingStat")
    private int stepIsRingStat;

    public long getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(long j) {
        this.generateTime = j;
    }

    public int getStepGoalValueStat() {
        return this.stepGoalValueStat;
    }

    public void setStepGoalValueStat(int i) {
        this.stepGoalValueStat = i;
    }

    public int getStepGoalStateStat() {
        return this.stepGoalStateStat;
    }

    public void setStepGoalStateStat(int i) {
        this.stepGoalStateStat = i;
    }

    public int getStepIsRingStat() {
        return this.stepIsRingStat;
    }

    public void setStepIsRingStat(int i) {
        this.stepIsRingStat = i;
    }

    public int getCalorieGoalValueStat() {
        return this.calorieGoalValueStat;
    }

    public void setCalorieGoalValueStat(int i) {
        this.calorieGoalValueStat = i;
    }

    public int getCalorieGoalStateStat() {
        return this.calorieGoalStateStat;
    }

    public void setCalorieGoalStateStat(int i) {
        this.calorieGoalStateStat = i;
    }

    public int getCalorieIsRingStat() {
        return this.calorieIsRingStat;
    }

    public void setCalorieIsRingStat(int i) {
        this.calorieIsRingStat = i;
    }

    public int getDurationGoalValueStat() {
        return this.durationGoalValueStat;
    }

    public void setDurationGoalValueStat(int i) {
        this.durationGoalValueStat = i;
    }

    public int getDurationGoalStateStat() {
        return this.durationGoalStateStat;
    }

    public void setDurationGoalStateStat(int i) {
        this.durationGoalStateStat = i;
    }

    public int getDurationIsRingStat() {
        return this.durationIsRingStat;
    }

    public void setDurationIsRingStat(int i) {
        this.durationIsRingStat = i;
    }

    public int getActiveGoalValueStat() {
        return this.activeGoalValueStat;
    }

    public void setActiveGoalValueStat(int i) {
        this.activeGoalValueStat = i;
    }

    public int getActiveGoalStateStat() {
        return this.activeGoalStateStat;
    }

    public void setActiveGoalStateStat(int i) {
        this.activeGoalStateStat = i;
    }

    public int getActiveIsRingStat() {
        return this.activeIsRingStat;
    }

    public void setActiveIsRingStat(int i) {
        this.activeIsRingStat = i;
    }

    public String toString() {
        return "GoalAchieveBasic{generateTime=" + this.generateTime + ", stepGoalValueStat=" + this.stepGoalValueStat + ", stepGoalStateStat=" + this.stepGoalStateStat + ", stepIsRingStat=" + this.stepIsRingStat + ", calorieGoalValueStat=" + this.calorieGoalValueStat + ", calorieGoalStateStat=" + this.calorieGoalStateStat + ", calorieIsRingStat=" + this.calorieIsRingStat + ", durationGoalValueStat=" + this.durationGoalValueStat + ", durationGoalStateStat=" + this.durationGoalStateStat + ", durationIsRingStat=" + this.durationIsRingStat + ", activeGoalValueStat=" + this.activeGoalValueStat + ", activeGoalStateStat=" + this.activeGoalStateStat + ", activeIsRingStat=" + this.activeIsRingStat + '}';
    }
}
