package com.huawei.featureuserprofile.route.navigationparser;

import com.google.android.gms.common.util.CollectionUtils;
import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.Gpx;
import com.huawei.featureuserprofile.route.hwgpxmodel.Route;
import com.huawei.featureuserprofile.route.hwgpxmodel.RoutePoint;
import com.huawei.featureuserprofile.route.hwgpxmodel.Track;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackPoint;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.featureuserprofile.route.hwkmlmodel.Data;
import com.huawei.featureuserprofile.route.hwkmlmodel.Document;
import com.huawei.featureuserprofile.route.hwkmlmodel.ExtendedData;
import com.huawei.featureuserprofile.route.hwkmlmodel.FolderFirst;
import com.huawei.featureuserprofile.route.hwkmlmodel.FolderSec;
import com.huawei.featureuserprofile.route.hwkmlmodel.GxTrack;
import com.huawei.featureuserprofile.route.hwkmlmodel.Kml;
import com.huawei.featureuserprofile.route.hwkmlmodel.LineString;
import com.huawei.featureuserprofile.route.hwkmlmodel.Placemark;
import com.huawei.featureuserprofile.route.hwkmlmodel.TimeSpan;
import com.huawei.featureuserprofile.route.hwtcxmodel.Activities;
import com.huawei.featureuserprofile.route.hwtcxmodel.Activity;
import com.huawei.featureuserprofile.route.hwtcxmodel.Lap;
import com.huawei.featureuserprofile.route.hwtcxmodel.Position;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.route.CpInfo;
import com.huawei.route.Point;
import com.huawei.route.RouteType;
import defpackage.btp;
import defpackage.btt;
import defpackage.btu;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class NavigationFileParser {
    public static final String BIN_FILE_NAME = "byte.bin";
    private static final double DOUBLE_ZERO = 0.0d;
    private static final double EARTH_RADIUS = 6371.0d;
    public static final int FAIL = 0;
    public static final String GPX = "gpx";
    public static final String KML = "kml";
    private static final long LONG_ZERO = 0;
    private static final double ONE_IN_A_THOUSAND = 0.001d;
    private static final int ONE_MULTIPLES = 1;
    private static final int ONE_THOUSAND = 1000;
    public static final int SUCCESS = 1;
    private static final String TAG = "NavigationFileParser";
    public static final String TCX = "tcx";
    private static final int TWO_MULTIPLES = 2;
    private static final int TWO_THOUSAND = 2000;
    private static final double ZERO_THRESHOLD = 1.0E-6d;
    private double mCumulativeClimb;
    private double mCumulativeDecrease;
    private List<Point> mPoints;
    private double mSportTotalDistance;
    private double mSportTotalTime;
    private boolean isParsedSuccessful = true;
    private String mSportType = "";
    private int mRouteType = RouteType.DEFAULT.routeType();

    public double getCumulativeClimb() {
        return this.mCumulativeClimb;
    }

    public double getCumulativeDecrease() {
        return this.mCumulativeDecrease;
    }

    public int navigationParser(InputStream inputStream, String str) throws IOException, XmlPullParserException, ParseException {
        if (str.equalsIgnoreCase(GPX)) {
            return setGpxParser(inputStream);
        }
        if (str.equalsIgnoreCase(TCX)) {
            return setTcxParser(inputStream);
        }
        if (str.equalsIgnoreCase(KML)) {
            return setKmlParser(inputStream);
        }
        LogUtil.b(TAG, "Parsing of this format is not supported.");
        return 0;
    }

    public String getSportType() {
        return this.mSportType;
    }

    public int getRouteType() {
        return this.mRouteType;
    }

    public List<Point> getPoints() {
        return this.mPoints;
    }

    public double getSportTotalTime() {
        return this.mSportTotalTime;
    }

    public double getSportTotalDistance() {
        return this.mSportTotalDistance;
    }

    private int setGpxParser(InputStream inputStream) throws IOException, XmlPullParserException, ParseException {
        Gpx trackParseInput = new btp().trackParseInput(inputStream);
        List<Track> tracks = trackParseInput.getTracks();
        if (CollectionUtils.isEmpty(tracks)) {
            LogUtil.b(TAG, "tracks is null");
            return parseGpx(trackParseInput);
        }
        this.mPoints = new ArrayList();
        e eVar = new e();
        Iterator<Track> it = tracks.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            List<TrackSegment> assigningValuesFromTrack = assigningValuesFromTrack(it.next());
            if (CollectionUtils.isEmpty(assigningValuesFromTrack)) {
                Object[] objArr = new Object[1];
                objArr[i] = "trackSegments is null";
                LogUtil.b(TAG, objArr);
                return i;
            }
            Iterator<TrackSegment> it2 = assigningValuesFromTrack.iterator();
            int i3 = i2;
            while (it2.hasNext()) {
                List<TrackPoint> trackPoints = it2.next().getTrackPoints();
                if (!CollectionUtils.isEmpty(trackPoints)) {
                    int size = trackPoints.size();
                    int i4 = i;
                    while (i4 < trackPoints.size()) {
                        TrackPoint trackPoint = trackPoints.get(i4);
                        int i5 = i4;
                        List<TrackPoint> list = trackPoints;
                        addPoint(trackPoint.getLongitude(), trackPoint.getLatitude(), trackPoint.getElevation(), trackPoint.getTime(), getCpInfo(trackParseInput, new Wpt(trackPoint.getLatitude(), trackPoint.getLongitude(), trackPoint.getElevation(), trackPoint.getTime())));
                        if (i5 != 0) {
                            calAccumulateData(eVar, i5);
                        }
                        i4 = i5 + 1;
                        trackPoints = list;
                    }
                    i3 += size;
                    i = 0;
                }
            }
            i2 = i3;
        }
        assigningValues(eVar);
        int pointNum = getPointNum(trackParseInput, i2);
        this.mRouteType = trackParseInput.getRouteType();
        if (pointNum != 0) {
            return 1;
        }
        LogUtil.b(TAG, "setGpxParser point is null");
        return 0;
    }

    private CpInfo getCpInfo(Gpx gpx, Wpt wpt) {
        int indexOf;
        List<Wpt> wpts = gpx.getWpts();
        Wpt wpt2 = (CollectionUtils.isEmpty(wpts) || (indexOf = wpts.indexOf(new Wpt(wpt.getLatitude(), wpt.getLongitude(), wpt.getElevation(), wpt.getTime()))) == -1) ? null : wpts.get(indexOf);
        CpInfo cpInfo = getCpInfo(wpt2);
        if (cpInfo != null) {
            gpx.getWpts().remove(wpt2);
        }
        return cpInfo;
    }

    private CpInfo getCpInfo(Wpt wpt) {
        if (wpt == null) {
            return null;
        }
        CpInfo cpInfo = new CpInfo();
        cpInfo.setType(wpt.getSym());
        cpInfo.setColor(wpt.getIconColor());
        cpInfo.setName(wpt.getName());
        cpInfo.setDescription(wpt.getDesc());
        cpInfo.setMode(wpt.getMode());
        return cpInfo;
    }

    private int getPointNum(Gpx gpx, int i) {
        if (CollectionUtils.isEmpty(gpx.getWpts())) {
            return i;
        }
        for (Wpt wpt : gpx.getWpts()) {
            Point point = new Point();
            point.setLongitude(wpt.getLongitude());
            point.setLatitude(wpt.getLatitude());
            point.setAltitude(wpt.getElevation());
            point.setTimeStamp(wpt.getTime());
            CpInfo cpInfo = new CpInfo();
            cpInfo.setType(wpt.getSym());
            cpInfo.setColor(wpt.getIconColor());
            cpInfo.setName(wpt.getName());
            cpInfo.setDescription(wpt.getDesc());
            cpInfo.setMode(wpt.getMode());
            point.setCpInfo(cpInfo);
            this.mPoints.add(point);
            i++;
        }
        return i;
    }

    private int parseGpx(Gpx gpx) {
        List<Route> route = gpx.getRoute();
        int i = 0;
        if (CollectionUtils.isEmpty(route)) {
            LogUtil.b(TAG, "route is null");
            return 0;
        }
        this.mPoints = new ArrayList();
        e eVar = new e();
        Iterator<Route> it = route.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            List<RoutePoint> routePoint = it.next().getRoutePoint();
            if (CollectionUtils.isEmpty(routePoint)) {
                LogUtil.b(TAG, "trackPoints is null");
                return i;
            }
            int i3 = i;
            while (i3 < routePoint.size()) {
                RoutePoint routePoint2 = routePoint.get(i3);
                List<RoutePoint> list = routePoint;
                int i4 = i3;
                Iterator<Route> it2 = it;
                int i5 = i2;
                addPoint(routePoint2.getLongitude(), routePoint2.getLatitude(), routePoint2.getElevation(), routePoint2.getTime(), getCpInfo(gpx, new Wpt(routePoint2.getLatitude(), routePoint2.getLongitude(), routePoint2.getElevation(), routePoint2.getTime())));
                if (i4 != 0) {
                    calAccumulateData(eVar, i4);
                }
                i3 = i4 + 1;
                i2 = i5;
                it = it2;
                routePoint = list;
            }
            i2 += routePoint.size();
            it = it;
            i = 0;
        }
        assigningValues(eVar);
        int pointNum = getPointNum(gpx, i2);
        this.mRouteType = gpx.getRouteType();
        if (pointNum != 0) {
            return 1;
        }
        LogUtil.b(TAG, "parseGpx point is null");
        return 0;
    }

    private List<TrackSegment> assigningValuesFromTrack(Track track) {
        this.mSportType = track.getType() != null ? track.getType() : "";
        Extensions extensions = track.getExtensions();
        if (extensions != null) {
            Map<String, Object> extensionsMap = extensions.getExtensionsMap();
            if (extensionsMap != null) {
                Object obj = extensionsMap.get("totalTime");
                if (obj instanceof Double) {
                    this.mSportTotalTime = ((Double) obj).doubleValue();
                }
                Object obj2 = extensionsMap.get(BleConstants.TOTAL_DISTANCE);
                if (obj2 instanceof Double) {
                    this.mSportTotalDistance = ((Double) obj2).doubleValue();
                }
                Object obj3 = extensionsMap.get("cumulativeClimb");
                if (obj3 instanceof Double) {
                    this.mCumulativeClimb = ((Double) obj3).doubleValue();
                }
                Object obj4 = extensionsMap.get("cumulativeDecrease");
                if (obj4 instanceof Double) {
                    this.mCumulativeDecrease = ((Double) obj4).doubleValue();
                }
                Object obj5 = extensionsMap.get("routeType");
                if (obj5 instanceof Integer) {
                    this.mRouteType = ((Integer) obj5).intValue();
                }
            } else {
                LogUtil.b(TAG, "extensionsMap is null");
            }
        }
        return track.getTrackSegments();
    }

    private int setTcxParser(InputStream inputStream) throws IOException, XmlPullParserException, ParseException {
        Activities activities = new btu().trackParseInput(inputStream).getActivities();
        int i = 0;
        if (activities == null) {
            LogUtil.b(TAG, "activities is null");
            return 0;
        }
        Activity activity = activities.getActivity();
        if (activity == null) {
            LogUtil.b(TAG, "activity is null");
            return 0;
        }
        this.mSportType = activity.getSport() != null ? activity.getSport() : "";
        List<Lap> lap = activity.getLap();
        if (CollectionUtils.isEmpty(lap)) {
            LogUtil.b(TAG, "laps is null");
            return 0;
        }
        e eVar = new e();
        this.mPoints = new ArrayList();
        for (Lap lap2 : lap) {
            List<com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint> trackPointsFromLap = getTrackPointsFromLap(lap2);
            this.mRouteType = lap2.getRouteType();
            com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint trackPoint = trackPointsFromLap.get(i);
            Position position = trackPoint.getPosition();
            if (position == null) {
                Object[] objArr = new Object[1];
                objArr[i] = "positionFirst is null";
                LogUtil.b(TAG, objArr);
                return i;
            }
            List<com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint> list = trackPointsFromLap;
            addPoint(position.getLongitudeDegrees(), position.getLatitudeDegrees(), trackPoint.getAltitudeMeters(), trackPoint.getTime(), getCpInfo(trackPoint));
            int i2 = 1;
            while (i2 < list.size()) {
                List<com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint> list2 = list;
                com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint trackPoint2 = list2.get(i2);
                Position position2 = trackPoint2.getPosition();
                if (position2 == null) {
                    Object[] objArr2 = new Object[1];
                    objArr2[i] = "position is null";
                    LogUtil.b(TAG, objArr2);
                    return i;
                }
                int i3 = i2;
                addPoint(position2.getLongitudeDegrees(), position2.getLatitudeDegrees(), trackPoint2.getAltitudeMeters(), trackPoint2.getTime(), getCpInfo(trackPoint2));
                calAccumulateData(eVar, i3);
                i2 = i3 + 1;
                list = list2;
                i = 0;
            }
        }
        assigningValues(eVar);
        return 1;
    }

    private CpInfo getCpInfo(com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint trackPoint) {
        Extensions extensions = trackPoint.getExtensions();
        if (extensions == null || com.huawei.haf.common.utils.CollectionUtils.e(extensions.getExtensionsMap())) {
            return null;
        }
        CpInfo cpInfo = new CpInfo();
        cpInfo.setMode(Extensions.getInt(extensions.getExtensionsMap(), "PointMode", 1));
        cpInfo.setColor(Extensions.getInt(extensions.getExtensionsMap(), "PointColor", MarkPoint.PointColor.AUTO.color()));
        cpInfo.setType(Extensions.getInt(extensions.getExtensionsMap(), "PointIcon", MarkPoint.MarkType.COMMON.type()));
        cpInfo.setName(Extensions.getString(extensions.getExtensionsMap(), com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint.NAME, ""));
        cpInfo.setDescription(Extensions.getString(extensions.getExtensionsMap(), com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint.DESC, ""));
        return cpInfo;
    }

    private List<com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint> getTrackPointsFromLap(Lap lap) {
        this.mSportTotalTime += lap.getTotalTimeSeconds();
        this.mSportTotalDistance += lap.getDistanceMeters();
        this.mCumulativeClimb += lap.getCumulativeClimb();
        this.mCumulativeDecrease += lap.getCumulativeDecrease();
        return lap.getTrack().getTrackPoint();
    }

    private int setKmlParser(InputStream inputStream) throws IOException, XmlPullParserException, ParseException {
        Kml trackParseInput = new btt().trackParseInput(inputStream);
        FolderFirst folderFirst = trackParseInput.getFolderFirst();
        if (folderFirst == null) {
            LogUtil.b(TAG, "folderFirst is null");
            return setDocumentParser(trackParseInput);
        }
        FolderSec folderSec = assignValuesFromFolderFirst(folderFirst).getFolderSec();
        if (folderSec == null) {
            LogUtil.b(TAG, "folderSec is null");
            return 0;
        }
        List<Placemark> placeMark = folderSec.getPlaceMark();
        if (CollectionUtils.isEmpty(placeMark)) {
            LogUtil.b(TAG, "placeMarks is null");
            return 0;
        }
        e eVar = new e();
        this.mPoints = new ArrayList();
        processKmlPoint(placeMark, eVar);
        assigningValues(eVar);
        return 1;
    }

    private CpInfo getCpInfo(Placemark placemark) {
        ExtendedData extendedData = placemark.getExtendedData();
        if (extendedData == null || CollectionUtils.isEmpty(extendedData.getData())) {
            return null;
        }
        CpInfo cpInfo = new CpInfo();
        for (Data data : extendedData.getData()) {
            double doubleValue = data.getValue().doubleValue();
            if ("PointMode".equals(data.getName())) {
                cpInfo.setMode((int) doubleValue);
            }
            if ("PointColor".equals(data.getName())) {
                cpInfo.setColor((int) doubleValue);
            }
            if ("PointIcon".equals(data.getName())) {
                cpInfo.setType((int) doubleValue);
            }
        }
        cpInfo.setName(placemark.getLapName());
        cpInfo.setDescription(placemark.getDescription());
        return cpInfo;
    }

    private void processKmlPoint(List<Placemark> list, e eVar) {
        TimeSpan timeSpan = list.get(0).getTimeSpan();
        if (timeSpan != null) {
            com.huawei.featureuserprofile.route.hwkmlmodel.Point point = list.get(0).getPoint();
            addPoint(point.getLon(), point.getLat(), point.getEle(), timeSpan.getBegin(), getCpInfo(list.get(0)));
        }
        for (int i = 1; i < list.size(); i++) {
            TimeSpan timeSpan2 = list.get(i).getTimeSpan();
            if (timeSpan2 != null) {
                com.huawei.featureuserprofile.route.hwkmlmodel.Point point2 = list.get(i).getPoint();
                addPoint(point2.getLon(), point2.getLat(), point2.getEle(), timeSpan2.getBegin(), getCpInfo(list.get(i)));
                calAccumulateData(eVar, i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private FolderFirst assignValuesFromFolderFirst(FolderFirst folderFirst) {
        char c;
        ExtendedData extendedData = folderFirst.getExtendedData();
        if (extendedData == null) {
            LogUtil.a(TAG, "extendedData is null");
            return folderFirst;
        }
        for (Data data : extendedData.getData()) {
            String name = data.getName();
            double doubleValue = data.getValue().doubleValue();
            name.hashCode();
            switch (name.hashCode()) {
                case -577281999:
                    if (name.equals("totalTime")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 167668003:
                    if (name.equals("routeType")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 314918745:
                    if (name.equals(BleConstants.TOTAL_DISTANCE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1228549506:
                    if (name.equals("cumulativeClimb")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1960284849:
                    if (name.equals("cumulativeDecrease")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                this.mSportTotalTime = doubleValue;
            } else if (c == 1) {
                this.mRouteType = (int) doubleValue;
            } else if (c == 2) {
                this.mSportTotalDistance = doubleValue;
            } else if (c == 3) {
                this.mCumulativeClimb = doubleValue;
            } else if (c == 4) {
                this.mCumulativeDecrease = doubleValue;
            } else {
                LogUtil.a(TAG, "no one match");
            }
        }
        return folderFirst;
    }

    private int setDocumentParser(Kml kml) {
        Document document = kml.getDocument();
        if (document == null) {
            LogUtil.b(TAG, "document is null");
            return 0;
        }
        List<FolderSec> folderSec = document.getFolderSec();
        if (CollectionUtils.isEmpty(folderSec)) {
            LogUtil.b(TAG, "folderSec is null");
            return 0;
        }
        e eVar = new e();
        this.mPoints = new ArrayList();
        Iterator<FolderSec> it = folderSec.iterator();
        while (it.hasNext()) {
            List<Placemark> placeMark = it.next().getPlaceMark();
            if (CollectionUtils.isEmpty(placeMark)) {
                LogUtil.b(TAG, "placeMarks is null");
                return 0;
            }
            for (Placemark placemark : placeMark) {
                LineString lineString = placemark.getLineString();
                GxTrack gxTrack = placemark.getGxTrack();
                if (lineString != null) {
                    lineString.getCoordinates();
                    if (CollectionUtils.isEmpty(lineString.getLineStringPointList())) {
                        LogUtil.b(TAG, "lineStringPointList is null");
                        return 0;
                    }
                    setAccumulate(eVar, lineString, placemark);
                } else if (gxTrack != null) {
                    gxTrack.getGxCoordinates();
                    if (CollectionUtils.isEmpty(gxTrack.getGxTrackPoints())) {
                        LogUtil.b(TAG, "gxTrackPoints is null");
                        return 0;
                    }
                    setAccumulate(eVar, gxTrack, placemark);
                } else {
                    processKmlPoint(placeMark, eVar);
                }
            }
        }
        assigningValues(eVar);
        return 1;
    }

    private void setAccumulate(e eVar, LineString lineString, Placemark placemark) {
        LineString.LineStringPoint lineStringPoint = lineString.getLineStringPointList().get(0);
        addPoint(lineStringPoint.getLon(), lineStringPoint.getLat(), lineStringPoint.getEle(), getCpInfo(placemark));
        for (int i = 1; i < lineString.getLineStringPointList().size(); i++) {
            LineString.LineStringPoint lineStringPoint2 = lineString.getLineStringPointList().get(i);
            addPoint(lineStringPoint2.getLon(), lineStringPoint2.getLat(), lineStringPoint2.getEle(), getCpInfo(placemark));
            eVar.d += twoPointDistance(this.mPoints.get(i - 1), this.mPoints.get(i));
        }
    }

    private void setAccumulate(e eVar, GxTrack gxTrack, Placemark placemark) {
        GxTrack.GxTrackPoint gxTrackPoint = gxTrack.getGxTrackPoints().get(0);
        addPoint(gxTrackPoint.getLon(), gxTrackPoint.getLat(), gxTrackPoint.getEle(), gxTrackPoint.getTime(), getCpInfo(placemark));
        for (int i = 1; i < gxTrack.getGxTrackPoints().size(); i++) {
            GxTrack.GxTrackPoint gxTrackPoint2 = gxTrack.getGxTrackPoints().get(i);
            addPoint(gxTrackPoint2.getLon(), gxTrackPoint2.getLat(), gxTrackPoint2.getEle(), gxTrackPoint2.getTime(), getCpInfo(placemark));
            int i2 = i - 1;
            eVar.d += twoPointDistance(this.mPoints.get(i2), this.mPoints.get(i));
            eVar.e += twoPointTime(this.mPoints.get(i2), this.mPoints.get(i));
        }
    }

    public static double twoPointDistance(Point point, Point point2) {
        double latitude = point.getLatitude();
        double latitude2 = point2.getLatitude();
        double longitude = point.getLongitude();
        double longitude2 = point2.getLongitude();
        double radians = Math.toRadians(latitude2 - latitude);
        double radians2 = Math.toRadians(longitude2 - longitude);
        double d = radians / 2.0d;
        double d2 = radians2 / 2.0d;
        double sin = (Math.sin(d) * Math.sin(d)) + (Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(latitude2)) * Math.sin(d2) * Math.sin(d2));
        return Math.sqrt(Math.pow(Math.atan2(Math.sqrt(sin), Math.sqrt(1.0d - sin)) * 2.0d * EARTH_RADIUS * 1000.0d, 2.0d));
    }

    public long twoPointTime(Point point, Point point2) {
        if (point.getTimeStamp() == 0 || point2.getTimeStamp() == 0) {
            return 0L;
        }
        if (point.getTimeStamp() > point2.getTimeStamp()) {
            this.isParsedSuccessful = false;
            return 0L;
        }
        return point2.getTimeStamp() - point.getTimeStamp();
    }

    private void assigningValues(e eVar) {
        if (this.mSportTotalDistance == 0.0d) {
            this.mSportTotalDistance = eVar.d;
        }
        if (this.mSportTotalTime == 0.0d) {
            this.mSportTotalTime = this.isParsedSuccessful ? ONE_IN_A_THOUSAND * eVar.e : 0.0d;
        }
    }

    private void calAccumulateData(e eVar, int i) {
        int i2 = i - 1;
        eVar.d += twoPointDistance(this.mPoints.get(i2), this.mPoints.get(i));
        eVar.e += twoPointTime(this.mPoints.get(i2), this.mPoints.get(i));
    }

    private void addPoint(double d, double d2, double d3, long j, CpInfo cpInfo) {
        Point.Builder builder = new Point.Builder();
        builder.setLongitude(d).setLatitude(d2).setAltitude(d3).setTimeStamp(j);
        if (cpInfo != null) {
            builder.setCpInfo(cpInfo);
        }
        this.mPoints.add(builder.build());
    }

    private void addPoint(double d, double d2, double d3, CpInfo cpInfo) {
        Point.Builder builder = new Point.Builder();
        builder.setLongitude(d).setLatitude(d2).setAltitude(d3);
        if (cpInfo != null) {
            builder.setCpInfo(cpInfo);
        }
        this.mPoints.add(builder.build());
    }

    static class e {
        double d;
        long e;

        private e() {
            this.d = 0.0d;
            this.e = 0L;
        }
    }
}
