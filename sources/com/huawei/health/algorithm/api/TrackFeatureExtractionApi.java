package com.huawei.health.algorithm.api;

import com.huawei.route.Point;
import com.huawei.route.RouteOutputForApp;
import com.huawei.route.RouteTrackInfo;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public interface TrackFeatureExtractionApi {
    byte[] algTrackImport(int i, float f, List<Point> list) throws IOException;

    byte[] algTrackImport(RouteTrackInfo routeTrackInfo, int i, double d);

    List<Point> featurePointExtraction(List<Point> list, int i);

    RouteOutputForApp getTrackOutputForApp(RouteTrackInfo routeTrackInfo, int i, double d, boolean z);
}
