package defpackage;

import android.animation.ValueAnimator;

/* loaded from: classes6.dex */
public class nol {

    /* renamed from: a, reason: collision with root package name */
    private float f15413a = 1.0f;
    private float b = 1.0f;
    private float c = 1.0f;

    public void cCx_(final ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: nol.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                nol.this.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                animatorUpdateListener.onAnimationUpdate(valueAnimator);
            }
        });
        ofFloat.setDuration(i);
        ofFloat.start();
    }

    public void cCw_(final ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: nol.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                nol.this.e(((Float) valueAnimator.getAnimatedValue()).floatValue());
                animatorUpdateListener.onAnimationUpdate(valueAnimator);
            }
        });
        ofFloat.setDuration(i);
        ofFloat.start();
    }

    public void cCv_(final ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: nol.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                nol.this.d(((Float) valueAnimator.getAnimatedValue()).floatValue());
                animatorUpdateListener.onAnimationUpdate(valueAnimator);
            }
        });
        ofFloat.setDuration(i);
        ofFloat.start();
    }

    public void e(float f) {
        this.f15413a = f;
    }

    public float b() {
        return this.f15413a;
    }

    public void b(float f) {
        this.b = f;
    }

    public float d() {
        return this.b;
    }

    public void d(float f) {
        this.c = f;
    }

    public float a() {
        return this.c;
    }
}
