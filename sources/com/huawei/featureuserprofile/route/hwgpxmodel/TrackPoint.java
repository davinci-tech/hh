package com.huawei.featureuserprofile.route.hwgpxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import defpackage.buc;

@Tag("trkpt")
/* loaded from: classes3.dex */
public class TrackPoint {

    @Ignore
    private static final double DOUBLE_ZERO = 0.0d;

    @Tag("ele")
    private double mElevation;

    @Tag("extensions")
    private TrackPointExtensions mExtensions;

    @Attribute("lat")
    private double mLatitude;

    @Attribute("lon")
    private double mLongitude;

    @Tag("time")
    private String mStringTime;

    @Ignore
    private long mTime;

    public TrackPoint(double d, double d2, String str) {
        this.mElevation = Double.MIN_VALUE;
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mStringTime = str;
    }

    public TrackPoint() {
        this(0.0d, 0.0d, "");
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public TrackPointExtensions getExtensions() {
        return this.mExtensions;
    }

    public void setExtensions(TrackPointExtensions trackPointExtensions) {
        this.mExtensions = trackPointExtensions;
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
        this.mStringTime = longTimeToString(j);
    }

    public String getStringTime() {
        return this.mStringTime;
    }

    private String longTimeToString(long j) {
        return buc.a(j);
    }
}
