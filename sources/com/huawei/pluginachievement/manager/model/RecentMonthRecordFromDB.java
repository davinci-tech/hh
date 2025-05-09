package com.huawei.pluginachievement.manager.model;

import com.huawei.pluginachievement.report.bean.ReportDataBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class RecentMonthRecordFromDB {
    private int mAvgSteps;
    private float mDayAvgCalorie;
    private double mDisBeatPercent;
    private long mFirstDay;
    private long mLastDay;
    private int mMaxSteps;
    private double mStepBeatPercent;
    private int mSumAllCount;
    private long mSumAllTime;
    private float mSumCalcCalorie;
    private float mSumCalorie;
    private int mSumCount;
    private int mSumDis;
    private int mSumSteps;
    private int mSumTime;
    public int[] mPercentCalorie = new int[5];
    public Map<Integer, ReportDataBean> mReportDataMap = new ConcurrentHashMap(0);
    private Map<Long, Integer> mOneMonthDis = new HashMap(0);
    private Map<Long, Integer> mOneMonthSteps = new HashMap(0);
    private Map<Long, Long> mWeekTime = new HashMap(0);
    private Map<Long, Float> mWeekCalorie = new HashMap(0);
    private Map<Long, Integer> mSportDays = new ConcurrentHashMap(0);
    private ArrayList<ReportDataBean> mReportDataDetail = new ArrayList<>(0);
    private ArrayList<String> mMedalId = new ArrayList<>(0);

    public ArrayList<String> acquireMedalId() {
        return this.mMedalId;
    }

    public void setDayAvgCalorie(float f) {
        this.mDayAvgCalorie = f;
    }

    public float acquireDayAvgCalorie() {
        return this.mDayAvgCalorie;
    }

    public Map<Long, Integer> acquireSportDays() {
        return this.mSportDays;
    }

    public void setSumCalorie(float f) {
        this.mSumCalorie = f;
    }

    public float acquireSumCalorie() {
        return this.mSumCalorie;
    }

    public void setSumAllCount(int i) {
        this.mSumAllCount = i;
    }

    public int acquireSumAllCount() {
        return this.mSumAllCount;
    }

    public void setSumCalcCalorie(float f) {
        this.mSumCalcCalorie = f;
    }

    public float acquireSumCalcCalorie() {
        return this.mSumCalcCalorie;
    }

    public Map<Long, Long> acquireWeekTime() {
        return this.mWeekTime;
    }

    public Map<Long, Float> acquireWeekCalorie() {
        return this.mWeekCalorie;
    }

    public void setReportDataDetail(ArrayList<ReportDataBean> arrayList) {
        this.mReportDataDetail = arrayList;
    }

    public ArrayList<ReportDataBean> acquireReportDataDetail() {
        return this.mReportDataDetail;
    }

    public Map<Long, Integer> acquireOneMonthDis() {
        return this.mOneMonthDis;
    }

    public void setOneMonthDis(Map<Long, Integer> map) {
        this.mOneMonthDis = map;
    }

    public long acquireSumAllTime() {
        return this.mSumAllTime;
    }

    public void setSumAllTime(long j) {
        this.mSumAllTime = j;
    }

    public int acquireSumTime() {
        return this.mSumTime;
    }

    public void setSumTime(int i) {
        this.mSumTime = i;
    }

    public long acquireFirstday() {
        return this.mFirstDay;
    }

    public void setFirstday(long j) {
        this.mFirstDay = j;
    }

    public long acquireLastDay() {
        return this.mLastDay;
    }

    public void setLastDay(long j) {
        this.mLastDay = j;
    }

    public int acquireSumCount() {
        return this.mSumCount;
    }

    public void setSumCount(int i) {
        this.mSumCount = i;
    }

    public int acquireSumDis() {
        return this.mSumDis;
    }

    public void setSumDis(int i) {
        this.mSumDis = i;
    }

    public Map<Long, Integer> acquireOneMonthSteps() {
        return this.mOneMonthSteps;
    }

    public void setOneMonthSteps(Map<Long, Integer> map) {
        this.mOneMonthSteps = map;
    }

    public int acquireMaxSteps() {
        return this.mMaxSteps;
    }

    public void setMaxSteps(int i) {
        this.mMaxSteps = i;
    }

    public int acquireAvgSteps() {
        return this.mAvgSteps;
    }

    public void setAvgSteps(int i) {
        this.mAvgSteps = i;
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
