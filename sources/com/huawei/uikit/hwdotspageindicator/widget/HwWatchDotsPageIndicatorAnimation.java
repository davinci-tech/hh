package com.huawei.uikit.hwdotspageindicator.widget;

import android.animation.Animator;
import defpackage.slk;

/* loaded from: classes9.dex */
public class HwWatchDotsPageIndicatorAnimation implements Animator.AnimatorListener {

    public interface AnimationUpdateListener {
        void onAnimationUpdate(slk slkVar);

        void onSpringAnimationUpdate(boolean z, float f);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }
}
