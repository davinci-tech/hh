package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.annotation.Packed;
import java.security.SecureRandom;
import java.util.Locale;

@Deprecated
/* loaded from: classes9.dex */
public class PurchaseIntentWithPriceReq extends BaseReq {

    @Packed
    private String amount;

    @Packed
    private String country;

    @Packed
    private String currency;

    @Packed
    private String developerPayload;

    @Packed
    private int priceType;

    @Packed
    private String productId;

    @Packed
    private String productName;

    @Packed
    private String sdkChannel;

    @Packed
    private String serviceCatalog;

    @Packed
    private String signatureAlgorithm;

    @Packed
    private String transactionId = createTransactionId();

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setServiceCatalog(String str) {
        this.serviceCatalog = str;
    }

    public void setSdkChannel(String str) {
        this.sdkChannel = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setPriceType(int i) {
        this.priceType = i;
    }

    public void setDeveloperPayload(String str) {
        this.developerPayload = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getServiceCatalog() {
        return this.serviceCatalog;
    }

    public String getSdkChannel() {
        return this.sdkChannel;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductId() {
        return this.productId;
    }

    public int getPriceType() {
        return this.priceType;
    }

    public String getDeveloperPayload() {
        return this.developerPayload;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAmount() {
        return this.amount;
    }

    private static String createTransactionId() {
        return (System.currentTimeMillis() + "") + String.format(Locale.ENGLISH, "%06d", Integer.valueOf(new SecureRandom().nextInt(1000000)));
    }
}
