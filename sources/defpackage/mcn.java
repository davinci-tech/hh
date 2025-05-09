package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes6.dex */
public class mcn<V> extends FutureTask<V> implements Comparable<Runnable> {
    public Runnable b;

    public mcn(Callable<V> callable) {
        super(callable);
    }

    public mcn(Runnable runnable, V v) {
        super(runnable, v);
        this.b = runnable;
    }

    @Override // java.lang.Comparable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(Runnable runnable) {
        Runnable runnable2 = this.b;
        mck mckVar = runnable2 instanceof mck ? (mck) runnable2 : null;
        if (mckVar != null) {
            return mckVar.compareTo(runnable);
        }
        return 0;
    }
}
