package com.huawei.health.plan.model.intplan;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.AdjustmentExercisePlan;
import com.huawei.basefitnessadvice.model.intplan.DayInfo;
import com.huawei.basefitnessadvice.model.intplan.LeaveInfo;
import java.util.List;

/* loaded from: classes3.dex */
public class LeavePlanCalendarBean {

    @SerializedName("adjustmentExercisePlan")
    private AdjustmentExercisePlan mAdjustmentExercisePlan;

    @SerializedName("category")
    private int mCategory;

    @SerializedName("days")
    private List<DayInfo> mDays;

    @SerializedName("leaveInfo")
    private LeaveInfo mLeaveInfo;

    @SerializedName("operationType")
    private int mOperationType;

    @SerializedName("planId")
    private String mPlanId;

    @SerializedName("subCategory")
    private int mSubCategory;

    private LeavePlanCalendarBean(Builder builder) {
        this.mPlanId = builder.mPlanId;
        this.mCategory = builder.mCategory;
        this.mSubCategory = builder.mSubCategory;
        this.mLeaveInfo = builder.mLeaveInfo;
        this.mOperationType = builder.mOperationType;
        this.mDays = builder.mDays;
        this.mAdjustmentExercisePlan = builder.mAdjustmentExercisePlan;
    }

    public static final class Builder {
        private AdjustmentExercisePlan mAdjustmentExercisePlan;
        private int mCategory;
        private List<DayInfo> mDays;
        private LeaveInfo mLeaveInfo;
        private int mOperationType;
        private String mPlanId;
        private int mSubCategory;

        public Builder planId(String str) {
            this.mPlanId = str;
            return this;
        }

        public Builder category(Integer num) {
            this.mCategory = num.intValue();
            return this;
        }

        public Builder subCategory(Integer num) {
            this.mSubCategory = num.intValue();
            return this;
        }

        public Builder leaveInfo(LeaveInfo leaveInfo) {
            this.mLeaveInfo = leaveInfo;
            return this;
        }

        public Builder operationType(Integer num) {
            this.mOperationType = num.intValue();
            return this;
        }

        public Builder days(List<DayInfo> list) {
            this.mDays = list;
            return this;
        }

        public Builder adjustmentExercisePlan(AdjustmentExercisePlan adjustmentExercisePlan) {
            this.mAdjustmentExercisePlan = adjustmentExercisePlan;
            return this;
        }

        public LeavePlanCalendarBean build() {
            return new LeavePlanCalendarBean(this);
        }
    }

    public String getPlanId() {
        return this.mPlanId;
    }

    public Integer getCategory() {
        return Integer.valueOf(this.mCategory);
    }

    public Integer getSubCategory() {
        return Integer.valueOf(this.mSubCategory);
    }

    public LeaveInfo getLeaveInfo() {
        return this.mLeaveInfo;
    }

    public Integer getOperationType() {
        return Integer.valueOf(this.mOperationType);
    }

    public List<DayInfo> getDays() {
        return this.mDays;
    }

    public AdjustmentExercisePlan getExercisePlan() {
        return this.mAdjustmentExercisePlan;
    }
}
