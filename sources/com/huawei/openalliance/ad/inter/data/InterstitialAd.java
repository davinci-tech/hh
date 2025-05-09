package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.inter.listeners.InterstitialAdListener;
import java.util.List;

/* loaded from: classes5.dex */
public class InterstitialAd {

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.openalliance.ad.inter.g f7045a;

    public final void show() {
        this.f7045a.f();
    }

    public void setVideoConfiguration(VideoConfiguration videoConfiguration) {
        this.f7045a.a(videoConfiguration);
    }

    public final void setRewardVerifyConfig(RewardVerifyConfig rewardVerifyConfig) {
        this.f7045a.a(rewardVerifyConfig);
    }

    public final void setRewardAdStatusListener(IRewardAdStatusListener iRewardAdStatusListener) {
        this.f7045a.a(iRewardAdStatusListener);
    }

    public final void setLocation(Location location) {
        this.f7045a.a(location);
    }

    public final void setContentBundle(String str) {
        this.f7045a.b(str);
    }

    public final void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.f7045a.a(interstitialAdListener);
    }

    public final void setAdId(String str) {
        this.f7045a.a(str);
    }

    public final void loadAd(RequestOptions requestOptions) {
        this.f7045a.a(requestOptions);
    }

    public final boolean isLoading() {
        return this.f7045a.d();
    }

    public final boolean isLoaded() {
        return this.f7045a.c();
    }

    public BiddingInfo getBiddingInfo() {
        return this.f7045a.g();
    }

    public final Bundle getAdMetadata() {
        return this.f7045a.e();
    }

    public final InterstitialAdListener getAdListener() {
        return this.f7045a.a();
    }

    public final String getAdId() {
        return this.f7045a.b();
    }

    public void a(List<b> list) {
        this.f7045a.a(list);
    }

    public InterstitialAd(Context context) {
        this.f7045a = new com.huawei.openalliance.ad.inter.g(context);
    }
}
