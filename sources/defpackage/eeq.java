package defpackage;

/* loaded from: classes8.dex */
public class eeq {
    public static int a(int i, int i2) {
        if (i == 0) {
            return 12000;
        }
        if (i2 <= 1) {
            return i;
        }
        int b = b(i);
        if (b < 1) {
            b = 1;
        }
        int pow = (int) Math.pow(10.0d, b - 1);
        int i3 = 0;
        while (i3 < i) {
            i3 += (i2 - 1) * pow;
        }
        return i3;
    }

    private static int b(int i) {
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
