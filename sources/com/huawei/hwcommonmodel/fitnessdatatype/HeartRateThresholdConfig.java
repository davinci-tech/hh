package com.huawei.hwcommonmodel.fitnessdatatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class HeartRateThresholdConfig {
    private static final int DEFAULT_REST_HEART_RATE = 60;
    public static final int HEART_RATE_LIMIT = 220;
    private static final int OTHER_POSTURE_CALIBRATION = 5;
    private static final int RIDING_POSTURE_CALIBRATION = 6;
    private static final int STANDING_POSTURE_CALIBRATION = 0;
    private static final int STROKES_POSTURE_CALIBRATION = 10;
    private static final String TAG = "HeartRateThresholdConfig";

    @SerializedName("mAerobicAdvanceThreshold")
    private int mAerobicAdvanceThreshold;

    @SerializedName("mAerobicBaseThreshold")
    private int mAerobicBaseThreshold;

    @SerializedName("mAerobicThreshold")
    private int mAerobicThreshold;

    @SerializedName("mAnaerobicAdvanceThreshold")
    private int mAnaerobicAdvanceThreshold;

    @SerializedName("mAnaerobicBaseThreshold")
    private int mAnaerobicBaseThreshold;

    @SerializedName("mAnaerobicThreshold")
    private int mAnaerobicThreshold;

    @SerializedName("mClassifyMethod")
    private int mClassifyMethod;

    @SerializedName("mFatBurnThreshold")
    private int mFatBurnThreshold;

    @SerializedName("mFitnessThreshold")
    private int mFitnessThreshold;

    @SerializedName("mLactateThresholdHeartRate")
    private int mLactateThresholdHeartRate;

    @SerializedName("mLacticAcidThreshold")
    private int mLacticAcidThreshold;

    @SerializedName("mLthrAerobicHighZone")
    private int mLthrAerobicHighZone;

    @SerializedName("mLthrAerobicLowZone")
    private int mLthrAerobicLowZone;

    @SerializedName("mLthrAnaerobicInterval")
    private int mLthrAnaerobicInterval;

    @SerializedName("mLthrLactateThreshold")
    private int mLthrLactateThreshold;

    @SerializedName("mLthrRecoveryInterval")
    private int mLthrRecoveryInterval;

    @SerializedName("mMaxThreshold")
    private int mMaxThreshold;

    @SerializedName("mOldMaxThreshold")
    private int mOldMaxThreshold;

    @SerializedName("mPostureType")
    private int mPostureType;

    @SerializedName("mWarmUpThreshold")
    private int mWarmUpThreshold;

    @SerializedName("mWarningLimit")
    private int mWarningLimit;
    private int mRestHeartRateDefault = 60;

    @SerializedName("mRestHeartRate")
    private int mRestHeartRate = 60;

    @SerializedName("mIsWarningEnable")
    private boolean mIsWarningEnable = true;

    @SerializedName("mHeartZoneStateConfig")
    private HeartZoneStateConfig mHeartZoneStateConfig = new HeartZoneStateConfig();

    public HeartRateThresholdConfig(int i, int i2) {
        this.mPostureType = i;
        resetHeartZoneConf(i2);
        this.mWarningLimit = 220 - i2;
        this.mOldMaxThreshold = this.mMaxThreshold;
    }

    public void resetHeartZoneConf(int i) {
        this.mMaxThreshold = (220 - i) - getPostureCalibrationValue();
        resetHrrHeartRateZoneThreshold();
        resetHeartRateZoneThreshold();
        if (this.mPostureType == 1) {
            resetLthrHeartRateZoneThreshold();
        }
        this.mOldMaxThreshold = this.mMaxThreshold;
    }

    private void resetLthrHeartRateZoneThreshold() {
        this.mLactateThresholdHeartRate = Math.round(this.mRestHeartRate + (((this.mMaxThreshold - r0) * 85) / 100.0f));
        this.mLthrAnaerobicInterval = Math.round((r0 * 102) / 100.0f);
        this.mLthrLactateThreshold = Math.round((this.mLactateThresholdHeartRate * 97) / 100.0f);
        this.mLthrAerobicHighZone = Math.round((this.mLactateThresholdHeartRate * 89) / 100.0f);
        this.mLthrAerobicLowZone = Math.round((this.mLactateThresholdHeartRate * 80) / 100.0f);
        this.mLthrRecoveryInterval = Math.round((this.mLactateThresholdHeartRate * 67) / 100.0f);
        LogUtil.a(TAG, " resetLthrHeartRateZoneThreshold  mMaxThreshold = ", Integer.valueOf(this.mMaxThreshold));
    }

    private void resetHeartRateZoneThreshold() {
        this.mFitnessThreshold = Math.round((this.mMaxThreshold * 50) / 100.0f);
        this.mWarmUpThreshold = Math.round((this.mMaxThreshold * 60) / 100.0f);
        this.mFatBurnThreshold = Math.round((this.mMaxThreshold * 70) / 100.0f);
        this.mAerobicThreshold = Math.round((this.mMaxThreshold * 80) / 100.0f);
        this.mAnaerobicThreshold = Math.round((this.mMaxThreshold * 90) / 100.0f);
        LogUtil.a(TAG, " resetHeartRateZoneThreshold  mMaxThreshold =", Integer.valueOf(this.mMaxThreshold));
        this.mHeartZoneStateConfig.setIsSetMaxHeart(false);
    }

    private void resetHrrHeartRateZoneThreshold() {
        int i = this.mMaxThreshold - this.mRestHeartRate;
        this.mAnaerobicAdvanceThreshold = Math.round((i * 95) / 100.0f) + this.mRestHeartRate;
        this.mAnaerobicBaseThreshold = Math.round((i * 88) / 100.0f) + this.mRestHeartRate;
        this.mLacticAcidThreshold = Math.round((i * 84) / 100.0f) + this.mRestHeartRate;
        this.mAerobicAdvanceThreshold = Math.round((i * 74) / 100.0f) + this.mRestHeartRate;
        this.mAerobicBaseThreshold = Math.round((i * 59) / 100.0f) + this.mRestHeartRate;
        LogUtil.a(TAG, " resetHrrHeartRateZoneThreshold  mMaxHeartRateThreshold =", Integer.valueOf(this.mMaxThreshold));
    }

    private int getPostureCalibrationValue() {
        int i = this.mPostureType;
        if (i != 1) {
            if (i == 2) {
                return 6;
            }
            if (i == 3) {
                return 10;
            }
            if (i == 4) {
                return 5;
            }
        }
        return 0;
    }

    public void setRestHeartRateDefault(int i) {
        this.mRestHeartRateDefault = i;
        this.mRestHeartRate = i;
    }

    public int getRestHeartRateDefault() {
        return this.mRestHeartRateDefault;
    }

    public int getClassifyMethod() {
        return this.mClassifyMethod;
    }

    public void setClassifyMethod(int i) {
        this.mClassifyMethod = i;
    }

    public boolean getWarningEnable() {
        return this.mIsWarningEnable;
    }

    public void setWarningEnable(boolean z) {
        this.mIsWarningEnable = z;
    }

    public int getWarningLimit() {
        return this.mWarningLimit;
    }

    public void setWarningLimit(int i) {
        this.mWarningLimit = i;
    }

    public int getMaxThreshold() {
        return this.mMaxThreshold;
    }

    public void setMaxThreshold(int i) {
        this.mMaxThreshold = i;
    }

    public int getRestHeartRate() {
        return this.mRestHeartRate;
    }

    public void setRestHeartRate(int i) {
        this.mRestHeartRate = i;
    }

    public int getFitnessThreshold() {
        return this.mFitnessThreshold;
    }

    public void setFitnessThreshold(int i) {
        this.mFitnessThreshold = i;
    }

    public int getWarmUpThreshold() {
        return this.mWarmUpThreshold;
    }

    public void setWarmUpThreshold(int i) {
        this.mWarmUpThreshold = i;
    }

    public int getFatBurnThreshold() {
        return this.mFatBurnThreshold;
    }

    public void setFatBurnThreshold(int i) {
        this.mFatBurnThreshold = i;
    }

    public int getAerobicThreshold() {
        return this.mAerobicThreshold;
    }

    public void setAerobicThreshold(int i) {
        this.mAerobicThreshold = i;
    }

    public int getAnaerobicThreshold() {
        return this.mAnaerobicThreshold;
    }

    public void setAnaerobicThreshold(int i) {
        this.mAnaerobicThreshold = i;
    }

    public int getAerobicBaseThreshold() {
        return this.mAerobicBaseThreshold;
    }

    public void setAerobicBaseThreshold(int i) {
        this.mAerobicBaseThreshold = i;
    }

    public int getAerobicAdvanceThreshold() {
        return this.mAerobicAdvanceThreshold;
    }

    public void setAerobicAdvanceThreshold(int i) {
        this.mAerobicAdvanceThreshold = i;
    }

    public int getLacticAcidThreshold() {
        return this.mLacticAcidThreshold;
    }

    public void setLacticAcidThreshold(int i) {
        this.mLacticAcidThreshold = i;
    }

    public int getAnaerobicBaseThreshold() {
        return this.mAnaerobicBaseThreshold;
    }

    public void setAnaerobicBaseThreshold(int i) {
        this.mAnaerobicBaseThreshold = i;
    }

    public int getAnaerobicAdvanceThreshold() {
        return this.mAnaerobicAdvanceThreshold;
    }

    public void setAnaerobicAdvanceThreshold(int i) {
        this.mAnaerobicAdvanceThreshold = i;
    }

    public int getLactateThresholdHeartRate() {
        return this.mLactateThresholdHeartRate;
    }

    public void setLactateThresholdHeartRate(int i) {
        this.mLactateThresholdHeartRate = i;
    }

    public int getLthrAnaerobicInterval() {
        return this.mLthrAnaerobicInterval;
    }

    public void setLthrAnaerobicInterval(int i) {
        this.mLthrAnaerobicInterval = i;
    }

    public int getLthrLactateThreshold() {
        return this.mLthrLactateThreshold;
    }

    public void setLthrLactateThreshold(int i) {
        this.mLthrLactateThreshold = i;
    }

    public int getLthrAerobicHighZone() {
        return this.mLthrAerobicHighZone;
    }

    public void setLthrAerobicHighZone(int i) {
        this.mLthrAerobicHighZone = i;
    }

    public int getLthrAerobicLowZone() {
        return this.mLthrAerobicLowZone;
    }

    public void setLthrAerobicLowZone(int i) {
        this.mLthrAerobicLowZone = i;
    }

    public int getLthrRecoveryInterval() {
        return this.mLthrRecoveryInterval;
    }

    public void setLthrRecoveryInterval(int i) {
        this.mLthrRecoveryInterval = i;
    }

    public HeartZoneStateConfig getHeartZoneStateConfig() {
        return this.mHeartZoneStateConfig;
    }

    public int getOldMaxThreshold() {
        return this.mOldMaxThreshold;
    }

    public void setOldMaxThreshold(int i) {
        this.mOldMaxThreshold = i;
    }

    public static class HeartZoneStateConfig {

        @SerializedName("mIsSetWarningLimit")
        private boolean mIsSetWarningLimit = false;

        @SerializedName("mIsSetMaxHeart")
        private boolean mIsSetMaxHeart = false;

        public boolean getIsSetWarningLimit() {
            return this.mIsSetWarningLimit;
        }

        public void setIsSetWarningLimit(boolean z) {
            this.mIsSetWarningLimit = z;
        }

        public boolean getIsSetMaxHeart() {
            return this.mIsSetMaxHeart;
        }

        public void setIsSetMaxHeart(boolean z) {
            this.mIsSetMaxHeart = z;
        }
    }

    public int[] getHeartData() {
        return new int[]{this.mOldMaxThreshold, this.mPostureType, this.mClassifyMethod, this.mIsWarningEnable, this.mWarningLimit, this.mMaxThreshold, this.mRestHeartRate, this.mRestHeartRateDefault, this.mFitnessThreshold, this.mWarmUpThreshold, this.mFatBurnThreshold, this.mAerobicThreshold, this.mAnaerobicThreshold, this.mAerobicBaseThreshold, this.mAerobicAdvanceThreshold, this.mLacticAcidThreshold, this.mAnaerobicBaseThreshold, this.mAnaerobicAdvanceThreshold, this.mLactateThresholdHeartRate, this.mLthrAnaerobicInterval, this.mLthrLactateThreshold, this.mLthrAerobicHighZone, this.mLthrAerobicLowZone, this.mLthrRecoveryInterval, this.mHeartZoneStateConfig.getIsSetMaxHeart(), this.mHeartZoneStateConfig.getIsSetWarningLimit()};
    }

    public String toString() {
        if (this.mPostureType == 1) {
            StringBuffer stringBuffer = new StringBuffer("HeartRateThresholdConfig{mOldMaxThreshold=");
            stringBuffer.append(this.mOldMaxThreshold);
            stringBuffer.append(", mPostureType=").append(this.mPostureType);
            stringBuffer.append(", mClassifyMethod=").append(this.mClassifyMethod);
            stringBuffer.append(", mIsWarningEnable=").append(this.mIsWarningEnable);
            stringBuffer.append(", mWarningLimit=").append(this.mWarningLimit);
            stringBuffer.append(", mMaxThreshold=").append(this.mMaxThreshold);
            stringBuffer.append(", mRestHeartRate=").append(this.mRestHeartRate);
            stringBuffer.append(", mFitnessThreshold=").append(this.mFitnessThreshold);
            stringBuffer.append(", mWarmUpThreshold=").append(this.mWarmUpThreshold);
            stringBuffer.append(", mFatBurnThreshold=").append(this.mFatBurnThreshold);
            stringBuffer.append(", mAerobicThreshold=").append(this.mAerobicThreshold);
            stringBuffer.append(", mAnaerobicThreshold=").append(this.mAnaerobicThreshold);
            stringBuffer.append(", mAerobicBaseThreshold=").append(this.mAerobicBaseThreshold);
            stringBuffer.append(", mAerobicAdvanceThreshold=").append(this.mAerobicAdvanceThreshold);
            stringBuffer.append(", mLacticAcidThreshold=").append(this.mLacticAcidThreshold);
            stringBuffer.append(", mAnaerobicBaseThreshold=").append(this.mAnaerobicBaseThreshold);
            stringBuffer.append(", mAnaerobicAdvanceThreshold=").append(this.mAnaerobicAdvanceThreshold);
            stringBuffer.append(", mLactateThresholdHeartRate=").append(this.mLactateThresholdHeartRate);
            stringBuffer.append(", mLthrAnaerobicInterval=").append(this.mLthrAnaerobicInterval);
            stringBuffer.append(", mLthrLactateThreshold=").append(this.mLthrLactateThreshold);
            stringBuffer.append(", mLthrAerobicHighZone=").append(this.mLthrAerobicHighZone);
            stringBuffer.append(", mLthrAerobicLowZone=").append(this.mLthrAerobicLowZone);
            stringBuffer.append(", mLthrRecoveryInterval=").append(this.mLthrRecoveryInterval);
            stringBuffer.append(", mIsSetWarningLimit=").append(this.mHeartZoneStateConfig.getIsSetWarningLimit());
            stringBuffer.append(", mIsSetMaxHeart=").append(this.mHeartZoneStateConfig.getIsSetMaxHeart());
            stringBuffer.append("}");
            return stringBuffer.toString();
        }
        return "HeartRateThresholdConfig{mOldMaxThreshold=" + this.mOldMaxThreshold + ", mPostureType='" + this.mPostureType + ", mClassifyMethod='" + this.mClassifyMethod + ", mIsWarningEnable='" + this.mIsWarningEnable + ", mWarningLimit='" + this.mWarningLimit + ", mMaxThreshold='" + this.mMaxThreshold + ", mRestHeartRate='" + this.mRestHeartRate + ", mFitnessThreshold='" + this.mFitnessThreshold + ", mWarmUpThreshold='" + this.mWarmUpThreshold + ", mFatBurnThreshold='" + this.mFatBurnThreshold + ", mAerobicThreshold='" + this.mAerobicThreshold + ", mAnaerobicThreshold='" + this.mAnaerobicThreshold + ", mAerobicBaseThreshold='" + this.mAerobicBaseThreshold + ", mAerobicAdvanceThreshold='" + this.mAerobicAdvanceThreshold + ", mLacticAcidThreshold='" + this.mLacticAcidThreshold + ", mAnaerobicBaseThreshold='" + this.mAnaerobicBaseThreshold + ", mAnaerobicAdvanceThreshold='" + this.mAnaerobicAdvanceThreshold + ", mIsSetWarningLimit='" + this.mHeartZoneStateConfig.getIsSetWarningLimit() + ", mIsSetMaxHeart='" + this.mHeartZoneStateConfig.getIsSetMaxHeart() + "'}";
    }
}
