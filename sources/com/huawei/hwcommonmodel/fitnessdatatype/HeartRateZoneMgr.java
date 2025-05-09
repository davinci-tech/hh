package com.huawei.hwcommonmodel.fitnessdatatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class HeartRateZoneMgr {
    public static final int OTHER_POSTURE = 4;
    public static final int RIDING_POSTURE = 2;
    public static final int STANDING_POSTURE = 1;
    public static final int STROKES_POSTURE = 3;

    @SerializedName("mOtherHeartRateData")
    private HeartRateThresholdConfig mOtherHeartRateData;

    @SerializedName("mRidingHeartRateData")
    private HeartRateThresholdConfig mRidingHeartRateData;

    @SerializedName("mStandingPostureHeartRateData")
    private HeartRateThresholdConfig mStandingPostureHeartRateData;

    @SerializedName("mStrokesHeartRateData")
    private HeartRateThresholdConfig mStrokesHeartRateData;

    public HeartRateZoneMgr(int i) {
        this.mStandingPostureHeartRateData = new HeartRateThresholdConfig(1, i);
        this.mRidingHeartRateData = new HeartRateThresholdConfig(2, i);
        this.mStrokesHeartRateData = new HeartRateThresholdConfig(3, i);
        this.mOtherHeartRateData = new HeartRateThresholdConfig(4, i);
    }

    public HeartRateThresholdConfig getPostureType(int i) {
        if (i == 1) {
            return this.mStandingPostureHeartRateData;
        }
        if (i == 2) {
            return this.mRidingHeartRateData;
        }
        if (i == 3) {
            return this.mStrokesHeartRateData;
        }
        if (i == 4) {
            return this.mOtherHeartRateData;
        }
        return this.mStandingPostureHeartRateData;
    }

    public void setHeartRateConfig(int i, boolean z, int i2, int i3, int i4, int i5) {
        HeartRateThresholdConfig postureType = getPostureType(i);
        if (postureType != null) {
            postureType.setWarningEnable(z);
            postureType.setWarningLimit(i2);
            postureType.setClassifyMethod(i3);
            postureType.setMaxThreshold(i4);
            postureType.setRestHeartRate(i5);
        }
    }

    public void setMaxHeartRateThreshold(int i, int i2, int i3, int i4, int i5, int i6) {
        HeartRateThresholdConfig postureType = getPostureType(i);
        if (postureType != null) {
            postureType.setAnaerobicThreshold(i2);
            postureType.setAerobicThreshold(i3);
            postureType.setFatBurnThreshold(i4);
            postureType.setWarmUpThreshold(i5);
            postureType.setFitnessThreshold(i6);
        }
    }

    public void setHrrHeartRateZoneThreshold(int i, int i2, int i3, int i4, int i5, int i6) {
        HeartRateThresholdConfig postureType = getPostureType(i);
        if (postureType != null) {
            postureType.setAnaerobicAdvanceThreshold(i2);
            postureType.setAnaerobicBaseThreshold(i3);
            postureType.setLacticAcidThreshold(i4);
            postureType.setAerobicAdvanceThreshold(i5);
            postureType.setAerobicBaseThreshold(i6);
        }
    }

    public void setHeartRateThreshold(int i, HeartRateThresholdConfig heartRateThresholdConfig) {
        if (i == 1) {
            this.mStandingPostureHeartRateData = heartRateThresholdConfig;
            return;
        }
        if (i == 2) {
            this.mRidingHeartRateData = heartRateThresholdConfig;
        } else if (i == 3) {
            this.mStrokesHeartRateData = heartRateThresholdConfig;
        } else if (i == 4) {
            this.mOtherHeartRateData = heartRateThresholdConfig;
        }
    }
}
