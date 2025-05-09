package defpackage;

import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.util.ClockUtil;

/* loaded from: classes7.dex */
public class uzq {
    protected final long b;
    protected final int c;

    public uzq(int i) {
        this(i, ClockUtil.d());
    }

    public uzq(int i, long j) {
        this.c = i;
        this.b = j;
    }

    public int c() {
        return this.c;
    }

    public boolean a(uxr uxrVar) {
        Integer t = uxrVar.getOptions().t();
        if (t == null) {
            return true;
        }
        return e(this.b, this.c, ClockUtil.d(), t.intValue());
    }

    public static boolean e(long j, int i, long j2, int i2) {
        if (i >= i2 || i2 - i >= 8388608) {
            return (i > i2 && ((long) (i - i2)) > 8388608) || j2 > j + TimeUnit.SECONDS.toNanos(128L);
        }
        return true;
    }
}
