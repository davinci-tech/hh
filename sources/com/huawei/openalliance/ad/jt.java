package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class jt implements ko {
    @Override // com.huawei.openalliance.ad.ko
    public void a(final Object obj) {
        if (obj == null) {
            return;
        }
        final String name = Thread.currentThread().getName();
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.jt.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b("HiAdResponseDataLogger", "upper thread name: %s response data: %s ", name, com.huawei.openalliance.ad.utils.be.c(obj));
            }
        });
    }
}
