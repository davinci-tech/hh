package com.huawei.hms.iapfull.network.model;

import com.huawei.openalliance.ad.constant.ParamConstants;

/* loaded from: classes9.dex */
public class WebBasePayResponse extends WebBaseResponse {
    public static final int CODE_FAIL = -1;
    public static final int CODE_SUCCESS = 0;
    protected int returnCode;
    protected String returnDesc;

    public void setReturnDesc(String str) {
        this.returnDesc = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public String getReturnDesc() {
        return this.returnDesc;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public WebBasePayResponse(int i, String str) {
        this.returnCode = i;
        this.returnDesc = str;
    }

    public WebBasePayResponse(int i) {
        this.returnCode = i;
    }

    public WebBasePayResponse() {
        this.returnCode = -1;
        this.returnDesc = ParamConstants.CallbackMethod.ON_FAIL;
    }
}
