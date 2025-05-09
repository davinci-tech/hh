package com.huawei.hwcloudjs.e.a;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6218a = "CacheManager";
    private static final c b = new c();
    private static HashMap<String, a> c = new b(100);
    private long d = 10000000;
    private long e = 0;

    public boolean e(String str) {
        boolean containsKey;
        synchronized (this) {
            containsKey = c.containsKey(str);
        }
        return containsKey;
    }

    public long e() {
        return this.d;
    }

    public a d(String str) {
        StringBuilder sb;
        String authority;
        a c2;
        synchronized (this) {
            a c3 = c(str);
            if (c3 == null || !(c3 instanceof a)) {
                c3 = null;
            }
            if (c3 != null) {
                com.huawei.hwcloudjs.f.d.c("CacheManager", "getCacheInfo success!", true);
                return c3;
            }
            try {
                URL url = new URL(str);
                if (!url.getPath().isEmpty() && !url.getPath().equalsIgnoreCase("/")) {
                    sb = new StringBuilder();
                    sb.append(url.getProtocol());
                    sb.append("://");
                    sb.append(url.getAuthority());
                    authority = url.getPath();
                    sb.append(authority);
                    c2 = c(sb.toString());
                    if (c2 == null && (c2 instanceof a)) {
                        c3 = c2;
                    } else {
                        com.huawei.hwcloudjs.f.d.c("CacheManager", "getCacheInfo(urlPath) is not Cache type", true);
                    }
                    return c3;
                }
                sb = new StringBuilder();
                sb.append(url.getProtocol());
                sb.append("://");
                authority = url.getAuthority();
                sb.append(authority);
                c2 = c(sb.toString());
                if (c2 == null) {
                }
                com.huawei.hwcloudjs.f.d.c("CacheManager", "getCacheInfo(urlPath) is not Cache type", true);
                return c3;
            } catch (MalformedURLException unused) {
                com.huawei.hwcloudjs.f.d.b("CacheManager", "MalformedURLException", true);
                return null;
            }
        }
    }

    public a c(String str) {
        synchronized (this) {
            if (!e(str)) {
                return null;
            }
            a b2 = b(str);
            if (b2 != null && b2.b() == 0) {
                this.e -= b2.d();
                c.remove(str);
            }
            return b2;
        }
    }

    public long c() {
        long j;
        synchronized (this) {
            j = this.e;
        }
        return j;
    }

    public a b(String str) {
        synchronized (this) {
            a aVar = c.get(str);
            if (aVar != null) {
                if (aVar instanceof a) {
                    return aVar;
                }
            }
            return null;
        }
    }

    public int b() {
        int size;
        synchronized (this) {
            size = c.size();
        }
        return size;
    }

    public void a(String str, a aVar) {
        synchronized (this) {
            if (c.get(str) != null) {
                this.e -= r0.d();
            }
            c.put(str, aVar);
            long d = this.e + aVar.d();
            this.e = d;
            if (d > this.d) {
                Iterator<Map.Entry<String, a>> it = c.entrySet().iterator();
                while (it.hasNext()) {
                    a(it.next().getKey());
                    if (this.e < this.d / 2) {
                        return;
                    }
                }
            }
        }
    }

    public void a(String str) {
        synchronized (this) {
            if (b(str) != null) {
                this.e -= r0.d();
            }
            c.remove(str);
        }
    }

    public void a(long j) {
        this.d = j;
    }

    public void a() {
        synchronized (this) {
            c.clear();
            this.e = 0L;
        }
    }

    public static c d() {
        return b;
    }

    private c() {
    }
}
