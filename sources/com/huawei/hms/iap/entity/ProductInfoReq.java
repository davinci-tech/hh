package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.annotation.Packed;
import java.util.List;

/* loaded from: classes4.dex */
public class ProductInfoReq extends BaseReq {

    @Packed
    private int priceType;

    @Packed
    private List<String> skuIds;

    public void setProductIds(List<String> list) {
        this.skuIds = list;
    }

    public void setPriceType(int i) {
        this.priceType = i;
    }

    public List<String> getProductIds() {
        return this.skuIds;
    }

    public int getPriceType() {
        return this.priceType;
    }
}
