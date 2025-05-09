package com.huawei.maps.offlinedata.service.device;

import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6473a;

    private a() {
    }

    public static a a() {
        if (f6473a == null) {
            synchronized (a.class) {
                if (f6473a == null) {
                    f6473a = new a();
                }
            }
        }
        return f6473a;
    }

    public void a(int i) {
        g.a("DeviceConnectStatusService", "onConnectStatusChanged statusCode: " + i);
        if (i == 2) {
            try {
                if (OfflineMapWearEngineManager.getInstance().getWearEngine4OfflineMap() != null) {
                    g.a("DeviceConnectStatusService", "call queryDevicePing from political.");
                    d.a().a(new IOfflineMapPingCallback() { // from class: com.huawei.maps.offlinedata.service.device.a$$ExternalSyntheticLambda0
                        @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback
                        public final void onPingResult(int i2) {
                            a.b(i2);
                        }
                    });
                } else {
                    g.a("DeviceConnectStatusService", "onConnectStatusChanged wearEngine4OfflineMap is null.");
                }
            } catch (Exception e) {
                g.d("DeviceConnectStatusService", "onConnectStatusChanged exception: " + e.getMessage());
                return;
            }
        }
        com.huawei.maps.offlinedata.jsbridge.b.a().b("deviceClient.connect", String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(int i) {
        g.a("DeviceConnectStatusService", "political onPingResult code:" + i);
        if (i == 202) {
            d.a().a(-1, 0);
        } else {
            g.a("DeviceConnectStatusService", "political ping failed.");
        }
    }
}
