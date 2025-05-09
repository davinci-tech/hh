package defpackage;

import com.huawei.health.algorithm.api.TrackFeatureExtractionApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.route.CpInfo;
import com.huawei.route.Point;
import com.huawei.route.RouteType;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class sqm {
    public static <T> boolean a(List<T> list, int i, CpInfo cpInfo, T t) {
        return cpInfo != null && i == RouteType.EXP.routeType() && list.contains(t);
    }

    public static boolean e(int i, CpInfo cpInfo) {
        return cpInfo != null && i == RouteType.DEFAULT.routeType();
    }

    public static boolean a(double d, double d2) {
        return (Math.abs(d - d2) < 1.0E-6d && Math.abs(d2 * d) < 1.0E-6d) || d2 > 180.0d || d2 <= -180.0d || d <= -90.0d || d >= 90.0d;
    }

    public static List<Point> e(int i, List<Point> list) {
        if (list == null) {
            LogUtil.h("Track_RouteUtils", "trackPoints is null");
            return new ArrayList();
        }
        if (i != RouteType.DEFAULT.routeType() || list.size() <= 2000) {
            return list;
        }
        TrackFeatureExtractionApi trackFeatureExtractionApi = (TrackFeatureExtractionApi) Services.a("TrackFeatureExtractionAlgorithmService", TrackFeatureExtractionApi.class);
        if (trackFeatureExtractionApi == null) {
            LogUtil.h("Track_RouteUtils", "trackTrainApi is null");
        }
        return trackFeatureExtractionApi.featurePointExtraction(list, 2);
    }
}
