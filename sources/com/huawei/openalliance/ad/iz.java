package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class iz extends ix {
    private final int p;

    @Override // com.huawei.openalliance.ad.ix
    protected void c(ContentRecord contentRecord) {
    }

    @Override // com.huawei.openalliance.ad.ix
    protected String z() {
        return "CacheAdMediator" + this.p;
    }

    @Override // com.huawei.openalliance.ad.ix
    protected String r() {
        return String.valueOf(1);
    }

    @Override // com.huawei.openalliance.ad.qw
    public List<ContentRecord> b(AdContentRsp adContentRsp) {
        List<ContentRecord> a2 = pg.a(adContentRsp, this.n);
        if (k() != null) {
            a(a2, k().b(), 1);
        }
        return a2;
    }

    @Override // com.huawei.openalliance.ad.qw
    public List<ContentRecord> a(AdContentRsp adContentRsp) {
        return pg.b(adContentRsp, this.n);
    }

    @Override // com.huawei.openalliance.ad.jb
    public void B() {
        ho.b("CacheAdMediator", "onAdFailToDisplay");
        j();
    }

    @Override // com.huawei.openalliance.ad.jb
    public void A() {
        ho.b("CacheAdMediator", "start");
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p == null) {
            c(-4);
            j();
            return;
        }
        Context context = p.getContext();
        cy cyVar = new cy(context);
        cyVar.a(new da(context));
        boolean z = com.huawei.openalliance.ad.utils.d.s(context) == 1;
        ho.b("CacheAdMediator", "readScreenOn: %s", Boolean.valueOf(z));
        ContentRecord contentRecord = (cyVar.a() || z) ? null : (ContentRecord) com.huawei.openalliance.ad.utils.dc.b(new Callable<ContentRecord>() { // from class: com.huawei.openalliance.ad.iz.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public ContentRecord call() {
                iz.this.h();
                return iz.this.D();
            }
        });
        this.g = true;
        if (contentRecord != null) {
            d(contentRecord);
            if (contentRecord.D() == 12) {
                if (d() != 1 || !(c() instanceof LinkedAdListener)) {
                    c(1200);
                    B();
                    C();
                    return;
                }
                LinkedAdListener linkedAdListener = (LinkedAdListener) c();
                LinkedSplashAd a2 = oz.a(context, contentRecord);
                ho.b("CacheAdMediator", "on content find, linkedAd loaded. ");
                this.l.b(System.currentTimeMillis());
                linkedAdListener.onLinkedAdLoaded(a2);
                this.k = contentRecord;
                C();
                g(200);
                return;
            }
            this.e.a(contentRecord);
            if (!b(contentRecord)) {
                c(ErrorCode.ERROR_CODE_NULL_AD_VIEW);
                B();
            }
        } else {
            ho.b("CacheAdMediator", "show sloganView");
            p.a(new hl() { // from class: com.huawei.openalliance.ad.iz.2
                @Override // com.huawei.openalliance.ad.hl
                public void b() {
                    ho.b("CacheAdMediator", "on Slogan Show End");
                    com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.iz.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            iz.this.c(ErrorCode.ERROR_CODE_NO_CACHE_AD);
                            iz.this.j();
                        }
                    });
                }

                @Override // com.huawei.openalliance.ad.hl
                public void a() {
                    ho.b("CacheAdMediator", "on Slogan Reach Min Show Time");
                }
            });
        }
        C();
    }

    private ContentRecord a(String str, int i, long j, Map<String, Integer> map) {
        ho.b("CacheAdMediator", "query CachedContentV3");
        List<ContentRecord> b = et.b(this.o).b(str, i, j, this.n);
        if (com.huawei.openalliance.ad.utils.bg.a(b)) {
            return null;
        }
        for (ContentRecord contentRecord : b) {
            if (contentRecord != null && !fl.a(this.o).a(str, contentRecord.aN(), map)) {
                ho.b("CacheAdMediator", "v3 content got");
                return contentRecord;
            }
        }
        return null;
    }

    private ContentRecord a(String str, int i, long j) {
        boolean z = false;
        ContentRecord contentRecord = null;
        ContentRecord contentRecord2 = null;
        boolean z2 = false;
        for (ContentRecord contentRecord3 : this.d.b(str, i, j, this.n)) {
            if (contentRecord3 != null) {
                if (z && z2) {
                    break;
                }
                if (!z && contentRecord3.D() == 12) {
                    contentRecord2 = contentRecord3;
                    z = true;
                } else if (!z2 && contentRecord3.D() != 12) {
                    contentRecord = contentRecord3;
                    z2 = true;
                }
            }
        }
        ho.b("CacheAdMediator", "linkedSupportMode:%s, firstNormal: %s, firstLink:%s", Integer.valueOf(d()), contentRecord, contentRecord2);
        if (d() != 1) {
            dc.a((ContentRecord) null);
            return contentRecord;
        }
        if (contentRecord2 == null) {
            dc.a((ContentRecord) null);
            return contentRecord;
        }
        if (contentRecord != null) {
            dc.a(contentRecord);
        } else {
            dc.a((ContentRecord) null);
        }
        return contentRecord2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentRecord D() {
        AdSlotParam k = k();
        if (k == null) {
            return null;
        }
        String str = k.a().get(0);
        int b = k.b();
        long aG = this.c.aG();
        ContentRecord a2 = a(str, b, aG, k.G());
        return a2 == null ? a(str, b, aG) : a2;
    }

    private void C() {
        com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.iz.3
            @Override // java.lang.Runnable
            public void run() {
                iz.this.m();
            }
        }, 2000L);
    }

    public iz(com.huawei.openalliance.ad.views.interfaces.l lVar) {
        super(lVar);
        this.p = hashCode();
    }
}
