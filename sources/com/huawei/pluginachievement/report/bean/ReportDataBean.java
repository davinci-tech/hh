package com.huawei.pluginachievement.report.bean;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class ReportDataBean {
    private transient CountDownLatch mCountDownLatch;
    private int mDataType;
    private long mMaxTime;
    private int mSportCount;
    private int mSportType;
    private float mSumCalorie;
    private long mSumTime;
    private transient ArrayList<Callable<Void>> mTaskList = new ArrayList<>(0);

    public ReportDataBean(int i, int i2, int i3) {
        this.mSportType = i;
        this.mSportCount = i2;
        this.mDataType = i3;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.mCountDownLatch = countDownLatch;
    }

    public CountDownLatch getCountDownLatch() {
        return this.mCountDownLatch;
    }

    public void setTaskList(ArrayList<Callable<Void>> arrayList) {
        this.mTaskList = arrayList;
    }

    public ArrayList<Callable<Void>> getTaskList() {
        return this.mTaskList;
    }

    public void setDataType(int i) {
        this.mDataType = i;
    }

    public int getDataType() {
        return this.mDataType;
    }

    public void setSportType(int i) {
        this.mSportType = i;
    }

    public int getSportType() {
        return this.mSportType;
    }

    public void setSportCount(int i) {
        this.mSportCount = i;
    }

    public int getSportCount() {
        return this.mSportCount;
    }

    public void setSumCalorie(float f) {
        this.mSumCalorie = f;
    }

    public float getSumCalorie() {
        return this.mSumCalorie;
    }

    public void setSumTime(long j) {
        this.mSumTime = j;
    }

    public long getSumTime() {
        return this.mSumTime;
    }

    public void setMaxTime(long j) {
        this.mMaxTime = j;
    }

    public long getMaxTime() {
        return this.mMaxTime;
    }
}
