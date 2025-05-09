package com.huawei.basichealthmodel.devicelink.bean;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class TaskBean {
    private double mProgress;
    private int mRestStatus;
    private int mStatus;
    private int mTaskId;
    private int mTaskType;
    private long mTimestamp;
    private String mTaskTarget = "";
    private String mTargetDes = "";
    private String mTaskValue = "";
    private String mTaskName = "";
    private String mUnit = "";
    private String mStatusDes = "";

    public int getTaskId() {
        return this.mTaskId;
    }

    public void setTaskId(int i) {
        this.mTaskId = i;
    }

    public String getTaskTarget() {
        return this.mTaskTarget;
    }

    public void setTaskTarget(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTaskTarget = "";
        } else {
            this.mTaskTarget = str;
        }
    }

    public String getTargetDes() {
        return this.mTargetDes;
    }

    public String getTaskValue() {
        return this.mTaskValue;
    }

    public void setTaskValue(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTaskValue = "";
        } else {
            this.mTaskValue = str;
        }
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public int getRestStatus() {
        return this.mRestStatus;
    }

    public void setRestStatus(int i) {
        this.mRestStatus = i;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public int getTaskType() {
        return this.mTaskType;
    }

    public void setTaskType(int i) {
        this.mTaskType = i;
    }

    public String getTaskName() {
        return this.mTaskName;
    }

    public void setTaskName(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTaskName = "";
        } else {
            this.mTaskName = str;
        }
    }

    public String getUnit() {
        return this.mUnit;
    }

    public double getProgress() {
        return this.mProgress;
    }

    public void setProgress(double d) {
        this.mProgress = d;
    }

    public String getStatusDes() {
        return this.mStatusDes;
    }

    public void setStatusDes(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mStatusDes = "";
        } else {
            this.mStatusDes = str;
        }
    }

    public String toString() {
        return "TaskBean{mTaskId=" + this.mTaskId + ", mTaskTarget='" + this.mTaskTarget + "', mTargetDes='" + this.mTargetDes + "', mTaskValue='" + this.mTaskValue + "', mStatus=" + this.mStatus + ", mRestStatus=" + this.mRestStatus + ", mTimestamp=" + this.mTimestamp + ", mTaskType=" + this.mTaskType + ", mTaskName='" + this.mTaskName + "', mUnit='" + this.mUnit + "', mProgress=" + this.mProgress + ", mStatusDes='" + this.mStatusDes + "'}";
    }
}
