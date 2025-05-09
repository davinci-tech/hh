package com.huawei.pluginachievement.report.bean;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class AnnualReportWeight extends AnnualData {
    private double max;
    private double min;
    private double weightChange;

    public AnnualReportWeight() {
        super(EnumAnnualType.REPORT_WEIGHT.value());
    }

    public void setWeightChange(double d) {
        this.weightChange = d;
    }

    public void setMax(double d) {
        this.max = d;
    }

    public void setMin(double d) {
        this.min = d;
    }

    public String toString() {
        return "AnnualReportWeight{weightChange=" + this.weightChange + ", max=" + this.max + ", min=" + this.min + '}';
    }
}
