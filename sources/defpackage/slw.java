package defpackage;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;

/* loaded from: classes7.dex */
public class slw extends BitmapDrawable implements Animatable {

    /* renamed from: a, reason: collision with root package name */
    private Paint f17115a;
    private int b;
    private float c;
    private ValueAnimator d;
    private float e;
    private float f;
    private float g;
    private Interpolator h;
    private float i;
    private float j;

    class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            slw.this.d(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    private static Bitmap a(int i) {
        if (i > 250) {
            i = 250;
        } else if (i <= 0) {
            i = 1;
        }
        return Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    }

    private void e(int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.d = ofFloat;
        ofFloat.setDuration(1000L);
        this.d.setRepeatCount(-1);
        this.d.setInterpolator(new LinearInterpolator());
        Paint paint = new Paint();
        this.f17115a = paint;
        this.j = 0.0f;
        this.g = 0.0f;
        this.b = i;
        paint.setColor(i);
        this.f17115a.setAntiAlias(true);
        this.d.addUpdateListener(new c());
    }

    public void d(float f) {
        this.e = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (canvas == null) {
            Log.w("HwLoadingDrawableImpl", "draw: canvas is null");
            return;
        }
        this.f17115a.setColor(this.b);
        if (this.e * 60.0f >= 60.0f) {
            this.e = 0.0f;
        }
        canvas.save();
        for (int i = 0; i < 12; i++) {
            float f = (this.e * 60.0f) + (i * 5);
            this.f17115a.setAlpha(((int) d(f, false)) + 127);
            canvas.drawCircle(this.i, this.f, this.c + (((float) d(f, true)) * this.c), this.f17115a);
            canvas.rotate(-30.0f, this.j, this.g);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        ValueAnimator valueAnimator = this.d;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        c();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator == null || valueAnimator.isRunning()) {
            return;
        }
        this.d.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.d.end();
    }

    public slw(Resources resources, int i, int i2) {
        super(resources, a(i));
        this.e = 0.0f;
        this.h = new HwCubicBezierInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
        e(i2);
    }

    private float e() {
        Rect bounds = getBounds();
        this.j = (bounds.left + bounds.right) / 2.0f;
        float f = (bounds.top + bounds.bottom) / 2.0f;
        this.g = f;
        float f2 = this.j;
        return f2 < f ? f2 : f;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double d(float r4, boolean r5) {
        /*
            r3 = this;
            r0 = 1114636288(0x42700000, float:60.0)
            float r4 = r4 % r0
            r1 = 0
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r2 = 1092616192(0x41200000, float:10.0)
            if (r1 < 0) goto L19
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 >= 0) goto L19
            android.view.animation.Interpolator r0 = r3.h
            r1 = 1036831949(0x3dcccccd, float:0.1)
            float r4 = r4 * r1
            float r4 = r0.getInterpolation(r4)
            goto L35
        L19:
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            r2 = 1107578565(0x42044ec5, float:33.076923)
            if (r1 < 0) goto L37
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 >= 0) goto L37
            android.view.animation.Interpolator r0 = r3.h
            r1 = -1120829877(0xffffffffbd317e4b, float:-0.043333333)
            float r4 = r4 * r1
            r1 = 1065353216(0x3f800000, float:1.0)
            float r4 = r4 + r1
            r1 = 1054727646(0x3eddddde, float:0.43333334)
            float r4 = r4 + r1
            float r4 = r0.getInterpolation(r4)
        L35:
            double r0 = (double) r4
            goto L49
        L37:
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 < 0) goto L40
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 >= 0) goto L40
            goto L47
        L40:
            java.lang.String r4 = "HwLoadingDrawableImpl"
            java.lang.String r0 = "invalid tempFrame"
            android.util.Log.w(r4, r0)
        L47:
            r0 = 0
        L49:
            if (r5 == 0) goto L4c
            return r0
        L4c:
            r4 = 4638707616191610880(0x4060000000000000, double:128.0)
            double r0 = r0 * r4
            int r4 = (int) r0
            double r4 = (double) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.slw.d(float, boolean):double");
    }

    private void c() {
        float e = e() * 0.6944444f;
        this.c = 0.1f * e;
        this.i = this.j;
        this.f = this.g - e;
    }
}
