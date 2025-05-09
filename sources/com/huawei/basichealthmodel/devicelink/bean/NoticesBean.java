package com.huawei.basichealthmodel.devicelink.bean;

/* loaded from: classes3.dex */
public class NoticesBean {
    private int mRecordDay;
    private int mTaskId;
    private String mTaskName = "";
    private String mMessage = "";

    public int getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(int i) {
        this.mRecordDay = i;
    }

    public int getTaskId() {
        return this.mTaskId;
    }

    public void setTaskId(int i) {
        this.mTaskId = i;
    }

    public String getTaskName() {
        return this.mTaskName;
    }

    public void setTaskName(String str) {
        this.mTaskName = str;
    }

    public String getMsg() {
        return this.mMessage;
    }

    public void setMsg(String str) {
        this.mMessage = str;
    }

    public String toString() {
        return "NoticesBean{mRecordDay=" + this.mRecordDay + ", mTaskId=" + this.mTaskId + ", mTaskName='" + this.mTaskName + "', mMessage='" + this.mMessage + "'}";
    }
}
