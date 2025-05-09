package com.huawei.watchface.mvp.model.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class BriefDiscountInfo {
    private String discountCode;
    private String discountEndTime;
    private String discountId;
    private String discountPrice;
    private String discountType;
    private String fullDownUrls;
    private String givePrice;
    private String isActiveDiscount;
    private String markText;
    private String markUrl;
    private String priority;
    private String startTime;

    @SerializedName("UPDATEDATE")
    private String updateDate;

    public String getGivePrice() {
        return this.givePrice;
    }

    public void setGivePrice(String str) {
        this.givePrice = str;
    }

    public String getMarkUrl() {
        return this.markUrl;
    }

    public void setMarkUrl(String str) {
        this.markUrl = str;
    }

    public String getFullDownUrls() {
        return this.fullDownUrls;
    }

    public void setFullDownUrls(String str) {
        this.fullDownUrls = str;
    }

    public String getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(String str) {
        this.discountPrice = str;
    }

    public String getMarkText() {
        return this.markText;
    }

    public void setMarkText(String str) {
        this.markText = str;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String str) {
        this.priority = str;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public void setDiscountCode(String str) {
        this.discountCode = str;
    }

    public String getDiscountEndTime() {
        return this.discountEndTime;
    }

    public void setDiscountEndTime(String str) {
        this.discountEndTime = str;
    }

    public String getIsActiveDiscount() {
        return this.isActiveDiscount;
    }

    public void setIsActiveDiscount(String str) {
        this.isActiveDiscount = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getDiscountType() {
        return this.discountType;
    }

    public void setDiscountType(String str) {
        this.discountType = str;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String str) {
        this.updateDate = str;
    }

    public String getDiscountId() {
        return this.discountId;
    }

    public void setDiscountId(String str) {
        this.discountId = str;
    }
}
