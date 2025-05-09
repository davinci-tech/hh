package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* loaded from: classes10.dex */
public class NullReader extends Reader {
    public static final NullReader INSTANCE = new NullReader();
    private boolean eof;
    private long mark;
    private final boolean markSupported;
    private long position;
    private long readLimit;
    private final long size;
    private final boolean throwEofException;

    protected int processChar() {
        return 0;
    }

    protected void processChars(char[] cArr, int i, int i2) {
    }

    public NullReader() {
        this(0L, true, false);
    }

    public NullReader(long j) {
        this(j, true, false);
    }

    public NullReader(long j, boolean z, boolean z2) {
        this.mark = -1L;
        this.size = j;
        this.markSupported = z;
        this.throwEofException = z2;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.eof = false;
        this.position = 0L;
        this.mark = -1L;
    }

    private int doEndOfFile() throws EOFException {
        this.eof = true;
        if (this.throwEofException) {
            throw new EOFException();
        }
        return -1;
    }

    public long getPosition() {
        return this.position;
    }

    public long getSize() {
        return this.size;
    }

    @Override // java.io.Reader
    public void mark(int i) {
        synchronized (this) {
            if (!this.markSupported) {
                throw UnsupportedOperationExceptions.mark();
            }
            this.mark = this.position;
            this.readLimit = i;
        }
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.markSupported;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        long j = this.position;
        if (j == this.size) {
            return doEndOfFile();
        }
        this.position = j + 1;
        return processChar();
    }

    @Override // java.io.Reader
    public int read(char[] cArr) throws IOException {
        return read(cArr, 0, cArr.length);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        long j = this.position;
        long j2 = this.size;
        if (j == j2) {
            return doEndOfFile();
        }
        long j3 = j + i2;
        this.position = j3;
        if (j3 > j2) {
            i2 -= (int) (j3 - j2);
            this.position = j2;
        }
        processChars(cArr, i, i2);
        return i2;
    }

    @Override // java.io.Reader
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

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        if (this.eof) {
            throw new IOException("Skip after end of file");
        }
        long j2 = this.position;
        long j3 = this.size;
        if (j2 == j3) {
            return doEndOfFile();
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
