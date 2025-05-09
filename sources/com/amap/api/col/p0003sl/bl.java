package com.amap.api.col.p0003sl;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class bl {

    /* renamed from: a, reason: collision with root package name */
    private static volatile bl f923a;
    private static iz b;
    private Context c;

    public static bl a(Context context) {
        if (f923a == null) {
            synchronized (bl.class) {
                if (f923a == null) {
                    f923a = new bl(context);
                }
            }
        }
        return f923a;
    }

    private bl(Context context) {
        this.c = context;
        b = b(context);
    }

    private static iz b(Context context) {
        try {
            return new iz(context, bk.a());
        } catch (Throwable th) {
            iv.c(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    private boolean b() {
        if (b == null) {
            b = b(this.c);
        }
        return b != null;
    }

    public final ArrayList<bg> a() {
        ArrayList<bg> arrayList = new ArrayList<>();
        if (!b()) {
            return arrayList;
        }
        Iterator it = b.b("", bg.class).iterator();
        while (it.hasNext()) {
            arrayList.add((bg) it.next());
        }
        return arrayList;
    }

    public final bg a(String str) {
        synchronized (this) {
            if (!b()) {
                return null;
            }
            List b2 = b.b(bg.e(str), bg.class);
            if (b2.size() <= 0) {
                return null;
            }
            return (bg) b2.get(0);
        }
    }

    public final void a(bg bgVar) {
        synchronized (this) {
            if (b()) {
                b.a(bgVar, bg.f(bgVar.h()));
                a(bgVar.e(), bgVar.a());
            }
        }
    }

    private static void a(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        String a2 = bi.a(str);
        if (b.b(a2, bi.class).size() > 0) {
            b.a(a2, bi.class);
        }
        String[] split = str2.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str3 : split) {
            arrayList.add(new bi(str, str3));
        }
        b.a((List) arrayList);
    }

    public final List<String> b(String str) {
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            if (!b()) {
                return arrayList;
            }
            arrayList.addAll(a((List<bi>) b.b(bi.a(str), bi.class)));
            return arrayList;
        }
    }

    private static List<String> a(List<bi> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() > 0) {
            Iterator<bi> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().a());
            }
        }
        return arrayList;
    }

    public final void c(String str) {
        synchronized (this) {
            if (b()) {
                b.a(bj.e(str), bj.class);
                b.a(bi.a(str), bi.class);
                b.a(bh.a(str), bh.class);
            }
        }
    }

    public final void b(bg bgVar) {
        synchronized (this) {
            if (b()) {
                b.a(bj.f(bgVar.h()), bj.class);
                b.a(bi.a(bgVar.e()), bi.class);
                b.a(bh.a(bgVar.e()), bh.class);
            }
        }
    }

    public final void a(String str, int i, long j, long j2, long j3) {
        if (b()) {
            a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    private void a(String str, int i, long j, long[] jArr, long[] jArr2) {
        synchronized (this) {
            if (b()) {
                b.a(new bh(str, j, i, jArr[0], jArr2[0]), bh.a(str));
            }
        }
    }

    public final String d(String str) {
        synchronized (this) {
            if (!b()) {
                return null;
            }
            List b2 = b.b(bj.f(str), bj.class);
            return b2.size() > 0 ? ((bj) b2.get(0)).d() : null;
        }
    }
}
