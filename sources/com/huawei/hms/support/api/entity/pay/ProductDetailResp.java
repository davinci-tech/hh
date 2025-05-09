package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import java.util.List;

/* loaded from: classes9.dex */
public class ProductDetailResp extends AbstractMessageEntity {

    @Packed
    public String errMsg;

    @Packed
    public List<ProductFailObject> failList;

    @Packed
    public List<ProductDetail> productList;

    @Packed
    public String requestId;

    @Packed
    public int returnCode;

    public int getReturnCode() {
        return this.returnCode;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public List<ProductDetail> getProductList() {
        return this.productList;
    }

    public List<ProductFailObject> getFailList() {
        return this.failList;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
