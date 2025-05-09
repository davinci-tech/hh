package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import health.compact.a.HiDateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class MotionPathDetail implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;

    @SerializedName("abnormalTrack")
    private Integer abnormalTrack;

    @SerializedName("attribute")
    private String attribute;

    @SerializedName("coordinate")
    private String coordinate;

    @SerializedName("dataId")
    private long dataId;

    @SerializedName("deviceCode")
    private Long deviceCode;

    @SerializedName("duplicateTrack")
    private Integer duplicateTrack;

    @SerializedName("endTime")
    private Long endTime;

    @SerializedName("heartRateList")
    private List<MotionPathHeartRate> heartRateList;

    @SerializedName("location")
    private List<Location> location;

    @SerializedName("paceMap")
    private Map<String, Float> paceMap;

    @SerializedName("partTimeMap")
    private Map<Double, Double> partTimeMap;

    @SerializedName("pathImageUri")
    private String pathImageUri;

    @SerializedName("recordId")
    private String recordId;

    @SerializedName("runState")
    private Integer runState;

    @SerializedName("samplePoints")
    private List<SamplePoint> samplePoints;

    @SerializedName("sportDataSource")
    private Integer sportDataSource;

    @SerializedName(BleConstants.SPORT_TYPE)
    private Integer sportType;

    @SerializedName("startTime")
    private Long startTime;

    @SerializedName("timeZone")
    private String timeZone;

    @SerializedName(BleConstants.TOTAL_CALORIES)
    private Integer totalCalories;

    @SerializedName(BleConstants.TOTAL_DISTANCE)
    private Integer totalDistance;

    @SerializedName("totalSteps")
    private Integer totalSteps;

    @SerializedName("totalTime")
    private Long totalTime;

    @SerializedName("vendor")
    private String vendor;

    @SerializedName("version")
    private Long version;

    public long getDataId() {
        return this.dataId;
    }

    public void setDataId(long j) {
        this.dataId = j;
    }

    public int getDuplicateTrack() {
        return this.duplicateTrack.intValue();
    }

    public void setDuplicateTrack(Integer num) {
        this.duplicateTrack = num;
    }

    public Integer getAbnormalTrack() {
        return this.abnormalTrack;
    }

    public void setAbnormalTrack(Integer num) {
        this.abnormalTrack = num;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public List<SamplePoint> getSamplePoints() {
        return this.samplePoints;
    }

    public void setSamplePoints(List<SamplePoint> list) {
        this.samplePoints = list;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String str) {
        this.recordId = str;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public Integer getTotalSteps() {
        return this.totalSteps;
    }

    public void setTotalSteps(Integer num) {
        this.totalSteps = num;
    }

    public Integer getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(Integer num) {
        this.totalDistance = num;
    }

    public Integer getTotalCalories() {
        return this.totalCalories;
    }

    public void setTotalCalories(Integer num) {
        this.totalCalories = num;
    }

    public Long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(Long l) {
        this.totalTime = l;
    }

    public String getPathImageUri() {
        return this.pathImageUri;
    }

    public void setPathImageUri(String str) {
        this.pathImageUri = str;
    }

    public List<MotionPathHeartRate> getHeartRateList() {
        return this.heartRateList;
    }

    public void setHeartRateList(List<MotionPathHeartRate> list) {
        this.heartRateList = list;
    }

    public Integer getSportType() {
        return this.sportType;
    }

    public void setSportType(Integer num) {
        this.sportType = num;
    }

    public Integer getRunState() {
        return this.runState;
    }

    public void setRunState(Integer num) {
        this.runState = num;
    }

    public Map<String, Float> getPaceMap() {
        return this.paceMap;
    }

    public void setPaceMap(Map<String, Float> map) {
        this.paceMap = map;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String str) {
        this.vendor = str;
    }

    public String getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(String str) {
        this.coordinate = str;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String str) {
        this.attribute = str;
    }

    public List<Location> getLocation() {
        return this.location;
    }

    public void setLocation(List<Location> list) {
        this.location = list;
    }

    public Map<Double, Double> getPartTimeMap() {
        return this.partTimeMap;
    }

    public void setPartTimeMap(Map<Double, Double> map) {
        this.partTimeMap = map;
    }

    public void setSportDataSource(int i) {
        this.sportDataSource = Integer.valueOf(i);
    }

    public Integer getSportDataSource() {
        return this.sportDataSource;
    }

    public String toString() {
        return "MotionPathDetail [dataId=" + this.dataId + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", deviceCode=" + this.deviceCode + ", recordId=" + this.recordId + ", timeZone=" + this.timeZone + ", totalSteps=" + this.totalSteps + ", totalDistance=" + this.totalDistance + ", totalCalories=" + this.totalCalories + ", totalTime=" + this.totalTime + ", pathImageUri=" + this.pathImageUri + ", heartRateList=" + this.heartRateList + ", sportType=" + this.sportType + ", runState=" + this.runState + ", paceMap=" + this.paceMap + ", vendor=" + this.vendor + ", coordinate=" + this.coordinate + ", attribute=" + this.attribute + ", location=" + this.location + ", partTimeMap=" + this.partTimeMap + ", version=" + this.version + ", abnormalTrack=" + this.abnormalTrack + "]";
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public MotionPathDetail m646clone() {
        try {
            return (MotionPathDetail) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public String getMotionPathKeyInfo() {
        return "MotionPathInfo: start = " + this.startTime + Constants.LEFT_BRACKET_ONLY + HiDateUtil.m(this.startTime.longValue()) + "), end = " + this.endTime + Constants.LEFT_BRACKET_ONLY + HiDateUtil.m(this.endTime.longValue()) + "), SportType = " + this.sportType + ", S = " + this.totalSteps + ", D = " + this.totalDistance + ", C = " + this.totalCalories + ", T = " + this.totalTime;
    }
}
