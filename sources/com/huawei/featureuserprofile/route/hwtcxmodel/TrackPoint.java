package com.huawei.featureuserprofile.route.hwtcxmodel;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import defpackage.buc;

@Tag("Trackpoint")
/* loaded from: classes3.dex */
public class TrackPoint {

    @Ignore
    public static final String COLOR = "PointColor";

    @Ignore
    public static final String DESC = "PointDescription";

    @Ignore
    public static final String ICON = "PointIcon";

    @Ignore
    public static final String MODE = "PointMode";

    @Ignore
    public static final String NAME = "PointName";

    @Tag(order = 2, value = "AltitudeMeters")
    private double mAltitudeMeters = Double.MIN_VALUE;

    @Tag(order = 3, value = "Extensions")
    private Extensions mExtensions;

    @Tag(order = 1, value = "Position")
    private Position mPosition;

    @Ignore
    private long mTime;

    @Tag("Time")
    private String mTimeString;

    public void setTime(long j) {
        this.mTime = j;
        this.mTimeString = longTimeToString(j);
    }

    public long getTime() {
        return this.mTime;
    }

    public String getTimeString() {
        return this.mTimeString;
    }

    public void setTimeString(String str) {
        this.mTimeString = str;
    }

    public void setPosition(Position position) {
        this.mPosition = position;
    }

    public Position getPosition() {
        return this.mPosition;
    }

    public void setAltitudeMeters(double d) {
        this.mAltitudeMeters = d;
    }

    public double getAltitudeMeters() {
        return this.mAltitudeMeters;
    }

    private String longTimeToString(long j) {
        return buc.a(j);
    }

    public Extensions getExtensions() {
        return this.mExtensions;
    }

    public void setExtensions(Extensions extensions) {
        this.mExtensions = extensions;
    }
}
