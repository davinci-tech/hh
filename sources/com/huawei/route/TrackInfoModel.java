package com.huawei.route;

import java.util.List;

/* loaded from: classes6.dex */
public class TrackInfoModel {
    private final double mCumulativeClimb;
    private final double mCumulativeDecrease;
    private final List<Point> mPoints;
    private final String mRouteName;
    private final int mRouteType;
    private final double mSportTotalDistance;
    private final double mSportTotalTime;
    private final String mSportType;

    public String getSportType() {
        return this.mSportType;
    }

    public int getRouteType() {
        return this.mRouteType;
    }

    public double getSportTotalTime() {
        return this.mSportTotalTime;
    }

    public double getSportTotalDistance() {
        return this.mSportTotalDistance;
    }

    public double getCumulativeClimb() {
        return this.mCumulativeClimb;
    }

    public double getCumulativeDecrease() {
        return this.mCumulativeDecrease;
    }

    public List<Point> getPoints() {
        return this.mPoints;
    }

    public String getRouteName() {
        return this.mRouteName;
    }

    public TrackInfoModel(Builder builder) {
        this.mSportTotalTime = builder.mSportTotalTime;
        this.mSportTotalDistance = builder.mSportTotalDistance;
        this.mRouteName = builder.mRouteName;
        this.mSportType = builder.mSportType;
        this.mPoints = builder.mPoints;
        this.mCumulativeClimb = builder.mCumulativeClimb;
        this.mCumulativeDecrease = builder.mCumulativeDecrease;
        this.mRouteType = builder.mRouteType;
    }

    public static class Builder {
        private double mCumulativeClimb;
        private double mCumulativeDecrease;
        private List<Point> mPoints;
        private String mRouteName;
        private int mRouteType;
        private double mSportTotalDistance;
        private double mSportTotalTime;
        private String mSportType;

        public Builder points(List<Point> list) {
            this.mPoints = list;
            return this;
        }

        public Builder sportType(String str) {
            this.mSportType = str;
            return this;
        }

        public Builder sportTotalTime(double d) {
            this.mSportTotalTime = d;
            return this;
        }

        public Builder sportTotalDistance(double d) {
            this.mSportTotalDistance = d;
            return this;
        }

        public Builder cumulativeClimb(double d) {
            this.mCumulativeClimb = d;
            return this;
        }

        public Builder cumulativeDecrease(double d) {
            this.mCumulativeDecrease = d;
            return this;
        }

        public Builder routeName(String str) {
            this.mRouteName = str;
            return this;
        }

        public Builder routeType(int i) {
            this.mRouteType = i;
            return this;
        }

        public TrackInfoModel build() {
            return new TrackInfoModel(this);
        }
    }
}
