package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.ek;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.AdParseConfig;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.listeners.ContentIdListener;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.pe;
import com.huawei.openalliance.ad.qs;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class NativeAdLoader implements INativeAdLoader, pe.a {
    private static final String b = "NativeAdLoader";
    private String A;

    /* renamed from: a, reason: collision with root package name */
    boolean f7005a;
    private a c;
    private Context d;
    private final String[] e;
    private String f;
    private Boolean g;
    private NativeAdListener h;
    private qs i;
    private String j;
    private int k;
    private List<String> l;
    private RequestOptions m;
    private String n;
    private Location o;
    private String p;
    private DelayInfo q;
    private Integer r;
    private List<Integer> s;
    private boolean t;
    private Integer u;
    private Integer v;
    private Integer w;
    private Integer x;
    private Integer y;
    private int z;

    enum a {
        IDLE,
        LOADING
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setSupportTptAd(boolean z) {
        ho.b(b, "setSupportTptAd: %s", Boolean.valueOf(z));
        this.t = z;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setRequestOptions(RequestOptions requestOptions) {
        this.m = requestOptions;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setMaxAdNumbers(int i) {
        this.z = i;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setLocation(Location location) {
        this.o = location;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setListener(NativeAdListener nativeAdListener) {
        this.h = nativeAdListener;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setJssdkVersion(String str) {
        ho.b(b, "setJssdkVersion: %s", str);
        this.A = str;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setIsSmart(Integer num) {
        this.u = num;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setExtraInfo(String str) {
        this.j = str;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setDetailedCreativeType(List<Integer> list) {
        this.s = list;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setContentIdListener(ContentIdListener contentIdListener) {
        qs qsVar = this.i;
        if (qsVar != null) {
            qsVar.a(contentIdListener);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setContentBundle(String str) {
        this.n = str;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setAudioTotalDuration(Integer num) {
        this.y = num;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setAdsReturnedFromThread(boolean z) {
        this.f7005a = z;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setAdWidth(Integer num) {
        this.v = num;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void setAdHeight(Integer num) {
        this.w = num;
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void loadAds(int i, boolean z) {
        loadAds(i, null, z);
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void loadAds(final int i, final String str, final boolean z) {
        int i2;
        RequestOptions requestOptions;
        this.q.a(System.currentTimeMillis());
        String str2 = b;
        ho.b(str2, "loadAds");
        if (!ao.b(this.d)) {
            i2 = 1001;
        } else if (a.LOADING == this.c) {
            ho.b(str2, "waiting for request finish");
            i2 = 701;
        } else {
            String[] strArr = this.e;
            if (strArr == null || strArr.length == 0) {
                ho.c(str2, "empty ad ids");
                i2 = 702;
            } else {
                if (this.k != 13 || ((requestOptions = this.m) != null && !TextUtils.isEmpty(requestOptions.getSearchTerm()))) {
                    bi.b(this.d, this.m);
                    ek.a(this.d).e();
                    this.c = a.LOADING;
                    Pair<String, Boolean> b2 = com.huawei.openalliance.ad.utils.d.b(this.d, true);
                    if (b2 == null && fh.b(this.d).bf() && bz.h(this.d)) {
                        ho.b(str2, "start to request oaid " + System.currentTimeMillis());
                        OAIDServiceManager.getInstance(this.d).requireOaid(new OAIDServiceManager.OaidResultCallback() { // from class: com.huawei.openalliance.ad.inter.NativeAdLoader.1
                            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                            public int b() {
                                return NativeAdLoader.this.k;
                            }

                            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                            public void a(String str3, boolean z2) {
                                ho.b(NativeAdLoader.b, "onOaidAcquired " + System.currentTimeMillis());
                                NativeAdLoader.this.a(str3);
                                NativeAdLoader.this.a(Boolean.valueOf(z2));
                                NativeAdLoader.this.a(i, str, z);
                                com.huawei.openalliance.ad.utils.d.a(NativeAdLoader.this.d, str3, z2);
                            }

                            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                            public void a() {
                                ho.b(NativeAdLoader.b, "onOaidAcquireFailed " + System.currentTimeMillis());
                                NativeAdLoader.this.a(i, str, z);
                            }
                        });
                        return;
                    }
                    if (b2 != null) {
                        ho.b(str2, "use cached oaid " + System.currentTimeMillis());
                        a((String) b2.first);
                        a((Boolean) b2.second);
                    }
                    a(i, str, z);
                    return;
                }
                ho.c(str2, "search ad's searchTerm is empty");
                i2 = 1000;
            }
        }
        a(i2);
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void enableDirectReturnVideoAd(boolean z) {
        qs qsVar = this.i;
        if (qsVar != null) {
            qsVar.a(z);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.INativeAdLoader
    public void enableDirectCacheVideo(boolean z) {
        qs qsVar = this.i;
        if (qsVar != null) {
            qsVar.c(z);
        }
    }

    public void b(Integer num) {
        this.x = num;
        this.q.c(num);
    }

    @Override // com.huawei.openalliance.ad.pe.a
    public void a(final Map<String, List<INativeAd>> map) {
        String str = b;
        StringBuilder sb = new StringBuilder("onAdsLoaded, size:");
        sb.append(map != null ? Integer.valueOf(map.size()) : null);
        sb.append(", listener:");
        sb.append(this.h);
        ho.b(str, sb.toString());
        if (this.h == null) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.q.u().i(currentTimeMillis);
        if (this.f7005a) {
            this.q.b(currentTimeMillis);
            ho.b(str, "onAdsLoaded thread");
            this.h.onAdsLoaded(map);
            com.huawei.openalliance.ad.analysis.d.a(this.d, 200, this.p, this.k, map, this.q);
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.NativeAdLoader.3
                @Override // java.lang.Runnable
                public void run() {
                    NativeAdListener nativeAdListener = NativeAdLoader.this.h;
                    long currentTimeMillis2 = System.currentTimeMillis();
                    NativeAdLoader.this.q.b(currentTimeMillis2);
                    long j = currentTimeMillis2 - currentTimeMillis;
                    NativeAdLoader.this.q.g(j);
                    ho.b(NativeAdLoader.b, "onAdsLoaded main thread switch: %s ms", Long.valueOf(j));
                    if (nativeAdListener != null) {
                        nativeAdListener.onAdsLoaded(map);
                    }
                    com.huawei.openalliance.ad.analysis.d.a(NativeAdLoader.this.d, 200, NativeAdLoader.this.p, NativeAdLoader.this.k, map, NativeAdLoader.this.q);
                }
            });
        }
        b(map);
    }

    public void a(Integer num) {
        this.r = num;
    }

    @Override // com.huawei.openalliance.ad.pe.a
    public void a(final int i) {
        String str = b;
        ho.b(str, "onAdFailed, errorCode:" + i);
        if (this.h == null) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.q.u().i(currentTimeMillis);
        if (!this.f7005a) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.NativeAdLoader.5
                @Override // java.lang.Runnable
                public void run() {
                    NativeAdListener nativeAdListener = NativeAdLoader.this.h;
                    long currentTimeMillis2 = System.currentTimeMillis();
                    NativeAdLoader.this.q.b(currentTimeMillis2);
                    long j = currentTimeMillis2 - currentTimeMillis;
                    NativeAdLoader.this.q.g(j);
                    ho.b(NativeAdLoader.b, "onAdFailed main thread switch: %s ms", Long.valueOf(j));
                    if (nativeAdListener != null) {
                        nativeAdListener.onAdFailed(i);
                    }
                    com.huawei.openalliance.ad.analysis.d.a(NativeAdLoader.this.d, i, NativeAdLoader.this.p, NativeAdLoader.this.k, (Map) null, NativeAdLoader.this.q);
                }
            });
            return;
        }
        this.q.b(currentTimeMillis);
        ho.b(str, "onAdFailed thread");
        this.h.onAdFailed(i);
        com.huawei.openalliance.ad.analysis.d.a(this.d, i, this.p, this.k, (Map) null, this.q);
    }

    private List<AdContentData> d(Map<String, List<INativeAd>> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<INativeAd>> entry : map.entrySet()) {
            if (entry != null && !bg.a(entry.getValue())) {
                for (INativeAd iNativeAd : entry.getValue()) {
                    if (iNativeAd instanceof com.huawei.openalliance.ad.inter.data.e) {
                        com.huawei.openalliance.ad.inter.data.e eVar = (com.huawei.openalliance.ad.inter.data.e) iNativeAd;
                        if (99 == eVar.getCreativeType()) {
                            arrayList.add(eVar.v());
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Map<String, List<INativeAd>> map) {
        String str;
        String str2;
        if (bl.a(map)) {
            str = b;
            str2 = "nativeAdsMap is empty";
        } else {
            List<AdContentData> d = d(map);
            if (!bg.a(d)) {
                if (com.huawei.openalliance.ad.utils.c.a() && com.huawei.openalliance.ad.e.b() != null) {
                    try {
                        String b2 = be.b(d);
                        ho.a(b, "contentJson : %s", b2);
                        if (this.i == null) {
                            com.huawei.openalliance.ad.e.b().a(b2, (Bundle) null);
                            return true;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(ParamConstants.Param.DOWNLOAD_IN_DATA, this.i.a());
                        bundle.putBoolean("directCacheVideo", this.i.b());
                        com.huawei.openalliance.ad.e.b().a(b2, bundle);
                        return true;
                    } catch (Throwable th) {
                        ho.c(b, "down err: %s", th.getClass().getSimpleName());
                    }
                }
                return false;
            }
            str = b;
            str2 = "tpt ads is empty";
        }
        ho.a(str, str2);
        return false;
    }

    private void b(final Map<String, List<INativeAd>> map) {
        m.d(new Runnable() { // from class: com.huawei.openalliance.ad.inter.NativeAdLoader.4
            @Override // java.lang.Runnable
            public void run() {
                NativeAdLoader.this.c((Map<String, List<INativeAd>>) map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        IHiAdSplash hiAdSplash = HiAdSplash.getInstance(this.d);
        if (hiAdSplash instanceof HiAdSplash) {
            HiAdSplash hiAdSplash2 = (HiAdSplash) hiAdSplash;
            long bo = fh.b(this.d).bo();
            long a2 = hiAdSplash2.a();
            if (System.currentTimeMillis() - a2 >= bo) {
                hiAdSplash2.preloadAd();
                return;
            }
            ho.b(b, "request time limit, timeInter=" + bo + ", lastTime=" + a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.f = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Boolean bool) {
        this.g = bool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final String str, final boolean z) {
        final long currentTimeMillis = System.currentTimeMillis();
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.NativeAdLoader.2
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis2 = System.currentTimeMillis();
                NativeAdLoader.this.q.d(currentTimeMillis2 - currentTimeMillis);
                ho.b(NativeAdLoader.b, "doRequestAd " + currentTimeMillis2);
                AdSlotParam.Builder builder = new AdSlotParam.Builder();
                builder.setAdIds(Arrays.asList(NativeAdLoader.this.e)).setDeviceType(i).setRequestSequence(str).setOrientation(1).setWidth(com.huawei.openalliance.ad.utils.d.d(NativeAdLoader.this.d)).setHeight(com.huawei.openalliance.ad.utils.d.e(NativeAdLoader.this.d)).setOaid(NativeAdLoader.this.f).setTrackLimited(NativeAdLoader.this.g).setTest(z).setRequestOptions(NativeAdLoader.this.m).setLocation(NativeAdLoader.this.o).a(NativeAdLoader.this.r).b(NativeAdLoader.this.u).setDetailedCreativeTypeList(NativeAdLoader.this.s).setContentBundle(NativeAdLoader.this.n).setMaxCount(NativeAdLoader.this.z).a(NativeAdLoader.this.t);
                if (NativeAdLoader.this.v != null && NativeAdLoader.this.w != null) {
                    builder.setAdWidth(NativeAdLoader.this.v);
                    builder.setAdHeight(NativeAdLoader.this.w);
                }
                if (NativeAdLoader.this.x != null) {
                    builder.c(NativeAdLoader.this.x);
                }
                if (NativeAdLoader.this.y != null) {
                    builder.setTotalDuration(NativeAdLoader.this.y.intValue());
                }
                if (NativeAdLoader.this.A != null) {
                    builder.a(NativeAdLoader.this.A);
                }
                ol olVar = new ol(NativeAdLoader.this.d);
                olVar.a(NativeAdLoader.this.q);
                AdContentRsp a2 = olVar.a(NativeAdLoader.this.d, builder.build(), NativeAdLoader.this.j, NativeAdLoader.this.k, NativeAdLoader.this.l);
                if (a2 != null) {
                    NativeAdLoader.this.p = a2.k();
                }
                ho.b(NativeAdLoader.b, "doRequestAd, ad loaded,adType is " + NativeAdLoader.this.k + ",cacheContentIds is " + NativeAdLoader.this.l);
                NativeAdLoader.this.q.u().h(System.currentTimeMillis());
                NativeAdLoader.this.i.a(a2, currentTimeMillis2, new AdParseConfig.Builder().build());
                NativeAdLoader.this.c = a.IDLE;
                NativeAdLoader.this.b();
            }
        }, m.a.NETWORK, false);
    }

    public NativeAdLoader(Context context, String[] strArr, boolean z) {
        this.c = a.IDLE;
        this.k = 3;
        this.l = null;
        this.q = new DelayInfo();
        this.t = false;
        if (!ao.b(context)) {
            this.e = new String[0];
            return;
        }
        this.d = context.getApplicationContext();
        if (strArr == null || strArr.length <= 0) {
            this.e = new String[0];
        } else {
            String[] strArr2 = new String[strArr.length];
            this.e = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        }
        pe peVar = new pe(this.d, this, this.k, false);
        this.i = peVar;
        peVar.b(z);
    }

    public NativeAdLoader(Context context, String[] strArr, int i, List<String> list) {
        this(context, strArr, false);
        this.k = i;
        this.l = list;
        qs qsVar = this.i;
        if (qsVar != null) {
            qsVar.a(i);
        }
    }

    public NativeAdLoader(Context context, String[] strArr) {
        this(context, strArr, false);
    }
}
