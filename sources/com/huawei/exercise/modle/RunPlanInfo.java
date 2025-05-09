package com.huawei.exercise.modle;

import java.util.List;

/* loaded from: classes3.dex */
public class RunPlanInfo {
    private List<RunPlanStruct> runPlanStructList;
    private String run_plan_sign;
    private long run_plan_start_date;
    private String run_plan_total_sign;

    public String getRun_plan_total_sign() {
        return this.run_plan_total_sign;
    }

    public void setRun_plan_total_sign(String str) {
        this.run_plan_total_sign = str;
    }

    public String getRun_plan_sign() {
        return this.run_plan_sign;
    }

    public void setRun_plan_sign(String str) {
        this.run_plan_sign = str;
    }

    public long getRun_plan_start_date() {
        return this.run_plan_start_date;
    }

    public void setRun_plan_start_date(long j) {
        this.run_plan_start_date = j;
    }

    public List<RunPlanStruct> getRunPlanStructList() {
        return this.runPlanStructList;
    }

    public void setRunPlanStructList(List<RunPlanStruct> list) {
        this.runPlanStructList = list;
    }
}
