package com.huawei.hms.iap.entity;

import com.huawei.hms.support.api.client.Result;
import java.util.List;

/* loaded from: classes4.dex */
public class ProductInfoResult extends Result {
    private String errMsg;
    private List<ProductInfo> productInfoList;
    private int returnCode;

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setProductInfoList(List<ProductInfo> list) {
        this.productInfoList = list;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public List<ProductInfo> getProductInfoList() {
        return this.productInfoList;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
