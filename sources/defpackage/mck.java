package defpackage;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes6.dex */
public class mck implements Runnable, Comparable<Runnable> {
    private static final AtomicLong c = new AtomicLong();

    /* renamed from: a, reason: collision with root package name */
    private final long f14882a = c.getAndIncrement();
    private int d;
    private final Runnable e;

    public mck(Runnable runnable, int i) {
        this.e = runnable;
        this.d = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.e.run();
    }

    @Override // java.lang.Comparable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(Runnable runnable) {
        mck mckVar;
        if (runnable instanceof mck) {
            mckVar = (mck) runnable;
        } else {
            if (runnable instanceof mcn) {
                mcn mcnVar = (mcn) runnable;
                if (mcnVar.b instanceof mck) {
                    mckVar = (mck) mcnVar.b;
                }
            }
            mckVar = null;
        }
        if (mckVar != null) {
            int i = this.d;
            int i2 = mckVar.d;
            if (i != i2) {
                return i2 - i;
            }
            if (mckVar.e != this.e) {
                return this.f14882a < mckVar.f14882a ? -1 : 1;
            }
        }
        return 0;
    }
}
