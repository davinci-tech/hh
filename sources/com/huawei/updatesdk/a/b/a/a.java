package com.huawei.updatesdk.a.b.a;

import android.content.Context;

/* loaded from: classes7.dex */
public class a {
    private static final Object b = new Object();
    private static volatile a c;

    /* renamed from: a, reason: collision with root package name */
    private final Context f10813a;

    public String b() {
        Context context = this.f10813a;
        return (context == null || context.getFilesDir() == null) ? "" : this.f10813a.getFilesDir().getAbsolutePath();
    }

    public Context a() {
        return this.f10813a;
    }

    public static a c() {
        return c;
    }

    public static void a(Context context) {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
    }

    private a(Context context) {
        this.f10813a = context.getApplicationContext();
    }
}
