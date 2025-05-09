package com.huawei.hms.network.embedded;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.core.location.LocationRequestCompat;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.operation.utils.Constants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.internal.connection.RealConnection;
import okio.Utf8;

/* loaded from: classes9.dex */
public final class bb implements db, cb, Cloneable, ByteChannel {
    public static final byte[] c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public static final int d = 65533;

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    public vb f5191a;
    public long b;

    @Override // com.huawei.hms.network.embedded.db, com.huawei.hms.network.embedded.cb
    public bb a() {
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb c() {
        return this;
    }

    @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    public void close() {
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb d() {
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb, com.huawei.hms.network.embedded.yb, java.io.Flushable
    public void flush() {
    }

    @Override // com.huawei.hms.network.embedded.db
    public bb g() {
        return this;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final eb z() {
        return b("SHA-256");
    }

    public final eb y() {
        return b("SHA-1");
    }

    public List<Integer> x() {
        if (this.f5191a == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        vb vbVar = this.f5191a;
        arrayList.add(Integer.valueOf(vbVar.c - vbVar.b));
        vb vbVar2 = this.f5191a;
        while (true) {
            vbVar2 = vbVar2.f;
            if (vbVar2 == this.f5191a) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(vbVar2.c - vbVar2.b));
        }
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb writeShort(int i) {
        vb e = e(2);
        byte[] bArr = e.f5550a;
        int i2 = e.c;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 1] = (byte) (i & 255);
        e.c = i2 + 2;
        this.b += 2;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb writeLong(long j) {
        vb e = e(8);
        byte[] bArr = e.f5550a;
        int i = e.c;
        bArr[i] = (byte) ((j >>> 56) & 255);
        bArr[i + 1] = (byte) ((j >>> 48) & 255);
        bArr[i + 2] = (byte) ((j >>> 40) & 255);
        bArr[i + 3] = (byte) ((j >>> 32) & 255);
        bArr[i + 4] = (byte) ((j >>> 24) & 255);
        bArr[i + 5] = (byte) ((j >>> 16) & 255);
        bArr[i + 6] = (byte) ((j >>> 8) & 255);
        bArr[i + 7] = (byte) (j & 255);
        e.c = i + 8;
        this.b += 8;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb writeInt(int i) {
        vb e = e(4);
        byte[] bArr = e.f5550a;
        int i2 = e.c;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >>> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
        e.c = i2 + 4;
        this.b += 4;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb writeByte(int i) {
        vb e = e(1);
        byte[] bArr = e.f5550a;
        int i2 = e.c;
        e.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) {
        if (bbVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (bbVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        cc.a(bbVar.b, 0L, j);
        while (j > 0) {
            vb vbVar = bbVar.f5191a;
            if (j < vbVar.c - vbVar.b) {
                vb vbVar2 = this.f5191a;
                vb vbVar3 = vbVar2 != null ? vbVar2.g : null;
                if (vbVar3 != null && vbVar3.e) {
                    if ((vbVar3.c + j) - (vbVar3.d ? 0 : vbVar3.b) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        bbVar.f5191a.a(vbVar3, (int) j);
                        bbVar.b -= j;
                        this.b += j;
                        return;
                    }
                }
                bbVar.f5191a = bbVar.f5191a.a((int) j);
            }
            vb vbVar4 = bbVar.f5191a;
            long j2 = vbVar4.c - vbVar4.b;
            bbVar.f5191a = vbVar4.b();
            vb vbVar5 = this.f5191a;
            if (vbVar5 == null) {
                this.f5191a = vbVar4;
                vbVar4.g = vbVar4;
                vbVar4.f = vbVar4;
            } else {
                vbVar5.g.a(vbVar4).a();
            }
            bbVar.b -= j2;
            this.b += j2;
            j -= j2;
        }
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb write(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i2;
        cc.a(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            vb e = e(1);
            int min = Math.min(i3 - i, 8192 - e.c);
            System.arraycopy(bArr, i, e.f5550a, e.c, min);
            i += min;
            e.c += min;
        }
        this.b += j;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int remaining = byteBuffer.remaining();
        int i = remaining;
        while (i > 0) {
            vb e = e(1);
            int min = Math.min(i, 8192 - e.c);
            byteBuffer.get(e.f5550a, e.c, min);
            i -= min;
            e.c += min;
        }
        this.b += remaining;
        return remaining;
    }

    public final c w() {
        return b(new c());
    }

    public final c v() {
        return a(new c());
    }

    public final eb u() {
        return b("MD5");
    }

    public String toString() {
        return C().toString();
    }

    @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return ac.d;
    }

    public final long t() {
        long j = this.b;
        if (j == 0) {
            return 0L;
        }
        vb vbVar = this.f5191a.g;
        return (vbVar.c >= 8192 || !vbVar.e) ? j : j - (r3 - vbVar.b);
    }

    @Override // com.huawei.hms.network.embedded.db
    public void skip(long j) throws EOFException {
        while (j > 0) {
            if (this.f5191a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, r0.c - r0.b);
            long j2 = min;
            this.b -= j2;
            j -= j2;
            vb vbVar = this.f5191a;
            vbVar.b += min;
            if (vbVar.b == vbVar.c) {
                this.f5191a = vbVar.b();
                wb.a(vbVar);
            }
        }
    }

    public final void s() {
        try {
            skip(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public short readShort() {
        int i;
        int i2;
        long j = this.b;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.b);
        }
        vb vbVar = this.f5191a;
        int i3 = vbVar.b;
        int i4 = vbVar.c;
        if (i4 - i3 < 2) {
            i = (readByte() & 255) << 8;
            i2 = readByte() & 255;
        } else {
            byte[] bArr = vbVar.f5550a;
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            int i6 = i3 + 2;
            byte b3 = bArr[i5];
            this.b = j - 2;
            if (i6 == i4) {
                this.f5191a = vbVar.b();
                wb.a(vbVar);
            } else {
                vbVar.b = i6;
            }
            i = (b2 & 255) << 8;
            i2 = b3 & 255;
        }
        return (short) (i | i2);
    }

    @Override // com.huawei.hms.network.embedded.db
    public long readLong() {
        long j = this.b;
        if (j < 8) {
            throw new IllegalStateException("size < 8: " + this.b);
        }
        vb vbVar = this.f5191a;
        int i = vbVar.b;
        int i2 = vbVar.c;
        if (i2 - i < 8) {
            return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
        }
        byte[] bArr = vbVar.f5550a;
        long j2 = bArr[i];
        long j3 = bArr[i + 1];
        long j4 = bArr[i + 2];
        long j5 = bArr[i + 3];
        long j6 = bArr[i + 4];
        long j7 = bArr[i + 5];
        int i3 = i + 7;
        long j8 = bArr[i + 6];
        int i4 = i + 8;
        long j9 = bArr[i3];
        this.b = j - 8;
        if (i4 == i2) {
            this.f5191a = vbVar.b();
            wb.a(vbVar);
        } else {
            vbVar.b = i4;
        }
        return (255 & j9) | ((j3 & 255) << 48) | ((j2 & 255) << 56) | ((j4 & 255) << 40) | ((j5 & 255) << 32) | ((j6 & 255) << 24) | ((j7 & 255) << 16) | ((j8 & 255) << 8);
    }

    @Override // com.huawei.hms.network.embedded.db
    public int readInt() {
        int i;
        int i2;
        long j = this.b;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        vb vbVar = this.f5191a;
        int i3 = vbVar.b;
        int i4 = vbVar.c;
        if (i4 - i3 < 4) {
            i = ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8);
            i2 = readByte() & 255;
        } else {
            byte[] bArr = vbVar.f5550a;
            byte b2 = bArr[i3];
            byte b3 = bArr[i3 + 1];
            int i5 = i3 + 3;
            byte b4 = bArr[i3 + 2];
            int i6 = i3 + 4;
            byte b5 = bArr[i5];
            this.b = j - 4;
            if (i6 == i4) {
                this.f5191a = vbVar.b();
                wb.a(vbVar);
            } else {
                vbVar.b = i6;
            }
            i = ((b2 & 255) << 24) | ((b3 & 255) << 16) | ((b4 & 255) << 8);
            i2 = b5 & 255;
        }
        return i | i2;
    }

    @Override // com.huawei.hms.network.embedded.db
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read == -1) {
                throw new EOFException();
            }
            i += read;
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public byte readByte() {
        long j = this.b;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        vb vbVar = this.f5191a;
        int i = vbVar.b;
        int i2 = vbVar.c;
        int i3 = i + 1;
        byte b2 = vbVar.f5550a[i];
        this.b = j - 1;
        if (i3 == i2) {
            this.f5191a = vbVar.b();
            wb.a(vbVar);
        } else {
            vbVar.b = i3;
        }
        return b2;
    }

    @Override // com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j) {
        if (bbVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.b;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        bbVar.write(this, j);
        return j;
    }

    @Override // com.huawei.hms.network.embedded.db
    public int read(byte[] bArr, int i, int i2) {
        cc.a(bArr.length, i, i2);
        vb vbVar = this.f5191a;
        if (vbVar == null) {
            return -1;
        }
        int min = Math.min(i2, vbVar.c - vbVar.b);
        System.arraycopy(vbVar.f5550a, vbVar.b, bArr, i, min);
        vbVar.b += min;
        this.b -= min;
        if (vbVar.b == vbVar.c) {
            this.f5191a = vbVar.b();
            wb.a(vbVar);
        }
        return min;
    }

    @Override // com.huawei.hms.network.embedded.db
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        vb vbVar = this.f5191a;
        if (vbVar == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), vbVar.c - vbVar.b);
        byteBuffer.put(vbVar.f5550a, vbVar.b, min);
        vbVar.b += min;
        this.b -= min;
        if (vbVar.b == vbVar.c) {
            this.f5191a = vbVar.b();
            wb.a(vbVar);
        }
        return min;
    }

    @Override // com.huawei.hms.network.embedded.db
    public eb r() {
        return new eb(q());
    }

    @Override // com.huawei.hms.network.embedded.db
    public byte[] q() {
        try {
            return g(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public db peek() {
        return ob.a(new qb(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a6 A[EDGE_INSN: B:46:0x00a6->B:40:0x00a6 BREAK  A[LOOP:0: B:4:0x000f->B:45:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009e  */
    @Override // com.huawei.hms.network.embedded.db
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long p() {
        /*
            r15 = this;
            long r0 = r15.b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lb1
            r0 = 0
            r4 = -7
            r1 = r0
            r5 = r4
            r3 = r2
            r2 = r1
        Lf:
            com.huawei.hms.network.embedded.vb r7 = r15.f5191a
            byte[] r8 = r7.f5550a
            int r9 = r7.b
            int r10 = r7.c
        L17:
            if (r9 >= r10) goto L92
            r11 = r8[r9]
            r12 = 48
            if (r11 < r12) goto L66
            r12 = 57
            if (r11 > r12) goto L66
            int r12 = 48 - r11
            r13 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r13 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r13 < 0) goto L3c
            if (r13 != 0) goto L36
            long r13 = (long) r12
            int r13 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r13 >= 0) goto L36
            goto L3c
        L36:
            r13 = 10
            long r3 = r3 * r13
            long r11 = (long) r12
            long r3 = r3 + r11
            goto L71
        L3c:
            com.huawei.hms.network.embedded.bb r1 = new com.huawei.hms.network.embedded.bb
            r1.<init>()
            com.huawei.hms.network.embedded.bb r1 = r1.a(r3)
            com.huawei.hms.network.embedded.bb r1 = r1.writeByte(r11)
            if (r0 != 0) goto L4e
            r1.readByte()
        L4e:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Number too large: "
            r2.<init>(r3)
            java.lang.String r1 = r1.o()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L66:
            r12 = 45
            r13 = 1
            if (r11 != r12) goto L76
            if (r1 != 0) goto L76
            r11 = 1
            long r5 = r5 - r11
            r0 = r13
        L71:
            int r9 = r9 + 1
            int r1 = r1 + 1
            goto L17
        L76:
            if (r1 == 0) goto L7a
            r2 = r13
            goto L92
        L7a:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected leading [0-9] or '-' character but was 0x"
            r1.<init>(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r11)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L92:
            if (r9 != r10) goto L9e
            com.huawei.hms.network.embedded.vb r8 = r7.b()
            r15.f5191a = r8
            com.huawei.hms.network.embedded.wb.a(r7)
            goto La0
        L9e:
            r7.b = r9
        La0:
            if (r2 != 0) goto La6
            com.huawei.hms.network.embedded.vb r7 = r15.f5191a
            if (r7 != 0) goto Lf
        La6:
            long r5 = r15.b
            long r1 = (long) r1
            long r5 = r5 - r1
            r15.b = r5
            if (r0 == 0) goto Laf
            goto Lb0
        Laf:
            long r3 = -r3
        Lb0:
            return r3
        Lb1:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.bb.p():long");
    }

    @Override // com.huawei.hms.network.embedded.db
    public String o() {
        try {
            return a(this.b, cc.f5207a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public String n() throws EOFException {
        return e(LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // com.huawei.hms.network.embedded.db
    public InputStream m() {
        return new b();
    }

    @Override // com.huawei.hms.network.embedded.db
    @Nullable
    public String l() throws EOFException {
        long a2 = a((byte) 10);
        if (a2 != -1) {
            return k(a2);
        }
        long j = this.b;
        if (j != 0) {
            return f(j);
        }
        return null;
    }

    public String k(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (j(j2) == 13) {
                String f = f(j2);
                skip(2L);
                return f;
            }
        }
        String f2 = f(j);
        skip(1L);
        return f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009e A[EDGE_INSN: B:41:0x009e->B:38:0x009e BREAK  A[LOOP:0: B:4:0x000b->B:40:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0096  */
    @Override // com.huawei.hms.network.embedded.db
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long k() {
        /*
            r14 = this;
            long r0 = r14.b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto La5
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            com.huawei.hms.network.embedded.vb r6 = r14.f5191a
            byte[] r7 = r6.f5550a
            int r8 = r6.b
            int r9 = r6.c
        L13:
            if (r8 >= r9) goto L8a
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L39
        L22:
            r11 = 97
            if (r10 < r11) goto L2d
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2d
            int r11 = r10 + (-97)
            goto L37
        L2d:
            r11 = 65
            if (r10 < r11) goto L6e
            r11 = 70
            if (r10 > r11) goto L6e
            int r11 = r10 + (-65)
        L37:
            int r11 = r11 + 10
        L39:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L49
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L49:
            com.huawei.hms.network.embedded.bb r0 = new com.huawei.hms.network.embedded.bb
            r0.<init>()
            com.huawei.hms.network.embedded.bb r0 = r0.c(r4)
            com.huawei.hms.network.embedded.bb r0 = r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Number too large: "
            r2.<init>(r3)
            java.lang.String r0 = r0.o()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L6e:
            if (r0 == 0) goto L72
            r1 = 1
            goto L8a
        L72:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.<init>(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L8a:
            if (r8 != r9) goto L96
            com.huawei.hms.network.embedded.vb r7 = r6.b()
            r14.f5191a = r7
            com.huawei.hms.network.embedded.wb.a(r6)
            goto L98
        L96:
            r6.b = r8
        L98:
            if (r1 != 0) goto L9e
            com.huawei.hms.network.embedded.vb r6 = r14.f5191a
            if (r6 != 0) goto Lb
        L9e:
            long r1 = r14.b
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.b = r1
            return r4
        La5:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.bb.k():long");
    }

    @Override // com.huawei.hms.network.embedded.db
    public short j() {
        return cc.a(readShort());
    }

    public final byte j(long j) {
        int i;
        cc.a(this.b, j, 1L);
        long j2 = this.b;
        if (j2 - j <= j) {
            long j3 = j - j2;
            vb vbVar = this.f5191a;
            do {
                vbVar = vbVar.g;
                int i2 = vbVar.c;
                i = vbVar.b;
                j3 += i2 - i;
            } while (j3 < 0);
            return vbVar.f5550a[i + ((int) j3)];
        }
        vb vbVar2 = this.f5191a;
        while (true) {
            int i3 = vbVar2.c;
            int i4 = vbVar2.b;
            long j4 = i3 - i4;
            if (j < j4) {
                return vbVar2.f5550a[i4 + ((int) j)];
            }
            j -= j4;
            vbVar2 = vbVar2.f;
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean i() {
        return this.b == 0;
    }

    @Override // com.huawei.hms.network.embedded.db
    public void i(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public int hashCode() {
        vb vbVar = this.f5191a;
        if (vbVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = vbVar.c;
            for (int i3 = vbVar.b; i3 < i2; i3++) {
                i = (i * 31) + vbVar.f5550a[i3];
            }
            vbVar = vbVar.f;
        } while (vbVar != this.f5191a);
        return i;
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean h(long j) {
        return this.b >= j;
    }

    @Override // com.huawei.hms.network.embedded.db
    public int h() throws EOFException {
        int i;
        int i2;
        int i3;
        if (this.b == 0) {
            throw new EOFException();
        }
        byte j = j(0L);
        if ((j & 128) == 0) {
            i = j & Byte.MAX_VALUE;
            i3 = 0;
            i2 = 1;
        } else if ((j & 224) == 192) {
            i = j & 31;
            i2 = 2;
            i3 = 128;
        } else if ((j & 240) == 224) {
            i = j & BaseType.Obj;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((j & 248) != 240) {
                skip(1L);
                return 65533;
            }
            i = j & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j2 = i2;
        if (this.b < j2) {
            throw new EOFException("size < " + i2 + ": " + this.b + " (to read code point prefixed 0x" + Integer.toHexString(j) + Constants.RIGHT_BRACKET_ONLY);
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j3 = i4;
            byte j4 = j(j3);
            if ((j4 & 192) != 128) {
                skip(j3);
                return 65533;
            }
            i = (i << 6) | (j4 & Utf8.REPLACEMENT_BYTE);
        }
        skip(j2);
        if (i > 1114111) {
            return 65533;
        }
        if ((i < 55296 || i > 57343) && i >= i3) {
            return i;
        }
        return 65533;
    }

    @Override // com.huawei.hms.network.embedded.db
    public byte[] g(long j) throws EOFException {
        cc.a(this.b, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    @Override // com.huawei.hms.network.embedded.db
    public String f(long j) throws EOFException {
        return a(j, cc.f5207a);
    }

    public final eb f(eb ebVar) {
        return a("HmacSHA512", ebVar);
    }

    @Override // com.huawei.hms.network.embedded.db
    public long f() {
        return cc.a(readLong());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bb)) {
            return false;
        }
        bb bbVar = (bb) obj;
        long j = this.b;
        if (j != bbVar.b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        vb vbVar = this.f5191a;
        vb vbVar2 = bbVar.f5191a;
        int i = vbVar.b;
        int i2 = vbVar2.b;
        while (j2 < this.b) {
            long min = Math.min(vbVar.c - i, vbVar2.c - i2);
            int i3 = 0;
            while (i3 < min) {
                if (vbVar.f5550a[i] != vbVar2.f5550a[i2]) {
                    return false;
                }
                i3++;
                i++;
                i2++;
            }
            if (i == vbVar.c) {
                vbVar = vbVar.f;
                i = vbVar.b;
            }
            if (i2 == vbVar2.c) {
                vbVar2 = vbVar2.f;
                i2 = vbVar2.b;
            }
            j2 += min;
        }
        return true;
    }

    @Override // com.huawei.hms.network.embedded.db
    public String e(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
        if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
            j2 = j + 1;
        }
        long a2 = a((byte) 10, 0L, j2);
        if (a2 != -1) {
            return k(a2);
        }
        if (j2 < B() && j(j2 - 1) == 13 && j(j2) == 10) {
            return k(j2);
        }
        bb bbVar = new bb();
        a(bbVar, 0L, Math.min(32L, B()));
        throw new EOFException("\\n not found: limit=" + Math.min(B(), j) + " content=" + bbVar.r().d() + (char) 8230);
    }

    public vb e(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        vb vbVar = this.f5191a;
        if (vbVar != null) {
            vb vbVar2 = vbVar.g;
            return (vbVar2.c + i > 8192 || !vbVar2.e) ? vbVar2.a(wb.a()) : vbVar2;
        }
        vb a2 = wb.a();
        this.f5191a = a2;
        a2.g = a2;
        a2.f = a2;
        return a2;
    }

    public final eb e(eb ebVar) {
        return a("HmacSHA256", ebVar);
    }

    @Override // com.huawei.hms.network.embedded.db
    public int e() {
        return cc.a(readInt());
    }

    public final eb d(eb ebVar) {
        return a("HmacSHA1", ebVar);
    }

    @Override // com.huawei.hms.network.embedded.db
    public eb d(long j) throws EOFException {
        return new eb(g(j));
    }

    public final eb d(int i) {
        return i == 0 ? eb.f : new xb(this, i);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public bb m628clone() {
        bb bbVar = new bb();
        if (this.b == 0) {
            return bbVar;
        }
        vb c2 = this.f5191a.c();
        bbVar.f5191a = c2;
        c2.g = c2;
        c2.f = c2;
        vb vbVar = this.f5191a;
        while (true) {
            vbVar = vbVar.f;
            if (vbVar == this.f5191a) {
                bbVar.b = this.b;
                return bbVar;
            }
            bbVar.f5191a.g.a(vbVar.c());
        }
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb c(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        vb e = e(numberOfTrailingZeros);
        byte[] bArr = e.f5550a;
        int i = e.c;
        for (int i2 = (i + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = c[(int) (15 & j)];
            j >>>= 4;
        }
        e.c += numberOfTrailingZeros;
        this.b += numberOfTrailingZeros;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb c(int i) {
        int i2;
        int i3;
        if (i >= 128) {
            if (i < 2048) {
                i3 = (i >> 6) | 192;
            } else {
                if (i < 65536) {
                    if (i >= 55296 && i <= 57343) {
                        writeByte(63);
                        return this;
                    }
                    i2 = (i >> 12) | 224;
                } else {
                    if (i > 1114111) {
                        throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
                    }
                    writeByte((i >> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
                    i2 = ((i >> 12) & 63) | 128;
                }
                writeByte(i2);
                i3 = ((i >> 6) & 63) | 128;
            }
            writeByte(i3);
            i = (i & 63) | 128;
        }
        writeByte(i);
        return this;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long c(eb ebVar) {
        return b(ebVar, 0L);
    }

    @Override // com.huawei.hms.network.embedded.cb
    public OutputStream b() {
        return new a();
    }

    public final bb b(OutputStream outputStream) throws IOException {
        return a(outputStream, this.b);
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb b(long j) {
        return writeLong(cc.a(j));
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb b(int i) {
        return writeShort((int) cc.a((short) i));
    }

    public final c b(c cVar) {
        if (cVar.f5194a != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        cVar.f5194a = this;
        cVar.b = false;
        return cVar;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long b(eb ebVar, long j) {
        int i;
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        vb vbVar = this.f5191a;
        if (vbVar == null) {
            return -1L;
        }
        long j3 = this.b;
        if (j3 - j < j) {
            while (j3 > j) {
                vbVar = vbVar.g;
                j3 -= vbVar.c - vbVar.b;
            }
        } else {
            while (true) {
                long j4 = (vbVar.c - vbVar.b) + j2;
                if (j4 >= j) {
                    break;
                }
                vbVar = vbVar.f;
                j2 = j4;
            }
            j3 = j2;
        }
        if (ebVar.j() == 2) {
            byte a2 = ebVar.a(0);
            byte a3 = ebVar.a(1);
            while (j3 < this.b) {
                byte[] bArr = vbVar.f5550a;
                i = (int) ((vbVar.b + j) - j3);
                int i2 = vbVar.c;
                while (i < i2) {
                    byte b2 = bArr[i];
                    if (b2 != a2 && b2 != a3) {
                        i++;
                    }
                    return (i - vbVar.b) + j3;
                }
                j3 += vbVar.c - vbVar.b;
                vbVar = vbVar.f;
                j = j3;
            }
            return -1L;
        }
        byte[] e = ebVar.e();
        while (j3 < this.b) {
            byte[] bArr2 = vbVar.f5550a;
            i = (int) ((vbVar.b + j) - j3);
            int i3 = vbVar.c;
            while (i < i3) {
                byte b3 = bArr2[i];
                for (byte b4 : e) {
                    if (b3 == b4) {
                        return (i - vbVar.b) + j3;
                    }
                }
                i++;
            }
            j3 += vbVar.c - vbVar.b;
            vbVar = vbVar.f;
            j = j3;
        }
        return -1L;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long b(eb ebVar) throws IOException {
        return a(ebVar, 0L);
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean a(long j, eb ebVar, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.b - j < i2 || ebVar.j() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (j(i3 + j) != ebVar.a(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.hms.network.embedded.db
    public boolean a(long j, eb ebVar) {
        return a(j, ebVar, 0, ebVar.j());
    }

    @Override // com.huawei.hms.network.embedded.db
    public void a(bb bbVar, long j) throws EOFException {
        long j2 = this.b;
        if (j2 >= j) {
            bbVar.write(this, j);
        } else {
            bbVar.write(this, j2);
            throw new EOFException();
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public String a(Charset charset) {
        try {
            return a(this.b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public String a(long j, Charset charset) throws EOFException {
        cc.a(this.b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        vb vbVar = this.f5191a;
        int i = vbVar.b;
        if (i + j > vbVar.c) {
            return new String(g(j), charset);
        }
        String str = new String(vbVar.f5550a, i, (int) j, charset);
        vbVar.b = (int) (vbVar.b + j);
        this.b -= j;
        if (vbVar.b == vbVar.c) {
            this.f5191a = vbVar.b();
            wb.a(vbVar);
        }
        return str;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public cb a(zb zbVar, long j) throws IOException {
        while (j > 0) {
            long read = zbVar.read(this, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
        }
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(String str, Charset charset) {
        return a(str, 0, str.length(), charset);
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (charset.equals(cc.f5207a)) {
            return a(str, i, i2);
        }
        byte[] bytes = str.substring(i, i2).getBytes(charset);
        return write(bytes, 0, bytes.length);
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(String str, int i, int i2) {
        char charAt;
        int i3;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        while (i < i2) {
            char charAt2 = str.charAt(i);
            if (charAt2 < 128) {
                vb e = e(1);
                byte[] bArr = e.f5550a;
                int i4 = e.c - i;
                int min = Math.min(i2, 8192 - i4);
                int i5 = i + 1;
                bArr[i + i4] = (byte) charAt2;
                while (true) {
                    i = i5;
                    if (i >= min || (charAt = str.charAt(i)) >= 128) {
                        break;
                    }
                    i5 = i + 1;
                    bArr[i + i4] = (byte) charAt;
                }
                int i6 = e.c;
                int i7 = (i4 + i) - i6;
                e.c = i6 + i7;
                this.b += i7;
            } else {
                if (charAt2 < 2048) {
                    i3 = (charAt2 >> 6) | 192;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    writeByte((charAt2 >> '\f') | 224);
                    i3 = ((charAt2 >> 6) & 63) | 128;
                } else {
                    int i8 = i + 1;
                    char charAt3 = i8 < i2 ? str.charAt(i8) : (char) 0;
                    if (charAt2 > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                        writeByte(63);
                        i = i8;
                    } else {
                        int i9 = (((charAt2 & 10239) << 10) | (9215 & charAt3)) + 65536;
                        writeByte((i9 >> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
                        writeByte(((i9 >> 12) & 63) | 128);
                        writeByte(((i9 >> 6) & 63) | 128);
                        writeByte((i9 & 63) | 128);
                        i += 2;
                    }
                }
                writeByte(i3);
                writeByte((charAt2 & '?') | 128);
                i++;
            }
        }
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(String str) {
        return a(str, 0, str.length());
    }

    public final bb a(OutputStream outputStream, long j, long j2) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        cc.a(this.b, j, j2);
        if (j2 == 0) {
            return this;
        }
        vb vbVar = this.f5191a;
        while (true) {
            long j3 = vbVar.c - vbVar.b;
            if (j < j3) {
                break;
            }
            j -= j3;
            vbVar = vbVar.f;
        }
        while (j2 > 0) {
            int min = (int) Math.min(vbVar.c - r8, j2);
            outputStream.write(vbVar.f5550a, (int) (vbVar.b + j), min);
            j2 -= min;
            vbVar = vbVar.f;
            j = 0;
        }
        return this;
    }

    public final bb a(OutputStream outputStream, long j) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        cc.a(this.b, 0L, j);
        vb vbVar = this.f5191a;
        while (j > 0) {
            int min = (int) Math.min(j, vbVar.c - vbVar.b);
            outputStream.write(vbVar.f5550a, vbVar.b, min);
            vbVar.b += min;
            long j2 = min;
            this.b -= j2;
            j -= j2;
            if (vbVar.b == vbVar.c) {
                vb b2 = vbVar.b();
                this.f5191a = b2;
                wb.a(vbVar);
                vbVar = b2;
            }
        }
        return this;
    }

    public final bb a(OutputStream outputStream) throws IOException {
        return a(outputStream, 0L, this.b);
    }

    public final bb a(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            a(inputStream, j, false);
            return this;
        }
        throw new IllegalArgumentException("byteCount < 0: " + j);
    }

    public final bb a(InputStream inputStream) throws IOException {
        a(inputStream, LocationRequestCompat.PASSIVE_INTERVAL, true);
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(eb ebVar) {
        if (ebVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        ebVar.a(this);
        return this;
    }

    public final bb a(bb bbVar, long j, long j2) {
        if (bbVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        cc.a(this.b, j, j2);
        if (j2 == 0) {
            return this;
        }
        bbVar.b += j2;
        vb vbVar = this.f5191a;
        while (true) {
            long j3 = vbVar.c - vbVar.b;
            if (j < j3) {
                break;
            }
            j -= j3;
            vbVar = vbVar.f;
        }
        while (j2 > 0) {
            vb c2 = vbVar.c();
            c2.b = (int) (c2.b + j);
            c2.c = Math.min(c2.b + ((int) j2), c2.c);
            vb vbVar2 = bbVar.f5191a;
            if (vbVar2 == null) {
                c2.g = c2;
                c2.f = c2;
                bbVar.f5191a = c2;
            } else {
                vbVar2.g.a(c2);
            }
            j2 -= c2.c - c2.b;
            vbVar = vbVar.f;
            j = 0;
        }
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(long j) {
        boolean z;
        if (j == 0) {
            return writeByte(48);
        }
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return a("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
        }
        int i = j < 100000000 ? j < PreConnectManager.CONNECT_INTERNAL ? j < 100 ? j < 10 ? 1 : 2 : j < 1000 ? 3 : 4 : j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8 : j < 1000000000000L ? j < RealConnection.IDLE_CONNECTION_HEALTHY_NS ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        if (z) {
            i++;
        }
        vb e = e(i);
        byte[] bArr = e.f5550a;
        int i2 = e.c + i;
        while (j != 0) {
            i2--;
            bArr[i2] = c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        e.c += i;
        this.b += i;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public bb a(int i) {
        return writeInt(cc.a(i));
    }

    public final c a(c cVar) {
        if (cVar.f5194a != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        cVar.f5194a = this;
        cVar.b = true;
        return cVar;
    }

    @Override // com.huawei.hms.network.embedded.cb
    public long a(zb zbVar) throws IOException {
        if (zbVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = zbVar.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(yb ybVar) throws IOException {
        long j = this.b;
        if (j > 0) {
            ybVar.write(this, j);
        }
        return j;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(eb ebVar, long j) throws IOException {
        byte[] bArr;
        if (ebVar.j() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        vb vbVar = this.f5191a;
        long j3 = -1;
        if (vbVar == null) {
            return -1L;
        }
        long j4 = this.b;
        if (j4 - j < j) {
            while (j4 > j) {
                vbVar = vbVar.g;
                j4 -= vbVar.c - vbVar.b;
            }
        } else {
            while (true) {
                long j5 = (vbVar.c - vbVar.b) + j2;
                if (j5 >= j) {
                    break;
                }
                vbVar = vbVar.f;
                j2 = j5;
            }
            j4 = j2;
        }
        byte a2 = ebVar.a(0);
        int j6 = ebVar.j();
        long j7 = 1 + (this.b - j6);
        long j8 = j;
        vb vbVar2 = vbVar;
        long j9 = j4;
        while (j9 < j7) {
            byte[] bArr2 = vbVar2.f5550a;
            int min = (int) Math.min(vbVar2.c, (vbVar2.b + j7) - j9);
            int i = (int) ((vbVar2.b + j8) - j9);
            while (i < min) {
                if (bArr2[i] == a2) {
                    bArr = bArr2;
                    if (a(vbVar2, i + 1, ebVar, 1, j6)) {
                        return (i - vbVar2.b) + j9;
                    }
                } else {
                    bArr = bArr2;
                }
                i++;
                bArr2 = bArr;
            }
            j9 += vbVar2.c - vbVar2.b;
            vbVar2 = vbVar2.f;
            j8 = j9;
            j3 = -1;
        }
        return j3;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(byte b2, long j, long j2) {
        vb vbVar;
        long j3 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.b), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j4 = this.b;
        long j5 = j2 > j4 ? j4 : j2;
        if (j == j5 || (vbVar = this.f5191a) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                vbVar = vbVar.g;
                j4 -= vbVar.c - vbVar.b;
            }
        } else {
            while (true) {
                long j6 = (vbVar.c - vbVar.b) + j3;
                if (j6 >= j) {
                    break;
                }
                vbVar = vbVar.f;
                j3 = j6;
            }
            j4 = j3;
        }
        long j7 = j;
        while (j4 < j5) {
            byte[] bArr = vbVar.f5550a;
            int min = (int) Math.min(vbVar.c, (vbVar.b + j5) - j4);
            for (int i = (int) ((vbVar.b + j7) - j4); i < min; i++) {
                if (bArr[i] == b2) {
                    return (i - vbVar.b) + j4;
                }
            }
            j4 += vbVar.c - vbVar.b;
            vbVar = vbVar.f;
            j7 = j4;
        }
        return -1L;
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(byte b2, long j) {
        return a(b2, j, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // com.huawei.hms.network.embedded.db
    public long a(byte b2) {
        return a(b2, 0L, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0054, code lost:
    
        if (r19 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0056, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0057, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(com.huawei.hms.network.embedded.pb r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            com.huawei.hms.network.embedded.vb r2 = r1.f5191a
            r3 = -2
            if (r2 != 0) goto L13
            if (r19 == 0) goto Lc
            return r3
        Lc:
            com.huawei.hms.network.embedded.eb r2 = com.huawei.hms.network.embedded.eb.f
            int r0 = r0.indexOf(r2)
            return r0
        L13:
            byte[] r4 = r2.f5550a
            int r5 = r2.b
            int r6 = r2.c
            int[] r0 = r0.b
            r8 = -1
            r10 = r2
            r11 = r8
            r9 = 0
        L1f:
            int r12 = r9 + 1
            r13 = r0[r9]
            int r9 = r9 + 2
            r12 = r0[r12]
            if (r12 == r8) goto L2a
            r11 = r12
        L2a:
            if (r10 != 0) goto L2d
            goto L54
        L2d:
            r12 = 0
            if (r13 >= 0) goto L6e
            r14 = r9
        L31:
            int r15 = r5 + 1
            r5 = r4[r5]
            int r7 = r14 + 1
            r5 = r5 & 255(0xff, float:3.57E-43)
            r14 = r0[r14]
            if (r5 == r14) goto L3e
            return r11
        L3e:
            int r5 = r13 * (-1)
            int r5 = r5 + r9
            if (r7 != r5) goto L45
            r5 = 1
            goto L46
        L45:
            r5 = 0
        L46:
            if (r15 != r6) goto L61
            com.huawei.hms.network.embedded.vb r4 = r10.f
            int r6 = r4.b
            byte[] r10 = r4.f5550a
            int r14 = r4.c
            if (r4 != r2) goto L5b
            if (r5 != 0) goto L58
        L54:
            if (r19 == 0) goto L57
            return r3
        L57:
            return r11
        L58:
            r4 = r10
            r10 = r12
            goto L63
        L5b:
            r16 = r10
            r10 = r4
            r4 = r16
            goto L63
        L61:
            r14 = r6
            r6 = r15
        L63:
            if (r5 == 0) goto L6a
            r5 = r0[r7]
            r3 = r6
            r6 = r14
            goto L90
        L6a:
            r5 = r6
            r6 = r14
            r14 = r7
            goto L31
        L6e:
            int r7 = r5 + 1
            r5 = r4[r5]
            r14 = r9
        L73:
            int r15 = r9 + r13
            if (r14 != r15) goto L78
            return r11
        L78:
            r15 = r5 & 255(0xff, float:3.57E-43)
            r3 = r0[r14]
            if (r15 != r3) goto L97
            int r14 = r14 + r13
            r5 = r0[r14]
            if (r7 != r6) goto L8f
            com.huawei.hms.network.embedded.vb r10 = r10.f
            int r3 = r10.b
            byte[] r4 = r10.f5550a
            int r6 = r10.c
            if (r10 != r2) goto L90
            r10 = r12
            goto L90
        L8f:
            r3 = r7
        L90:
            if (r5 < 0) goto L93
            return r5
        L93:
            int r9 = -r5
            r5 = r3
            r3 = -2
            goto L1f
        L97:
            int r14 = r14 + 1
            r3 = -2
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.bb.a(com.huawei.hms.network.embedded.pb, boolean):int");
    }

    @Override // com.huawei.hms.network.embedded.db
    public int a(pb pbVar) {
        int a2 = a(pbVar, false);
        if (a2 == -1) {
            return -1;
        }
        try {
            skip(pbVar.f5423a[a2].j());
            return a2;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public final eb C() {
        long j = this.b;
        if (j <= 2147483647L) {
            return d((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
    }

    public final long B() {
        return this.b;
    }

    public class a extends OutputStream {
        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            bb.this.write(bArr, i, i2);
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            bb.this.writeByte((int) ((byte) i));
        }

        public String toString() {
            return bb.this + ".outputStream()";
        }

        public a() {
        }
    }

    public class b extends InputStream {
        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        public String toString() {
            return bb.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            return bb.this.read(bArr, i, i2);
        }

        @Override // java.io.InputStream
        public int read() {
            bb bbVar = bb.this;
            if (bbVar.b > 0) {
                return bbVar.readByte() & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(bb.this.b, 2147483647L);
        }

        public b() {
        }
    }

    public static final class c implements Closeable {

        /* renamed from: a, reason: collision with root package name */
        public bb f5194a;
        public boolean b;
        public vb c;
        public byte[] e;
        public long d = -1;
        public int f = -1;
        public int g = -1;

        public final int s() {
            long j = this.d;
            if (j != this.f5194a.b) {
                return k(j == -1 ? 0L : j + (this.g - this.f));
            }
            throw new IllegalStateException();
        }

        public final int k(long j) {
            if (j >= -1) {
                bb bbVar = this.f5194a;
                long j2 = bbVar.b;
                if (j <= j2) {
                    if (j == -1 || j == j2) {
                        this.c = null;
                        this.d = j;
                        this.e = null;
                        this.f = -1;
                        this.g = -1;
                        return -1;
                    }
                    vb vbVar = bbVar.f5191a;
                    vb vbVar2 = this.c;
                    long j3 = 0;
                    if (vbVar2 != null) {
                        long j4 = this.d - (this.f - vbVar2.b);
                        if (j4 > j) {
                            j2 = j4;
                        } else {
                            j3 = j4;
                            vbVar2 = vbVar;
                            vbVar = vbVar2;
                        }
                    } else {
                        vbVar2 = vbVar;
                    }
                    if (j2 - j > j - j3) {
                        while (true) {
                            long j5 = (vbVar.c - vbVar.b) + j3;
                            if (j < j5) {
                                break;
                            }
                            vbVar = vbVar.f;
                            j3 = j5;
                        }
                    } else {
                        while (j2 > j) {
                            vbVar2 = vbVar2.g;
                            j2 -= vbVar2.c - vbVar2.b;
                        }
                        vbVar = vbVar2;
                        j3 = j2;
                    }
                    if (this.b && vbVar.d) {
                        vb d = vbVar.d();
                        bb bbVar2 = this.f5194a;
                        if (bbVar2.f5191a == vbVar) {
                            bbVar2.f5191a = d;
                        }
                        vbVar = vbVar.a(d);
                        vbVar.g.b();
                    }
                    this.c = vbVar;
                    this.d = j;
                    this.e = vbVar.f5550a;
                    this.f = vbVar.b + ((int) (j - j3));
                    int i = vbVar.c;
                    this.g = i;
                    return i - this.f;
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j), Long.valueOf(this.f5194a.b)));
        }

        public final long j(long j) {
            bb bbVar = this.f5194a;
            if (bbVar == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.b) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long j2 = bbVar.b;
            if (j <= j2) {
                if (j < 0) {
                    throw new IllegalArgumentException("newSize < 0: " + j);
                }
                long j3 = j2 - j;
                while (true) {
                    if (j3 <= 0) {
                        break;
                    }
                    bb bbVar2 = this.f5194a;
                    vb vbVar = bbVar2.f5191a.g;
                    int i = vbVar.c;
                    long j4 = i - vbVar.b;
                    if (j4 > j3) {
                        vbVar.c = (int) (i - j3);
                        break;
                    }
                    bbVar2.f5191a = vbVar.b();
                    wb.a(vbVar);
                    j3 -= j4;
                }
                this.c = null;
                this.d = j;
                this.e = null;
                this.f = -1;
                this.g = -1;
            } else if (j > j2) {
                long j5 = j - j2;
                boolean z = true;
                while (j5 > 0) {
                    vb e = this.f5194a.e(1);
                    int min = (int) Math.min(j5, 8192 - e.c);
                    e.c += min;
                    j5 -= min;
                    if (z) {
                        this.c = e;
                        this.d = j2;
                        this.e = e.f5550a;
                        int i2 = e.c;
                        this.f = i2 - min;
                        this.g = i2;
                        z = false;
                    }
                }
            }
            this.f5194a.b = j;
            return j2;
        }

        public final long d(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("minByteCount <= 0: " + i);
            }
            if (i > 8192) {
                throw new IllegalArgumentException("minByteCount > Segment.SIZE: " + i);
            }
            bb bbVar = this.f5194a;
            if (bbVar == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.b) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
            }
            long j = bbVar.b;
            vb e = bbVar.e(i);
            int i2 = 8192 - e.c;
            e.c = 8192;
            long j2 = i2;
            this.f5194a.b = j + j2;
            this.c = e;
            this.d = j;
            this.e = e.f5550a;
            this.f = 8192 - i2;
            this.g = 8192;
            return j2;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f5194a == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.f5194a = null;
            this.c = null;
            this.d = -1L;
            this.e = null;
            this.f = -1;
            this.g = -1;
        }
    }

    public final eb A() {
        return b("SHA-512");
    }

    private eb b(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            vb vbVar = this.f5191a;
            if (vbVar != null) {
                messageDigest.update(vbVar.f5550a, this.f5191a.b, this.f5191a.c - this.f5191a.b);
                vb vbVar2 = this.f5191a;
                while (true) {
                    vbVar2 = vbVar2.f;
                    if (vbVar2 == this.f5191a) {
                        break;
                    }
                    messageDigest.update(vbVar2.f5550a, vbVar2.b, vbVar2.c - vbVar2.b);
                }
            }
            return eb.e(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private boolean a(vb vbVar, int i, eb ebVar, int i2, int i3) {
        int i4 = vbVar.c;
        byte[] bArr = vbVar.f5550a;
        while (i2 < i3) {
            if (i == i4) {
                vbVar = vbVar.f;
                byte[] bArr2 = vbVar.f5550a;
                bArr = bArr2;
                i = vbVar.b;
                i4 = vbVar.c;
            }
            if (bArr[i] != ebVar.a(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    private void a(InputStream inputStream, long j, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j <= 0 && !z) {
                return;
            }
            vb e = e(1);
            int read = inputStream.read(e.f5550a, e.c, (int) Math.min(j, 8192 - e.c));
            if (read == -1) {
                if (e.b == e.c) {
                    this.f5191a = e.b();
                    wb.a(e);
                }
                if (!z) {
                    throw new EOFException();
                }
                return;
            }
            e.c += read;
            long j2 = read;
            this.b += j2;
            j -= j2;
        }
    }

    private eb a(String str, eb ebVar) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(ebVar.m(), str));
            vb vbVar = this.f5191a;
            if (vbVar != null) {
                mac.update(vbVar.f5550a, this.f5191a.b, this.f5191a.c - this.f5191a.b);
                vb vbVar2 = this.f5191a;
                while (true) {
                    vbVar2 = vbVar2.f;
                    if (vbVar2 == this.f5191a) {
                        break;
                    }
                    mac.update(vbVar2.f5550a, vbVar2.b, vbVar2.c - vbVar2.b);
                }
            }
            return eb.e(mac.doFinal());
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }
}
