package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class vb implements com.huawei.openalliance.ad.views.interfaces.z {

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.openalliance.ad.views.interfaces.k f7765a;

    @Override // com.huawei.openalliance.ad.views.interfaces.z
    public void a(final int i) {
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.vb.2
            @Override // java.lang.Runnable
            public void run() {
                if (i == 100) {
                    vb.this.f7765a.c(false);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.z
    public void a() {
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.vb.1
            @Override // java.lang.Runnable
            public void run() {
                vb.this.f7765a.c(true);
            }
        });
    }

    public vb(com.huawei.openalliance.ad.views.interfaces.k kVar) {
        this.f7765a = kVar;
    }
}
