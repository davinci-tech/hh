package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class FitnessDayPlan implements Cloneable {
    private static final String TAG = "Suggestion_FitnessDayPlan";
    private long date;

    @SerializedName("dayStatus")
    private int dayStatus;

    @SerializedName("description")
    private String description;

    @SerializedName("dayOrder")
    private int mDayOrder;

    @SerializedName("punchFlag")
    private int mPunchFlag;

    @SerializedName("punchTime")
    private long mPunchTime;

    @SerializedName("suggestedReading")
    private SuggestedReading mSuggestedReading;

    @SerializedName("workoutList")
    private List<FitnessPlanCourse> mWorkoutList;

    @SerializedName("name")
    private String name;

    @SerializedName("recommendWorkoutList")
    private List<FitnessPlanCourse> recommendCourses;

    @SerializedName("workoutPlanList")
    private List<FitnessPlanCourse> workoutPlanList;

    public String acquireName() {
        return this.name;
    }

    public final void saveName(String str) {
        this.name = str;
    }

    public String acquireDescription() {
        return this.description;
    }

    public final void saveDescription(String str) {
        this.description = str;
    }

    public long acquireDate() {
        return this.date;
    }

    public void saveStartTime(long j) {
        this.date = j;
    }

    public List<FitnessPlanCourse> acquireRecommendCourses() {
        return this.recommendCourses;
    }

    public final void saveRecommendCourses(List<FitnessPlanCourse> list) {
        this.recommendCourses = list;
    }

    public List<FitnessPlanCourse> acquireDayPlanCourses() {
        List<FitnessPlanCourse> list = this.workoutPlanList;
        return list == null ? this.mWorkoutList : list;
    }

    public final void saveDayPlanCourses(List<FitnessPlanCourse> list) {
        this.workoutPlanList = list;
    }

    public int acquireDayStatus() {
        return this.dayStatus;
    }

    public final void saveDayStatus(int i) {
        this.dayStatus = i;
    }

    public SuggestedReading acquireSuggestedReading() {
        return this.mSuggestedReading;
    }

    public void saveSuggestedReading(SuggestedReading suggestedReading) {
        this.mSuggestedReading = suggestedReading;
    }

    public List<FitnessPlanCourse> acquireWorkoutList() {
        return this.mWorkoutList;
    }

    public void saveWorkoutList(List<FitnessPlanCourse> list) {
        this.mWorkoutList = list;
    }

    public int acquireDayOrder() {
        return this.mDayOrder;
    }

    public void saveDayOrder(int i) {
        this.mDayOrder = i;
    }

    public int acquirePunchFlag() {
        return this.mPunchFlag;
    }

    public void savePunchFlag(int i) {
        this.mPunchFlag = i;
    }

    public long acquirePunchTime() {
        return this.mPunchTime;
    }

    public void savePunchTime(long j) {
        this.mPunchTime = j;
    }

    public String toString() {
        return "FitnessDayPlan{dayStatus=" + this.dayStatus + ", workoutPlanList=" + this.workoutPlanList + ", name='" + this.name + "', description='" + this.description + "', recommendCourses=" + this.recommendCourses + ", date=" + this.date + ", mSuggestedReading=" + this.mSuggestedReading + ", mWorkoutList=" + this.mWorkoutList + '}';
    }

    public Object clone() {
        try {
            if (!(super.clone() instanceof FitnessDayPlan)) {
                return new FitnessDayPlan();
            }
            FitnessDayPlan fitnessDayPlan = (FitnessDayPlan) super.clone();
            fitnessDayPlan.saveDayStatus(this.dayStatus);
            fitnessDayPlan.saveName(this.name);
            fitnessDayPlan.saveDescription(this.description);
            fitnessDayPlan.saveSuggestedReading(this.mSuggestedReading);
            fitnessDayPlan.saveDayOrder(this.mDayOrder);
            fitnessDayPlan.savePunchFlag(this.mPunchFlag);
            fitnessDayPlan.savePunchTime(this.mPunchTime);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            List<FitnessPlanCourse> list = this.workoutPlanList;
            if (list != null) {
                for (FitnessPlanCourse fitnessPlanCourse : list) {
                    if (fitnessPlanCourse != null && (fitnessPlanCourse.clone() instanceof FitnessPlanCourse)) {
                        arrayList.add((FitnessPlanCourse) fitnessPlanCourse.clone());
                    }
                }
                fitnessDayPlan.saveDayPlanCourses(arrayList);
            }
            List<FitnessPlanCourse> list2 = this.recommendCourses;
            if (list2 != null) {
                for (FitnessPlanCourse fitnessPlanCourse2 : list2) {
                    if (fitnessPlanCourse2 != null && (fitnessPlanCourse2.clone() instanceof FitnessPlanCourse)) {
                        arrayList2.add((FitnessPlanCourse) fitnessPlanCourse2.clone());
                    }
                }
                fitnessDayPlan.saveRecommendCourses(arrayList2);
            }
            List<FitnessPlanCourse> list3 = this.mWorkoutList;
            if (list3 != null) {
                for (FitnessPlanCourse fitnessPlanCourse3 : list3) {
                    if (fitnessPlanCourse3 != null && (fitnessPlanCourse3.clone() instanceof FitnessPlanCourse)) {
                        arrayList3.add((FitnessPlanCourse) fitnessPlanCourse3.clone());
                    }
                }
                fitnessDayPlan.saveWorkoutList(arrayList3);
            }
            return fitnessDayPlan;
        } catch (CloneNotSupportedException e) {
            LogUtil.h(TAG, LogAnonymous.b((Throwable) e));
            return new FitnessDayPlan();
        }
    }
}
