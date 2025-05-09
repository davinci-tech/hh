package com.huawei.hwversionmgr.info;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class EnterpriseResponseInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String enterprise;
    private String expiredTime;

    public String getEnterprise() {
        return this.enterprise;
    }

    public void setEnterprise(String str) {
        this.enterprise = str;
    }

    public String getExpiredTime() {
        return this.expiredTime;
    }

    public void setExpiredTime(String str) {
        this.expiredTime = str;
    }
}
