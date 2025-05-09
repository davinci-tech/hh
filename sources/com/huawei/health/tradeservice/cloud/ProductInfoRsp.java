package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.ProductSummary;
import java.util.List;

/* loaded from: classes4.dex */
public class ProductInfoRsp extends BaseRsp {
    private List<ProductSummary> productSummaryList;

    public List<ProductSummary> getProductSummaryList() {
        return this.productSummaryList;
    }

    public void setProductSummaryList(List<ProductSummary> list) {
        this.productSummaryList = list;
    }
}
