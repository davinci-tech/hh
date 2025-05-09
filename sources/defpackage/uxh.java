package defpackage;

/* loaded from: classes7.dex */
public final class uxh {

    /* renamed from: a, reason: collision with root package name */
    private final int f17572a;
    private final boolean d;
    private final int e;

    public static int a(int i) {
        if (i <= 0) {
            return 16;
        }
        if (i >= 6) {
            return 1024;
        }
        return 1 << (i + 4);
    }

    public uxh(int i, boolean z, int i2) {
        if (i < 0 || 7 < i) {
            throw new IllegalArgumentException("Block option's szx " + i + " must be between 0 and 7 inclusive");
        }
        if (i2 < 0 || 1048575 < i2) {
            throw new IllegalArgumentException("Block option's num " + i2 + " must be between 0 and 524288 inclusive");
        }
        this.f17572a = i;
        this.d = z;
        this.e = i2;
    }

    public uxh(byte[] bArr) {
        bArr.getClass();
        if (bArr.length > 3) {
            throw new IllegalArgumentException("Block option's length " + bArr.length + " must be at most 3 bytes inclusive");
        }
        if (bArr.length == 0) {
            this.f17572a = 0;
            this.d = false;
            this.e = 0;
            return;
        }
        byte b = bArr[bArr.length - 1];
        this.f17572a = b & 7;
        this.d = ((b >> 3) & 1) == 1;
        int i = (b & 255) >> 4;
        for (int i2 = 1; i2 < bArr.length; i2++) {
            i += (bArr[(bArr.length - i2) - 1] & 255) << ((i2 * 8) - 4);
        }
        this.e = i;
    }

    public boolean g() {
        return this.f17572a == 7;
    }

    public void b(int i) {
        int c;
        if (this.f17572a >= 7 || i <= 0 || i <= (c = c())) {
            return;
        }
        throw new IllegalStateException("Message with " + i + " bytes payload exceeds the blocksize of " + c + " bytes!");
    }

    public int d() {
        return this.f17572a;
    }

    public int c() {
        return a(this.f17572a);
    }

    public boolean f() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public byte[] e() {
        int i = this.f17572a;
        boolean z = this.d;
        int i2 = (z ? 8 : 0) | i;
        int i3 = this.e;
        if (i3 == 0 && !z && i == 0) {
            return vbj.c;
        }
        return i3 < 16 ? new byte[]{(byte) ((i3 << 4) | i2)} : i3 < 4096 ? new byte[]{(byte) (i3 >> 4), (byte) (i2 | (i3 << 4))} : new byte[]{(byte) (i3 >> 12), (byte) (i3 >> 4), (byte) (i2 | (i3 << 4))};
    }

    public int a() {
        return this.e * a(this.f17572a);
    }

    public String toString() {
        return String.format("(szx=%d/%d, m=%b, num=%d)", Integer.valueOf(this.f17572a), Integer.valueOf(a(this.f17572a)), Boolean.valueOf(this.d), Integer.valueOf(this.e));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof uxh)) {
            return false;
        }
        uxh uxhVar = (uxh) obj;
        return this.f17572a == uxhVar.f17572a && this.e == uxhVar.e && this.d == uxhVar.d;
    }

    public int hashCode() {
        return (((this.f17572a * 31) + (this.d ? 1 : 0)) * 31) + this.e;
    }

    public static int e(int i) {
        if (i >= 1024) {
            return 6;
        }
        if (i <= 16) {
            return 0;
        }
        return Integer.numberOfTrailingZeros(Integer.highestOneBit(i)) - 4;
    }
}
