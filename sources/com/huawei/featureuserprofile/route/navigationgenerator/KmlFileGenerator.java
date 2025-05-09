package com.huawei.featureuserprofile.route.navigationgenerator;

import com.huawei.featureuserprofile.route.hwkmlmodel.Data;
import com.huawei.featureuserprofile.route.hwkmlmodel.ExtendedData;
import com.huawei.featureuserprofile.route.hwkmlmodel.FolderFirst;
import com.huawei.featureuserprofile.route.hwkmlmodel.FolderSec;
import com.huawei.featureuserprofile.route.hwkmlmodel.Kml;
import com.huawei.featureuserprofile.route.hwkmlmodel.Placemark;
import com.huawei.featureuserprofile.route.hwkmlmodel.Point;
import com.huawei.featureuserprofile.route.hwkmlmodel.TimeSpan;
import com.huawei.operation.ble.BleConstants;
import com.huawei.route.CpInfo;
import com.huawei.route.Point;
import com.huawei.route.TrackInfoModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class KmlFileGenerator extends BaseGenerator<Kml> {
    private Kml makeKmlObj(TrackInfoModel trackInfoModel) {
        List<Point> points = trackInfoModel.getPoints();
        FolderSec folderSec = new FolderSec();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < points.size(); i++) {
            Point.Builder builder = new Point.Builder();
            com.huawei.route.Point point = points.get(i);
            builder.setLon(point.getLongitude()).setLat(point.getLatitude()).setEle(point.getAltitude());
            TimeSpan timeSpan = new TimeSpan();
            timeSpan.setBegin(point.getTimeStamp());
            Placemark placemark = new Placemark();
            placemark.setPoint(builder.build());
            placemark.setTimeSpan(timeSpan);
            CpInfo cpInfo = point.getCpInfo();
            if (cpInfo != null) {
                ExtendedData extendedData = new ExtendedData();
                ArrayList arrayList2 = new ArrayList();
                addDataTime(arrayList2, "PointColor", cpInfo.getColor());
                addDataTime(arrayList2, "PointIcon", cpInfo.getType());
                addDataTime(arrayList2, "PointMode", cpInfo.getMode());
                extendedData.setData(arrayList2);
                placemark.setExtendedData(extendedData);
                placemark.setLapName(cpInfo.getName());
                placemark.setDescription(cpInfo.getDescription());
            }
            arrayList.add(placemark);
        }
        folderSec.setPlaceMark(arrayList);
        ArrayList arrayList3 = new ArrayList();
        addDataTime(arrayList3, "totalTime", trackInfoModel.getSportTotalTime());
        addDataTime(arrayList3, BleConstants.TOTAL_DISTANCE, trackInfoModel.getSportTotalDistance());
        addDataTime(arrayList3, "cumulativeClimb", trackInfoModel.getCumulativeClimb());
        addDataTime(arrayList3, "cumulativeDecrease", trackInfoModel.getCumulativeDecrease());
        addDataTime(arrayList3, "routeType", trackInfoModel.getRouteType());
        ExtendedData extendedData2 = new ExtendedData();
        extendedData2.setData(arrayList3);
        FolderFirst folderFirst = new FolderFirst(trackInfoModel.getRouteName(), extendedData2, makeFolderSecInfo(trackInfoModel), folderSec);
        Kml kml = new Kml();
        kml.setFolderFirst(folderFirst);
        return kml;
    }

    private void addDataTime(List<Data> list, String str, double d) {
        list.add(new Data(str, Double.valueOf(d)));
    }

    private FolderSec makeFolderSecInfo(TrackInfoModel trackInfoModel) {
        FolderSec folderSec = new FolderSec();
        folderSec.setName("Laps");
        Placemark placemark = new Placemark();
        placemark.setLapName("Start");
        Point.Builder builder = new Point.Builder();
        builder.setCoordinates(trackInfoModel.getPoints().get(0).getLongitude() + "," + trackInfoModel.getPoints().get(0).getLatitude());
        placemark.setPoint(builder.build());
        Placemark placemark2 = new Placemark();
        placemark2.setLapName("End");
        Point.Builder builder2 = new Point.Builder();
        builder2.setCoordinates(trackInfoModel.getPoints().get(trackInfoModel.getPoints().size() - 1).getLongitude() + "," + trackInfoModel.getPoints().get(trackInfoModel.getPoints().size() - 1).getLatitude());
        placemark2.setPoint(builder2.build());
        ArrayList arrayList = new ArrayList();
        arrayList.add(placemark);
        arrayList.add(placemark2);
        folderSec.setPlaceMark(arrayList);
        return folderSec;
    }

    public File makeKmlFile(TrackInfoModel trackInfoModel, String str) {
        return makeFile(makeKmlObj(trackInfoModel), str);
    }
}
