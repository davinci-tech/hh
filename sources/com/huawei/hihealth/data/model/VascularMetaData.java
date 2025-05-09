package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class VascularMetaData {

    @SerializedName("activityRecord")
    private int mActivityRecord;

    @SerializedName("arterialElasticity")
    private int mArterialElasticity;

    @SerializedName("pulseWaveVelocity")
    private int mPulseWaveVelocity;

    @SerializedName("vascularPulse")
    private int mVascularPulse;

    public int getPulseWaveVelocity() {
        return this.mPulseWaveVelocity;
    }

    public void setPulseWaveVelocity(int i) {
        this.mPulseWaveVelocity = i;
    }

    public int getVascularPulse() {
        return this.mVascularPulse;
    }

    public void setVascularPulse(int i) {
        this.mVascularPulse = i;
    }

    public int getArterialElasticity() {
        return this.mArterialElasticity;
    }

    public void setArterialElasticity(int i) {
        this.mArterialElasticity = i;
    }

    public int getActivityRecord() {
        return this.mActivityRecord;
    }

    public void setActivityRecord(int i) {
        this.mActivityRecord = i;
    }
}
