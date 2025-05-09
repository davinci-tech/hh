package com.huawei.openalliance.ad.inter.listeners;

import com.huawei.openalliance.ad.inter.data.INativeAd;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface NativeAdListener {
    void onAdFailed(int i);

    void onAdsLoaded(Map<String, List<INativeAd>> map);
}
