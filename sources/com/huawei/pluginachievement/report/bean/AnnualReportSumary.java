package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportSumary extends AnnualData {
    private int CurrentLevel;
    private int MedalSum;
    private int StepOverGoal;
    private int TotalDistance;
    private long totalCalorie;

    public AnnualReportSumary() {
        super(EnumAnnualType.REPORT_SUMARY.value());
    }

    public void saveTotalCalorie(long j) {
        this.totalCalorie = j;
    }

    public void saveMedalSum(int i) {
        this.MedalSum = i;
    }

    public void saveCurrentLevel(int i) {
        this.CurrentLevel = i;
    }

    public void saveStepOverGoal(int i) {
        this.StepOverGoal = i;
    }

    public void saveTotalDistance(int i) {
        this.TotalDistance = i;
    }

    public String toString() {
        return "AnnualReportSumary{totalCalorie=" + this.totalCalorie + ", MedalSum=" + this.MedalSum + ", CurrentLevel=" + this.CurrentLevel + ", StepOverGoal=" + this.StepOverGoal + ", TotalDistance=" + this.TotalDistance + '}';
    }
}
