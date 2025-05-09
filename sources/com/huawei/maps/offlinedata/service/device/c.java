package com.huawei.maps.offlinedata.service.device;

import com.huawei.maps.offlinedata.health.p2p.OfflineMapP2pMessage;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile c f6475a;

    private c() {
    }

    public static c a() {
        if (f6475a == null) {
            synchronized (c.class) {
                if (f6475a == null) {
                    f6475a = new c();
                }
            }
        }
        return f6475a;
    }

    public void a(int i, OfflineMapP2pMessage offlineMapP2pMessage) {
        byte[] data = offlineMapP2pMessage.getData();
        if (data == null) {
            return;
        }
        byte b = data[0];
        g.a("DeviceReceiverService", "onDataReceived msgType: " + ((int) b));
        int length = data.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(data, 1, bArr, 0, length);
        com.huawei.maps.offlinedata.service.device.receivers.d b2 = e.a().b(b);
        if (b2 != null) {
            b2.a(b, bArr);
        }
    }
}
