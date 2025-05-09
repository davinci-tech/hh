package com.huawei.openalliance.ad.inter.listeners;

/* loaded from: classes5.dex */
public interface IRewardAdStatusListener {
    void onAdClicked();

    void onAdClosed();

    void onAdCompleted();

    void onAdError(int i, int i2);

    void onAdShown();

    void onRewarded();
}
