package com.huawei.hwcloudmodel.model.unite;

import java.io.Serializable;

/* loaded from: classes7.dex */
public class Location implements Serializable, Cloneable {
    private static final long serialVersionUID = -3816465305762918837L;
    private double altitude;
    private double latitude;
    private double longitude;
    private String name;
    private Long timestamp;

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public String toString() {
        return "Location [timestamp=" + this.timestamp + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", altitude=" + this.altitude + ", name=" + this.name + "]";
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Location m645clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException unused) {
            Location location = new Location();
            location.altitude = this.altitude;
            location.latitude = this.latitude;
            location.name = this.name;
            location.timestamp = this.timestamp;
            location.longitude = this.longitude;
            return location;
        }
    }
}
