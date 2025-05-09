package com.huawei.basichealthmodel.devicelink.bean;

import java.util.List;

/* loaded from: classes3.dex */
public class ResponseMsgBody extends BaseDeviceBean {
    private CloverProgress mCloverProgress;
    private List<NoticesBean> mNoticesBean;
    private int mRecordDay;
    private int mResultCode;
    private List<TaskBean> mTasks;
    private String mAllTasks = "";
    private String mCompletedTasks = "";
    private String mResultDes = "";

    public int getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(int i) {
        this.mRecordDay = i;
    }

    public String getAllTasks() {
        return this.mAllTasks;
    }

    public void setAllTasks(String str) {
        this.mAllTasks = str;
    }

    public String getCompletedTasks() {
        return this.mCompletedTasks;
    }

    public void setCompletedTasks(String str) {
        this.mCompletedTasks = str;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }

    public String getResultDes() {
        return this.mResultDes;
    }

    public void setResultDes(String str) {
        this.mResultDes = str;
    }

    public List<TaskBean> getTasks() {
        return this.mTasks;
    }

    public void setTasks(List<TaskBean> list) {
        this.mTasks = list;
    }

    public List<NoticesBean> getNoticesBean() {
        return this.mNoticesBean;
    }

    public void setNoticesBean(List<NoticesBean> list) {
        this.mNoticesBean = list;
    }

    public CloverProgress getCloverProgress() {
        return this.mCloverProgress;
    }

    public void setCloverProgress(CloverProgress cloverProgress) {
        this.mCloverProgress = cloverProgress;
    }

    @Override // com.huawei.basichealthmodel.devicelink.bean.BaseDeviceBean
    public String toString() {
        return "ResponseMsgBody{mRecordDay=" + this.mRecordDay + ", mAllTasks='" + this.mAllTasks + "', mCompletedTasks=" + this.mCompletedTasks + ", mResultCode=" + this.mResultCode + ", mResultDes='" + this.mResultDes + "', mTasks=" + this.mTasks + ", mNoticesBean=" + this.mNoticesBean + ", mCloverProgress=" + this.mCloverProgress + '}';
    }
}
