package com.huawei.hwsmartinteractmgr.data;

/* loaded from: classes9.dex */
public class CommodityInfo {
    private long mDiscountEndTime;
    private String mDiscountPrice;
    private long mDiscountStartTime;
    private String mImageUrl;
    private String mName;
    private String mOriginalPrice;
    private String mPurchaseUrl;

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public String getPurchaseUrl() {
        return this.mPurchaseUrl;
    }

    public void setPurchaseUrl(String str) {
        this.mPurchaseUrl = str;
    }

    public String getOriginalPrice() {
        return this.mOriginalPrice;
    }

    public void setOriginalPrice(String str) {
        this.mOriginalPrice = str;
    }

    public String getDiscountPrice() {
        return this.mDiscountPrice;
    }

    public void setDiscountPrice(String str) {
        this.mDiscountPrice = str;
    }

    public long getDiscountStartTime() {
        return this.mDiscountStartTime;
    }

    public void setDiscountStartTime(long j) {
        this.mDiscountStartTime = j;
    }

    public long getDiscountEndTime() {
        return this.mDiscountEndTime;
    }

    public void setDiscountEndTime(long j) {
        this.mDiscountEndTime = j;
    }
}
