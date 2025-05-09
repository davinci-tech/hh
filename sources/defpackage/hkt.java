package defpackage;

import android.view.animation.Interpolator;

/* loaded from: classes4.dex */
public class hkt implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    private final float f13223a;
    private final float b;
    private final float c;
    private final float d;

    private float c(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public hkt(float f, float f2, float f3, float f4) {
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.f13223a = f4;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return d(c(f));
    }

    private float a(float f) {
        return d(f, this.b, this.d);
    }

    private float d(float f) {
        return d(f, this.c, this.f13223a);
    }

    private float d(float f, float f2, float f3) {
        if (f == 0.0f || f == 1.0f) {
            return f;
        }
        float c = c(0.0f, f2, f);
        float c2 = c(f2, f3, f);
        return c(c(c, c2, f), c(c2, c(f3, 1.0f, f), f), f);
    }

    private float c(float f) {
        float f2 = 0.0f;
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        float f3 = f;
        float f4 = 1.0f;
        float f5 = 0.0f;
        for (int i = 0; i < 8; i++) {
            f5 = a(f3);
            double a2 = (a(f3 + 1.0E-6f) - f5) / 1.0E-6f;
            float f6 = f5 - f;
            if (Math.abs(f6) < 1.0E-6f) {
                return f3;
            }
            if (Math.abs(a2) < 9.999999974752427E-7d) {
                break;
            }
            if (f5 < f) {
                f2 = f3;
            } else {
                f4 = f3;
            }
            f3 = (float) (f3 - (f6 / a2));
        }
        for (int i2 = 0; Math.abs(f5 - f) > 1.0E-6f && i2 < 8; i2++) {
            if (f5 < f) {
                float f7 = f3;
                f3 = (f3 + f4) / 2.0f;
                f2 = f7;
            } else {
                f4 = f3;
                f3 = (f3 + f2) / 2.0f;
            }
            f5 = a(f3);
        }
        return f3;
    }
}
