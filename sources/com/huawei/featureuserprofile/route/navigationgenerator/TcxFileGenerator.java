package com.huawei.featureuserprofile.route.navigationgenerator;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwtcxmodel.Activities;
import com.huawei.featureuserprofile.route.hwtcxmodel.Activity;
import com.huawei.featureuserprofile.route.hwtcxmodel.Lap;
import com.huawei.featureuserprofile.route.hwtcxmodel.Position;
import com.huawei.featureuserprofile.route.hwtcxmodel.Tcx;
import com.huawei.featureuserprofile.route.hwtcxmodel.Track;
import com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint;
import com.huawei.route.CpInfo;
import com.huawei.route.Point;
import com.huawei.route.TrackInfoModel;
import defpackage.buc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class TcxFileGenerator extends BaseGenerator<Tcx> {
    private Tcx makeTcxObj(TrackInfoModel trackInfoModel) {
        ArrayList arrayList = new ArrayList();
        List<Point> points = trackInfoModel.getPoints();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            TrackPoint trackPoint = new TrackPoint();
            trackPoint.setTime(point.getTimeStamp());
            trackPoint.setAltitudeMeters(point.getAltitude());
            Position position = new Position();
            position.setLongitudeDegrees(point.getLongitude());
            position.setLatitudeDegrees(point.getLatitude());
            CpInfo cpInfo = point.getCpInfo();
            if (cpInfo != null) {
                Extensions extensions = new Extensions();
                extensions.addExtension("PointIcon", Integer.valueOf(cpInfo.getType()));
                extensions.addExtension("PointColor", Integer.valueOf(cpInfo.getColor()));
                extensions.addExtension("PointMode", Integer.valueOf(cpInfo.getMode()));
                extensions.addExtension(TrackPoint.NAME, cpInfo.getName());
                extensions.addExtension(TrackPoint.DESC, cpInfo.getDescription());
                trackPoint.setExtensions(extensions);
            }
            trackPoint.setPosition(position);
            arrayList.add(trackPoint);
        }
        Extensions extensions2 = new Extensions();
        extensions2.addExtension(Lap.ROUTE_TYPE, Integer.valueOf(trackInfoModel.getRouteType()));
        Track track = new Track();
        track.setTrackPoint(arrayList);
        String longTimeToString = longTimeToString(points.get(0).getTimeStamp());
        Lap build = new Lap.Builder().setStartTime(longTimeToString).setTotalTimeSeconds(trackInfoModel.getSportTotalTime()).setDistanceMeters(trackInfoModel.getSportTotalDistance()).setCumulativeClimb(trackInfoModel.getCumulativeClimb()).setTrack(track).setCumulativeDecrease(trackInfoModel.getCumulativeDecrease()).setExtensions(extensions2).build();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(build);
        Activity activity = new Activity(longTimeToString, arrayList2, trackInfoModel.getSportType());
        Activities activities = new Activities();
        activities.setActivity(activity);
        Tcx tcx = new Tcx();
        tcx.setActivities(activities);
        return tcx;
    }

    private String longTimeToString(long j) {
        return buc.a(j);
    }

    public File makeTcxFile(TrackInfoModel trackInfoModel, String str) {
        return makeFile(makeTcxObj(trackInfoModel), str);
    }
}
