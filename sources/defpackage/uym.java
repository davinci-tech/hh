package defpackage;

import defpackage.uyl;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uym extends uyl {
    private static final Logger e = vha.a((Class<?>) uym.class);
    private final ConcurrentMap<Object, Queue<uyf>> g;
    private final int h;

    public uym(Configuration configuration) {
        super(configuration);
        this.g = new ConcurrentHashMap();
        this.b = new b();
        this.h = ((Integer) configuration.a((BasicDefinition) CoapConfig.aj)).intValue();
    }

    @Override // defpackage.uyl
    protected void a(uyf uyfVar, boolean z) {
        Object c = uyfVar.c();
        Queue<uyf> queue = this.g.get(c);
        if (queue == null) {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(this.h);
            arrayBlockingQueue.add(uyfVar);
            queue = this.g.putIfAbsent(c, arrayBlockingQueue);
            if (queue == null) {
                return;
            }
        }
        if (z) {
            queue.remove(uyfVar);
        }
        while (!queue.offer(uyfVar)) {
            this.f17601a.remove(queue.poll());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Queue<uyf> queue, uyf uyfVar) {
        Iterator<uyf> it = queue.iterator();
        while (it.hasNext()) {
            if (it.next() == uyfVar) {
                it.remove();
                return;
            }
        }
    }

    @Override // defpackage.uyl, org.eclipse.californium.core.network.deduplication.Deduplicator
    public void clear() {
        super.clear();
        this.g.clear();
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f17602a;

        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                uym.e.trace("Start Mark-And-Sweep with {} entries", Integer.valueOf(uym.this.f17601a.size()));
                d();
            } catch (Throwable th) {
                uym.e.warn("Exception in Mark-and-Sweep algorithm", th);
            }
        }

        private void d() {
            if (uym.this.f17601a.isEmpty()) {
                return;
            }
            long d = ClockUtil.d();
            long nanos = TimeUnit.MILLISECONDS.toNanos(uym.this.c);
            int size = uym.this.f17601a.size();
            int i = 0;
            int i2 = 0;
            for (Map.Entry entry : uym.this.g.entrySet()) {
                Queue queue = (Queue) entry.getValue();
                if (queue.isEmpty()) {
                    uym.this.g.remove(entry.getKey());
                } else {
                    i2 += queue.size();
                    while (true) {
                        uyf uyfVar = (uyf) queue.peek();
                        if (uyfVar == null) {
                            break;
                        }
                        uyl.c cVar = uym.this.f17601a.get(uyfVar);
                        long j = cVar == null ? -1L : cVar.c - (d - nanos);
                        if (j < 0) {
                            if (cVar != null) {
                                uym.this.f17601a.remove(uyfVar, cVar);
                                uym.e.trace("Mark-And-Sweep removes {}", uyfVar);
                            } else {
                                i++;
                            }
                            uym.this.b(queue, uyfVar);
                        } else if (uym.e.isTraceEnabled()) {
                            uym.e.trace("Time left {}ms", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(j)));
                        }
                    }
                }
            }
            uym.e.debug("Sweep run took {}ms", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - d)));
            if (i > 0) {
                uym.e.warn("{} exchanges missing", Integer.valueOf(i));
            }
            int i3 = size - i2;
            if (Math.abs(this.f17602a) > 1000 && Math.abs(i3) > 1000) {
                uym.e.info("Map size {} differs from queues size {}!", Integer.valueOf(size), Integer.valueOf(i2));
            }
            this.f17602a = i3;
        }
    }
}
