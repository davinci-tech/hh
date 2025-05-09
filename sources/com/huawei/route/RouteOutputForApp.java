package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.List;

/* loaded from: classes6.dex */
public class RouteOutputForApp {

    @SerializedName("caliPoints")
    private List<CorrectTrackPoint> caliPoints;

    @SerializedName("caliPointsNum")
    private int caliPointsNum;

    @SerializedName("isShowAltitude")
    private boolean isShowAltitude;

    @SerializedName("segmentInfo")
    private List<SegmentInfo> segmentInfo;

    @SerializedName("segmentNum")
    private int segmentNum;

    @SerializedName("totalAscent")
    private float totalAscent;

    @SerializedName(BleConstants.TOTAL_DESCENT)
    private float totalDescent;

    @SerializedName("totalDist")
    private float totalDist;

    @SerializedName("totalDist3D")
    private float totalDist3D;

    @SerializedName("trackType")
    private int trackType;

    public float getTotalDist() {
        return this.totalDist;
    }

    public void setTotalDist(float f) {
        this.totalDist = f;
    }

    public float getTotalDist3D() {
        return this.totalDist3D;
    }

    public void setTotalDist3D(float f) {
        this.totalDist3D = f;
    }

    public float getTotalAscent() {
        return this.totalAscent;
    }

    public void setTotalAscent(float f) {
        this.totalAscent = f;
    }

    public float getTotalDescent() {
        return this.totalDescent;
    }

    public void setTotalDescent(float f) {
        this.totalDescent = f;
    }

    public List<CorrectTrackPoint> getCaliPoints() {
        return this.caliPoints;
    }

    public void setCaliPoints(List<CorrectTrackPoint> list) {
        this.caliPoints = list;
    }

    public int getCaliPointsNum() {
        return this.caliPointsNum;
    }

    public void setCaliPointsNum(int i) {
        this.caliPointsNum = i;
    }

    public List<SegmentInfo> getSegmentInfo() {
        return this.segmentInfo;
    }

    public void setSegmentInfo(List<SegmentInfo> list) {
        this.segmentInfo = list;
    }

    public int getSegmentNum() {
        return this.segmentNum;
    }

    public void setSegmentNum(int i) {
        this.segmentNum = i;
    }

    public boolean getIsShowAltitude() {
        return this.isShowAltitude;
    }

    public void setIsShowAltitude(boolean z) {
        this.isShowAltitude = z;
    }

    public int getTrackType() {
        return this.trackType;
    }

    public void setTrackType(int i) {
        this.trackType = i;
    }

    public String toString() {
        return "RouteOutputForApp{totalDist=" + this.totalDist + ", totalDist3D=" + this.totalDist3D + ", totalAscent=" + this.totalAscent + ", totalDescent=" + this.totalDescent + ", caliPoints=" + this.caliPoints + ", caliPointsNum=" + this.caliPointsNum + ", segmentInfo=" + this.segmentInfo + ", segmentNum='" + this.segmentNum + "', isShowAltitude=" + this.isShowAltitude + ", trackType=" + this.trackType + '}';
    }
}
