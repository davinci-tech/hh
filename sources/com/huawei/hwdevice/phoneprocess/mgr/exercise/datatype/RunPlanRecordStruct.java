package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class RunPlanRecordStruct {

    @SerializedName("paceIndexCount")
    private int mPaceIndexCount = -1;

    @SerializedName("run_plan_index_count")
    private int mRunPlanIndexCount;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_RECORD_ID)
    private int mRunPlanRecordId;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_ID)
    private int mRunPlanWorkoutId;

    public int getRunPlanWorkoutId() {
        return ((Integer) jdy.d(Integer.valueOf(this.mRunPlanWorkoutId))).intValue();
    }

    public void setRunPlanWorkoutId(int i) {
        this.mRunPlanWorkoutId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getRunPlanRecordId() {
        return ((Integer) jdy.d(Integer.valueOf(this.mRunPlanRecordId))).intValue();
    }

    public void setRunPlanRecordId(int i) {
        this.mRunPlanRecordId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getRunPlanIndexCount() {
        return ((Integer) jdy.d(Integer.valueOf(this.mRunPlanIndexCount))).intValue();
    }

    public void setRunPlanIndexCount(int i) {
        this.mRunPlanIndexCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPaceIndexCount() {
        return ((Integer) jdy.d(Integer.valueOf(this.mPaceIndexCount))).intValue();
    }

    public void setPaceIndexCount(int i) {
        this.mPaceIndexCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
