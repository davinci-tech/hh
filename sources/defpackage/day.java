package defpackage;

/* loaded from: classes3.dex */
public class day {
    public static Boolean b(byte b, int i) {
        if (i < 0 || i >= 8) {
            return null;
        }
        boolean[] zArr = new boolean[8];
        for (int i2 = 0; i2 < 8; i2++) {
            boolean z = true;
            if ((b & 1) != 1) {
                z = false;
            }
            zArr[i2] = z;
            b = (byte) (b >> 1);
        }
        return Boolean.valueOf(zArr[i]);
    }
}
