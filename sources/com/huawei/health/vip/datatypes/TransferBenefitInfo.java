package com.huawei.health.vip.datatypes;

/* loaded from: classes8.dex */
public class TransferBenefitInfo {
    private long benefitEndTime;
    private long benefitExpirationTime;
    private long benefitStartTime;
    private long expireTimeAfterTransfer;
    private long nowTime;
    private String orderCode;
    private long purchaseTime;
    private String resourceId;
    private int resourceType;
    private TransferInfo transferInfo;

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public long getPurchaseTime() {
        return this.purchaseTime;
    }

    public void setPurchaseTime(long j) {
        this.purchaseTime = j;
    }

    public long getBenefitStartTime() {
        return this.benefitStartTime;
    }

    public void setBenefitStartTime(long j) {
        this.benefitStartTime = j;
    }

    public long getBenefitEndTime() {
        return this.benefitEndTime;
    }

    public void setBenefitEndTime(long j) {
        this.benefitEndTime = j;
    }

    public long getBenefitExpirationTime() {
        return this.benefitExpirationTime;
    }

    public void setBenefitExpirationTime(long j) {
        this.benefitExpirationTime = j;
    }

    public long getExpireTimeAfterTransfer() {
        return this.expireTimeAfterTransfer;
    }

    public void setExpireTimeAfterTransfer(long j) {
        this.expireTimeAfterTransfer = j;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public TransferInfo getTransferInfo() {
        return this.transferInfo;
    }

    public void setTransferInfo(TransferInfo transferInfo) {
        this.transferInfo = transferInfo;
    }

    public String toString() {
        return "TransferBenefitInfo{orderCode='" + this.orderCode + "', resourceType=" + this.resourceType + ", resourceId='" + this.resourceId + "', purchaseTime=" + this.purchaseTime + ", benefitStartTime=" + this.benefitStartTime + ", benefitEndTime=" + this.benefitEndTime + ", benefitExpirationTime=" + this.benefitExpirationTime + ", expireTimeAfterTransfer=" + this.expireTimeAfterTransfer + ", nowTime=" + this.nowTime + ", transferInfo=" + this.transferInfo + '}';
    }
}
