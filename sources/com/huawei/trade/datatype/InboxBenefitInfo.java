package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class InboxBenefitInfo {
    private int benefitType;
    private boolean isMainBenefit;
    private String productId;
    private DeviceBenefitProductInfo productInfo;

    public int getBenefitType() {
        return this.benefitType;
    }

    public void setBenefitType(int i) {
        this.benefitType = i;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public DeviceBenefitProductInfo getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(DeviceBenefitProductInfo deviceBenefitProductInfo) {
        this.productInfo = deviceBenefitProductInfo;
    }

    public boolean isMainBenefit() {
        return this.isMainBenefit;
    }

    public void setMainBenefit(boolean z) {
        this.isMainBenefit = z;
    }
}
