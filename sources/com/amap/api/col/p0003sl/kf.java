package com.amap.api.col.p0003sl;

import android.content.Context;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class kf {

    /* renamed from: a, reason: collision with root package name */
    static WeakReference<kd> f1252a;

    public static void a(final String str, final Context context) {
        iv.d().submit(new Runnable() { // from class: com.amap.api.col.3sl.kf.1
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (kf.class) {
                    try {
                        String a2 = hv.a(ia.a(str));
                        kd a3 = kk.a(kf.f1252a);
                        kk.a(context, a3, it.j, 50, 102400, "10");
                        if (a3.e == null) {
                            a3.e = new jl(new jo(new jn()));
                        }
                        ke.a(a2, ia.a(" \"timestamp\":\"" + ia.a(System.currentTimeMillis(), "yyyyMMdd HH:mm:ss") + "\",\"details\":" + str), a3);
                    } finally {
                    }
                }
            }
        });
    }
}
