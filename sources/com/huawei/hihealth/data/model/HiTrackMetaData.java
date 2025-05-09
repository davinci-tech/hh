package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.ResultUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HiTrackMetaData implements Parcelable {
    public static final Parcelable.Creator<HiTrackMetaData> CREATOR = new Parcelable.Creator<HiTrackMetaData>() { // from class: com.huawei.hihealth.data.model.HiTrackMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiTrackMetaData createFromParcel(Parcel parcel) {
            return new HiTrackMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiTrackMetaData[] newArray(int i) {
            return new HiTrackMetaData[i];
        }
    };
    private String coordinate;
    private int mAverageHangTime;
    private int mAvgEversionExcursion;
    private int mAvgForeFootStrikePattern;
    private int mAvgGroundContactTime;
    private int mAvgGroundImpactAcceleration;
    private int mAvgHindFootStrikePattern;
    private int mAvgSwingAngle;
    private int mAvgWholeFootStrikePattern;
    private RelativeSportData mFatherSportItem;
    private float mGroundHangTimeRate;
    private boolean mIsNewCoordinate;
    private int mMaxSpo2;
    private int mMinSpo2;
    private String mRuncourseId;
    private float mTotalDescent;
    private String sportId;
    private int sportType;
    private int trackType;
    private String vendor;
    private float avgPace = 0.0f;
    private float bestPace = 0.0f;
    private int avgHeartRate = 0;
    private int maxHeartRate = 0;
    private int minHeartRate = 0;
    private int avgStepRate = 0;
    private int bestStepRate = 0;
    private int totalDistance = 0;
    private int totalCalories = 0;
    private int totalSteps = 0;
    private long totalTime = 0;
    private Map<Double, Double> partTimeMap = new HashMap(16);
    private Map<Double, Double> britishPartTimeMap = new HashMap(16);
    private Map<Integer, Float> paceMap = new HashMap(16);
    private Map<Integer, Float> britishPaceMap = new HashMap(16);
    private float creepingWave = 0.0f;
    private Map<String, Integer> wearSportData = new HashMap(16);
    private boolean isFreeMotion = false;
    private int sportDataSource = 0;
    private int chiefSportDataType = 0;
    private boolean hasTrackPoint = true;
    private int abnormalTrack = 0;
    private int mDuplicated = 0;
    private int mHeartrateZoneType = 0;
    private float mSwolfBase = 0.0f;
    private float mBritishSwolfBase = 0.0f;
    private float mMaxAlti = Float.MIN_VALUE;
    private float mMinAlti = Float.MAX_VALUE;
    private List<TrackSwimSegment> mSwimSegments = new ArrayList(16);
    private List<TrackSwimSegment> mBritishSwimSegments = new ArrayList(16);
    private List<RelativeSportData> mChildSportItems = new ArrayList(16);
    private Map<String, String> mExtendTrackDataMap = new HashMap();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiTrackMetaData() {
    }

    protected HiTrackMetaData(Parcel parcel) {
        readBasicDataFromParcel(parcel);
        readExtendDataFromParcel(parcel);
    }

    private void readExtendDataFromParcel(Parcel parcel) {
        this.mSwolfBase = parcel.readFloat();
        this.mBritishSwolfBase = parcel.readFloat();
        this.mMaxAlti = parcel.readFloat();
        this.mMinAlti = parcel.readFloat();
        this.mTotalDescent = parcel.readFloat();
        parcel.readList(this.mSwimSegments, TrackSwimSegment.class.getClassLoader());
        parcel.readList(this.mBritishSwimSegments, TrackSwimSegment.class.getClassLoader());
        this.mAvgGroundContactTime = parcel.readInt();
        this.mAverageHangTime = parcel.readInt();
        this.mGroundHangTimeRate = parcel.readFloat();
        this.mAvgGroundImpactAcceleration = parcel.readInt();
        this.mAvgEversionExcursion = parcel.readInt();
        this.mAvgSwingAngle = parcel.readInt();
        this.mAvgForeFootStrikePattern = parcel.readInt();
        this.mAvgWholeFootStrikePattern = parcel.readInt();
        this.mAvgHindFootStrikePattern = parcel.readInt();
        this.mHeartrateZoneType = parcel.readInt();
        this.mRuncourseId = parcel.readString();
        parcel.readList(this.mChildSportItems, RelativeSportData.class.getClassLoader());
        this.mFatherSportItem = (RelativeSportData) parcel.readSerializable();
        this.mMaxSpo2 = parcel.readInt();
        this.mMinSpo2 = parcel.readInt();
        parcel.readMap(this.mExtendTrackDataMap, String.class.getClassLoader());
        this.mIsNewCoordinate = parcel.readByte() != 0;
    }

    private void readBasicDataFromParcel(Parcel parcel) {
        this.avgPace = parcel.readFloat();
        this.bestPace = parcel.readFloat();
        this.avgHeartRate = parcel.readInt();
        this.maxHeartRate = parcel.readInt();
        this.avgStepRate = parcel.readInt();
        this.bestStepRate = parcel.readInt();
        this.totalDistance = parcel.readInt();
        this.totalCalories = parcel.readInt();
        this.totalSteps = parcel.readInt();
        this.totalTime = parcel.readLong();
        this.sportId = parcel.readString();
        this.sportType = parcel.readInt();
        this.trackType = parcel.readInt();
        parcel.readMap(this.partTimeMap, Double.class.getClassLoader());
        parcel.readMap(this.paceMap, Float.class.getClassLoader());
        parcel.readMap(this.wearSportData, Integer.class.getClassLoader());
        this.creepingWave = parcel.readFloat();
        this.minHeartRate = parcel.readInt();
        this.vendor = parcel.readString();
        this.coordinate = parcel.readString();
        this.isFreeMotion = parcel.readByte() != 0;
        this.sportDataSource = parcel.readInt();
        this.chiefSportDataType = parcel.readInt();
        this.hasTrackPoint = parcel.readByte() != 0;
        parcel.readMap(this.britishPartTimeMap, Double.class.getClassLoader());
        parcel.readMap(this.britishPaceMap, Float.class.getClassLoader());
        this.abnormalTrack = parcel.readInt();
        this.mDuplicated = parcel.readInt();
    }

    public int getTotalDistance() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.totalDistance))).intValue();
    }

    public int getDuplicated() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mDuplicated))).intValue();
    }

    public void setDuplicated(int i) {
        this.mDuplicated = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getHeartrateZoneType() {
        return this.mHeartrateZoneType;
    }

    public void setHeartrateZoneType(int i) {
        this.mHeartrateZoneType = i;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public long getTotalTime() {
        return ((Long) ResultUtils.a(Long.valueOf(this.totalTime))).longValue();
    }

    public void setTotalTime(long j) {
        this.totalTime = ((Long) ResultUtils.a(Long.valueOf(j))).longValue();
    }

    public int getTotalCalories() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.totalCalories))).intValue();
    }

    public void setTotalCalories(int i) {
        this.totalCalories = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getTotalSteps() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.totalSteps))).intValue();
    }

    public void setTotalSteps(int i) {
        this.totalSteps = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public float getAvgPace() {
        return ((Float) ResultUtils.a(Float.valueOf(this.avgPace))).floatValue();
    }

    public void setAvgPace(float f) {
        this.avgPace = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public int getAvgHeartRate() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.avgHeartRate))).intValue();
    }

    public void setAvgHeartRate(int i) {
        this.avgHeartRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgStepRate() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.avgStepRate))).intValue();
    }

    public void setAvgStepRate(int i) {
        this.avgStepRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public void setSportId(String str) {
        this.sportId = (String) ResultUtils.a(str);
    }

    public String getSportId() {
        return (String) ResultUtils.a(this.sportId);
    }

    public void setSportType(int i) {
        this.sportType = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getSportType() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.sportType))).intValue();
    }

    public void setPartTimeMap(Map<Double, Double> map) {
        this.partTimeMap = (Map) ResultUtils.a(map);
    }

    public Map<Double, Double> getPartTimeMap() {
        return (Map) ResultUtils.a(this.partTimeMap);
    }

    public int getTrackType() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.trackType))).intValue();
    }

    public void setTrackType(int i) {
        this.trackType = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getMaxHeartRate() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.maxHeartRate))).intValue();
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getBestStepRate() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.bestStepRate))).intValue();
    }

    public void setBestStepRate(int i) {
        this.bestStepRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public void setBestPace(float f) {
        this.bestPace = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public float getBestPace() {
        return ((Float) ResultUtils.a(Float.valueOf(this.bestPace))).floatValue();
    }

    public Map<Integer, Float> getPaceMap() {
        return (Map) ResultUtils.a(this.paceMap);
    }

    public void setPaceMap(Map<Integer, Float> map) {
        this.paceMap = (Map) ResultUtils.a(map);
    }

    public Map<Integer, Float> getBritishPaceMap() {
        return (Map) ResultUtils.a(this.britishPaceMap);
    }

    public void setBritishPaceMap(Map<Integer, Float> map) {
        this.britishPaceMap = (Map) ResultUtils.a(map);
    }

    public float getCreepingWave() {
        return ((Float) ResultUtils.a(Float.valueOf(this.creepingWave))).floatValue();
    }

    public void setCreepingWave(float f) {
        this.creepingWave = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public Map<String, Integer> getWearSportData() {
        return (Map) ResultUtils.a(this.wearSportData);
    }

    public void setWearSportData(Map<String, Integer> map) {
        this.wearSportData = (Map) ResultUtils.a(map);
    }

    public int getMinHeartRate() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.minHeartRate))).intValue();
    }

    public void setMinHeartRate(int i) {
        this.minHeartRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public String getVendor() {
        return (String) ResultUtils.a(this.vendor);
    }

    public void setVendor(String str) {
        this.vendor = (String) ResultUtils.a(str);
    }

    public String getCoordinate() {
        return (String) ResultUtils.a(this.coordinate);
    }

    public void setCoordinate(String str) {
        this.coordinate = (String) ResultUtils.a(str);
    }

    public boolean getIsNewCoordinate() {
        return ((Boolean) ResultUtils.a(Boolean.valueOf(this.mIsNewCoordinate))).booleanValue();
    }

    public void setIsNewCoordinate(boolean z) {
        this.mIsNewCoordinate = ((Boolean) ResultUtils.a(Boolean.valueOf(z))).booleanValue();
    }

    public boolean getIsFreeMotion() {
        return ((Boolean) ResultUtils.a(Boolean.valueOf(this.isFreeMotion))).booleanValue();
    }

    public void setIsFreeMotion(boolean z) {
        this.isFreeMotion = ((Boolean) ResultUtils.a(Boolean.valueOf(z))).booleanValue();
    }

    public int getSportDataSource() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.sportDataSource))).intValue();
    }

    public void setSportDataSource(int i) {
        this.sportDataSource = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getChiefSportDataType() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.chiefSportDataType))).intValue();
    }

    public void setChiefSportDataType(int i) {
        this.chiefSportDataType = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public boolean getHasTrackPoint() {
        return ((Boolean) ResultUtils.a(Boolean.valueOf(this.hasTrackPoint))).booleanValue();
    }

    public void setHasTrackPoint(boolean z) {
        this.hasTrackPoint = ((Boolean) ResultUtils.a(Boolean.valueOf(z))).booleanValue();
    }

    public Map<Double, Double> getBritishPartTimeMap() {
        return (Map) ResultUtils.a(this.britishPartTimeMap);
    }

    public void setBritishPartTimeMap(Map<Double, Double> map) {
        this.britishPartTimeMap = (Map) ResultUtils.a(map);
    }

    public String getRuncourseId() {
        return this.mRuncourseId;
    }

    public void setRuncourseId(String str) {
        this.mRuncourseId = str;
    }

    public int getAbnormalTrack() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.abnormalTrack))).intValue();
    }

    public void setAbnormalTrack(int i) {
        this.abnormalTrack = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("HiTrackMetaData{totalDistance=");
        stringBuffer.append(this.totalDistance);
        stringBuffer.append(", totalCalories=").append(this.totalCalories);
        stringBuffer.append(", totalSteps=").append(this.totalSteps);
        stringBuffer.append(", totalTime=").append(this.totalTime);
        stringBuffer.append(", trackType=").append(this.trackType);
        stringBuffer.append(", sportType=").append(this.sportType);
        stringBuffer.append(", bestStepRate=").append(this.bestStepRate);
        stringBuffer.append(", avgStepRate=").append(this.avgStepRate);
        stringBuffer.append(", avgPace=").append(this.avgPace);
        stringBuffer.append(", bestPace=").append(this.bestPace);
        stringBuffer.append(", creepingWave=").append(this.creepingWave);
        stringBuffer.append(", wearSportData=").append(this.wearSportData.toString());
        stringBuffer.append(", vendor=").append(this.vendor);
        stringBuffer.append(", coordinate=").append(this.coordinate);
        stringBuffer.append(", isFreeMotion=").append(this.isFreeMotion);
        stringBuffer.append(", sportDataSource=").append(this.sportDataSource);
        stringBuffer.append(", chiefSportDataType=").append(this.chiefSportDataType);
        stringBuffer.append(", hasTrackPoint=").append(this.hasTrackPoint);
        stringBuffer.append(", abnormalTrack=").append(this.abnormalTrack);
        stringBuffer.append(", mDuplicated=").append(this.mDuplicated);
        stringBuffer.append(", mSwolfBase=").append(this.mSwolfBase);
        stringBuffer.append(", mBritishSwolfBase=").append(this.mBritishSwolfBase);
        stringBuffer.append(", mMaxAlti=").append(this.mMaxAlti);
        stringBuffer.append(", mMinAlti=").append(this.mMinAlti);
        stringBuffer.append(", mTotalDescent=").append(this.mTotalDescent);
        stringBuffer.append(", mAvgGroundContactTime=").append(this.mAvgGroundContactTime);
        stringBuffer.append(", mAverageHangTime=").append(this.mAverageHangTime);
        stringBuffer.append(", mGroundHangTimeRate=").append(this.mGroundHangTimeRate);
        stringBuffer.append(", mAvgGroundImpactAcceleration=").append(this.mAvgGroundImpactAcceleration);
        stringBuffer.append(", mAvgEversionExcursion=").append(this.mAvgEversionExcursion);
        stringBuffer.append(", mAvgSwingAngle=").append(this.mAvgSwingAngle);
        stringBuffer.append(", mAvgForeFootStrikePattern=").append(this.mAvgForeFootStrikePattern);
        stringBuffer.append(", mAvgWholeFootStrikePattern=").append(this.mAvgWholeFootStrikePattern);
        stringBuffer.append(", mAvgHindFootStrikePattern=").append(this.mAvgHindFootStrikePattern);
        stringBuffer.append(", mHeartrateZoneType=").append(this.mHeartrateZoneType);
        stringBuffer.append(", mRuncourseId=").append(this.mRuncourseId);
        stringBuffer.append(", childs ").append(this.mChildSportItems);
        stringBuffer.append(", father  ").append(this.mFatherSportItem);
        stringBuffer.append(", mMaxSpo2=").append(this.mMaxSpo2);
        stringBuffer.append(", mMinSpo2=").append(this.mMinSpo2);
        stringBuffer.append(", mExtendTrackDataMap=").append(this.mExtendTrackDataMap);
        stringBuffer.append(", mIsNewCoordinate=").append(this.mIsNewCoordinate);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        writeBasicDataToParcel(parcel);
        writeExtendDataToParcel(parcel);
    }

    private void writeExtendDataToParcel(Parcel parcel) {
        parcel.writeFloat(this.mSwolfBase);
        parcel.writeFloat(this.mBritishSwolfBase);
        parcel.writeFloat(this.mMaxAlti);
        parcel.writeFloat(this.mMinAlti);
        parcel.writeFloat(this.mTotalDescent);
        parcel.writeList(this.mSwimSegments);
        parcel.writeList(this.mBritishSwimSegments);
        parcel.writeInt(this.mAvgGroundContactTime);
        parcel.writeInt(this.mAverageHangTime);
        parcel.writeFloat(this.mGroundHangTimeRate);
        parcel.writeInt(this.mAvgGroundImpactAcceleration);
        parcel.writeInt(this.mAvgEversionExcursion);
        parcel.writeInt(this.mAvgSwingAngle);
        parcel.writeInt(this.mAvgForeFootStrikePattern);
        parcel.writeInt(this.mAvgWholeFootStrikePattern);
        parcel.writeInt(this.mAvgHindFootStrikePattern);
        parcel.writeInt(this.mHeartrateZoneType);
        parcel.writeString(this.mRuncourseId);
        parcel.writeList(this.mChildSportItems);
        parcel.writeSerializable(this.mFatherSportItem);
        parcel.writeInt(this.mMaxSpo2);
        parcel.writeInt(this.mMinSpo2);
        parcel.writeMap(this.mExtendTrackDataMap);
        parcel.writeByte(this.mIsNewCoordinate ? (byte) 1 : (byte) 0);
    }

    private void writeBasicDataToParcel(Parcel parcel) {
        parcel.writeFloat(this.avgPace);
        parcel.writeFloat(this.bestPace);
        parcel.writeInt(this.avgHeartRate);
        parcel.writeInt(this.minHeartRate);
        parcel.writeInt(this.maxHeartRate);
        parcel.writeInt(this.avgStepRate);
        parcel.writeInt(this.bestStepRate);
        parcel.writeInt(this.totalDistance);
        parcel.writeInt(this.totalCalories);
        parcel.writeInt(this.totalSteps);
        parcel.writeLong(this.totalTime);
        parcel.writeString(this.sportId);
        parcel.writeInt(this.sportType);
        parcel.writeInt(this.trackType);
        parcel.writeMap(this.partTimeMap);
        parcel.writeMap(this.paceMap);
        parcel.writeFloat(this.creepingWave);
        parcel.writeMap(this.wearSportData);
        parcel.writeString(this.vendor);
        parcel.writeString(this.coordinate);
        parcel.writeByte(this.isFreeMotion ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.sportDataSource);
        parcel.writeInt(this.chiefSportDataType);
        parcel.writeByte(this.hasTrackPoint ? (byte) 1 : (byte) 0);
        parcel.writeMap(this.britishPartTimeMap);
        parcel.writeMap(this.britishPaceMap);
        parcel.writeInt(this.abnormalTrack);
        parcel.writeInt(this.mDuplicated);
    }

    public float getSwolfBase() {
        return ((Float) ResultUtils.a(Float.valueOf(this.mSwolfBase))).floatValue();
    }

    public void setSwolfBase(float f) {
        this.mSwolfBase = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public float getBritishSwolfBase() {
        return ((Float) ResultUtils.a(Float.valueOf(this.mBritishSwolfBase))).floatValue();
    }

    public void setBritishSwolfBase(float f) {
        this.mBritishSwolfBase = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public float getMaxAlti() {
        return ((Float) ResultUtils.a(Float.valueOf(this.mMaxAlti))).floatValue();
    }

    public void setMaxAlti(float f) {
        this.mMaxAlti = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public float getMinAlti() {
        return ((Float) ResultUtils.a(Float.valueOf(this.mMinAlti))).floatValue();
    }

    public void setMinAlti(float f) {
        this.mMinAlti = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public float getTotalDescent() {
        return ((Float) ResultUtils.a(Float.valueOf(this.mTotalDescent))).floatValue();
    }

    public void setTotalDescent(float f) {
        this.mTotalDescent = ((Float) ResultUtils.a(Float.valueOf(f))).floatValue();
    }

    public List<TrackSwimSegment> getSwimSegments() {
        return (List) ResultUtils.a(this.mSwimSegments);
    }

    public void setSwimSegments(List<TrackSwimSegment> list) {
        this.mSwimSegments = (List) ResultUtils.a(list);
    }

    public List<TrackSwimSegment> getBritishSwimSegments() {
        return (List) ResultUtils.a(this.mBritishSwimSegments);
    }

    public void setBritishSwimSegments(List<TrackSwimSegment> list) {
        this.mBritishSwimSegments = (List) ResultUtils.a(list);
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

    public int getAvgGroundContactTime() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgGroundContactTime))).intValue();
    }

    public void setAvgGroundContactTime(int i) {
        this.mAvgGroundContactTime = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgGroundImpactAcceleration() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgGroundImpactAcceleration))).intValue();
    }

    public void setAvgGroundImpactAcceleration(int i) {
        this.mAvgGroundImpactAcceleration = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgEversionExcursion() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgEversionExcursion))).intValue();
    }

    public void setAvgEversionExcursion(int i) {
        this.mAvgEversionExcursion = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgSwingAngle() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgSwingAngle))).intValue();
    }

    public void setAvgSwingAngle(int i) {
        this.mAvgSwingAngle = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgForeFootStrikePattern() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgForeFootStrikePattern))).intValue();
    }

    public void setAvgForeFootStrikePattern(int i) {
        this.mAvgForeFootStrikePattern = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgWholeFootStrikePattern() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgWholeFootStrikePattern))).intValue();
    }

    public void setAvgWholeFootStrikePattern(int i) {
        this.mAvgWholeFootStrikePattern = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAvgHindFootStrikePattern() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.mAvgHindFootStrikePattern))).intValue();
    }

    public void setAvgHindFootStrikePattern(int i) {
        this.mAvgHindFootStrikePattern = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public void setChildSportItems(List<RelativeSportData> list) {
        this.mChildSportItems = list;
    }

    public List<RelativeSportData> getChildSportItems() {
        return this.mChildSportItems;
    }

    public RelativeSportData getFatherSportItem() {
        return this.mFatherSportItem;
    }

    public void setFatherSportItem(RelativeSportData relativeSportData) {
        this.mFatherSportItem = relativeSportData;
    }

    public void setMaxSpo2(int i) {
        this.mMaxSpo2 = i;
    }

    public int getMaxSpo2() {
        return this.mMaxSpo2;
    }

    public void setMinSpo2(int i) {
        this.mMinSpo2 = i;
    }

    public int getMinSpo2() {
        return this.mMinSpo2;
    }

    public Map<String, String> getExtendTrackMap() {
        return this.mExtendTrackDataMap;
    }

    public void setExtendTrackDataMap(Map map) {
        this.mExtendTrackDataMap = map;
    }
}
