package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class ha {

    /* renamed from: a, reason: collision with root package name */
    private static gz f6894a;
    private static final byte[] b = new byte[0];

    public static void a(String str) {
        synchronized (b) {
            ho.a("LinkedAdConfigurationDataShare", "set soundSwitch for linked");
            gz gzVar = f6894a;
            if (gzVar == null) {
                return;
            }
            gzVar.a(str);
        }
    }

    public static void a(gz gzVar) {
        synchronized (b) {
            if (gzVar == null) {
                ho.a("LinkedAdConfigurationDataShare", "set linked ad from linked");
                f6894a = null;
            } else {
                f6894a = gzVar;
            }
        }
    }

    public static gz a() {
        gz gzVar;
        synchronized (b) {
            ho.a("LinkedAdConfigurationDataShare", "get linked ad from linked");
            gzVar = f6894a;
        }
        return gzVar;
    }
}
