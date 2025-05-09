package defpackage;

import java.util.Comparator;

/* loaded from: classes9.dex */
public final /* synthetic */ class oiy implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Long.compare(((Long) obj).longValue(), ((Long) obj2).longValue());
        return compare;
    }
}
