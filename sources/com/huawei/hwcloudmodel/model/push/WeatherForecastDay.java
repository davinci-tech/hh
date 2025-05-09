package com.huawei.hwcloudmodel.model.push;

import com.huawei.hwcloudmodel.hwwear.hag.model.outdoor.FutureWeatherSunMoonData;

/* loaded from: classes5.dex */
public class WeatherForecastDay {
    private int mCnWeatherIcon;
    private FutureWeatherSunMoonData mFutureWeatherSunMoonData;
    private int mHighestTemperature;
    private boolean mIsComplete = true;
    private int mLowestTemperature;
    private long mTime;
    private String mUnit;
    private int mWeatherIcon;

    public FutureWeatherSunMoonData getFutureWeatherSunMoonData() {
        return this.mFutureWeatherSunMoonData;
    }

    public void setFutureWeatherSunMoonData(FutureWeatherSunMoonData futureWeatherSunMoonData) {
        this.mFutureWeatherSunMoonData = futureWeatherSunMoonData;
    }

    public int getCnWeatherIcon() {
        return this.mCnWeatherIcon;
    }

    public void setCnWeatherIcon(int i) {
        this.mCnWeatherIcon = i;
    }

    public boolean isComplete() {
        return this.mIsComplete;
    }

    public void setIsComplete(boolean z) {
        this.mIsComplete = z;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public int getWeatherIcon() {
        return this.mWeatherIcon;
    }

    public void setWeatherIcon(int i) {
        this.mWeatherIcon = i;
    }

    public int getHighestTemperature() {
        return this.mHighestTemperature;
    }

    public void setHighestTemperature(int i) {
        this.mHighestTemperature = i;
    }

    public int getLowestTemperature() {
        return this.mLowestTemperature;
    }

    public void setLowestTemperature(int i) {
        this.mLowestTemperature = i;
    }

    public String getUnit() {
        return this.mUnit;
    }

    public void setUnit(String str) {
        this.mUnit = str;
    }

    public String toString() {
        return "WeatherForecastDay{mTime=" + this.mTime + ", mWeatherIcon=" + this.mWeatherIcon + ", mHighestTemperature=" + this.mHighestTemperature + ", mLowestTemperature=" + this.mLowestTemperature + ", mUnit='" + this.mUnit + "', mIsComplete=" + this.mIsComplete + ", mCnWeatherIcon=" + this.mCnWeatherIcon + ", mFutureWeatherSunMoonData=" + this.mFutureWeatherSunMoonData + '}';
    }
}
