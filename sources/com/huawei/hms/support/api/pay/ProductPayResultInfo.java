package com.huawei.hms.support.api.pay;

/* loaded from: classes9.dex */
public class ProductPayResultInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f5965a;
    private String b;
    private String c;
    private String d;
    private long e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    public void setTime(String str) {
        this.h = str;
    }

    public void setSignatureAlgorithm(String str) {
        this.m = str;
    }

    public void setSign(String str) {
        this.k = str;
    }

    public void setReturnCode(int i) {
        this.f5965a = i;
    }

    public void setRequestId(String str) {
        this.i = str;
    }

    public void setProductNo(String str) {
        this.j = str;
    }

    public void setOrderID(String str) {
        this.d = str;
    }

    public void setNewSign(String str) {
        this.l = str;
    }

    public void setMicrosAmount(long j) {
        this.e = j;
    }

    public void setMerchantId(String str) {
        this.c = str;
    }

    public void setErrMsg(String str) {
        this.b = str;
    }

    public void setCurrency(String str) {
        this.f = str;
    }

    public void setCountry(String str) {
        this.g = str;
    }

    public String getTime() {
        return this.h;
    }

    public String getSignatureAlgorithm() {
        return this.m;
    }

    public String getSign() {
        return this.k;
    }

    public int getReturnCode() {
        return this.f5965a;
    }

    public String getRequestId() {
        return this.i;
    }

    public String getProductNo() {
        return this.j;
    }

    public String getOrderID() {
        return this.d;
    }

    public String getNewSign() {
        return this.l;
    }

    public long getMicrosAmount() {
        return this.e;
    }

    public String getMerchantId() {
        return this.c;
    }

    public String getErrMsg() {
        return this.b;
    }

    public String getCurrency() {
        return this.f;
    }

    public String getCountry() {
        return this.g;
    }
}
