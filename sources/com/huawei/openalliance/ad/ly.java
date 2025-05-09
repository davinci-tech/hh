package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.mc;
import com.iab.omid.library.huawei.adsession.AdEvents;
import com.iab.omid.library.huawei.adsession.AdSession;
import com.iab.omid.library.huawei.adsession.media.InteractionType;
import com.iab.omid.library.huawei.adsession.media.MediaEvents;
import com.iab.omid.library.huawei.adsession.media.VastProperties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ly extends lx implements lw {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7191a = false;
    private mc d;
    private final List<MediaEvents> b = new ArrayList();
    private final List<AdEvents> c = new ArrayList();
    private boolean e = false;
    private int f = 0;
    private float g = 0.0f;
    private boolean h = false;

    @Override // com.huawei.openalliance.ad.mn
    public void l() {
        this.f = 1;
        if (this.b.isEmpty()) {
            ho.c(n(), "resume, mVideoEventsList isEmpty");
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "resume ");
                    }
                    mediaEvents.resume();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "resume, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void k() {
        if (this.b.isEmpty() || 1 != this.f) {
            return;
        }
        try {
            this.f = 2;
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "pause ");
                    }
                    mediaEvents.pause();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "pause, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void j() {
        if (!this.h) {
            this.f = 0;
        }
        if (this.b.isEmpty()) {
            ho.c(n(), "skipped, mVideoEventsList isEmpty");
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "skipped ");
                    }
                    mediaEvents.skipped();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "skipped, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void i() {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "bufferFinish ");
                    }
                    mediaEvents.bufferFinish();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "bufferFinish, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void h() {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "bufferStart ");
                    }
                    mediaEvents.bufferStart();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "bufferStart, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void g() {
        this.g = 0.0f;
        this.f = 0;
        if (this.b.isEmpty()) {
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "complete ");
                    }
                    mediaEvents.complete();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "complete, fail");
        }
    }

    public void f() {
        if (this.c.isEmpty()) {
            ho.c(n(), "impressionOccurred, mAdEventList isEmpty");
            return;
        }
        try {
            Iterator<AdEvents> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().impressionOccurred();
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "impressionOccurred, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.lx
    void d() {
        if (this.b.isEmpty()) {
            ho.c(n(), "thirdQuartile, mVideoEventsList isEmpty");
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    ho.b(n(), "thirdQuartile ");
                    mediaEvents.thirdQuartile();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "thirdQuartile, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.lx
    void c() {
        if (this.b.isEmpty()) {
            ho.c(n(), "midpoint, mVideoEventsList isEmpty");
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    ho.b(n(), "midpoint ");
                    mediaEvents.midpoint();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "midpoint, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void b(boolean z) {
        this.h = z;
    }

    @Override // com.huawei.openalliance.ad.mn
    public void b(float f) {
        mc mcVar;
        ho.b(n(), "volumeChange %s", Float.valueOf(f));
        this.e = Math.abs(f - 0.0f) < 1.0E-8f;
        if (this.b.isEmpty() || this.f != 1) {
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null && (mcVar = this.d) != null) {
                    if (f == -1.0f) {
                        mediaEvents.volumeChange(mcVar.a(this.e));
                    } else {
                        mediaEvents.volumeChange(f);
                    }
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "volumeChange, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.lw
    public void b() {
        if (ho.a()) {
            ho.a(n(), "release ");
        }
        this.f = 0;
        mc mcVar = this.d;
        if (mcVar != null) {
            mcVar.a();
        }
        com.huawei.openalliance.ad.utils.bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.ly.2
            @Override // java.lang.Runnable
            public void run() {
                ly.this.b.clear();
                ly.this.c.clear();
            }
        }, 200L);
    }

    @Override // com.huawei.openalliance.ad.lx
    void a(VastProperties vastProperties) {
        if (this.c.isEmpty()) {
            return;
        }
        try {
            for (AdEvents adEvents : this.c) {
                if (adEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "loaded ");
                    }
                    adEvents.loaded(vastProperties);
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "loaded, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.lx
    void a(InteractionType interactionType) {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "adUserInteraction ");
                    }
                    mediaEvents.adUserInteraction(interactionType);
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "adUserInteraction, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(mq mqVar) {
        VastProperties b;
        if (mqVar == null || !mq.a() || (b = mqVar.b()) == null) {
            return;
        }
        a(b);
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(mo moVar) {
        InteractionType a2;
        if (!mo.a() || (a2 = mo.a(moVar)) == null) {
            return;
        }
        a(a2);
    }

    @Override // com.huawei.openalliance.ad.lw
    public void a(mi miVar) {
        String n;
        String str;
        if (f7191a) {
            if ((miVar instanceof lp) && e()) {
                lp lpVar = (lp) miVar;
                Context e = lpVar.e();
                if (e != null) {
                    ho.b(n(), "Set VolumeChange observer");
                    mc mcVar = new mc(e);
                    this.d = mcVar;
                    mcVar.a(new mc.b() { // from class: com.huawei.openalliance.ad.ly.1
                        @Override // com.huawei.openalliance.ad.mc.b
                        public void a() {
                            ly.this.m();
                        }
                    });
                }
                List<AdSession> b = lpVar.b();
                if (!b.isEmpty()) {
                    for (AdSession adSession : b) {
                        if (adSession != null) {
                            if (ho.a()) {
                                ho.a(n(), "setAdSessionAgent, add mVideoEventsList");
                            }
                            this.b.add(MediaEvents.createMediaEvents(adSession));
                            this.c.add(AdEvents.createAdEvents(adSession));
                        }
                    }
                    return;
                }
                n = n();
                str = "adSessionList is empty";
            } else {
                n = n();
                str = "adsessionAgent is null";
            }
            ho.b(n, str);
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(float f, boolean z) {
        this.f = 1;
        this.e = z;
        a(f, z ? 0.0f : 1.0f);
    }

    @Override // com.huawei.openalliance.ad.lx
    void a(float f, float f2) {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    if (ho.a()) {
                        ho.a(n(), "start，duration: %s ", Float.valueOf(f));
                    }
                    mediaEvents.start(f, f2);
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "start, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(float f) {
        int a2 = mb.a(this.g, f);
        if (ho.a()) {
            ho.a(n(), "onProgress %s", Integer.valueOf(a2));
        }
        if (a2 == 25) {
            this.g = a2;
            a();
        } else if (a2 == 50) {
            this.g = a2;
            c();
        } else {
            if (a2 != 75) {
                return;
            }
            this.g = a2;
            d();
        }
    }

    @Override // com.huawei.openalliance.ad.lx
    void a() {
        if (this.b.isEmpty()) {
            ho.c(n(), "firstQuartile, mVideoEventsList isEmpty");
            return;
        }
        try {
            for (MediaEvents mediaEvents : this.b) {
                if (mediaEvents != null) {
                    ho.b(n(), "firstQuartile");
                    mediaEvents.firstQuartile();
                }
            }
        } catch (IllegalStateException unused) {
            ho.b(n(), "firstQuartile, fail");
        }
    }

    private String n() {
        return "VideoEventAgent" + hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (ho.a()) {
            ho.a(n(), "volumeChangeInner %s", Boolean.valueOf(this.e));
        }
        b(this.e ? 0.0f : 1.0f);
    }

    public static boolean e() {
        return f7191a;
    }

    ly() {
    }

    static {
        f7191a = ma.a("com.iab.omid.library.huawei.adsession.media.MediaEvents") && ma.a("com.iab.omid.library.huawei.adsession.AdEvents");
    }
}
