package defpackage;

import health.compact.a.util.LogUtil;

/* loaded from: classes5.dex */
public class lan {
    private int c(byte b) {
        return b & 255;
    }

    private int c(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }

    private int d(int i) {
        return i & 15;
    }

    private int d(byte b, byte b2) {
        return c(b) + (c(b2) << 8);
    }

    private int a(byte b, byte b2, byte b3) {
        return c(b) + (c(b2) << 8) + (c(b3) << 16);
    }

    private int b(byte b, byte b2, byte b3, byte b4) {
        return c(b) + (c(b2) << 8) + (c(b3) << 16) + (c(b4) << 24);
    }

    protected int d(byte[] bArr, int i, int i2) {
        if (bArr == null || d(i) + i2 > bArr.length) {
            return -1;
        }
        if (i == 17) {
            return c(bArr[i2]);
        }
        if (i == 18) {
            return d(bArr[i2], bArr[i2 + 1]);
        }
        if (i == 20) {
            return b(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]);
        }
        if (i == 36) {
            return c(b(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]), 32);
        }
        if (i == 33) {
            return c(c(bArr[i2]), 8);
        }
        if (i == 34) {
            return c(d(bArr[i2], bArr[i2 + 1]), 16);
        }
        LogUtil.e("GattParser", "no formatType");
        return -1;
    }

    protected int d(byte[] bArr, int i) {
        if (bArr == null || i + 3 > bArr.length) {
            return -1;
        }
        return a(bArr[i], bArr[i + 1], bArr[i + 2]);
    }
}
