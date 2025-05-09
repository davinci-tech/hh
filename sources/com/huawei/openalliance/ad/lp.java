package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.iab.omid.library.huawei.Omid;
import com.iab.omid.library.huawei.adsession.AdSession;
import com.iab.omid.library.huawei.adsession.AdSessionConfiguration;
import com.iab.omid.library.huawei.adsession.AdSessionContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class lp implements mi {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7189a = ma.a("com.iab.omid.library.huawei.adsession.AdSession");
    private final List<AdSession> b = new ArrayList();
    private Context c;

    Context e() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.mi
    public void d() {
        if (!this.b.isEmpty()) {
            try {
                Iterator<AdSession> it = this.b.iterator();
                while (it.hasNext()) {
                    it.next().finish();
                    ho.a("AdsessionAgent", " adSession finish");
                }
            } catch (Throwable unused) {
                ho.b("AdsessionAgent", "finish, fail");
            }
        }
        this.b.clear();
    }

    @Override // com.huawei.openalliance.ad.mi
    public void c() {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            for (AdSession adSession : this.b) {
                ho.a("AdsessionAgent", "adsession start");
                adSession.start();
            }
        } catch (Throwable unused) {
            ho.b("AdsessionAgent", "start, fail");
        }
    }

    List<AdSession> b() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.mi
    public void a(View view, mh mhVar, String str) {
        if (this.b.isEmpty() || mhVar == null || !mh.a()) {
            return;
        }
        try {
            Iterator<AdSession> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().addFriendlyObstruction(view, mh.a(mhVar), str);
            }
        } catch (Throwable unused) {
            ho.b("AdsessionAgent", "addFriendlyObstruction-f, fail");
        }
    }

    @Override // com.huawei.openalliance.ad.mi
    public void a(View view) {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            Iterator<AdSession> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().registerAdView(view);
            }
        } catch (Throwable unused) {
            ho.b("AdsessionAgent", "registerAdView, fail");
        }
    }

    public void a(Context context, List<Om> list, me meVar) {
        if (!a() || context == null || list == null) {
            ho.b("AdsessionAgent", "not available, not init");
            return;
        }
        if (list.isEmpty() || meVar == null) {
            ho.b("AdsessionAgent", "oms is empty or sessionWrapper is null, not init");
            return;
        }
        ho.b("AdsessionAgent", "init");
        this.c = context;
        a(list, meVar);
    }

    private static boolean a(Context context) {
        Omid.activate(context);
        return true;
    }

    public static boolean a() {
        return f7189a;
    }

    private void a(List<Om> list, me meVar) {
        if (!ml.a()) {
            ho.b("AdsessionAgent", "init VerficationScriptResourceWrapper failed");
            return;
        }
        for (Om om : list) {
            ho.b("AdsessionAgent", "Init Verfication Script");
            ml mlVar = new ml();
            mlVar.a(om);
            a(meVar, mlVar);
        }
    }

    private void a(AdSessionContext adSessionContext, me meVar) {
        try {
            if (me.a() && meVar != null) {
                AdSessionConfiguration b = meVar.b();
                if (b == null) {
                    ho.b("AdsessionAgent", "adSessionConfiguration is null");
                    return;
                }
                ho.b("AdsessionAgent", "initAdSession");
                AdSession createAdSession = a(this.c) ? AdSession.createAdSession(b, adSessionContext) : null;
                if (createAdSession == null) {
                    ho.b("AdsessionAgent", "adSession is null");
                    return;
                } else {
                    this.b.add(createAdSession);
                    return;
                }
            }
            ho.b("AdsessionAgent", "init AdSession failed");
        } catch (Throwable unused) {
            ho.c("AdsessionAgent", "initAdSession error");
        }
    }

    private void a(me meVar, ml mlVar) {
        String str;
        if (mlVar == null) {
            str = "init AdSessionContext failed";
        } else {
            if (!mf.a()) {
                return;
            }
            AdSessionContext a2 = new mf(this.c).a(mlVar, null);
            if (a2 != null) {
                a(a2, meVar);
                return;
            }
            str = "adSessionContext is null";
        }
        ho.b("AdsessionAgent", str);
    }
}
