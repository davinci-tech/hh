package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes5.dex */
public class Options {
    private String adRating;
    private Integer coppa;
    private Integer tfua;

    public boolean d() {
        return (this.coppa == null && this.tfua == null && this.adRating == null) ? false : true;
    }

    public String c() {
        return this.adRating;
    }

    public void b(Integer num) {
        this.tfua = num;
    }

    public Integer b() {
        return this.tfua;
    }

    public void a(String str) {
        this.adRating = str;
    }

    public void a(Integer num) {
        this.coppa = num;
    }

    public Integer a() {
        return this.coppa;
    }
}
