package com.huawei.hms.iapfull.network.model;

/* loaded from: classes9.dex */
public class WebPayPayResponse extends WebBasePayResponse {
    private String cashierUrl;
    private String errMsg;

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setCashierUrl(String str) {
        this.cashierUrl = str;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getCashierUrl() {
        return this.cashierUrl;
    }

    public WebPayPayResponse(int i, String str) {
        super(i, str);
    }
}
