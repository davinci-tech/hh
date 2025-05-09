package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class PayRequest {
    private String mActivityPath;
    private String mPayLoad;
    private String mProductId;
    private String mPurchasePolicy;

    public void setActivityPath(String str) {
        this.mActivityPath = str;
    }

    public String getActivityPath() {
        return this.mActivityPath;
    }

    public void setProductId(String str) {
        this.mProductId = str;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public void setPurchasePolicy(String str) {
        this.mPurchasePolicy = str;
    }

    public String getPurchasePolicy() {
        return this.mPurchasePolicy;
    }

    public void setPayLoad(String str) {
        this.mPayLoad = str;
    }

    public String getPayLoad() {
        return this.mPayLoad;
    }
}
