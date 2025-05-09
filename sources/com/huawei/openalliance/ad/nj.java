package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.AdActionListener;
import com.huawei.openalliance.ad.inter.listeners.AdShowListener;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.views.interfaces.IPPSLinkedView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class nj extends jj<IPPSLinkedView> implements nz {
    private Context d;
    private LinkedSplashAd e;
    private AdActionListener f;
    private AdShowListener g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;

    public boolean k() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.nz
    public void j() {
        this.b.f();
    }

    @Override // com.huawei.openalliance.ad.nz
    public void i() {
        this.b.c();
    }

    @Override // com.huawei.openalliance.ad.nz
    public void h() {
        this.b.p();
    }

    @Override // com.huawei.openalliance.ad.nz
    public void c() {
        this.b.b(0, 0);
    }

    public void b(boolean z) {
        this.i = z;
    }

    @Override // com.huawei.openalliance.ad.nz
    public void b(long j, long j2, long j3, long j4) {
        this.b.b(j, j2, (int) j3, (int) j4);
    }

    @Override // com.huawei.openalliance.ad.nz
    public void b() {
        this.b.b();
    }

    @Override // com.huawei.openalliance.ad.nz
    public boolean a(int i, MaterialClickInfo materialClickInfo) {
        LinkedSplashAd linkedSplashAd = this.e;
        if (linkedSplashAd == null) {
            return false;
        }
        linkedSplashAd.f(true);
        dc.a((IAd) this.e);
        ho.b("PPSLinkedVideoViewPresenter", "begin to deal click");
        HashMap hashMap = new HashMap();
        hashMap.put("appId", this.e.M());
        hashMap.put("thirdId", this.e.L());
        a(hashMap);
        AdActionListener adActionListener = this.f;
        if (adActionListener != null) {
            adActionListener.onAdClick();
        }
        ta a2 = sz.a(this.d, this.f7126a, hashMap);
        boolean a3 = a2.a();
        if (a3) {
            a(a2, i, materialClickInfo);
        }
        com.huawei.openalliance.ad.inter.c.a(this.d).a(false);
        return a3;
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(boolean z) {
        this.b.b(z);
    }

    @Override // com.huawei.openalliance.ad.jj, com.huawei.openalliance.ad.nz
    public void a(String str) {
        super.a(str);
        c(false);
        b(false);
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(Long l, Integer num, Integer num2) {
        String str;
        LinkedSplashAd linkedSplashAd = this.e;
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(linkedSplashAd != null ? linkedSplashAd.d() : null, num2);
        if (l() && (!a2 || k())) {
            ho.c("PPSLinkedVideoViewPresenter", "show event already reported before, ignore this");
            return;
        }
        IPPSLinkedView d = d();
        if (d != null) {
            str = d.getSplashViewSlotPosition();
            LinkedSplashAd linkedSplashAd2 = this.e;
            if (linkedSplashAd2 != null) {
                ho.a("PPSLinkedVideoViewPresenter", "slotId: %s, contentId: %s, slot pos: %s", linkedSplashAd2.getSlotId(), this.e.getContentId(), str);
            }
        } else {
            str = "";
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(l).a(num).b(num2).e(g()).a(com.huawei.openalliance.ad.utils.b.a(d()));
        if (!com.huawei.openalliance.ad.utils.cz.b(str)) {
            c0207a.d(str);
        }
        this.b.a(c0207a.a());
        if (a2) {
            b(true);
        }
        if (l()) {
            return;
        }
        c(true);
        if (this.g != null && this.f7126a != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AdShowExtras.PRELOAD_MODE, String.valueOf(fd.a(this.d).a(this.f7126a.l())));
            hashMap.put(AdShowExtras.DOWNLOAD_SOURCE, com.huawei.openalliance.ad.utils.d.a(this.f7126a.T()));
            this.g.onAdShowed(hashMap);
        }
        AdActionListener adActionListener = this.f;
        if (adActionListener != null) {
            adActionListener.onAdShowed();
        }
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(AdShowListener adShowListener) {
        this.g = adShowListener;
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(AdActionListener adActionListener) {
        this.f = adActionListener;
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(LinkedSplashAd linkedSplashAd) {
        this.e = linkedSplashAd;
        if (linkedSplashAd != null) {
            this.f7126a = linkedSplashAd.I();
        }
        this.b = new ou(this.d, new si(this.d, 1), this.f7126a);
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(long j, long j2, long j3, long j4) {
        this.b.c(j, j2, (int) j3, (int) j4);
    }

    @Override // com.huawei.openalliance.ad.nz
    public void a(long j, int i) {
        this.b.a(j, i);
    }

    @Override // com.huawei.openalliance.ad.jj
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public IPPSLinkedView d() {
        return (IPPSLinkedView) super.d();
    }

    private boolean l() {
        return this.h;
    }

    private void c(boolean z) {
        this.h = z;
    }

    private void a(Map<String, String> map) {
        LinkedSplashAd linkedSplashAd;
        if (map == null || map.isEmpty() || (linkedSplashAd = this.e) == null || linkedSplashAd.getVideoInfo() == null) {
            return;
        }
        int b = this.e.getVideoInfo().b();
        if (Math.abs(this.e.getVideoInfo().getVideoDuration() - b) < 1000) {
            b = 0;
        }
        ho.b("PPSLinkedVideoViewPresenter", "buildLinkedAdConfig, duration: %s, set progress from LinkedSplash view:%s ", Integer.valueOf(this.e.getVideoInfo().getVideoDuration()), Integer.valueOf(b));
        map.put(MapKeyNames.LINKED_CUSTOM_RETURN_VIDEO_DIRECT, this.e.getVideoInfo().f() ? "true" : "false");
        map.put(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH, this.e.getVideoInfo().getSoundSwitch());
        map.put(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS, String.valueOf(b));
        map.put(MapKeyNames.LINKED_SPLASH_MEDIA_PATH, this.e.D());
        map.put(MapKeyNames.LINKED_CUSTOM_SHOW_ID, this.e.getShowId());
    }

    private void a(ta taVar, int i, MaterialClickInfo materialClickInfo) {
        b.a aVar = new b.a();
        aVar.b(taVar.c()).a(Integer.valueOf(i)).a(materialClickInfo).d(com.huawei.openalliance.ad.utils.b.a(d()));
        this.b.a(aVar.a());
    }

    public nj(Context context, IPPSLinkedView iPPSLinkedView) {
        this.d = context.getApplicationContext();
        a((nj) iPPSLinkedView);
        this.b = new ou(this.d, new si(this.d, 1));
    }
}
