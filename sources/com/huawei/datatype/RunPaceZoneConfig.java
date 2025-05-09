package com.huawei.datatype;

/* loaded from: classes3.dex */
public class RunPaceZoneConfig {
    private int mAnaerobicPaceZoneMinValue;
    private int mEasyPaceZoneMinValue;
    private int mLactatePaceZoneMinValue;
    private int mMarathonPaceZoneMinValue;
    private int mMaxOxygenPaceZoneMaxValue;
    private int mMaxOxygenPaceZoneMinValue;

    public int getEasyPaceZoneMinValue() {
        return this.mEasyPaceZoneMinValue;
    }

    public int getMarathonPaceZoneMinValue() {
        return this.mMarathonPaceZoneMinValue;
    }

    public void setMarathonPaceZoneMinValue(int i) {
        this.mMarathonPaceZoneMinValue = i;
    }

    public int getLactatePaceZoneMinValue() {
        return this.mLactatePaceZoneMinValue;
    }

    public void setLactatePaceZoneMinValue(int i) {
        this.mLactatePaceZoneMinValue = i;
    }

    public int getAnaerobicPaceZoneMinValue() {
        return this.mAnaerobicPaceZoneMinValue;
    }

    public void setAnaerobicPaceZoneMinValue(int i) {
        this.mAnaerobicPaceZoneMinValue = i;
    }

    public int getMaxOxygenPaceZoneMinValue() {
        return this.mMaxOxygenPaceZoneMinValue;
    }

    public void setMaxOxygenPaceZoneMinValue(int i) {
        this.mMaxOxygenPaceZoneMinValue = i;
    }

    public int getMaxOxygenPaceZoneMaxValue() {
        return this.mMaxOxygenPaceZoneMaxValue;
    }

    public void setMaxOxygenPaceZoneMaxValue(int i) {
        this.mMaxOxygenPaceZoneMaxValue = i;
    }

    public void setEasyPaceZoneMinValue(int i) {
        this.mEasyPaceZoneMinValue = i;
    }

    public String toString() {
        return "RunPaceZoneConfig{mEasyPaceZoneMinValue=" + this.mEasyPaceZoneMinValue + ", mMarathonPaceZoneMinValue=" + this.mMarathonPaceZoneMinValue + ",mLactatePaceZoneMinValue=" + this.mLactatePaceZoneMinValue + ", mAnaerobicPaceZoneMinValue=" + this.mAnaerobicPaceZoneMinValue + ", mMaxOxygenPaceZoneMinValue=" + this.mMaxOxygenPaceZoneMinValue + ", mMaxOxygenPaceZoneMaxValue=" + this.mMaxOxygenPaceZoneMaxValue + " " + super.toString() + '}';
    }
}
