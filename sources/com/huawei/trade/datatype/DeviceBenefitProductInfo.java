package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes6.dex */
public class DeviceBenefitProductInfo {

    @SerializedName(HwPayConstant.KEY_CURRENCY)
    private String currency;

    @SerializedName("duration")
    private String duration;

    @SerializedName("microPrice")
    private long microPrice;

    @SerializedName("nowTime")
    private long nowTime;

    @SerializedName("priority")
    private int priority;

    @SerializedName(HwPayConstant.KEY_PRODUCTDESC)
    private String productDesc;

    @SerializedName("productId")
    private String productId;

    @SerializedName(HwPayConstant.KEY_PRODUCTNAME)
    private String productName;

    @SerializedName("productType")
    private String productType;

    @SerializedName("productUrl")
    private String productUrl;

    @SerializedName("purchaseChannel")
    private int purchaseChannel;

    @SerializedName("purchasePolicy")
    private int purchasePolicy;

    @SerializedName("title")
    private String title;

    @SerializedName("titleVisibility")
    private boolean titleVisibility;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public int getPurchasePolicy() {
        return this.purchasePolicy;
    }

    public void setPurchasePolicy(int i) {
        this.purchasePolicy = i;
    }

    public int getPurchaseChannel() {
        return this.purchaseChannel;
    }

    public void setPurchaseChannel(int i) {
        this.purchaseChannel = i;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public boolean isTitleVisibility() {
        return this.titleVisibility;
    }

    public void setTitleVisibility(boolean z) {
        this.titleVisibility = z;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public long getMicroPrice() {
        return this.microPrice;
    }

    public void setMicroPrice(long j) {
        this.microPrice = j;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }
}
