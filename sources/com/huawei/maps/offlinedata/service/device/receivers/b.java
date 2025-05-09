package com.huawei.maps.offlinedata.service.device.receivers;

/* loaded from: classes5.dex */
public class b implements d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f6490a;

    private b() {
    }

    public static b a() {
        if (f6490a == null) {
            synchronized (b.class) {
                if (f6490a == null) {
                    f6490a = new b();
                }
            }
        }
        return f6490a;
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        try {
            com.huawei.maps.offlinedata.service.device.tlvtools.d a2 = com.huawei.maps.offlinedata.service.device.tlvtools.e.a(bArr);
            if (a2 == null) {
                com.huawei.maps.offlinedata.utils.g.c("DeviceRemainSpaceReceiver", "deviceRemainSpace TlvByteFather is null");
                return;
            }
            long j = 0;
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : a2.a()) {
                if (fVar.a() != 1) {
                    com.huawei.maps.offlinedata.utils.g.d("DeviceRemainSpaceReceiver", "handleDeviceRemainSpace type error:" + ((int) fVar.a()));
                } else {
                    j = com.huawei.maps.offlinedata.service.device.tlvtools.a.a(fVar.b(), -1L);
                    com.huawei.maps.offlinedata.utils.g.a("DeviceRemainSpaceReceiver", "handleDeviceRemainSpace deviceRemainSpace:" + j);
                }
            }
            com.huawei.maps.offlinedata.jsbridge.b.a().b("deviceClient.size", String.valueOf(j));
        } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g unused) {
            com.huawei.maps.offlinedata.utils.g.d("DeviceRemainSpaceReceiver", "catch TlvException");
        }
    }
}
