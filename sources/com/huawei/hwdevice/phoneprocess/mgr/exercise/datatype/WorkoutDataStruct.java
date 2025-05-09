package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class WorkoutDataStruct {
    private DataHeader dataHeader;

    @SerializedName(HwExerciseConstants.WORKOUT_DATA_INDEX)
    private int workoutDataIndex;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)
    private int workoutRecordId;

    public int getWorkoutRecordId() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutRecordId))).intValue();
    }

    public void setWorkoutRecordId(int i) {
        this.workoutRecordId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutDataIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutDataIndex))).intValue();
    }

    public void setWorkoutDataIndex(int i) {
        this.workoutDataIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public DataHeader getDataHeader() {
        return (DataHeader) jdy.d(this.dataHeader);
    }

    public void setDataHeader(DataHeader dataHeader) {
        this.dataHeader = (DataHeader) jdy.d(dataHeader);
    }
}
