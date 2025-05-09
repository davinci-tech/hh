package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* loaded from: classes9.dex */
public class y {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5576a = "DnsUtil";
    public static final int b = 10000;
    public static final int c = 15000;
    public static int d = 10000;
    public static final int e = 4;
    public static final int f = 16;
    public static ExecutorService g = ExecutorsUtils.newCachedThreadPool("Dns_executor");

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5577a = "trigger_type";
        public static final String b = "request_domain";
        public static final String c = "error_code";
        public static final int d = 10020000;
        public static final int e = 10020001;
        public static final String f = "dns_subtype";
        public static final String g = "site_detect";
        public static final String h = "dns_change";
        public static final String i = "protocol_impl";
        public static final String j = "okhttp";
        public static final String k = "tcpconn_time";
        public static final String l = "ssl_time";
        public static final String m = "connect_time";
        public static final String n = "dns_request";
        public static final String o = "site_detect_threshold";
        public static final String p = "server_ip";
    }

    public static void c(m0 m0Var) {
        List<InetAddress> a2;
        if (b(m0Var) || (a2 = a(m0Var.d())) == null || a2.size() <= 0) {
            return;
        }
        boolean z = false;
        boolean z2 = false;
        for (InetAddress inetAddress : a2) {
            if (inetAddress.getAddress().length == 4) {
                z = true;
            } else if (inetAddress.getAddress().length == 16) {
                z2 = true;
            }
        }
        a(m0Var, z, z2);
    }

    public static boolean b(m0 m0Var) {
        return m0Var == null || m0Var.j();
    }

    public static <T> void b(List<T> list) {
        if (list == null) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(list.size());
        linkedHashSet.addAll(list);
        list.clear();
        list.addAll(linkedHashSet);
    }

    public static ExecutorService b() {
        return g;
    }

    public static List<InetAddress> b(String str) throws UnknownHostException {
        return r0.a().a(str, h0.b);
    }

    public static boolean a(InetAddress inetAddress) {
        return inetAddress instanceof Inet4Address;
    }

    public static boolean a(String str) {
        try {
            return InetAddress.getByName(str) instanceof Inet4Address;
        } catch (SecurityException | UnknownHostException e2) {
            Logger.d(f5576a, "isIPv4 failed,exception message is:" + e2.getMessage());
            return false;
        }
    }

    public static void a(m0 m0Var, boolean z, boolean z2) {
        String str;
        if (m0Var == null) {
            Logger.w(f5576a, "dnsResult is null.");
            return;
        }
        if (z && z2) {
            str = "ALL";
        } else if (z) {
            str = "A";
        } else {
            if (!z2) {
                Logger.w(f5576a, "invalid ip type");
                return;
            }
            str = "AAAA";
        }
        m0Var.c(str);
    }

    public static void a(int i) {
        if (i <= 0 || i > 15000) {
            return;
        }
        d = i;
    }

    public static List<InetAddress> a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            try {
                arrayList.add(InetAddress.getByName(str));
            } catch (UnknownHostException unused) {
                Logger.w(f5576a, "convertAddress failed, ip:" + str);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x003d, code lost:
    
        if (r6.equals(com.huawei.hms.network.embedded.w.m) == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.net.InetAddress> a(java.lang.String r5, java.lang.String r6) throws java.net.UnknownHostException {
        /*
            com.huawei.hms.network.embedded.t r0 = com.huawei.hms.network.embedded.t.m()
            r1 = 0
            r0.d(r1)
            r6.hashCode()
            int r0 = r6.hashCode()
            r2 = -1387696287(0xffffffffad496f61, float:-1.1450258E-11)
            r3 = 2
            r4 = 1
            if (r0 == r2) goto L37
            r1 = 1001410588(0x3bb0501c, float:0.0053806435)
            if (r0 == r1) goto L2c
            r1 = 1965413854(0x7525d5de, float:2.1022137E32)
            if (r0 == r1) goto L21
            goto L3f
        L21:
            java.lang.String r0 = "LocalDns"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L2a
            goto L3f
        L2a:
            r1 = r3
            goto L40
        L2c:
            java.lang.String r0 = "DNKeeper"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L35
            goto L3f
        L35:
            r1 = r4
            goto L40
        L37:
            java.lang.String r0 = "HttpDns"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L40
        L3f:
            r1 = -1
        L40:
            if (r1 == 0) goto L67
            if (r1 == r4) goto L59
            if (r1 == r3) goto L4b
            java.util.List r5 = java.util.Collections.emptyList()
            return r5
        L4b:
            com.huawei.hms.network.embedded.t r6 = com.huawei.hms.network.embedded.t.m()
            r6.f(r3)
            com.huawei.hms.network.embedded.r0 r6 = com.huawei.hms.network.embedded.r0.a()
            com.huawei.hms.network.embedded.h0 r0 = com.huawei.hms.network.embedded.h0.b
            goto L75
        L59:
            com.huawei.hms.network.embedded.t r6 = com.huawei.hms.network.embedded.t.m()
            r6.f(r4)
            com.huawei.hms.network.embedded.r0 r6 = com.huawei.hms.network.embedded.r0.a()
            com.huawei.hms.network.embedded.h0 r0 = com.huawei.hms.network.embedded.h0.c
            goto L75
        L67:
            com.huawei.hms.network.embedded.t r6 = com.huawei.hms.network.embedded.t.m()
            r0 = 3
            r6.f(r0)
            com.huawei.hms.network.embedded.r0 r6 = com.huawei.hms.network.embedded.r0.a()
            com.huawei.hms.network.embedded.h0 r0 = com.huawei.hms.network.embedded.h0.d
        L75:
            java.util.List r5 = r6.a(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.y.a(java.lang.String, java.lang.String):java.util.List");
    }

    public static List<String> a(m0 m0Var) {
        List<String> d2 = m0Var.d();
        b(d2);
        return d2;
    }

    public static m0 a(InetAddress[] inetAddressArr) {
        boolean z;
        m0 m0Var = new m0();
        boolean z2 = false;
        if (inetAddressArr == null || inetAddressArr.length <= 0) {
            z = false;
        } else {
            z = false;
            boolean z3 = false;
            for (InetAddress inetAddress : inetAddressArr) {
                if (inetAddress.getAddress().length == 4) {
                    z3 = true;
                } else if (inetAddress.getAddress().length == 16) {
                    z = true;
                }
                m0Var.a(inetAddress.getHostAddress());
            }
            z2 = z3;
        }
        a(m0Var, z2, z);
        return m0Var;
    }

    public static HianalyticsBaseData a(n5 n5Var, long j) {
        if (n5Var != null) {
            return new a(n5Var, j);
        }
        Logger.w(f5576a, "Site detect metrics is null");
        return null;
    }

    public static int a() {
        return d;
    }

    public static class a extends HianalyticsBaseData {
        public a(n5 n5Var, long j) {
            put("sdk_version", "8.0.1.307");
            put("dns_subtype", b.g);
            put("trigger_type", b.h);
            put("request_domain", n5Var.d());
            put("error_code", n5Var.h() ? 10020000L : 10020001L);
            put("protocol_impl", b.j);
            put("tcpconn_time", n5Var.f() - n5Var.a());
            put("ssl_time", n5Var.g());
            put("connect_time", n5Var.b());
            put(b.o, j);
            put("server_ip", n5Var.e());
        }
    }
}
