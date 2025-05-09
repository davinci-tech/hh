package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class js implements ko {
    @Override // com.huawei.openalliance.ad.ko
    public void a(final Object obj) {
        if (obj == null) {
            return;
        }
        final String name = Thread.currentThread().getName();
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.js.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b("HiAdRequestDataLogger", "upper thread name: %s request data: %s ", name, com.huawei.openalliance.ad.utils.be.c(obj));
            }
        });
    }
}
