package com.huawei.pluginachievement.report.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class ReportSwimData extends ReportDataBean {
    private int mBestFreq;
    private int mBestSwolf;
    private int mMaxDistance;
    private int mMaxStroke;
    private Map<Integer, Integer> mStroke;
    private int mSumDistance;

    public ReportSwimData(int i) {
        super(262, i, 2009);
        this.mStroke = new ConcurrentHashMap(0);
        this.mBestSwolf = Integer.MAX_VALUE;
    }

    public void setSumDistance(int i) {
        this.mSumDistance = i;
    }

    public int getSumDistance() {
        return this.mSumDistance;
    }

    public void setMaxDistance(int i) {
        this.mMaxDistance = i;
    }

    public int getMaxDistance() {
        return this.mMaxDistance;
    }

    public void setBestSwolf(int i) {
        this.mBestSwolf = i;
    }

    public int getBestSwolf() {
        return this.mBestSwolf;
    }

    public void setBestFreq(int i) {
        this.mBestFreq = i;
    }

    public int getBestFreq() {
        return this.mBestFreq;
    }

    public void setStroke(Map<Integer, Integer> map) {
        this.mStroke = map;
    }

    public Map<Integer, Integer> getStroke() {
        return this.mStroke;
    }

    public void setMaxStroke(int i) {
        this.mMaxStroke = i;
    }

    public int getMaxStroke() {
        return this.mMaxStroke;
    }
}
