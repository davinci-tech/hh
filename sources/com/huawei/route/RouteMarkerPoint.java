package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.network.embedded.k;

/* loaded from: classes6.dex */
public class RouteMarkerPoint {

    @SerializedName("alt")
    private double altitude;

    @SerializedName("color")
    private int color;

    @SerializedName("indexOfTrackPoint")
    private int indexOfTrackPoint;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    @SerializedName("name")
    private String name;

    @SerializedName(k.f.f5336a)
    private int serial;

    @SerializedName("type")
    private int type;

    public RouteMarkerPoint() {
    }

    private RouteMarkerPoint(Builder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.altitude = builder.altitude;
        this.name = builder.name;
        this.serial = builder.serial;
        this.type = builder.type;
        this.color = builder.color;
        this.indexOfTrackPoint = builder.indexOfTrackPoint;
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private double altitude;
        private int color;
        private int indexOfTrackPoint;
        private double latitude;
        private double longitude;
        private String name;
        private int serial;
        private int type;

        public Builder setLongitude(double d) {
            this.longitude = d;
            return this;
        }

        public Builder setLatitude(double d) {
            this.latitude = d;
            return this;
        }

        public Builder setAltitude(double d) {
            this.altitude = d;
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setSerial(int i) {
            this.serial = i;
            return this;
        }

        public Builder setType(int i) {
            this.type = i;
            return this;
        }

        public Builder setColor(int i) {
            this.color = i;
            return this;
        }

        public Builder setIndexOfTrackPoint(int i) {
            this.indexOfTrackPoint = i;
            return this;
        }

        public RouteMarkerPoint build() {
            return new RouteMarkerPoint(this);
        }
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

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public int getIndexOfTrackPoint() {
        return this.indexOfTrackPoint;
    }

    public void setIndexOfTrackPoint(int i) {
        this.indexOfTrackPoint = i;
    }

    public static RouteMarkerPoint convertRouteMarkerPoint(int i, Point point, CpInfo cpInfo) {
        RouteMarkerPoint routeMarkerPoint = new RouteMarkerPoint();
        routeMarkerPoint.setIndexOfTrackPoint(i);
        if (point != null) {
            routeMarkerPoint.setLatitude(point.getLatitude());
            routeMarkerPoint.setLongitude(point.getLongitude());
            routeMarkerPoint.setAltitude(point.getAltitude());
        }
        if (cpInfo != null) {
            routeMarkerPoint.setColor(cpInfo.getColor());
            routeMarkerPoint.setName(cpInfo.getName());
            routeMarkerPoint.setType(cpInfo.getType());
        }
        return routeMarkerPoint;
    }

    public String toString() {
        return "RouteMarkerPoint{longitude=" + this.longitude + ", latitude=" + this.latitude + ", altitude=" + this.altitude + ", name=" + this.name + ", serial=" + this.serial + ", type=" + this.type + ", color=" + this.color + ", indexOfTrackPoint=" + this.indexOfTrackPoint + '}';
    }
}
