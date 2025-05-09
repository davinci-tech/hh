package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;
import java.util.List;

/* loaded from: classes9.dex */
public class WebProductDetailResponse extends WebBaseResponse implements b1 {
    private String errMsg;
    private List<ProductFailObject> failList;
    private List<ProductDetail> productList;
    private String requestId;
    private int returnCode;

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductList(List<ProductDetail> list) {
        this.productList = list;
    }

    public void setFailList(List<ProductFailObject> list) {
        this.failList = list;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

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
