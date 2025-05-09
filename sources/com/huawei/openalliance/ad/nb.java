package com.huawei.openalliance.ad;

import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class nb extends ns {
    @Override // com.huawei.openalliance.ad.ns
    public void a() {
        long longValue = ((Long) com.huawei.openalliance.ad.utils.dc.a(new Callable<Long>() { // from class: com.huawei.openalliance.ad.nb.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Long call() {
                return Long.valueOf(nb.this.f7335a.aH());
            }
        }, 300L)).longValue();
        int intValue = ((Integer) com.huawei.openalliance.ad.utils.dc.a(new Callable<Integer>() { // from class: com.huawei.openalliance.ad.nb.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                return Integer.valueOf(nb.this.f7335a.aF());
            }
        }, 2000)).intValue();
        b(longValue);
        a(intValue);
    }

    public nb(gc gcVar, hl hlVar) {
        super(gcVar, hlVar);
    }
}
