package defpackage;

import java.util.Arrays;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.OptionNumberRegistry;

/* loaded from: classes7.dex */
public class uxs implements Comparable<uxs> {
    private byte[] b;
    private final int e;

    public uxs() {
        this.e = 0;
        d(vbj.c);
    }

    public uxs(int i) {
        this.e = i;
    }

    public uxs(int i, String str) {
        this.e = i;
        b(str);
    }

    public uxs(int i, int i2) {
        this.e = i;
        c(i2);
    }

    public uxs(int i, long j) {
        this.e = i;
        a(j);
    }

    public uxs(int i, byte[] bArr) {
        this.e = i;
        d(bArr);
    }

    public int e() {
        return this.e;
    }

    public byte[] b() {
        byte[] bArr = this.b;
        if (bArr != null) {
            return bArr;
        }
        throw new IllegalStateException(OptionNumberRegistry.a(this.e) + " option value must be set before!");
    }

    public String a() {
        return new String(b(), CoAP.d);
    }

    public int c() {
        byte[] b = b();
        int i = 0;
        for (int i2 = 0; i2 < b.length; i2++) {
            i += (b[(b.length - i2) - 1] & 255) << (i2 * 8);
        }
        return i;
    }

    public long d() {
        long j = 0;
        for (int i = 0; i < b().length; i++) {
            j += (r0[(r0.length - i) - 1] & 255) << (i * 8);
        }
        return j;
    }

    public void d(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException(OptionNumberRegistry.a(this.e) + " option value must not be null!");
        }
        OptionNumberRegistry.a(this.e, bArr.length);
        this.b = bArr;
    }

    public void b(String str) {
        d(str == null ? null : str.getBytes(CoAP.d));
    }

    public void c(int i) {
        int numberOfLeadingZeros = (39 - Integer.numberOfLeadingZeros(i)) / 8;
        byte[] bArr = new byte[numberOfLeadingZeros];
        for (int i2 = 0; i2 < numberOfLeadingZeros; i2++) {
            bArr[(numberOfLeadingZeros - i2) - 1] = (byte) (i >> (i2 * 8));
        }
        d(bArr);
    }

    public void a(long j) {
        int numberOfLeadingZeros = (71 - Long.numberOfLeadingZeros(j)) / 8;
        byte[] bArr = new byte[numberOfLeadingZeros];
        for (int i = 0; i < numberOfLeadingZeros; i++) {
            bArr[(numberOfLeadingZeros - i) - 1] = (byte) (j >> (i * 8));
        }
        d(bArr);
    }

    @Override // java.lang.Comparable
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(uxs uxsVar) {
        return this.e - uxsVar.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof uxs)) {
            return false;
        }
        uxs uxsVar = (uxs) obj;
        return this.e == uxsVar.e && Arrays.equals(this.b, uxsVar.b);
    }

    public int hashCode() {
        return (this.e * 31) + Arrays.hashCode(this.b);
    }

    public String toString() {
        return OptionNumberRegistry.a(this.e) + ": " + j();
    }

    public String j() {
        if (this.b == null) {
            return "not available";
        }
        int i = AnonymousClass1.e[OptionNumberRegistry.e(this.e).ordinal()];
        if (i != 1) {
            if (i == 2) {
                return "\"" + a() + "\"";
            }
            if (i == 3) {
                return "";
            }
            return "0x" + vcb.e(this.b);
        }
        int i2 = this.e;
        if (i2 == 27 || i2 == 23) {
            return "\"" + new uxh(this.b) + "\"";
        }
        int c = c();
        int i3 = this.e;
        if (i3 == 17 || i3 == 12) {
            return "\"" + uxm.a(c) + "\"";
        }
        if (i3 == 258) {
            return "\"" + new uxp(c) + "\"";
        }
        return Long.toString(d());
    }

    /* renamed from: uxs$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[OptionNumberRegistry.OptionFormat.values().length];
            e = iArr;
            try {
                iArr[OptionNumberRegistry.OptionFormat.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[OptionNumberRegistry.OptionFormat.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[OptionNumberRegistry.OptionFormat.EMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
