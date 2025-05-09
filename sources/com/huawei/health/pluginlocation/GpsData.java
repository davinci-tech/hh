package com.huawei.health.pluginlocation;

/* loaded from: classes3.dex */
public class GpsData {
    private double Latitude;
    private double accuracy;
    private double altitude;
    private double longitude;
    private double speed;
    private double time;

    public GpsData() {
    }

    public GpsData(double d, double d2, double d3, double d4, double d5, double d6) {
        this.time = d;
        this.Latitude = d2;
        this.longitude = d3;
        this.altitude = d4;
        this.accuracy = d5;
        this.speed = d6;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double d) {
        this.time = d;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(double d) {
        this.Latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(double d) {
        this.accuracy = d;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double d) {
        this.speed = d;
    }

    public String toString() {
        return this.time + "," + this.altitude + "," + this.longitude + "," + this.Latitude + "," + this.speed + "," + this.accuracy;
    }
}
