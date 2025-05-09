package com.huawei.hms.support.api.pay;

import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.entity.pay.ProductDetail;
import com.huawei.hms.support.api.entity.pay.ProductFailObject;
import java.util.List;

/* loaded from: classes9.dex */
public class ProductDetailResult extends Result {
    private List<ProductFailObject> failList;
    private List<ProductDetail> productList;
    private String requestId;

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductList(List<ProductDetail> list) {
        this.productList = list;
    }

    public void setFailList(List<ProductFailObject> list) {
        this.failList = list;
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
}
