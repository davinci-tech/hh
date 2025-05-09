package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class tsr extends InputStream implements onm {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public InputStream bcd;
    public pon cde;
    public final ByteBuffer def;
    public boolean efg;
    public long fgh;

    public tsr(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, z, 4096);
    }

    @Override // com.huawei.agconnect.apms.onm
    public void abc(qpo qpoVar) {
        pon ponVar = this.cde;
        synchronized (ponVar.cde) {
            ponVar.cde.remove(qpoVar);
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        try {
            return (this.efg ? this.def.remaining() : 0) + this.bcd.available();
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    public void bcd(qpo qpoVar) {
        pon ponVar = this.cde;
        synchronized (ponVar.cde) {
            ponVar.cde.add(qpoVar);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.bcd.close();
            bcd();
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        if (this.bcd.markSupported()) {
            this.bcd.mark(i);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.bcd.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.efg) {
            synchronized (this.def) {
                if (abc(1L)) {
                    byte b = this.def.hasRemaining() ^ true ? (byte) -1 : this.def.get();
                    if (b >= 0) {
                        this.fgh++;
                    }
                    return b;
                }
            }
        }
        try {
            int read = this.bcd.read();
            if (read >= 0) {
                this.fgh++;
            } else {
                bcd();
            }
            return read;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (this.bcd.markSupported()) {
            try {
                this.bcd.reset();
            } catch (IOException e) {
                abc(e);
                throw e;
            }
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (this.efg) {
            synchronized (this.def) {
                if (abc(j)) {
                    this.def.position((int) j);
                    this.fgh += j;
                    return j;
                }
                j -= this.def.remaining();
                if (j <= 0) {
                    throw new IOException("failed to skip partial read from buffer.");
                }
                ByteBuffer byteBuffer = this.def;
                byteBuffer.position(byteBuffer.remaining());
            }
        }
        try {
            long skip = this.bcd.skip(j);
            this.fgh += skip;
            return skip;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    public tsr(InputStream inputStream, boolean z, int i) throws IOException {
        this.fgh = 0L;
        if (inputStream == null) {
            throw new IOException("InputStreamExtension: input stream can not be null.");
        }
        this.bcd = inputStream;
        this.efg = z;
        this.cde = new pon();
        if (!z) {
            this.def = null;
        } else {
            this.def = ByteBuffer.allocate(i);
            abc();
        }
    }

    public final boolean abc(long j) {
        return ((long) this.def.remaining()) >= j;
    }

    public final void bcd() {
        if (this.cde.cde()) {
            return;
        }
        this.cde.abc(new rqp(this, this.fgh, null));
    }

    public final int abc(byte[] bArr, int i, int i2) {
        if (!this.def.hasRemaining()) {
            return -1;
        }
        int remaining = this.def.remaining();
        this.def.get(bArr, i, i2);
        return remaining - this.def.remaining();
    }

    public final void abc() {
        int read;
        ByteBuffer byteBuffer = this.def;
        if (byteBuffer == null || !byteBuffer.hasArray()) {
            return;
        }
        synchronized (this.def) {
            int i = 0;
            while (i < this.def.capacity() && (read = this.bcd.read(this.def.array(), i, this.def.capacity() - i)) > 0) {
                try {
                    i += read;
                } catch (IOException e) {
                    abc.warn("fill buffer error: " + e.toString());
                    this.def.limit(0);
                }
            }
            this.def.limit(i);
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        if (this.efg) {
            synchronized (this.def) {
                if (abc(length)) {
                    int abc2 = abc(bArr, 0, bArr.length);
                    if (abc2 >= 0) {
                        this.fgh += abc2;
                        return abc2;
                    }
                    throw new IOException("failed to read buffer bytes.");
                }
                int remaining = this.def.remaining();
                if (remaining > 0) {
                    i = abc(bArr, 0, remaining);
                    if (i >= 0) {
                        length -= i;
                        this.fgh += i;
                    } else {
                        throw new IOException("failed to partial read from buffer.");
                    }
                }
            }
        }
        try {
            int read = this.bcd.read(bArr, i, length);
            if (read >= 0) {
                this.fgh += read;
                return read + i;
            }
            if (i > 0) {
                return i;
            }
            bcd();
            return read;
        } catch (IOException e) {
            abc.warn("failed to read input stream: " + e.getMessage());
            abc(e);
            throw e;
        }
    }

    public final void abc(Exception exc) {
        if (this.cde.cde()) {
            return;
        }
        this.cde.bcd(new rqp(this, this.fgh, exc));
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (this.efg) {
            synchronized (this.def) {
                if (abc(i2)) {
                    int abc2 = abc(bArr, i, i2);
                    if (abc2 >= 0) {
                        this.fgh += abc2;
                        return abc2;
                    }
                    throw new IOException("failed to read buffer bytes.");
                }
                int remaining = this.def.remaining();
                if (remaining > 0) {
                    i3 = abc(bArr, i, remaining);
                    if (i3 >= 0) {
                        i2 -= i3;
                        this.fgh += i3;
                    } else {
                        throw new IOException("failed to partial read from buffer.");
                    }
                }
            }
        }
        try {
            int read = this.bcd.read(bArr, i + i3, i2);
            if (read >= 0) {
                this.fgh += read;
                return read + i3;
            }
            if (i3 > 0) {
                return i3;
            }
            bcd();
            return read;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }
}
