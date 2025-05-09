package defpackage;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.util.CounterStatisticManager;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzm extends CounterStatisticManager {
    private static final Logger b = vha.a((Class<?>) uzm.class);

    /* renamed from: a, reason: collision with root package name */
    private final vca f17619a;
    private final vca c;
    private final vca d;
    private final vca e;

    public uzm(String str, int i, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        super(str, i, timeUnit, scheduledExecutorService);
        this.f17619a = new vca("sent-requests", this.align);
        this.d = new vca("queue-requests", this.align);
        this.c = new vca("dequeue-requests", this.align);
        this.e = new vca("recv-responses", this.align);
        a();
    }

    private void a() {
        add(this.f17619a);
        add(this.d);
        add(this.e);
    }

    @Override // org.eclipse.californium.elements.util.CounterStatisticManager
    public boolean isEnabled() {
        return b.isInfoEnabled();
    }

    @Override // org.eclipse.californium.elements.util.CounterStatisticManager
    public void dump() {
        try {
            if (isEnabled()) {
                Logger logger = b;
                if (logger.isDebugEnabled() && (this.e.d() || this.f17619a.d() || this.d.d())) {
                    String a2 = vcb.a();
                    String str = "   " + this.tag;
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.tag);
                    sb.append("congestion statistic:");
                    sb.append(a2);
                    sb.append(str);
                    sb.append(this.f17619a);
                    sb.append(a2);
                    sb.append(str);
                    sb.append(this.d);
                    sb.append(a2);
                    sb.append(str);
                    sb.append(this.c);
                    sb.append(a2);
                    sb.append(str);
                    sb.append(this.e);
                    sb.append(a2);
                    logger.debug("{}", sb);
                }
                transferCounter();
            }
        } catch (Throwable th) {
            b.error("{}", this.tag, th);
        }
    }

    public void b() {
        this.f17619a.a();
    }

    public void c() {
        this.d.a();
    }

    public void d() {
        this.c.a();
    }

    public void b(uxr uxrVar) {
        if (uxrVar.isDuplicate()) {
            return;
        }
        this.e.a();
    }
}
