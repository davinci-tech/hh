package com.huawei.hwsmartinteractmgr.data;

/* loaded from: classes5.dex */
public class ActivitySimple {
    private String mActivityId;
    private String mActivityName;
    private int mActivityPosition;
    private int mActivityStatus;
    private int mActivityType;
    private String mDescription;
    private String mEndDate;
    private String mJoinNum;
    private String mLastModifyTime;
    private String mStartDate;
    private int mTargetValue;
    private String mWordDesc;

    public String getActivityName() {
        return this.mActivityName;
    }

    public void setActivityName(String str) {
        this.mActivityName = str;
    }

    public String getWordDesc() {
        return this.mWordDesc;
    }

    public void setWordDesc(String str) {
        this.mWordDesc = str;
    }

    public String getActivityId() {
        return this.mActivityId;
    }

    public void setActivityId(String str) {
        this.mActivityId = str;
    }

    public String getStartDate() {
        return this.mStartDate;
    }

    public void setStartDate(String str) {
        this.mStartDate = str;
    }

    public void setJoinNum(String str) {
        this.mJoinNum = str;
    }

    public String getEndDate() {
        return this.mEndDate;
    }

    public void setEndDate(String str) {
        this.mEndDate = str;
    }

    public int getActivityType() {
        return this.mActivityType;
    }

    public void setActivityType(int i) {
        this.mActivityType = i;
    }

    public int getActivityStatus() {
        return this.mActivityStatus;
    }

    public void setActivityStatus(int i) {
        this.mActivityStatus = i;
    }

    public int getTargetValue() {
        return this.mTargetValue;
    }

    public void setTargetValue(int i) {
        this.mTargetValue = i;
    }

    public String getLastModifyTime() {
        return this.mLastModifyTime;
    }

    public void setLastModifyTime(String str) {
        this.mLastModifyTime = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public int getActivityPosition() {
        return this.mActivityPosition;
    }

    public void setActivityPosition(int i) {
        this.mActivityPosition = i;
    }

    public String toString() {
        return "ActivitySimple{activityId='" + this.mActivityId + "', activityName='" + this.mActivityName + "', startDate='" + this.mStartDate + "', endDate='" + this.mEndDate + "', joinNum='" + this.mJoinNum + "', activityStatus=" + this.mActivityStatus + ", activityType=" + this.mActivityType + ", targetValue=" + this.mTargetValue + ", description='" + this.mDescription + "', lastModifyTime='" + this.mLastModifyTime + "', activityPosition=" + this.mActivityPosition + '}';
    }
}
