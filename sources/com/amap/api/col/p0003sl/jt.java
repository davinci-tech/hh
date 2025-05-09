package com.amap.api.col.p0003sl;

import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.maps.AMapException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class jt {

    /* renamed from: a, reason: collision with root package name */
    public static int f1230a = 0;
    public static String b = "";
    public static HashMap<String, String> c;
    public static HashMap<String, String> d;
    public static HashMap<String, String> e;
    private static jt f;

    public interface a {
        URLConnection a();
    }

    public jt() {
        ho.e();
    }

    public static jt a() {
        if (f == null) {
            f = new jt();
        }
        return f;
    }

    @Deprecated
    private static kb d(ka kaVar, boolean z) throws hm {
        d(kaVar);
        kaVar.setHttpProtocol(z ? ka.c.HTTPS : ka.c.HTTP);
        kb kbVar = null;
        long j = 0;
        boolean z2 = false;
        if (b(kaVar)) {
            boolean c2 = c(kaVar);
            try {
                j = SystemClock.elapsedRealtime();
                kbVar = a(kaVar, a(kaVar, c2), c(kaVar, c2));
            } catch (hm e2) {
                if (e2.f() == 21 && kaVar.getDegradeAbility() == ka.a.INTERRUPT_IO) {
                    throw e2;
                }
                if (!c2) {
                    throw e2;
                }
                z2 = true;
            }
        }
        return (kbVar == null || kbVar.f1250a == null || kbVar.f1250a.length <= 0) ? a(kaVar, b(kaVar, z2), a(kaVar, j)) : kbVar;
    }

    public static kb a(ka kaVar) throws hm {
        return d(kaVar, kaVar.isHttps());
    }

    private static kb a(ka kaVar, ka.b bVar, int i) throws hm {
        try {
            d(kaVar);
            kaVar.setDegradeType(bVar);
            kaVar.setReal_max_timeout(i);
            return new jx().c(kaVar);
        } catch (hm e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new hm(AMapException.ERROR_UNKNOWN);
        }
    }

    protected static ka.b a(ka kaVar, boolean z) {
        if (kaVar.getDegradeAbility() == ka.a.FIX) {
            return ka.b.FIX_NONDEGRADE;
        }
        if (kaVar.getDegradeAbility() == ka.a.SINGLE) {
            return ka.b.NEVER_GRADE;
        }
        return z ? ka.b.FIRST_NONDEGRADE : ka.b.NEVER_GRADE;
    }

    protected static ka.b b(ka kaVar, boolean z) {
        return kaVar.getDegradeAbility() == ka.a.FIX ? z ? ka.b.FIX_DEGRADE_BYERROR : ka.b.FIX_DEGRADE_ONLY : z ? ka.b.DEGRADE_BYERROR : ka.b.DEGRADE_ONLY;
    }

    protected static boolean b(ka kaVar) throws hm {
        d(kaVar);
        try {
            String ipv6url = kaVar.getIPV6URL();
            if (TextUtils.isEmpty(ipv6url)) {
                return false;
            }
            String host = new URL(ipv6url).getHost();
            if (!TextUtils.isEmpty(kaVar.getIPDNSName())) {
                host = kaVar.getIPDNSName();
            }
            return ho.d(host);
        } catch (Throwable unused) {
            return true;
        }
    }

    protected static boolean c(ka kaVar) throws hm {
        d(kaVar);
        if (!b(kaVar)) {
            return true;
        }
        if (kaVar.getURL().equals(kaVar.getIPV6URL()) || kaVar.getDegradeAbility() == ka.a.SINGLE) {
            return false;
        }
        return ho.h;
    }

    protected static int c(ka kaVar, boolean z) {
        try {
            d(kaVar);
            int conntectionTimeout = kaVar.getConntectionTimeout();
            int i = ho.e;
            if (kaVar.getDegradeAbility() != ka.a.FIX) {
                if (kaVar.getDegradeAbility() != ka.a.SINGLE && conntectionTimeout >= i && z) {
                    return i;
                }
            }
            return conntectionTimeout;
        } catch (Throwable unused) {
            return 5000;
        }
    }

    protected static int a(ka kaVar, long j) {
        try {
            d(kaVar);
            long j2 = 0;
            if (j != 0) {
                j2 = SystemClock.elapsedRealtime() - j;
            }
            int conntectionTimeout = kaVar.getConntectionTimeout();
            if (kaVar.getDegradeAbility() != ka.a.FIX && kaVar.getDegradeAbility() != ka.a.SINGLE) {
                long j3 = conntectionTimeout;
                if (j2 < j3) {
                    long j4 = j3 - j2;
                    if (j4 >= 1000) {
                        return (int) j4;
                    }
                }
                return Math.min(1000, kaVar.getConntectionTimeout());
            }
            return conntectionTimeout;
        } catch (Throwable unused) {
            return 5000;
        }
    }

    protected static void d(ka kaVar) throws hm {
        if (kaVar == null) {
            throw new hm("requeust is null");
        }
        if (kaVar.getURL() == null || "".equals(kaVar.getURL())) {
            throw new hm("request url is empty");
        }
    }
}
