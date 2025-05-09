package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;

/* loaded from: classes9.dex */
public class TrainingStruct {

    @SerializedName("training_duration")
    private int mTrainingDuration;

    @SerializedName("training_hr_limit_high")
    private int mTrainingHrLimitHigh;

    @SerializedName("training_hr_limit_low")
    private int mTrainingHrLimitLow;

    @SerializedName("training_intensity_limit_high")
    private int mTrainingIntensityLimitHigh;

    @SerializedName("training_intensity_limit_low")
    private int mTrainingIntensityLimitLow;

    @SerializedName("training_speed_limit_high")
    private int mTrainingSpeedLimitHigh;

    @SerializedName("training_speed_limit_low")
    private int mTrainingSpeedLimitLow;

    @SerializedName("training_type")
    private int mTrainingType;

    public int getTrainingType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingType))).intValue();
    }

    public void setTrainingType(int i) {
        this.mTrainingType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingSpeedLimitHigh() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingSpeedLimitHigh))).intValue();
    }

    public void setTrainingSpeedLimitHigh(int i) {
        this.mTrainingSpeedLimitHigh = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingSpeedLimitLow() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingSpeedLimitLow))).intValue();
    }

    public void setTrainingSpeedLimitLow(int i) {
        this.mTrainingSpeedLimitLow = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingHrLimitHigh() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingHrLimitHigh))).intValue();
    }

    public void setTrainingHrLimitHigh(int i) {
        this.mTrainingHrLimitHigh = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingHrLimitLow() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingHrLimitLow))).intValue();
    }

    public void setTrainingHrLimitLow(int i) {
        this.mTrainingHrLimitLow = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingIntensityLimitHigh() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingIntensityLimitHigh))).intValue();
    }

    public void setTrainingIntensityLimitHigh(int i) {
        this.mTrainingIntensityLimitHigh = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingIntensityLimitLow() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingIntensityLimitLow))).intValue();
    }

    public void setTrainingIntensityLimitLow(int i) {
        this.mTrainingIntensityLimitLow = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getTrainingDuration() {
        return ((Integer) jdy.d(Integer.valueOf(this.mTrainingDuration))).intValue();
    }

    public void setTrainingDuration(int i) {
        this.mTrainingDuration = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(this.mTrainingType);
        stringBuffer.append(this.mTrainingSpeedLimitHigh);
        stringBuffer.append(this.mTrainingSpeedLimitLow);
        stringBuffer.append(this.mTrainingHrLimitHigh);
        stringBuffer.append(this.mTrainingHrLimitLow);
        stringBuffer.append(this.mTrainingIntensityLimitHigh);
        stringBuffer.append(this.mTrainingIntensityLimitLow);
        stringBuffer.append(this.mTrainingDuration);
        return stringBuffer.toString();
    }
}
