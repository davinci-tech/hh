package com.huawei.openalliance.ad.inter.listeners;

/* loaded from: classes5.dex */
public interface InterstitialAdListener {
    void onAdClicked();

    void onAdClosed();

    void onAdFailed(int i);

    void onAdImpression();

    void onAdLeave();

    void onAdLoaded();

    void onAdOpened();

    void onVideoStarted();
}
