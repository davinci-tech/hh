package com.huawei.health.suggestion.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import java.io.Serializable;

/* loaded from: classes8.dex */
public class RunRecommendWorkout implements Serializable {
    private static final long serialVersionUID = -504642144826917740L;

    @SerializedName("weight")
    private int mWeight;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String mWorkoutId;

    public String getWorkoutId() {
        return this.mWorkoutId;
    }

    public void setWorkoutId(String str) {
        this.mWorkoutId = str;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public void setWeight(int i) {
        this.mWeight = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RunRecommendWorkout) {
            return this.mWorkoutId.equals(((RunRecommendWorkout) obj).getWorkoutId());
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
