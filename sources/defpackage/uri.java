package defpackage;

/* loaded from: classes7.dex */
public class uri {
    private static final int[] e = new int[256];

    /* renamed from: a, reason: collision with root package name */
    private final int[] f17515a = new int[3];

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = i2 & 1;
                i2 >>>= 1;
                if (i4 == 1) {
                    i2 ^= -306674912;
                }
            }
            e[i] = i2;
        }
    }

    public void c(char[] cArr, boolean z) {
        int[] iArr = this.f17515a;
        iArr[0] = 305419896;
        iArr[1] = 591751049;
        iArr[2] = 878082192;
        for (byte b : utd.e(cArr, z)) {
            e((byte) (b & 255));
        }
    }

    public void e(byte b) {
        int[] iArr = this.f17515a;
        iArr[0] = e(iArr[0], b);
        int[] iArr2 = this.f17515a;
        int i = iArr2[1] + (iArr2[0] & 255);
        iArr2[1] = i;
        int i2 = (i * 134775813) + 1;
        iArr2[1] = i2;
        iArr2[2] = e(iArr2[2], (byte) (i2 >> 24));
    }

    private int e(int i, byte b) {
        return (i >>> 8) ^ e[(b ^ i) & 255];
    }

    public byte b() {
        int i = this.f17515a[2] | 2;
        return (byte) ((i * (i ^ 1)) >>> 8);
    }
}
