package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class ProductDetail implements IMessageEntity {

    @Packed
    public String country;

    @Packed
    public String currency;

    @Packed
    public long microsPrice;

    @Packed
    public String price;

    @Packed
    public String productDesc;

    @Packed
    public String productName;

    @Packed
    public String productNo;

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
