package com.huawei.featureuserprofile.route.hwtcxmodel;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import com.huawei.route.RouteType;

@Tag("Lap")
/* loaded from: classes3.dex */
public class Lap {

    @Ignore
    public static final String ROUTE_TYPE = "RouteType";

    @Tag(order = 3, value = "CumulativeClimb")
    private double mCumulativeClimb;

    @Tag(order = 4, value = "CumulativeDecrease")
    private double mCumulativeDecrease;

    @Tag(order = 2, value = "DistanceMeters")
    private double mDistanceMeters;

    @Tag(order = 2, value = "Extensions")
    private Extensions mExtensions;

    @Attribute("StartTime")
    private String mStartTime;

    @Tag(order = 1, value = "TotalTimeSeconds")
    private double mTotalTimeSeconds;

    @Tag(order = 3, value = "Track")
    private Track mTrack;

    public Lap(Builder builder) {
        this.mStartTime = builder.startTime;
        this.mTotalTimeSeconds = builder.totalTimeSeconds;
        this.mDistanceMeters = builder.distanceMeters;
        this.mCumulativeClimb = builder.cumulativeClimb;
        this.mCumulativeDecrease = builder.cumulativeDecrease;
        this.mTrack = builder.track;
        this.mExtensions = builder.extensions;
    }

    public String getStartTime() {
        return this.mStartTime;
    }

    public double getTotalTimeSeconds() {
        return this.mTotalTimeSeconds;
    }

    public double getDistanceMeters() {
        return this.mDistanceMeters;
    }

    public double getCumulativeDecrease() {
        return this.mCumulativeDecrease;
    }

    public double getCumulativeClimb() {
        return this.mCumulativeClimb;
    }

    public Track getTrack() {
        return this.mTrack;
    }

    public Extensions getExtensions() {
        return this.mExtensions;
    }

    public int getRouteType() {
        Extensions extensions = this.mExtensions;
        if (extensions == null) {
            return RouteType.DEFAULT.routeType();
        }
        return Extensions.getInt(extensions.getExtensionsMap(), ROUTE_TYPE, RouteType.DEFAULT.routeType());
    }

    public static class Builder {
        private double cumulativeClimb;
        private double cumulativeDecrease;
        private double distanceMeters;
        private Extensions extensions;
        private String startTime;
        private double totalTimeSeconds;
        private Track track;

        public Builder setStartTime(String str) {
            this.startTime = str;
            return this;
        }

        public Builder setTotalTimeSeconds(double d) {
            this.totalTimeSeconds = d;
            return this;
        }

        public Builder setDistanceMeters(double d) {
            this.distanceMeters = d;
            return this;
        }

        public Builder setCumulativeClimb(double d) {
            this.cumulativeClimb = d;
            return this;
        }

        public Builder setCumulativeDecrease(double d) {
            this.cumulativeDecrease = d;
            return this;
        }

        public Builder setTrack(Track track) {
            this.track = track;
            return this;
        }

        public Builder setExtensions(Extensions extensions) {
            this.extensions = extensions;
            return this;
        }

        public Lap build() {
            return new Lap(this);
        }
    }
}
