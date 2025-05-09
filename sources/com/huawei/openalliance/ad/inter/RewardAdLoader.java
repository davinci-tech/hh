package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.location.Location;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.ek;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.listeners.RewardAdListener;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.po;
import com.huawei.openalliance.ad.qu;
import com.huawei.openalliance.ad.utils.aa;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class RewardAdLoader implements IRewardAdLoader, po.a {

    /* renamed from: a, reason: collision with root package name */
    private Context f7021a;
    private final String[] b;
    private qu c;
    private String e;
    private Boolean f;
    private RewardAdListener g;
    private RequestOptions h;
    private String i;
    private Location j;
    private String k;
    private Integer m;
    private b d = b.IDLE;
    private DelayInfo l = new DelayInfo();

    enum b {
        IDLE,
        LOADING
    }

    @Override // com.huawei.openalliance.ad.inter.IRewardAdLoader
    public void setRequestOptions(RequestOptions requestOptions) {
        this.h = requestOptions;
    }

    @Override // com.huawei.openalliance.ad.inter.IRewardAdLoader
    public void setLocation(Location location) {
        this.j = location;
    }

    @Override // com.huawei.openalliance.ad.inter.IRewardAdLoader
    public void setListener(RewardAdListener rewardAdListener) {
        this.g = rewardAdListener;
    }

    @Override // com.huawei.openalliance.ad.inter.IRewardAdLoader
    public void setContentBundle(String str) {
        this.i = str;
    }

    @Override // com.huawei.openalliance.ad.inter.IRewardAdLoader
    public void loadAds(int i, boolean z) {
        int i2;
        this.l.a(System.currentTimeMillis());
        ho.b("RewardAdLoader", "loadAds");
        if (!ao.b(this.f7021a)) {
            i2 = 1001;
        } else if (b.LOADING == this.d) {
            ho.b("RewardAdLoader", "waiting for request finish");
            i2 = 901;
        } else {
            String[] strArr = this.b;
            if (strArr != null && strArr.length != 0) {
                ek.a(this.f7021a).e();
                bi.b(this.f7021a, this.h);
                this.d = b.LOADING;
                Pair<String, Boolean> b2 = com.huawei.openalliance.ad.utils.d.b(this.f7021a, true);
                if (b2 == null && bz.h(this.f7021a)) {
                    ho.b("RewardAdLoader", "start to request oaid " + System.currentTimeMillis());
                    OAIDServiceManager.getInstance(this.f7021a).requireOaid(new a(i, z));
                    return;
                }
                if (b2 != null) {
                    ho.b("RewardAdLoader", "use cached oaid " + System.currentTimeMillis());
                    a((String) b2.first);
                    a(((Boolean) b2.second).booleanValue());
                }
                a(i, z);
                return;
            }
            ho.c("RewardAdLoader", "empty ad ids");
            i2 = 902;
        }
        a(i2);
    }

    @Override // com.huawei.openalliance.ad.po.a
    public void a(final Map<String, List<IRewardAd>> map) {
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        sb.append(", listener:");
        sb.append(this.g);
        ho.b("RewardAdLoader", sb.toString());
        if (this.g == null) {
            return;
        }
        this.l.u().i(System.currentTimeMillis());
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.RewardAdLoader.2
            @Override // java.lang.Runnable
            public void run() {
                RewardAdListener rewardAdListener = RewardAdLoader.this.g;
                RewardAdLoader.this.l.b(System.currentTimeMillis());
                if (rewardAdListener != null) {
                    rewardAdListener.onAdsLoaded(map);
                }
                com.huawei.openalliance.ad.analysis.d.a(RewardAdLoader.this.f7021a, 200, RewardAdLoader.this.k, 7, map, RewardAdLoader.this.l);
            }
        });
        aa.a(this.f7021a, map);
    }

    public void a(Integer num) {
        this.m = num;
    }

    @Override // com.huawei.openalliance.ad.po.a
    public void a(final int i) {
        ho.b("RewardAdLoader", "onAdFailed, errorCode:" + i);
        if (this.g == null) {
            return;
        }
        this.l.u().i(System.currentTimeMillis());
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.RewardAdLoader.3
            @Override // java.lang.Runnable
            public void run() {
                RewardAdListener rewardAdListener = RewardAdLoader.this.g;
                RewardAdLoader.this.l.b(System.currentTimeMillis());
                if (rewardAdListener != null) {
                    rewardAdListener.onAdFailed(i);
                }
                com.huawei.openalliance.ad.analysis.d.a(RewardAdLoader.this.f7021a, i, RewardAdLoader.this.k, 7, (Map) null, RewardAdLoader.this.l);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.f = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.e = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final boolean z) {
        final long currentTimeMillis = System.currentTimeMillis();
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.RewardAdLoader.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b("RewardAdLoader", "doRequestAd " + System.currentTimeMillis());
                RewardAdLoader.this.l.d(System.currentTimeMillis() - currentTimeMillis);
                AdSlotParam.Builder builder = new AdSlotParam.Builder();
                builder.setAdIds(Arrays.asList(RewardAdLoader.this.b)).setDeviceType(i).setOrientation(1).setWidth(com.huawei.openalliance.ad.utils.d.d(RewardAdLoader.this.f7021a)).setHeight(com.huawei.openalliance.ad.utils.d.e(RewardAdLoader.this.f7021a)).setOaid(RewardAdLoader.this.e).setTrackLimited(RewardAdLoader.this.f).setLocation(RewardAdLoader.this.j).setRequestOptions(RewardAdLoader.this.h).setTest(z).setContentBundle(RewardAdLoader.this.i);
                if (RewardAdLoader.this.m != null) {
                    builder.c(RewardAdLoader.this.m);
                }
                ol olVar = new ol(RewardAdLoader.this.f7021a);
                olVar.a(RewardAdLoader.this.l);
                AdContentRsp a2 = olVar.a(builder.build());
                if (a2 != null) {
                    RewardAdLoader.this.k = a2.k();
                }
                RewardAdLoader.this.l.u().h(System.currentTimeMillis());
                RewardAdLoader.this.c.a(a2);
                RewardAdLoader.this.d = b.IDLE;
            }
        }, m.a.NETWORK, false);
    }

    class a extends OAIDServiceManager.OaidResultCallback {
        private int b;
        private boolean c;

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public int b() {
            return 7;
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a(String str, boolean z) {
            ho.b("RewardAdLoader", "onOaidAcquired " + System.currentTimeMillis());
            RewardAdLoader.this.a(str);
            RewardAdLoader.this.a(z);
            RewardAdLoader.this.a(this.b, this.c);
            com.huawei.openalliance.ad.utils.d.a(RewardAdLoader.this.f7021a, str, z);
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a() {
            ho.b("RewardAdLoader", "onOaidAcquireFailed " + System.currentTimeMillis());
            RewardAdLoader.this.a(this.b, this.c);
        }

        public a(int i, boolean z) {
            this.b = i;
            this.c = z;
        }
    }

    public RewardAdLoader(Context context, String[] strArr) {
        if (!ao.b(context)) {
            this.b = new String[0];
            return;
        }
        this.f7021a = context.getApplicationContext();
        if (strArr == null || strArr.length <= 0) {
            this.b = new String[0];
        } else {
            String[] strArr2 = new String[strArr.length];
            this.b = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        }
        this.c = new po(context, this);
    }
}
