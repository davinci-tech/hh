package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public final class ChecksumInputStream extends CountingInputStream {
    private final long countThreshold;
    private final long expectedChecksumValue;

    public static class Builder extends AbstractStreamBuilder<ChecksumInputStream, Builder> {
        private Checksum checksum;
        private long countThreshold = -1;
        private long expectedChecksumValue;

        @Override // org.apache.commons.io.function.IOSupplier
        public ChecksumInputStream get() throws IOException {
            return new ChecksumInputStream(getInputStream(), this.checksum, this.expectedChecksumValue, this.countThreshold);
        }

        public Builder setChecksum(Checksum checksum) {
            this.checksum = checksum;
            return this;
        }

        public Builder setCountThreshold(long j) {
            this.countThreshold = j;
            return this;
        }

        public Builder setExpectedChecksumValue(long j) {
            this.expectedChecksumValue = j;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private ChecksumInputStream(InputStream inputStream, Checksum checksum, long j, long j2) {
        super(new CheckedInputStream(inputStream, checksum));
        this.countThreshold = j2;
        this.expectedChecksumValue = j;
    }

    @Override // org.apache.commons.io.input.CountingInputStream, org.apache.commons.io.input.ProxyInputStream
    protected void afterRead(int i) throws IOException {
        synchronized (this) {
            super.afterRead(i);
            if (((this.countThreshold > 0 && getByteCount() >= this.countThreshold) || i == -1) && this.expectedChecksumValue != getChecksum().getValue()) {
                throw new IOException("Checksum verification failed.");
            }
        }
    }

    private Checksum getChecksum() {
        return ((CheckedInputStream) this.in).getChecksum();
    }

    public long getRemaining() {
        return this.countThreshold - getByteCount();
    }
}
