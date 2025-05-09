package org.eclipse.californium.core.network.stack;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.CongestionControlLayer;

/* loaded from: classes7.dex */
public abstract class RemoteEndpoint {
    private static final int RTOARRAYSIZE = 3;
    private int currentOverallIndex;
    private volatile long currentRTO;
    private final Set<Exchange> inFlight;
    private boolean initializedRto;
    protected long meanOverallRTO;
    private final Queue<CongestionControlLayer.e> notifyQueue;
    private final int nstart;
    private long[] overallRTO = new long[3];
    private boolean processingNotifies;
    private final InetSocketAddress remoteAddress;
    private final Queue<Exchange> requestQueue;
    private final Queue<Exchange> responseQueue;
    private final boolean usesBlindEstimator;

    public enum RtoType {
        STRONG,
        WEAK,
        NONE
    }

    public void checkAging() {
    }

    public abstract void processRttMeasurement(RtoType rtoType, long j);

    public RemoteEndpoint(InetSocketAddress inetSocketAddress, int i, int i2, boolean z) {
        this.remoteAddress = inetSocketAddress;
        this.nstart = i2;
        this.usesBlindEstimator = z;
        for (int i3 = 0; i3 < 3; i3++) {
            this.overallRTO[i3] = i;
        }
        long j = i;
        this.currentRTO = j;
        this.meanOverallRTO = j;
        this.currentOverallIndex = 0;
        this.inFlight = new HashSet();
        this.requestQueue = new LinkedList();
        this.responseQueue = new LinkedList();
        this.notifyQueue = new LinkedList();
    }

    public InetSocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    public Queue<Exchange> getRequestQueue() {
        return this.requestQueue;
    }

    public Queue<Exchange> getResponseQueue() {
        return this.responseQueue;
    }

    public Queue<CongestionControlLayer.e> getNotifyQueue() {
        return this.notifyQueue;
    }

    public void setCurrentRTO(long j) {
        this.currentRTO = j;
    }

    public long getCurrentRTO() {
        return this.currentRTO;
    }

    public boolean startProcessingNotifies() {
        synchronized (this) {
            if (this.processingNotifies) {
                return false;
            }
            this.processingNotifies = true;
            return true;
        }
    }

    public boolean stopProcessingNotifies() {
        synchronized (this) {
            if (!this.processingNotifies) {
                return false;
            }
            this.processingNotifies = false;
            return true;
        }
    }

    public boolean initialRto() {
        synchronized (this) {
            if (this.initializedRto) {
                return false;
            }
            this.initializedRto = true;
            return true;
        }
    }

    public long getRTO() {
        long j = this.currentRTO;
        int numberOfOngoingExchanges = getNumberOfOngoingExchanges();
        if (this.usesBlindEstimator && numberOfOngoingExchanges > 1 && !this.initializedRto) {
            j *= numberOfOngoingExchanges;
        }
        return Math.min(j, 32000L);
    }

    public void updateRTO(long j) {
        synchronized (this) {
            long[] jArr = this.overallRTO;
            int i = this.currentOverallIndex;
            int i2 = i + 1;
            this.currentOverallIndex = i2;
            jArr[i] = j;
            if (i2 >= jArr.length) {
                this.currentOverallIndex = 0;
            }
            long j2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                j2 += this.overallRTO[i3];
            }
            this.meanOverallRTO = j2 / 3;
            setCurrentRTO(j);
        }
    }

    public boolean registerExchange(Exchange exchange) {
        synchronized (this) {
            if (this.inFlight.contains(exchange)) {
                return true;
            }
            if (this.inFlight.size() >= this.nstart) {
                return false;
            }
            this.inFlight.add(exchange);
            return true;
        }
    }

    public boolean inFlightExchange(Exchange exchange) {
        boolean contains;
        synchronized (this) {
            contains = this.inFlight.contains(exchange);
        }
        return contains;
    }

    public boolean removeExchange(Exchange exchange) {
        synchronized (this) {
            return this.inFlight.remove(exchange);
        }
    }

    public int getNumberOfOngoingExchanges() {
        int size;
        synchronized (this) {
            size = this.inFlight.size();
        }
        return size;
    }
}
