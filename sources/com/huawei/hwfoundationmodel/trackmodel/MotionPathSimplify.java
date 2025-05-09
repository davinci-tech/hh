package com.huawei.hwfoundationmodel.trackmodel;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hihealth.data.model.TrackSwimSegment;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class MotionPathSimplify implements Serializable {
    private static final long serialVersionUID = 4613834291759846024L;

    @SerializedName("britishPaceMap")
    private Map<Integer, Float> britishPaceMap;

    @SerializedName("britishPartTimeMap")
    private Map<Double, Double> britishPartTimeMap;

    @SerializedName("deviceType")
    private int deviceType;

    @SerializedName("endTime")
    private long endTime;

    @SerializedName("mAverageHangTime")
    private int mAverageHangTime;

    @SerializedName("mAvgEversionExcursion")
    private int mAvgEversionExcursion;

    @SerializedName("mAvgForeFootStrikePattern")
    private int mAvgForeFootStrikePattern;

    @SerializedName("mAvgGroundContactTime")
    private int mAvgGroundContactTime;

    @SerializedName("mAvgGroundImpactAcceleration")
    private int mAvgGroundImpactAcceleration;

    @SerializedName("mAvgHindFootStrikePattern")
    private int mAvgHindFootStrikePattern;

    @SerializedName("mAvgSwingAngle")
    private int mAvgSwingAngle;

    @SerializedName("mAvgWholeFootStrikePattern")
    private int mAvgWholeFootStrikePattern;
    private List<TrackSwimSegment> mBritishSwimSegments;
    private List<RelativeSportData> mChildSportItems;

    @SerializedName("mDeviceUuid")
    private String mDeviceUuid;
    private RelativeSportData mFatherSportItem;

    @SerializedName("mGroundHangTimeRate")
    private float mGroundHangTimeRate;

    @SerializedName("mMaxSpo2")
    private int mMaxSpo2;

    @SerializedName("mMinSpo2")
    private int mMinSpo2;

    @SerializedName("mRealTimeSteps")
    private int mRealTimeSteps;

    @SerializedName("mRuncourseId")
    private String mRuncourseId;

    @SerializedName("mStartSteps")
    private int mStartSteps;
    private List<TrackSwimSegment> mSwimSegments;

    @SerializedName("mTotalDescent")
    private float mTotalDescent;

    @SerializedName("paceMap")
    private Map<Integer, Float> paceMap;

    @SerializedName("partTimeMap")
    private Map<Double, Double> partTimeMap;

    @SerializedName("sportId")
    private String sportId;

    @SerializedName(BleConstants.SPORT_TYPE)
    private int sportType;

    @SerializedName("startTime")
    private long startTime;

    @SerializedName("trackType")
    private int trackType;

    @SerializedName("wearSportData")
    private Map<String, Integer> wearSportData;

    @SerializedName("avgPace")
    private float avgPace = 0.0f;

    @SerializedName("bestPace")
    private float bestPace = 0.0f;

    @SerializedName("avgHeartRate")
    private int avgHeartRate = 0;

    @SerializedName("maxHeartRate")
    private int maxHeartRate = 0;

    @SerializedName("minHeartRate")
    private int minHeartRate = 0;

    @SerializedName("avgStepRate")
    private int avgStepRate = 0;

    @SerializedName("bestStepRate")
    private int bestStepRate = 0;

    @SerializedName(BleConstants.TOTAL_DISTANCE)
    private int totalDistance = 0;

    @SerializedName(BleConstants.TOTAL_CALORIES)
    private int totalCalories = 0;

    @SerializedName("totalSteps")
    private int totalSteps = 0;

    @SerializedName("totalTime")
    private long totalTime = 0;

    @SerializedName("creepingWave")
    private float creepingWave = 0.0f;

    @SerializedName("mapType")
    private int mapType = 0;

    @SerializedName("mCoordinate")
    private String mCoordinate = AMapLocation.COORD_TYPE_WGS84;

    @SerializedName("mIsNewCoordinate")
    private boolean mIsNewCoordinate = false;

    @SerializedName("isFreeMotion")
    private boolean isFreeMotion = false;

    @SerializedName("sportDataSource")
    private int sportDataSource = 0;

    @SerializedName("chiefSportDataType")
    private int chiefSportDataType = 0;

    @SerializedName("hasTrackPoint")
    private boolean hasTrackPoint = true;

    @SerializedName("abnormalTrack")
    private int abnormalTrack = 0;

    @SerializedName("mSwolfBase")
    private float mSwolfBase = 0.0f;

    @SerializedName("mBritishSwolfBase")
    private float mBritishSwolfBase = 0.0f;

    @SerializedName("mMaxAlti")
    private float mMaxAlti = Float.MIN_VALUE;

    @SerializedName("mMinAlti")
    private float mMinAlti = Float.MAX_VALUE;

    @SerializedName("mDuplicated")
    private int mDuplicated = 0;

    @SerializedName("mHeartrateZoneType")
    private int mHeartrateZoneType = 0;

    @SerializedName("mRuncourseType")
    private int mRuncourseType = 0;

    @SerializedName("mTemporaryData")
    private Map<String, Object> mTemporaryData = new LinkedHashMap();

    @SerializedName("mExtendDataMap")
    private Map<String, String> mExtendDataMap = new ConcurrentHashMap();

    public int requestAverageHangTime() {
        return this.mAverageHangTime;
    }

    public void saveAverageHangTime(int i) {
        this.mAverageHangTime = i;
    }

    public float requestGroundHangTimeRate() {
        return this.mGroundHangTimeRate;
    }

    public void saveGroundHangTimeRate(float f) {
        this.mGroundHangTimeRate = f;
    }

    public int requestAvgGroundContactTime() {
        return this.mAvgGroundContactTime;
    }

    public void saveAvgGroundContactTime(int i) {
        this.mAvgGroundContactTime = i;
    }

    public int requestAvgGroundImpactAcceleration() {
        return this.mAvgGroundImpactAcceleration;
    }

    public void saveAvgGroundImpactAcceleration(int i) {
        this.mAvgGroundImpactAcceleration = i;
    }

    public int requestAvgEversionExcursion() {
        return this.mAvgEversionExcursion;
    }

    public void saveAvgEversionExcursion(int i) {
        this.mAvgEversionExcursion = i;
    }

    public int requestAvgSwingAngle() {
        return this.mAvgSwingAngle;
    }

    public void saveAvgSwingAngle(int i) {
        this.mAvgSwingAngle = i;
    }

    public int requestAvgForeFootStrikePattern() {
        return this.mAvgForeFootStrikePattern;
    }

    public void saveAvgForeFootStrikePattern(int i) {
        this.mAvgForeFootStrikePattern = i;
    }

    public int requestAvgWholeFootStrikePattern() {
        return this.mAvgWholeFootStrikePattern;
    }

    public void saveAvgWholeFootStrikePattern(int i) {
        this.mAvgWholeFootStrikePattern = i;
    }

    public int requestAvgHindFootStrikePattern() {
        return this.mAvgHindFootStrikePattern;
    }

    public void saveAvgHindFootStrikePattern(int i) {
        this.mAvgHindFootStrikePattern = i;
    }

    public int requestAbnormalTrack() {
        return this.abnormalTrack;
    }

    public void saveAbnormalTrack(int i) {
        this.abnormalTrack = i;
    }

    public long requestStartTime() {
        return this.startTime;
    }

    public void saveStartTime(long j) {
        this.startTime = j;
    }

    public long requestEndTime() {
        return this.endTime;
    }

    public void saveEndTime(long j) {
        this.endTime = j;
    }

    public void saveTotalDistance(int i) {
        this.totalDistance = i;
    }

    public int requestTotalDistance() {
        if (this.sportType == 222) {
            return getExtendDataInt("wayPointDistance");
        }
        return this.totalDistance;
    }

    public void saveTotalTime(long j) {
        this.totalTime = j;
    }

    public long requestTotalTime() {
        return this.totalTime;
    }

    public int requestTotalCalories() {
        return this.totalCalories;
    }

    public void saveTotalCalories(int i) {
        this.totalCalories = i;
    }

    public int requestActiveCalories() {
        return getExtendDataInt(HwExerciseConstants.JSON_NAME_ACTIVECALORIE);
    }

    public void saveActiveCalories(int i) {
        if (i > 0) {
            addExtendDataMap(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, String.valueOf(i));
        }
    }

    public int requestTotalSteps() {
        return this.totalSteps;
    }

    public void saveTotalSteps(int i) {
        this.totalSteps = i;
    }

    public float requestAvgPace() {
        if (this.sportType == 222) {
            if (requestTotalDistance() <= 0) {
                return 0.0f;
            }
            return TimeUnit.MILLISECONDS.toSeconds(requestTotalTime()) / ((requestTotalDistance() * 1.0f) / 1000.0f);
        }
        return this.avgPace;
    }

    public void saveAvgPace(float f) {
        this.avgPace = f;
    }

    public int requestAvgHeartRate() {
        return this.avgHeartRate;
    }

    public void saveAvgHeartRate(int i) {
        this.avgHeartRate = i;
    }

    public int requestAvgStepRate() {
        return this.avgStepRate;
    }

    public void saveAvgStepRate(int i) {
        this.avgStepRate = i;
    }

    public void saveSportId(String str) {
        this.sportId = str;
    }

    public String requestSportId() {
        return this.sportId;
    }

    public void saveSportType(int i) {
        this.sportType = i;
    }

    public int requestSportType() {
        return this.sportType;
    }

    public void savePartTimeMap(Map<Double, Double> map) {
        this.partTimeMap = map;
    }

    public Map<Double, Double> requestPartTimeMap() {
        return this.partTimeMap;
    }

    public Map<Double, Double> requestBritishPartTimeMap() {
        return this.britishPartTimeMap;
    }

    public void saveBritishPartTimeMap(Map<Double, Double> map) {
        this.britishPartTimeMap = map;
    }

    public int requestTrackType() {
        return this.trackType;
    }

    public void saveTrackType(int i) {
        this.trackType = i;
    }

    public int requestMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void saveMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public int requestBestStepRate() {
        return this.bestStepRate;
    }

    public void saveBestStepRate(int i) {
        this.bestStepRate = i;
    }

    public void saveBestPace(float f) {
        this.bestPace = f;
    }

    public float requestBestPace() {
        return this.bestPace;
    }

    public Map<Integer, Float> requestPaceMap() {
        return this.paceMap;
    }

    public void savePaceMap(Map<Integer, Float> map) {
        this.paceMap = map;
    }

    public Map<Integer, Float> requestBritishPaceMap() {
        return this.britishPaceMap;
    }

    public Map<Integer, Float> localePaceMap() {
        if (UnitUtil.h()) {
            return this.britishPaceMap;
        }
        return this.paceMap;
    }

    public void saveBritishPaceMap(Map<Integer, Float> map) {
        this.britishPaceMap = map;
    }

    public Map<String, Integer> requestSportData() {
        return this.wearSportData;
    }

    public void saveSportData(Map<String, Integer> map) {
        this.wearSportData = map;
    }

    public float requestCreepingWave() {
        return this.creepingWave;
    }

    public void saveCreepingWave(float f) {
        this.creepingWave = f;
    }

    public int requestMinHeartRate() {
        return this.minHeartRate;
    }

    public int requestMapType() {
        return this.mapType;
    }

    public void saveMapType(int i) {
        this.mapType = i;
    }

    public String requestMapCoordinate() {
        return this.mCoordinate;
    }

    public void saveMapCoordinate(String str) {
        this.mCoordinate = str;
    }

    public boolean isNewCoordinate() {
        return this.mIsNewCoordinate;
    }

    public void saveIsNewCoordinate(boolean z) {
        this.mIsNewCoordinate = z;
    }

    public boolean isFreeMotion() {
        return this.isFreeMotion;
    }

    public void saveFreeMotion(boolean z) {
        this.isFreeMotion = z;
    }

    public int requestSportDataSource() {
        return this.sportDataSource;
    }

    public void saveSportDataSource(int i) {
        this.sportDataSource = i;
    }

    public String requestRunCourseId() {
        return this.mRuncourseId;
    }

    public void saveRunCourseId(String str) {
        this.mRuncourseId = str;
    }

    public int requestRunCourseType() {
        return this.mRuncourseType;
    }

    public void saveRunCourseType(int i) {
        this.mRuncourseType = i;
    }

    public int requestChiefSportDataType() {
        return this.chiefSportDataType;
    }

    public void saveChiefSportDataType(int i) {
        this.chiefSportDataType = i;
    }

    public boolean hasTrackPoint() {
        return this.hasTrackPoint;
    }

    public void saveHasTrackPoint(boolean z) {
        this.hasTrackPoint = z;
    }

    public int requestDeviceType() {
        return this.deviceType;
    }

    public void saveDeviceType(int i) {
        this.deviceType = i;
    }

    public String requestDeviceUuid() {
        return this.mDeviceUuid;
    }

    public void saveDeviceUuid(String str) {
        this.mDeviceUuid = str;
    }

    public float requestSwolfBase() {
        return this.mSwolfBase;
    }

    public void saveSwolfBase(float f) {
        this.mSwolfBase = f;
    }

    public float requestBritishSwolfBase() {
        return this.mBritishSwolfBase;
    }

    public void saveBritishSwolfBase(float f) {
        this.mBritishSwolfBase = f;
    }

    public List<TrackSwimSegment> requestSwimSegments() {
        return this.mSwimSegments;
    }

    public void saveSwimSegments(List<TrackSwimSegment> list) {
        this.mSwimSegments = list;
    }

    public List<TrackSwimSegment> requestBritishSwimSegments() {
        return this.mBritishSwimSegments;
    }

    public void saveBritishSwimSegments(List<TrackSwimSegment> list) {
        this.mBritishSwimSegments = list;
    }

    public float requestMaxAltitude() {
        return this.mMaxAlti;
    }

    public void saveMaxAltitude(float f) {
        this.mMaxAlti = f;
    }

    public float requestMinAltitude() {
        return this.mMinAlti;
    }

    public void saveMinAltitude(float f) {
        this.mMinAlti = f;
    }

    public float requestTotalDescent() {
        return this.mTotalDescent;
    }

    public void saveTotalDescent(float f) {
        this.mTotalDescent = f;
    }

    public int requestDuplicated() {
        return this.mDuplicated;
    }

    public void saveDuplicated(int i) {
        this.mDuplicated = i;
    }

    public int requestHeartRateZoneType() {
        return this.mHeartrateZoneType;
    }

    public void saveHeartRateZoneType(int i) {
        this.mHeartrateZoneType = i;
    }

    public void setTemporaryData(Map<String, Object> map) {
        this.mTemporaryData = map;
    }

    public Map<String, Object> getTemporaryData() {
        return this.mTemporaryData;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("sportType ").append(this.sportType).append(" trackType ").append(this.trackType).append(System.lineSeparator());
        stringBuffer.append("sportTime ").append(this.startTime).append("--").append(this.endTime).append(System.lineSeparator());
        stringBuffer.append("duration ").append(this.totalTime).append(" distance ").append(this.totalDistance).append(" calories ").append(this.totalCalories).append(System.lineSeparator());
        stringBuffer.append("avgPace ").append(this.avgPace).append(" bestPace ").append(this.bestPace).append(System.lineSeparator());
        heartRateDataToString(stringBuffer);
        stepDataToString(stringBuffer);
        paceMapDataToString(stringBuffer);
        stringBuffer.append("isFreeMotion ").append(this.isFreeMotion).append(System.lineSeparator());
        stringBuffer.append("sportDataSource ").append(this.sportDataSource).append(System.lineSeparator());
        stringBuffer.append("chiefSportDataType ").append(this.chiefSportDataType).append(System.lineSeparator());
        stringBuffer.append("hasTrackPoint ").append(this.hasTrackPoint).append(System.lineSeparator());
        stringBuffer.append("mapType ").append(this.mapType).append(System.lineSeparator());
        stringBuffer.append("deviceType ").append(this.deviceType).append(System.lineSeparator());
        stringBuffer.append("abnormalTrack ").append(this.abnormalTrack).append(System.lineSeparator());
        stringBuffer.append("SwolfBase ").append(this.mSwolfBase).append(System.lineSeparator());
        stringBuffer.append("BritishSwolfBase ").append(this.mBritishSwolfBase).append(System.lineSeparator());
        altitudeDataToString(stringBuffer);
        stringBuffer.append("Segments ").append(this.mSwimSegments).append(System.lineSeparator());
        stringBuffer.append("BritishSegments ").append(this.mBritishSwimSegments).append(System.lineSeparator());
        runningPostureToString(stringBuffer);
        spo2ToString(stringBuffer);
        stringBuffer.append(", mDuplicated=").append(this.mDuplicated);
        stringBuffer.append(", mHeartRateZoneType=").append(this.mHeartrateZoneType);
        stringBuffer.append(", mRunCourseId=").append(this.mRuncourseId);
        stringBuffer.append(", mRunCourseType=").append(this.mRuncourseType).append(System.lineSeparator());
        extendDataToString(stringBuffer);
        stringBuffer.append("Childs ").append(this.mChildSportItems).append(" Father ").append(this.mFatherSportItem).append(System.lineSeparator());
        return stringBuffer.toString();
    }

    private void extendDataToString(StringBuffer stringBuffer) {
        stringBuffer.append("mExtendDataMap=");
        for (Map.Entry<String, String> entry : this.mExtendDataMap.entrySet()) {
            stringBuffer.append(entry.getKey()).append(' ').append(entry.getValue()).append(',');
        }
        stringBuffer.append(System.lineSeparator());
    }

    private void spo2ToString(StringBuffer stringBuffer) {
        stringBuffer.append(", mMaxSpo2=").append(this.mMaxSpo2);
        stringBuffer.append(", mMinSpo2=").append(this.mMinSpo2);
    }

    private void runningPostureToString(StringBuffer stringBuffer) {
        stringBuffer.append(", mAverageHangTime=").append(this.mAverageHangTime);
        stringBuffer.append(", mGroundHangTimeRate=").append(this.mGroundHangTimeRate);
        stringBuffer.append(", mAvgGroundContactTime=").append(this.mAvgGroundContactTime);
        stringBuffer.append(", mAvgGroundImpactAcceleration=").append(this.mAvgGroundImpactAcceleration);
        stringBuffer.append(", mAvgEversionExcursion=").append(this.mAvgEversionExcursion);
        stringBuffer.append(", mAvgSwingAngle=").append(this.mAvgSwingAngle);
        stringBuffer.append(", mAvgForeFootStrikePattern=").append(this.mAvgForeFootStrikePattern);
        stringBuffer.append(", mAvgWholeFootStrikePattern=").append(this.mAvgWholeFootStrikePattern);
        stringBuffer.append(", mAvgHindFootStrikePattern=").append(this.mAvgHindFootStrikePattern).append(System.lineSeparator());
    }

    private void altitudeDataToString(StringBuffer stringBuffer) {
        stringBuffer.append(" creepingWave ").append(this.creepingWave).append("Descent ").append(this.mTotalDescent).append(" ").append("MaxAltitude ").append(this.mMaxAlti).append(" ").append("MinAltitude ").append(this.mMaxAlti).append(System.lineSeparator());
    }

    private void paceMapDataToString(StringBuffer stringBuffer) {
        Map<Double, Double> map = this.partTimeMap;
        if (map != null && map.size() > 0) {
            stringBuffer.append("partTimeMap ").append(this.partTimeMap.toString()).append(System.lineSeparator());
        }
        Map<Double, Double> map2 = this.britishPartTimeMap;
        if (map2 != null && map2.size() > 0) {
            stringBuffer.append("britishPartTimeMap ").append(this.britishPartTimeMap.toString()).append(System.lineSeparator());
        }
        Map<Integer, Float> map3 = this.paceMap;
        if (map3 != null && map3.size() > 0) {
            stringBuffer.append("paceMap ").append(this.paceMap.toString()).append(System.lineSeparator());
        }
        Map<Integer, Float> map4 = this.britishPaceMap;
        if (map4 == null || map4.size() <= 0) {
            return;
        }
        stringBuffer.append("britishPaceMap ").append(this.britishPaceMap.toString()).append(System.lineSeparator());
    }

    private void stepDataToString(StringBuffer stringBuffer) {
        stringBuffer.append("totalSteps ").append(this.totalSteps).append(" avgStepRate ").append(this.avgStepRate).append(" bestStepRate ").append(this.bestStepRate).append(System.lineSeparator());
    }

    private void heartRateDataToString(StringBuffer stringBuffer) {
        if (LogUtil.c()) {
            stringBuffer.append("avgHeartRate ").append("***").append(" max ").append("***").append(" min ").append("***").append(System.lineSeparator());
        } else {
            stringBuffer.append("avgHeartRate ").append(this.avgHeartRate).append(" max ").append(this.maxHeartRate).append(" min ").append(this.minHeartRate).append(System.lineSeparator());
        }
    }

    public List<RelativeSportData> requestChildSportItems() {
        return this.mChildSportItems;
    }

    public void saveChildSportItems(List<RelativeSportData> list) {
        this.mChildSportItems = list;
    }

    public RelativeSportData requestFatherSportItem() {
        return this.mFatherSportItem;
    }

    public void saveFatherSportItem(RelativeSportData relativeSportData) {
        this.mFatherSportItem = relativeSportData;
    }

    public int requestRealTimeSteps() {
        return this.mRealTimeSteps;
    }

    public void saveRealTimeSteps(int i) {
        this.mRealTimeSteps = i;
    }

    public int requireStartSteps() {
        return this.mStartSteps;
    }

    public void saveStartSteps(int i) {
        this.mStartSteps = i;
    }

    public int requestMaxSpo2() {
        return this.mMaxSpo2;
    }

    public void saveMaxSpo2(int i) {
        this.mMaxSpo2 = i;
    }

    public int requestMinSpo2() {
        return this.mMinSpo2;
    }

    public void saveMinSpo2(int i) {
        this.mMinSpo2 = i;
    }

    public Map<String, String> requestExtendDataMap() {
        return this.mExtendDataMap;
    }

    public void saveExtendDataMap(Map<String, String> map) {
        if (map != null) {
            this.mExtendDataMap.putAll(map);
        }
    }

    public void addExtendDataMap(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        this.mExtendDataMap.put(str, str2);
    }

    public int getExtendDataInt(String str) {
        return getExtendDataInt(str, -1);
    }

    public int getExtendDataInt(String str, int i) {
        return CommonUtil.e(this.mExtendDataMap.get(str), i);
    }

    public long getExtendDataLong(String str) {
        return getExtendDataLong(str, -1L);
    }

    public long getExtendDataLong(String str, long j) {
        return CommonUtil.b(this.mExtendDataMap.get(str), j);
    }

    public String getExtendDataString(String str) {
        return getExtendDataString(str, null);
    }

    public String getExtendDataString(String str, String str2) {
        String str3 = this.mExtendDataMap.get(str);
        return str3 != null ? str3 : str2;
    }

    public float getExtendDataFloat(String str) {
        return getExtendDataFloat(str, -1.0f);
    }

    public float getExtendDataFloat(String str, float f) {
        return CommonUtil.c(this.mExtendDataMap.get(str), f);
    }

    public double getExtendDataDouble(String str) {
        return getExtendDataDouble(str, -1.0d);
    }

    public double getExtendDataDouble(String str, double d) {
        return CommonUtil.b(this.mExtendDataMap.get(str), d);
    }

    public String requestProductId() {
        return getExtendDataString("sourceProdId", "");
    }

    public void saveProductId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        addExtendDataMap("sourceProdId", str);
    }

    public void printReleaseSimplifyLog(String str) {
        ReleaseLogUtil.e(str, "startTime: ", Long.valueOf(this.startTime), "endTime: ", Long.valueOf(this.endTime), "avgPace: ", Float.valueOf(this.avgPace), "bestPace: ", Float.valueOf(this.bestPace), "avgHeartRate: ", Integer.valueOf(this.avgHeartRate), "maxHeartRate: ", Integer.valueOf(this.maxHeartRate), "minHeartRate: ", Integer.valueOf(this.minHeartRate), "avgStepRate", Integer.valueOf(this.avgStepRate), "bestStepRate: ", Integer.valueOf(this.bestStepRate), "totalDistance: ", Integer.valueOf(this.totalDistance), "totalCalories: ", Integer.valueOf(this.totalCalories), "totalSteps: ", Integer.valueOf(this.totalSteps), "totalTime: ", Long.valueOf(this.totalTime), "sportType: ", Integer.valueOf(this.sportType), "partTimeMap: ", this.partTimeMap, "britishPartTimeMap: ", this.britishPartTimeMap, "paceMap: ", this.paceMap, "britishPaceMap: ", this.britishPaceMap, "trackType: ", Integer.valueOf(this.trackType), "creepingWave: ", Float.valueOf(this.creepingWave), "mapType: ", Integer.valueOf(this.mapType), "isFreeMotion: ", Boolean.valueOf(this.isFreeMotion), "sportDataSource: ", Integer.valueOf(this.sportDataSource), "chiefSportDataType: ", Integer.valueOf(this.chiefSportDataType), "hasTrackPoint: ", Boolean.valueOf(this.hasTrackPoint), "deviceType: ", Integer.valueOf(this.deviceType), "abnormalTrack: ", Integer.valueOf(this.abnormalTrack), "swolfBase: ", Float.valueOf(this.mSwolfBase), "britishSwolfBase: ", Float.valueOf(this.mBritishSwolfBase), "swimSegments: ", this.mSwimSegments, "britishSwimSegments: ", this.mBritishSwimSegments, "maxAltitude: ", Float.valueOf(this.mMaxAlti), "mMinAltitude: ", Float.valueOf(this.mMinAlti), "totalDescent: ", Float.valueOf(this.mTotalDescent), "avgGroundContactTime: ", Integer.valueOf(this.mAvgGroundContactTime), "avgGroundImpactAcceleration: ", Integer.valueOf(this.mAvgGroundImpactAcceleration), "avgEversionExcursion: ", Integer.valueOf(this.mAvgEversionExcursion), "avgSwingAngle: ", Integer.valueOf(this.mAvgSwingAngle), "avgForeFootStrikePattern: ", Integer.valueOf(this.mAvgForeFootStrikePattern), "avgWholeFootStrikePattern: ", Integer.valueOf(this.mAvgWholeFootStrikePattern), "avgHindFootStrikePattern: ", Integer.valueOf(this.mAvgHindFootStrikePattern), "mDuplicated: ", Integer.valueOf(this.mDuplicated), "heartRateZoneType: ", Integer.valueOf(this.mHeartrateZoneType), "runCourseId: ", this.mRuncourseId, "runCourseType: ", Integer.valueOf(this.mRuncourseType), "childSportItems: ", this.mChildSportItems, "fatherSportItem: ", this.mFatherSportItem, "sourceProdId：", requestProductId(), "activeCalories: ", Integer.valueOf(requestActiveCalories()));
    }

    public void correctTotalTime(String str) {
        Map<Integer, Float> map = this.paceMap;
        if (map == null || map.size() < 1) {
            LogUtil.b(str, "correctTotalTime paceMap is invalid");
            return;
        }
        int i = 0;
        long j = 0;
        for (Map.Entry<Integer, Float> entry : this.paceMap.entrySet()) {
            int intValue = entry.getKey().intValue() / 100000;
            int i2 = intValue % 100;
            if (i2 > 0) {
                j = (long) (j + (entry.getValue().floatValue() * 10.0f * i2));
            } else if (i != intValue) {
                j += TimeUnit.SECONDS.toMillis(Math.round(entry.getValue().floatValue()));
                i = intValue;
            }
        }
        LogUtil.a(str, "correctTotalTime totalTime: " + j);
        if (j > 0) {
            saveTotalTime(j);
        }
    }
}
