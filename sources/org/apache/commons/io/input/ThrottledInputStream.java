package org.apache.commons.io.input;

import androidx.core.location.LocationRequestCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public final class ThrottledInputStream extends CountingInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long maxBytesPerSecond;
    private final long startTime;
    private Duration totalSleepDuration;

    static long toSleepMillis(long j, long j2, long j3) {
        if (j <= 0 || j2 <= 0 || j3 == 0) {
            return 0L;
        }
        long j4 = (long) (((j / j2) * 1000.0d) - j3);
        if (j4 <= 0) {
            return 0L;
        }
        return j4;
    }

    public static class Builder extends AbstractStreamBuilder<ThrottledInputStream, Builder> {
        private long maxBytesPerSecond = LocationRequestCompat.PASSIVE_INTERVAL;

        @Override // org.apache.commons.io.function.IOSupplier
        public ThrottledInputStream get() throws IOException {
            return new ThrottledInputStream(getInputStream(), this.maxBytesPerSecond);
        }

        public void setMaxBytesPerSecond(long j) {
            this.maxBytesPerSecond = j;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private ThrottledInputStream(InputStream inputStream, long j) {
        super(inputStream);
        this.startTime = System.currentTimeMillis();
        this.totalSleepDuration = Duration.ZERO;
        this.maxBytesPerSecond = j;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream
    protected void beforeRead(int i) throws IOException {
        throttle();
    }

    private long getBytesPerSecond() {
        long currentTimeMillis = (System.currentTimeMillis() - this.startTime) / 1000;
        if (currentTimeMillis == 0) {
            return getByteCount();
        }
        return getByteCount() / currentTimeMillis;
    }

    private long getSleepMillis() {
        return toSleepMillis(getByteCount(), this.maxBytesPerSecond, System.currentTimeMillis() - this.startTime);
    }

    Duration getTotalSleepDuration() {
        return this.totalSleepDuration;
    }

    private void throttle() throws InterruptedIOException {
        long sleepMillis = getSleepMillis();
        if (sleepMillis > 0) {
            this.totalSleepDuration = this.totalSleepDuration.plus(sleepMillis, ChronoUnit.MILLIS);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepMillis);
            } catch (InterruptedException unused) {
                throw new InterruptedIOException("Thread aborted");
            }
        }
    }

    public String toString() {
        return "ThrottledInputStream[bytesRead=" + getByteCount() + ", maxBytesPerSec=" + this.maxBytesPerSecond + ", bytesPerSec=" + getBytesPerSecond() + ", totalSleepDuration=" + this.totalSleepDuration + ']';
    }
}
