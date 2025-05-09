package com.huawei.openalliance.ad;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ie {
    private static ie b;

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6940a = new byte[0];
    private static final byte[] c = new byte[0];
    private static Map<String, Long> d = new HashMap();
    private static boolean e = false;

    public void b(boolean z) {
        synchronized (c) {
            a(z);
        }
    }

    public void b() {
        synchronized (c) {
            d.clear();
            a(false);
        }
    }

    public void a(String str, long j) {
        synchronized (c) {
            if (d.containsKey(str)) {
                d.put(str, Long.valueOf(d.get(str).longValue() + j));
            } else {
                d.put(str, Long.valueOf(j));
            }
        }
    }

    public long a(String str) {
        synchronized (c) {
            if (d.containsKey(str)) {
                return d.get(str).longValue();
            }
            d.put(str, 0L);
            return 0L;
        }
    }

    private static ie c() {
        ie ieVar;
        synchronized (f6940a) {
            if (b == null) {
                b = new ie();
            }
            ieVar = b;
        }
        return ieVar;
    }

    public static void a(boolean z) {
        e = z;
    }

    public static ie a() {
        return c();
    }

    private ie() {
    }
}
