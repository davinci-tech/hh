package com.huawei.health.sport.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginfitnessadvice.Classify;
import java.util.List;

/* loaded from: classes4.dex */
public class AnnualWorkoutRecord extends WorkoutRecord {

    @SerializedName("primaryClassify")
    private List<Classify> mPrimaryClassify;

    @SerializedName("secondClassify")
    private List<Classify> secondClassify;

    public void setPrimaryClassify(List<Classify> list) {
        this.mPrimaryClassify = list;
    }

    public List<Classify> getPrimaryClassify() {
        return this.mPrimaryClassify;
    }

    public List<Classify> getSecondClassify() {
        return this.secondClassify;
    }

    public void setSecondClassify(List<Classify> list) {
        this.secondClassify = list;
    }
}
