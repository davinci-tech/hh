package com.huawei.featureuserprofile.route.hwgpxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import java.util.ArrayList;
import java.util.List;

@Tag("trkseg")
/* loaded from: classes3.dex */
public class TrackSegment {
    private List<TrackPoint> mTrackPoints;

    public TrackSegment() {
        this(null);
    }

    public TrackSegment(List<TrackPoint> list) {
        this.mTrackPoints = list;
    }

    public void addTrackPoint(double d, double d2, String str) {
        addTrackPoint(new TrackPoint(d, d2, str));
    }

    public void addTrackPoint(double d, double d2, String str, int i) {
        TrackPoint trackPoint = new TrackPoint(d, d2, str);
        if (i > 0) {
            TrackPointExtensions trackPointExtensions = new TrackPointExtensions();
            trackPointExtensions.setHeart(i);
            trackPoint.setExtensions(trackPointExtensions);
        }
        addTrackPoint(trackPoint);
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoint == null) {
            throw new IllegalArgumentException("trackPoint cannot be null.");
        }
        if (this.mTrackPoints == null) {
            this.mTrackPoints = new ArrayList();
        }
        this.mTrackPoints.add(trackPoint);
    }

    public List<TrackPoint> getTrackPoints() {
        return this.mTrackPoints;
    }

    public void setTrackPoints(List<TrackPoint> list) {
        this.mTrackPoints = list;
    }
}
