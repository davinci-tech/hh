package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.ApiNames;
import com.huawei.openalliance.ad.ek;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.inter.listeners.InterstitialAdListener;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.oy;
import com.huawei.openalliance.ad.utils.aa;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.utils.x;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class g implements oy.a {
    private Context b;
    private InterstitialAdListener c;
    private String d;
    private String e;
    private Location f;
    private IRewardAdStatusListener g;
    private String h;
    private Boolean i;
    private Bundle j;
    private String k;
    private oy m;
    private RequestOptions n;
    private oy.a o;
    private RewardVerifyConfig q;
    private Integer r;
    private VideoConfiguration s;
    private BiddingInfo t;

    /* renamed from: a, reason: collision with root package name */
    private c f7057a = c.IDLE;
    private DelayInfo l = new DelayInfo();
    private List<com.huawei.openalliance.ad.inter.data.b> p = new ArrayList();
    private com.huawei.openalliance.ad.inter.listeners.a u = new d(this);

    enum c {
        IDLE,
        LOADING
    }

    public BiddingInfo g() {
        BiddingInfo biddingInfo = this.t;
        return biddingInfo == null ? new BiddingInfo() : biddingInfo;
    }

    public final void f() {
        ho.b("InterstitialAdManager", "show, adlist= %s", Boolean.valueOf(this.p.isEmpty()));
        for (com.huawei.openalliance.ad.inter.data.b bVar : this.p) {
            if (bVar != null && !bVar.b_()) {
                VideoConfiguration videoConfiguration = this.s;
                if (videoConfiguration != null) {
                    bVar.a(videoConfiguration);
                }
                bVar.setRewardVerifyConfig(this.q);
                bVar.a(this.b, this.u);
                return;
            }
        }
    }

    public final Bundle e() {
        Bundle bundle = this.j;
        return bundle == null ? new Bundle() : bundle;
    }

    public final boolean d() {
        return this.f7057a == c.LOADING;
    }

    public final boolean c() {
        if (bg.a(this.p)) {
            return false;
        }
        for (com.huawei.openalliance.ad.inter.data.b bVar : this.p) {
            if (bVar != null && !bVar.b_()) {
                a(bVar.getBiddingInfo());
                return true;
            }
        }
        return false;
    }

    public void b(String str) {
        this.e = str;
    }

    public final String b() {
        return this.d;
    }

    @Override // com.huawei.openalliance.ad.oy.a
    public void a(final Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
        b(map);
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        ho.b("InterstitialAdManager", sb.toString());
        this.l.u().i(System.currentTimeMillis());
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.g.2
            @Override // java.lang.Runnable
            public void run() {
                g.this.l.b(System.currentTimeMillis());
                if (g.this.c != null) {
                    g.this.c.onAdLoaded();
                }
                if (g.this.o != null) {
                    g.this.o.a(map);
                }
                com.huawei.openalliance.ad.analysis.d.a(g.this.b, 200, g.this.k, 12, map, g.this.l);
            }
        });
        aa.b(this.b, map);
    }

    public void a(List<com.huawei.openalliance.ad.inter.data.b> list) {
        this.p = list;
    }

    public final void a(String str) {
        this.d = str;
    }

    public void a(Integer num) {
        this.r = num;
    }

    public void a(oy.a aVar) {
        this.o = aVar;
    }

    public final void a(InterstitialAdListener interstitialAdListener) {
        this.c = interstitialAdListener;
    }

    public final void a(IRewardAdStatusListener iRewardAdStatusListener) {
        if (this.g != null) {
            ho.b("InterstitialAdManager", "Update rewarded video listener.");
        }
        this.g = iRewardAdStatusListener;
    }

    public void a(RewardVerifyConfig rewardVerifyConfig) {
        this.q = rewardVerifyConfig;
    }

    public void a(BiddingInfo biddingInfo) {
        this.t = biddingInfo;
    }

    public final void a(RequestOptions requestOptions) {
        this.l.a(System.currentTimeMillis());
        ho.b("InterstitialAdManager", ApiNames.LOAD_AD);
        if (!ao.b(this.b)) {
            a(1001);
            return;
        }
        if (requestOptions != null && h()) {
            this.n = requestOptions;
            bi.b(this.b.getApplicationContext(), requestOptions);
            ek.a(this.b).e();
            this.f7057a = c.LOADING;
            this.p.clear();
            Pair<String, Boolean> b2 = com.huawei.openalliance.ad.utils.d.b(this.b, true);
            if (b2 == null && bz.h(this.b)) {
                ho.b("InterstitialAdManager", "start to request oaid " + System.currentTimeMillis());
                OAIDServiceManager.getInstance(this.b).requireOaid(new b());
                return;
            }
            if (b2 != null) {
                ho.b("InterstitialAdManager", "use cached oaid " + System.currentTimeMillis());
                c((String) b2.first);
                a(((Boolean) b2.second).booleanValue());
            }
            i();
        }
    }

    public void a(VideoConfiguration videoConfiguration) {
        this.s = videoConfiguration;
    }

    public void a(Location location) {
        this.f = location;
    }

    @Override // com.huawei.openalliance.ad.oy.a
    public void a(final int i) {
        ho.b("InterstitialAdManager", "onAdFailed: %s", Integer.valueOf(i));
        this.l.u().i(System.currentTimeMillis());
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.g.1
            @Override // java.lang.Runnable
            public void run() {
                g.this.l.b(System.currentTimeMillis());
                if (g.this.c != null) {
                    g.this.c.onAdFailed(i);
                }
                if (g.this.o != null) {
                    g.this.o.a(i);
                }
                com.huawei.openalliance.ad.analysis.d.a(g.this.b, i, g.this.k, 12, (Map) null, g.this.l);
            }
        });
    }

    public final InterstitialAdListener a() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        final long currentTimeMillis = System.currentTimeMillis();
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.g.3
            @Override // java.lang.Runnable
            public void run() {
                System.currentTimeMillis();
                g.this.l.d(System.currentTimeMillis() - currentTimeMillis);
                AdSlotParam.Builder builder = new AdSlotParam.Builder();
                g.this.a(builder);
                ol olVar = new ol(g.this.b);
                olVar.a(g.this.l);
                AdContentRsp b2 = olVar.b(builder.build());
                if (b2 != null) {
                    g.this.k = b2.k();
                }
                g.this.l.u().h(System.currentTimeMillis());
                g.this.m.a(b2);
                g.this.f7057a = c.IDLE;
            }
        }, m.a.NETWORK, false);
    }

    private boolean h() {
        if (!ao.b(this.b)) {
            InterstitialAdListener interstitialAdListener = this.c;
            if (interstitialAdListener != null) {
                interstitialAdListener.onAdFailed(1001);
            }
            return false;
        }
        if (this.f7057a == c.LOADING) {
            ho.b("InterstitialAdManager", "waiting for request finish");
            InterstitialAdListener interstitialAdListener2 = this.c;
            if (interstitialAdListener2 != null) {
                interstitialAdListener2.onAdFailed(1101);
            }
            return false;
        }
        if (!TextUtils.isEmpty(this.d)) {
            return true;
        }
        ho.c("InterstitialAdManager", "empty ad ids");
        InterstitialAdListener interstitialAdListener3 = this.c;
        if (interstitialAdListener3 != null) {
            interstitialAdListener3.onAdFailed(-4);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        this.h = str;
    }

    private void b(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (List<com.huawei.openalliance.ad.inter.data.b> list : map.values()) {
            if (!bg.a(list)) {
                for (com.huawei.openalliance.ad.inter.data.b bVar : list) {
                    if (bVar.isExpired() || !bVar.c_()) {
                        ho.b("InterstitialAdManager", "ad is invalid, content id:" + bVar.getContentId());
                    } else {
                        this.p.add(bVar);
                    }
                }
                Collections.sort(this.p, new a());
            }
        }
    }

    static class d implements com.huawei.openalliance.ad.inter.listeners.a {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<g> f7063a;

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void f() {
            g gVar = this.f7063a.get();
            if (gVar == null || gVar.c == null) {
                return;
            }
            gVar.c.onVideoStarted();
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void e() {
            g gVar = this.f7063a.get();
            if (gVar == null || gVar.c == null) {
                return;
            }
            gVar.c.onAdLeave();
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void d() {
            g gVar = this.f7063a.get();
            if (gVar != null) {
                if (gVar.c != null) {
                    gVar.c.onAdClosed();
                }
                if (gVar.g != null) {
                    gVar.g.onAdClosed();
                }
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void c() {
            g gVar = this.f7063a.get();
            if (gVar == null || gVar.g == null) {
                return;
            }
            gVar.g.onAdCompleted();
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void b() {
            g gVar = this.f7063a.get();
            if (gVar != null) {
                if (gVar.c != null) {
                    gVar.c.onAdClicked();
                }
                if (gVar.g != null) {
                    gVar.g.onAdClicked();
                }
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void a(int i, int i2) {
            g gVar = this.f7063a.get();
            if (gVar != null) {
                if (gVar.c != null) {
                    gVar.c.onAdFailed(i);
                }
                if (gVar.g != null) {
                    gVar.g.onAdError(i, i2);
                }
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void a() {
            g gVar = this.f7063a.get();
            if (gVar != null) {
                if (gVar.c != null) {
                    gVar.c.onAdOpened();
                }
                if (gVar.g != null) {
                    gVar.g.onAdShown();
                }
            }
        }

        public d(g gVar) {
            this.f7063a = new WeakReference<>(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.i = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AdSlotParam.Builder builder) {
        ArrayList arrayList = new ArrayList(1);
        int k = x.k(this.b);
        arrayList.add(this.d);
        builder.setAdIds(arrayList).setDeviceType(k).setTest(false).setOrientation(this.b.getResources().getConfiguration().orientation != 1 ? 0 : 1).setWidth(com.huawei.openalliance.ad.utils.d.d(this.b)).setHeight(com.huawei.openalliance.ad.utils.d.e(this.b)).setOaid(this.h).setTrackLimited(this.i).setRequestOptions(this.n).setContentBundle(this.e).setLocation(this.f);
        Integer num = this.r;
        if (num != null) {
            builder.c(num);
        }
    }

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

    class b extends OAIDServiceManager.OaidResultCallback {
        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public int b() {
            return 12;
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a(String str, boolean z) {
            ho.b("InterstitialAdManager", "onOaidAcquired " + System.currentTimeMillis());
            g.this.c(str);
            g.this.a(z);
            g.this.i();
            com.huawei.openalliance.ad.utils.d.a(g.this.b, str, z);
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a() {
            ho.b("InterstitialAdManager", "onOaidAcquireFailed " + System.currentTimeMillis());
            g.this.i();
        }

        public b() {
        }
    }

    public g(Context context) {
        this.b = context;
        this.m = new oy(context, this);
    }
}
