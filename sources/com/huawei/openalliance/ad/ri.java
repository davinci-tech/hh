package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.VastVersion;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ri {

    /* renamed from: a, reason: collision with root package name */
    private static VastVersion f7506a = VastVersion.VAST_30;
    private static Map<VastVersion, rc> b = new HashMap();
    private static Map<VastVersion, ra> c = new HashMap();
    private static Map<VastVersion, rb> d = new HashMap();

    public static ra c() {
        return c.get(f7506a);
    }

    public static rb b() {
        return d.get(f7506a);
    }

    public static void a(VastVersion vastVersion) {
        f7506a = vastVersion;
    }

    public static rc a() {
        return b.get(f7506a);
    }

    static {
        b.put(VastVersion.VAST_20, new rj());
        b.put(VastVersion.VAST_30, new rk());
        c.put(VastVersion.VAST_20, new rd());
        c.put(VastVersion.VAST_30, new re());
        d.put(VastVersion.VAST_20, new rf());
        d.put(VastVersion.VAST_30, new rg());
    }
}
