package com.huawei.maps.offlinedata.service.device;

import com.huawei.maps.offlinedata.service.device.consts.a;
import com.huawei.maps.offlinedata.service.device.receivers.h;
import com.huawei.maps.offlinedata.utils.g;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static volatile e f6487a;
    private final HashMap<Integer, Long> b = new HashMap<>();

    private e() {
    }

    public static e a() {
        if (f6487a == null) {
            synchronized (e.class) {
                if (f6487a == null) {
                    f6487a = new e();
                }
            }
        }
        return f6487a;
    }

    public void a(int i, long j) {
        this.b.put(Integer.valueOf(i), Long.valueOf(j));
    }

    public void a(int i) {
        this.b.remove(Integer.valueOf(i));
    }

    public HashMap<Integer, Long> b() {
        return this.b;
    }

    public com.huawei.maps.offlinedata.service.device.receivers.d b(int i) {
        if (i == a.b.QUERY_DEVICE_REMAIN_SPACE.a()) {
            return com.huawei.maps.offlinedata.service.device.receivers.b.a();
        }
        if (i == a.b.SYNC_POLITICAL_PERSPECTIVE.a() || i == a.b.TRANSMIT_MAP_HANDSHAKE.a()) {
            return com.huawei.maps.offlinedata.service.device.receivers.e.a();
        }
        if (i == a.b.DEVICE_REQUEST_POLITICAL_PERSPECTIVE.a()) {
            return com.huawei.maps.offlinedata.service.device.receivers.f.a();
        }
        if (i == a.b.QUERY_DEVICE_DOWNLOADED_MAP_INFO.a()) {
            return h.a();
        }
        if (i == a.b.DELETE_DEVICE_MAP.a()) {
            return com.huawei.maps.offlinedata.service.device.receivers.a.a();
        }
        g.d("OfflineMapReceiverManager", "getReceiver msgType wrong: " + i);
        return null;
    }
}
