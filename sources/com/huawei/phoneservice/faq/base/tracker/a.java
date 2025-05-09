package com.huawei.phoneservice.faq.base.tracker;

import android.content.Context;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static WeakReference<Context> f8237a;

    private static void b(Context context, boolean z) {
        f8237a = new WeakReference<>(context.getApplicationContext());
    }

    public static void e(Context context, boolean z) {
        b(context, z);
    }
}
