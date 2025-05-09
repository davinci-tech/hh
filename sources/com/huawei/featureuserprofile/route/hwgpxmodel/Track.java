package com.huawei.featureuserprofile.route.hwgpxmodel;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import java.util.ArrayList;
import java.util.List;

@Tag("trk")
/* loaded from: classes3.dex */
public class Track {

    @Ignore
    public static final String ROUTE_TYPE = "routeType";

    @Tag(order = 1, value = "extensions")
    private Extensions mExtensions;

    @Tag(order = 2)
    private List<TrackSegment> mTrackSegments;

    @Tag("type")
    private String mType;

    public void setType(String str) {
        if (TextUtils.isEmpty(str.trim())) {
            return;
        }
        this.mType = str;
    }

    public String getType() {
        return this.mType;
    }

    public <T> void addExtension(String str, T t) {
        if (this.mExtensions == null) {
            this.mExtensions = new Extensions();
        }
        this.mExtensions.addExtension(str, t);
    }

    public void addTrackSegment(TrackSegment trackSegment) {
        if (trackSegment == null) {
            throw new IllegalArgumentException("trackSegment cannot be null.");
        }
        if (this.mTrackSegments == null) {
            this.mTrackSegments = new ArrayList();
        }
        this.mTrackSegments.add(trackSegment);
    }

    public void addTrackSegmentWithTrackPoints(List<TrackPoint> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("trackPoints cannot be null or empty.");
        }
        addTrackSegment(new TrackSegment(list));
    }

    public List<TrackSegment> getTrackSegments() {
        return this.mTrackSegments;
    }

    public void setTrackSegments(List<TrackSegment> list) {
        this.mTrackSegments = list;
    }

    public Extensions getExtensions() {
        return this.mExtensions;
    }

    public void setExtensions(Extensions extensions) {
        this.mExtensions = extensions;
    }
}
