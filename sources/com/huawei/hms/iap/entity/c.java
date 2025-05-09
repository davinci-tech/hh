package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class c extends AbstractMessageEntity {

    @Packed
    private String errMsg;

    @Packed
    private Boolean isSandboxApk;

    @Packed
    private Boolean isSandboxUser;

    @Packed
    private int returnCode;

    @Packed
    private String versionFrMarket;

    @Packed
    private String versionInApk;

    public String getVersionInApk() {
        return this.versionInApk;
    }

    public String getVersionFrMarket() {
        return this.versionFrMarket;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public Boolean getIsSandboxUser() {
        return this.isSandboxUser;
    }

    public Boolean getIsSandboxApk() {
        return this.isSandboxApk;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
