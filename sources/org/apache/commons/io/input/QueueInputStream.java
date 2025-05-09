package org.apache.commons.io.input;

import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.output.QueueOutputStream;

/* loaded from: classes10.dex */
public class QueueInputStream extends InputStream {
    private final BlockingQueue<Integer> blockingQueue;
    private final long timeoutNanos;

    public static class Builder extends AbstractStreamBuilder<QueueInputStream, Builder> {
        private BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue();
        private Duration timeout = Duration.ZERO;

        @Override // org.apache.commons.io.function.IOSupplier
        public QueueInputStream get() {
            return new QueueInputStream(this.blockingQueue, this.timeout);
        }

        public Builder setBlockingQueue(BlockingQueue<Integer> blockingQueue) {
            if (blockingQueue == null) {
                blockingQueue = new LinkedBlockingQueue<>();
            }
            this.blockingQueue = blockingQueue;
            return this;
        }

        public Builder setTimeout(Duration duration) {
            if (duration != null && duration.toNanos() < 0) {
                throw new IllegalArgumentException("timeout must not be negative");
            }
            if (duration == null) {
                duration = Duration.ZERO;
            }
            this.timeout = duration;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public QueueInputStream() {
        this(new LinkedBlockingQueue());
    }

    @Deprecated
    public QueueInputStream(BlockingQueue<Integer> blockingQueue) {
        this(blockingQueue, Duration.ZERO);
    }

    private QueueInputStream(BlockingQueue<Integer> blockingQueue, Duration duration) {
        this.blockingQueue = (BlockingQueue) Objects.requireNonNull(blockingQueue, "blockingQueue");
        this.timeoutNanos = ((Duration) Objects.requireNonNull(duration, "timeout")).toNanos();
    }

    BlockingQueue<Integer> getBlockingQueue() {
        return this.blockingQueue;
    }

    Duration getTimeout() {
        return Duration.ofNanos(this.timeoutNanos);
    }

    public QueueOutputStream newQueueOutputStream() {
        return new QueueOutputStream(this.blockingQueue);
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            Integer poll = this.blockingQueue.poll(this.timeoutNanos, TimeUnit.NANOSECONDS);
            if (poll == null) {
                return -1;
            }
            return poll.intValue() & 255;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
