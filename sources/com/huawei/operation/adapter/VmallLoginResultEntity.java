package com.huawei.operation.adapter;

/* loaded from: classes9.dex */
public class VmallLoginResultEntity {
    private String code;
    private String euid;
    private String redirectUrl;
    private boolean success;
    private String ukmc;

    public String getEuid() {
        return this.euid;
    }

    public void setEuid(String str) {
        this.euid = str;
    }

    public String getUkmc() {
        return this.ukmc;
    }

    public void setUkmc(String str) {
        this.ukmc = str;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String str) {
        this.redirectUrl = str;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }
}
