package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class eu extends ep implements ft {
    private static eu c;
    private static final byte[] d = new byte[0];
    private static final byte[] e = new byte[0];

    @Override // com.huawei.openalliance.ad.ft
    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            ho.d("ContentResourceDao", "contentId is null, can't update content resource");
            return;
        }
        synchronized (d) {
            List<ContentResource> e2 = e(str);
            if (com.huawei.openalliance.ad.utils.bg.a(e2)) {
                ho.b("ContentResourceDao", "contentResources is empty");
            } else {
                for (ContentResource contentResource : e2) {
                    ho.a("ContentResourceDao", "contentResource slotId: %s contentId: %s useCount: %s", contentResource.b(), contentResource.c(), Integer.valueOf(contentResource.h()));
                    contentResource.e(contentResource.h() + 1);
                }
                a(e2);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.ft
    public List<ContentResource> c(String str, String str2) {
        return TextUtils.isEmpty(str) ? new ArrayList() : a(ContentResource.class, null, fi.CONTENT_BY_RESOURCE_NAME_AND_CACHETYPE_WHERE, new String[]{str, str2}, null, null);
    }

    @Override // com.huawei.openalliance.ad.ft
    public List<ContentResource> c(String str) {
        return a(ContentResource.class, null, fi.CONTENT_BY_CACHETYPE_WHERE, new String[]{str}, "priority ASC", null);
    }

    @Override // com.huawei.openalliance.ad.ft
    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            ho.c("ContentResourceDao", "deleteContentResource with empty contentId");
        } else {
            ho.a("ContentResourceDao", "deleteContentResourceByContentId: %s", str);
            a(ContentResource.class, fi.CONTENT_BY_CONTENT_ID_AND_RESOURCE_NAME_WHERE, new String[]{str, str2});
        }
    }

    @Override // com.huawei.openalliance.ad.ft
    public List<ContentResource> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            ho.d("ContentResourceDao", "contentId is null, can't update content resource");
            return arrayList;
        }
        synchronized (d) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.addAll(e(it.next()));
            }
        }
        return arrayList;
    }

    @Override // com.huawei.openalliance.ad.ft
    public List<ContentResource> b(String str) {
        return a(ContentResource.class, null, fi.CONTENT_BY_CACHETYPE_WHERE, new String[]{str}, "priority ASC, updateTime ASC", null);
    }

    @Override // com.huawei.openalliance.ad.ft
    public void a(List<ContentResource> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (ContentResource contentResource : list) {
            if (contentResource != null) {
                arrayList.add(a(contentResource));
            }
        }
        c(arrayList);
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            ho.c("ContentResourceDao", "deleteContentResource with empty file name");
        } else {
            ho.a("ContentResourceDao", "deleteContentResourceByName: %s cacheType: %s", str, str2);
            a(ContentResource.class, fi.CONTENT_BY_RESOURCE_NAME_AND_CACHETYPE_WHERE, new String[]{str, str2});
        }
    }

    @Override // com.huawei.openalliance.ad.ft
    public void a(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            ho.d("ContentResourceDao", "contentId is null, can't update prio");
            return;
        }
        synchronized (d) {
            List<ContentResource> e2 = e(str);
            if (com.huawei.openalliance.ad.utils.bg.a(e2)) {
                ho.b("ContentResourceDao", "contentResources is empty");
            } else {
                ArrayList arrayList = new ArrayList();
                for (ContentResource contentResource : e2) {
                    if (ho.a()) {
                        ho.a("ContentResourceDao", "contentResource fileName: %s, contentId: %s oldPrio: %s newPrio: %s cacheType: %s", contentResource.a(), contentResource.c(), Integer.valueOf(contentResource.e()), Integer.valueOf(i), str2);
                    }
                    List<ContentResource> c2 = c(contentResource.a(), str2);
                    if (com.huawei.openalliance.ad.utils.bg.a(c2)) {
                        ho.b("ContentResourceDao", "contentResourcesByName is empty");
                    } else {
                        for (ContentResource contentResource2 : c2) {
                            if (i != contentResource2.e()) {
                                contentResource2.b(i);
                                arrayList.add(contentResource2);
                            }
                        }
                    }
                }
                a(arrayList);
            }
        }
    }

    public void a(ContentResource contentResource, String str) {
        if (contentResource == null) {
            return;
        }
        synchronized (d) {
            String c2 = contentResource.c();
            if (TextUtils.isEmpty(c2)) {
                ho.b("ContentResourceDao", "insertContent - content id is empty");
                return;
            }
            if (com.huawei.openalliance.ad.utils.bg.a(a(c2, contentResource.a(), str, contentResource.b()))) {
                ho.a("ContentResourceDao", "insert contentid: %s fileName: %s cacheType: %s", c2, contentResource.a(), str);
                contentResource.e(str);
                a(ContentResource.class, contentResource.d(this.f6846a));
            } else {
                ho.b("ContentResourceDao", "resource is exist, contentId:" + c2);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.ft
    public List<ContentResource> a(String str) {
        return TextUtils.isEmpty(str) ? new ArrayList() : a(ContentResource.class, null, fi.CONTENT_BY_RESOURCE_NAME_WHERE, new String[]{str}, null, null);
    }

    private List<ContentResource> e(String str) {
        return TextUtils.isEmpty(str) ? new ArrayList() : a(ContentResource.class, null, fi.CONTENT_BY_CONTENT_ID_WHERE, new String[]{str}, null, null);
    }

    private List<ContentResource> a(String str, String str2, String str3, String str4) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? new ArrayList() : a(ContentResource.class, null, fi.CONTENT_BY_RESOURCE_NAME_AND_CONTENTID_AND_CACHETYPE_AND_SLOTID_WHERE, new String[]{str2, str, str3, str4}, null, null);
    }

    public static eu a(Context context) {
        eu euVar;
        synchronized (e) {
            if (c == null) {
                c = new eu(context);
            }
            euVar = c;
        }
        return euVar;
    }

    private de a(ContentResource contentResource) {
        return new de("ContentResource", null, null, fi.CONTENT_BY_RESOURCE_NAME_AND_CONTENTID_AND_CACHETYPE_AND_SLOTID_WHERE.a(), new String[]{contentResource.a(), contentResource.c(), contentResource.j(), contentResource.b()}, contentResource.d(this.f6846a));
    }

    protected eu(Context context) {
        super(context);
    }
}
