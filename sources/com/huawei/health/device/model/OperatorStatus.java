package com.huawei.health.device.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;

/* loaded from: classes3.dex */
public class OperatorStatus {

    @SerializedName("operation_time")
    private long mOperationTime;

    @SerializedName("operator_type")
    private int mOperatorType;

    @SerializedName("run_plan_date")
    private long mRunPlanDate;

    @SerializedName("sport_exist")
    private int mSportExist = 1;

    @SerializedName("sport_startTime")
    private long mSportStartTime;

    @SerializedName("sport_type")
    private int mSportType;

    @SerializedName("train_monitor_state")
    private int mTrainMonitorState;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE)
    private int mWorkoutType;

    public int getSportExist() {
        return ((Integer) jdy.d(Integer.valueOf(this.mSportExist))).intValue();
    }

    public void setSportExist(int i) {
        this.mSportExist = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSportType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mSportType))).intValue();
    }

    public void setSportType(int i) {
        this.mSportType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getSportStartTime() {
        return ((Long) jdy.d(Long.valueOf(this.mSportStartTime))).longValue();
    }

    public void setSportStartTime(long j) {
        this.mSportStartTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getWorkoutType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutType))).intValue();
    }

    public void setWorkoutType(int i) {
        this.mWorkoutType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getRunPlanDate() {
        return ((Long) jdy.d(Long.valueOf(this.mRunPlanDate))).longValue();
    }

    public void setRunPlanDate(long j) {
        this.mRunPlanDate = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getOperatorType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mOperatorType))).intValue();
    }

    public void setOperatorType(int i) {
        this.mOperatorType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void setTrainMonitorState(int i) {
        this.mTrainMonitorState = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainMonitorState() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainMonitorState))).intValue();
    }

    public void setOperationTime(long j) {
        this.mOperationTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long getOperationTime() {
        return ((Long) jdy.d(Long.valueOf(this.mOperationTime))).longValue();
    }
}
