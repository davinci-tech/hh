package com.huawei.basefitnessadvice.model;

import com.google.gson.Gson;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class PlanRecord {
    private static final String TAG = "Suggestion_Fitness_PlanRecord";
    private float mActualCalorie;
    private float mActualDistance;
    private long mActualDuration;
    private float mCalorie;
    private int mDifficulty;
    private float mDistance;
    private String mEndDate;
    private TreeSet<Integer> mExcludedDates;
    private long mFinishDate;
    private float mFinishRate;
    private int mGoal;
    private int mPlanCategory;
    private String mPlanId;
    private String mPlanName;
    private String mPlanTempId;
    private int mPlanType;
    private String mStartDate;
    private String mVersion;
    private int mWeekCount;
    private int mWeekTimes;
    private int mWorkoutDays;
    private int mWorkoutTimes;

    public String acquirePlanTempId() {
        return this.mPlanTempId;
    }

    public void savePlanTempId(String str) {
        this.mPlanTempId = str;
    }

    public String acquirePlanId() {
        return this.mPlanId;
    }

    public void savePlanId(String str) {
        this.mPlanId = str;
    }

    public float acquireCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public float acquireDistance() {
        return this.mDistance;
    }

    public void saveDistance(float f) {
        this.mDistance = f;
    }

    public long getTotalDuration() {
        return this.mActualDuration;
    }

    public void setDuration(long j) {
        this.mActualDuration = j;
    }

    public float acquireActualCalorie() {
        return this.mActualCalorie;
    }

    public void saveActualCalorie(float f) {
        this.mActualCalorie = f;
    }

    public float acquireActualDistance() {
        return this.mActualDistance;
    }

    public void saveActualDistance(float f) {
        this.mActualDistance = f;
    }

    public int acquireWorkoutDays() {
        return this.mWorkoutDays;
    }

    public void saveWorkoutDays(int i) {
        this.mWorkoutDays = i;
    }

    public int acquireWorkoutTimes() {
        return this.mWorkoutTimes;
    }

    public void saveWorkoutTimes(int i) {
        this.mWorkoutTimes = i;
    }

    public String acquirePlanName() {
        return this.mPlanName;
    }

    public void savePlanName(String str) {
        this.mPlanName = str;
    }

    public int acquirePlanType() {
        return this.mPlanType;
    }

    public void savePlanType(int i) {
        this.mPlanType = i;
    }

    public String acquireStartDate() {
        return this.mStartDate;
    }

    public void saveStartDate(String str) {
        this.mStartDate = str;
    }

    public String acquireEndDate() {
        return this.mEndDate;
    }

    public void saveEndDate(String str) {
        this.mEndDate = str;
    }

    public float acquireFinishRate() {
        float f = this.mFinishRate;
        if (f > 100.0f) {
            return 100.0f;
        }
        return f;
    }

    public void saveFinishRate(float f) {
        this.mFinishRate = f;
    }

    public int acquireWeekCount() {
        return this.mWeekCount;
    }

    public void saveWeekCount(int i) {
        this.mWeekCount = i;
    }

    public long acquireFinishDate() {
        return this.mFinishDate;
    }

    public void saveFinishDate(long j) {
        this.mFinishDate = j;
    }

    public TreeSet<Integer> acquireExcludedDates() {
        TreeSet<Integer> treeSet = this.mExcludedDates;
        if (treeSet == null) {
            LogUtil.h(TAG, "mExcludedDates == null");
            return null;
        }
        return (TreeSet) treeSet.clone();
    }

    public void saveExcludedDates(TreeSet<Integer> treeSet) {
        this.mExcludedDates = treeSet;
    }

    public int acquireWeekTimes() {
        return this.mWeekTimes;
    }

    public void saveWeekTimes(int i) {
        this.mWeekTimes = i;
    }

    public int acquireDifficulty() {
        return this.mDifficulty;
    }

    public void saveDifficulty(int i) {
        this.mDifficulty = i;
    }

    public int acquireGoal() {
        return this.mGoal;
    }

    public void saveGoal(int i) {
        this.mGoal = i;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public int getPlanCategory() {
        return this.mPlanCategory;
    }

    public void setPlanCategory(int i) {
        this.mPlanCategory = i;
    }

    public String toString() {
        try {
            return new Gson().toJson(this);
        } catch (IllegalArgumentException e) {
            LogUtil.h(TAG, "toString() ", LogAnonymous.b((Throwable) e));
            return "";
        }
    }
}
