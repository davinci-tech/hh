package com.huawei.hwcloudmodel.model.push;

/* loaded from: classes5.dex */
public class WeatherForecastHour {
    private int mCnWeatherIcon;
    private int mPrecipitation;
    private int mTemperature;
    private long mTime;
    private String mUnit;
    private int mWeatherIcon;

    public int getPrecipitation() {
        return this.mPrecipitation;
    }

    public void setPrecipitation(int i) {
        this.mPrecipitation = i;
    }

    public int getCnWeatherIcon() {
        return this.mCnWeatherIcon;
    }

    public void setCnWeatherIcon(int i) {
        this.mCnWeatherIcon = i;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public int getTemperature() {
        return this.mTemperature;
    }

    public void setTemperature(int i) {
        this.mTemperature = i;
    }

    public int getWeatherIcon() {
        return this.mWeatherIcon;
    }

    public void setWeatherIcon(int i) {
        this.mWeatherIcon = i;
    }

    public String getUnit() {
        return this.mUnit;
    }

    public void setUnit(String str) {
        this.mUnit = str;
    }

    public String toString() {
        return "WeatherForecastHour{mTime=" + this.mTime + ", mTemperature=" + this.mTemperature + ", mWeatherIcon=" + this.mWeatherIcon + ", mUnit='" + this.mUnit + "', mCnWeatherIcon=" + this.mCnWeatherIcon + ", mPrecipitation=" + this.mPrecipitation + '}';
    }
}
