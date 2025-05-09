package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.knz;
import defpackage.sqm;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RouteTrackInfo {

    @SerializedName("isShowAltitude")
    private boolean isShowAltitude;
    private transient double mMaxAltitude;
    private transient double mMinAltitude;

    @SerializedName("markerPoints")
    private List<RouteMarkerPoint> markerPoints;

    @SerializedName("markerPointsNum")
    private int markerPointsNum;

    @SerializedName("points")
    private List<RouteTrackPoint> points;

    @SerializedName("pointsNum")
    private long pointsNum;

    @SerializedName("targetNum")
    private int targetNum;

    @SerializedName("totalTime")
    private long totalTime;

    @SerializedName("trackId")
    private String trackId;

    @SerializedName("trackName")
    private String trackName;

    @SerializedName("trackType")
    private int trackType;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)
    private int workoutType;

    @SerializedName("version")
    private int version = 2;
    private transient List<knz> mAltitudeList = new ArrayList();

    public List<RouteTrackPoint> getPoints() {
        return this.points;
    }

    public void setPoints(List<RouteTrackPoint> list) {
        this.points = list;
    }

    public long getPointsNum() {
        return this.pointsNum;
    }

    public void setPointsNum(long j) {
        this.pointsNum = j;
    }

    public List<RouteMarkerPoint> getMarkerPoints() {
        return this.markerPoints;
    }

    public void setMarkerPoints(List<RouteMarkerPoint> list) {
        this.markerPoints = list;
    }

    public int getMarkerPointsNum() {
        return this.markerPointsNum;
    }

    public void setMarkerPointsNum(int i) {
        this.markerPointsNum = i;
    }

    public boolean isShowAltitude() {
        return this.isShowAltitude;
    }

    public void setShowAltitude(boolean z) {
        this.isShowAltitude = z;
    }

    public int getTrackType() {
        return this.trackType;
    }

    public void setTrackType(int i) {
        this.trackType = i;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public void setTrackName(String str) {
        this.trackName = str;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long j) {
        this.totalTime = j;
    }

    public int getTargetNum() {
        return this.targetNum;
    }

    public void setTargetNum(int i) {
        this.targetNum = i;
    }

    public int getWorkoutType() {
        return this.workoutType;
    }

    public void setWorkoutType(int i) {
        this.workoutType = i;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public List<knz> getAltitudeList() {
        return this.mAltitudeList;
    }

    public void setAltitudeList(List<knz> list) {
        this.mAltitudeList = list;
    }

    public double getMinAltitude() {
        return this.mMinAltitude;
    }

    public void setMinAltitude(double d) {
        this.mMinAltitude = d;
    }

    public double getMaxAltitude() {
        return this.mMaxAltitude;
    }

    public void setMaxAltitude(double d) {
        this.mMaxAltitude = d;
    }

    public String toString() {
        return "RouteTrackInfo{points=" + this.points + ", pointsNum=" + this.pointsNum + ", markerPoints=" + this.markerPoints + ", markerPointsNum=" + this.markerPointsNum + ", isShowAltitude=" + this.isShowAltitude + ", trackType=" + this.trackType + ", trackId='" + this.trackId + "', trackName='" + this.trackName + "', totalTime=" + this.totalTime + ", targetNum=" + this.targetNum + ", workoutType=" + this.workoutType + ", version=" + this.version + '}';
    }

    public static RouteTrackInfo convertRouteTrackInfo(RouteData routeData) {
        RouteTrackInfo routeTrackInfo = new RouteTrackInfo();
        routeTrackInfo.setTrackName(routeData.getRouteName());
        routeTrackInfo.setTrackType(routeData.getRouteType());
        routeTrackInfo.setTotalTime((int) routeData.getRouteTime());
        routeTrackInfo.setWorkoutType(0);
        convertRoutePoints(routeData, routeTrackInfo);
        routeTrackInfo.setShowAltitude(routeData.getRouteType() == RouteType.DEFAULT.routeType());
        return routeTrackInfo;
    }

    private static void convertRoutePoints(RouteData routeData, RouteTrackInfo routeTrackInfo) {
        if (CollectionUtils.d(routeData.getRoutePoints())) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int routeType = routeData.getRouteType();
        int i = 0;
        for (Point point : routeData.getRoutePoints()) {
            if (!sqm.a(point.getLatitude(), point.getLongitude())) {
                i++;
                CpInfo cpInfo = point.getCpInfo();
                if (cpInfo != null) {
                    arrayList2.add(RouteMarkerPoint.convertRouteMarkerPoint(i, point, cpInfo));
                }
                if (!sqm.e(routeType, cpInfo)) {
                    routeTrackInfo.updateAltitude(point);
                    RouteTrackPoint convertRouteTrackPoint = RouteTrackPoint.convertRouteTrackPoint(point);
                    if (!sqm.a(arrayList, routeType, cpInfo, convertRouteTrackPoint)) {
                        arrayList.add(convertRouteTrackPoint);
                    }
                }
            }
        }
        routeTrackInfo.setPoints(arrayList);
        routeTrackInfo.setPointsNum(arrayList.size());
        routeTrackInfo.setMarkerPoints(arrayList2);
        routeTrackInfo.setMarkerPointsNum(arrayList2.size());
    }

    private void updateAltitude(Point point) {
        this.mAltitudeList.add(new knz(point.getTimeStamp(), point.getAltitude()));
        if (Double.compare(this.mMaxAltitude, 0.0d) == 0 || point.getAltitude() > this.mMaxAltitude) {
            this.mMaxAltitude = point.getAltitude();
        }
        if (Double.compare(this.mMinAltitude, 0.0d) == 0 || point.getAltitude() < this.mMinAltitude) {
            this.mMinAltitude = point.getAltitude();
        }
    }
}
