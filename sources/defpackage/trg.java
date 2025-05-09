package defpackage;

import java.util.Collection;

/* loaded from: classes7.dex */
public class trg {
    public static boolean a(Collection<?> collection, int i) {
        return d(collection) || i < 0 || i >= collection.size();
    }

    public static boolean d(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
