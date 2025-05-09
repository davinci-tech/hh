package com.amap.api.col.p0003sl;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class jd {

    /* renamed from: a, reason: collision with root package name */
    private hz f1211a;
    private volatile int b = -1;

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static Map<String, jd> f1212a = new HashMap();
    }

    public static jd a(hz hzVar) {
        if (a.f1212a.get(hzVar.a()) == null) {
            a.f1212a.put(hzVar.a(), new jd(hzVar));
        }
        return a.f1212a.get(hzVar.a());
    }

    private jd(hz hzVar) {
        this.f1211a = hzVar;
    }

    public final void a(Context context, boolean z, boolean z2) {
        jh.a(context, this.f1211a, "sckey", String.valueOf(z));
        if (z) {
            jh.a(context, this.f1211a, "scisf", String.valueOf(z2));
        }
    }

    public final boolean a(Context context) {
        try {
            return Boolean.parseBoolean(jh.a(context, this.f1211a, "sckey"));
        } catch (Throwable unused) {
            return false;
        }
    }

    public final boolean b(Context context) {
        try {
            return Boolean.parseBoolean(jh.a(context, this.f1211a, "scisf"));
        } catch (Throwable unused) {
            return true;
        }
    }
}
