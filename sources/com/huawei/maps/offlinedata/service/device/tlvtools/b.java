package com.huawei.maps.offlinedata.service.device.tlvtools;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static int f6497a;

    public static int a() {
        int i;
        synchronized (b.class) {
            int i2 = f6497a + 1;
            f6497a = i2;
            i = i2 % 10000;
            f6497a = i;
        }
        return i;
    }
}
