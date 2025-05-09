package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.utils.m;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class di implements dn {

    /* renamed from: a, reason: collision with root package name */
    private Context f6700a;
    private String b;

    @Override // com.huawei.openalliance.ad.dn
    public void a(String str, boolean z, Integer num) {
        if (TextUtils.isEmpty(str)) {
            ho.b("DiskCacheFileOperation", "fileName is empty");
            return;
        }
        ho.b("DiskCacheFileOperation", "onFileRemoved: %s", str);
        List<ContentResource> c = eu.a(this.f6700a).c(str, this.b);
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            ho.b("DiskCacheFileOperation", "contentResources is empty");
            return;
        }
        ho.b("DiskCacheFileOperation", "contentResources is not empty");
        if (z) {
            new com.huawei.openalliance.ad.analysis.g(this.f6700a).a(c, num);
        }
        eu.a(this.f6700a).a(str, this.b);
        for (ContentResource contentResource : c) {
            if (MetaCreativeType.AR.equalsIgnoreCase(contentResource.i())) {
                ho.b("DiskCacheFileOperation", "AR deleteUnzipDir");
                a(str);
            } else if (contentResource.d() == 1 || contentResource.d() == 18) {
                if (TextUtils.equals(this.b, "normal")) {
                    es.a(this.f6700a).c(contentResource.c());
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.dn
    public void a(String str, final ContentResource contentResource) {
        if (contentResource != null) {
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.di.1
                @Override // java.lang.Runnable
                public void run() {
                    eu.a(di.this.f6700a).a(contentResource, di.this.b);
                }
            }, m.a.DISK_CACHE, false);
        }
    }

    @Override // com.huawei.openalliance.ad.dn
    public void a(final String str, final long j) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.di.2
            @Override // java.lang.Runnable
            public void run() {
                eu a2 = eu.a(di.this.f6700a);
                List<ContentResource> c = a2.c(str, di.this.b);
                if (com.huawei.openalliance.ad.utils.bg.a(c)) {
                    return;
                }
                Iterator<ContentResource> it = c.iterator();
                while (it.hasNext()) {
                    it.next().a(j);
                }
                a2.a(c);
            }
        }, m.a.DISK_CACHE, false);
    }

    @Override // com.huawei.openalliance.ad.dn
    public void a(final String str, final int i) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.di.3
            @Override // java.lang.Runnable
            public void run() {
                eu a2 = eu.a(di.this.f6700a);
                List<ContentResource> c = a2.c(str, di.this.b);
                if (com.huawei.openalliance.ad.utils.bg.a(c)) {
                    return;
                }
                Iterator<ContentResource> it = c.iterator();
                while (it.hasNext()) {
                    it.next().b(i);
                }
                a2.a(c);
            }
        }, m.a.DISK_CACHE, false);
    }

    @Override // com.huawei.openalliance.ad.dn
    public Set<String> a(List<String> list) {
        HashSet hashSet = new HashSet();
        List<ContentResource> b = eu.a(this.f6700a).b(list);
        if (!com.huawei.openalliance.ad.utils.bg.a(b)) {
            for (ContentResource contentResource : b) {
                if (contentResource != null) {
                    hashSet.add(contentResource.a());
                }
            }
        }
        return hashSet;
    }

    @Override // com.huawei.openalliance.ad.dn
    public List<ContentResource> a() {
        eu a2 = eu.a(this.f6700a);
        return 1 == fh.b(this.f6700a).by() ? a2.c(this.b) : a2.b(this.b);
    }

    private void a(String str) {
        StringBuilder sb;
        try {
            com.huawei.openalliance.ad.utils.ae.a(dk.a(this.f6700a, this.b).getCanonicalPath() + File.separator + MetaCreativeType.AR + str);
        } catch (IOException e) {
            e = e;
            sb = new StringBuilder("IOException delete ar unzip dir:");
            sb.append(e.getClass().getSimpleName());
            ho.c("DiskCacheFileOperation", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("Exception delete ar unzip dir:");
            sb.append(e.getClass().getSimpleName());
            ho.c("DiskCacheFileOperation", sb.toString());
        }
    }

    public di(Context context, String str) {
        this.f6700a = context.getApplicationContext();
        this.b = str;
    }
}
