package com.huawei.health.plan.model.intplan;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.Goal;

/* loaded from: classes3.dex */
public class GoalBean {

    @SerializedName("actual")
    private Object mActual;

    @SerializedName("estimate")
    private Object mEstimate;

    @SerializedName("src")
    private Object mSrc;

    @SerializedName("type")
    private String mType;

    @SerializedName("value")
    private Object mValue;

    public GoalBean(String str, Object obj) {
        this.mType = str;
        this.mValue = obj;
    }

    public GoalBean(Goal goal) {
        this.mType = goal.getStatType();
        this.mValue = goal.getGoalDst();
        this.mActual = goal.getGoalActual();
        this.mSrc = goal.getGoalSrc();
        this.mEstimate = goal.getGoalEstimate();
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public Object getValue() {
        return this.mValue;
    }

    public void setValue(Object obj) {
        this.mValue = obj;
    }
}
