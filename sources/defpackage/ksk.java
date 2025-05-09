package defpackage;

import java.util.Collection;

/* loaded from: classes5.dex */
public class ksk {
    public static Boolean b(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static Boolean d(Collection collection) {
        return Boolean.valueOf(!b(collection).booleanValue());
    }
}
