package com.huawei.health.userprofilemgr.api;

import com.huawei.hihealth.HiHealthData;
import com.huawei.route.LbsInfo;
import com.huawei.route.Point;
import com.huawei.route.TrackInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes4.dex */
public interface RouteApi {
    File generateGpxFile(HiHealthData hiHealthData, String str, String str2, Map<String, Object> map, LbsInfo lbsInfo) throws IOException, XmlPullParserException;

    File generateGpxFile(HiHealthData hiHealthData, String str, String str2, Map<String, Object> map, TrackInfo trackInfo) throws IOException, XmlPullParserException;

    double getCumulativeClimb();

    double getCumulativeDecrease();

    List<Point> getPoints();

    int getRouteType();

    double getSportTotalDistance();

    double getSportTotalTime();

    String getSportType();

    int startParser(InputStream inputStream, String str) throws IOException, XmlPullParserException;
}
