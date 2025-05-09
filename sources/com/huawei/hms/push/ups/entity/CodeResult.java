package com.huawei.hms.push.ups.entity;

/* loaded from: classes9.dex */
public class CodeResult {

    /* renamed from: a, reason: collision with root package name */
    private int f5693a;
    private String b;

    public CodeResult() {
    }

    public String getReason() {
        return this.b;
    }

    public int getReturnCode() {
        return this.f5693a;
    }

    public void setReason(String str) {
        this.b = str;
    }

    public void setReturnCode(int i) {
        this.f5693a = i;
    }

    public CodeResult(int i) {
        this.f5693a = i;
    }

    public CodeResult(int i, String str) {
        this.f5693a = i;
        this.b = str;
    }
}
