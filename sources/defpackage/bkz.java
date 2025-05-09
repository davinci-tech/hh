package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bkz {
    public static boolean e(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean a(Object obj, Class cls) {
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
}
