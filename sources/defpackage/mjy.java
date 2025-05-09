package defpackage;

import android.animation.ValueAnimator;

/* loaded from: classes9.dex */
public class mjy {

    /* renamed from: a, reason: collision with root package name */
    private float f15030a = 1.0f;

    public void cjf_(final ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: mjy.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mjy.this.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                animatorUpdateListener.onAnimationUpdate(valueAnimator);
            }
        });
        ofFloat.setDuration(i);
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(float f) {
        this.f15030a = f;
    }

    public float a() {
        return this.f15030a;
    }
}
