package com.huawei.hms.hatool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class s {
    static Map<String, l1> b = new HashMap();
    private static s c;

    /* renamed from: a, reason: collision with root package name */
    private g1 f4615a = new g1();

    public g1 b() {
        return this.f4615a;
    }

    public void a(String str, l1 l1Var) {
        b.put(str, l1Var);
    }

    public Set<String> a() {
        return b.keySet();
    }

    public l1 a(String str) {
        return b.get(str);
    }

    private static void d() {
        synchronized (s.class) {
            if (c == null) {
                c = new s();
            }
        }
    }

    public static s c() {
        if (c == null) {
            d();
        }
        return c;
    }

    private s() {
    }
}
