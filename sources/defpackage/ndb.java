package defpackage;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes6.dex */
public class ndb {
    public static <V> boolean a(List<V> list) {
        return list == null || list.size() == 0;
    }

    public static <V> boolean e(Collection<V> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean a(ConcurrentMap concurrentMap) {
        return concurrentMap == null || concurrentMap.size() == 0;
    }
}
