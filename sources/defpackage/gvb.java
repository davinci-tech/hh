package defpackage;

import com.huawei.up.model.UserInfomation;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class gvb {
    private static int[] e = {0, 0};

    /* renamed from: a, reason: collision with root package name */
    private static int[] f12952a = {0, 0};
    private static final float[] c = {1.0f, 1.0f};
    private static final float[] b = {0.0f, 0.0f};

    private static float b(int i) {
        float f;
        float f2;
        float f3;
        if (i == 0) {
            i = 170;
        }
        if (i < 149) {
            f2 = i;
            f3 = 0.48f;
        } else if (i < 166) {
            f2 = i;
            f3 = (0.0025f * f2) + 0.145f;
        } else {
            if (i >= 185) {
                f = 110.0f;
                return f / 100.0f;
            }
            f2 = i;
            f3 = 0.56f;
        }
        f = f2 * f3;
        return f / 100.0f;
    }

    private static float c(int i) {
        if (i <= 0 || i > 220) {
            return 1.0f;
        }
        if (i <= 96) {
            return 0.75f;
        }
        if (i <= 132) {
            return 0.85f;
        }
        return i >= 180 ? 1.1f : 1.0f;
    }

    private static float c(int i, UserInfomation userInfomation) {
        if (i <= 0) {
            return 0.0f;
        }
        return i * (((userInfomation == null ? 170 : userInfomation.getHeightOrDefaultValue()) * 0.42f) / 100.0f);
    }

    private static float e(int i, UserInfomation userInfomation, int i2, int i3, boolean z) {
        if (i2 <= 0 || i3 > 1) {
            return -1.0f;
        }
        if (i3 == 0) {
            return c(i, userInfomation, i2, z);
        }
        return c(i, userInfomation);
    }

    private static float c(int i, UserInfomation userInfomation, int i2, boolean z) {
        if (i2 <= 0) {
            return -1.0f;
        }
        int heightOrDefaultValue = userInfomation == null ? 170 : userInfomation.getHeightOrDefaultValue();
        int a2 = a(i, i2);
        float b2 = b(heightOrDefaultValue);
        if (z) {
            b2 *= c(a2);
        }
        return i * b2;
    }

    private static int a(int i, int i2) {
        if (i2 != 0) {
            return (i * 60) / i2;
        }
        return 0;
    }

    public static float e(int i, UserInfomation userInfomation, int i2, int i3) {
        if (i2 <= 0) {
            return -1.0f;
        }
        if (i3 > 1 || i3 < 0) {
            return -1.0f;
        }
        int a2 = a(i, i2);
        float c2 = c(a2, i3);
        if (c2 < 0.4f || c2 > 1.6f) {
            c2 = 1.0f;
        }
        return c2 * (gvl.a(0.025f, 0.2f, 185.0f, a2) + 1.0f) * e(i, userInfomation, i2, i3, c2 != 1.0f);
    }

    private static float c(int i, int i2) {
        float f;
        float f2;
        float f3 = i;
        if (f3 < e[i2] * 0.95f || f3 > f12952a[i2] * 1.05f) {
            return 1.0f;
        }
        float[] fArr = c;
        if (i2 > fArr.length) {
            return 1.0f;
        }
        if (i < 80) {
            f = fArr[i2];
            f2 = b[i2];
            f3 = 80.0f;
        } else if (i > 220) {
            f = fArr[i2];
            f2 = b[i2];
            f3 = 220.0f;
        } else {
            f = fArr[i2];
            f2 = b[i2];
        }
        return f + (f2 * f3);
    }

    public static int d(UserInfomation userInfomation, int[] iArr, int[] iArr2, float[] fArr, int i) {
        int length;
        if (iArr == null || iArr2 == null || fArr == null || (length = iArr.length) == 0 || iArr2.length != length || fArr.length != length || i > 1) {
            return -1;
        }
        float[] fArr2 = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return -2;
            }
            fArr2[i2] = (iArr2[i2] * 60.0f) / i3;
        }
        float[] fArr3 = new float[length];
        for (int i4 = 0; i4 < length; i4++) {
            float e2 = e(iArr2[i4], userInfomation, iArr[i4], i, true);
            if (e2 == 0.0f) {
                return -3;
            }
            fArr3[i4] = fArr[i4] / e2;
        }
        float[] fArr4 = b;
        float a2 = gvm.a(fArr2, fArr3);
        fArr4[i] = a2;
        c[i] = gvm.b(fArr2, fArr3, a2);
        c(fArr2, i);
        return 0;
    }

    private static void c(float[] fArr, int i) {
        Arrays.sort(fArr);
        e[i] = (int) fArr[0];
        f12952a[i] = (int) fArr[fArr.length - 1];
    }
}
