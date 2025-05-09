package com.huawei.ui.homehealth.runcard.operation.recommendalgo.model;

/* loaded from: classes9.dex */
public class RecommendWorkout {
    private int mRestWeight;
    private int mWeight;
    private String mWorkoutId;

    public RecommendWorkout(String str, int i) {
        this.mWeight = i;
        this.mRestWeight = i;
        this.mWorkoutId = str;
    }

    public void resetWeight() {
        this.mRestWeight = this.mWeight;
    }

    public int acquireWeight() {
        return this.mWeight;
    }

    public void saveWeight(int i) {
        this.mWeight = i;
    }

    public int acquireRestWeight() {
        return this.mRestWeight;
    }

    public void saveRestWeight(int i) {
        this.mRestWeight = i;
    }

    public String acquireWorkoutId() {
        return this.mWorkoutId;
    }

    public void saveWorkoutId(String str) {
        this.mWorkoutId = str;
    }
}
