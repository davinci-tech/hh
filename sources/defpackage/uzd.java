package defpackage;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.network.Exchange;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzd extends uyw {

    /* renamed from: a, reason: collision with root package name */
    static final Logger f17612a = vha.a((Class<?>) uzd.class);
    private final long b;
    private volatile ScheduledFuture<?> e;
    private final ScheduledExecutorService i;

    public uzd(Exchange exchange, ScheduledExecutorService scheduledExecutorService, long j) {
        super(exchange);
        this.i = scheduledExecutorService;
        this.b = j;
    }

    @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
    public void onSent(boolean z) {
        if (z) {
            return;
        }
        this.e = this.i.schedule(new Runnable() { // from class: uzd.2
            @Override // java.lang.Runnable
            public void run() {
                uzd.this.c.d(new Runnable() { // from class: uzd.2.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (uzd.this.c.t() == null) {
                            uzd.this.c.p().setCanceled(true);
                        } else {
                            uzd.this.c.af();
                        }
                    }
                });
            }
        }, this.b, TimeUnit.MILLISECONDS);
    }

    @Override // defpackage.uyw
    protected void b(String str) {
        ScheduledFuture<?> scheduledFuture = this.e;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        super.b(str);
    }
}
