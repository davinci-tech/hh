package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class RunPlanRecord {

    @SerializedName("run_plan_record_count")
    private int runPlanRecordCount;
    private List<RunPlanRecordStruct> runPlanRecordStructList;

    public int getRunPlanRecordCount() {
        return ((Integer) jdy.d(Integer.valueOf(this.runPlanRecordCount))).intValue();
    }

    public void setRunPlanRecordCount(int i) {
        this.runPlanRecordCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<RunPlanRecordStruct> getRunPlanRecordStructList() {
        return (List) jdy.d(this.runPlanRecordStructList);
    }

    public void setRunPlanRecordStructList(List<RunPlanRecordStruct> list) {
        this.runPlanRecordStructList = (List) jdy.d(list);
    }
}
