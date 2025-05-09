package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("Placemark")
/* loaded from: classes3.dex */
public class Placemark {

    @Ignore
    public static final String COLOR = "PointColor";

    @Ignore
    public static final String ICON = "PointIcon";

    @Ignore
    public static final String MODE = "PointMode";

    @Ignore
    public static final String ROUTE_TYPE = "routeType";

    @Tag("description")
    private String mDescription;

    @Tag(order = 3, value = "ExtendedData")
    private ExtendedData mExtendedData;

    @Ignore
    private GxTrack mGxTrack;

    @Tag("name")
    private String mLapName;

    @Ignore
    private LineString mLineString;

    @Tag(order = 2, value = "Point")
    private Point mPoint;

    @Tag(order = 1, value = "TimeSpan")
    private TimeSpan mTimeSpan;

    public void setTimeSpan(TimeSpan timeSpan) {
        this.mTimeSpan = timeSpan;
    }

    public TimeSpan getTimeSpan() {
        return this.mTimeSpan;
    }

    public void setPoint(Point point) {
        this.mPoint = point;
    }

    public Point getPoint() {
        return this.mPoint;
    }

    public String getLapName() {
        return this.mLapName;
    }

    public void setLapName(String str) {
        this.mLapName = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public LineString getLineString() {
        return this.mLineString;
    }

    public void setLineString(LineString lineString) {
        this.mLineString = lineString;
    }

    public GxTrack getGxTrack() {
        return this.mGxTrack;
    }

    public void setGxTrack(GxTrack gxTrack) {
        this.mGxTrack = gxTrack;
    }

    public ExtendedData getExtendedData() {
        return this.mExtendedData;
    }

    public void setExtendedData(ExtendedData extendedData) {
        this.mExtendedData = extendedData;
    }
}
