package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;

/* loaded from: classes9.dex */
public class ProductFailObject implements b1 {
    private int code;
    private String msg;
    private String productNo;

    public void setProductNo(String str) {
        this.productNo = str;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getProductNo() {
        return this.productNo;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}
