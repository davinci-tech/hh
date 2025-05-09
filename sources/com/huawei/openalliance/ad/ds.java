package com.huawei.openalliance.ad;

import android.content.Context;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class ds implements com.huawei.openalliance.ad.download.a<du> {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Set<com.huawei.openalliance.ad.download.a<du>>> f6818a = new ConcurrentHashMap();
    private com.huawei.openalliance.ad.analysis.g b;

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void onDownloadFail(du duVar) {
        a("2", duVar);
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().onDownloadFail(duVar);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void b(du duVar) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().b(duVar);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onDownloadSuccess(du duVar) {
        a("5", duVar);
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().onDownloadSuccess(duVar);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onDownloadProgress(du duVar) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().onDownloadProgress(duVar);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void c(du duVar, boolean z) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().c(duVar, z);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onDownloadStart(du duVar) {
        duVar.a(Long.valueOf(com.huawei.openalliance.ad.utils.ao.c()));
        a("72", duVar);
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().onDownloadStart(duVar);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void b(du duVar, boolean z) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().b(duVar, z);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(du duVar) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().a(duVar);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void a(du duVar, boolean z) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().a(duVar, z);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void onDownloadWaiting(du duVar) {
        Set<com.huawei.openalliance.ad.download.a<du>> a2 = a(duVar.n());
        if (a2 != null) {
            Iterator<com.huawei.openalliance.ad.download.a<du>> it = a2.iterator();
            while (it.hasNext()) {
                it.next().onDownloadWaiting(duVar);
            }
        }
    }

    private void a(final String str, final du duVar) {
        if (this.b != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ds.1
                @Override // java.lang.Runnable
                public void run() {
                    ds.this.b.a(duVar, str, currentTimeMillis);
                }
            });
        }
    }

    private Set<com.huawei.openalliance.ad.download.a<du>> a(String str) {
        Set<com.huawei.openalliance.ad.download.a<du>> set;
        synchronized (this) {
            set = this.f6818a.get(str);
        }
        return set;
    }

    ds(Context context) {
        this.b = new com.huawei.openalliance.ad.analysis.g(context);
    }
}
