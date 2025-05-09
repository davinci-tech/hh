package com.huawei.hms.kit.awareness.barrier.internal.f;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class c {
    public static <T> boolean a(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static boolean a(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static boolean a(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static <T, K> boolean a(Map<T, K> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean a(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean a(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().length() == 0;
        }
        if (obj instanceof List) {
            return ((List) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }
}
