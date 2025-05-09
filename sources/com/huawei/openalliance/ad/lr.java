package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.AdEvents;
import com.iab.omid.library.huawei.adsession.AdSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class lr extends ls implements lw {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7190a = ma.a("com.iab.omid.library.huawei.adsession.AdEvents");
    private final List<AdEvents> b = new ArrayList();

    @Override // com.huawei.openalliance.ad.mm
    public void f() {
        ho.b("DisplayEventAgent", "load");
        if (this.b.isEmpty()) {
            ho.b("DisplayEventAgent", "load, AdEventList isEmpty");
            return;
        }
        try {
            Iterator<AdEvents> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().loaded();
            }
        } catch (IllegalStateException unused) {
            ho.b("DisplayEventAgent", "loaded, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mm
    public void e() {
        ho.b("DisplayEventAgent", "impressionOccurred");
        if (this.b.isEmpty()) {
            ho.c("DisplayEventAgent", "impressionOccurred, mAdEventList isEmpty");
            return;
        }
        try {
            Iterator<AdEvents> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().impressionOccurred();
            }
        } catch (IllegalStateException unused) {
            ho.b("DisplayEventAgent", "impressionOccurred, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.lw
    public void b() {
        this.b.clear();
    }

    @Override // com.huawei.openalliance.ad.lw
    public void a(mi miVar) {
        if (miVar instanceof lp) {
            List<AdSession> b = ((lp) miVar).b();
            if (b.isEmpty()) {
                return;
            }
            for (AdSession adSession : b) {
                if (adSession != null) {
                    this.b.add(AdEvents.createAdEvents(adSession));
                }
            }
        }
    }

    public static boolean a() {
        return f7190a;
    }

    lr() {
    }
}
