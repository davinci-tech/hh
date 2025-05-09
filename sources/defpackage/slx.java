package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.Log;

/* loaded from: classes7.dex */
public class slx extends Drawable {
    private float d;
    private Paint e;
    private long h;
    private Paint k;
    private RectF l;
    private ValueAnimator o;
    private float b = -80.0f;

    /* renamed from: a, reason: collision with root package name */
    private int f17116a = 872415231;
    private boolean i = true;
    private float g = 0.0f;
    private int f = 2;
    private boolean j = false;
    private boolean n = false;
    private boolean c = false;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwFlickerDrawable", "onAnimationUpdate: animation is null.");
            } else if (slx.this.f()) {
                slx.this.m();
                slx.this.invalidateSelf();
            }
        }
    }

    class d extends AnimatorListenerAdapter {
        d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            if (slx.this.n) {
                slx.this.j();
                slx.this.n = false;
            }
        }
    }

    public slx(RectF rectF, int i, Paint paint) {
        this.l = rectF;
        this.k = paint;
        a(i);
        g();
    }

    private boolean h() {
        return this.f == 1;
    }

    private void k() {
        setLevel(0);
        this.i = true;
        this.f = 2;
    }

    private void l() {
        if (this.i || this.c) {
            float f = this.d;
            this.g = (80.0f + f) / 2000.0f;
            if (Float.compare(f, 0.0f) != 0) {
                this.i = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        l();
        float i = this.b + (this.g * i());
        this.b = i;
        if (Float.compare(i, this.d) > 0) {
            int i2 = (int) this.d;
            if (i2 != 0) {
                this.b = (this.b % i2) - 80.0f;
            }
            this.i = true;
        }
    }

    private boolean n() {
        return this.f == 2;
    }

    private void o() {
        int i = this.f17116a;
        int i2 = 16777215 & i;
        this.e.setShader(new SweepGradient(this.l.centerX(), this.l.centerY(), new int[]{i2, i, i2, i2}, new float[]{0.0f, 0.11f, 0.222f, 1.0f}));
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.o.isRunning();
    }

    public void b() {
        this.f = 0;
        this.n = false;
        if (a()) {
            return;
        }
        c(System.currentTimeMillis());
        this.b = -80.0f;
        this.o.start();
        this.o.setRepeatCount(-1);
    }

    public void c() {
        this.j = false;
        b();
    }

    public void c(int i) {
        Paint paint = this.e;
        if (paint != null) {
            paint.setStrokeWidth(i);
        }
    }

    public float d() {
        return this.b;
    }

    public void d(int i) {
        if (this.f17116a != i) {
            this.f17116a = i;
            o();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.i && (Float.compare(this.d, 360.0f) == 0 || h())) {
            this.n = true;
            return;
        }
        canvas.save();
        eeb_(canvas, this.b);
        canvas.rotate(this.b, this.l.centerX(), this.l.centerY());
        float f = Float.compare(this.b + 80.0f, this.d) > 0 ? this.d - this.b : 80.0f;
        float f2 = this.b;
        if (f2 < 0.0f) {
            f = Math.min(this.d, f2 + f);
        }
        float f3 = f;
        float f4 = this.b;
        float f5 = f4 < 0.0f ? -f4 : 0.0f;
        o();
        canvas.drawArc(this.l, f5, f3, false, this.e);
        canvas.restore();
    }

    public void e() {
        this.j = true;
        this.f = 1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void j() {
        if (a()) {
            this.o.cancel();
            k();
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        this.d = (i * 360.0f) / 10000.0f;
        if (i < 10000) {
            if (!h()) {
                this.j = false;
            }
            if (n()) {
                this.f = 0;
            }
        } else {
            this.j = true;
            if (this.i) {
                k();
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        boolean z;
        if (n() || (h() && this.i)) {
            this.j = false;
            z = false;
        } else {
            z = true;
        }
        if (!this.j || !this.i) {
            return z;
        }
        this.j = false;
        k();
        return false;
    }

    private void g() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.o = ofFloat;
        ofFloat.addUpdateListener(new a());
        this.o.addListener(new d());
    }

    private void a(int i) {
        Paint paint = new Paint();
        this.e = paint;
        paint.setAntiAlias(true);
        this.e.setStrokeWidth(i);
        this.e.setStrokeCap(Paint.Cap.SQUARE);
        this.e.setStyle(Paint.Style.STROKE);
        o();
    }

    private void eeb_(Canvas canvas, float f) {
        Path path = new Path();
        float min = Math.min(Math.max(0.0f, f), 360.0f);
        path.addArc(this.l, min, Float.compare(f, 0.0f) < 0 ? Math.min(80.0f, this.d) : this.d - min);
        this.k.getFillPath(path, path);
        canvas.clipPath(path);
    }

    private void c(long j) {
        this.h = j;
    }

    private long i() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.h;
        c(currentTimeMillis);
        return Math.max(0L, currentTimeMillis - j);
    }
}
