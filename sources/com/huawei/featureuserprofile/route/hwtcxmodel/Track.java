package com.huawei.featureuserprofile.route.hwtcxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import java.util.List;

@Tag("Track")
/* loaded from: classes3.dex */
public class Track {

    @Tag(order = 1)
    private List<TrackPoint> mTrackPoint;

    public void setTrackPoint(List<TrackPoint> list) {
        this.mTrackPoint = list;
    }

    public List<TrackPoint> getTrackPoint() {
        return this.mTrackPoint;
    }
}
