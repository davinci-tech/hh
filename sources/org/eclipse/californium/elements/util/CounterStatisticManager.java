package org.eclipse.californium.elements.util;

import defpackage.vca;
import defpackage.vcb;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes7.dex */
public abstract class CounterStatisticManager {
    protected final vca.a align;
    private final ScheduledExecutorService executor;
    private final long interval;
    private AtomicLong lastTransfer;
    private final List<String> orderedKeys;
    private AtomicBoolean running;
    private final ConcurrentMap<String, vca> statistics;
    protected final String tag;
    private ScheduledFuture<?> taskHandle;
    private final TimeUnit unit;

    public abstract void dump();

    public abstract boolean isEnabled();

    public CounterStatisticManager(String str) {
        this.align = new vca.a();
        this.statistics = new ConcurrentHashMap();
        this.orderedKeys = new CopyOnWriteArrayList();
        this.running = new AtomicBoolean();
        this.lastTransfer = new AtomicLong(ClockUtil.d());
        this.tag = vcb.h(str);
        this.interval = 0L;
        this.unit = null;
        this.executor = null;
    }

    public CounterStatisticManager(String str, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        this.align = new vca.a();
        this.statistics = new ConcurrentHashMap();
        this.orderedKeys = new CopyOnWriteArrayList();
        this.running = new AtomicBoolean();
        this.lastTransfer = new AtomicLong(ClockUtil.d());
        if (scheduledExecutorService == null) {
            throw new NullPointerException("executor must not be null!");
        }
        this.tag = vcb.h(str);
        if (isEnabled()) {
            this.interval = j;
            this.unit = timeUnit;
            this.executor = j <= 0 ? null : scheduledExecutorService;
        } else {
            this.interval = 0L;
            this.unit = null;
            this.executor = null;
        }
    }

    protected void add(String str, vca vcaVar) {
        addByKey(str + vcaVar.b(), vcaVar);
    }

    protected void add(vca vcaVar) {
        addByKey(vcaVar.b(), vcaVar);
    }

    protected void addByKey(String str, vca vcaVar) {
        if (this.statistics.put(str, vcaVar) != null) {
            this.orderedKeys.remove(str);
        }
        this.orderedKeys.add(str);
    }

    protected void removeByKey(String str, vca vcaVar) {
        if (this.statistics.remove(str, vcaVar)) {
            this.orderedKeys.remove(str);
        }
    }

    protected void removeByKey(String str) {
        if (this.statistics.containsKey(str)) {
            this.statistics.remove(str);
            this.orderedKeys.remove(str);
        }
    }

    protected vca get(String str) {
        return this.statistics.get(str);
    }

    public vca getByKey(String str) {
        return this.statistics.get(str);
    }

    public List<String> getKeys() {
        return Collections.unmodifiableList(this.orderedKeys);
    }

    public void start() {
        synchronized (this) {
            if (this.executor != null && this.taskHandle == null) {
                this.running.set(true);
                ScheduledExecutorService scheduledExecutorService = this.executor;
                Runnable runnable = new Runnable() { // from class: org.eclipse.californium.elements.util.CounterStatisticManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CounterStatisticManager.this.running.get()) {
                            CounterStatisticManager.this.dump();
                        }
                    }
                };
                long j = this.interval;
                this.taskHandle = scheduledExecutorService.scheduleAtFixedRate(runnable, j, j, this.unit);
            }
        }
    }

    public boolean stop() {
        synchronized (this) {
            if (this.taskHandle == null) {
                return false;
            }
            this.running.set(false);
            this.taskHandle.cancel(false);
            this.taskHandle = null;
            return true;
        }
    }

    public long getLastTransferTime() {
        return this.lastTransfer.get();
    }

    public void transferCounter() {
        Iterator<vca> it = this.statistics.values().iterator();
        while (it.hasNext()) {
            it.next().h();
        }
        this.lastTransfer.set(ClockUtil.d());
    }

    public void reset() {
        Iterator<vca> it = this.statistics.values().iterator();
        while (it.hasNext()) {
            it.next().g();
        }
        this.lastTransfer.set(ClockUtil.d());
    }

    public long getCounter(String str) {
        return getByKey(str).e();
    }

    public long getCounterByKey(String str) {
        return getByKey(str).e();
    }

    public String getTag() {
        return this.tag;
    }
}
