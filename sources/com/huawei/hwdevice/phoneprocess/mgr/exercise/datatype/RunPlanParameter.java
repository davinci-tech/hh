package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class RunPlanParameter {

    @SerializedName("run_plan_algorithm_type")
    private int runPlanAlgorithmType;

    @SerializedName("run_plan_algorithm_version")
    private String runPlanAlgorithmVersion;

    @SerializedName("run_plan_sign")
    private String runPlanSign;

    @SerializedName("run_plan_sync_size")
    private int runPlanSyncSize;

    @SerializedName("run_plan_sync_size_pre")
    private int runPlanSyncSizePre;

    @SerializedName("run_plan_sync_size_sub")
    private int runPlanSyncSizeSub;

    @SerializedName("run_plan_total_sign")
    private String runPlanTotalSign;

    public int getRunPlanSyncSizeSub() {
        return ((Integer) jdy.d(Integer.valueOf(this.runPlanSyncSizeSub))).intValue();
    }

    public void setRunPlanSyncSizeSub(int i) {
        this.runPlanSyncSizeSub = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getRunPlanSyncSizePre() {
        return ((Integer) jdy.d(Integer.valueOf(this.runPlanSyncSizePre))).intValue();
    }

    public void setRunPlanSyncSizePre(int i) {
        this.runPlanSyncSizePre = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

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

    public int getRunPlanAlgorithmType() {
        return ((Integer) jdy.d(Integer.valueOf(this.runPlanAlgorithmType))).intValue();
    }

    public void setRunPlanAlgorithmType(int i) {
        this.runPlanAlgorithmType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getRunPlanAlgorithmVersion() {
        return (String) jdy.d(this.runPlanAlgorithmVersion);
    }

    public void setRunPlanAlgorithmVersion(String str) {
        this.runPlanAlgorithmVersion = (String) jdy.d(str);
    }

    public int getRunPlanSyncSize() {
        return ((Integer) jdy.d(Integer.valueOf(this.runPlanSyncSize))).intValue();
    }

    public void setRunPlanSyncSize(int i) {
        this.runPlanSyncSize = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
