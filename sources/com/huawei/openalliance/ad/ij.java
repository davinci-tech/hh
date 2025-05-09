package com.huawei.openalliance.ad;

import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class ij implements iq {

    /* renamed from: a, reason: collision with root package name */
    WeakReference<com.huawei.openalliance.ad.linked.view.b> f6943a;

    @Override // com.huawei.openalliance.ad.iq
    public void a(final int i) {
        ho.b("LinkedVideoStreamListener", "stream error, code: %s", Integer.valueOf(i));
        final com.huawei.openalliance.ad.linked.view.b bVar = this.f6943a.get();
        if (bVar != null) {
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ij.1
                @Override // java.lang.Runnable
                public void run() {
                    bVar.a(i);
                    bVar.g();
                }
            });
        }
    }

    public ij(com.huawei.openalliance.ad.linked.view.b bVar) {
        this.f6943a = new WeakReference<>(bVar);
    }
}
