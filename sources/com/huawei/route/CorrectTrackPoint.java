package com.huawei.route;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class CorrectTrackPoint {

    @SerializedName("accumDist")
    private float accumDist;

    @SerializedName("accumDist3D")
    private float accumDist3D;

    @SerializedName("alt")
    private float alt;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    public double getLon() {
        return this.lon;
    }

    public void setLon(double d) {
        this.lon = d;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public float getAlt() {
        return this.alt;
    }

    public void setAlt(float f) {
        this.alt = f;
    }

    public float getAccumDist() {
        return this.accumDist;
    }

    public void setAccumDist(float f) {
        this.accumDist = f;
    }

    public float getAccumDist3D() {
        return this.accumDist3D;
    }

    public void setAccumDist3D(float f) {
        this.accumDist3D = f;
    }

    public String toString() {
        return "CorrectTrackPoint{lon=" + this.lon + ", lat=" + this.lat + ", alt=" + this.alt + ", accumDist=" + this.accumDist + ", accumDist3D=" + this.accumDist3D + '}';
    }
}
