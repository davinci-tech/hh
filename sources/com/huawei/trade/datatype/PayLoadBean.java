package com.huawei.trade.datatype;

/* loaded from: classes8.dex */
public class PayLoadBean {
    private String currency;
    private String deepLink;
    private long microPrice;
    private String productId;
    private String productName;
    private String type;

    public String getType() {
        return this.type;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setDeepLink(String str) {
        this.deepLink = str;
    }

    public void setMicroPrice(long j) {
        this.microPrice = j;
    }

    public long getMicroPrice() {
        return this.microPrice;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getDeepLink() {
        return this.deepLink;
    }
}
