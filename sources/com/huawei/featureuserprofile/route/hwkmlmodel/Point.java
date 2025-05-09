package com.huawei.featureuserprofile.route.hwkmlmodel;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("Point")
/* loaded from: classes3.dex */
public class Point {

    @Tag("altitudeMode")
    private String mAltitudeMode = "clampToGround";

    @Tag(order = 2, value = "coordinates")
    private String mCoordinates;

    @Ignore
    private double mEle;

    @Ignore
    private double mLat;

    @Ignore
    private double mLon;

    public Point(Builder builder) {
        this.mLon = builder.mLon;
        this.mLat = builder.mLat;
        this.mEle = builder.mEle;
        this.mCoordinates = builder.mCoordinates;
    }

    public void setLon(double d) {
        this.mLon = d;
    }

    public double getLon() {
        return this.mLon;
    }

    public void setLat(double d) {
        this.mLat = d;
    }

    public double getLat() {
        return this.mLat;
    }

    public void setEle(double d) {
        this.mEle = d;
    }

    public double getEle() {
        return this.mEle;
    }

    public String getCoordinates() {
        if (TextUtils.isEmpty(this.mCoordinates)) {
            if (Math.abs(this.mEle - Double.MIN_VALUE) < 1.0E-6d) {
                this.mCoordinates = this.mLon + "," + this.mLat;
            } else {
                this.mCoordinates = this.mLon + "," + this.mLat + ", " + this.mEle;
            }
        }
        return this.mCoordinates;
    }

    public void setCoordinates(String str) {
        this.mCoordinates = str;
    }

    public String getAltitudeMode() {
        return this.mAltitudeMode;
    }

    public static class Builder {
        private String mCoordinates;
        private double mEle = Double.MIN_VALUE;
        private double mLat;
        private double mLon;

        public Builder setLon(double d) {
            this.mLon = d;
            return this;
        }

        public Builder setLat(double d) {
            this.mLat = d;
            return this;
        }

        public Builder setEle(double d) {
            this.mEle = d;
            return this;
        }

        public Builder setCoordinates(String str) {
            this.mCoordinates = str;
            return this;
        }

        public Point build() {
            return new Point(this);
        }
    }
}
