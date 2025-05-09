package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class RunPlanInfo {

    @SerializedName("run_plan_sign")
    private String runPlanSign;

    @SerializedName("run_plan_start_date")
    private long runPlanStartDate;
    private List<RunPlanStruct> runPlanStructList;

    @SerializedName("run_plan_total_sign")
    private String runPlanTotalSign;

    public String getRunPlanTotalSign() {
        return (String) jdy.d(this.runPlanTotalSign);
    }

    public void setRunPlanTotalSign(String str) {
        this.runPlanTotalSign = (String) jdy.d(str);
    }

    public String getRunPlanSign() {
        return (String) jdy.d(this.runPlanSign);
    }

    public void setRunPlanSign(String str) {
        this.runPlanSign = (String) jdy.d(str);
    }

    public long getRunPlanStartDate() {
        return ((Long) jdy.d(Long.valueOf(this.runPlanStartDate))).longValue();
    }

    public void setRunPlanStartDate(long j) {
        this.runPlanStartDate = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public List<RunPlanStruct> getRunPlanStructList() {
        return (List) jdy.d(this.runPlanStructList);
    }

    public void setRunPlanStructList(List<RunPlanStruct> list) {
        this.runPlanStructList = (List) jdy.d(list);
    }
}
