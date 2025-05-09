package defpackage;

import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.deduplication.Deduplicator;

/* loaded from: classes7.dex */
public class uyo implements Deduplicator {
    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void clear() {
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public Exchange find(uyf uyfVar) {
        return null;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public Exchange findPrevious(uyf uyfVar, Exchange exchange) {
        return null;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public boolean isEmpty() {
        return true;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public boolean replacePrevious(uyf uyfVar, Exchange exchange, Exchange exchange2) {
        return true;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void setExecutor(ScheduledExecutorService scheduledExecutorService) {
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public int size() {
        return 0;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void start() {
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void stop() {
    }
}
