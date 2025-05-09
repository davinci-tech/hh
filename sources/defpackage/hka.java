package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

/* loaded from: classes4.dex */
public class hka {
    public static AnimatorSet bhw_() {
        PathInterpolator bhe_ = hjx.bhe_();
        ObjectAnimator bhx_ = bhx_(0.0f, 1.0f, 200L);
        bhx_.setInterpolator(bhe_);
        ObjectAnimator bhx_2 = bhx_(1.0f, 0.5f, 1000L);
        bhx_2.setInterpolator(bhe_);
        bhx_2.setRepeatCount(-1);
        bhx_2.setRepeatMode(2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(bhx_).before(bhx_2);
        return animatorSet;
    }

    private static ObjectAnimator bhx_(float f, float f2, long j) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("alpha");
        objectAnimator.setFloatValues(f, f2);
        objectAnimator.setDuration(j);
        return objectAnimator;
    }

    public static AnimatorSet bhy_(int i, boolean z) {
        PathInterpolator bhe_ = hjx.bhe_();
        ObjectAnimator bhx_ = bhx_(0.0f, 1.0f, 150L);
        bhx_.setInterpolator(bhe_);
        ObjectAnimator bhx_2 = bhx_(1.0f, 1.0f, 450L);
        bhx_2.setInterpolator(bhe_);
        ObjectAnimator bhx_3 = bhx_(1.0f, 0.0f, 400L);
        bhx_3.setInterpolator(bhe_);
        bhx_3.setRepeatCount(-1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bhx_, bhx_2, bhx_3);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(bhz_(i, z), animatorSet);
        return animatorSet2;
    }

    private static ObjectAnimator bhz_(int i, boolean z) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        if (z) {
            objectAnimator.setPropertyName("translationY");
            if (i == 1) {
                objectAnimator.setFloatValues(200.0f);
            } else {
                objectAnimator.setFloatValues(-200.0f);
            }
        } else {
            objectAnimator.setPropertyName("translationX");
            if (i == 1) {
                objectAnimator.setFloatValues(-200.0f);
            } else {
                objectAnimator.setFloatValues(200.0f);
            }
        }
        objectAnimator.setDuration(1000L);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());
        return objectAnimator;
    }

    public static AlphaAnimation bhv_() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500L);
        alphaAnimation.setInterpolator(new FastOutSlowInInterpolator());
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        return alphaAnimation;
    }

    public static AnimationSet bhu_(int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 0, 0.0f, 0, i);
        translateAnimation.setDuration(2000L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(2000L);
        alphaAnimation.setFillAfter(false);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }
}
