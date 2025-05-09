package com.huawei.hms.network.embedded;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.core.location.LocationRequestCompat;
import com.huawei.operation.utils.Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class ub implements db {

    /* renamed from: a, reason: collision with root package name */
    public final bb f5531a = new bb();
    public final zb b;
    public boolean c;

    public String toString() {
        return "buffer(" + this.b + Constants.RIGHT_BRACKET_ONLY;
    }

    @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.b.timeout();
    }

    @Override // com.huawei.hms.network.embedded.db
    public void skip(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            bb bbVar = this.f5531a;
            if (bbVar.b == 0 && this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.f5531a.B());
            this.f5531a.skip(min);
            j -= min;
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public short readShort() throws IOException {
        i(2L);
        return this.f5531a.readShort();
    }

    @Override // com.huawei.hms.network.embedded.db
    public long readLong() throws IOException {
        i(8L);
        return this.f5531a.readLong();
    }

    @Override // com.huawei.hms.network.embedded.db
    public int readInt() throws IOException {
        i(4L);
        return this.f5531a.readInt();
    }

    @Override // com.huawei.hms.network.embedded.db
    public void readFully(byte[] bArr) throws IOException {
        try {
            i(bArr.length);
            this.f5531a.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                bb bbVar = this.f5531a;
                long j = bbVar.b;
                if (j <= 0) {
                    throw e;
                }
                int read = bbVar.read(bArr, i, (int) j);
                if (read == -1) {
                    throw new AssertionError();
                }
                i += read;
            }
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public byte readByte() throws IOException {
        i(1L);
        return this.f5531a.readByte();
    }

    @Override // com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j) throws IOException {
        if (bbVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        bb bbVar2 = this.f5531a;
        if (bbVar2.b == 0 && this.b.read(bbVar2, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.f5531a.read(bbVar, Math.min(j, this.f5531a.b));
    }

    @Override // com.huawei.hms.network.embedded.db
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = i2;
        cc.a(bArr.length, i, j);
        bb bbVar = this.f5531a;
        if (bbVar.b == 0 && this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.f5531a.read(bArr, i, (int) Math.min(j, this.f5531a.b));
    }

    @Override // com.huawei.hms.network.embedded.db
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        bb bbVar = this.f5531a;
        if (bbVar.b == 0 && this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.f5531a.read(byteBuffer);
    }

    @Override // com.huawei.hms.network.embedded.db
    public eb r() throws IOException {
        this.f5531a.a(this.b);
        return this.f5531a.r();
    }

    @Override // com.huawei.hms.network.embedded.db
    public byte[] q() throws IOException {
        this.f5531a.a(this.b);
        return this.f5531a.q();
    }

    @Override // com.huawei.hms.network.embedded.db
    public db peek() {
        return ob.a(new qb(this));
    }

    @Override // com.huawei.hms.network.embedded.db
    public long p() throws IOException {
        byte j;
        i(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!h(i2)) {
                break;
            }
            j = this.f5531a.j(i);
            if ((j < 48 || j > 57) && !(i == 0 && j == 45)) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(j)));
        }
        return this.f5531a.p();
    }

    @Override // com.huawei.hms.network.embedded.db
    public String o() throws IOException {
        this.f5531a.a(this.b);
        return this.f5531a.o();
    }

    @Override // com.huawei.hms.network.embedded.db
    public String n() throws IOException {
        return e(LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // com.huawei.hms.network.embedded.db
    public InputStream m() {
        return new a();
    }

    @Override // com.huawei.hms.network.embedded.db
    @Nullable
    public String l() throws IOException {
        long a2 = a((byte) 10);
        if (a2 != -1) {
            return this.f5531a.k(a2);
        }
        long j = this.f5531a.b;
        if (j != 0) {
            return f(j);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0031, code lost:
    
        if (r0 == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
    
        throw new java.lang.NumberFormatException(java.lang.String.format("Expected leading [0-9a-fA-F] character but was %#x", java.lang.Byte.valueOf(r2)));
     */
    @Override // com.huawei.hms.network.embedded.db
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long k() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 1
            r5.i(r0)
            r0 = 0
        L6:
            int r1 = r0 + 1
            long r2 = (long) r1
            boolean r2 = r5.h(r2)
            if (r2 == 0) goto L48
            com.huawei.hms.network.embedded.bb r2 = r5.f5531a
            long r3 = (long) r0
            byte r2 = r2.j(r3)
            r3 = 48
            if (r2 < r3) goto L1e
            r3 = 57
            if (r2 <= r3) goto L2f
        L1e:
            r3 = 97
            if (r2 < r3) goto L26
            r3 = 102(0x66, float:1.43E-43)
            if (r2 <= r3) goto L2f
        L26:
            r3 = 65
            if (r2 < r3) goto L31
            r3 = 70
            if (r2 <= r3) goto L2f
            goto L31
        L2f:
            r0 = r1
            goto L6
        L31:
            if (r0 == 0) goto L34
            goto L48
        L34:
            java.lang.Byte r0 = java.lang.Byte.valueOf(r2)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was %#x"
            java.lang.String r0 = java.lang.String.format(r2, r0)
            r1.<init>(r0)
            throw r1
        L48:
            com.huawei.hms.network.embedded.bb r0 = r5.f5531a
            long r0 = r0.k()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.ub.k():long");
    }

    @Override // com.huawei.hms.network.embedded.db
    public short j() throws IOException {
        i(2L);
        return this.f5531a.j();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.c;
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean i() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        return this.f5531a.i() && this.b.read(this.f5531a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // com.huawei.hms.network.embedded.db
    public void i(long j) throws IOException {
        if (!h(j)) {
            throw new EOFException();
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean h(long j) throws IOException {
        bb bbVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        do {
            bbVar = this.f5531a;
            if (bbVar.b >= j) {
                return true;
            }
        } while (this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return false;
    }

    @Override // com.huawei.hms.network.embedded.db
    public int h() throws IOException {
        long j;
        i(1L);
        byte j2 = this.f5531a.j(0L);
        if ((j2 & 224) == 192) {
            j = 2;
        } else {
            if ((j2 & 240) != 224) {
                if ((j2 & 248) == 240) {
                    j = 4;
                }
                return this.f5531a.h();
            }
            j = 3;
        }
        i(j);
        return this.f5531a.h();
    }

    @Override // com.huawei.hms.network.embedded.db
    public byte[] g(long j) throws IOException {
        i(j);
        return this.f5531a.g(j);
    }

    @Override // com.huawei.hms.network.embedded.db
    public bb g() {
        return this.f5531a;
    }

    @Override // com.huawei.hms.network.embedded.db
    public String f(long j) throws IOException {
        i(j);
        return this.f5531a.f(j);
    }

    @Override // com.huawei.hms.network.embedded.db
    public long f() throws IOException {
        i(8L);
        return this.f5531a.f();
    }

    @Override // com.huawei.hms.network.embedded.db
    public String e(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == LocationRequestCompat.PASSIVE_INTERVAL ? Long.MAX_VALUE : j + 1;
        long a2 = a((byte) 10, 0L, j2);
        if (a2 != -1) {
            return this.f5531a.k(a2);
        }
        if (j2 < LocationRequestCompat.PASSIVE_INTERVAL && h(j2) && this.f5531a.j(j2 - 1) == 13 && h(1 + j2) && this.f5531a.j(j2) == 10) {
            return this.f5531a.k(j2);
        }
        bb bbVar = new bb();
        bb bbVar2 = this.f5531a;
        bbVar2.a(bbVar, 0L, Math.min(32L, bbVar2.B()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f5531a.B(), j) + " content=" + bbVar.r().d() + (char) 8230);
    }

    @Override // com.huawei.hms.network.embedded.db
    public int e() throws IOException {
        i(4L);
        return this.f5531a.e();
    }

    @Override // com.huawei.hms.network.embedded.db
    public eb d(long j) throws IOException {
        i(j);
        return this.f5531a.d(j);
    }

    @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    public void close() throws IOException {
        if (this.c) {
            return;
        }
        this.c = true;
        this.b.close();
        this.f5531a.s();
    }

    @Override // com.huawei.hms.network.embedded.db
    public long c(eb ebVar) throws IOException {
        return b(ebVar, 0L);
    }

    @Override // com.huawei.hms.network.embedded.db
    public long b(eb ebVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long b = this.f5531a.b(ebVar, j);
            if (b != -1) {
                return b;
            }
            bb bbVar = this.f5531a;
            long j2 = bbVar.b;
            if (this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public long b(eb ebVar) throws IOException {
        return a(ebVar, 0L);
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean a(long j, eb ebVar, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i < 0 || i2 < 0 || ebVar.j() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = i3 + j;
            if (!h(1 + j2) || this.f5531a.j(j2) != ebVar.a(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean a(long j, eb ebVar) throws IOException {
        return a(j, ebVar, 0, ebVar.j());
    }

    @Override // com.huawei.hms.network.embedded.db
    public void a(bb bbVar, long j) throws IOException {
        try {
            i(j);
            this.f5531a.a(bbVar, j);
        } catch (EOFException e) {
            bbVar.a((zb) this.f5531a);
            throw e;
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public String a(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.f5531a.a(this.b);
        return this.f5531a.a(charset);
    }

    @Override // com.huawei.hms.network.embedded.db
    public String a(long j, Charset charset) throws IOException {
        i(j);
        if (charset != null) {
            return this.f5531a.a(j, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // com.huawei.hms.network.embedded.db, com.huawei.hms.network.embedded.cb
    public bb a() {
        return this.f5531a;
    }

    public class a extends InputStream {
        public String toString() {
            return ub.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (ub.this.c) {
                throw new IOException("closed");
            }
            cc.a(bArr.length, i, i2);
            ub ubVar = ub.this;
            bb bbVar = ubVar.f5531a;
            if (bbVar.b == 0 && ubVar.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            return ub.this.f5531a.read(bArr, i, i2);
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            ub ubVar = ub.this;
            if (ubVar.c) {
                throw new IOException("closed");
            }
            bb bbVar = ubVar.f5531a;
            if (bbVar.b == 0 && ubVar.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            return ub.this.f5531a.readByte() & 255;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            ub.this.close();
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            ub ubVar = ub.this;
            if (ubVar.c) {
                throw new IOException("closed");
            }
            return (int) Math.min(ubVar.f5531a.b, 2147483647L);
        }

        public a() {
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(yb ybVar) throws IOException {
        if (ybVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.b.read(this.f5531a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long t = this.f5531a.t();
            if (t > 0) {
                j += t;
                ybVar.write(this.f5531a, t);
            }
        }
        if (this.f5531a.B() <= 0) {
            return j;
        }
        long B = j + this.f5531a.B();
        bb bbVar = this.f5531a;
        ybVar.write(bbVar, bbVar.B());
        return B;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(eb ebVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a2 = this.f5531a.a(ebVar, j);
            if (a2 != -1) {
                return a2;
            }
            bb bbVar = this.f5531a;
            long j2 = bbVar.b;
            if (this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - ebVar.j()) + 1);
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(byte b, long j, long j2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long a2 = this.f5531a.a(b, j, j2);
            if (a2 == -1) {
                bb bbVar = this.f5531a;
                long j3 = bbVar.b;
                if (j3 >= j2 || this.b.read(bbVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    break;
                }
                j = Math.max(j, j3);
            } else {
                return a2;
            }
        }
        return -1L;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(byte b, long j) throws IOException {
        return a(b, j, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(byte b) throws IOException {
        return a(b, 0L, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // com.huawei.hms.network.embedded.db
    public int a(pb pbVar) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        do {
            int a2 = this.f5531a.a(pbVar, true);
            if (a2 == -1) {
                return -1;
            }
            if (a2 != -2) {
                this.f5531a.skip(pbVar.f5423a[a2].j());
                return a2;
            }
        } while (this.b.read(this.f5531a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return -1;
    }

    public ub(zb zbVar) {
        if (zbVar == null) {
            throw new NullPointerException("source == null");
        }
        this.b = zbVar;
    }
}
