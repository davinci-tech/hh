package com.huawei.healthcloud.plugintrack.golf.bean;

/* loaded from: classes.dex */
public class GolfLocationData {
    private String city;
    private double latitude;
    private double longitude;

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String toString() {
        return "GolfLocationData{longitude=" + this.longitude + ", latitude=" + this.latitude + ", city=" + this.city + "}";
    }
}
