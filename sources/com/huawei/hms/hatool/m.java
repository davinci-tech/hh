package com.huawei.hms.hatool;

import android.content.Context;

/* loaded from: classes4.dex */
public final class m {
    private static m b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f4601a;

    public void a(String str) {
        v.a("hmsSdk", "HiAnalyticsDataManager.setAppid(String appid) is execute.");
        Context context = this.f4601a;
        if (context == null) {
            v.e("hmsSdk", "sdk is not init");
        } else {
            s.c().b().i(e1.a("appID", str, "[a-zA-Z0-9_][a-zA-Z0-9. _-]{0,255}", context.getPackageName()));
        }
    }

    public void a(Context context) {
        synchronized (c) {
            if (this.f4601a != null) {
                v.f("hmsSdk", "DataManager already initialized.");
                return;
            }
            this.f4601a = context;
            s.c().b().a(this.f4601a);
            s.c().b().j(context.getPackageName());
            j.a().a(context);
        }
    }

    private static void b() {
        synchronized (m.class) {
            if (b == null) {
                b = new m();
            }
        }
    }

    public static m a() {
        if (b == null) {
            b();
        }
        return b;
    }

    private m() {
    }
}
