package com.huawei.health.plan.model.intplan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.mob;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DayPlanBean {
    private static final String TAG = "DayPlanBean";

    @SerializedName("customAction")
    private List<IntActionBean> mCustomAction;

    @SerializedName("dayOrder")
    private int mDayOrder;

    @SerializedName("dayStatus")
    private int mDayStatus;

    @SerializedName("punchFlag")
    private int mPunchFlag;

    @SerializedName("punchTime")
    private long mPunchTime;

    @SerializedName("recordList")
    private List<mob> mRecordData;

    @SerializedName("scheduledAction")
    private List<IntActionBean> mScheduledAction;

    @SerializedName("statistics")
    private List<StatInfoBean> mStatistics;

    public DayPlanBean() {
    }

    public DayPlanBean(IntDayPlan intDayPlan) {
        this.mDayOrder = intDayPlan.getDayOrder();
        this.mScheduledAction = new ArrayList();
        int i = 0;
        while (true) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction == null) {
                return;
            }
            this.mScheduledAction.add(new IntActionBean(inPlanAction));
            i++;
        }
    }

    public int getDayOrder() {
        return this.mDayOrder;
    }

    public void setDayOrder(int i) {
        this.mDayOrder = i;
    }

    public List<IntActionBean> getScheduledAction() {
        return this.mScheduledAction;
    }

    public void setScheduledAction(List<IntActionBean> list) {
        this.mScheduledAction = list;
    }

    public int getPunchFlag() {
        return this.mPunchFlag;
    }

    public void setPunchFlag(int i) {
        this.mPunchFlag = i;
    }

    public long getPunchTime() {
        return this.mPunchTime;
    }

    public void setPunchTime(long j) {
        this.mPunchTime = j;
    }

    public int getDayStatus() {
        return this.mDayStatus;
    }

    public void setDayStatus(int i) {
        this.mDayStatus = i;
    }

    public List<StatInfoBean> getStatistics() {
        return this.mStatistics;
    }

    public void setStatistics(List<StatInfoBean> list) {
        this.mStatistics = list;
    }

    public List<IntActionBean> getCustomAction() {
        return this.mCustomAction;
    }

    public void setCustomAction(List<IntActionBean> list) {
        this.mCustomAction = list;
    }

    public List<mob> getRecordData() {
        return this.mRecordData;
    }

    public void setRecordData(List<mob> list) {
        this.mRecordData = list;
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
