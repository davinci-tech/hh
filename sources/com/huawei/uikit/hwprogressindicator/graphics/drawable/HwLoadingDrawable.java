package com.huawei.uikit.hwprogressindicator.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import defpackage.skd;

/* loaded from: classes7.dex */
public class HwLoadingDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Interpolator f10683a;
    private b c;
    private CycleInterpolator d;
    private ValueAnimator e;
    private volatile boolean b = false;
    private float h = 0.0f;
    private float g = 0.0f;
    private float f = 0.0f;
    private OnLoadingListener i = null;

    public interface OnLoadingListener {
        void onLoadingFinish();

        void onLoadingStart();
    }

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwLoadingDrawable", "onAnimationUpdate: animation is null.");
                return;
            }
            HwLoadingDrawable.this.f = valueAnimator.getAnimatedFraction();
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            HwLoadingDrawable hwLoadingDrawable = HwLoadingDrawable.this;
            hwLoadingDrawable.c(floatValue, hwLoadingDrawable.f);
            HwLoadingDrawable.this.c.a(HwLoadingDrawable.this.h, HwLoadingDrawable.this.g);
            HwLoadingDrawable.this.invalidateSelf();
        }
    }

    static class b {
        private int c;
        private int d;
        private float e;
        private final c f;
        private final d[] g;
        private RectF j;

        /* renamed from: a, reason: collision with root package name */
        private final FloatEvaluator f10684a = new FloatEvaluator();
        private final ArgbEvaluator b = new ArgbEvaluator();
        private final Paint i = new Paint(1);
        private float h = 0.0f;

        static class c {

            /* renamed from: a, reason: collision with root package name */
            private PointF f10685a;
            private final Paint c = new Paint(1);

            c(PointF pointF, int i) {
                this.f10685a = pointF;
                d(i);
            }

            private void d(int i) {
                this.c.setStyle(Paint.Style.FILL);
                this.c.setColor(i);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void eeg_(PointF pointF) {
                this.f10685a = pointF;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(Canvas canvas, float f) {
                PointF pointF = this.f10685a;
                canvas.drawCircle(pointF.x, pointF.y, f, this.c);
            }
        }

        static class d {

            /* renamed from: a, reason: collision with root package name */
            private int f10686a;
            private PointF c;
            private int e;

            d(PointF pointF) {
                this.c = pointF;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(Canvas canvas, Paint paint, float f) {
                if (this.e == 0) {
                    return;
                }
                paint.setColor(this.f10686a);
                paint.setAlpha(this.e);
                PointF pointF = this.c;
                canvas.drawCircle(pointF.x, pointF.y, f, paint);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void d(int i, int i2) {
                this.f10686a = i;
                this.e = i2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void eej_(PointF pointF) {
                this.c = pointF;
            }
        }

        b(RectF rectF, int i, int i2, float f) {
            this.e = f;
            this.d = i;
            this.c = i2;
            this.j = rectF;
            PointF pointF = new PointF(rectF.right, rectF.centerY());
            this.f = new c(pointF, this.c);
            this.g = new d[50];
            int i3 = 0;
            while (true) {
                d[] dVarArr = this.g;
                if (i3 >= dVarArr.length) {
                    c(i);
                    return;
                } else {
                    dVarArr[i3] = new d(pointF);
                    i3++;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(float f) {
            this.e = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(float f, float f2) {
            this.h = Math.min(f2, 90.0f);
            this.f.eeg_(eed_(0.0f, f));
            int i = 0;
            while (i < 50) {
                int i2 = i + 1;
                float f3 = i2 / 50.0f;
                int c2 = c(f3);
                int b = b(f3);
                PointF eed_ = eed_(f3, f);
                d dVar = this.g[i];
                dVar.d(c2, b);
                dVar.eej_(eed_);
                i = i2;
            }
        }

        private void c(int i) {
            this.i.setColor(i);
            this.i.setStyle(Paint.Style.FILL);
        }

        private int b(float f) {
            if (Float.compare(f, 1.0f) >= 0) {
                return 0;
            }
            return (int) ((1.0f - f) * 255.0f);
        }

        private PointF eed_(float f, float f2) {
            double b = b(f, f2, f2 - this.h);
            double sin = Math.sin(Math.toRadians(b));
            double cos = Math.cos(Math.toRadians(b));
            double width = this.j.width() / 2.0f;
            return new PointF((float) (this.j.centerX() + (cos * width)), (float) (this.j.centerY() + (width * sin)));
        }

        private int c(float f) {
            if (Float.compare(f, 1.0f) >= 0) {
                return this.d;
            }
            return ((Integer) this.b.evaluate(f, Integer.valueOf(this.c), Integer.valueOf(this.d))).intValue();
        }

        private float b(float f, float f2, float f3) {
            return Float.compare(f, 1.0f) >= 0 ? f3 : this.f10684a.evaluate(f, (Number) Float.valueOf(f2), (Number) Float.valueOf(f3)).floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void eee_(Canvas canvas) {
            for (int i = 49; i >= 0; i--) {
                this.g[i].a(canvas, this.i, this.e);
            }
            this.f.a(canvas, this.e);
        }
    }

    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            if (HwLoadingDrawable.this.d()) {
                if (HwLoadingDrawable.this.i != null) {
                    HwLoadingDrawable.this.i.onLoadingFinish();
                }
                HwLoadingDrawable.this.e.end();
                HwLoadingDrawable.this.b = false;
                HwLoadingDrawable.this.f = 0.0f;
            }
        }
    }

    public HwLoadingDrawable(RectF rectF, int i, int i2, float f) {
        this.c = new b(rectF, i, i2, f);
        b();
    }

    public boolean a() {
        return this.e.isRunning();
    }

    public void c() {
        if (a()) {
            this.b = false;
            return;
        }
        OnLoadingListener onLoadingListener = this.i;
        if (onLoadingListener != null) {
            onLoadingListener.onLoadingStart();
        }
        this.e.start();
        this.e.setRepeatCount(-1);
    }

    public void d(float f) {
        b bVar = this.c;
        if (bVar != null) {
            bVar.a(f);
            this.c.a(this.h, this.g);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.c.eee_(canvas);
    }

    public void e() {
        if (a()) {
            this.b = true;
        }
    }

    public void e(OnLoadingListener onLoadingListener) {
        this.i = onLoadingListener;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        return a() && this.b;
    }

    private void b() {
        this.d = new CycleInterpolator(0.5f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.e = ofFloat;
        ofFloat.setDuration(2000L);
        this.e.setInterpolator(new LinearInterpolator());
        this.f10683a = skd.dYQ_();
        this.e.addUpdateListener(new a());
        this.e.addListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f, float f2) {
        double interpolation = this.d.getInterpolation(f);
        double sqrt = Math.sqrt(1.0d - (interpolation * interpolation));
        if (Float.compare(f2, 0.5f) > 0) {
            sqrt = -sqrt;
        }
        this.h = (((1.0f - ((float) sqrt)) + (f * 3.1415927f)) * 360.0f) / 5.141593f;
        this.g = Math.min(this.h, this.f10683a.getInterpolation(f2 < 0.5f ? f2 * 2.0f : (1.0f - f2) * 2.0f) * 90.0f);
    }
}
