package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.cy;
import com.huawei.openalliance.ad.da;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ek;
import com.huawei.openalliance.ad.es;
import com.huawei.openalliance.ad.fd;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.fs;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.pg;
import com.huawei.openalliance.ad.pm;
import com.huawei.openalliance.ad.qk;
import com.huawei.openalliance.ad.qw;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.bo;
import com.huawei.openalliance.ad.utils.cn;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dc;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class HiAdSplash implements IHiAdSplash {

    /* renamed from: a, reason: collision with root package name */
    private static HiAdSplash f6990a;
    private static final byte[] b = new byte[0];
    private Context c;
    private gc d;
    private fs e;
    private AdSlotParam f;
    private long g;
    private Integer h = null;

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setUsePostAtFront(boolean z) {
        this.d.a(z);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setSmartScreenSloganTime(int i) {
        if (!ao.b(this.c) || i < 0 || i > 5000) {
            return;
        }
        this.d.d(i);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setSloganShowTimeWhenNoAd(final int i) {
        if (bz.b(this.c)) {
            ho.b("HiAdSplash", "setSloganShowTimeWhenNoAd");
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.9
                @Override // java.lang.Runnable
                public void run() {
                    ms.a(HiAdSplash.this.c).a(RTCMethods.SET_SLOGAN_TIME_NO_AD, String.valueOf(i), null, null);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setSloganDefTime(int i) {
        if (ao.b(this.c)) {
            if (i < 0 || i > 5000) {
                ho.d("HiAdSplash", "time is out limit");
            } else {
                this.d.c(i);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setExSplashShowTime(final int i) {
        if (bz.b(this.c)) {
            ho.b("HiAdSplash", "setExSplashShowTime");
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.2
                @Override // java.lang.Runnable
                public void run() {
                    ms.a(HiAdSplash.this.c).a(RTCMethods.SET_SPLASH_TIME, String.valueOf(i), null, null);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setDefaultSplashMode(int i) {
        if (1 == i || 2 == i) {
            fh.b(this.c).k(i);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void setAllowMobileTraffic(int i) {
        this.h = (i == 0 || i == 1) ? Integer.valueOf(i) : null;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void preloadSmartScreenAd(AdSlotParam adSlotParam) {
        a(adSlotParam, 18);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void preloadSmartScreenAd() {
        preloadSmartScreenAd(this.f);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void preloadAd(AdSlotParam adSlotParam) {
        a(adSlotParam, 1);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void preloadAd() {
        preloadAd(this.f);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public boolean isSmartSplashAvailable(AdSlotParam adSlotParam) {
        adSlotParam.d(18);
        return b(adSlotParam);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public boolean isExSplashEnable(Context context) {
        return com.huawei.openalliance.ad.utils.d.t(context);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public boolean isAvailable(AdSlotParam adSlotParam) {
        adSlotParam.d(1);
        return b(adSlotParam);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public Integer getAllowMobileTraffic() {
        return this.h;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void enableLinkedVideo(final boolean z) {
        if (bz.b(this.c)) {
            ho.b("HiAdSplash", RTCMethods.ENABLE_LINKED_VIDEO);
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.7
                @Override // java.lang.Runnable
                public void run() {
                    ms.a(HiAdSplash.this.c).a(RTCMethods.ENABLE_LINKED_VIDEO, String.valueOf(z), null, null);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void dismissExSplashSlogan() {
        if (bz.b(this.c)) {
            ho.b("HiAdSplash", RTCMethods.DISMISS_EXSPLASH_SLOGAN);
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.8
                @Override // java.lang.Runnable
                public void run() {
                    ms.a(HiAdSplash.this.c).a(RTCMethods.DISMISS_EXSPLASH_SLOGAN, null, null, null);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAdSplash
    public void dismissExSplash() {
        if (bz.b(this.c)) {
            ho.b("HiAdSplash", RTCMethods.DISMISS_EXSPLASH);
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.10
                @Override // java.lang.Runnable
                public void run() {
                    ms.a(HiAdSplash.this.c).a(RTCMethods.DISMISS_EXSPLASH, null, null, null);
                }
            });
        }
    }

    public void a(AdSlotParam adSlotParam) {
        if (adSlotParam != null) {
            this.f = adSlotParam.I();
        }
    }

    public long a() {
        return this.g;
    }

    public static IHiAdSplash getInstance(Context context) {
        return a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AdSlotParam adSlotParam) {
        adSlotParam.a(false);
        int D = adSlotParam.D();
        long currentTimeMillis = System.currentTimeMillis();
        ol olVar = new ol(this.c);
        olVar.a(olVar.a(this.c, adSlotParam, D), adSlotParam, new a(D), (qk) null, currentTimeMillis, 1);
    }

    private boolean b(final AdSlotParam adSlotParam) {
        if (adSlotParam == null || !ao.b(this.c)) {
            return false;
        }
        ek.a(this.c).e();
        this.f = adSlotParam.I();
        int a2 = com.huawei.openalliance.ad.utils.d.a(this.c, adSlotParam.b());
        int b2 = com.huawei.openalliance.ad.utils.d.b(this.c, adSlotParam.b());
        adSlotParam.b(a2);
        adSlotParam.c(b2);
        boolean booleanValue = ((Boolean) dc.a(new Callable<Boolean>() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean call() {
                AdLoadMode ay = HiAdSplash.this.d.ay();
                long aF = HiAdSplash.this.d.aF();
                ho.b("HiAdSplash", "isAvailable mode: " + ay + " sloganShowTime: " + aF);
                if (AdLoadMode.CACHE != ay || 0 != aF || (!HiAdSplash.this.b() && HiAdSplash.this.e.a(adSlotParam.a().get(0), adSlotParam.b(), HiAdSplash.this.d.aG(), adSlotParam.D()) != null)) {
                    ho.b("HiAdSplash", "isAvailable call true");
                    return true;
                }
                ho.b("HiAdSplash", "isAvailable call false");
                m.b(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Pair<String, Boolean> b3 = com.huawei.openalliance.ad.utils.d.b(HiAdSplash.this.c, true);
                        if (b3 == null && HiAdSplash.this.d.be() && bz.h(HiAdSplash.this.c)) {
                            HiAdSplash.this.a(adSlotParam, HiAdSplash.this.c);
                            return;
                        }
                        if (b3 != null) {
                            ho.b("HiAdSplash", "use cached oaid ");
                            adSlotParam.a((String) b3.first);
                            adSlotParam.a((Boolean) b3.second);
                        }
                        HiAdSplash.this.c(adSlotParam);
                    }
                });
                return false;
            }
        }, false)).booleanValue();
        ho.b("HiAdSplash", "isAvailable " + booleanValue);
        return booleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        cy cyVar = new cy(this.c);
        cyVar.a(new da(this.c));
        return cyVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AdSlotParam adSlotParam, int i) {
        ho.a("HiAdSplash", "do preload from sdk");
        ol olVar = new ol(this.c);
        olVar.a(olVar.a(this.c, adSlotParam, i), adSlotParam, new b(i), (qk) null, this.g, 1);
    }

    private void a(final String str, final int i) {
        bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.5
            @Override // java.lang.Runnable
            public void run() {
                m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int a2 = fd.a(HiAdSplash.this.c).a(str);
                        if (a2 == 0 || (1 != a2 && ao.d(HiAdSplash.this.c))) {
                            new pm(HiAdSplash.this.c, true, i).d();
                        }
                    }
                }, m.a.DISK_CACHE, false);
            }
        }, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AdSlotParam adSlotParam, List<String> list, List<String> list2) {
        ho.a("HiAdSplash", "do preload from kit");
        adSlotParam.a(this.h);
        String a2 = com.huawei.openalliance.ad.e.a();
        if (!cz.b(a2)) {
            adSlotParam.c(a2);
        }
        adSlotParam.a(cn.a(this.c, adSlotParam.l()));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MapKeyNames.AD_SLOT_PARAM, be.b(adSlotParam));
            jSONObject.put(MapKeyNames.KIT_RELOAD_ADIDS, be.b(list));
            jSONObject.put(MapKeyNames.SDK_KIT_RELOAD_ADIDS, be.b(list2));
            final AdSlotParam I = adSlotParam.I();
            ms.a(this.c).a(RTCMethods.PRELOAD_SPLASH_AD, jSONObject.toString(), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.6
                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                public void onRemoteCallResult(String str, CallResult<String> callResult) {
                    if (callResult.getCode() != 200) {
                        ho.b("HiAdSplash", "call kit preload failed");
                        HiAdSplash.this.b(I, 1);
                    }
                }
            }, String.class);
        } catch (Throwable th) {
            ho.c("HiAdSplash", "do kit preload failed: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final AdSlotParam adSlotParam, final Context context) {
        final int D = adSlotParam.D();
        OAIDServiceManager.getInstance(context).requireOaid(new OAIDServiceManager.OaidResultCallback() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.3
            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
            public int b() {
                return D;
            }

            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
            public void a(String str, boolean z) {
                ho.b("HiAdSplash", "onOaidAcquired " + System.currentTimeMillis());
                adSlotParam.a(str);
                adSlotParam.a(Boolean.valueOf(z));
                com.huawei.openalliance.ad.utils.d.a(context, str, z);
                HiAdSplash.this.c(adSlotParam);
            }

            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
            public void a() {
                ho.b("HiAdSplash", "onOaidAcquireFailed " + System.currentTimeMillis());
                HiAdSplash.this.c(adSlotParam);
            }
        });
    }

    private void a(final AdSlotParam adSlotParam, final int i) {
        ho.b("HiAdSplash", "preloadAd request");
        if (adSlotParam != null) {
            ho.b("HiAdSplash", "request preload splash ad");
            ek.a(this.c).e();
            final List<String> a2 = adSlotParam.a();
            if (bg.a(a2)) {
                return;
            }
            m.b(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAdSplash.4
                @Override // java.lang.Runnable
                public void run() {
                    adSlotParam.d(i);
                    adSlotParam.a(true);
                    HiAdSplash.this.g = System.currentTimeMillis();
                    Pair<String, Boolean> b2 = com.huawei.openalliance.ad.utils.d.b(HiAdSplash.this.c, true);
                    if (b2 != null) {
                        adSlotParam.a((String) b2.first);
                        adSlotParam.a((Boolean) b2.second);
                    }
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    for (String str : a2) {
                        int a3 = fd.a(HiAdSplash.this.c).a(str);
                        if (a3 != 0) {
                            if (a3 == 2) {
                                arrayList2.add(str);
                            } else if (a3 != 3) {
                                arrayList.add(str);
                            } else {
                                arrayList3.add(str);
                            }
                        }
                    }
                    if (!com.huawei.openalliance.ad.utils.d.c(HiAdSplash.this.c, i)) {
                        AdSlotParam I = adSlotParam.I();
                        arrayList.addAll(arrayList2);
                        arrayList.addAll(arrayList3);
                        if (bg.a(arrayList)) {
                            return;
                        }
                        I.a(arrayList);
                        HiAdSplash.this.b(I, i);
                        return;
                    }
                    if (!bg.a(arrayList2) || !bg.a(arrayList3)) {
                        HiAdSplash.this.a(adSlotParam.I(), arrayList2, arrayList3);
                    }
                    if (bg.a(arrayList)) {
                        return;
                    }
                    AdSlotParam I2 = adSlotParam.I();
                    I2.a(arrayList);
                    HiAdSplash.this.b(I2, i);
                }
            });
            bi.b(this.c, adSlotParam.l());
            a(a2.get(0), i);
        }
    }

    private static IHiAdSplash a(Context context) {
        HiAdSplash hiAdSplash;
        synchronized (b) {
            if (f6990a == null) {
                f6990a = new HiAdSplash(context);
            }
            hiAdSplash = f6990a;
        }
        return hiAdSplash;
    }

    public static class a implements qw {

        /* renamed from: a, reason: collision with root package name */
        private int f7003a;

        @Override // com.huawei.openalliance.ad.qw
        public List<ContentRecord> b(AdContentRsp adContentRsp) {
            return pg.a(adContentRsp, this.f7003a);
        }

        @Override // com.huawei.openalliance.ad.qw
        public List<ContentRecord> a(AdContentRsp adContentRsp) {
            return new ArrayList(0);
        }

        public a(int i) {
            this.f7003a = i;
        }
    }

    public static class b implements qw {

        /* renamed from: a, reason: collision with root package name */
        private int f7004a;

        @Override // com.huawei.openalliance.ad.qw
        public List<ContentRecord> b(AdContentRsp adContentRsp) {
            return pg.a(adContentRsp, this.f7004a);
        }

        @Override // com.huawei.openalliance.ad.qw
        public List<ContentRecord> a(AdContentRsp adContentRsp) {
            return pg.b(adContentRsp, this.f7004a);
        }

        public b(int i) {
            this.f7004a = i;
        }
    }

    private HiAdSplash(Context context) {
        this.c = context.getApplicationContext();
        this.d = fh.b(context);
        this.e = es.a(context);
    }
}
