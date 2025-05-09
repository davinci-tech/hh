package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/* loaded from: classes7.dex */
public class sjx {
    public static ValueAnimator dYk_(int i, boolean z) {
        return dYj_(i, z ? 0 : 255);
    }

    public static Animator dYl_(View view, float f) {
        Interpolator dYP_ = skd.dYP_();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", view.getScaleX(), f);
        ofFloat.setInterpolator(dYP_);
        ofFloat.setDuration(250L);
        ofFloat.setAutoCancel(true);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", view.getScaleY(), f);
        ofFloat2.setInterpolator(dYP_);
        ofFloat2.setDuration(250L);
        ofFloat2.setAutoCancel(true);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        return animatorSet;
    }

    public static ValueAnimator dYj_(int i, int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.setInterpolator(skd.dYP_());
        ofInt.setDuration(250L);
        return ofInt;
    }
}
