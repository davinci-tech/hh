package com.huawei.health.plan.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.CoachParamsMapping;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

/* loaded from: classes8.dex */
public class RunPlanParams implements Serializable {
    private static final String TAG = "Suggestion_RunPlanParams";
    private static final long serialVersionUID = 1;

    @SerializedName("mCoachParamsMapings")
    private List<CoachParamsMapping> mCoachParamsMappings;

    @SerializedName("mEventDate")
    private Calendar mEventDate;

    @SerializedName("mExcludedDates")
    private TreeSet<Integer> mExcludedDates;

    @SerializedName("mGoal")
    private int mGoal;

    @SerializedName("mMaxMet")
    private int mMaxMet;

    @SerializedName("nowDate")
    private Calendar mNowDate;

    @SerializedName("mPlanDays")
    private int mPlanDays;

    @SerializedName(Constants.START_DATE)
    private Calendar mStartDate;

    @SerializedName("userChosenTime")
    private int mUserChosenTime;

    @SerializedName("mWeeklyTrainingDays")
    private int mWeeklyTrainingDays;

    public Calendar getStartDate() {
        return this.mStartDate;
    }

    public void setStartDate(Calendar calendar) {
        this.mStartDate = calendar;
    }

    public Calendar getNowDate() {
        return this.mNowDate;
    }

    public void setNowDate(Calendar calendar) {
        this.mNowDate = calendar;
    }

    public int getGoal() {
        return this.mGoal;
    }

    public void setGoal(int i) {
        this.mGoal = i;
    }

    public int getPlanDays() {
        return this.mPlanDays;
    }

    public void setPlanDays(int i) {
        this.mPlanDays = i;
    }

    public Calendar acquireEventDate() {
        return this.mEventDate;
    }

    public void setEventDate(Calendar calendar) {
        this.mEventDate = calendar;
    }

    public int getMaxMet() {
        return this.mMaxMet;
    }

    public void setMaxMet(int i) {
        this.mMaxMet = i;
    }

    public int getUserChosenTime() {
        return this.mUserChosenTime;
    }

    public void setUserChosenTime(int i) {
        this.mUserChosenTime = i;
    }

    public int getWeeklyTrainingDays() {
        return this.mWeeklyTrainingDays;
    }

    public void setWeeklyTrainingDays(int i) {
        this.mWeeklyTrainingDays = i;
    }

    public TreeSet<Integer> getExcludedDates() {
        TreeSet<Integer> treeSet = this.mExcludedDates;
        if (treeSet == null) {
            LogUtil.h(TAG, "mExcludedDates == null");
            return null;
        }
        return (TreeSet) treeSet.clone();
    }

    public void setExcludedDates(TreeSet<Integer> treeSet) {
        this.mExcludedDates = treeSet;
    }

    public List<CoachParamsMapping> getCoachParamsMapings() {
        return this.mCoachParamsMappings;
    }

    public void setCoachParamsMapings(List<CoachParamsMapping> list) {
        this.mCoachParamsMappings = list;
    }
}
