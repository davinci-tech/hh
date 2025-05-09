package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes10.dex */
public class NullInputStream extends InputStream {
    public static final NullInputStream INSTANCE = new NullInputStream();
    private boolean eof;
    private long mark;
    private final boolean markSupported;
    private long position;
    private long readLimit;
    private final long size;
    private final boolean throwEofException;

    protected int processByte() {
        return 0;
    }

    protected void processBytes(byte[] bArr, int i, int i2) {
    }

    public NullInputStream() {
        this(0L, true, false);
    }

    public NullInputStream(long j) {
        this(j, true, false);
    }

    public NullInputStream(long j, boolean z, boolean z2) {
        this.mark = -1L;
        this.size = j;
        this.markSupported = z;
        this.throwEofException = z2;
    }

    @Override // java.io.InputStream
    public int available() {
        long j = this.size - this.position;
        if (j <= 0) {
            return 0;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    private void checkThrowEof(String str) throws EOFException {
        if (this.throwEofException) {
            throw new EOFException(str);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.eof = false;
        this.position = 0L;
        this.mark = -1L;
    }

    public long getPosition() {
        return this.position;
    }

    public long getSize() {
        return this.size;
    }

    private int handleEof() throws EOFException {
        this.eof = true;
        checkThrowEof("handleEof()");
        return -1;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        synchronized (this) {
            if (!this.markSupported) {
                throw UnsupportedOperationExceptions.mark();
            }
            this.mark = this.position;
            this.readLimit = i;
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.markSupported;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.eof) {
            checkThrowEof("read()");
            return -1;
        }
        long j = this.position;
        if (j == this.size) {
            return handleEof();
        }
        this.position = j + 1;
        return processByte();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.eof) {
            checkThrowEof("read(byte[], int, int)");
            return -1;
        }
        long j = this.position;
        long j2 = this.size;
        if (j == j2) {
            return handleEof();
        }
        long j3 = j + i2;
        this.position = j3;
        if (j3 > j2) {
            i2 -= (int) (j3 - j2);
            this.position = j2;
        }
        processBytes(bArr, i, i2);
        return i2;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        synchronized (this) {
            if (!this.markSupported) {
                throw UnsupportedOperationExceptions.reset();
            }
            long j = this.mark;
            if (j < 0) {
                throw new IOException("No position has been marked");
            }
            if (this.position > this.readLimit + j) {
                throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readLimit + "]");
            }
            this.position = j;
            this.eof = false;
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (this.eof) {
            checkThrowEof("skip(long)");
            return -1L;
        }
        long j2 = this.position;
        long j3 = this.size;
        if (j2 == j3) {
            return handleEof();
        }
        long j4 = j2 + j;
        this.position = j4;
        if (j4 <= j3) {
            return j;
        }
        long j5 = j - (j4 - j3);
        this.position = j3;
        return j5;
    }
}
