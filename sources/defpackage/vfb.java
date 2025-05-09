package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class vfb {
    public static <T> List<T> a(List<T> list) {
        if (list == null) {
            return null;
        }
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                T t = list.get(i);
                for (int i2 = 0; i2 < i; i2++) {
                    if (list.get(i2).equals(t)) {
                        throw new IllegalArgumentException("Item " + t + "[" + i + "] is already contained at index [" + i2 + "]!");
                    }
                }
            }
        }
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> a(List<T> list, T t) {
        if (list == null) {
            throw new NullPointerException("List must not be null!");
        }
        if (t != null && !list.contains(t)) {
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> e(List<T> list, List<T> list2) {
        if (list == null) {
            throw new NullPointerException("List must not be null!");
        }
        if (list2 != null && !list2.isEmpty()) {
            for (T t : list2) {
                if (t != null && !list.contains(t)) {
                    list.add(t);
                }
            }
        }
        return list;
    }
}
