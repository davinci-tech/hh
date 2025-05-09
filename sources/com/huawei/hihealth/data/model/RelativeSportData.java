package com.huawei.hihealth.data.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class RelativeSportData implements Serializable {
    private static final long serialVersionUID = 5263764572673372430L;
    private int mCalories;
    private long mChangeIntervalTime;
    private int mDistance;
    private long mDuration;
    private long mEndTime;
    private boolean mHasDetailInfo;
    private int mIndex;
    private int mSportType;
    private long mStartTime;

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int i) {
        this.mIndex = i;
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

    public int getSportType() {
        return this.mSportType;
    }

    public void setSportType(int i) {
        this.mSportType = i;
    }

    public long getChangeIntervalTime() {
        return this.mChangeIntervalTime;
    }

    public void setChangeIntervalTime(long j) {
        this.mChangeIntervalTime = j;
    }

    public boolean isHasDetailInfo() {
        return this.mHasDetailInfo;
    }

    public void setHasDetailInfo(boolean z) {
        this.mHasDetailInfo = z;
    }

    public int getDistance() {
        return this.mDistance;
    }

    public void setDistance(int i) {
        this.mDistance = i;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public int getCalories() {
        return this.mCalories;
    }

    public void setCalories(int i) {
        this.mCalories = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("RelativeSportData{mIndex=").append(this.mIndex).append(", mStartTime=").append(this.mStartTime).append(", mEndTime=").append(this.mEndTime).append(", mSportType=").append(this.mSportType).append(", mChangeIntervalTime=").append(this.mChangeIntervalTime).append(", mHasDetailInfo=").append(this.mHasDetailInfo).append(", mDistance=").append(this.mDistance).append(", mDuration=").append(this.mDuration).append(", mCalories=").append(this.mCalories).append("}");
        return stringBuffer.toString();
    }
}
