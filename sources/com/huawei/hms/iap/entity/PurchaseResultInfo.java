package com.huawei.hms.iap.entity;

/* loaded from: classes4.dex */
public class PurchaseResultInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f4657a;
    private String b;
    private String c;
    private String d;
    private String e;

    public void setSignatureAlgorithm(String str) {
        this.e = str;
    }

    public void setReturnCode(int i) {
        this.f4657a = i;
    }

    public void setInAppPurchaseData(String str) {
        this.b = str;
    }

    public void setInAppDataSignature(String str) {
        this.c = str;
    }

    public void setErrMsg(String str) {
        this.d = str;
    }

    public String getSignatureAlgorithm() {
        return this.e;
    }

    public int getReturnCode() {
        return this.f4657a;
    }

    public String getInAppPurchaseData() {
        return this.b;
    }

    public String getInAppDataSignature() {
        return this.c;
    }

    public String getErrMsg() {
        return this.d;
    }
}
