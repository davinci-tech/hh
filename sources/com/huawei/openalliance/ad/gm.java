package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.InterstitialAd;
import com.huawei.openalliance.ad.inter.listeners.InterstitialAdCallback;
import com.huawei.openalliance.ad.inter.listeners.InterstitialAdListener;
import com.huawei.openalliance.ad.oy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class gm implements gl {

    /* renamed from: a, reason: collision with root package name */
    private InterstitialAdListener f6882a;
    private InterstitialAdCallback b;
    private InterstitialAd c;
    private Context d;
    private List<com.huawei.openalliance.ad.inter.data.b> e = new ArrayList();
    private oy.a f;

    static class a implements Serializable, Comparator<com.huawei.openalliance.ad.inter.data.b> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(com.huawei.openalliance.ad.inter.data.b bVar, com.huawei.openalliance.ad.inter.data.b bVar2) {
            if (a(bVar.getBiddingInfo()) && !a(bVar2.getBiddingInfo())) {
                return -1;
            }
            if (a(bVar.getBiddingInfo()) && a(bVar2.getBiddingInfo())) {
                return (bVar.getBiddingInfo().getPrice().floatValue() <= 0.0f || Float.compare(bVar.getBiddingInfo().getPrice().floatValue(), bVar2.getBiddingInfo().getPrice().floatValue()) >= 0) ? -1 : 1;
            }
            return 1;
        }

        private boolean a(BiddingInfo biddingInfo) {
            return (biddingInfo == null || biddingInfo.getPrice() == null) ? false : true;
        }

        a() {
        }
    }

    @Override // com.huawei.openalliance.ad.gl
    public void a(Map map) {
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        ho.b("InterstitialAd", sb.toString());
        b(map);
        InterstitialAdListener interstitialAdListener = this.f6882a;
        if (interstitialAdListener != null) {
            interstitialAdListener.onAdLoaded();
        }
        InterstitialAdCallback interstitialAdCallback = this.b;
        if (interstitialAdCallback != null) {
            interstitialAdCallback.onAdsLoaded(this.c);
        }
        oy.a aVar = this.f;
        if (aVar != null) {
            aVar.a((Map<String, List<com.huawei.openalliance.ad.inter.data.b>>) map);
        }
        com.huawei.openalliance.ad.utils.aa.b(this.d, map);
    }

    @Override // com.huawei.openalliance.ad.gl
    public void a(int i) {
        ho.b("InterstitialAd", "onAdFailed, errorCode:" + i);
        InterstitialAdListener interstitialAdListener = this.f6882a;
        if (interstitialAdListener != null) {
            interstitialAdListener.onAdFailed(i);
        }
        oy.a aVar = this.f;
        if (aVar != null) {
            aVar.a(i);
        }
    }

    private void b(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (List<com.huawei.openalliance.ad.inter.data.b> list : map.values()) {
            if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
                for (com.huawei.openalliance.ad.inter.data.b bVar : list) {
                    if (bVar.isExpired() || !bVar.c_()) {
                        ho.b("InterstitialAd", "expired is true, content id:" + bVar.getContentId());
                    } else {
                        this.e.add(bVar);
                    }
                }
                Collections.sort(this.e, new a());
                this.c.a(this.e);
            }
        }
    }

    public gm(Context context, InterstitialAdCallback interstitialAdCallback, oy.a aVar) {
        this.d = context;
        this.b = interstitialAdCallback;
        this.f = aVar;
        if (interstitialAdCallback != null) {
            this.f6882a = interstitialAdCallback.getInterstitialAdListener();
        }
        if (this.c == null) {
            InterstitialAd interstitialAd = new InterstitialAd(context);
            this.c = interstitialAd;
            interstitialAd.setAdListener(this.f6882a);
        }
        this.e.clear();
    }
}
