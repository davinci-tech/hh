package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public class RandomAccessFileInputStream extends InputStream {
    private final boolean closeOnClose;
    private final RandomAccessFile randomAccessFile;

    public static class Builder extends AbstractStreamBuilder<RandomAccessFileInputStream, Builder> {
        private boolean closeOnClose;
        private RandomAccessFile randomAccessFile;

        @Override // org.apache.commons.io.function.IOSupplier
        public RandomAccessFileInputStream get() throws IOException {
            if (this.randomAccessFile != null) {
                if (getOrigin() != null) {
                    throw new IllegalStateException(String.format("Only set one of RandomAccessFile (%s) or origin (%s)", this.randomAccessFile, getOrigin()));
                }
                return new RandomAccessFileInputStream(this.randomAccessFile, this.closeOnClose);
            }
            return new RandomAccessFileInputStream(RandomAccessFileMode.READ_ONLY.create(checkOrigin().getFile()), this.closeOnClose);
        }

        public Builder setCloseOnClose(boolean z) {
            this.closeOnClose = z;
            return this;
        }

        public Builder setRandomAccessFile(RandomAccessFile randomAccessFile) {
            this.randomAccessFile = randomAccessFile;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public RandomAccessFileInputStream(RandomAccessFile randomAccessFile) {
        this(randomAccessFile, false);
    }

    @Deprecated
    public RandomAccessFileInputStream(RandomAccessFile randomAccessFile, boolean z) {
        this.randomAccessFile = (RandomAccessFile) Objects.requireNonNull(randomAccessFile, "file");
        this.closeOnClose = z;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        long availableLong = availableLong();
        if (availableLong > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) availableLong;
    }

    public long availableLong() throws IOException {
        return this.randomAccessFile.length() - this.randomAccessFile.getFilePointer();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        if (this.closeOnClose) {
            this.randomAccessFile.close();
        }
    }

    public RandomAccessFile getRandomAccessFile() {
        return this.randomAccessFile;
    }

    public boolean isCloseOnClose() {
        return this.closeOnClose;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.randomAccessFile.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.randomAccessFile.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.randomAccessFile.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        long filePointer = this.randomAccessFile.getFilePointer();
        long length = this.randomAccessFile.length();
        if (filePointer >= length) {
            return 0L;
        }
        long j2 = j + filePointer;
        if (j2 > length) {
            j2 = length - 1;
        }
        if (j2 > 0) {
            this.randomAccessFile.seek(j2);
        }
        return this.randomAccessFile.getFilePointer() - filePointer;
    }
}
