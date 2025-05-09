package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;

/* loaded from: classes9.dex */
public class WebIsEnvReadyResponse extends WebBaseResponse implements b1 {
    private int returnCode;
    private String returnDesc;

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
}
