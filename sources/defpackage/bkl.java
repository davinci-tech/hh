package defpackage;

import com.huawei.devicesdk.strategy.ScanStrategy;
import health.compact.a.LogUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;

/* loaded from: classes8.dex */
public class bkl {
    private Set<ScanStrategy> b = new HashSet();
    private Timer c;
    private static final bkl e = new bkl();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f424a = new Object();

    private bkl() {
    }

    public void d(Timer timer) {
        this.c = timer;
    }

    public void e(ScanStrategy scanStrategy) {
        synchronized (f424a) {
            this.b.add(scanStrategy);
        }
    }

    public void b(ScanStrategy scanStrategy) {
        synchronized (f424a) {
            this.b.remove(scanStrategy);
        }
    }

    public void c() {
        LogUtil.c("ScanStrategyCache", "stop scan");
        Timer timer = this.c;
        if (timer != null) {
            timer.cancel();
            this.c = null;
        }
        synchronized (f424a) {
            Iterator<ScanStrategy> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().stopScan(true);
                it.remove();
            }
        }
    }

    public static bkl e() {
        return e;
    }
}
