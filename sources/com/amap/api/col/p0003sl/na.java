package com.amap.api.col.p0003sl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class na {
    static final Charset c = Charset.forName("UTF-8");
    static final /* synthetic */ boolean p = true;

    /* renamed from: a, reason: collision with root package name */
    ByteBuffer f1348a;
    int b;
    int d;
    int[] e;
    int f;
    boolean g;
    boolean h;
    int i;
    int[] j;
    int k;
    int l;
    boolean m;
    CharsetEncoder n;
    ByteBuffer o;

    private na() {
        this.d = 1;
        this.e = null;
        this.f = 0;
        this.g = false;
        this.h = false;
        this.j = new int[16];
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = c.newEncoder();
        this.b = 1024;
        this.f1348a = d(1024);
    }

    public na(ByteBuffer byteBuffer) {
        this.d = 1;
        this.e = null;
        this.f = 0;
        this.g = false;
        this.h = false;
        this.j = new int[16];
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = c.newEncoder();
        a(byteBuffer);
    }

    public final na a(ByteBuffer byteBuffer) {
        this.f1348a = byteBuffer;
        byteBuffer.clear();
        this.f1348a.order(ByteOrder.LITTLE_ENDIAN);
        this.d = 1;
        this.b = this.f1348a.capacity();
        this.f = 0;
        this.g = false;
        this.h = false;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        return this;
    }

    private static ByteBuffer d(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        return allocate;
    }

    private static ByteBuffer b(ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity();
        if (((-1073741824) & capacity) != 0) {
            throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
        }
        int i = capacity << 1;
        byteBuffer.position(0);
        ByteBuffer d = d(i);
        d.position(i - capacity);
        d.put(byteBuffer);
        return d;
    }

    private int d() {
        return this.f1348a.capacity() - this.b;
    }

    private void e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.f1348a;
            int i3 = this.b - 1;
            this.b = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    private void c(int i, int i2) {
        if (i > this.d) {
            this.d = i;
        }
        int i3 = ((~((this.f1348a.capacity() - this.b) + i2)) + 1) & (i - 1);
        while (this.b < i3 + i + i2) {
            int capacity = this.f1348a.capacity();
            ByteBuffer b = b(this.f1348a);
            this.f1348a = b;
            this.b += b.capacity() - capacity;
        }
        e(i3);
    }

    private void b(boolean z) {
        ByteBuffer byteBuffer = this.f1348a;
        int i = this.b - 1;
        this.b = i;
        byteBuffer.put(i, z ? (byte) 1 : (byte) 0);
    }

    private void b(byte b) {
        ByteBuffer byteBuffer = this.f1348a;
        int i = this.b - 1;
        this.b = i;
        byteBuffer.put(i, b);
    }

    private void a(short s) {
        ByteBuffer byteBuffer = this.f1348a;
        int i = this.b - 2;
        this.b = i;
        byteBuffer.putShort(i, s);
    }

    private void f(int i) {
        ByteBuffer byteBuffer = this.f1348a;
        int i2 = this.b - 4;
        this.b = i2;
        byteBuffer.putInt(i2, i);
    }

    private void a(long j) {
        ByteBuffer byteBuffer = this.f1348a;
        int i = this.b - 8;
        this.b = i;
        byteBuffer.putLong(i, j);
    }

    private void c(boolean z) {
        c(1, 0);
        b(z);
    }

    public final void a(byte b) {
        c(1, 0);
        b(b);
    }

    private void b(short s) {
        c(2, 0);
        a(s);
    }

    private void g(int i) {
        c(4, 0);
        f(i);
    }

    private void b(long j) {
        c(8, 0);
        a(j);
    }

    public final void a(int i) {
        c(4, 0);
        if (!p && i > d()) {
            throw new AssertionError();
        }
        f((d() - i) + 4);
    }

    public final void a(int i, int i2, int i3) {
        f();
        this.l = i2;
        int i4 = i * i2;
        c(4, i4);
        c(i3, i4);
        this.g = true;
    }

    public final int a() {
        if (!this.g) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.g = false;
        f(this.l);
        return d();
    }

    public int a(CharSequence charSequence) {
        int length = (int) (charSequence.length() * this.n.maxBytesPerChar());
        ByteBuffer byteBuffer = this.o;
        if (byteBuffer == null || byteBuffer.capacity() < length) {
            this.o = ByteBuffer.allocate(Math.max(128, length));
        }
        this.o.clear();
        CoderResult encode = this.n.encode(charSequence instanceof CharBuffer ? (CharBuffer) charSequence : CharBuffer.wrap(charSequence), this.o, true);
        if (encode.isError()) {
            try {
                encode.throwException();
            } catch (CharacterCodingException e) {
                throw new Error(e);
            }
        }
        this.o.flip();
        return c(this.o);
    }

    private int c(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        a((byte) 0);
        a(1, remaining, 1);
        ByteBuffer byteBuffer2 = this.f1348a;
        int i = this.b - remaining;
        this.b = i;
        byteBuffer2.position(i);
        this.f1348a.put(byteBuffer);
        return a();
    }

    private void e() {
        if (!this.h) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
    }

    private void f() {
        if (this.g) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    public final void b(int i) {
        f();
        int[] iArr = this.e;
        if (iArr == null || iArr.length < i) {
            this.e = new int[i];
        }
        this.f = i;
        Arrays.fill(this.e, 0, i, 0);
        this.g = true;
        this.i = d();
    }

    public final void a(boolean z) {
        if (this.m || z) {
            c(z);
            h(0);
        }
    }

    public final void a(int i, byte b) {
        if (this.m || b != 0) {
            a(b);
            h(i);
        }
    }

    public final void a(int i, short s) {
        if (this.m || s != 0) {
            b(s);
            h(i);
        }
    }

    public final void a(int i, int i2) {
        if (this.m || i2 != 0) {
            g(i2);
            h(i);
        }
    }

    public final void a(int i, long j) {
        if (this.m || j != 0) {
            b(j);
            h(i);
        }
    }

    public final void b(int i, int i2) {
        if (this.m || i2 != 0) {
            a(i2);
            h(i);
        }
    }

    private void h(int i) {
        this.e[i] = d();
    }

    public final int b() {
        int i;
        int i2;
        if (this.e == null || !this.g) {
            throw new AssertionError("FlatBuffers: endObject called without startObject");
        }
        g(0);
        int d = d();
        for (int i3 = this.f - 1; i3 >= 0; i3--) {
            int i4 = this.e[i3];
            b((short) (i4 != 0 ? d - i4 : 0));
        }
        b((short) (d - this.i));
        b((short) ((this.f + 2) * 2));
        int i5 = 0;
        loop1: while (true) {
            if (i5 >= this.k) {
                i = 0;
                break;
            }
            int capacity = this.f1348a.capacity() - this.j[i5];
            int i6 = this.b;
            short s = this.f1348a.getShort(capacity);
            if (s == this.f1348a.getShort(i6)) {
                for (2; i2 < s; i2 + 2) {
                    i2 = this.f1348a.getShort(capacity + i2) == this.f1348a.getShort(i6 + i2) ? i2 + 2 : 2;
                }
                i = this.j[i5];
                break loop1;
            }
            i5++;
        }
        if (i != 0) {
            int capacity2 = this.f1348a.capacity() - d;
            this.b = capacity2;
            this.f1348a.putInt(capacity2, i - d);
        } else {
            int i7 = this.k;
            int[] iArr = this.j;
            if (i7 == iArr.length) {
                this.j = Arrays.copyOf(iArr, i7 * 2);
            }
            int[] iArr2 = this.j;
            int i8 = this.k;
            this.k = i8 + 1;
            iArr2[i8] = d();
            ByteBuffer byteBuffer = this.f1348a;
            byteBuffer.putInt(byteBuffer.capacity() - d, d() - d);
        }
        this.g = false;
        return d;
    }

    public final void c(int i) {
        c(this.d, 4);
        a(i);
        this.f1348a.position(this.b);
        this.h = true;
    }

    private byte[] d(int i, int i2) {
        e();
        byte[] bArr = new byte[i2];
        this.f1348a.position(i);
        this.f1348a.get(bArr);
        return bArr;
    }

    public final byte[] c() {
        return d(this.b, this.f1348a.capacity() - this.b);
    }
}
