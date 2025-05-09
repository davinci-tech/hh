package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
final class fy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f1056a = false;

    public static void a() {
        synchronized (fy.class) {
            if (!f1056a) {
                fz.a().a("regeo", new gb("/geocode/regeo"));
                fz.a().a("placeAround", new gb("/place/around"));
                fz.a().a("placeText", new ga("/place/text"));
                fz.a().a("geo", new ga("/geocode/geo"));
                f1056a = true;
            }
        }
    }
}
