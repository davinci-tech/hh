package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes10.dex */
public class UnixLineEndingInputStream extends InputStream {
    private boolean atEos;
    private boolean atSlashCr;
    private boolean atSlashLf;
    private final InputStream in;
    private final boolean lineFeedAtEndOfFile;

    public UnixLineEndingInputStream(InputStream inputStream, boolean z) {
        this.in = inputStream;
        this.lineFeedAtEndOfFile = z;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.in.close();
    }

    private int handleEos(boolean z) {
        if (z || !this.lineFeedAtEndOfFile || this.atSlashLf) {
            return -1;
        }
        this.atSlashLf = true;
        return 10;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        synchronized (this) {
            throw UnsupportedOperationExceptions.mark();
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        boolean z = this.atSlashCr;
        if (this.atEos) {
            return handleEos(z);
        }
        int readWithUpdate = readWithUpdate();
        if (this.atEos) {
            return handleEos(z);
        }
        if (this.atSlashCr) {
            return 10;
        }
        return (z && this.atSlashLf) ? read() : readWithUpdate;
    }

    private int readWithUpdate() throws IOException {
        int read = this.in.read();
        boolean z = read == -1;
        this.atEos = z;
        if (z) {
            return read;
        }
        this.atSlashCr = read == 13;
        this.atSlashLf = read == 10;
        return read;
    }
}
