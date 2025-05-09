package defpackage;

import java.util.Arrays;
import java.util.Random;

/* loaded from: classes7.dex */
public class vbj {
    public static final byte[] c = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private final boolean f17644a;
    private final int b;
    private final byte[] d;
    private String e;

    public vbj(byte[] bArr) {
        this(bArr, 255, false);
    }

    public vbj(byte[] bArr, int i, boolean z) {
        this(bArr, i, z, false);
    }

    public vbj(byte[] bArr, int i, boolean z, boolean z2) {
        if (bArr == null) {
            throw new NullPointerException("bytes must not be null");
        }
        if (bArr.length > i) {
            throw new IllegalArgumentException("bytes length must be between 0 and " + i + " inclusive");
        }
        this.f17644a = z2;
        this.d = z ? Arrays.copyOf(bArr, bArr.length) : bArr;
        this.b = Arrays.hashCode(bArr);
    }

    public String toString() {
        return "BYTES=" + e();
    }

    public final int hashCode() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof vbj)) {
            return false;
        }
        vbj vbjVar = (vbj) obj;
        if (((this.f17644a || vbjVar.f17644a) && getClass() != obj.getClass()) || this.b != vbjVar.b) {
            return false;
        }
        return Arrays.equals(this.d, vbjVar.d);
    }

    public final byte[] c() {
        return this.d;
    }

    public final String e() {
        if (this.e == null) {
            this.e = vcb.e(this.d);
        }
        return this.e;
    }

    public final boolean d() {
        return this.d.length == 0;
    }

    public final int b() {
        return this.d.length;
    }

    public static byte[] d(Random random, int i) {
        byte[] bArr = new byte[i];
        try {
            random.nextBytes(bArr);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Number of bits per request limited ") && i > 4096) {
                byte[] bArr2 = new byte[4096];
                int i2 = 0;
                while (i2 < i) {
                    random.nextBytes(bArr2);
                    int min = Math.min(i - i2, 4096);
                    System.arraycopy(bArr2, 0, bArr, i2, min);
                    i2 += min;
                }
            }
        }
        return bArr;
    }

    public static byte[] e(vbj vbjVar, vbj vbjVar2) {
        return e(vbjVar.c(), vbjVar2.c());
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] bArr3 = new byte[length + length2];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr2, 0, bArr3, length, length2);
        return bArr3;
    }

    public static void b(byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
    }

    public static boolean d(vbj vbjVar, vbj vbjVar2) {
        if (vbjVar == vbjVar2) {
            return true;
        }
        if (vbjVar == null || vbjVar2 == null) {
            return false;
        }
        return vbjVar.equals(vbjVar2);
    }
}
