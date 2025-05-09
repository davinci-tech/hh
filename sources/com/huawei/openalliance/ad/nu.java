package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import com.huawei.openalliance.ad.inter.listeners.AdListener;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class nu extends jj<com.huawei.openalliance.ad.views.interfaces.l> implements oh<com.huawei.openalliance.ad.views.interfaces.l> {
    private gc d;
    private AdListener e;
    private Context f;
    private com.huawei.openalliance.ad.analysis.c g;

    @Override // com.huawei.openalliance.ad.oh
    public void h() {
        ho.a("SplashPresenter", "notifyAdDismissed");
        AdListener adListener = this.e;
        if (adListener != null) {
            adListener.onAdDismissed();
        }
        com.huawei.openalliance.ad.utils.ao.i(this.f);
    }

    @Override // com.huawei.openalliance.ad.oh
    public boolean c() {
        return com.huawei.openalliance.ad.utils.d.t(this.f);
    }

    @Override // com.huawei.openalliance.ad.oh
    public boolean b() {
        if (com.huawei.openalliance.ad.utils.ao.b(this.f)) {
            return true;
        }
        i();
        h();
        return false;
    }

    @Override // com.huawei.openalliance.ad.oh
    public void a(String str, int i) {
        this.g.a(str, i);
    }

    @Override // com.huawei.openalliance.ad.oh
    public void a(AdListener adListener) {
        this.e = adListener;
    }

    @Override // com.huawei.openalliance.ad.oh
    public void a() {
        d().a((AdLoadMode) com.huawei.openalliance.ad.utils.dc.a(new Callable<AdLoadMode>() { // from class: com.huawei.openalliance.ad.nu.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public AdLoadMode call() {
                return nu.this.d.ay();
            }
        }, AdLoadMode.CACHE));
    }

    private void i() {
        ho.c("SplashPresenter", "notifyNotSupport");
        AdListener adListener = this.e;
        if (adListener != null) {
            adListener.onAdFailedToLoad(1001);
        }
    }

    public nu(Context context, com.huawei.openalliance.ad.views.interfaces.l lVar) {
        a((nu) lVar);
        this.d = fh.b(context);
        this.f = context.getApplicationContext();
        this.g = new com.huawei.openalliance.ad.analysis.c(this.f);
    }
}
