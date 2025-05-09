package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dsy {

    @SerializedName("abnormalHeartRateRatio")
    private float mAbnormalHeartRateRatio;

    @SerializedName("adjustMode")
    private int mAdjustMode;

    @SerializedName("adjustSpeedMax")
    private float mAdjustSpeedMax;

    @SerializedName("bikeChangeNum")
    private int mBikeChangeNum;

    @SerializedName("checkSustainTime")
    private int mCheckSustainTime;

    @SerializedName("heartRateStableTime")
    private int mHeartRateStableTime;

    @SerializedName("lowerLimitRpm")
    private int mLowerLimitRpm;

    @SerializedName("maxChangedNumber")
    private int mMaxChangedNumber;

    @SerializedName("maxPower")
    private int mMaxPower;

    @SerializedName("maximumSafeRateLimit")
    private int mMaximumSafeRateLimit;

    @SerializedName("version")
    private long mVersion;

    public int getMaximumSafeRateLimit() {
        return this.mMaximumSafeRateLimit;
    }

    public int getCheckSustainTime() {
        return this.mCheckSustainTime;
    }

    public int getHeartRateStableTime() {
        return this.mHeartRateStableTime;
    }

    public float getAdjustSpeedMax() {
        return this.mAdjustSpeedMax;
    }

    public int getMaxChangedNumber() {
        return this.mMaxChangedNumber;
    }

    public float getAbnormalHeartRateRatio() {
        return this.mAbnormalHeartRateRatio;
    }

    public int getAdjustMode() {
        return this.mAdjustMode;
    }

    public int getLowerLimitRpm() {
        return this.mLowerLimitRpm;
    }

    public int getMaxPower() {
        return this.mMaxPower;
    }
}
