package com.huawei.watchface.videoedit.utils;

import java.util.List;

/* loaded from: classes9.dex */
public class ListUtil {
    private ListUtil() {
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static int size(List<?> list) {
        if (isEmpty(list)) {
            return 0;
        }
        return list.size();
    }

    public static <T> boolean contains(List<T> list, T t) {
        if (isEmpty(list)) {
            return false;
        }
        return list.contains(t);
    }

    public static <T> boolean equals(List<T> list, List<T> list2) {
        if (size(list) != size(list2)) {
            return false;
        }
        int size = size(list);
        for (int i = 0; i < size; i++) {
            if (!list.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static <T> int indexOf(List<T> list, T t) {
        if (isEmpty(list)) {
            return -1;
        }
        return list.indexOf(t);
    }

    public static <T> void clear(List<T> list) {
        if (isEmpty(list)) {
            return;
        }
        list.clear();
    }
}
