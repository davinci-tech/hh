package com.huawei.updatesdk.b.c;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Class<? extends c>> f10829a;
    private static c b;

    public static c a() {
        synchronized (b.class) {
            c cVar = b;
            if (cVar != null) {
                return cVar;
            }
            com.huawei.updatesdk.a.a.a.b("DeviceImplFactory", "deviceType: " + com.huawei.updatesdk.a.a.d.i.c.e());
            Class<? extends c> cls = f10829a.get(Integer.valueOf(com.huawei.updatesdk.a.a.d.i.c.e()));
            if (cls == null) {
                d dVar = new d();
                b = dVar;
                return dVar;
            }
            try {
                b = cls.newInstance();
            } catch (Throwable unused) {
                b = new d();
                com.huawei.updatesdk.a.a.a.a("DeviceImplFactory", "createDeviceInfo error and create default phone deviceinfo");
            }
            return b;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f10829a = hashMap;
        hashMap.put(3, a.class);
        hashMap.put(1, e.class);
        hashMap.put(2, f.class);
        hashMap.put(0, d.class);
        hashMap.put(4, d.class);
        hashMap.put(7, a.class);
    }
}
