package com.huawei.pluginachievement.report.bean;

import java.util.List;

/* loaded from: classes6.dex */
public class AnnualReportStepResult {
    private int key;
    private long mFirstTimestamp;
    private List<Long> mReachStepDayList;
    private List<Long> mReachTimeList;
    private int mStepDays;
    private int mTotalSteps;
    private int overGoal;
    private long timestamp;
    private int value;

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public int getOverGoal() {
        return this.overGoal;
    }

    public void setOverGoal(int i) {
        this.overGoal = i;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int i) {
        this.key = i;
    }

    public long getFirstTimestamp() {
        return this.mFirstTimestamp;
    }

    public void setFirstTimestamp(long j) {
        this.mFirstTimestamp = j;
    }

    public int getStepDays() {
        return this.mStepDays;
    }

    public void setStepDays(int i) {
        this.mStepDays = i;
    }

    public List<Long> getReachTimeList() {
        return this.mReachTimeList;
    }

    public void setReachTimeList(List<Long> list) {
        this.mReachTimeList = list;
    }

    public List<Long> getReachStepDayList() {
        return this.mReachStepDayList;
    }

    public void setReachStepDayList(List<Long> list) {
        this.mReachStepDayList = list;
    }

    public int getTotalSteps() {
        return this.mTotalSteps;
    }

    public void setTotalSteps(int i) {
        this.mTotalSteps = i;
    }

    public String toString() {
        return "AnnualReportStepResult{value=" + this.value + ", timestamp=" + this.timestamp + ", overGoal=" + this.overGoal + ", key=" + this.key + '}';
    }
}
