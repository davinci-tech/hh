package com.huawei.health.plan.model.intplan;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class ReplacePlanBean {
    private static final String TAG = "ReplacePlanBean";

    @SerializedName("dayNo")
    private int mDayNo;

    @SerializedName("planId")
    private String mPlanId;

    @SerializedName("planType")
    private int mPlanType;

    @SerializedName("propensityWorkoutId")
    private String mPropensityWorkoutId;

    @SerializedName("replacedWorkoutId")
    private String mReplacedWorkoutId;

    @SerializedName("weekNo")
    private int mWeekNo;

    private ReplacePlanBean(Builder builder) {
        this.mPlanId = builder.mPlanId;
        this.mPlanType = builder.mPlanType;
        this.mWeekNo = builder.mWeekNo;
        this.mDayNo = builder.mDayNo;
        this.mPropensityWorkoutId = builder.mPropensityWorkoutId;
        this.mReplacedWorkoutId = builder.mReplacedWorkoutId;
    }

    public static final class Builder {
        private int mDayNo;
        private String mPlanId;
        private int mPlanType;
        private String mPropensityWorkoutId;
        private String mReplacedWorkoutId;
        private int mWeekNo;

        public Builder planId(String str) {
            this.mPlanId = str;
            return this;
        }

        public Builder planType(int i) {
            this.mPlanType = i;
            return this;
        }

        public Builder weekNo(int i) {
            this.mWeekNo = i;
            return this;
        }

        public Builder dayNo(int i) {
            this.mDayNo = i;
            return this;
        }

        public Builder propensityWorkoutId(String str) {
            this.mPropensityWorkoutId = str;
            return this;
        }

        public Builder replacedWorkoutId(String str) {
            this.mReplacedWorkoutId = str;
            return this;
        }

        public ReplacePlanBean build() {
            return new ReplacePlanBean(this);
        }
    }

    public String getPlanId() {
        return this.mPlanId;
    }

    public int getPlanType() {
        return this.mPlanType;
    }

    public int getWeekNo() {
        return this.mWeekNo;
    }

    public int getDayNo() {
        return this.mDayNo;
    }

    public String getPropensityWorkoutId() {
        return this.mPropensityWorkoutId;
    }

    public String getReplacedWorkoutId() {
        return this.mReplacedWorkoutId;
    }
}
