package defpackage;

import android.graphics.PointF;
import android.view.animation.Interpolator;

/* loaded from: classes6.dex */
public class obs implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    private int f15605a = 0;
    private final PointF b;
    private final PointF d;

    private double b(double d, double d2, double d3, double d4, double d5) {
        double d6 = 1.0d - d;
        double d7 = d * d;
        double d8 = d6 * d6;
        return (d8 * d6 * d2) + (d8 * 3.0d * d * d3) + (d6 * 3.0d * d7 * d4) + (d7 * d * d5);
    }

    public obs(float f, float f2, float f3, float f4) {
        PointF pointF = new PointF();
        this.d = pointF;
        PointF pointF2 = new PointF();
        this.b = pointF2;
        pointF.x = f;
        pointF.y = f2;
        pointF2.x = f3;
        pointF2.y = f4;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        int i = this.f15605a;
        float f2 = f;
        while (true) {
            if (i >= 4096) {
                break;
            }
            f2 = (i * 1.0f) / 4096.0f;
            if (b(f2, 0.0d, this.d.x, this.b.x, 1.0d) >= f) {
                this.f15605a = i;
                break;
            }
            i++;
        }
        double b = b(f2, 0.0d, this.d.y, this.b.y, 1.0d);
        if (b > 0.999d) {
            this.f15605a = 0;
            b = 1.0d;
        }
        return (float) b;
    }
}
