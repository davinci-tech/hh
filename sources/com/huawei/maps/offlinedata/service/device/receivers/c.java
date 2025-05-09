package com.huawei.maps.offlinedata.service.device.receivers;

import com.huawei.maps.offlinedata.service.device.consts.a;

/* loaded from: classes5.dex */
public class c implements d {

    /* renamed from: a, reason: collision with root package name */
    private final String f6491a;
    private final long b;

    public c(String str, long j) {
        this.f6491a = str;
        this.b = j;
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        try {
            int i2 = -1;
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : com.huawei.maps.offlinedata.service.device.tlvtools.e.a(bArr).a()) {
                if (fVar.a() == 12) {
                    i2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(fVar.b(), -1);
                    com.huawei.maps.offlinedata.utils.g.a("HandShakeReceiver", "handleHandshakeResponse handshakeValue:" + i2);
                } else if (fVar.a() != 19) {
                    com.huawei.maps.offlinedata.utils.g.c("HandShakeReceiver", "handleHandshakeResponse type error:" + ((int) fVar.a()));
                } else {
                    com.huawei.maps.offlinedata.utils.g.a("HandShakeReceiver", "handleHandshakeResponse msgId:" + com.huawei.maps.offlinedata.service.device.tlvtools.a.b(fVar.b(), -1));
                }
            }
            if (i2 == a.EnumC0168a.MAP_SUCCESS.a()) {
                com.huawei.maps.offlinedata.utils.g.a("HandShakeReceiver", "handshake success, start transmit file. ");
                com.huawei.maps.offlinedata.service.device.d.a().a(this.f6491a, this.b);
            } else {
                com.huawei.maps.offlinedata.service.device.f.a().b(String.valueOf(this.b));
            }
        } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g unused) {
            com.huawei.maps.offlinedata.utils.g.d("HandShakeReceiver", "catch TlvException");
            com.huawei.maps.offlinedata.service.device.f.a().b(String.valueOf(this.b));
        }
    }
}
