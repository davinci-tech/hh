package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hihealth.data.model.TrackSwimSegment;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class MotionPathSimplify implements Serializable {
    private static final String STAR = "***";
    private static final String TAG = "MotionPathSimplify";
    private static final long serialVersionUID = 4613834291759846024L;

    @SerializedName("britishPaceMap")
    private Map<Integer, Float> mBritishPaceMap;

    @SerializedName("britishPartTimeMap")
    private Map<Double, Double> mBritishPartTimeMap;
    private List<TrackSwimSegment> mBritishSwimSegments;
    private float mBritishSwolfBase;
    private List<RelativeSportData> mChildSportItems;

    @SerializedName("endTime")
    private long mEndTime;
    private RelativeSportData mFatherSportItem;

    @SerializedName("jsonString")
    private String mJsonString;
    private int mMaxSpo2;
    private int mMinSpo2;

    @SerializedName("paceMap")
    private Map<Integer, Float> mPaceMap;

    @SerializedName("partTimeMap")
    private Map<Double, Double> mPartTimeMap;
    private String mRuncourseId;

    @SerializedName("sportId")
    private String mSportId;

    @SerializedName(BleConstants.SPORT_TYPE)
    private int mSportType;

    @SerializedName("startTime")
    private long mStartTime;
    private List<TrackSwimSegment> mSwimSegments;
    private float mSwolfBase;
    private float mTotalDescent;

    @SerializedName("trackType")
    private int mTrackType;

    @SerializedName("wearSportData")
    private Map<String, Integer> mWearSportDataMap;

    @SerializedName("avgPace")
    private float mAvgPace = 0.0f;

    @SerializedName("bestPace")
    private float mBestPace = 0.0f;

    @SerializedName("avgHeartRate")
    private int mAvgHeartRate = 0;

    @SerializedName("maxHeartRate")
    private int mMaxHeartRate = 0;

    @SerializedName("minHeartRate")
    private int mMinHeartRate = 0;

    @SerializedName("avgStepRate")
    private int mAvgStepRate = 0;

    @SerializedName("bestStepRate")
    private int mBestStepRate = 0;

    @SerializedName(BleConstants.TOTAL_DISTANCE)
    private int mTotalDistance = 0;

    @SerializedName(BleConstants.TOTAL_CALORIES)
    private int mTotalCalories = 0;

    @SerializedName("totalSteps")
    private int mTotalSteps = 0;

    @SerializedName("totalTime")
    private long mTotalTime = 0;

    @SerializedName("creepingWave")
    private float mCreepingWave = -1.0f;

    @SerializedName("mapType")
    private int mMapType = 0;

    @SerializedName("isFreeMotion")
    private boolean mIsFreeMotion = false;

    @SerializedName("sportDataSource")
    private int mSportDataSource = 0;

    @SerializedName("chiefSportDataType")
    private int mChiefSportDataType = 0;

    @SerializedName("hasTrackPoint")
    private boolean mIsHasTrackPoint = true;

    @SerializedName("abnormalTrack")
    private int mAbnormalTrack = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_TARGET_PERCENT)
    private int mTargetPercent = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_EXERCISE_LEVEL)
    private int mExerciseLevel = -1;

    @SerializedName("exercise_time")
    private int mExerciseTime = 0;
    private float mMaxAlti = Float.MIN_VALUE;
    private float mMinAlti = Float.MAX_VALUE;

    @SerializedName("mAvgGroundContactTime")
    private int mAvgGroundContactTime = -1;
    private int mAvgGroundImpactAcceleration = -1;

    @SerializedName("mAvgEversionExcursion")
    private int mAvgEversionExcursion = -101;

    @SerializedName("mAvgSwingAngle")
    private int mAvgSwingAngle = -1;

    @SerializedName("mAvgForeFootStrikePattern")
    private int mAvgForeFootStrikePattern = -1;

    @SerializedName("mAvgWholeFootStrikePattern")
    private int mAvgWholeFootStrikePattern = -1;

    @SerializedName("mAvgHindFootStrikePattern")
    private int mAvgHindFootStrikePattern = -1;
    private int mHeartrateZoneType = 0;
    private Map<String, String> mExtendDataMap = new ConcurrentHashMap(16);

    @SerializedName("mAverageHangTime")
    private int mAverageHangTime = -1;

    @SerializedName("mGroundHangTimeRate")
    private float mGroundHangTimeRate = -1.0f;
    private boolean mIsWorkout = true;
    private boolean mIsPackSaveSuccess = true;
    private int mWorkoutTrajectories = -1;
    private int mDivingEvent = -1;
    private List<Integer> dictTypeList = new ArrayList(0);

    public Map<String, String> requestExtendDataMap() {
        return this.mExtendDataMap;
    }

    public void saveExtendDataMap(Map<String, String> map) {
        this.mExtendDataMap.clear();
        this.mExtendDataMap.putAll(map);
    }

    public void addExtendDataMap(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        this.mExtendDataMap.put(str, str2);
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

    public float getSwolfBase() {
        return this.mSwolfBase;
    }

    public void setSwolfBase(float f) {
        this.mSwolfBase = f;
    }

    public float getBritishSwolfBase() {
        return this.mBritishSwolfBase;
    }

    public void setBritishSwolfBase(float f) {
        this.mBritishSwolfBase = f;
    }

    public List<TrackSwimSegment> getSwimSegments() {
        return this.mSwimSegments;
    }

    public void setSwimSegments(List<TrackSwimSegment> list) {
        this.mSwimSegments = list;
    }

    public List<TrackSwimSegment> getBritishSwimSegments() {
        return this.mBritishSwimSegments;
    }

    public void setBritishSwimSegments(List<TrackSwimSegment> list) {
        this.mBritishSwimSegments = list;
    }

    public float getMaxAlti() {
        return this.mMaxAlti;
    }

    public void setMaxAlti(float f) {
        this.mMaxAlti = f;
    }

    public float getMinAlti() {
        return this.mMinAlti;
    }

    public void setMinAlti(float f) {
        this.mMinAlti = f;
    }

    public float getTotalDescent() {
        return this.mTotalDescent;
    }

    public void setTotalDescent(float f) {
        this.mTotalDescent = f;
    }

    public int requestAbnormalTrack() {
        return this.mAbnormalTrack;
    }

    public void saveAbnormalTrack(int i) {
        this.mAbnormalTrack = i;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public int getTotalDistance() {
        return this.mTotalDistance;
    }

    public void setTotalDistance(int i) {
        this.mTotalDistance = i;
    }

    public long getTotalTime() {
        return this.mTotalTime;
    }

    public void setTotalTime(long j) {
        this.mTotalTime = j;
    }

    public int getTotalCalories() {
        return this.mTotalCalories;
    }

    public void setTotalCalories(int i) {
        this.mTotalCalories = i;
    }

    public int getTotalSteps() {
        return this.mTotalSteps;
    }

    public void setTotalSteps(int i) {
        this.mTotalSteps = i;
    }

    public float getAvgPace() {
        return this.mAvgPace;
    }

    public void setAvgPace(float f) {
        this.mAvgPace = f;
    }

    public int getAvgHeartRate() {
        return this.mAvgHeartRate;
    }

    public void setAvgHeartRate(int i) {
        this.mAvgHeartRate = i;
    }

    public int getAvgStepRate() {
        return this.mAvgStepRate;
    }

    public void setAvgStepRate(int i) {
        this.mAvgStepRate = i;
    }

    public void setSportId(String str) {
        this.mSportId = str;
    }

    public String getSportId() {
        return this.mSportId;
    }

    public void setSportType(int i) {
        this.mSportType = i;
    }

    public int getSportType() {
        return this.mSportType;
    }

    public void setPartTimeMap(Map<Double, Double> map) {
        this.mPartTimeMap = map;
    }

    public Map<Double, Double> getPartTimeMap() {
        return this.mPartTimeMap;
    }

    public Map<Double, Double> getBritishPartTimeMap() {
        return this.mBritishPartTimeMap;
    }

    public void setBritishPartTimeMap(Map<Double, Double> map) {
        this.mBritishPartTimeMap = map;
    }

    public int getTrackType() {
        return this.mTrackType;
    }

    public void setTrackType(int i) {
        this.mTrackType = i;
    }

    public int getMaxHeartRate() {
        return this.mMaxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.mMaxHeartRate = i;
    }

    public int getBestStepRate() {
        return this.mBestStepRate;
    }

    public void setBestStepRate(int i) {
        this.mBestStepRate = i;
    }

    public void setBestPace(float f) {
        this.mBestPace = f;
    }

    public float getBestPace() {
        return this.mBestPace;
    }

    public Map<Integer, Float> getPaceMap() {
        return this.mPaceMap;
    }

    public void setPaceMap(Map<Integer, Float> map) {
        this.mPaceMap = map;
    }

    public Map<Integer, Float> getBritishPaceMap() {
        return this.mBritishPaceMap;
    }

    public void setBritishPaceMap(Map<Integer, Float> map) {
        this.mBritishPaceMap = map;
    }

    public Map<String, Integer> getSportData() {
        return this.mWearSportDataMap;
    }

    public void setSportData(Map<String, Integer> map) {
        this.mWearSportDataMap = map;
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

    public int getTargetPercent() {
        return this.mTargetPercent;
    }

    public void setTargetPercent(int i) {
        this.mTargetPercent = i;
    }

    public int getExerciseLevel() {
        return this.mExerciseLevel;
    }

    public void setExerciseLevel(int i) {
        this.mExerciseLevel = i;
    }

    public int getExerciseTime() {
        return this.mExerciseTime;
    }

    public void setExerciseTime(int i) {
        this.mExerciseTime = i;
    }

    public float getCreepingWave() {
        return this.mCreepingWave;
    }

    public void setCreepingWave(float f) {
        this.mCreepingWave = f;
    }

    public int getMinHeartRate() {
        return this.mMinHeartRate;
    }

    public void setMinHeartRate(int i) {
        this.mMinHeartRate = i;
    }

    public int getMapType() {
        return this.mMapType;
    }

    public void setMapType(int i) {
        this.mMapType = i;
    }

    public boolean getIsFreeMotion() {
        return this.mIsFreeMotion;
    }

    public void setIsFreeMotion(boolean z) {
        this.mIsFreeMotion = z;
    }

    public int getSportDataSource() {
        return this.mSportDataSource;
    }

    public void setSportDataSource(int i) {
        this.mSportDataSource = i;
    }

    public int getChiefSportDataType() {
        return this.mChiefSportDataType;
    }

    public void setChiefSportDataType(int i) {
        this.mChiefSportDataType = i;
    }

    public boolean getHasTrackPoint() {
        return this.mIsHasTrackPoint;
    }

    public void setHasTrackPoint(boolean z) {
        this.mIsHasTrackPoint = z;
    }

    public int getmHeartrateZoneType() {
        return this.mHeartrateZoneType;
    }

    public void setHeartrateZoneType(int i) {
        this.mHeartrateZoneType = i;
    }

    public String getRuncourseId() {
        return this.mRuncourseId;
    }

    public void setRuncourseId(String str) {
        this.mRuncourseId = str;
    }

    public String getJsonString() {
        return this.mJsonString;
    }

    public void setJsonString(String str) {
        this.mJsonString = str;
    }

    public int getMaxSpo2() {
        return this.mMaxSpo2;
    }

    public void setMaxSpo2(int i) {
        this.mMaxSpo2 = i;
    }

    public int getMinSpo2() {
        return this.mMinSpo2;
    }

    public void setMinSpo2(int i) {
        this.mMinSpo2 = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stitchingStringBuffer1(stringBuffer);
        Map<Double, Double> map = this.mPartTimeMap;
        if (map != null && map.size() > 0) {
            stringBuffer.append("partTimeMap ").append(this.mPartTimeMap.toString()).append(System.lineSeparator());
        }
        Map<Double, Double> map2 = this.mBritishPartTimeMap;
        if (map2 != null && map2.size() > 0) {
            stringBuffer.append("britishPartTimeMap ").append(this.mBritishPartTimeMap.toString()).append(System.lineSeparator());
        }
        Map<Integer, Float> map3 = this.mPaceMap;
        if (map3 != null && map3.size() > 0) {
            stringBuffer.append("paceMap ").append(this.mPaceMap.toString()).append(System.lineSeparator());
        }
        Map<Integer, Float> map4 = this.mBritishPaceMap;
        if (map4 != null && map4.size() > 0) {
            stringBuffer.append("britishPaceMap ").append(this.mBritishPaceMap.toString()).append(System.lineSeparator());
        }
        stitchingStringBuffer2(stringBuffer);
        List<RelativeSportData> list = this.mChildSportItems;
        if (list == null) {
            stringBuffer.append(Constants.NULL);
        } else {
            stringBuffer.append(list.toString());
        }
        stringBuffer.append(",mFatherSportItem=");
        RelativeSportData relativeSportData = this.mFatherSportItem;
        if (relativeSportData == null) {
            stringBuffer.append(Constants.NULL);
        } else {
            stringBuffer.append(relativeSportData.toString());
        }
        Map<String, String> map5 = this.mExtendDataMap;
        if (map5 != null && map5.size() > 0) {
            stringBuffer.append(",mExtendDataMap=").append(this.mExtendDataMap.toString()).append(System.lineSeparator());
        }
        return stringBuffer.toString();
    }

    private void stitchingStringBuffer1(StringBuffer stringBuffer) {
        stringBuffer.append("sportType ").append(this.mSportType).append(" trackType ").append(this.mTrackType).append(System.lineSeparator());
        stringBuffer.append("sportTime ").append(this.mStartTime).append("--").append(this.mEndTime).append(System.lineSeparator());
        stringBuffer.append("duration ").append(this.mTotalTime).append(" distance ").append(this.mTotalDistance).append(" calories ").append(this.mTotalCalories).append(" creepingWave ").append(this.mCreepingWave).append(" exerciseTime ").append(this.mExerciseTime).append(System.lineSeparator());
        stringBuffer.append("avgPace ").append(this.mAvgPace).append(" bestPace ").append(this.mBestPace).append(System.lineSeparator());
        stringBuffer.append("avgHeartRate ").append(STAR).append(" max ").append(STAR).append(" min ").append(STAR).append(System.lineSeparator());
        stringBuffer.append("totalSteps ").append(this.mTotalSteps).append(" avgStepRate ").append(this.mAvgStepRate).append(" bestStepRate ").append(this.mBestStepRate).append(System.lineSeparator());
        stringBuffer.append(" targetPercent ").append(this.mTargetPercent).append(System.lineSeparator());
        stringBuffer.append(" exerciseLevel ").append(this.mExerciseLevel).append(System.lineSeparator());
    }

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

    public boolean isIsWorkout() {
        return this.mIsWorkout;
    }

    public void setIsWorkout(boolean z) {
        this.mIsWorkout = z;
    }

    public boolean isPackSaveSuccess() {
        return this.mIsPackSaveSuccess;
    }

    public void setIsPackSaveSuccess(boolean z) {
        this.mIsPackSaveSuccess = z;
    }

    public int getWorkoutTrajectories() {
        return this.mWorkoutTrajectories;
    }

    public void setWorkoutTrajectories(int i) {
        this.mWorkoutTrajectories = i;
    }

    public int getDivingEvent() {
        return this.mDivingEvent;
    }

    public void setDivingEvent(int i) {
        this.mDivingEvent = i;
    }

    public List<Integer> getDictTypeList() {
        return this.dictTypeList;
    }

    private void stitchingStringBuffer2(StringBuffer stringBuffer) {
        stringBuffer.append("isFreeMotion ").append(this.mIsFreeMotion).append(System.lineSeparator());
        stringBuffer.append("sportDataSource ").append(this.mSportDataSource).append(System.lineSeparator());
        stringBuffer.append("chiefSportDataType ").append(this.mChiefSportDataType).append(System.lineSeparator());
        stringBuffer.append("hasTrackPoint ").append(this.mIsHasTrackPoint).append(System.lineSeparator());
        stringBuffer.append("abnormalTrack ").append(this.mAbnormalTrack).append(System.lineSeparator());
        stringBuffer.append("mMaxAlti ").append(this.mMaxAlti).append(System.lineSeparator());
        stringBuffer.append("mMinAlti ").append(this.mMinAlti).append(System.lineSeparator());
        stringBuffer.append("mTotalDescent ").append(this.mTotalDescent);
        stringBuffer.append(", mAvgGroundContactTime=").append(this.mAvgGroundContactTime);
        stringBuffer.append(", mAvgGroundImpactAcceleration=").append(this.mAvgGroundImpactAcceleration);
        stringBuffer.append(", mAvgEversionExcursion=").append(this.mAvgEversionExcursion);
        stringBuffer.append(", mAvgSwingAngle=").append(this.mAvgSwingAngle);
        stringBuffer.append(", mAvgForeFootStrikePattern=").append(this.mAvgForeFootStrikePattern);
        stringBuffer.append(", mAvgWholeFootStrikePattern=").append(this.mAvgWholeFootStrikePattern);
        stringBuffer.append(", mAvgHindFootStrikePattern=").append(this.mAvgHindFootStrikePattern);
        stringBuffer.append(", mAverageHangTime=").append(this.mAverageHangTime);
        stringBuffer.append(", mGroundHangTimeRate=").append(this.mGroundHangTimeRate);
        stringBuffer.append(", mHeartrateZoneType=").append(this.mHeartrateZoneType);
        stringBuffer.append(", mRuncourseId=").append(this.mRuncourseId);
        stringBuffer.append(",mChildSportItems=");
    }

    public void changeKey(String str, String str2) {
        char c;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "name or value is null or empty.");
            return;
        }
        try {
            switch (str.hashCode()) {
                case -1905729366:
                    if (str.equals("mAvgEversionExcursion")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1754136002:
                    if (str.equals("mAvgWholeFootStrikePattern")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1238957201:
                    if (str.equals("mAverageHangTime")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -714805677:
                    if (str.equals("mGroundHangTimeRate")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -251587116:
                    if (str.equals("mAvgHindFootStrikePattern")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -158368159:
                    if (str.equals("mAvgGroundContactTime")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1087740463:
                    if (str.equals("mAvgForeFootStrikePattern")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1477212538:
                    if (str.equals("mAvgSwingAngle")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    saveAvgForeFootStrikePattern(Integer.parseInt(str2));
                    break;
                case 1:
                    saveAvgWholeFootStrikePattern(Integer.parseInt(str2));
                    break;
                case 2:
                    saveAvgHindFootStrikePattern(Integer.parseInt(str2));
                    break;
                case 3:
                    saveAvgGroundContactTime(Integer.parseInt(str2));
                    break;
                case 4:
                    saveAverageHangTime(Integer.parseInt(str2));
                    break;
                case 5:
                    saveGroundHangTimeRate(Float.parseFloat(str2));
                    break;
                case 6:
                    saveAvgEversionExcursion(Integer.parseInt(str2));
                    break;
                case 7:
                    saveAvgSwingAngle(Integer.parseInt(str2));
                    break;
                default:
                    Object[] objArr = new Object[1];
                    objArr[0] = "changeKey parse else.";
                    LogUtil.h(TAG, objArr);
                    break;
            }
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "changeKey NumberFormatException:", ExceptionUtils.d(e));
        }
    }
}
