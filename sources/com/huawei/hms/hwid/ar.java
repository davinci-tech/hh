package com.huawei.hms.hwid;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class ar {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<Integer> f4645a;

    static {
        HashSet hashSet = new HashSet();
        f4645a = hashSet;
        hashSet.add(0);
    }

    public static int a(int i) {
        return f4645a.contains(Integer.valueOf(i)) ? 0 : 1;
    }
}
