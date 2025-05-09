package com.huawei.hms.network.embedded;

import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.inner.DomainManager;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;

/* loaded from: classes9.dex */
public class a0 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5148a = "CacheManager";
    public static final int c = 300000;
    public static final d0<m0> b = new d0<>();
    public static long d = 0;

    public static void e() {
        Map<String, m0> c2 = c();
        if (c2 == null || c2.isEmpty()) {
            return;
        }
        Logger.v(f5148a, "Totol Cache Num: %s", Integer.valueOf(c2.size()));
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - d < 300000 || !NetworkUtil.isForeground()) {
            return;
        }
        Logger.v(f5148a, "updateAllCache updateAll all");
        d = currentTimeMillis;
        ListIterator listIterator = new ArrayList(c2.entrySet()).listIterator(c2.size());
        int i = 0;
        while (listIterator.hasPrevious()) {
            String str = (String) ((Map.Entry) listIterator.previous()).getKey();
            if (i >= 5) {
                c(str);
            } else if (t.m().f(str) != 1) {
                g0.a(str, "dns_network_change", 2);
                i++;
            }
        }
    }

    public static void d() {
        Logger.v(f5148a, "enter loadFileCacheToMemeory");
        try {
            for (Map.Entry<String, m0> entry : c0.b().entrySet()) {
                Logger.v(f5148a, "Load a record from File, host:%s, value:%s", entry.getKey(), entry.getValue());
                b.a(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            Logger.w(f5148a, "loadFileCacheToMemeory error:" + e.getMessage());
        }
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str) && b.a(str)) {
            c0.b(str);
        }
    }

    public static Map<String, m0> c() {
        return b.b();
    }

    public static void b() {
        b.a();
        c0.e();
    }

    public static m0 b(String str) {
        return c0.a(str);
    }

    public static void a(String str, m0 m0Var) {
        if (TextUtils.isEmpty(str) || y.b(m0Var)) {
            Logger.w(f5148a, "saveValidIP: host or dnsResult is null");
            return;
        }
        if (DomainManager.getInstance().isExcludePrefetch(str)) {
            Logger.v(f5148a, "exclude save db");
            return;
        }
        d0<m0> d0Var = b;
        m0 b2 = d0Var.b(str);
        if (m0Var.a(b2) || b2.g() != 0) {
            Logger.v(f5148a, "saveValidIP，host: %s, value: %s", str, m0Var);
            d0Var.a(str, m0Var);
            c0.b(str, m0Var);
        }
    }

    public static void a(NetworkInfo networkInfo) {
        int c2 = w.c();
        if (c2 == 0) {
            a();
            return;
        }
        if (c2 != 1) {
            if (c2 != 2) {
                Logger.w(f5148a, "Unkown netowrk change strategy, used to update all cache, strategy:" + w.c());
            }
        } else if (networkInfo == null || NetworkUtil.getNetworkType(networkInfo) != 1) {
            return;
        }
        e();
    }

    public static void a() {
        b.a();
    }

    public static m0 a(String str) {
        m0 b2 = b.b(str);
        if (b2 == null) {
            return null;
        }
        Logger.v(f5148a, "Memory Cache host:" + str);
        b2.a(1);
        int g = b2.g();
        if (g == 0) {
            return b2;
        }
        if (g != 1) {
            c(str);
            return null;
        }
        g0.a(str, "dns_lazy_update", t.m().k() ? 3 : 2);
        return b2;
    }
}
