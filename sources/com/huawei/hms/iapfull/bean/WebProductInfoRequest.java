package com.huawei.hms.iapfull.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class WebProductInfoRequest extends BaseWebRequest {
    private int priceType;
    private List<String> productIds;

    public void setProductIds(List<String> list) {
        this.productIds = list;
    }

    public void setPriceType(int i) {
        this.priceType = i;
    }

    public List<String> getProductIds() {
        return this.productIds;
    }

    public int getPriceType() {
        return this.priceType;
    }
}
