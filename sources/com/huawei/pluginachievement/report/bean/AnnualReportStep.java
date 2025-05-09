package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportStep extends AnnualData {
    private int maxStep;
    private String maxStepDay;
    private int maxWalkMonth;
    private int totalStep;
    private int totalStepDistance;
    private String yearAvgSteps;

    public AnnualReportStep() {
        super(EnumAnnualType.REPORT_STEP.value());
    }

    public int acquireTotalStep() {
        return this.totalStep;
    }

    public void saveTotalStep(int i) {
        this.totalStep = i;
    }

    public void saveTotalStepDistance(int i) {
        this.totalStepDistance = i;
    }

    public void saveMaxStep(int i) {
        this.maxStep = i;
    }

    public void saveMaxStepDay(String str) {
        this.maxStepDay = str;
    }

    public void saveMaxWalkMonth(int i) {
        this.maxWalkMonth = i;
    }

    public String acquireYearAvgSteps() {
        return this.yearAvgSteps;
    }

    public void saveYearAvgSteps(String str) {
        this.yearAvgSteps = str;
    }

    public String toString() {
        return "AnnualReportStep{totalStep=" + this.totalStep + ", totalStepDistance=" + this.totalStepDistance + ", maxStep=" + this.maxStep + ", maxStepDay='" + this.maxStepDay + "', maxWalkMonth=" + this.maxWalkMonth + '}';
    }
}
