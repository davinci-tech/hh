package com.huawei.maps.offlinedata.service.device.receivers;

/* loaded from: classes5.dex */
public class f implements d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile f f6493a;

    public static f a() {
        if (f6493a == null) {
            synchronized (f.class) {
                if (f6493a == null) {
                    f6493a = new f();
                }
            }
        }
        return f6493a;
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        com.huawei.maps.offlinedata.service.device.d.a().a(-1, 0);
    }
}
