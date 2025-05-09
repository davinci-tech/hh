package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.annotation.Packed;
import java.security.SecureRandom;
import java.util.Locale;

/* loaded from: classes4.dex */
public class PurchaseIntentReq extends BaseReq {

    @Packed
    private String developerPayload;

    @Packed
    private int priceType;

    @Packed
    private String productId;

    @Packed
    private String signatureAlgorithm;

    @Packed
    private String transactionId = createTransactionId();

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
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

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
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

    private static String createTransactionId() {
        return (System.currentTimeMillis() + "") + String.format(Locale.ENGLISH, "%06d", Integer.valueOf(new SecureRandom().nextInt(1000000)));
    }
}
