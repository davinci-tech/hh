package com.huawei.basefitnessadvice.model.intplan;

import com.huawei.basefitnessadvice.model.intplan.IntPlan;

/* loaded from: classes8.dex */
public class PlanIntroInfo {
    private String description;
    private String imgUrl;
    private String name;
    private String planId;
    private IntPlan.PlanType planType;
    private int status;

    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public IntPlan.PlanType getPlanType() {
        return this.planType;
    }

    public void setPlanType(IntPlan.PlanType planType) {
        this.planType = planType;
    }
}
