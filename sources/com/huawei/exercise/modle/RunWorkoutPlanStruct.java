package com.huawei.exercise.modle;

import com.huawei.basefitnessadvice.model.RunWorkout;

/* loaded from: classes3.dex */
public class RunWorkoutPlanStruct {
    private long mDate;
    private RunWorkout mRunWorkout;
    private String mWorkoutName;

    public long getDate() {
        return this.mDate;
    }

    public void setDate(long j) {
        this.mDate = j;
    }

    public RunWorkout getRunWorkout() {
        return this.mRunWorkout;
    }

    public void setRunWorkout(RunWorkout runWorkout) {
        this.mRunWorkout = runWorkout;
    }

    public String getWorkoutName() {
        return this.mWorkoutName;
    }

    public void setWorkoutName(String str) {
        this.mWorkoutName = str;
    }
}
