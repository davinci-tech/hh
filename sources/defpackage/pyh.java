package defpackage;

/* loaded from: classes6.dex */
public class pyh {
    public static int c(int i, int i2) {
        if (i == 0) {
            return 12000;
        }
        if (i2 <= 1) {
            return i;
        }
        int e = e(i);
        if (e < 1) {
            e = 1;
        }
        int pow = (int) Math.pow(10.0d, e - 1);
        int i3 = 0;
        while (i3 < i) {
            i3 += (i2 - 1) * pow;
        }
        return i3;
    }

    private static int e(int i) {
        int i2 = 0;
        do {
            i /= 10;
            if (i != 0) {
                i2++;
            }
        } while (i != 0);
        return i2;
    }
}
