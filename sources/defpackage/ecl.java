package defpackage;

/* loaded from: classes3.dex */
public class ecl {
    public static Integer[] c(float f, float f2) {
        int i = (int) f2;
        int i2 = (int) f;
        int i3 = i2 % 4;
        int i4 = i3 == 0 ? i2 - 4 : i2 - i3;
        int i5 = i % 4;
        int i6 = i5 == 0 ? i + 4 : (i + 4) - i5;
        if (i == 100 || i6 > 100) {
            i6 = 100;
        }
        return new Integer[]{Integer.valueOf(i4), Integer.valueOf(i6)};
    }
}
