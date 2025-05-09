package com.huawei.health.plan.model.intplan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class IntWeekPlanBean {
    private static final String TAG = "IntWeekPlanBean";

    @SerializedName("dayPlans")
    private List<DayPlanBean> mDayPlans;

    @SerializedName("weekDesc")
    private String mWeekDesc;

    @SerializedName("weekOrder")
    private int mWeekOrder;

    @SerializedName("weekPeriod")
    private String mWeekPeriod;

    public IntWeekPlanBean() {
    }

    public IntWeekPlanBean(IntWeekPlan intWeekPlan) {
        this.mWeekOrder = intWeekPlan.getWeekOrder();
        this.mWeekPeriod = intWeekPlan.getWeekPeriod();
        this.mWeekDesc = intWeekPlan.getWeekDesc();
        this.mDayPlans = new ArrayList();
        int i = 0;
        while (true) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i);
            if (dayByIdx == null) {
                return;
            }
            this.mDayPlans.add(new DayPlanBean(dayByIdx));
            i++;
        }
    }

    public int getWeekOrder() {
        return this.mWeekOrder;
    }

    public void setWeekOrder(int i) {
        this.mWeekOrder = i;
    }

    public String getWeekPeriod() {
        return this.mWeekPeriod;
    }

    public void setWeekPeriod(String str) {
        this.mWeekPeriod = str;
    }

    public String getWeekDesc() {
        return this.mWeekDesc;
    }

    public void setWeekDesc(String str) {
        this.mWeekDesc = str;
    }

    public List<DayPlanBean> getDayPlans() {
        return this.mDayPlans;
    }

    public void setDayPlans(List<DayPlanBean> list) {
        this.mDayPlans = list;
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
