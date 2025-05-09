package defpackage;

import java.util.Comparator;
import org.ahocorasick.interval.Intervalable;

/* loaded from: classes7.dex */
public class uwz implements Comparator<Intervalable> {
    @Override // java.util.Comparator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compare(Intervalable intervalable, Intervalable intervalable2) {
        return intervalable.getStart() - intervalable2.getStart();
    }
}
