package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.ProductSummary;

/* loaded from: classes8.dex */
public class OrderUnpaidRsp extends BaseRsp {
    private long expireTime;
    private long nowTime;
    private ProductSummary productSummary;

    public ProductSummary getProductSummary() {
        return this.productSummary;
    }

    public void setProductSummary(ProductSummary productSummary) {
        this.productSummary = productSummary;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }
}
