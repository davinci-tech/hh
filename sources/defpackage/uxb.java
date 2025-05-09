package defpackage;

import java.util.Comparator;
import org.ahocorasick.interval.Intervalable;

/* loaded from: classes7.dex */
public class uxb implements Comparator<Intervalable> {
    @Override // java.util.Comparator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compare(Intervalable intervalable, Intervalable intervalable2) {
        int size = intervalable2.size() - intervalable.size();
        return size == 0 ? intervalable.getStart() - intervalable2.getStart() : size;
    }
}
