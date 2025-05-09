package com.huawei.haf.common.utils;

import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static boolean b(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean d(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean e(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean c(Collection<?> collection, int i) {
        return d(collection) || i < 0 || i >= collection.size();
    }
}
