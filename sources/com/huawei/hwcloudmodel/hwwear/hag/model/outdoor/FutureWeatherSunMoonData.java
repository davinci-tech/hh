package com.huawei.hwcloudmodel.hwwear.hag.model.outdoor;

/* loaded from: classes5.dex */
public class FutureWeatherSunMoonData {
    private int mMoonPhase;
    private long mMoonRise;
    private long mMoonSet;
    private long mSecondMoonRise;
    private long mSecondMoonSet;
    private long mSecondSunriseTime;
    private long mSecondSunsetTime;
    private long mSunriseTime;
    private long mSunsetTime;

    public long getSunriseTime() {
        return this.mSunriseTime;
    }

    public void setSunriseTime(long j) {
        this.mSunriseTime = j;
    }

    public long getSunsetTime() {
        return this.mSunsetTime;
    }

    public void setSunsetTime(long j) {
        this.mSunsetTime = j;
    }

    public long getSecondSunriseTime() {
        return this.mSecondSunriseTime;
    }

    public long getSecondSunsetTime() {
        return this.mSecondSunsetTime;
    }

    public long getMoonRise() {
        return this.mMoonRise;
    }

    public void setMoonRise(long j) {
        this.mMoonRise = j;
    }

    public long getMoonSet() {
        return this.mMoonSet;
    }

    public void setMoonSet(long j) {
        this.mMoonSet = j;
    }

    public long getSecondMoonRise() {
        return this.mSecondMoonRise;
    }

    public long getSecondMoonSet() {
        return this.mSecondMoonSet;
    }

    public int getMoonPhase() {
        return this.mMoonPhase;
    }

    public void setMoonPhase(int i) {
        this.mMoonPhase = i;
    }

    public String toString() {
        return "FutureWeatherSunMoonData{mSunriseTime=" + this.mSunriseTime + ", mSunsetTime=" + this.mSunsetTime + ", mSecondSunriseTime=" + this.mSecondSunriseTime + ", mSecondSunsetTime=" + this.mSecondSunsetTime + ", mMoonRise=" + this.mMoonRise + ", mMoonSet=" + this.mMoonSet + ", mSecondMoonRise=" + this.mSecondMoonRise + ", mSecondMoonSet=" + this.mSecondMoonSet + ", mMoonPhase=" + this.mMoonPhase + '}';
    }
}
