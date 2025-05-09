package org.apache.commons.io.monitor;

import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.ThreadUtils;

/* loaded from: classes10.dex */
public final class FileAlterationMonitor implements Runnable {
    private static final FileAlterationObserver[] EMPTY_ARRAY = new FileAlterationObserver[0];
    private final long intervalMillis;
    private final List<FileAlterationObserver> observers;
    private volatile boolean running;
    private Thread thread;
    private ThreadFactory threadFactory;

    public FileAlterationMonitor() {
        this(PreConnectManager.CONNECT_INTERNAL);
    }

    public FileAlterationMonitor(long j) {
        this.observers = new CopyOnWriteArrayList();
        this.intervalMillis = j;
    }

    public FileAlterationMonitor(long j, Collection<FileAlterationObserver> collection) {
        this(j, (FileAlterationObserver[]) ((Collection) Optional.ofNullable(collection).orElse(Collections.emptyList())).toArray(EMPTY_ARRAY));
    }

    public FileAlterationMonitor(long j, FileAlterationObserver... fileAlterationObserverArr) {
        this(j);
        if (fileAlterationObserverArr != null) {
            Stream.of((Object[]) fileAlterationObserverArr).forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationMonitor$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FileAlterationMonitor.this.addObserver((FileAlterationObserver) obj);
                }
            });
        }
    }

    public void addObserver(FileAlterationObserver fileAlterationObserver) {
        if (fileAlterationObserver != null) {
            this.observers.add(fileAlterationObserver);
        }
    }

    public long getInterval() {
        return this.intervalMillis;
    }

    public Iterable<FileAlterationObserver> getObservers() {
        return new ArrayList(this.observers);
    }

    public void removeObserver(final FileAlterationObserver fileAlterationObserver) {
        if (fileAlterationObserver != null) {
            List<FileAlterationObserver> list = this.observers;
            Objects.requireNonNull(fileAlterationObserver);
            list.removeIf(new Predicate() { // from class: org.apache.commons.io.monitor.FileAlterationMonitor$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = FileAlterationObserver.this.equals((FileAlterationObserver) obj);
                    return equals;
                }
            });
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.running) {
            this.observers.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationMonitor$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((FileAlterationObserver) obj).checkAndNotify();
                }
            });
            if (!this.running) {
                return;
            } else {
                try {
                    ThreadUtils.sleep(Duration.ofMillis(this.intervalMillis));
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        synchronized (this) {
            this.threadFactory = threadFactory;
        }
    }

    public void start() throws Exception {
        synchronized (this) {
            if (this.running) {
                throw new IllegalStateException("Monitor is already running");
            }
            Iterator<FileAlterationObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().initialize();
            }
            this.running = true;
            ThreadFactory threadFactory = this.threadFactory;
            if (threadFactory != null) {
                this.thread = threadFactory.newThread(this);
            } else {
                this.thread = new Thread(this);
            }
            this.thread.start();
        }
    }

    public void stop() throws Exception {
        synchronized (this) {
            stop(this.intervalMillis);
        }
    }

    public void stop(long j) throws Exception {
        synchronized (this) {
            if (!this.running) {
                throw new IllegalStateException("Monitor is not running");
            }
            this.running = false;
            try {
                this.thread.interrupt();
                this.thread.join(j);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            Iterator<FileAlterationObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().destroy();
            }
        }
    }
}
