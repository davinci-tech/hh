package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class WorkoutRecordStruct {

    @SerializedName("sequence_data_dict_type")
    private String dictType;

    @SerializedName("mWorkoutBloodOxygenIndex")
    private int workoutBloodOxygenIndex;

    @SerializedName("workout_index_count")
    private int workoutIndexCount;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)
    private int workoutRecordId;

    @SerializedName("workout_section_index")
    private int workoutSectionIndex;
    private int workoutSectionNumber;

    @SerializedName("mWorkoutSliceNumber")
    private int workoutSliceNumber;

    @SerializedName("paceIndexCount")
    private int paceIndexCount = -1;

    @SerializedName("workout_trajectories")
    private int workoutTrajectories = -1;

    @SerializedName("diving_event")
    private int divingEvent = -1;

    public int getWorkoutRecordId() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutRecordId))).intValue();
    }

    public void setWorkoutRecordId(int i) {
        this.workoutRecordId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutIndexCount() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutIndexCount))).intValue();
    }

    public void setWorkoutIndexCount(int i) {
        this.workoutIndexCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPaceIndexRecord() {
        return ((Integer) jdy.d(Integer.valueOf(this.paceIndexCount))).intValue();
    }

    public void setPaceIndexCount(int i) {
        this.paceIndexCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutSectionIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutSectionIndex))).intValue();
    }

    public void setWorkoutSectionIndex(int i) {
        this.workoutSectionIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void setWorkoutSliceNumber(int i) {
        this.workoutSliceNumber = i;
    }

    public int getWorkoutSliceNumber() {
        return this.workoutSliceNumber;
    }

    public int getWorkoutBloodOxygenIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutBloodOxygenIndex))).intValue();
    }

    public void setWorkoutBloodOxygenIndex(int i) {
        this.workoutBloodOxygenIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutSectionNumber() {
        return this.workoutSectionNumber;
    }

    public void setWorkoutSectionNumber(int i) {
        this.workoutSectionNumber = i;
    }

    public int getWorkoutTrajectories() {
        return this.workoutTrajectories;
    }

    public void setWorkoutTrajectories(int i) {
        this.workoutTrajectories = i;
    }

    public int getDivingEvent() {
        return this.divingEvent;
    }

    public void setDivingEvent(int i) {
        this.divingEvent = i;
    }

    public void setDictType(String str) {
        this.dictType = str;
    }
}
