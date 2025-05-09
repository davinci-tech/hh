package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import java.util.List;

/* loaded from: classes.dex */
public abstract class PlanInfo {

    @SerializedName("commodityDescImg")
    protected String mCommodityDescImg;

    @SerializedName("commodityFlag")
    protected int mCommodityFlag;

    @SerializedName("cornerImgDisplay")
    protected int mCornerImgDisplay;

    @SerializedName("poster")
    protected PlanPoster mPlanPoster;

    @SerializedName("cornerList")
    protected List<PriceTagBean> mPriceTagBeanList;

    @SerializedName("transactionStatus")
    protected int mTransactionStatus;

    public abstract List<String> getLabels();

    public abstract int getType();

    public abstract void setLabels(List<String> list);

    public List<PriceTagBean> getPriceTagBeanList() {
        return this.mPriceTagBeanList;
    }

    public void setPriceTagBeanList(List<PriceTagBean> list) {
        this.mPriceTagBeanList = list;
    }

    public int getCommodityFlag() {
        return this.mCommodityFlag;
    }

    public void setCommodityFlag(int i) {
        this.mCommodityFlag = i;
    }

    public int getCornerImgDisplay() {
        return this.mCornerImgDisplay;
    }

    public void setCommodityDescImg(String str) {
        this.mCommodityDescImg = str;
    }

    public String getCommodityDescImg() {
        return this.mCommodityDescImg;
    }

    public void setCornerImgDisplay(int i) {
        this.mCornerImgDisplay = i;
    }

    public int getTransactionStatus() {
        return this.mTransactionStatus;
    }

    public void setTransactionStatus(int i) {
        this.mTransactionStatus = i;
    }

    public PlanPoster getPlanPoster() {
        return this.mPlanPoster;
    }

    public void setPlanPoster(PlanPoster planPoster) {
        this.mPlanPoster = planPoster;
    }
}
