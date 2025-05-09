package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.utils.m;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class dl {

    /* renamed from: a, reason: collision with root package name */
    private volatile long f6705a;
    private volatile int b;
    private File c;
    private dn d;
    private dk e;
    private volatile long f = 345600000;

    public void d() {
        com.huawei.openalliance.ad.utils.ae.b(this.c);
    }

    public void c(final String str) {
        ho.b("FileDiskCache", "remove key " + str);
        com.huawei.openalliance.ad.utils.ae.e(new File(this.c, str));
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.dl.1
            @Override // java.lang.Runnable
            public void run() {
                dl.this.a(str, true, (Integer) 2);
            }
        }, m.a.DISK_CACHE, false);
    }

    public int c() {
        return this.b;
    }

    public void b(long j) {
        this.f = j * 60000;
    }

    public String b(String str) {
        try {
            return new File(this.c, str).getCanonicalPath();
        } catch (IOException e) {
            ho.c("FileDiskCache", "getFilePath " + e.getClass().getSimpleName());
            return "";
        }
    }

    public long b() {
        return this.f6705a;
    }

    public boolean a(String str, File file, ContentResource contentResource) {
        boolean z;
        ho.b("FileDiskCache", "put file: " + str);
        File file2 = new File(this.c, str);
        if (com.huawei.openalliance.ad.utils.ae.a(file, file2)) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean lastModified = file2.setLastModified(currentTimeMillis);
            if (contentResource != null) {
                contentResource.a(currentTimeMillis);
            }
            ho.b("FileDiskCache", "set last modify result: " + lastModified);
            a(str, contentResource);
            z = true;
        } else {
            z = false;
        }
        if (contentResource == null || contentResource.f() != 1) {
            str = null;
        }
        d(str);
        return z;
    }

    public void a(String str, int i) {
        dn dnVar;
        if (!new File(this.c, str).exists() || (dnVar = this.d) == null) {
            return;
        }
        dnVar.a(str, i);
    }

    public void a(String str) {
        File file = new File(this.c, str);
        if (file.exists()) {
            com.huawei.openalliance.ad.utils.ae.c(file);
            dn dnVar = this.d;
            if (dnVar != null) {
                dnVar.a(str, System.currentTimeMillis());
            }
        }
    }

    public void a(dn dnVar) {
        this.d = dnVar;
    }

    public void a(dk dkVar) {
        if (dkVar != null) {
            this.e = dkVar;
        }
    }

    public void a(long j) {
        ho.a("FileDiskCache", "set max size: %d", Long.valueOf(j));
        this.f6705a = j;
    }

    public void a(int i) {
        ho.a("FileDiskCache", "set max num: %d", Integer.valueOf(i));
        this.b = i;
    }

    public File a() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int h() {
        File[] listFiles = this.c.listFiles();
        if (listFiles != null) {
            return a(listFiles);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long g() {
        File[] listFiles = this.c.listFiles();
        long j = 0;
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    j += file.length();
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ho.b("FileDiskCache", "clearAllExpiredFiles valid time: " + this.f);
        long currentTimeMillis = System.currentTimeMillis();
        File[] listFiles = this.c.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.lastModified() < currentTimeMillis - this.f) {
                    ho.a("FileDiskCache", "remove old expired file, modify time: %d file: %s", Long.valueOf(file.lastModified()), com.huawei.openalliance.ad.utils.dl.a(com.huawei.openalliance.ad.utils.ae.h(file)));
                    if (com.huawei.openalliance.ad.utils.ae.a(file)) {
                        ho.a("FileDiskCache", "file delete success");
                        a(file.getName(), true, (Integer) 1);
                    }
                }
                if (file.isDirectory() && (("normal".equals(this.e.f()) && file.getName().startsWith(MetaCreativeType.AR)) || Constants.WEBVIEW_CACHE.equals(this.e.f()))) {
                    ho.a("FileDiskCache", "delete file: %s", com.huawei.openalliance.ad.utils.dl.a(file.getName()));
                    com.huawei.openalliance.ad.utils.ae.b(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<String> e() {
        dn dnVar;
        List<String> e = this.e.e();
        return (com.huawei.openalliance.ad.utils.bg.a(e) || (dnVar = this.d) == null) ? new HashSet() : dnVar.a(e);
    }

    private void d(final String str) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.dl.2
            @Override // java.lang.Runnable
            public void run() {
                Set e = dl.this.e();
                if (!com.huawei.openalliance.ad.utils.cz.b(str)) {
                    e.add(str);
                }
                dl.this.f();
                long g = dl.this.g();
                ho.b("FileDiskCache", "cacheType: %s current used size: %s maxSize: %s", dl.this.e.f(), Long.valueOf(g), Long.valueOf(dl.this.f6705a));
                if (g > dl.this.f6705a) {
                    dl.this.a(g, (Set<String>) e);
                }
                int h = dl.this.h();
                ho.b("FileDiskCache", "cacheType: %s current used num: %s maxNum: %s", dl.this.e.f(), Integer.valueOf(h), Integer.valueOf(dl.this.b));
                if (h > dl.this.b) {
                    dl.this.a(h, (Set<String>) e);
                }
            }
        }, m.a.DISK_CACHE, false);
    }

    private boolean b(int i, List<ContentResource> list, Set<String> set) {
        ho.b("FileDiskCache", "delete files not recorded for num");
        File[] listFiles = this.c.listFiles();
        if (listFiles != null) {
            Arrays.sort(listFiles, new a());
            for (File file : listFiles) {
                if (file.isFile() && !a(file.getName(), list)) {
                    if (a(file, set) > 0) {
                        i--;
                    }
                    ho.a("FileDiskCache", "cachetype: %s current num: %d", this.e.f(), Integer.valueOf(i));
                    if (i <= this.b) {
                        ho.b("FileDiskCache", "used num is lower than max num");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void b(long j, List<ContentResource> list, Set<String> set) {
        ho.b("FileDiskCache", "delete sorted content resource files");
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        for (ContentResource contentResource : list) {
            File file = new File(this.c, contentResource.a());
            dk dkVar = this.e;
            boolean a2 = dkVar == null ? false : dkVar.a(contentResource.a());
            if (ho.a()) {
                ho.a("FileDiskCache", "deleteFromSortedContentResources, isAccessed: %s", Boolean.valueOf(a2));
            }
            if (a2 || !file.exists()) {
                Object[] objArr = new Object[1];
                if (a2) {
                    objArr[0] = contentResource.a();
                    ho.b("FileDiskCache", "file %s is using, not delete", objArr);
                } else {
                    objArr[0] = contentResource.a();
                    ho.b("FileDiskCache", "file %s not exist, delete table entry", objArr);
                    a(contentResource.a(), false, (Integer) null);
                }
            } else {
                j -= a(file, set);
                ho.a("FileDiskCache", "current size: %d", Long.valueOf(j));
                if (j <= this.f6705a) {
                    ho.b("FileDiskCache", "used cache space is lower than max size");
                    return;
                }
            }
        }
    }

    private static boolean a(String str, List<ContentResource> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return false;
        }
        Iterator<ContentResource> it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().a(), str)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(long j, List<ContentResource> list, Set<String> set) {
        ho.b("FileDiskCache", "delete files not recorded");
        File[] listFiles = this.c.listFiles();
        if (listFiles != null) {
            Arrays.sort(listFiles, new a());
            for (File file : listFiles) {
                if (file.isFile() && !a(file.getName(), list)) {
                    j -= a(file, set);
                    ho.a("FileDiskCache", "cachetype: %s current size: %d", this.e.f(), Long.valueOf(j));
                    if (j <= this.f6705a) {
                        ho.b("FileDiskCache", "used cache space is lower than max size");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, boolean z, Integer num) {
        dn dnVar = this.d;
        if (dnVar != null) {
            dnVar.a(str, z, num);
        }
    }

    private void a(String str, ContentResource contentResource) {
        dn dnVar = this.d;
        if (dnVar != null) {
            dnVar.a(str, contentResource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, Set<String> set) {
        dn dnVar = this.d;
        List<ContentResource> a2 = dnVar != null ? dnVar.a() : null;
        if (a(j, a2, set)) {
            ho.b("FileDiskCache", "there is enough space in disk cache");
        } else {
            b(g(), a2, set);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Set<String> set) {
        dn dnVar = this.d;
        List<ContentResource> a2 = dnVar != null ? dnVar.a() : null;
        if (b(i, a2, set)) {
            ho.b("FileDiskCache", "there is enough space in disk cache");
        } else {
            a(h(), a2, set);
        }
    }

    private void a(int i, List<ContentResource> list, Set<String> set) {
        ho.b("FileDiskCache", "delete sorted content resource files for num");
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        for (ContentResource contentResource : list) {
            File file = new File(this.c, contentResource.a());
            dk dkVar = this.e;
            boolean a2 = dkVar == null ? false : dkVar.a(contentResource.a());
            if (ho.a()) {
                ho.a("FileDiskCache", "deleteFromSortedContentResources, isAccessed: %s", Boolean.valueOf(a2));
            }
            if (a2 || !file.exists()) {
                Object[] objArr = new Object[1];
                if (a2) {
                    objArr[0] = contentResource.a();
                    ho.b("FileDiskCache", "file %s is using, not delete", objArr);
                } else {
                    objArr[0] = contentResource.a();
                    ho.b("FileDiskCache", "file %s not exist, delete table entry", objArr);
                    a(contentResource.a(), false, (Integer) null);
                }
            } else {
                if (a(file, set) > 0) {
                    i--;
                }
                ho.a("FileDiskCache", "cachetype: %s current num: %d", this.e.f(), Integer.valueOf(i));
                if (i <= this.b) {
                    ho.b("FileDiskCache", "used cache num is lower than max num");
                    return;
                }
            }
        }
    }

    static class a implements Comparator<File> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }

        private a() {
        }
    }

    private long a(File file, Set<String> set) {
        if (!com.huawei.openalliance.ad.utils.bg.a(set) && set.contains(file.getName())) {
            if (ho.a()) {
                ho.a("FileDiskCache", "file %s in blackList, don't delete", file.getName());
            }
            return 0L;
        }
        ho.a("FileDiskCache", "remove old exceeded file, modify time: %d file: %s", Long.valueOf(file.lastModified()), file.getName());
        long length = file.length();
        if (!com.huawei.openalliance.ad.utils.ae.a(file)) {
            return 0L;
        }
        ho.b("FileDiskCache", "file %s deleted", file.getName());
        a(file.getName(), true, (Integer) 0);
        return length;
    }

    private int a(File[] fileArr) {
        int i = 0;
        for (File file : fileArr) {
            if (file.isFile()) {
                i++;
            }
        }
        return i;
    }

    public dl(File file, long j, int i) {
        this.c = file;
        this.f6705a = j;
        this.b = i;
        com.huawei.openalliance.ad.utils.ae.g(file);
    }
}
