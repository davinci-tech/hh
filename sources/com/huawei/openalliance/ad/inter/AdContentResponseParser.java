package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.AnalyticsEventType;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.gl;
import com.huawei.openalliance.ad.gm;
import com.huawei.openalliance.ad.gn;
import com.huawei.openalliance.ad.go;
import com.huawei.openalliance.ad.gp;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.AdParseConfig;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.IPreCheckInfo;
import com.huawei.openalliance.ad.inter.listeners.InterstitialAdCallback;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.openalliance.ad.inter.listeners.NativePreCheckInfoListener;
import com.huawei.openalliance.ad.inter.listeners.PlacementAdListener;
import com.huawei.openalliance.ad.inter.listeners.RewardAdListener;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.om;
import com.huawei.openalliance.ad.oy;
import com.huawei.openalliance.ad.pt;
import com.huawei.openalliance.ad.qm;
import com.huawei.openalliance.ad.utils.ab;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.openalliance.ad.utils.bo;
import com.huawei.openalliance.ad.utils.ct;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class AdContentResponseParser implements IAdContentResponseParser, om.a {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Integer, gl> f6952a;
    private qm b;
    private Context c;
    private String d;
    private long e;
    private long f;
    private ol g;
    private boolean h;
    private NativePreCheckInfoListener i;
    private String j;

    @Override // com.huawei.openalliance.ad.inter.IAdContentResponseParser
    public void stopCache() {
        gl glVar = this.f6952a.get(60);
        if (glVar instanceof go) {
            ((go) glVar).a();
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IAdContentResponseParser
    public void startCache(int i) {
        gl glVar = this.f6952a.get(60);
        if (glVar instanceof go) {
            ((go) glVar).b(i);
        }
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Context f6959a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f = false;
        private RewardAdListener g;
        private NativeAdListener h;
        private PlacementAdListener i;
        private InterstitialAdCallback j;
        private oy.a k;
        private NativeAdListener l;
        private NativeAdListener m;
        private NativePreCheckInfoListener n;

        public Builder setSearchAdListener(NativeAdListener nativeAdListener) {
            this.m = nativeAdListener;
            return this;
        }

        public Builder setRewardAdListener(RewardAdListener rewardAdListener) {
            this.g = rewardAdListener;
            return this;
        }

        public Builder setPlacementAdListener(PlacementAdListener placementAdListener) {
            this.i = placementAdListener;
            return this;
        }

        public Builder setNativePreCheckInfoListener(NativePreCheckInfoListener nativePreCheckInfoListener) {
            this.n = nativePreCheckInfoListener;
            return this;
        }

        public Builder setNativeAdListener(NativeAdListener nativeAdListener) {
            this.h = nativeAdListener;
            return this;
        }

        public Builder setInterstitialAdCallback(InterstitialAdCallback interstitialAdCallback) {
            this.j = interstitialAdCallback;
            return this;
        }

        public Builder setIconAdListener(NativeAdListener nativeAdListener) {
            this.l = nativeAdListener;
            return this;
        }

        public Builder setAutoCache(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setAdsReturnedFromThread(boolean z) {
            this.f = z;
            return this;
        }

        public Builder enableVideoDownloadInMobileNetwork(boolean z) {
            this.d = z;
            return this;
        }

        public Builder enableDirectReturnVideoAd(boolean z) {
            this.c = z;
            return this;
        }

        public Builder enableDirectCacheVideo(boolean z) {
            this.e = z;
            return this;
        }

        public AdContentResponseParser build() {
            return new AdContentResponseParser(this);
        }

        public Builder a(oy.a aVar) {
            this.k = aVar;
            return this;
        }

        public Builder(Context context) {
            this.f6959a = context;
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IAdContentResponseParser
    public void processAdResponse(String str, boolean z) {
        AdParseConfig.Builder builder = new AdParseConfig.Builder();
        builder.setAccelerate(false);
        if (!z) {
            builder.setUpdateConfig(false);
        } else {
            if (TextUtils.isEmpty(ct.a().c())) {
                ho.c("AdContentResponseParser", "must set media router country code first");
                a(-1, -1);
                return;
            }
            builder.setUpdateConfig(true);
        }
        a(str, builder.build());
    }

    @Override // com.huawei.openalliance.ad.inter.IAdContentResponseParser
    public void processAdResponse(String str, AdParseConfig adParseConfig) {
        if (!a(adParseConfig)) {
            ho.a(5);
        }
        if (adParseConfig == null) {
            a(str, new AdParseConfig.Builder().build());
        } else if (!adParseConfig.b() || !TextUtils.isEmpty(ct.a().c())) {
            a(str, adParseConfig);
        } else {
            ho.c("AdContentResponseParser", "must set media router country code first");
            a(-1, -1);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IAdContentResponseParser
    public void processAdResponse(String str) {
        AdParseConfig.Builder builder = new AdParseConfig.Builder();
        builder.setUpdateConfig(false).setAccelerate(false);
        a(str, builder.build());
    }

    @Override // com.huawei.openalliance.ad.om.a
    public void a(Map map, Map map2) {
        gl glVar = this.f6952a.get(60);
        if (glVar instanceof go) {
            go goVar = (go) glVar;
            goVar.a(this.d);
            goVar.a(this.e);
            goVar.a((Map<String, List<IPlacementAd>>) map, (Map<String, List<IPlacementAd>>) map2);
            goVar.b();
        }
    }

    @Override // com.huawei.openalliance.ad.om.a
    public void a(final int i, final Map map) {
        HashMap<Integer, gl> hashMap = this.f6952a;
        if (hashMap == null || !hashMap.containsKey(Integer.valueOf(i))) {
            return;
        }
        if (map == null || map.isEmpty()) {
            a(i, 204);
        } else if (!this.h) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.4
                @Override // java.lang.Runnable
                public void run() {
                    AdContentResponseParser.this.b(i, map);
                }
            });
        } else {
            ho.b("AdContentResponseParser", "onAdLoaded in Thread");
            b(i, map);
        }
    }

    @Override // com.huawei.openalliance.ad.om.a
    public void a(int i, int i2) {
        if (i == -1) {
            a(i2);
        } else {
            b(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Map map) {
        this.f6952a.get(Integer.valueOf(i)).a(map);
        bo.a(this.j);
        bo.a(new a(map, this.c.getApplicationContext()), this.j, 500L);
    }

    private void b(final int i, final int i2) {
        HashMap<Integer, gl> hashMap = this.f6952a;
        if (hashMap == null || !hashMap.containsKey(Integer.valueOf(i))) {
            return;
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.6
            @Override // java.lang.Runnable
            public void run() {
                gl glVar = (gl) AdContentResponseParser.this.f6952a.get(Integer.valueOf(i));
                int a2 = ab.a(i, i2);
                AdContentResponseParser.this.f = System.currentTimeMillis();
                glVar.a(a2);
            }
        });
    }

    private boolean a(AdParseConfig adParseConfig) {
        Context context = this.c;
        if (context == null || adParseConfig == null || Constants.HW_BROWSER_PACKAGE.equalsIgnoreCase(context.getPackageName())) {
            return false;
        }
        return adParseConfig.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<IPreCheckInfo> list) {
        if (!this.h) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.3
                @Override // java.lang.Runnable
                public void run() {
                    AdContentResponseParser.this.i.onPreCheck(list);
                }
            });
            return;
        }
        try {
            this.i.onPreCheck(list);
        } catch (Throwable unused) {
            ho.c("AdContentResponseParser", "native preCheck notify err!");
        }
    }

    private void a(final String str, final AdParseConfig adParseConfig) {
        ho.b("AdContentResponseParser", "processAdResponse");
        if (bl.a(this.f6952a)) {
            ho.c("AdContentResponseParser", "ad callbacks has't been set");
            return;
        }
        if (!ao.b(this.c)) {
            ho.c("AdContentResponseParser", "api level too low");
            a(-1, 1001);
        } else if (!TextUtils.isEmpty(str)) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.1
                @Override // java.lang.Runnable
                public void run() {
                    Pair<String, Map<Integer, AdContentRsp>> a2 = AdContentResponseParser.this.g.a(str, AdContentResponseParser.this.e, AdContentResponseParser.this.f6952a.keySet(), adParseConfig.b());
                    AdContentResponseParser.this.a((String) a2.first);
                    AdContentResponseParser.this.a(a2, adParseConfig);
                    AdContentResponseParser.this.b.a(AdContentResponseParser.this.a((Map<Integer, AdContentRsp>) a2.second), AdContentResponseParser.this.e, adParseConfig);
                }
            });
        } else {
            ho.b("AdContentResponseParser", "request ad fail");
            a(-1, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.d = str;
    }

    private void a(Builder builder) {
        this.f6952a = new HashMap<>(4);
        if (builder.h != null) {
            this.f6952a.put(3, new gn(builder.h));
        }
        if (builder.l != null) {
            this.f6952a.put(9, new gn(builder.l));
        }
        if (builder.m != null) {
            this.f6952a.put(13, new gn(builder.m));
        }
        if (builder.g != null) {
            this.f6952a.put(7, new gp(this.c, builder.g));
        }
        if (builder.j != null || builder.k != null) {
            this.f6952a.put(12, new gm(this.c, builder.j, builder.k));
        }
        if (builder.i != null) {
            this.f6952a.put(60, new go(this.c, builder.i, builder.b));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Pair<String, Map<Integer, AdContentRsp>> pair, AdParseConfig adParseConfig) {
        if (!adParseConfig.d() || pair.second == null || this.i == null) {
            return;
        }
        final AdContentRsp adContentRsp = (AdContentRsp) ((Map) pair.second).get(3);
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.2
            @Override // java.lang.Runnable
            public void run() {
                AdContentResponseParser.this.a(pt.a(AdContentResponseParser.this.c, adContentRsp));
            }
        });
    }

    private void a(final int i) {
        HashMap<Integer, gl> hashMap = this.f6952a;
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.5
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry entry : AdContentResponseParser.this.f6952a.entrySet()) {
                    ((gl) entry.getValue()).a(ab.a(((Integer) entry.getKey()).intValue(), i));
                }
            }
        });
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Map f6960a;
        private Context b;

        @Override // java.lang.Runnable
        public void run() {
            if (!bl.a(this.f6960a)) {
                a(this.f6960a.keySet());
            }
            ho.a(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Set<String> set) {
            if (set == null || set.size() == 0) {
                ho.d("AdContentResponseParser", "slotIds is empty");
                return;
            }
            String packageName = this.b.getPackageName();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                com.huawei.openalliance.ad.utils.h.a(this.b, packageName, AnalyticsEventType.AD_REQ, String.valueOf(1), it.next(), "");
            }
        }

        private void a(final Set<String> set) {
            m.h(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentResponseParser.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b(set);
                }
            });
        }

        public a(Map map, Context context) {
            this.f6960a = map;
            this.b = context;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Integer, AdContentRsp> a(Map<Integer, AdContentRsp> map) {
        Set<Integer> keySet = this.f6952a.keySet();
        Iterator<Map.Entry<Integer, AdContentRsp>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (!keySet.contains(Integer.valueOf(it.next().getKey().intValue()))) {
                it.remove();
            }
        }
        Iterator<Integer> it2 = keySet.iterator();
        while (it2.hasNext()) {
            int intValue = it2.next().intValue();
            if (!map.containsKey(Integer.valueOf(intValue))) {
                b(intValue, ErrorCode.ERROR_CODE_OTHER);
            }
        }
        return map;
    }

    private AdContentResponseParser(Builder builder) {
        this.e = System.currentTimeMillis();
        this.h = false;
        this.i = null;
        this.j = "delay_options_timeout_task_id" + hashCode();
        if (builder == null || builder.f6959a == null || !ao.b(builder.f6959a)) {
            return;
        }
        this.c = builder.f6959a;
        a(builder);
        if (this.f6952a.isEmpty()) {
            return;
        }
        om omVar = new om(this.c, this);
        this.b = omVar;
        omVar.a(builder.c);
        this.b.b(builder.d);
        this.b.c(builder.e);
        this.g = new ol(this.c);
        this.h = builder.f;
        this.i = builder.n;
    }
}
