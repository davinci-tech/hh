package com.huawei.maps.offlinedata.service.device.receivers;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class e implements d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile e f6492a;
    private final Map<Integer, Map<Integer, d>> b = new HashMap();

    private e() {
    }

    public static e a() {
        if (f6492a == null) {
            synchronized (e.class) {
                if (f6492a == null) {
                    f6492a = new e();
                }
            }
        }
        return f6492a;
    }

    private int a(byte[] bArr) {
        int i = -1;
        try {
            com.huawei.maps.offlinedata.service.device.tlvtools.d a2 = com.huawei.maps.offlinedata.service.device.tlvtools.e.a(bArr);
            if (a2 == null) {
                com.huawei.maps.offlinedata.utils.g.c("One2OneReceiversManager", "parseMsgId TlvByteFather is null");
            }
            int i2 = -1;
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : a2.a()) {
                try {
                    if (fVar.a() == 19) {
                        i2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(fVar.b(), -1);
                        com.huawei.maps.offlinedata.utils.g.a("One2OneReceiversManager", "parseMsgId msgId:" + i2);
                    }
                } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g e) {
                    e = e;
                    i = i2;
                    com.huawei.maps.offlinedata.utils.g.d("One2OneReceiversManager", "catch TlvException:" + e.getMessage());
                    return i;
                }
            }
            return i2;
        } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g e2) {
            e = e2;
        }
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        int a2 = a(bArr);
        if (this.b.get(Integer.valueOf(i)) == null || this.b.get(Integer.valueOf(i)).get(Integer.valueOf(a2)) == null) {
            com.huawei.maps.offlinedata.utils.g.d("One2OneReceiversManager", "receiver is not in map.");
        } else {
            this.b.get(Integer.valueOf(i)).get(Integer.valueOf(a2)).a(i, bArr);
            a(i, a2);
        }
    }

    public boolean a(d dVar, int i, int i2) {
        if (dVar == null) {
            return false;
        }
        if (this.b.get(Integer.valueOf(i)) == null) {
            this.b.put(Integer.valueOf(i), new HashMap());
        }
        this.b.get(Integer.valueOf(i)).put(Integer.valueOf(i2), dVar);
        return true;
    }

    public void a(int i, int i2) {
        if (this.b.get(Integer.valueOf(i)) != null) {
            this.b.get(Integer.valueOf(i)).remove(Integer.valueOf(i2));
        }
    }
}
