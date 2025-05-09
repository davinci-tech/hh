package com.huawei.health.plan.model.intplan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class IntPlanBean {
    private static final String TAG = "IntPlanBean";

    @SerializedName("goals")
    private List<GoalBean> mGoals;

    @SerializedName("metaInfo")
    private PlanMetaInfoBean mMetaInfo;

    @SerializedName("planInput")
    private PlanInputInfoBean mPlanInput;

    @SerializedName("statistics")
    private List<StatInfoBean> mStats;

    @SerializedName(BleConstants.TEMPERATURE)
    private int mTemp;

    @SerializedName("timeInfo")
    private PlanTimeInfoBean mTimeInfo;

    @SerializedName("timeZone")
    private String mTimeZone;

    @SerializedName("transactionStatus")
    private int mTransactionStatus;

    @SerializedName("uid")
    private String mUid;

    @SerializedName("weekPlans")
    private List<IntWeekPlanBean> mWeekPlans;

    public IntPlanBean() {
    }

    public IntPlanBean(IntPlan intPlan) {
        this.mTimeZone = intPlan.getTimeZone();
        this.mTransactionStatus = intPlan.getTransactionStatus();
        if (intPlan.getMetaInfo() != null) {
            this.mMetaInfo = new PlanMetaInfoBean(intPlan);
        }
        if (intPlan.getPlanTimeInfo() != null) {
            this.mTimeInfo = new PlanTimeInfoBean(intPlan);
        }
        if (intPlan.getPlanInputInfo() != null) {
            this.mPlanInput = new PlanInputInfoBean(intPlan);
        }
        this.mWeekPlans = new ArrayList();
        int i = 0;
        while (true) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i);
            if (weekInfoByIdx == null) {
                break;
            }
            this.mWeekPlans.add(new IntWeekPlanBean(weekInfoByIdx));
            i++;
        }
        this.mGoals = new ArrayList();
        for (String str : StatInfo.ALL_STAT_KEY) {
            if (intPlan.getGoal(str) != null) {
                this.mGoals.add(new GoalBean(intPlan.getGoal(str)));
            }
        }
        this.mStats = new ArrayList();
        for (String str2 : StatInfo.ALL_STAT_KEY) {
            if (intPlan.getStat(str2) != null) {
                this.mStats.add(new StatInfoBean(intPlan.getStat(str2)));
            }
        }
    }

    public List<GoalBean> getGoals() {
        return this.mGoals;
    }

    public void setGoals(List<GoalBean> list) {
        this.mGoals = list;
    }

    public List<StatInfoBean> getStats() {
        return this.mStats;
    }

    public void setStats(List<StatInfoBean> list) {
        this.mStats = list;
    }

    public String getUid() {
        return this.mUid;
    }

    public void setUid(String str) {
        this.mUid = str;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }

    public PlanMetaInfoBean getMetaInfo() {
        return this.mMetaInfo;
    }

    public void setMetaInfo(PlanMetaInfoBean planMetaInfoBean) {
        this.mMetaInfo = planMetaInfoBean;
    }

    public PlanTimeInfoBean getTimeInfo() {
        return this.mTimeInfo;
    }

    public void setTimeInfo(PlanTimeInfoBean planTimeInfoBean) {
        this.mTimeInfo = planTimeInfoBean;
    }

    public PlanInputInfoBean getPlanInput() {
        return this.mPlanInput;
    }

    public void setPlanInput(PlanInputInfoBean planInputInfoBean) {
        this.mPlanInput = planInputInfoBean;
    }

    public List<IntWeekPlanBean> getWeekPlans() {
        return this.mWeekPlans;
    }

    public void setWeekPlans(List<IntWeekPlanBean> list) {
        this.mWeekPlans = list;
    }

    public int getTransactionStatus() {
        return this.mTransactionStatus;
    }

    public void setTransactionStatus(int i) {
        this.mTransactionStatus = i;
    }

    public int getTemp() {
        return this.mTemp;
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
