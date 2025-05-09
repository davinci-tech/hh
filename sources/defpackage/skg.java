package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable;

/* loaded from: classes7.dex */
public class skg extends HwGravitationalLoadingDrawable {

    /* renamed from: a, reason: collision with root package name */
    private ValueAnimator f17087a;
    private ValueAnimator b;
    private ValueAnimator d;
    private boolean g;
    private boolean j;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwSeekableLoadingAnim", "onAnimationUpdate: null animator");
            } else {
                skg.this.c.e(((Float) valueAnimator.getAnimatedValue()).floatValue());
                skg.this.invalidateSelf();
            }
        }
    }

    class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwSeekableLoadingAnim", "onAnimationUpdate:null animator");
            } else {
                skg.this.c.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                skg.this.invalidateSelf();
            }
        }
    }

    class d extends AnimatorListenerAdapter {
        d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            skg.this.f17087a.start();
            skg.super.b(false);
        }
    }

    skg(HwGravitationalLoadingDrawable.i iVar, HwGravitationalLoadingDrawable.f fVar, int i, float f) {
        super(iVar, fVar, i, f);
        this.j = true;
        this.g = false;
        b();
        this.c.b(0.0f);
    }

    private void b() {
        c();
        f();
        j();
    }

    private void c() {
        this.d = e.a(-8.0f, 15.0f);
    }

    public static skg dYN_(int i, HwGravitationalLoadingDrawable.a aVar, DisplayMetrics displayMetrics, int i2) {
        if (i2 <= 0) {
            i2 = 1200;
        }
        int c2 = HwGravitationalLoadingDrawable.c(i);
        return new skg(HwGravitationalLoadingDrawable.c(c2, aVar), HwGravitationalLoadingDrawable.e(c2, aVar), i2, displayMetrics.density);
    }

    private void f() {
        ValueAnimator b = e.b(15.0f, 35.0f);
        this.b = b;
        b.addUpdateListener(new a());
        this.b.addListener(new d());
    }

    private void j() {
        ValueAnimator dYO_ = e.dYO_(60.0f, 480L);
        this.f17087a = dYO_;
        dYO_.addUpdateListener(new c());
    }

    public void d() {
        stop();
        this.j = false;
    }

    public void e() {
        this.j = true;
        setLevel(10000);
        start();
    }

    @Override // com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable, android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        if (isRunning()) {
            return false;
        }
        d(i / 10000.0f);
        return true;
    }

    @Override // com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable, android.graphics.drawable.Animatable
    public void start() {
        if (!isRunning() && this.j) {
            this.b.start();
            this.g = true;
        }
    }

    @Override // com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable, android.graphics.drawable.Animatable
    public void stop() {
        if (isRunning()) {
            this.b.cancel();
            this.f17087a.cancel();
            this.c.b(0.0f);
            this.g = false;
            super.stop();
        }
    }

    private void d(float f) {
        this.d.setCurrentPlayTime((long) (f * r0.getDuration()));
        this.e.e(((Float) this.d.getAnimatedValue("scale")).floatValue());
        this.c.e(((Float) this.d.getAnimatedValue("degrees")).floatValue());
        invalidateSelf();
    }

    static class e {
        e() {
        }

        static ValueAnimator a(float f, float f2) {
            return ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("scale", 0.5f, 1.0f), PropertyValuesHolder.ofFloat("degrees", f, f2));
        }

        static ValueAnimator b(float f, float f2) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
            ofFloat.setInterpolator(PathInterpolatorCompat.create(0.6f, 0.2f, 1.0f, 1.0f));
            ofFloat.setDuration(100L);
            return ofFloat;
        }

        static ValueAnimator dYO_(float f, long j) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, f);
            ofFloat.setDuration(j);
            ofFloat.setInterpolator(new LinearInterpolator());
            return ofFloat;
        }
    }
}
