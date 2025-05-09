package com.huawei.watchface;

import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class y {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11206a = "y";

    public static void a() {
        synchronized (y.class) {
            HwLog.i(f11206a, "create -- enter");
            ac.a().a(ac.a().f());
            ab.a().a(CommonUtils.B());
        }
    }

    public static void b() {
        synchronized (y.class) {
            HwLog.i(f11206a, "resume -- enter");
            ab.a().a(false);
        }
    }

    public static void c() {
        synchronized (y.class) {
            HwLog.i(f11206a, "destroy -- enter");
            ab.a().e();
            ac.a().d();
            x.a().c();
        }
    }

    public static void d() {
        synchronized (y.class) {
            ac.a().g();
            ab.a().g();
        }
    }
}
