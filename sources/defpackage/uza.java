package defpackage;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.network.Exchange;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uza extends uyw {
    static final Logger b = vha.a((Class<?>) uza.class);

    /* renamed from: a, reason: collision with root package name */
    private volatile ScheduledFuture<?> f17609a;
    private final long e;
    private final ScheduledExecutorService f;

    public uza(Exchange exchange, ScheduledExecutorService scheduledExecutorService, long j) {
        super(exchange);
        this.f = scheduledExecutorService;
        this.e = j;
        b.debug("no-response observer");
    }

    @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
    public void onSent(boolean z) {
        b.debug("no-response sent");
        if (z) {
            return;
        }
        this.f17609a = this.f.schedule(new Runnable() { // from class: uza.5
            @Override // java.lang.Runnable
            public void run() {
                uza.this.c.d(new Runnable() { // from class: uza.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        uza.b.debug("no-response-timeout");
                        uza.this.c.p().setTimedOut(true);
                    }
                });
            }
        }, this.e, TimeUnit.MILLISECONDS);
    }

    @Override // defpackage.uyw
    protected void b(String str) {
        ScheduledFuture<?> scheduledFuture = this.f17609a;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        super.b(str);
    }
}
