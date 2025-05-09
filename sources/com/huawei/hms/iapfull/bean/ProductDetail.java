package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;

/* loaded from: classes9.dex */
public class ProductDetail implements b1 {
    private String country;
    private String currency;
    private long microsPrice;
    private String price;
    private String productDesc;
    private String productName;
    private String productNo;

    public void setProductNo(String str) {
        this.productNo = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setMicrosPrice(long j) {
        this.microsPrice = j;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getProductNo() {
        return this.productNo;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public String getPrice() {
        return this.price;
    }

    public long getMicrosPrice() {
        return this.microsPrice;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCountry() {
        return this.country;
    }
}
