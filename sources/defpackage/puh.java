package defpackage;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class puh {
    public static long a(List<pus> list) {
        Iterator<pus> it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().b();
        }
        return j;
    }

    public static pus e(List<pus> list) {
        for (pus pusVar : list) {
            if (!pusVar.e()) {
                return pusVar;
            }
        }
        return null;
    }
}
