package org.apache.commons.io.input;

import com.huawei.operation.ble.BleConstants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public final class BufferedFileChannelInputStream extends InputStream {
    private final ByteBuffer byteBuffer;
    private final FileChannel fileChannel;

    public static class Builder extends AbstractStreamBuilder<BufferedFileChannelInputStream, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public BufferedFileChannelInputStream get() throws IOException {
            return new BufferedFileChannelInputStream(getPath(), getBufferSize());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public BufferedFileChannelInputStream(File file) throws IOException {
        this(file, 8192);
    }

    @Deprecated
    public BufferedFileChannelInputStream(File file, int i) throws IOException {
        this(file.toPath(), i);
    }

    @Deprecated
    public BufferedFileChannelInputStream(Path path) throws IOException {
        this(path, 8192);
    }

    @Deprecated
    public BufferedFileChannelInputStream(Path path, int i) throws IOException {
        Objects.requireNonNull(path, BleConstants.KEY_PATH);
        this.fileChannel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i);
        this.byteBuffer = allocateDirect;
        allocateDirect.flip();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int remaining;
        synchronized (this) {
            remaining = this.byteBuffer.remaining();
        }
        return remaining;
    }

    private void clean(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            cleanDirectBuffer(byteBuffer);
        }
    }

    private void cleanDirectBuffer(ByteBuffer byteBuffer) {
        if (ByteBufferCleaner.isSupported()) {
            ByteBufferCleaner.clean(byteBuffer);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            try {
                this.fileChannel.close();
            } finally {
                clean(this.byteBuffer);
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        synchronized (this) {
            if (!refill()) {
                return -1;
            }
            return this.byteBuffer.get() & 255;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        synchronized (this) {
            if (i >= 0 && i2 >= 0 && (i3 = i + i2) >= 0) {
                if (i3 <= bArr.length) {
                    if (!refill()) {
                        return -1;
                    }
                    int min = Math.min(i2, this.byteBuffer.remaining());
                    this.byteBuffer.get(bArr, i, min);
                    return min;
                }
            }
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean refill() throws IOException {
        if (!this.byteBuffer.hasRemaining()) {
            this.byteBuffer.clear();
            int i = 0;
            while (i == 0) {
                i = this.fileChannel.read(this.byteBuffer);
            }
            this.byteBuffer.flip();
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        synchronized (this) {
            if (j <= 0) {
                return 0L;
            }
            if (this.byteBuffer.remaining() >= j) {
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + ((int) j));
                return j;
            }
            long remaining = this.byteBuffer.remaining();
            this.byteBuffer.position(0);
            this.byteBuffer.flip();
            return remaining + skipFromFileChannel(j - remaining);
        }
    }

    private long skipFromFileChannel(long j) throws IOException {
        long position = this.fileChannel.position();
        long size = this.fileChannel.size();
        long j2 = size - position;
        if (j > j2) {
            this.fileChannel.position(size);
            return j2;
        }
        this.fileChannel.position(position + j);
        return j;
    }
}
