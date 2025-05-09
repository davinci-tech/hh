package defpackage;

import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwcloudmodel.model.unite.RouteExtendData;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.route.CpInfo;
import com.huawei.route.Point;
import com.huawei.route.RouteType;
import com.huawei.route.TrackInfoModel;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class hju {
    public static boolean e(int i) {
        return i == 258 || i == 259 || i == 257 || i == 260 || i == 282 || i == 280 || i == 219 || i == 222;
    }

    public static void a(boolean z, hjw hjwVar) {
        if (hjwVar == null) {
            LogUtil.a("Track_RouteExportUtils", "buildTrackDataAndJump dataManager == null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (hjwVar.e().requestSportType() == 222) {
            b(arrayList, hjwVar);
        } else {
            c(arrayList, hjwVar);
        }
        if (!koq.b(arrayList)) {
            c(z, arrayList, hjwVar);
        } else {
            LogUtil.a("Track_RouteExportUtils", "points is empty");
        }
    }

    private static void b(ArrayList<Point> arrayList, hjw hjwVar) {
        ArrayList<MarkPoint> requestMarkPointList = hjwVar.d().requestMarkPointList();
        ArrayList<knz> requestAltitudeList = hjwVar.d().requestAltitudeList();
        int size = requestMarkPointList.size();
        LogUtil.a("Track_RouteExportUtils", "buildExpPoints markPoints is ", Integer.valueOf(size), " altitudeList is", Integer.valueOf(requestAltitudeList.size()));
        if (koq.b(requestMarkPointList) || koq.b(requestAltitudeList)) {
            return;
        }
        Iterator<knz> it = requestAltitudeList.iterator();
        int i = 0;
        while (it.hasNext()) {
            knz next = it.next();
            if (size > i && requestMarkPointList.get(i).acquireTime() <= next.acquireTime()) {
                Point point = new Point();
                MarkPoint markPoint = requestMarkPointList.get(i);
                e(size, i, markPoint);
                e(markPoint, point);
                point.setAltitude(next.c());
                point.setTimeStamp(next.acquireTime());
                arrayList.add(point);
                i++;
            }
        }
        while (i < size) {
            Point point2 = new Point();
            MarkPoint markPoint2 = requestMarkPointList.get(i);
            e(size, i, markPoint2);
            e(markPoint2, point2);
            point2.setAltitude(requestAltitudeList.get(requestAltitudeList.size() - 1).c());
            arrayList.add(point2);
            i++;
        }
    }

    private static void e(int i, int i2, MarkPoint markPoint) {
        boolean z = markPoint.c() == 0;
        if (i2 == 0 && z) {
            markPoint.b(1);
            markPoint.c(MarkPoint.MarkType.END.type());
        }
        if (i2 == i - 1 && z) {
            markPoint.b(1);
            markPoint.c(MarkPoint.MarkType.END.type());
        }
    }

    private static void e(MarkPoint markPoint, Point point) {
        point.setLatitude(markPoint.d());
        point.setLongitude(markPoint.a());
        point.setTimeStamp(markPoint.acquireTime());
        if (markPoint.c() == 1) {
            CpInfo cpInfo = new CpInfo();
            cpInfo.setColor(markPoint.e());
            cpInfo.setMode(markPoint.c());
            cpInfo.setType(markPoint.j());
            cpInfo.setName("");
            cpInfo.setDescription("");
            point.setCpInfo(cpInfo);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void c(java.util.ArrayList<com.huawei.route.Point> r26, defpackage.hjw r27) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hju.c(java.util.ArrayList, hjw):void");
    }

    private static void c(boolean z, ArrayList<Point> arrayList, hjw hjwVar) {
        String e = gwg.e(BaseApplication.e(), hjwVar.e().requestSportType());
        LogUtil.a("Track_RouteExportUtils", "jumpRouteExport sportType=", e);
        String a2 = ggl.a(new Date(hjwVar.e().requestStartTime()), "yyyyMMdd");
        long totalTime = hjwVar.j().getTotalTime() / 1000;
        boolean z2 = hjwVar.e().requestSportType() == 222;
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        float requestCreepingWave = hjwVar.e().requestCreepingWave() / 10.0f;
        float requestTotalDescent = hjwVar.e().requestTotalDescent() / 10.0f;
        int routeType = (z2 ? RouteType.EXP : RouteType.DEFAULT).routeType();
        if (z) {
            LogUtil.a("Track_RouteExportUtils", "choose save to my route");
            RouteData routeData = new RouteData();
            routeData.setRouteName(a2 + e);
            routeData.setSportType(e);
            routeData.setRouteTime((double) totalTime);
            routeData.setRouteDistance((double) hjwVar.j().getTotalDistance());
            routeData.setExtendData(a(requestCreepingWave, requestTotalDescent, routeType));
            routeData.setRoutePoints(arrayList);
            routeData.setType(2);
            userProfileMgrApi.setRouteData(routeData);
            return;
        }
        LogUtil.a("Track_RouteExportUtils", "choose export route");
        userProfileMgrApi.setTrackInfoModel(new TrackInfoModel.Builder().routeName(a2 + e).sportType(e).sportTotalTime(totalTime).sportTotalDistance(hjwVar.j().getTotalDistance()).cumulativeClimb(requestCreepingWave).cumulativeDecrease(requestTotalDescent).points(arrayList).routeType(routeType).build());
    }

    private static String a(float f, float f2, int i) {
        RouteExtendData routeExtendData = new RouteExtendData();
        routeExtendData.setCumulativeClimb(f);
        routeExtendData.setCumulativeDecrease(f2);
        routeExtendData.setRouteType(i);
        return new Gson().toJson(routeExtendData);
    }
}
