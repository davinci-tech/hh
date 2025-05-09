package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;

/* loaded from: classes4.dex */
public class PayReq extends BaseReq {

    @Packed
    public String amount;

    @Packed
    public String country;

    @Packed
    public String currency;

    @Packed
    public String expireTime;

    @Packed
    public String partnerIDs;

    @Packed
    public String productDesc;

    @Packed
    public String productName;

    @Packed
    public int validTime;

    public int getValidTime() {
        return this.validTime;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public String getPartnerIDs() {
        return this.partnerIDs;
    }

    public String getExpireTime() {
        return this.expireTime;
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
}
