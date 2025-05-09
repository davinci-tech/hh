package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import net.lingala.zip4j.io.inputstream.CipherInputStream;
import net.lingala.zip4j.io.inputstream.DecompressedInputStream;

/* loaded from: classes10.dex */
public class uro extends DecompressedInputStream {

    /* renamed from: a, reason: collision with root package name */
    private int f17518a;
    private Inflater c;
    private byte[] d;
    private byte[] e;

    public uro(CipherInputStream<?> cipherInputStream, int i) {
        super(cipherInputStream);
        this.d = new byte[1];
        this.c = new Inflater(true);
        this.e = new byte[i];
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read() throws IOException {
        if (read(this.d) == -1) {
            return -1;
        }
        return this.d[0];
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            try {
                int inflate = this.c.inflate(bArr, i, i2);
                if (inflate != 0) {
                    return inflate;
                }
                if (!this.c.finished() && !this.c.needsDictionary()) {
                    if (this.c.needsInput()) {
                        c();
                    }
                }
                return -1;
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        }
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream
    public void endOfEntryReached(InputStream inputStream, int i) throws IOException {
        Inflater inflater = this.c;
        if (inflater != null) {
            inflater.end();
            this.c = null;
        }
        super.endOfEntryReached(inputStream, i);
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream
    public int pushBackInputStreamIfNecessary(PushbackInputStream pushbackInputStream) throws IOException {
        int remaining = this.c.getRemaining();
        if (remaining > 0) {
            pushbackInputStream.unread(getLastReadRawDataCache(), this.f17518a - remaining, remaining);
        }
        return remaining;
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Inflater inflater = this.c;
        if (inflater != null) {
            inflater.end();
        }
        super.close();
    }

    private void c() throws IOException {
        byte[] bArr = this.e;
        int read = super.read(bArr, 0, bArr.length);
        this.f17518a = read;
        if (read == -1) {
            throw new EOFException("Unexpected end of input stream");
        }
        this.c.setInput(this.e, 0, read);
    }
}
