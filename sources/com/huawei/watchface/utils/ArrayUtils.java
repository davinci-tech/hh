package com.huawei.watchface.utils;

import android.text.TextUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static boolean a(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean isEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean a(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static int a(Collection<?> collection) {
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.size();
    }

    public static int b(Map<?, ?> map) {
        if (a(map)) {
            return 0;
        }
        return map.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> boolean a(T t, T... tArr) {
        if (t != 0 && !isEmpty(tArr)) {
            for (Object[] objArr : tArr) {
                if (objArr != 0 && (((objArr instanceof String) && (t instanceof String) && ((String) objArr).equalsIgnoreCase((String) t)) || objArr.equals(t))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(String[] strArr, String str) {
        if (!isEmpty(strArr) && !TextUtils.isEmpty(str)) {
            for (String str2 : strArr) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> int a(T[] tArr) {
        if (tArr != null) {
            return tArr.length;
        }
        return 0;
    }

    public static <T> T a(List<T> list, int i) {
        if (i < 0 || i >= a(list)) {
            return null;
        }
        try {
            return list.get(i);
        } catch (Exception e) {
            HwLog.e("ArrayUtils", "getItem Exception " + HwLog.printException(e));
            return null;
        }
    }

    public static boolean isSafeIndex(Collection<?> collection, int i) {
        return !isEmpty(collection) && i >= 0 && i < collection.size();
    }

    public static <T> T a(T[] tArr, int i) {
        if (i < 0 || i >= a(tArr)) {
            return null;
        }
        return tArr[i];
    }

    public static boolean b(String[] strArr, String str) {
        if (!isEmpty(strArr) && !TextUtils.isEmpty(str)) {
            for (String str2 : strArr) {
                if (str2 != null && str2.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
