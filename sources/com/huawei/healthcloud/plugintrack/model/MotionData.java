package com.huawei.healthcloud.plugintrack.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwsportmodel.CommonSegment;
import defpackage.ffn;
import defpackage.ffs;
import defpackage.knz;
import defpackage.koa;
import defpackage.kod;
import defpackage.koe;
import defpackage.kof;
import defpackage.kog;
import defpackage.koh;
import defpackage.kol;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class MotionData implements Serializable, Cloneable {
    private static final long serialVersionUID = -2092683298890594281L;

    @SerializedName("mAltitudeList")
    private ArrayList<knz> mAltitudeList;

    @SerializedName("mBritishPaceMap")
    private Map<Integer, Float> mBritishPaceMap;

    @SerializedName("mExtendDetailList")
    private List<kod> mExtendDetailList;

    @SerializedName("mHeartRateList")
    private ArrayList<HeartRateData> mHeartRateList;

    @SerializedName("mInvalidHeartRateList")
    private ArrayList<HeartRateData> mInvalidHeartRateList;

    @SerializedName("mLbsDataMap")
    private Map<Long, double[]> mLbsDataMap;

    @SerializedName("mMarkPointList")
    private ArrayList<MarkPoint> mMarkPointList;

    @SerializedName("mMessageList")
    private List<koa> mMessageList;

    @SerializedName("mPaceMap")
    private Map<Integer, Float> mPaceMap;
    private List<kog> mPullFrequencyList;
    private List<koh> mRealTimePaceList;

    @SerializedName("mRealTimeSpeedList")
    private List<koe> mRealTimeSpeedList;
    private int mRealTimeSteps;
    private List<ffn> mRidePostureDataList;
    private ArrayList<ffs> mRunningPostureList;

    @SerializedName("mSegmentList")
    private List<CommonSegment> mSegmentList;

    @SerializedName("mSpo2List")
    private List<kof> mSpo2List;

    @SerializedName("mSportType")
    private int mSportType;
    private int mStartSteps;
    private ArrayList<StepRateData> mStepRateList;
    private List<kol> mSwolfList;

    @SerializedName("mTotalCalories")
    private int mTotalCalories;

    @SerializedName("mTotalDistance")
    private int mTotalDistance;

    @SerializedName("mTotalTime")
    private long mTotalTime;

    public Map<Long, double[]> getLbsDataMap() {
        return this.mLbsDataMap;
    }

    public void setLbsDataMap(Map<Long, double[]> map) {
        this.mLbsDataMap = map;
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

    public ArrayList<HeartRateData> getHeartRateList() {
        return this.mHeartRateList;
    }

    public void setHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.mHeartRateList = arrayList;
    }

    public ArrayList<HeartRateData> getInvalidHeartRateList() {
        return this.mInvalidHeartRateList;
    }

    public List<koa> getMessageList() {
        return this.mMessageList;
    }

    public void setMessageList(List<koa> list) {
        this.mMessageList = list;
    }

    public List<kod> getExtendDetailList() {
        return this.mExtendDetailList;
    }

    public void setExtendDetailList(List<kod> list) {
        this.mExtendDetailList = list;
    }

    public List<kof> getSpo2List() {
        return this.mSpo2List;
    }

    public void saveSpo2List(List<kof> list) {
        this.mSpo2List = list;
    }

    public void saveInvalidHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.mInvalidHeartRateList = arrayList;
    }

    public ArrayList<ffs> getRunningPostureList() {
        return this.mRunningPostureList;
    }

    public void saveRunningPostureList(ArrayList<ffs> arrayList) {
        this.mRunningPostureList = arrayList;
    }

    public ArrayList<StepRateData> getStepRateList() {
        return this.mStepRateList;
    }

    public void setStepRateList(ArrayList<StepRateData> arrayList) {
        this.mStepRateList = arrayList;
    }

    public ArrayList<knz> requestAltitudeList() {
        return this.mAltitudeList;
    }

    public void saveAltitudeList(ArrayList<knz> arrayList) {
        this.mAltitudeList = arrayList;
    }

    public ArrayList<MarkPoint> requestMarkPointList() {
        return this.mMarkPointList;
    }

    public void saveMarkPointList(ArrayList<MarkPoint> arrayList) {
        this.mMarkPointList = arrayList;
    }

    public List<kol> requestSwolfList() {
        return this.mSwolfList;
    }

    public void saveSwolfList(List<kol> list) {
        this.mSwolfList = list;
    }

    public List<kog> requestPullFreqList() {
        return this.mPullFrequencyList;
    }

    public void savePullFreqList(List<kog> list) {
        this.mPullFrequencyList = list;
    }

    public List<koh> requestRealTimePaceList() {
        return this.mRealTimePaceList;
    }

    public void saveRealTimePaceList(List<koh> list) {
        this.mRealTimePaceList = list;
    }

    public void saveSegmentList(List<CommonSegment> list) {
        this.mSegmentList = list;
    }

    public List<CommonSegment> requestSegmentList() {
        return this.mSegmentList;
    }

    public List<koe> requestRealTimeSpeedList() {
        return this.mRealTimeSpeedList;
    }

    public void saveRealTimeSpeedList(List<koe> list) {
        this.mRealTimeSpeedList = list;
    }

    public int getStartSteps() {
        return this.mStartSteps;
    }

    public void saveStartSteps(int i) {
        this.mStartSteps = i;
    }

    public int getRealTimeSteps() {
        return this.mRealTimeSteps;
    }

    public void saveRealTimeSteps(int i) {
        this.mRealTimeSteps = i;
    }

    public void saveCadenceDataList(List<ffn> list) {
        this.mRidePostureDataList = list;
    }

    public List<ffn> getRidePostureDataList() {
        return this.mRidePostureDataList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("MotionPath [, sportType=");
        sb.append(this.mSportType);
        sb.append(", paceMap=");
        sb.append(this.mPaceMap);
        sb.append(", britishPaceMap=");
        sb.append(this.mBritishPaceMap);
        sb.append(", heartrateList=");
        sb.append(this.mHeartRateList);
        sb.append(", altitude=");
        sb.append(this.mAltitudeList);
        sb.append(", totalDistance=");
        sb.append(this.mTotalDistance);
        sb.append(", totalTime=");
        sb.append(this.mTotalTime);
        sb.append(", getTotalCalories()=");
        sb.append(getTotalCalories());
        sb.append(", getClass()=");
        sb.append(getClass());
        sb.append(", hashCode()=");
        sb.append(hashCode());
        sb.append(",invalidHeartRateList");
        sb.append(this.mInvalidHeartRateList);
        sb.append(",runningPostureList");
        sb.append(this.mRunningPostureList);
        sb.append(",ridePostureDataList");
        sb.append(this.mRidePostureDataList);
        sb.append(",mSegmentList");
        sb.append(this.mSegmentList);
        sb.append(",mSpo2List");
        sb.append(this.mSpo2List);
        sb.append("]");
        return sb.toString();
    }

    public int getSportType() {
        return this.mSportType;
    }

    public void setSportType(int i) {
        this.mSportType = i;
    }

    public Map<Integer, Float> localePaceMap() {
        if (UnitUtil.h()) {
            return this.mBritishPaceMap;
        }
        return this.mPaceMap;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public MotionData m531clone() throws CloneNotSupportedException {
        return (MotionData) super.clone();
    }
}
