package com.huawei.hms.common.utils;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class PickerHiAnalyticsUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<Integer> f4477a;

    static {
        HashSet hashSet = new HashSet();
        f4477a = hashSet;
        hashSet.add(0);
    }

    public static int getHiAnalyticsStatus(int i) {
        return f4477a.contains(Integer.valueOf(i)) ? 0 : 1;
    }
}
