package com.huawei.ads.fund.util;

import java.util.Collection;

/* loaded from: classes2.dex */
public abstract class ListUtil {
    public static <T> boolean isEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static boolean isEmpty(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
}
