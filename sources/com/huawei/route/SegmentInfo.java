package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.network.embedded.k;

/* loaded from: classes9.dex */
public class SegmentInfo {

    @SerializedName("ascent")
    private float ascent;

    @SerializedName("color")
    private int color;

    @SerializedName("descent")
    private float descent;

    @SerializedName("dist")
    private float dist;

    @SerializedName("dist3D")
    private float dist3D;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("name")
    private String name;

    @SerializedName(k.f.f5336a)
    private int serial;

    @SerializedName("type")
    private int type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

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

    public float getDist() {
        return this.dist;
    }

    public void setDist(float f) {
        this.dist = f;
    }

    public float getDist3D() {
        return this.dist3D;
    }

    public void setDist3D(float f) {
        this.dist3D = f;
    }

    public float getAscent() {
        return this.ascent;
    }

    public void setAscent(float f) {
        this.ascent = f;
    }

    public float getDescent() {
        return this.descent;
    }

    public void setDescent(float f) {
        this.descent = f;
    }

    public int getSerial() {
        return this.serial;
    }

    public void setSerial(int i) {
        this.serial = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public String toString() {
        return "SegmentInfo{name='" + this.name + "', lon=" + this.lon + ", lat=" + this.lat + ", dist=" + this.dist + ", dist3D=" + this.dist3D + ", ascent=" + this.ascent + ", descent=" + this.descent + ", serial=" + this.serial + ", type=" + this.type + ", color=" + this.color + '}';
    }
}
