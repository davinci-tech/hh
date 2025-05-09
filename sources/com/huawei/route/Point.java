package com.huawei.route;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class Point {
    private static final double DOUBLE_ZERO = 0.0d;
    private static final long LONG_ZERO = 0;

    @SerializedName("al")
    private double altitude;

    @SerializedName("cp")
    private CpInfo cpInfo;

    @SerializedName("la")
    private double latitude;

    @SerializedName("lo")
    private double longitude;

    @SerializedName("ts")
    private long timeStamp;

    public Point() {
        this(0.0d, 0.0d, Double.MIN_VALUE, 0L);
    }

    public Point(double d, double d2, double d3, long j) {
        this.longitude = d;
        this.latitude = d2;
        this.altitude = d3;
        this.timeStamp = j;
    }

    public Point(Builder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.altitude = builder.altitude;
        this.timeStamp = builder.timeStamp;
        this.cpInfo = builder.cpInfo;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public CpInfo getCpInfo() {
        return this.cpInfo;
    }

    public void setCpInfo(CpInfo cpInfo) {
        this.cpInfo = cpInfo;
    }

    public static class Builder {
        private double altitude;
        private CpInfo cpInfo;
        private double latitude;
        private double longitude;
        private long timeStamp;

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

        public Builder setTimeStamp(long j) {
            this.timeStamp = j;
            return this;
        }

        public Builder setCpInfo(CpInfo cpInfo) {
            this.cpInfo = cpInfo;
            return this;
        }

        public Point build() {
            return new Point(this);
        }
    }
}
