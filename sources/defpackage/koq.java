package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class koq {
    public static boolean b(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean c(Collection<?> collection) {
        return !b(collection);
    }

    public static boolean b(Collection<?> collection, int i) {
        return b(collection) || i < 0 || i >= collection.size();
    }

    public static boolean d(Collection<?> collection, int i) {
        return !b(collection, i);
    }

    public static boolean e(Object obj, Class cls) {
        if (cls != null && obj != null && (obj instanceof List)) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                if (cls.isInstance(it.next())) {
                }
            }
            return true;
        }
        return false;
    }

    public static <T> boolean b(T[] tArr, int i) {
        return !e(tArr, i);
    }

    public static boolean e(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static <T> boolean e(T[] tArr, int i) {
        return tArr != null && tArr.length != 0 && i >= 0 && i < tArr.length;
    }

    public static <T, V> List<T> e(List<V> list, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        if (b(list)) {
            return arrayList;
        }
        for (V v : list) {
            if (cls.isInstance(v)) {
                arrayList.add(cls.cast(v));
            }
        }
        return arrayList;
    }

    public static <E> boolean b(List<E> list, List<E> list2, boolean z) {
        if (b(list) && b(list2)) {
            return true;
        }
        if (b(list) || b(list2)) {
            return false;
        }
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        ArrayList arrayList2 = new ArrayList(list2.size());
        arrayList2.addAll(list2);
        if (arrayList.size() != arrayList2.size()) {
            return false;
        }
        if (z) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!arrayList.get(i).equals(arrayList2.get(i))) {
                    return false;
                }
            }
        } else if (!arrayList.containsAll(arrayList2)) {
            return false;
        }
        return true;
    }

    public static <E> List<E> c(List<E> list, int i) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty() && i > 0) {
            if (list.size() <= i) {
                arrayList.addAll(list);
                return arrayList;
            }
            int size = list.size() % i;
            int size2 = list.size() / i;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                arrayList.add(list.get((i3 * size2) + i2));
                if (size > 0) {
                    size--;
                    i2++;
                }
            }
        }
        return arrayList;
    }
}
