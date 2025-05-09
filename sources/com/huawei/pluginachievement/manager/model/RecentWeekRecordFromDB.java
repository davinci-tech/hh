package com.huawei.pluginachievement.manager.model;

import com.huawei.pluginachievement.report.bean.ReportDataBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class RecentWeekRecordFromDB {
    private float mAvgCalorie;
    private int mAvgSteps;
    private double mDisBeatPercent;
    private int mMaxSteps;
    private long mMonday;
    private double mStepBeatPercent;
    private int mSumAllCount;
    private long mSumAllTime;
    private float mSumCalorie;
    private int mSumCount;
    private int mSumDis;
    private int mSumSteps;
    private int mSumTime;
    private long mSunDay;
    public Map<Integer, ReportDataBean> mReportDataMap = new ConcurrentHashMap(0);
    private Map<Long, Integer> mSevenDayDis = new HashMap(0);
    private Map<Long, Integer> mSevenDaySteps = new HashMap(0);
    private Map<Long, Long> mSevenDayTime = new ConcurrentHashMap(0);
    private Map<Long, Integer> mSportDays = new ConcurrentHashMap(0);
    private ArrayList<ReportDataBean> mReportDataDetail = new ArrayList<>(0);
    private ArrayList<String> mMedalId = new ArrayList<>(0);

    public ArrayList<String> acquireMedalId() {
        return this.mMedalId;
    }

    public void setSumAllCount(int i) {
        this.mSumAllCount = i;
    }

    public int acquireSumAllCount() {
        return this.mSumAllCount;
    }

    public void setSumCalorie(float f) {
        this.mSumCalorie = f;
    }

    public float acquireSumCalorie() {
        return this.mSumCalorie;
    }

    public void setAvgCalorie(float f) {
        this.mAvgCalorie = f;
    }

    public float acquireAvgCalorie() {
        return this.mAvgCalorie;
    }

    public Map<Long, Long> acquireSevenDayTime() {
        return this.mSevenDayTime;
    }

    public void setReportDataDetail(ArrayList<ReportDataBean> arrayList) {
        this.mReportDataDetail = arrayList;
    }

    public ArrayList<ReportDataBean> acquireReportDataDetail() {
        return this.mReportDataDetail;
    }

    public Map<Long, Integer> acquireSevenDayDis() {
        return this.mSevenDayDis;
    }

    public void setSevenDayDis(Map<Long, Integer> map) {
        this.mSevenDayDis = map;
    }

    public int acquireSumTime() {
        return this.mSumTime;
    }

    public void setSumTime(int i) {
        this.mSumTime = i;
    }

    public long acquireSumAllTime() {
        return this.mSumAllTime;
    }

    public void setSumAllTime(long j) {
        this.mSumAllTime = j;
    }

    public long acquireMonday() {
        return this.mMonday;
    }

    public void setMonday(long j) {
        this.mMonday = j;
    }

    public long acquireSunDay() {
        return this.mSunDay;
    }

    public void setSunDay(long j) {
        this.mSunDay = j;
    }

    public int acquireSumDis() {
        return this.mSumDis;
    }

    public void setSumDis(int i) {
        this.mSumDis = i;
    }

    public int acquireSumCount() {
        return this.mSumCount;
    }

    public void setSumCount(int i) {
        this.mSumCount = i;
    }

    public Map<Long, Integer> acquireSevenDaySteps() {
        return this.mSevenDaySteps;
    }

    public void setSevenDaySteps(Map<Long, Integer> map) {
        this.mSevenDaySteps = map;
    }

    public Map<Long, Integer> acquireSportDays() {
        return this.mSportDays;
    }

    public int acquireAvgSteps() {
        return this.mAvgSteps;
    }

    public void setAvgSteps(int i) {
        this.mAvgSteps = i;
    }

    public int acquireMaxSteps() {
        return this.mMaxSteps;
    }

    public void setMaxSteps(int i) {
        this.mMaxSteps = i;
    }

    public int acquireSumSteps() {
        return this.mSumSteps;
    }

    public void setSumSteps(int i) {
        this.mSumSteps = i;
    }

    public double acquireDisBeatPercent() {
        return this.mDisBeatPercent;
    }

    public void setDisBeatPercent(double d) {
        this.mDisBeatPercent = d;
    }

    public double acquireStepBeatPercent() {
        return this.mStepBeatPercent;
    }

    public void setStepBeatPercent(double d) {
        this.mStepBeatPercent = d;
    }
}
