package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class CouponResult {
    private String couponId;
    private int resultCode;

    public String getCouponId() {
        return this.couponId;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public void setCouponId(String str) {
        this.couponId = str;
    }

    public String toString() {
        return "CouponResult{couponId='" + this.couponId + "', resultCode=" + this.resultCode + '}';
    }
}
