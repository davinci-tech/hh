package defpackage;

import androidx.core.view.ViewCompat;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes7.dex */
public final class vbo {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f17649a;
    private int c;
    private int d;
    private final boolean g;
    private byte i;
    public static final AtomicLong e = new AtomicLong();
    public static final AtomicLong b = new AtomicLong();

    public vbo() {
        this(32, false);
    }

    public vbo(int i) {
        this(i, false);
    }

    public vbo(int i, boolean z) {
        this.g = z;
        this.f17649a = new byte[i];
        h();
    }

    public void c(long j, int i) {
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException(String.format("Number of bits must be 1 to 64, not %d", Integer.valueOf(i)));
        }
        if (i < 64 && (j >> i) != 0) {
            throw new IllegalArgumentException(String.format("Truncating value %d to %d-bit integer", Long.valueOf(j), Integer.valueOf(i)));
        }
        int i2 = (i + 7) / 8;
        if ((i & 7) == 0 && !e()) {
            b(i2);
            while (true) {
                i -= 8;
                if (i < 0) {
                    return;
                } else {
                    c((byte) (j >> i));
                }
            }
        } else {
            b(i2 + 1);
            while (true) {
                i--;
                if (i < 0) {
                    return;
                }
                if (((j >> i) & 1) != 0) {
                    this.i = (byte) (this.i | (1 << this.d));
                }
                int i3 = this.d - 1;
                this.d = i3;
                if (i3 < 0) {
                    i();
                }
            }
        }
    }

    public void b(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException(String.format("Number of bits must be 1 to 32, not %d", Integer.valueOf(i2)));
        }
        if (i2 < 32 && (i >> i2) != 0) {
            throw new IllegalArgumentException(String.format("Truncating value %d to %d-bit integer", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = (i2 + 7) / 8;
        if ((i2 & 7) == 0 && !e()) {
            b(i3);
            while (true) {
                i2 -= 8;
                if (i2 < 0) {
                    return;
                } else {
                    c((byte) (i >> i2));
                }
            }
        } else {
            b(i3 + 1);
            while (true) {
                i2--;
                if (i2 < 0) {
                    return;
                }
                if (((i >> i2) & 1) != 0) {
                    this.i = (byte) (this.i | (1 << this.d));
                }
                int i4 = this.d - 1;
                this.d = i4;
                if (i4 < 0) {
                    i();
                }
            }
        }
    }

    public void e(int i, int i2, int i3) {
        int i4 = ((i3 + 7) / 8) + i;
        int i5 = this.c;
        if (i4 > i5) {
            b(i4 - i5);
        }
        int i6 = this.c;
        int i7 = this.d;
        byte b2 = this.i;
        h();
        this.c = i;
        b(i2, i3);
        if (this.c < i6) {
            this.c = i6;
            this.i = b2;
            this.d = i7;
        }
    }

    public void d(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        e(bArr, 0, bArr.length);
    }

    public void e(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 == 0) {
            return;
        }
        if (!e()) {
            d(bArr, i, i2);
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            b(bArr[i3 + i] & 255, 8);
        }
    }

    public void d(byte b2) {
        if (e()) {
            b(b2 & 255, 8);
        } else {
            b(1);
            c(b2);
        }
    }

    public void d(byte[] bArr, int i) {
        int d = d(i);
        int a2 = a(d);
        if (bArr == null) {
            b(a2, d);
            return;
        }
        if (a2 == bArr.length) {
            throw new IllegalArgumentException(bArr.length + " bytes is too large for " + i + "!");
        }
        if (i < d && (bArr.length >> i) != 0) {
            throw new IllegalArgumentException(String.format("Truncating value %d to %d-bit integer", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        }
        b(bArr.length, d);
        d(bArr);
    }

    public void b(vbj vbjVar, int i) {
        d(vbjVar == null ? null : vbjVar.c(), i);
    }

    public int c(int i) {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("Number of bits must be multiple of 8, not " + i + "!");
        }
        if (e()) {
            throw new IllegalStateException("bits are pending!");
        }
        int i2 = this.c;
        int i3 = i / 8;
        b(i3);
        this.c += i3;
        return i2;
    }

    public byte[] c() {
        i();
        byte[] bArr = this.f17649a;
        int length = bArr.length;
        int i = this.c;
        if (length == i) {
            this.f17649a = vbj.c;
            b.incrementAndGet();
        } else {
            bArr = Arrays.copyOf(bArr, i);
            if (this.g) {
                Arrays.fill(this.f17649a, 0, this.c, (byte) 0);
            }
            e.incrementAndGet();
        }
        this.c = 0;
        return bArr;
    }

    public void e(vbo vboVar) {
        vboVar.i();
        d(vboVar.f17649a, 0, vboVar.c);
    }

    public void e(OutputStream outputStream) throws IOException {
        i();
        outputStream.write(this.f17649a, 0, this.c);
        this.c = 0;
    }

    public void d(int i, int i2) {
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("Number of bits must be multiple of 8, not " + i2 + "!");
        }
        e(i, (this.c - i) - (i2 / 8), i2);
    }

    public int d() {
        return this.c;
    }

    public void b() {
        if (this.g) {
            Arrays.fill(this.f17649a, 0, this.c, (byte) 0);
        }
        this.c = 0;
    }

    public void a() {
        b();
        this.f17649a = vbj.c;
    }

    public void i() {
        if (e()) {
            b(1);
            c(this.i);
            h();
        }
    }

    public final boolean e() {
        return this.d < 7;
    }

    private final void h() {
        this.i = (byte) 0;
        this.d = 7;
    }

    private final void d(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 <= 0) {
            return;
        }
        b(i2);
        System.arraycopy(bArr, i, this.f17649a, this.c, i2);
        this.c += i2;
    }

    private final void c(byte b2) {
        byte[] bArr = this.f17649a;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b2;
    }

    private final void b(int i) {
        int i2 = this.c + i;
        if (i2 > this.f17649a.length) {
            j(e(i2));
        }
    }

    private final void j(int i) {
        byte[] bArr = new byte[i];
        System.arraycopy(this.f17649a, 0, bArr, 0, this.c);
        if (this.g) {
            Arrays.fill(this.f17649a, 0, this.c, (byte) 0);
        }
        this.f17649a = bArr;
    }

    private final int e(int i) {
        int length = this.f17649a.length;
        if (length == 0) {
            length = 32;
        }
        if (length < i) {
            return i;
        }
        if (length > 1073741823) {
            return 1073741823;
        }
        return length;
    }

    public static int d(int i) {
        return i % 8 != 0 ? (i & (-8)) + 8 : i;
    }

    public static int a(int i) {
        if (i == 8) {
            return 255;
        }
        if (i == 16) {
            return 65535;
        }
        if (i == 24) {
            return ViewCompat.MEASURED_SIZE_MASK;
        }
        if (i == 32) {
            return -1;
        }
        throw new IllegalArgumentException("Var length Bits must be a multiple of 8, not " + i + "!");
    }

    public String toString() {
        byte[] copyOf = Arrays.copyOf(this.f17649a, this.c);
        if (copyOf == null || copyOf.length == 0) {
            return "--";
        }
        StringBuilder sb = new StringBuilder(copyOf.length * 3);
        for (int i = 0; i < copyOf.length; i++) {
            sb.append(String.format("%02X", Integer.valueOf(copyOf[i] & 255)));
            if (i < copyOf.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
