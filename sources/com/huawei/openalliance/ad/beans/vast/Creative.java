package com.huawei.openalliance.ad.beans.vast;

/* loaded from: classes5.dex */
public class Creative {
    private final String adId;
    private final String id;
    private LinearCreative linearAd;
    private NonLinearAds nonLinearAd;
    private final Integer sequence;

    public NonLinearAds b() {
        return this.nonLinearAd;
    }

    public void a(NonLinearAds nonLinearAds) {
        this.nonLinearAd = nonLinearAds;
    }

    public void a(LinearCreative linearCreative) {
        this.linearAd = linearCreative;
    }

    public LinearCreative a() {
        return this.linearAd;
    }

    public Creative(String str, Integer num, String str2) {
        this.id = str;
        this.sequence = num;
        this.adId = str2;
    }
}
