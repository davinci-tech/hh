package com.huawei.trade.datatype;

/* loaded from: classes9.dex */
public class MemberSummary {
    private String currency;
    private long microOriginPrice;
    private long microPrice;
    private String productId;
    private String productName;

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

    public long getMicroOriginPrice() {
        return this.microOriginPrice;
    }

    public void setMicroOriginPrice(long j) {
        this.microOriginPrice = j;
    }

    public String toString() {
        return "MemberSummary{productId='" + this.productId + "', productName='" + this.productName + "', currency='" + this.currency + "', microPrice=" + this.microPrice + ", microOriginPrice=" + this.microOriginPrice + '}';
    }
}
