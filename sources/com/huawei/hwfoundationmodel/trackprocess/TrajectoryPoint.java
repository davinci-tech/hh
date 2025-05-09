package com.huawei.hwfoundationmodel.trackprocess;

/* loaded from: classes5.dex */
public class TrajectoryPoint {
    private double altitude;
    private double latitude;
    private double longitude;
    private long utc_time;

    public TrajectoryPoint(long j, double d, double d2, double d3) {
        this.utc_time = j;
        this.longitude = d;
        this.latitude = d2;
        this.altitude = d3;
    }

    public long getUtc_time() {
        return this.utc_time;
    }

    public void setUtc_time(long j) {
        this.utc_time = j;
    }

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

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public String toString() {
        return "{lat:" + this.latitude + " lon:" + this.longitude + " altitude:" + this.altitude + " time:" + this.utc_time + "};";
    }
}
