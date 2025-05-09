package com.huawei.hms.iap.entity;

/* loaded from: classes7.dex */
public class RedeemCodeResultInfo {

    /* renamed from: a, reason: collision with root package name */
    private String f4658a;
    private int b = 1;

    public void setReturnCode(int i) {
        this.b = i;
    }

    public void setRedeemCode(String str) {
        this.f4658a = str;
    }

    public int getReturnCode() {
        return this.b;
    }

    public String getRedeemCode() {
        return this.f4658a;
    }
}
