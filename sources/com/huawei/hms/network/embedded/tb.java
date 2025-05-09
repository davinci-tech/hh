package com.huawei.hms.network.embedded;

import android.support.v4.media.session.PlaybackStateCompat;
import com.huawei.operation.utils.Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes9.dex */
public final class tb implements cb {

    /* renamed from: a, reason: collision with root package name */
    public final bb f5506a = new bb();
    public final yb b;
    public boolean c;

    @Override // com.huawei.hms.network.embedded.cb
    public cb writeShort(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.writeShort(i);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb writeLong(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.writeLong(j);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb writeInt(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.writeInt(i);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb writeByte(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.writeByte(i);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.write(bbVar, j);
        d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb write(byte[] bArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.write(bArr, i, i2);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb write(byte[] bArr) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.write(bArr);
        return d();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        int write = this.f5506a.write(byteBuffer);
        d();
        return write;
    }

    public String toString() {
        return "buffer(" + this.b + Constants.RIGHT_BRACKET_ONLY;
    }

    @Override // com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.b.timeout();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.c;
    }

    @Override // com.huawei.hms.network.embedded.cb, com.huawei.hms.network.embedded.yb, java.io.Flushable
    public void flush() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        bb bbVar = this.f5506a;
        long j = bbVar.b;
        if (j > 0) {
            this.b.write(bbVar, j);
        }
        this.b.flush();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb d() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long t = this.f5506a.t();
        if (t > 0) {
            this.b.write(this.f5506a, t);
        }
        return this;
    }

    @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.c) {
            return;
        }
        try {
            if (this.f5506a.b > 0) {
                yb ybVar = this.b;
                bb bbVar = this.f5506a;
                ybVar.write(bbVar, bbVar.b);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.b.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.c = true;
        if (th != null) {
            cc.a(th);
        }
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb c(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.c(j);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb c(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.c(i);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb c() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long B = this.f5506a.B();
        if (B > 0) {
            this.b.write(this.f5506a, B);
        }
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public OutputStream b() {
        return new a();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb b(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.b(j);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb b(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.b(i);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(String str, Charset charset) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(str, charset);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(String str, int i, int i2, Charset charset) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(str, i, i2, charset);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(String str, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(str, i, i2);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(String str) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(str);
        return d();
    }

    public class a extends OutputStream {
        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            tb tbVar = tb.this;
            if (tbVar.c) {
                throw new IOException("closed");
            }
            tbVar.f5506a.write(bArr, i, i2);
            tb.this.d();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            tb tbVar = tb.this;
            if (tbVar.c) {
                throw new IOException("closed");
            }
            tbVar.f5506a.writeByte((int) ((byte) i));
            tb.this.d();
        }

        public String toString() {
            return tb.this + ".outputStream()";
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            tb tbVar = tb.this;
            if (tbVar.c) {
                return;
            }
            tbVar.flush();
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            tb.this.close();
        }

        public a() {
        }
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(zb zbVar, long j) throws IOException {
        while (j > 0) {
            long read = zbVar.read(this.f5506a, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
            d();
        }
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(eb ebVar) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(ebVar);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(j);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f5506a.a(i);
        return d();
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a() {
        return this.f5506a;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public long a(zb zbVar) throws IOException {
        if (zbVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = zbVar.read(this.f5506a, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
            d();
        }
    }

    public tb(yb ybVar) {
        if (ybVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.b = ybVar;
    }
}
