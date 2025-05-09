package com.huawei.healthcloud.plugintrack.golf.bean;

/* loaded from: classes8.dex */
public class GolfTrackLatLng {
    private int distance;
    private double latitude;
    private double longitude;

    public GolfTrackLatLng(double d, double d2, int i) {
        this.latitude = d;
        this.longitude = d2;
        this.distance = i;
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

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }
}
