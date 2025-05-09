package defpackage;

import android.content.Context;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class odh {
    private static volatile boolean c;

    public static void a(Context context, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        c = false;
        oun.d();
        if (z) {
            oia.c();
        }
        c = true;
        LogUtil.d("HomeFragmentPreLoader", "preInitCardManager cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public static void b(Context context) {
        LogUtil.d("HomeFragmentPreLoader", "checkCardManagerInited");
        if (c) {
            LogUtil.d("HomeFragmentPreLoader", "async init completed");
        } else {
            d(context);
        }
    }

    private static void d(Context context) {
        oun.d();
        oia.c();
    }
}
