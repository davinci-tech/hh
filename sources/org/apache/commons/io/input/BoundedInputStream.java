package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes7.dex */
public class BoundedInputStream extends ProxyInputStream {
    private long count;
    private long mark;
    private final long maxCount;
    private boolean propagateClose;

    protected void onMaxLength(long j, long j2) throws IOException {
    }

    /* loaded from: classes10.dex */
    static abstract class AbstractBuilder<T extends AbstractBuilder<T>> extends AbstractStreamBuilder<BoundedInputStream, T> {
        private long count;
        private long maxCount = -1;
        private boolean propagateClose = true;

        AbstractBuilder() {
        }

        long getCount() {
            return this.count;
        }

        long getMaxCount() {
            return this.maxCount;
        }

        boolean isPropagateClose() {
            return this.propagateClose;
        }

        public T setCount(long j) {
            this.count = Math.max(0L, j);
            return (T) asThis();
        }

        public T setMaxCount(long j) {
            this.maxCount = Math.max(-1L, j);
            return (T) asThis();
        }

        public T setPropagateClose(boolean z) {
            this.propagateClose = z;
            return (T) asThis();
        }
    }

    /* loaded from: classes10.dex */
    public static class Builder extends AbstractBuilder<Builder> {
        /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.io.input.BoundedInputStream$AbstractBuilder, org.apache.commons.io.input.BoundedInputStream$Builder] */
        @Override // org.apache.commons.io.input.BoundedInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ Builder setCount(long j) {
            return super.setCount(j);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.io.input.BoundedInputStream$AbstractBuilder, org.apache.commons.io.input.BoundedInputStream$Builder] */
        @Override // org.apache.commons.io.input.BoundedInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ Builder setMaxCount(long j) {
            return super.setMaxCount(j);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.io.input.BoundedInputStream$AbstractBuilder, org.apache.commons.io.input.BoundedInputStream$Builder] */
        @Override // org.apache.commons.io.input.BoundedInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ Builder setPropagateClose(boolean z) {
            return super.setPropagateClose(z);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public BoundedInputStream get() throws IOException {
            return new BoundedInputStream(getInputStream(), getCount(), getMaxCount(), isPropagateClose());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public BoundedInputStream(InputStream inputStream) {
        this(inputStream, -1L);
    }

    @Deprecated
    public BoundedInputStream(InputStream inputStream, long j) {
        this(inputStream, 0L, j, true);
    }

    BoundedInputStream(InputStream inputStream, long j, long j2, boolean z) {
        super(inputStream);
        this.count = j;
        this.maxCount = j2;
        this.propagateClose = z;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream
    protected void afterRead(int i) throws IOException {
        synchronized (this) {
            if (i != -1) {
                this.count += i;
            }
        }
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (isMaxCount()) {
            onMaxLength(this.maxCount, getCount());
            return 0;
        }
        return this.in.available();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.propagateClose) {
            this.in.close();
        }
    }

    public long getCount() {
        long j;
        synchronized (this) {
            j = this.count;
        }
        return j;
    }

    public long getMaxCount() {
        return this.maxCount;
    }

    @Deprecated
    public long getMaxLength() {
        return this.maxCount;
    }

    public long getRemaining() {
        return Math.max(0L, getMaxCount() - getCount());
    }

    private boolean isMaxCount() {
        return this.maxCount >= 0 && getCount() >= this.maxCount;
    }

    public boolean isPropagateClose() {
        return this.propagateClose;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        synchronized (this) {
            this.in.mark(i);
            this.mark = this.count;
        }
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (isMaxCount()) {
            onMaxLength(this.maxCount, getCount());
            return -1;
        }
        return super.read();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (isMaxCount()) {
            onMaxLength(this.maxCount, getCount());
            return -1;
        }
        return super.read(bArr, i, (int) toReadLen(i2));
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        synchronized (this) {
            this.in.reset();
            this.count = this.mark;
        }
    }

    @Deprecated
    public void setPropagateClose(boolean z) {
        this.propagateClose = z;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long skip;
        synchronized (this) {
            skip = super.skip(toReadLen(j));
            this.count += skip;
        }
        return skip;
    }

    private long toReadLen(long j) {
        long j2 = this.maxCount;
        return j2 >= 0 ? Math.min(j, j2 - getCount()) : j;
    }

    public String toString() {
        return this.in.toString();
    }
}
