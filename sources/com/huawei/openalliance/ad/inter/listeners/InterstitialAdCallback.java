package com.huawei.openalliance.ad.inter.listeners;

import com.huawei.openalliance.ad.inter.data.InterstitialAd;

/* loaded from: classes5.dex */
public abstract class InterstitialAdCallback {

    /* renamed from: a, reason: collision with root package name */
    private InterstitialAdListener f7066a;

    public abstract void onAdsLoaded(InterstitialAd interstitialAd);

    public void setInterstitialAdListener(InterstitialAdListener interstitialAdListener) {
        this.f7066a = interstitialAdListener;
    }

    public InterstitialAdListener getInterstitialAdListener() {
        return this.f7066a;
    }

    public InterstitialAdCallback(InterstitialAdListener interstitialAdListener) {
        this.f7066a = interstitialAdListener;
    }
}
