package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;

/* loaded from: classes9.dex */
public class ProductPayRequest extends BaseReq {

    @Packed
    public String expireTime;

    @Packed
    public String productNo;

    @Packed
    public int validTime;

    public int getValidTime() {
        return this.validTime;
    }

    public String getProductNo() {
        return this.productNo;
    }

    public String getExpireTime() {
        return this.expireTime;
    }
}
