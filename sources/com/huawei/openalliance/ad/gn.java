package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import java.util.Map;

/* loaded from: classes5.dex */
public class gn implements gl {

    /* renamed from: a, reason: collision with root package name */
    private NativeAdListener f6883a;

    @Override // com.huawei.openalliance.ad.gl
    public void a(Map map) {
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        sb.append(", listener:");
        sb.append(this.f6883a);
        ho.b("NativeAd", sb.toString());
        NativeAdListener nativeAdListener = this.f6883a;
        if (nativeAdListener != null) {
            nativeAdListener.onAdsLoaded(map);
        }
    }

    @Override // com.huawei.openalliance.ad.gl
    public void a(int i) {
        ho.b("NativeAd", "onAdFailed, errorCode:" + i);
        NativeAdListener nativeAdListener = this.f6883a;
        if (nativeAdListener != null) {
            nativeAdListener.onAdFailed(i);
        }
    }

    public gn(NativeAdListener nativeAdListener) {
        this.f6883a = nativeAdListener;
    }
}
