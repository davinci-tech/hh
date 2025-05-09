package com.huawei.openalliance.ad.inter;

import android.content.Context;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;

/* loaded from: classes5.dex */
public interface IHiAdSplash {
    void dismissExSplash();

    void dismissExSplashSlogan();

    void enableLinkedVideo(boolean z);

    Integer getAllowMobileTraffic();

    boolean isAvailable(AdSlotParam adSlotParam);

    boolean isExSplashEnable(Context context);

    boolean isSmartSplashAvailable(AdSlotParam adSlotParam);

    void preloadAd();

    void preloadAd(AdSlotParam adSlotParam);

    void preloadSmartScreenAd();

    void preloadSmartScreenAd(AdSlotParam adSlotParam);

    void setAllowMobileTraffic(int i);

    void setDefaultSplashMode(int i);

    void setExSplashShowTime(int i);

    void setSloganDefTime(int i);

    void setSloganShowTimeWhenNoAd(int i);

    void setSmartScreenSloganTime(int i);

    void setUsePostAtFront(boolean z);
}
