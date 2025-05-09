package com.huawei.health.plan.model.intplan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes3.dex */
public class PlanMetaInfoBean {
    private static final String TAG = "PlanMetaInfoBean";

    @SerializedName("cardImage")
    private String mCardImage;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("exeDayNum")
    private int mExeDayNum;

    @SerializedName("exeDays")
    private String mExeDays;

    @SerializedName("name")
    private String mName;

    @SerializedName("openPageBigImage")
    private String mOpenPageBigImage;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("planId")
    private String mPlanId;

    @SerializedName("planStatus")
    private int mPlanStatus;

    @SerializedName("planTempId")
    private String mPlanTempId;

    @SerializedName("planType")
    private int mPlanType;

    public PlanMetaInfoBean() {
        this.mPlanType = 2;
    }

    public PlanMetaInfoBean(IntPlan intPlan) {
        this.mPlanType = 2;
        PlanMetaInfo metaInfo = intPlan.getMetaInfo();
        if (metaInfo != null) {
            this.mPlanId = metaInfo.getPlanId();
            this.mPlanTempId = metaInfo.getPlanTempId();
            this.mPlanType = metaInfo.getPlanType().getType();
            this.mName = metaInfo.getName();
            this.mPlanStatus = metaInfo.getPlanStatus();
            this.mDescription = metaInfo.getDescription();
            this.mExeDayNum = metaInfo.getExeDayNum();
            this.mExeDays = metaInfo.getExeDays();
            this.mCardImage = metaInfo.getCardImage();
            this.mPicture = metaInfo.getPicture();
            this.mOpenPageBigImage = metaInfo.getOpenPageBigImage();
        }
    }

    public int getPlanType() {
        return this.mPlanType;
    }

    public void setPlanType(int i) {
        this.mPlanType = i;
    }

    public int getPlanStatus() {
        return this.mPlanStatus;
    }

    public void setPlanStatus(int i) {
        this.mPlanStatus = i;
    }

    public String getPlanId() {
        return this.mPlanId;
    }

    public void setPlanId(String str) {
        this.mPlanId = str;
    }

    public String getPlanTempId() {
        return this.mPlanTempId;
    }

    public void setPlanTempId(String str) {
        this.mPlanTempId = str;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public int getExeDayNum() {
        return this.mExeDayNum;
    }

    public void setExeDayNum(int i) {
        this.mExeDayNum = i;
    }

    public String getExeDays() {
        return this.mExeDays;
    }

    public void setExeDays(String str) {
        this.mExeDays = str;
    }

    public void setPicture(String str) {
        this.mPicture = str;
    }

    public String getCardImage() {
        return this.mCardImage;
    }

    public void setCardImage(String str) {
        this.mCardImage = str;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public String getOpenPageBigImage() {
        return this.mOpenPageBigImage;
    }

    public void setOpenPageBigImage(String str) {
        this.mOpenPageBigImage = str;
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
