package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class ProductSummary {
    private String currency;
    private int giveawaysExist;
    private int linkType;
    private String linkValue;
    private boolean memberFlag;
    private String memberPriceRetentionDesc;
    private boolean memberPriceRetentionVisibility;
    private int memberType;
    private long microPrice;
    private long nowTime;
    private String priceDesc;
    private int priority;
    private String productId;
    private String productName;
    private TimeLimitedPromotion promotion;
    private int purchasePolicy;
    private long saleTime;

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

    public TimeLimitedPromotion getPromotion() {
        return this.promotion;
    }

    public void setPromotion(TimeLimitedPromotion timeLimitedPromotion) {
        this.promotion = timeLimitedPromotion;
    }

    public int getPurchasePolicy() {
        return this.purchasePolicy;
    }

    public void setPurchasePolicy(int i) {
        this.purchasePolicy = i;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public long getSaleTime() {
        return this.saleTime;
    }

    public void setSaleTime(long j) {
        this.saleTime = j;
    }

    public int getGiveawaysExist() {
        return this.giveawaysExist;
    }

    public void setGiveawaysExist(int i) {
        this.giveawaysExist = i;
    }

    public int getPriority() {
        return this.priority;
    }

    public boolean isMemberFlag() {
        return this.memberFlag;
    }

    public int getMemberType() {
        return this.memberType;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public void setMemberFlag(boolean z) {
        this.memberFlag = z;
    }

    public void setMemberType(int i) {
        this.memberType = i;
    }

    public String getPriceDesc() {
        return this.priceDesc;
    }

    public void setPriceDesc(String str) {
        this.priceDesc = str;
    }

    public boolean isMemberPriceRetentionVisibility() {
        return this.memberPriceRetentionVisibility;
    }

    public void setMemberPriceRetentionVisibility(boolean z) {
        this.memberPriceRetentionVisibility = z;
    }

    public String getMemberPriceRetentionDesc() {
        String str = this.memberPriceRetentionDesc;
        return str == null ? "" : str;
    }

    public void setMemberPriceRetentionDesc(String str) {
        this.memberPriceRetentionDesc = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public String getLinkValue() {
        String str = this.linkValue;
        return str == null ? "" : str;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String toString() {
        return "ProductSummary{productId='" + this.productId + "', purchasePolicy=" + this.purchasePolicy + ", productName='" + this.productName + "', currency='" + this.currency + "', microPrice=" + this.microPrice + ", promotion=" + this.promotion + ", nowTime=" + this.nowTime + ", saleTime=" + this.saleTime + ", giveawaysExist=" + this.giveawaysExist + ", priority=" + this.priority + ", memberFlag=" + this.memberFlag + ", memberType=" + this.memberType + ", memberPriceRetentionVisibility=" + this.memberPriceRetentionVisibility + ", memberPriceRetentionDesc='" + this.memberPriceRetentionDesc + "', priceDesc='" + this.priceDesc + "', linkType=" + this.linkType + ", linkValue='" + this.linkValue + "'}";
    }
}
