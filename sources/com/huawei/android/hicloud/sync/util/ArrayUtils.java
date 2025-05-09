package com.huawei.android.hicloud.sync.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class ArrayUtils {
    public static <T> List<T> arrayToList(T[] tArr) {
        return isEmpty(tArr) ? new ArrayList(0) : Arrays.asList(tArr);
    }

    public static String[] clone(String[] strArr) {
        return isEmpty(strArr) ? new String[0] : (String[]) strArr.clone();
    }

    public static boolean contains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static <T> T get(List<T> list, int i, T t) {
        T t2;
        return (!isEmpty(list) && i < list.size() && i >= 0 && (t2 = list.get(i)) != null) ? t2 : t;
    }

    public static <T> boolean isAbsoluteMatch(List<T> list, List<T> list2) {
        if (size(list) != size(list2)) {
            return false;
        }
        if (isEmpty(list) && isEmpty(list2)) {
            return true;
        }
        return list != null && list.containsAll(list2) && list2.containsAll(list);
    }

    public static <T> boolean isAlmostMatch(List<T> list, List<T> list2) {
        if (isEmpty(list)) {
            return isEmpty(list2);
        }
        if (isEmpty(list2)) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (list2.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static <T> boolean isMatch(List<T> list, List<T> list2) {
        if (isEmpty(list) || isEmpty(list2)) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (list2.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static int[] range(int i) {
        return range(0, i, 1);
    }

    public static <T> int size(List<T> list) {
        if (isEmpty(list)) {
            return 0;
        }
        return list.size();
    }

    public static <T> List<List<T>> splitList(List<T> list, int i) {
        if (isEmpty(list) || i <= 0) {
            return new ArrayList(0);
        }
        int size = list.size();
        int i2 = size / i;
        ArrayList arrayList = new ArrayList(((size + i) - 1) / i);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * i;
            arrayList.add(list.subList(i4, i4 + i));
        }
        if (size % i > 0) {
            arrayList.add(list.subList(i2 * i, size));
        }
        return arrayList;
    }

    public static <T> List<T> subList(List<T> list, int i) {
        return (size(list) <= i || i <= 0) ? list : list.subList(0, i);
    }

    public static boolean isEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public static int[] range(int i, int i2) {
        return range(i, i2, 1);
    }

    public static boolean isEmpty(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static <T> int size(T[] tArr) {
        if (isEmpty(tArr)) {
            return 0;
        }
        return tArr.length;
    }

    public static byte[] clone(byte[] bArr) {
        return isEmpty(bArr) ? new byte[0] : (byte[]) bArr.clone();
    }

    public static <T> T get(T[] tArr, int i, T t) {
        T t2;
        return (!isEmpty(tArr) && i < tArr.length && i >= 0 && (t2 = tArr[i]) != null) ? t2 : t;
    }

    public static boolean isEmpty(long[] jArr) {
        return jArr == null || jArr.length == 0;
    }

    public static boolean isMatch(int[] iArr, int[] iArr2) {
        if (!isEmpty(iArr) && !isEmpty(iArr2)) {
            for (int i : iArr) {
                for (int i2 : iArr2) {
                    if (i == i2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int[] range(int i, int i2, int i3) {
        if (i3 <= 0) {
            return new int[0];
        }
        int ceil = (int) Math.ceil((i2 - i) / i3);
        if (ceil <= 0) {
            return new int[0];
        }
        int[] iArr = new int[ceil];
        iArr[0] = i;
        for (int i4 = 1; i4 < ceil; i4++) {
            iArr[i4] = iArr[i4 - 1] + i3;
        }
        return iArr;
    }

    public static boolean isEmpty(float[] fArr) {
        return fArr == null || fArr.length == 0;
    }

    public static boolean isEmpty(double[] dArr) {
        return dArr == null || dArr.length == 0;
    }

    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
