package com.amap.api.col.p0003sl;

import android.util.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public final class ea implements dy {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, a> f984a = new ConcurrentHashMap();

    final class a {

        /* renamed from: a, reason: collision with root package name */
        String f985a;
        String b;
        int c;
        final AtomicInteger d = new AtomicInteger(0);

        public a(int i, String str, String str2) {
            this.f985a = str;
            this.b = str2;
            this.c = i;
        }

        public final int a() {
            return this.d.incrementAndGet();
        }
    }

    private static String b(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        if (str == null) {
            str = "";
        }
        sb.append(str);
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    @Override // com.amap.api.col.p0003sl.dy
    public final void a(int i, String str, String str2) {
        try {
            String b = b(i, str, str2);
            a aVar = f984a.get(b);
            if (aVar == null) {
                aVar = new a(i, str, str2);
                f984a.put(b, aVar);
            }
            if (aVar.a() > 100) {
                a(aVar.c, aVar.f985a, aVar.b, aVar.d.get());
                f984a.remove(b);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.amap.api.col.p0003sl.dy
    public final void a() {
        try {
            Iterator<Map.Entry<String, a>> it = f984a.entrySet().iterator();
            while (it.hasNext()) {
                a value = it.next().getValue();
                if (value != null) {
                    a(value.c, value.f985a, value.b, value.d.get());
                }
            }
            f984a.clear();
            ik.a(dv.a()).a();
        } catch (Throwable unused) {
        }
    }

    private static void a(int i, String str, String str2, int i2) {
        if (i == 0) {
            ik.a(dv.a()).a(ij.a(str, str2 + " counter " + i2));
        } else {
            ik.a(dv.a()).a(ij.a(str, str2 + " counter " + i2));
        }
        if (dw.b) {
            c(i, str, str2 + " counter " + i2);
        }
    }

    private static void c(int i, String str, String str2) {
        if (i == 0) {
            Log.i("linklog", str + " " + str2);
            return;
        }
        Log.e("linklog", str + " " + str2);
    }
}
