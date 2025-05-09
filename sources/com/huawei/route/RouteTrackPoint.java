package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes6.dex */
public class RouteTrackPoint {

    @SerializedName("alt")
    private double altitude;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    @SerializedName("timestamp")
    private long timestamp;

    public RouteTrackPoint() {
    }

    private RouteTrackPoint(Builder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.altitude = builder.altitude;
        this.timestamp = builder.timestamp;
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private double altitude;
        private double latitude;
        private double longitude;
        private long timestamp;

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

        public Builder setTimestamp(long j) {
            this.timestamp = j;
            return this;
        }

        public RouteTrackPoint build() {
            return new RouteTrackPoint(this);
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

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public static RouteTrackPoint convertRouteTrackPoint(Point point) {
        RouteTrackPoint routeTrackPoint = new RouteTrackPoint();
        routeTrackPoint.setLatitude(point.getLatitude());
        routeTrackPoint.setLongitude(point.getLongitude());
        routeTrackPoint.setAltitude(point.getAltitude());
        routeTrackPoint.setTimestamp(point.getTimeStamp());
        return routeTrackPoint;
    }

    public String toString() {
        return "RouteTrackPoint{longitude=" + this.longitude + ", latitude=" + this.latitude + ", altitude=" + this.altitude + ", timestamp=" + this.timestamp + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RouteTrackPoint routeTrackPoint = (RouteTrackPoint) obj;
        return Double.compare(routeTrackPoint.longitude, this.longitude) == 0 && Double.compare(routeTrackPoint.latitude, this.latitude) == 0 && Double.compare(routeTrackPoint.altitude, this.altitude) == 0 && this.timestamp == routeTrackPoint.timestamp;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.longitude), Double.valueOf(this.latitude), Double.valueOf(this.altitude), Long.valueOf(this.timestamp));
    }
}
