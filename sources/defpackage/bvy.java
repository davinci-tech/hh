package defpackage;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes3.dex */
public class bvy implements Comparator<gka>, Serializable {
    private static final long serialVersionUID = -4531604421710697216L;
    private final boolean e;

    public bvy(boolean z) {
        this.e = z;
    }

    @Override // java.util.Comparator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compare(gka gkaVar, gka gkaVar2) {
        return this.e ? c(gkaVar2, gkaVar) : c(gkaVar, gkaVar2);
    }

    private int c(gka gkaVar, gka gkaVar2) {
        if (gkaVar.k() != gkaVar2.k()) {
            return Integer.compare(gkaVar.k(), gkaVar2.k());
        }
        if (gkaVar.k() == 256) {
            return this.e ? -1 : 1;
        }
        if (gkaVar.k() == 512) {
            return 1;
        }
        return Long.compare(gkaVar2.h(), gkaVar.h());
    }
}
