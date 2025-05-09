package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;

/* loaded from: classes9.dex */
public class WithholdRequest extends BaseReq {

    @Packed
    public String amount;

    @Packed
    public String country;

    @Packed
    public String currency;

    @Packed
    public String productDesc;

    @Packed
    public String productName;

    @Packed
    public String tradeType;

    public String getTradeType() {
        return this.tradeType;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDesc() {
        return this.productDesc;
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
