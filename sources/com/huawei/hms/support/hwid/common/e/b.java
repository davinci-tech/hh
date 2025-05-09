package com.huawei.hms.support.hwid.common.e;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<Integer> f6002a;

    static {
        HashSet hashSet = new HashSet();
        f6002a = hashSet;
        hashSet.add(0);
    }

    public static int a(int i) {
        return f6002a.contains(Integer.valueOf(i)) ? 0 : 1;
    }
}
