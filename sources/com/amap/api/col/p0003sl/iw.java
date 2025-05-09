package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class iw {

    /* renamed from: a, reason: collision with root package name */
    private static WeakReference<kd> f1203a = null;
    private static boolean b = true;
    private static WeakReference<kx> c = null;
    private static WeakReference<kx> d = null;
    private static String[] e = new String[10];
    private static int f = 0;
    private static boolean g = false;
    private static int h;
    private static hz i;

    public interface a {
        void a(int i);
    }

    private static boolean a(hz hzVar) {
        return hzVar != null && hzVar.e();
    }

    private static void a(Context context, hz hzVar, int i2, String str, String str2) {
        String str3;
        String a2 = kk.a();
        String a3 = kk.a(context, hzVar);
        hn.a(context);
        String a4 = kk.a(a3, a2, i2, str, str2);
        if (a4 == null || "".equals(a4)) {
            return;
        }
        String c2 = hv.c(str2);
        if (i2 == 1) {
            str3 = it.b;
        } else if (i2 == 2) {
            str3 = it.d;
        } else if (i2 != 0) {
            return;
        } else {
            str3 = it.c;
        }
        String str4 = str3;
        kd a5 = kk.a(f1203a);
        kk.a(context, a5, str4, 1000, 4096000, "1");
        if (a5.e == null) {
            a5.e = new jk(new jl(new jn(new jo())));
        }
        try {
            ke.a(c2, ia.a(a4.replaceAll("\n", "<br/>")), a5);
        } catch (Throwable unused) {
        }
    }

    static void a(Context context) {
        String a2;
        hz hzVar;
        List<hz> a3 = it.a();
        if (a3 == null || a3.size() == 0 || (a2 = a(a3)) == null || "".equals(a2) || (hzVar = i) == null) {
            return;
        }
        a(context, hzVar, 2, "ANR", a2);
    }

    public static void a(Context context, hz hzVar, String str, int i2, String str2, String str3) {
        if (str2 == null || "".equals(str2)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append("class:");
            sb.append(str2);
        }
        if (str3 != null) {
            sb.append(" method:");
            sb.append(str3);
            sb.append("$<br/>");
        }
        sb.append(str);
        a(context, hzVar, i2, str2, sb.toString());
    }

    public static void a(Context context, Throwable th, int i2, String str, String str2) {
        String a2 = ia.a(th);
        hz a3 = a(a2);
        if (a(a3)) {
            String replaceAll = a2.replaceAll("\n", "<br/>");
            String th2 = th.toString();
            if (th2 == null || "".equals(th2)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (str != null) {
                sb.append("class:");
                sb.append(str);
            }
            if (str2 != null) {
                sb.append(" method:");
                sb.append(str2);
                sb.append("$<br/>");
            }
            sb.append(replaceAll);
            a(context, a3, i2, th2, sb.toString());
        }
    }

    static void a(hz hzVar, Context context, String str, String str2) {
        if (!a(hzVar) || str == null || "".equals(str)) {
            return;
        }
        a(context, hzVar, 1, str, str2);
    }

    public static void a(final Context context, final hz hzVar, final String str, final kd kdVar, final String str2) {
        try {
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.iw.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    String a2 = iw.a(context, hzVar, str, str2);
                    if (kdVar.e == null) {
                        kdVar.e = new jk(new jl(new jn(new jo())));
                    }
                    try {
                        ke.a(hv.c(a2), ia.a(a2), kdVar);
                    } catch (Throwable unused) {
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    static void b(Context context) {
        kv kvVar = new kv(b);
        b = false;
        a(context, kvVar, it.c);
    }

    static void c(Context context) {
        WeakReference<kx> weakReference = c;
        if (weakReference == null || weakReference.get() == null) {
            c = new WeakReference<>(new kw(context, 3600000, "hKey", new ky(context, false)));
        }
        a(context, c.get(), it.d);
    }

    static void d(Context context) {
        WeakReference<kx> weakReference = d;
        if (weakReference == null || weakReference.get() == null) {
            d = new WeakReference<>(new kw(context, 3600000, "gKey", new ky(context, false)));
        }
        a(context, d.get(), it.b);
    }

    private static void a(final Context context, final kx kxVar, final String str) {
        la.a().a(new lb() { // from class: com.amap.api.col.3sl.iw.2
            @Override // com.amap.api.col.p0003sl.lb
            public final void runTask() {
                try {
                    synchronized (iw.class) {
                        kd a2 = kk.a(iw.f1203a);
                        kk.a(context, a2, str, 1000, 4096000, "1");
                        a2.f = kxVar;
                        if (a2.g == null) {
                            a2.g = new ko(new kn(context, new ks(), new jl(new jn(new jo())), "QImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJhZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMi", hn.f(context), hr.v(context), hr.u(context), hn.c(context), Build.MODEL, hn.b(context), hn.d(context), Build.VERSION.RELEASE));
                        }
                        a2.h = 3600000;
                        ke.a(a2);
                    }
                } catch (Throwable th) {
                    iv.c(th, "lg", "pul");
                }
            }
        });
    }

    public static void a(final Context context, final kd kdVar, final a aVar) {
        try {
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.iw.3
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    try {
                        synchronized (iw.class) {
                            if (kd.this.g == null) {
                                kd.this.g = new ko(new kn(context, new ks(), new jl(new jn(new jo())), "QImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJhZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMi", hn.f(context), hr.v(context), hr.u(context), hn.c(context), Build.MODEL, hn.b(context), hn.d(context), Build.VERSION.RELEASE));
                            }
                            int a2 = ke.a(kd.this);
                            a aVar2 = aVar;
                            if (aVar2 != null) {
                                aVar2.a(a2);
                            }
                        }
                    } catch (Throwable th) {
                        iv.c(th, "lg", "pul");
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    private static hz a(String str) {
        List<hz> a2 = it.a();
        if (a2 == null) {
            a2 = new ArrayList();
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        for (hz hzVar : a2) {
            if (it.a(hzVar.f(), str)) {
                return hzVar;
            }
        }
        if (str.contains("com.amap.api.col")) {
            try {
                return ia.a();
            } catch (hm e2) {
                e2.printStackTrace();
            }
        }
        if (!str.contains("com.amap.co") && !str.contains("com.amap.opensdk.co") && !str.contains("com.amap.location")) {
            return null;
        }
        try {
            hz b2 = ia.b();
            b2.a(true);
            return b2;
        } catch (hm e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x00e9, code lost:
    
        if (r4 == null) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00c1, code lost:
    
        if (r4 == null) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.util.List<com.amap.api.col.p0003sl.hz> r11) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.iw.a(java.util.List):java.lang.String");
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i2 = f; i2 < 10 && i2 <= 9; i2++) {
                sb.append(e[i2]);
            }
            for (int i3 = 0; i3 < f; i3++) {
                sb.append(e[i3]);
            }
        } catch (Throwable th) {
            iv.c(th, "alg", "gLI");
        }
        return sb.toString();
    }

    static /* synthetic */ String a(Context context, hz hzVar, String str, String str2) {
        String a2 = kk.a();
        String a3 = kk.a(context, hzVar);
        hn.a(context);
        return kk.a(a3, a2, str, str2);
    }
}
