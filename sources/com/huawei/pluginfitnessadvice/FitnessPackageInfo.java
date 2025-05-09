package com.huawei.pluginfitnessadvice;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.FitnessDayPlan;
import com.huawei.basefitnessadvice.model.FitnessPlanCourse;
import com.huawei.basefitnessadvice.model.FitnessWeekPlan;
import com.huawei.basefitnessadvice.model.Introduction;
import com.huawei.basefitnessadvice.model.PlanInfo;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class FitnessPackageInfo extends PlanInfo {

    @SerializedName("cardImage")
    private String mCardImage;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("displayorder")
    private int mDisplayOrder;

    @SerializedName("displayStyle")
    private int mDisplayStyle;

    @SerializedName("weekPlanList")
    private List<FitnessWeekPlan> mFitnessWeekPlanList;

    @SerializedName("introduction")
    private Introduction mIntroduction;

    @SerializedName("packageUserLable")
    private List<String> mLabels;

    @SerializedName("name")
    private String mName;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("planCategory")
    private int mPlanCategory;

    @SerializedName("planTempId")
    private String mPlanTempId;

    @SerializedName("planType")
    private int mPlanType;

    @SerializedName("stage")
    private int mStage;

    @SerializedName("totalCalorie")
    private int mTotalCalorie;

    @SerializedName("totalCalorieMan")
    private int mTotalCalorieMan;

    @SerializedName("weeklyPlanList")
    private List<FitnessWeekPlan> mWeeklyPlanList;

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public int getType() {
        return 3;
    }

    public FitnessPackageInfo(String str) {
        this.mPlanTempId = str;
    }

    public int getTotalCalorieMan() {
        return this.mTotalCalorieMan;
    }

    public int acquireTotalCourse() {
        List<FitnessDayPlan> acquireWeekList;
        List<FitnessPlanCourse> acquireDayPlanCourses;
        List<FitnessWeekPlan> list = this.mFitnessWeekPlanList;
        int i = 0;
        if (list == null) {
            return 0;
        }
        for (FitnessWeekPlan fitnessWeekPlan : list) {
            if (fitnessWeekPlan != null && (acquireWeekList = fitnessWeekPlan.acquireWeekList()) != null) {
                for (FitnessDayPlan fitnessDayPlan : acquireWeekList) {
                    if (fitnessDayPlan != null && (acquireDayPlanCourses = fitnessDayPlan.acquireDayPlanCourses()) != null) {
                        i += acquireDayPlanCourses.size();
                    }
                }
            }
        }
        return i;
    }

    public String acquirePlanTempId() {
        return this.mPlanTempId;
    }

    public int acquireTotalCalorie() {
        return this.mTotalCalorie;
    }

    public List<FitnessWeekPlan> acquireFitnessWeekPlanList() {
        List<FitnessWeekPlan> list = this.mFitnessWeekPlanList;
        return list == null ? Collections.emptyList() : list;
    }

    public String acquireCardPicture() {
        return this.mCardImage;
    }

    public String acquireDescription() {
        return this.mDescription;
    }

    public int acquireDisplayOrder() {
        return this.mDisplayOrder;
    }

    public String acquireName() {
        return this.mName;
    }

    public String acquirePicture() {
        return this.mPicture;
    }

    public int acquireStage() {
        return this.mStage;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public List<String> getLabels() {
        return this.mLabels;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public void setLabels(List<String> list) {
        this.mLabels = list;
    }

    public int getPlanType() {
        return this.mPlanType;
    }

    public void setPlanType(int i) {
        this.mPlanType = i;
    }

    public int acquirePlanCategory() {
        return this.mPlanCategory;
    }

    public Introduction acquireIntroduction() {
        return this.mIntroduction;
    }

    public List<FitnessWeekPlan> acquireWeeklyPlanList() {
        return this.mWeeklyPlanList;
    }

    public int acquireDisplayStyle() {
        return this.mDisplayStyle;
    }

    public String toString() {
        if (("FitnessPackageInfo{mPlanTempId='" + this.mPlanTempId + "', mDescription='" + this.mDescription + "', mName='" + this.mName + "', mPicture='" + this.mPicture + "', mCardImage='" + this.mCardImage + "', mStage=" + this.mStage + ", mDisplayOrder=" + this.mDisplayOrder + ", mLabels=" + this.mLabels + ", mTotalCalorie=" + this.mTotalCalorie + ", mTotalCalorieMan=" + this.mTotalCalorieMan + ", mFitnessWeekPlanList=" + this.mFitnessWeekPlanList + ", mPriceTagBeanList" + this.mPriceTagBeanList) == null) {
            return "";
        }
        return this.mPriceTagBeanList.toString() + "mCommodityFlag" + this.mCommodityFlag + ", mPlanType=" + this.mPlanType + ", mTransactionStatus=" + this.mTransactionStatus + ", mWeeklyPlanList=" + this.mWeeklyPlanList + '}';
    }
}
