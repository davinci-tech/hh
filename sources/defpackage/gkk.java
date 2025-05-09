package defpackage;

import com.huawei.health.algorithm.api.TrackFeatureExtractionApi;
import com.huawei.health.trackimport.algorithm.TrackFeatureExtraction;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.route.Point;
import com.huawei.route.RouteOutputForApp;
import com.huawei.route.RouteTrackInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApiDefine(uri = TrackFeatureExtractionApi.class)
@Singleton
/* loaded from: classes4.dex */
public class gkk implements TrackFeatureExtractionApi {
    @Override // com.huawei.health.algorithm.api.TrackFeatureExtractionApi
    public byte[] algTrackImport(int i, float f, List<Point> list) throws IOException {
        TrackFeatureExtraction trackFeatureExtraction = new TrackFeatureExtraction();
        LogUtil.b("Track_FeatureExtractionImpl", "the init err is:" + trackFeatureExtraction.algInit(i, f));
        for (int i2 = 0; i2 < list.size(); i2++) {
            LogUtil.b("Track_FeatureExtractionImpl", "the add err is:" + trackFeatureExtraction.addTrackPoint(list.get(i2).getTimeStamp() / 1000, list.get(i2).getLatitude(), list.get(i2).getLongitude()));
        }
        trackFeatureExtraction.stopAlg();
        trackFeatureExtraction.obtainResultSize();
        trackFeatureExtraction.obtainCompressionRadio();
        return trackFeatureExtraction.obtainResult();
    }

    @Override // com.huawei.health.algorithm.api.TrackFeatureExtractionApi
    public byte[] algTrackImport(RouteTrackInfo routeTrackInfo, int i, double d) {
        ReleaseLogUtil.c("Track_FeatureExtractionImpl", "algTrackImport inflectPointsMaxNum:", Integer.valueOf(i));
        TrackFeatureExtraction trackFeatureExtraction = new TrackFeatureExtraction();
        int TrackAlgInit = trackFeatureExtraction.TrackAlgInit(routeTrackInfo.getPointsNum(), routeTrackInfo.getMarkerPointsNum(), i, d, true);
        if (TrackAlgInit != 0) {
            ReleaseLogUtil.c("Track_FeatureExtractionImpl", "initCode code is ", Integer.valueOf(TrackAlgInit));
            return new byte[0];
        }
        int ProcessTrackInfo = trackFeatureExtraction.ProcessTrackInfo(routeTrackInfo);
        if (ProcessTrackInfo != 0) {
            ReleaseLogUtil.c("Track_FeatureExtractionImpl", "algTrackImport processCode is ", Integer.valueOf(ProcessTrackInfo), " closeCode is ", Integer.valueOf(trackFeatureExtraction.TrackAlgClose()));
            return new byte[0];
        }
        byte[] GetTrackResultForWatch = trackFeatureExtraction.GetTrackResultForWatch();
        int GetTrackResultSizeForWatch = trackFeatureExtraction.GetTrackResultSizeForWatch();
        ReleaseLogUtil.e("Track_FeatureExtractionImpl", "init code is ", Integer.valueOf(TrackAlgInit), " processCode is ", Integer.valueOf(ProcessTrackInfo), " closeCode is ", Integer.valueOf(trackFeatureExtraction.TrackAlgClose()), " resultSize=", Integer.valueOf(GetTrackResultSizeForWatch));
        return GetTrackResultForWatch;
    }

    @Override // com.huawei.health.algorithm.api.TrackFeatureExtractionApi
    public RouteOutputForApp getTrackOutputForApp(RouteTrackInfo routeTrackInfo, int i, double d, boolean z) {
        ReleaseLogUtil.c("Track_FeatureExtractionImpl", "getTrackOutputForApp inflectPointsMaxNum:", Integer.valueOf(i));
        TrackFeatureExtraction trackFeatureExtraction = new TrackFeatureExtraction();
        int TrackAlgInit = trackFeatureExtraction.TrackAlgInit(routeTrackInfo.getPointsNum(), routeTrackInfo.getMarkerPointsNum(), i, d, z);
        if (TrackAlgInit != 0) {
            ReleaseLogUtil.c("Track_FeatureExtractionImpl", "initCode code is ", Integer.valueOf(TrackAlgInit));
            return null;
        }
        int ProcessTrackInfo = trackFeatureExtraction.ProcessTrackInfo(routeTrackInfo);
        if (ProcessTrackInfo != 0) {
            ReleaseLogUtil.c("Track_FeatureExtractionImpl", "getTrackOutputForApp processCode is ", Integer.valueOf(ProcessTrackInfo), " closeCode is ", Integer.valueOf(trackFeatureExtraction.TrackAlgClose()));
            return null;
        }
        RouteOutputForApp GetTrackResultForApp = trackFeatureExtraction.GetTrackResultForApp();
        int TrackAlgClose = trackFeatureExtraction.TrackAlgClose();
        Object[] objArr = new Object[8];
        objArr[0] = "init code is ";
        objArr[1] = Integer.valueOf(TrackAlgInit);
        objArr[2] = " processCode is ";
        objArr[3] = Integer.valueOf(ProcessTrackInfo);
        objArr[4] = " closeCode is ";
        objArr[5] = Integer.valueOf(TrackAlgClose);
        objArr[6] = " resultForApp is ";
        objArr[7] = Boolean.valueOf(GetTrackResultForApp == null);
        ReleaseLogUtil.e("Track_FeatureExtractionImpl", objArr);
        return GetTrackResultForApp;
    }

    @Override // com.huawei.health.algorithm.api.TrackFeatureExtractionApi
    public List<Point> featurePointExtraction(List<Point> list, int i) {
        int i2;
        int size = list.size();
        Point point = list.get(0);
        Point point2 = list.get(size - 1);
        if (Double.compare(point.getLongitude(), point2.getLongitude()) == 0 && Double.compare(point.getLatitude(), point2.getLatitude()) == 0) {
            size = list.size() - 1;
        }
        ArrayList arrayList = new ArrayList();
        double d = 0.0d;
        int i3 = 0;
        int i4 = 1;
        while (true) {
            i2 = size - 1;
            if (i4 >= i2) {
                break;
            }
            Point point3 = list.get(i4);
            double b = b(point3, list.get(0), list.get(i2));
            if (b > d) {
                i3 = i4;
                d = b;
            }
            if (point3.getCpInfo() != null) {
                arrayList.add(point3);
            }
            i4++;
        }
        ArrayList arrayList2 = new ArrayList();
        if (d > i) {
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            d(list, i3, arrayList3, arrayList4);
            List<Point> featurePointExtraction = featurePointExtraction(arrayList3, i);
            List<Point> featurePointExtraction2 = featurePointExtraction(arrayList4, i);
            featurePointExtraction2.remove(0);
            featurePointExtraction.addAll(featurePointExtraction2);
            return featurePointExtraction;
        }
        arrayList2.add(list.get(0));
        arrayList2.addAll(arrayList);
        arrayList2.add(list.get(i2));
        return arrayList2;
    }

    private double b(Point point, Point point2, Point point3) {
        double a2 = a(point2, point3);
        return (c(a(point, point2), a(point, point3), a2) * 2.0d) / a2;
    }

    public static double a(Point point, Point point2) {
        double latitude = point.getLatitude();
        double latitude2 = point2.getLatitude();
        double longitude = point.getLongitude();
        double longitude2 = point2.getLongitude();
        double radians = Math.toRadians(latitude2 - latitude);
        double radians2 = Math.toRadians(longitude2 - longitude);
        double d = radians / 2.0d;
        double d2 = radians2 / 2.0d;
        double sin = (Math.sin(d) * Math.sin(d)) + (Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(latitude2)) * Math.sin(d2) * Math.sin(d2));
        return Math.sqrt(Math.pow(Math.atan2(Math.sqrt(sin), Math.sqrt(1.0d - sin)) * 2.0d * 6371.0d * 1000.0d, 2.0d));
    }

    private static double c(double d, double d2, double d3) {
        double d4 = ((d + d2) + d3) / 2.0d;
        return Math.sqrt((d4 - d) * d4 * (d4 - d2) * (d4 - d3));
    }

    private void d(List<Point> list, int i, List<Point> list2, List<Point> list3) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 < i) {
                list2.add(list.get(i2));
            } else {
                if (i2 == i) {
                    list2.add(list.get(i2));
                }
                list3.add(list.get(i2));
            }
        }
    }
}
