package com.huawei.health.plan.model.intplan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.LogAnonymous;

/* loaded from: classes3.dex */
public class PlanTimeInfoBean {
    private static final String TAG = "PlanTimeInfoBean";

    @SerializedName(ParsedFieldTag.BEGIN_DATE)
    private long mBeginDate;

    @SerializedName("createTime")
    private long mCreateTime;

    @SerializedName("endDate")
    private long mEndDate;

    @SerializedName("remindTime")
    private long mRemindTime;

    @SerializedName("reportUpdateTime")
    private long mReportTime;

    public PlanTimeInfoBean(IntPlan intPlan) {
        PlanTimeInfo planTimeInfo = intPlan.getPlanTimeInfo();
        if (planTimeInfo != null) {
            this.mCreateTime = planTimeInfo.getCreateTime();
            this.mBeginDate = planTimeInfo.getBeginDate();
            this.mRemindTime = planTimeInfo.getRemindTime();
            this.mEndDate = planTimeInfo.getEndDate();
            this.mReportTime = planTimeInfo.getReportTime();
        }
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(long j) {
        this.mCreateTime = j;
    }

    public long getBeginDate() {
        return this.mBeginDate;
    }

    public void setBeginDate(long j) {
        this.mBeginDate = j;
    }

    public long getRemindTime() {
        return this.mRemindTime;
    }

    public void setRemindTime(long j) {
        this.mRemindTime = j;
    }

    public long getEndDate() {
        return this.mEndDate;
    }

    public void setEndDate(long j) {
        this.mEndDate = j;
    }

    public long getReportTime() {
        return this.mReportTime;
    }

    public void setReportTime(long j) {
        this.mReportTime = j;
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
