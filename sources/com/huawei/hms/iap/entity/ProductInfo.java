package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes4.dex */
public class ProductInfo implements IMessageEntity {

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
    private int status;

    @Packed
    private String subFreeTrialPeriod;

    @Packed
    private String subGroupId;

    @Packed
    private String subGroupTitle;

    @Packed
    private String subPeriod;

    @Packed
    private int subProductLevel;

    @Packed
    private String subSpecialPeriod;

    @Packed
    private int subSpecialPeriodCycles;

    @Packed
    private String subSpecialPrice;

    @Packed
    private long subSpecialPriceMicros;

    public void setSubSpecialPriceMicros(long j) {
        this.subSpecialPriceMicros = j;
    }

    public void setSubSpecialPrice(String str) {
        this.subSpecialPrice = str;
    }

    public void setSubSpecialPeriodCycles(int i) {
        this.subSpecialPeriodCycles = i;
    }

    public void setSubSpecialPeriod(String str) {
        this.subSpecialPeriod = str;
    }

    public void setSubProductLevel(int i) {
        this.subProductLevel = i;
    }

    public void setSubPeriod(String str) {
        this.subPeriod = str;
    }

    public void setSubGroupTitle(String str) {
        this.subGroupTitle = str;
    }

    public void setSubGroupId(String str) {
        this.subGroupId = str;
    }

    public void setSubFreeTrialPeriod(String str) {
        this.subFreeTrialPeriod = str;
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

    public long getSubSpecialPriceMicros() {
        return this.subSpecialPriceMicros;
    }

    public String getSubSpecialPrice() {
        return this.subSpecialPrice;
    }

    public int getSubSpecialPeriodCycles() {
        return this.subSpecialPeriodCycles;
    }

    public String getSubSpecialPeriod() {
        return this.subSpecialPeriod;
    }

    public int getSubProductLevel() {
        return this.subProductLevel;
    }

    public String getSubPeriod() {
        return this.subPeriod;
    }

    public String getSubGroupTitle() {
        return this.subGroupTitle;
    }

    public String getSubGroupId() {
        return this.subGroupId;
    }

    public String getSubFreeTrialPeriod() {
        return this.subFreeTrialPeriod;
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
