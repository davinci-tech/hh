package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class dk {

    /* renamed from: a, reason: collision with root package name */
    private dl f6704a;
    private dm b;
    private final byte[] c = new byte[0];
    private Context d;
    private SoftReference<List<String>> e;
    private String f;
    private String g;

    public void j(String str) {
        String k;
        dl a2 = a(true);
        if (a2 == null || (k = k(str)) == null) {
            return;
        }
        try {
            a2.c(k);
        } catch (Exception e) {
            ho.c(this.f, "deleteCacheFile " + e.getClass().getSimpleName());
        }
    }

    public void h(String str) {
        dl a2 = a(true);
        if (a2 == null) {
            ho.c(this.f, "fileDiskCache is null");
            return;
        }
        String k = k(str);
        if (TextUtils.isEmpty(k)) {
            return;
        }
        a2.a(k);
    }

    public boolean g(String str) {
        String c = c(str);
        return c != null && com.huawei.openalliance.ad.utils.ae.d(new File(c));
    }

    public boolean f(String str) {
        String c = c(str);
        return c != null && com.huawei.openalliance.ad.utils.ae.b(c);
    }

    public String f() {
        return this.g;
    }

    public List<String> e() {
        SoftReference<List<String>> softReference = this.e;
        List<String> list = softReference != null ? softReference.get() : null;
        return com.huawei.openalliance.ad.utils.bg.a(list) ? new ArrayList() : list;
    }

    public String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String d = d(str);
        return f(d) ? d : "";
    }

    public void d() {
        if (this.e != null) {
            this.e = null;
        }
    }

    public String c(String str) {
        String k = k(str);
        if (k != null) {
            return b(k);
        }
        return null;
    }

    public long c() {
        dl a2 = a(false);
        if (a2 == null) {
            return 0L;
        }
        return a2.b();
    }

    public void b(long j) {
        dl a2 = a(false);
        if (a2 == null) {
            return;
        }
        a2.b(j);
    }

    public String b(String str) {
        dl a2 = a(false);
        if (a2 == null) {
            return null;
        }
        return a2.b(str);
    }

    public int b() {
        dl a2 = a(false);
        if (a2 == null) {
            return 0;
        }
        return a2.c();
    }

    public boolean a(String str) {
        dm dmVar = this.b;
        if (dmVar != null) {
            return dmVar.a(str);
        }
        return false;
    }

    public boolean a(File file, String str, ContentResource contentResource) {
        dl a2 = a(true);
        if (a2 == null) {
            ho.c(this.f, "fileDiskCache is null");
            return false;
        }
        if (contentResource != null) {
            contentResource.e(this.g);
        }
        String k = k(str);
        if (k == null || file == null || !file.exists()) {
            ho.c(this.f, "param error");
            return false;
        }
        try {
            return a2.a(k, file, contentResource);
        } catch (Exception e) {
            ho.c(this.f, "putOuterFileToCache " + e.getClass().getSimpleName());
            return false;
        }
    }

    public void a(List<String> list, boolean z) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        try {
            SoftReference<List<String>> softReference = this.e;
            List<String> list2 = softReference != null ? softReference.get() : null;
            if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
                list2 = new ArrayList<>();
            } else if (z) {
                list2.clear();
            }
            list2.addAll(list);
            this.e = new SoftReference<>(list2);
        } catch (Throwable th) {
            ho.d(this.f, "updateCacheExcludeList exception: %s", th.getClass().getSimpleName());
        }
    }

    public void a(String str, int i) {
        dl a2 = a(true);
        if (a2 == null) {
            ho.c(this.f, "fileDiskCache is null");
            return;
        }
        String k = k(str);
        if (TextUtils.isEmpty(k)) {
            return;
        }
        a2.a(k, i);
    }

    public void a(long j) {
        dl a2 = a(false);
        if (a2 == null) {
            return;
        }
        a2.a(j);
    }

    public void a(int i) {
        dl a2 = a(false);
        if (a2 == null) {
            return;
        }
        a2.a(i);
    }

    public void a() {
        synchronized (this.c) {
            dl dlVar = this.f6704a;
            if (dlVar != null) {
                try {
                    try {
                        dlVar.d();
                    } catch (Exception unused) {
                        ho.d(this.f, "clear cache error");
                    }
                    this.f6704a = null;
                } catch (Throwable th) {
                    this.f6704a = null;
                    throw th;
                }
            }
            dm dmVar = this.b;
            try {
                if (dmVar != null) {
                    try {
                        dmVar.stopWatching();
                    } catch (Exception unused2) {
                        ho.d(this.f, "stop watching error");
                    }
                    this.b = null;
                }
                d();
            } catch (Throwable th2) {
                this.b = null;
                throw th2;
            }
        }
    }

    public static String l(String str) {
        String[] split;
        int length;
        if (com.huawei.openalliance.ad.utils.cz.b(str) || (length = (split = str.split(File.separator)).length) == 0) {
            return str;
        }
        String str2 = split[length - 1];
        if (com.huawei.openalliance.ad.utils.cz.b(str2)) {
            return str;
        }
        return Scheme.DISKCACHE + str2;
    }

    public static String k(String str) {
        String scheme = Scheme.DISKCACHE.toString();
        if (str == null || !str.startsWith(scheme)) {
            return null;
        }
        return str.substring(scheme.length());
    }

    public static boolean i(String str) {
        return str != null && str.startsWith(Scheme.DISKCACHE.toString());
    }

    public static String d(String str) {
        return Scheme.DISKCACHE.toString() + com.huawei.openalliance.ad.utils.cu.a(str);
    }

    private boolean b(Context context, String str) {
        File a2;
        String str2;
        StringBuilder sb;
        if (this.f6704a != null) {
            return true;
        }
        if (context == null || (a2 = a(context, str)) == null) {
            return false;
        }
        try {
            dm dmVar = Build.VERSION.SDK_INT >= 29 ? new dm(a2) : new dm(a2.getPath());
            this.b = dmVar;
            dmVar.startWatching();
        } catch (Throwable th) {
            ho.c(this.f, "start fileListener failed, " + th.getClass().getSimpleName());
            ho.a(3, th);
            this.b = null;
        }
        try {
            gc b = fh.b(context);
            dl dlVar = new dl(a2, b.aZ() * 1048576, b.av());
            this.f6704a = dlVar;
            dlVar.b(b.bA().longValue());
            this.f6704a.a(new di(context, str));
            this.f6704a.a(this);
            return true;
        } catch (IllegalStateException e) {
            e = e;
            str2 = this.f;
            sb = new StringBuilder("Unable to create FileDiskCache");
            sb.append(e.getClass().getSimpleName());
            ho.c(str2, sb.toString());
            return false;
        } catch (Exception e2) {
            e = e2;
            str2 = this.f;
            sb = new StringBuilder("Unable to create FileDiskCache ex: ");
            sb.append(e.getClass().getSimpleName());
            ho.c(str2, sb.toString());
            return false;
        }
    }

    public static File a(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        if (com.huawei.openalliance.ad.utils.cz.b(str) || "normal".equals(str)) {
            return new File(cacheDir, File.separator + Constants.PPS_ROOT_PATH + File.separator + "pps_diskcache");
        }
        return new File(cacheDir, File.separator + Constants.PPS_ROOT_PATH + File.separator + "pps_new_diskcache" + File.separator + str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0022, code lost:
    
        if (r5.exists() == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.openalliance.ad.dl a(boolean r5) {
        /*
            r4 = this;
            byte[] r0 = r4.c
            monitor-enter(r0)
            com.huawei.openalliance.ad.dl r1 = r4.f6704a     // Catch: java.lang.Throwable -> L4f
            if (r1 != 0) goto L16
            java.lang.String r5 = r4.g     // Catch: java.lang.Throwable -> L4f
            java.lang.String r1 = "fileDiskCache is null, recreate"
            com.huawei.openalliance.ad.ho.b(r5, r1)     // Catch: java.lang.Throwable -> L4f
            android.content.Context r5 = r4.d     // Catch: java.lang.Throwable -> L4f
            java.lang.String r1 = r4.g     // Catch: java.lang.Throwable -> L4f
            r4.b(r5, r1)     // Catch: java.lang.Throwable -> L4f
            goto L4b
        L16:
            if (r5 == 0) goto L4b
            java.io.File r5 = r1.a()     // Catch: java.lang.Throwable -> L4f
            if (r5 == 0) goto L24
            boolean r5 = r5.exists()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4f
            if (r5 != 0) goto L4b
        L24:
            r5 = 0
            r4.f6704a = r5     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4f
            android.content.Context r5 = r4.d     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4f
            java.lang.String r1 = r4.g     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4f
            r4.b(r5, r1)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4f
            goto L4b
        L2f:
            r5 = move-exception
            java.lang.String r1 = r4.f     // Catch: java.lang.Throwable -> L4f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f
            java.lang.String r3 = "init diskcache error:"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4f
            java.lang.Class r5 = r5.getClass()     // Catch: java.lang.Throwable -> L4f
            java.lang.String r5 = r5.getSimpleName()     // Catch: java.lang.Throwable -> L4f
            r2.append(r5)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L4f
            com.huawei.openalliance.ad.ho.c(r1, r5)     // Catch: java.lang.Throwable -> L4f
        L4b:
            com.huawei.openalliance.ad.dl r5 = r4.f6704a     // Catch: java.lang.Throwable -> L4f
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4f
            return r5
        L4f:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4f
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.dk.a(boolean):com.huawei.openalliance.ad.dl");
    }

    public dk(Context context, String str) {
        this.f = "DiskCacheManager_" + str;
        if (context == null) {
            return;
        }
        this.g = str;
        this.d = context.getApplicationContext();
        b(context, str);
    }
}
