package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class ProductDetailRequest implements IMessageEntity {

    @Packed
    public String applicationID;

    @Packed
    public String merchantId;

    @Packed
    public String productNos;

    @Packed
    public String requestId;

    @Packed
    public String reservedInfor;

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getProductNos() {
        return this.productNos;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getApplicationID() {
        return this.applicationID;
    }
}
