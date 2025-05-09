package com.huawei.pluginachievement.report.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes6.dex */
public class WorkoutPlayInfo {

    @SerializedName("lastPlayRecord")
    private int lastPlayRecord;

    @SerializedName("onlineDate")
    private String onlineDate;

    @SerializedName("status")
    private int status;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String workoutId;

    public String getWorkoutId() {
        return this.workoutId;
    }

    public void setWorkoutId(String str) {
        this.workoutId = str;
    }

    public String getOnlineDate() {
        return this.onlineDate;
    }

    public void setOnlineDate(String str) {
        this.onlineDate = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getLastPlayRecord() {
        return this.lastPlayRecord;
    }

    public void setLastPlayRecord(int i) {
        this.lastPlayRecord = i;
    }
}
