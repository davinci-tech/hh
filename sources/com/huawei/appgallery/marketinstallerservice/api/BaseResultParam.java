package com.huawei.appgallery.marketinstallerservice.api;

/* loaded from: classes3.dex */
public class BaseResultParam {
    private int d = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f1876a = 0;
    private int b = 0;
    private int c = 0;

    public void setRtnCode(int i) {
        this.c = i;
    }

    public void setResult(int i) {
        this.d = i;
    }

    public void setResponseCode(int i) {
        this.b = i;
    }

    public void setReason(int i) {
        this.f1876a = i;
    }

    public int getRtnCode() {
        return this.c;
    }

    public int getResult() {
        return this.d;
    }

    public int getResponseCode() {
        return this.b;
    }

    public int getReason() {
        return this.f1876a;
    }
}
