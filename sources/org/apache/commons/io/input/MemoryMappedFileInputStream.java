package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public final class MemoryMappedFileInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_SIZE = 262144;
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.wrap(new byte[0]).asReadOnlyBuffer();
    private ByteBuffer buffer;
    private final int bufferSize;
    private final FileChannel channel;
    private boolean closed;
    private long nextBufferPosition;

    public static class Builder extends AbstractStreamBuilder<MemoryMappedFileInputStream, Builder> {
        public Builder() {
            setBufferSizeDefault(262144);
            setBufferSize(262144);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public MemoryMappedFileInputStream get() throws IOException {
            return new MemoryMappedFileInputStream(getPath(), getBufferSize());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private MemoryMappedFileInputStream(Path path, int i) throws IOException {
        this.buffer = EMPTY_BUFFER;
        this.bufferSize = i;
        this.channel = FileChannel.open(path, StandardOpenOption.READ);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.buffer.remaining();
    }

    private void cleanBuffer() {
        if (ByteBufferCleaner.isSupported() && this.buffer.isDirect()) {
            ByteBufferCleaner.clean(this.buffer);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        cleanBuffer();
        this.buffer = null;
        this.channel.close();
        this.closed = true;
    }

    private void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    int getBufferSize() {
        return this.bufferSize;
    }

    private void nextBuffer() throws IOException {
        long size = this.channel.size() - this.nextBufferPosition;
        if (size > 0) {
            long min = Math.min(size, this.bufferSize);
            cleanBuffer();
            this.buffer = this.channel.map(FileChannel.MapMode.READ_ONLY, this.nextBufferPosition, min);
            this.nextBufferPosition += min;
            return;
        }
        this.buffer = EMPTY_BUFFER;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        ensureOpen();
        if (!this.buffer.hasRemaining()) {
            nextBuffer();
            if (!this.buffer.hasRemaining()) {
                return -1;
            }
        }
        return Short.toUnsignedInt(this.buffer.get());
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ensureOpen();
        if (!this.buffer.hasRemaining()) {
            nextBuffer();
            if (!this.buffer.hasRemaining()) {
                return -1;
            }
        }
        int min = Math.min(this.buffer.remaining(), i2);
        this.buffer.get(bArr, i, min);
        return min;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        ensureOpen();
        if (j <= 0) {
            return 0L;
        }
        if (j <= this.buffer.remaining()) {
            this.buffer.position((int) (r0.position() + j));
            return j;
        }
        long remaining = this.buffer.remaining() + Math.min(this.channel.size() - this.nextBufferPosition, j - this.buffer.remaining());
        this.nextBufferPosition += remaining - this.buffer.remaining();
        nextBuffer();
        return remaining;
    }
}
