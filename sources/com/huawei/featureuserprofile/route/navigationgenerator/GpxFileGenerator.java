package com.huawei.featureuserprofile.route.navigationgenerator;

import com.huawei.featureuserprofile.route.hwgpxmodel.Gpx;
import com.huawei.featureuserprofile.route.hwgpxmodel.Track;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackPoint;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.operation.ble.BleConstants;
import com.huawei.route.CpInfo;
import com.huawei.route.Point;
import com.huawei.route.TrackInfoModel;
import java.io.File;
import java.util.List;

/* loaded from: classes7.dex */
public class GpxFileGenerator extends BaseGenerator<Gpx> {
    private Gpx makeGpxObj(TrackInfoModel trackInfoModel) {
        List<Point> points = trackInfoModel.getPoints();
        TrackSegment trackSegment = new TrackSegment();
        Gpx gpx = new Gpx();
        String str = null;
        for (int i = 0; i < points.size(); i++) {
            TrackPoint trackPoint = new TrackPoint();
            Point point = points.get(i);
            trackPoint.setLatitude(point.getLatitude());
            trackPoint.setLongitude(point.getLongitude());
            trackPoint.setElevation(point.getAltitude());
            trackPoint.setTime(point.getTimeStamp());
            trackSegment.addTrackPoint(trackPoint);
            if (i == 0) {
                str = trackPoint.getStringTime();
            }
            if (point.getCpInfo() != null) {
                Wpt wpt = new Wpt();
                CpInfo cpInfo = point.getCpInfo();
                wpt.setLatitude(point.getLatitude());
                wpt.setLongitude(point.getLongitude());
                wpt.setTime(point.getTimeStamp());
                wpt.setElevation(point.getAltitude());
                wpt.setSym(cpInfo.getType());
                wpt.addExtension("color", Integer.valueOf(cpInfo.getColor()));
                wpt.addExtension(Wpt.MODE, Integer.valueOf(cpInfo.getMode()));
                wpt.setDesc(cpInfo.getDescription());
                wpt.setName(cpInfo.getName());
                gpx.addWpt(wpt);
            }
        }
        Track track = new Track();
        track.addTrackSegment(trackSegment);
        track.setType(trackInfoModel.getSportType());
        track.addExtension("totalTime", Double.valueOf(trackInfoModel.getSportTotalTime()));
        track.addExtension(BleConstants.TOTAL_DISTANCE, Double.valueOf(trackInfoModel.getSportTotalDistance()));
        track.addExtension("cumulativeClimb", Double.valueOf(trackInfoModel.getCumulativeClimb()));
        track.addExtension("cumulativeDecrease", Double.valueOf(trackInfoModel.getCumulativeDecrease()));
        track.addExtension("routeType", Integer.valueOf(trackInfoModel.getRouteType()));
        gpx.addMetadata("time", str);
        gpx.addTrack(track);
        return gpx;
    }

    public File makeGpxFile(TrackInfoModel trackInfoModel, String str) {
        return makeFile(makeGpxObj(trackInfoModel), str);
    }
}
