package okio;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.network.embedded.w3;
import defpackage.ueu;
import defpackage.uhx;
import defpackage.uhy;
import defpackage.uib;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0001J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u0004J\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\fH\u0014J%\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"Lokio/AsyncTimeout;", "Lokio/Timeout;", "()V", "inQueue", "", "next", "timeoutAt", "", "access$newTimeoutException", "Ljava/io/IOException;", "cause", "enter", "", "exit", "newTimeoutException", "remainingNanos", "now", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "timedOut", "withTimeout", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", w3.a.f5555a, "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes7.dex */
public class AsyncTimeout extends Timeout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long IDLE_TIMEOUT_MILLIS;
    private static final long IDLE_TIMEOUT_NANOS;
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static final Condition condition;
    private static AsyncTimeout head;
    private static final ReentrantLock lock;
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;

    protected void timedOut() {
    }

    public final void enter() {
        long timeoutNanos = getTimeoutNanos();
        boolean hasDeadline = getHasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            INSTANCE.scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    public final boolean exit() {
        return INSTANCE.cancelScheduledTimeout(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    public final Sink sink(final Sink sink) {
        uhy.e((Object) sink, "");
        return new Sink() { // from class: okio.AsyncTimeout$sink$1
            @Override // okio.Sink
            public void write(Buffer source, long byteCount) {
                uhy.e((Object) source, "");
                SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
                while (true) {
                    long j = 0;
                    if (byteCount <= 0) {
                        return;
                    }
                    Segment segment = source.head;
                    uhy.d(segment);
                    while (true) {
                        if (j >= 65536) {
                            break;
                        }
                        j += segment.limit - segment.pos;
                        if (j >= byteCount) {
                            j = byteCount;
                            break;
                        } else {
                            segment = segment.next;
                            uhy.d(segment);
                        }
                    }
                    AsyncTimeout asyncTimeout = AsyncTimeout.this;
                    Sink sink2 = sink;
                    asyncTimeout.enter();
                    try {
                        sink2.write(source, j);
                        ueu ueuVar = ueu.d;
                        if (asyncTimeout.exit()) {
                            throw asyncTimeout.access$newTimeoutException(null);
                        }
                        byteCount -= j;
                    } catch (IOException e) {
                        e = e;
                        if (asyncTimeout.exit()) {
                            e = asyncTimeout.access$newTimeoutException(e);
                        }
                        throw e;
                    } finally {
                        asyncTimeout.exit();
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.enter();
                try {
                    sink2.flush();
                    ueu ueuVar = ueu.d;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                } catch (IOException e) {
                    e = e;
                    if (asyncTimeout.exit()) {
                        e = asyncTimeout.access$newTimeoutException(e);
                    }
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.enter();
                try {
                    sink2.close();
                    ueu ueuVar = ueu.d;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                } catch (IOException e) {
                    e = e;
                    if (asyncTimeout.exit()) {
                        e = asyncTimeout.access$newTimeoutException(e);
                    }
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Sink
            /* renamed from: timeout, reason: from getter */
            public AsyncTimeout getThis$0() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + g4.l;
            }
        };
    }

    public final Source source(final Source source) {
        uhy.e((Object) source, "");
        return new Source() { // from class: okio.AsyncTimeout$source$1
            @Override // okio.Source
            public long read(Buffer sink, long byteCount) {
                uhy.e((Object) sink, "");
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.enter();
                try {
                    long read = source2.read(sink, byteCount);
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                    return read;
                } catch (IOException e) {
                    e = e;
                    if (asyncTimeout.exit()) {
                        e = asyncTimeout.access$newTimeoutException(e);
                    }
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.enter();
                try {
                    source2.close();
                    ueu ueuVar = ueu.d;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                } catch (IOException e) {
                    e = e;
                    if (asyncTimeout.exit()) {
                        e = asyncTimeout.access$newTimeoutException(e);
                    }
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Source
            /* renamed from: timeout, reason: from getter */
            public AsyncTimeout getThis$0() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + g4.l;
            }
        };
    }

    public final <T> T withTimeout(Function0<? extends T> block) {
        uhy.e((Object) block, "");
        enter();
        try {
            try {
                T invoke = block.invoke();
                uhx.a(1);
                if (exit()) {
                    throw access$newTimeoutException(null);
                }
                uhx.b(1);
                return invoke;
            } catch (IOException e) {
                e = e;
                if (exit()) {
                    e = access$newTimeoutException(e);
                }
                throw e;
            }
        } catch (Throwable th) {
            uhx.a(1);
            exit();
            uhx.b(1);
            throw th;
        }
    }

    public final IOException access$newTimeoutException(IOException cause) {
        return newTimeoutException(cause);
    }

    protected IOException newTimeoutException(IOException cause) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (cause != null) {
            interruptedIOException.initCause(cause);
        }
        return interruptedIOException;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "()V", "run", "", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ReentrantLock lock;
            AsyncTimeout awaitTimeout$okio;
            while (true) {
                try {
                    lock = AsyncTimeout.INSTANCE.getLock();
                    lock.lock();
                    try {
                        awaitTimeout$okio = AsyncTimeout.INSTANCE.awaitTimeout$okio();
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException unused) {
                }
                if (awaitTimeout$okio == AsyncTimeout.head) {
                    Companion companion = AsyncTimeout.INSTANCE;
                    AsyncTimeout.head = null;
                    return;
                } else {
                    ueu ueuVar = ueu.d;
                    lock.unlock();
                    if (awaitTimeout$okio != null) {
                        awaitTimeout$okio.timedOut();
                    }
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lokio/AsyncTimeout$Companion;", "", "()V", "IDLE_TIMEOUT_MILLIS", "", "IDLE_TIMEOUT_NANOS", "TIMEOUT_WRITE_SIZE", "", "condition", "Ljava/util/concurrent/locks/Condition;", "getCondition", "()Ljava/util/concurrent/locks/Condition;", "head", "Lokio/AsyncTimeout;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "awaitTimeout", "awaitTimeout$okio", "cancelScheduledTimeout", "", "node", "scheduleTimeout", "", "timeoutNanos", "hasDeadline", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final ReentrantLock getLock() {
            return AsyncTimeout.lock;
        }

        public final Condition getCondition() {
            return AsyncTimeout.condition;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
            ReentrantLock lock = AsyncTimeout.INSTANCE.getLock();
            lock.lock();
            try {
                if (!node.inQueue) {
                    node.inQueue = true;
                    if (AsyncTimeout.head == null) {
                        Companion companion = AsyncTimeout.INSTANCE;
                        AsyncTimeout.head = new AsyncTimeout();
                        new Watchdog().start();
                    }
                    long nanoTime = System.nanoTime();
                    if (timeoutNanos != 0 && hasDeadline) {
                        node.timeoutAt = Math.min(timeoutNanos, node.deadlineNanoTime() - nanoTime) + nanoTime;
                    } else if (timeoutNanos != 0) {
                        node.timeoutAt = timeoutNanos + nanoTime;
                    } else if (hasDeadline) {
                        node.timeoutAt = node.deadlineNanoTime();
                    } else {
                        throw new AssertionError();
                    }
                    long remainingNanos = node.remainingNanos(nanoTime);
                    AsyncTimeout asyncTimeout = AsyncTimeout.head;
                    uhy.d(asyncTimeout);
                    while (asyncTimeout.next != null) {
                        AsyncTimeout asyncTimeout2 = asyncTimeout.next;
                        uhy.d(asyncTimeout2);
                        if (remainingNanos < asyncTimeout2.remainingNanos(nanoTime)) {
                            break;
                        }
                        asyncTimeout = asyncTimeout.next;
                        uhy.d(asyncTimeout);
                    }
                    node.next = asyncTimeout.next;
                    asyncTimeout.next = node;
                    if (asyncTimeout == AsyncTimeout.head) {
                        AsyncTimeout.INSTANCE.getCondition().signal();
                    }
                    ueu ueuVar = ueu.d;
                    return;
                }
                throw new IllegalStateException("Unbalanced enter/exit".toString());
            } finally {
                lock.unlock();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean cancelScheduledTimeout(AsyncTimeout node) {
            ReentrantLock lock = AsyncTimeout.INSTANCE.getLock();
            lock.lock();
            try {
                if (!node.inQueue) {
                    return false;
                }
                node.inQueue = false;
                for (AsyncTimeout asyncTimeout = AsyncTimeout.head; asyncTimeout != null; asyncTimeout = asyncTimeout.next) {
                    if (asyncTimeout.next == node) {
                        asyncTimeout.next = node.next;
                        node.next = null;
                        return false;
                    }
                }
                lock.unlock();
                return true;
            } finally {
                lock.unlock();
            }
        }

        public final AsyncTimeout awaitTimeout$okio() throws InterruptedException {
            AsyncTimeout asyncTimeout = AsyncTimeout.head;
            uhy.d(asyncTimeout);
            AsyncTimeout asyncTimeout2 = asyncTimeout.next;
            if (asyncTimeout2 != null) {
                long remainingNanos = asyncTimeout2.remainingNanos(System.nanoTime());
                if (remainingNanos <= 0) {
                    AsyncTimeout asyncTimeout3 = AsyncTimeout.head;
                    uhy.d(asyncTimeout3);
                    asyncTimeout3.next = asyncTimeout2.next;
                    asyncTimeout2.next = null;
                    return asyncTimeout2;
                }
                getCondition().await(remainingNanos, TimeUnit.NANOSECONDS);
                return null;
            }
            long nanoTime = System.nanoTime();
            getCondition().await(AsyncTimeout.IDLE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            AsyncTimeout asyncTimeout4 = AsyncTimeout.head;
            uhy.d(asyncTimeout4);
            if (asyncTimeout4.next != null || System.nanoTime() - nanoTime < AsyncTimeout.IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return AsyncTimeout.head;
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        lock = reentrantLock;
        Condition newCondition = reentrantLock.newCondition();
        uhy.a(newCondition, "");
        condition = newCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }
}
