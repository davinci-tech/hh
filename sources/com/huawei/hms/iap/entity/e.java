package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes4.dex */
public class e implements IMessageEntity {

    @Packed
    private String currency;

    @Packed
    private long microsPrice;

    @Packed
    private int offerUsedStatus = -1;

    @Packed
    private String originalLocalPrice;

    @Packed
    private long originalMicroPrice;

    @Packed
    private String price;

    @Packed
    private int priceType;

    @Packed
    private String productDesc;

    @Packed
    private String productId;

    @Packed
    private String productName;

    @Packed
    public int status;

    @Packed
    private String subsFreeTrialPeriod;

    @Packed
    private String subsGroupId;

    @Packed
    private String subsGroupTitle;

    @Packed
    private String subsIntroductoryPeriod;

    @Packed
    private int subsIntroductoryPeriodCycles;

    @Packed
    private String subsIntroductoryPrice;

    @Packed
    private long subsIntroductoryPriceMicros;

    @Packed
    private int subsProductLevel;

    @Packed
    private String subscriptionPeriod;

    public ProductInfo toProductInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(getProductId());
        productInfo.setPriceType(getPriceType());
        productInfo.setPrice(getPrice());
        productInfo.setMicrosPrice(getMicrosPrice());
        productInfo.setOriginalLocalPrice(getOriginalLocalPrice());
        productInfo.setOriginalMicroPrice(getOriginalMicroPrice());
        productInfo.setCurrency(getCurrency());
        productInfo.setProductName(getProductName());
        productInfo.setProductDesc(getProductDesc());
        productInfo.setSubPeriod(getSubscriptionPeriod());
        productInfo.setSubSpecialPrice(getSubsIntroductoryPrice());
        productInfo.setOfferUsedStatus(getOfferUsedStatus());
        productInfo.setSubSpecialPriceMicros(getSubsIntroductoryPriceMicros());
        productInfo.setSubSpecialPeriod(getSubsIntroductoryPeriod());
        productInfo.setSubSpecialPeriodCycles(getSubsIntroductoryPeriodCycles());
        productInfo.setSubFreeTrialPeriod(getSubsFreeTrialPeriod());
        productInfo.setSubGroupId(getSubsGroupId());
        productInfo.setSubGroupTitle(getSubsGroupTitle());
        productInfo.setSubProductLevel(getSubsProductLevel());
        productInfo.setStatus(getStatus());
        return productInfo;
    }

    public void setSubscriptionPeriod(String str) {
        this.subscriptionPeriod = str;
    }

    public void setSubsProductLevel(int i) {
        this.subsProductLevel = i;
    }

    public void setSubsIntroductoryPriceMicros(long j) {
        this.subsIntroductoryPriceMicros = j;
    }

    public void setSubsIntroductoryPrice(String str) {
        this.subsIntroductoryPrice = str;
    }

    public void setSubsIntroductoryPeriodCycles(int i) {
        this.subsIntroductoryPeriodCycles = i;
    }

    public void setSubsIntroductoryPeriod(String str) {
        this.subsIntroductoryPeriod = str;
    }

    public void setSubsGroupTitle(String str) {
        this.subsGroupTitle = str;
    }

    public void setSubsGroupId(String str) {
        this.subsGroupId = str;
    }

    public void setSubsFreeTrialPeriod(String str) {
        this.subsFreeTrialPeriod = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public void setPriceType(int i) {
        this.priceType = i;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setOriginalMicroPrice(long j) {
        this.originalMicroPrice = j;
    }

    public void setOriginalLocalPrice(String str) {
        this.originalLocalPrice = str;
    }

    public void setOfferUsedStatus(int i) {
        this.offerUsedStatus = i;
    }

    public void setMicrosPrice(long j) {
        this.microsPrice = j;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getSubscriptionPeriod() {
        return this.subscriptionPeriod;
    }

    public int getSubsProductLevel() {
        return this.subsProductLevel;
    }

    public long getSubsIntroductoryPriceMicros() {
        return this.subsIntroductoryPriceMicros;
    }

    public String getSubsIntroductoryPrice() {
        return this.subsIntroductoryPrice;
    }

    public int getSubsIntroductoryPeriodCycles() {
        return this.subsIntroductoryPeriodCycles;
    }

    public String getSubsIntroductoryPeriod() {
        return this.subsIntroductoryPeriod;
    }

    public String getSubsGroupTitle() {
        return this.subsGroupTitle;
    }

    public String getSubsGroupId() {
        return this.subsGroupId;
    }

    public String getSubsFreeTrialPeriod() {
        return this.subsFreeTrialPeriod;
    }

    public int getStatus() {
        return this.status;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public int getPriceType() {
        return this.priceType;
    }

    public String getPrice() {
        return this.price;
    }

    public long getOriginalMicroPrice() {
        return this.originalMicroPrice;
    }

    public String getOriginalLocalPrice() {
        return this.originalLocalPrice;
    }

    public int getOfferUsedStatus() {
        return this.offerUsedStatus;
    }

    public long getMicrosPrice() {
        return this.microsPrice;
    }

    public String getCurrency() {
        return this.currency;
    }
}
