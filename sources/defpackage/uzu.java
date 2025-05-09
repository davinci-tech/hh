package defpackage;

import java.util.List;
import org.eclipse.californium.core.observe.ObserveRelation;

/* loaded from: classes7.dex */
public class uzu {

    /* renamed from: a, reason: collision with root package name */
    private final List<ObserveRelation> f17624a;

    public void e(ObserveRelation observeRelation) {
        this.f17624a.remove(observeRelation);
    }

    public void c() {
        for (ObserveRelation observeRelation : this.f17624a) {
            if (observeRelation.o()) {
                observeRelation.b();
            }
        }
    }

    public boolean a() {
        return this.f17624a.isEmpty();
    }
}
