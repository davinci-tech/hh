package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class Coupon {
    private String balance;
    private String couponDescription;
    private String couponId;
    private String couponName;
    private String couponSubTitle;
    private int couponType;
    private long createTime;
    private String currency;
    private String discount;
    private int effectiveDays;
    private long effectiveEndTime;
    private int effectiveMode;
    private long effectiveStartTime;
    private String faceValue;
    private int feeMode;
    private int linkType;
    private String linkValue;
    private String minFee;
    private boolean outOfStockFlag;
    private boolean showToReceiveFlag;
    private int status;
    private String subFee;
    private String userLabels;
    private boolean vipFlag;
    private int weight;

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public boolean isVipFlag() {
        return this.vipFlag;
    }

    public void setVipFlag(boolean z) {
        this.vipFlag = z;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setUserLabels(String str) {
        this.userLabels = str;
    }

    public String getUserLabels() {
        return this.userLabels;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setEffectiveDays(int i) {
        this.effectiveDays = i;
    }

    public int getEffectiveDays() {
        return this.effectiveDays;
    }

    public void setEffectiveEndTime(long j) {
        this.effectiveEndTime = j;
    }

    public long getEffectiveEndTime() {
        return this.effectiveEndTime;
    }

    public void setEffectiveStartTime(long j) {
        this.effectiveStartTime = j;
    }

    public long getEffectiveStartTime() {
        return this.effectiveStartTime;
    }

    public void setEffectiveMode(int i) {
        this.effectiveMode = i;
    }

    public int getEffectiveMode() {
        return this.effectiveMode;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public String getFaceValue() {
        return this.faceValue;
    }

    public void setFaceValue(String str) {
        this.faceValue = str;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public String getSubFee() {
        return this.subFee;
    }

    public void setSubFee(String str) {
        this.subFee = str;
    }

    public String getMinFee() {
        return this.minFee;
    }

    public void setMinFee(String str) {
        this.minFee = str;
    }

    public int getFeeMode() {
        return this.feeMode;
    }

    public void setFeeMode(int i) {
        this.feeMode = i;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public int getCouponType() {
        return this.couponType;
    }

    public void setCouponType(int i) {
        this.couponType = i;
    }

    public String getCouponDescription() {
        return this.couponDescription;
    }

    public void setCouponDescription(String str) {
        this.couponDescription = str;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String str) {
        this.couponName = str;
    }

    public String getCouponId() {
        return this.couponId;
    }

    public void setCouponId(String str) {
        this.couponId = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getCouponSubTitle() {
        return this.couponSubTitle;
    }

    public void setCouponSubTitle(String str) {
        this.couponSubTitle = str;
    }

    public boolean isOutOfStockFlag() {
        return this.outOfStockFlag;
    }

    public boolean isShowToReceiveFlag() {
        return this.showToReceiveFlag;
    }

    public void setOutOfStockFlag(boolean z) {
        this.outOfStockFlag = z;
    }

    public String toString() {
        return "Coupon{couponId='" + this.couponId + "', couponName='" + this.couponName + "', couponDescription='" + this.couponDescription + "', couponType=" + this.couponType + ", linkType=" + this.linkType + ", linkValue='" + this.linkValue + "', feeMode=" + this.feeMode + ", minFee='" + this.minFee + "', subFee='" + this.subFee + "', discount='" + this.discount + "', faceValue='" + this.faceValue + "', balance='" + this.balance + "', effectiveMode=" + this.effectiveMode + ", effectiveStartTime=" + this.effectiveStartTime + ", effectiveEndTime=" + this.effectiveEndTime + ", effectiveDays=" + this.effectiveDays + ", status=" + this.status + ", userLabels='" + this.userLabels + "', weight=" + this.weight + ", createTime=" + this.createTime + ", currency='" + this.currency + "', couponSubTitle='" + this.couponSubTitle + "', outOfStockFlag=" + this.outOfStockFlag + ", vipFlag=" + this.vipFlag + '}';
    }
}
