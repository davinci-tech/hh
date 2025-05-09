package com.huawei.featureuserprofile.route.hwgpxmodel;

/* loaded from: classes3.dex */
public class RoutePoint {
    private double mElevation = Double.MIN_VALUE;
    private double mLatitude;
    private double mLongitude;
    private long mTime;

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double d) {
        this.mLatitude = d;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double d) {
        this.mLongitude = d;
    }

    public double getElevation() {
        return this.mElevation;
    }

    public void setElevation(double d) {
        this.mElevation = d;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }
}
