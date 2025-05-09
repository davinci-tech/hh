package com.huawei.ui.commonui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.UnitUtil;
import org.slf4j.Marker;

/* loaded from: classes6.dex */
public class KakaClaimAnimation extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8974a;
    private AnimatorSet b;
    private AnimationDrawable d;
    private HealthTextView e;

    public KakaClaimAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cMN_(attributeSet);
    }

    private void cMN_(AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.kaka_claim_animation_layout, this);
        this.f8974a = (ImageView) inflate.findViewById(R.id.kaka_convert_anim);
        this.e = (HealthTextView) inflate.findViewById(R.id.kaka_exchange_result);
        this.d = (AnimationDrawable) this.f8974a.getDrawable();
    }

    private void d(int i) {
        this.e.setText(Marker.ANY_NON_NULL_MARKER + UnitUtil.e(i, 1, 0));
        AnimationDrawable animationDrawable = this.d;
        if (animationDrawable == null) {
            LogUtil.h("KakaClaimAnimation", "showExchangeResultAnimation mKakaConvertAnim is null");
            return;
        }
        animationDrawable.stop();
        if (this.b == null) {
            a();
        }
        this.b.start();
        this.d.start();
    }

    private void a() {
        ObjectAnimator cMM_ = cMM_("alpha", 0.0f, 1.0f, 800L);
        ObjectAnimator cMM_2 = cMM_("scaleX", 0.8f, 1.0f, 800L);
        ObjectAnimator cMM_3 = cMM_("scaleY", 0.8f, 1.0f, 800L);
        ObjectAnimator cMM_4 = cMM_("alpha", 1.0f, 0.0f, 800L);
        ObjectAnimator cMM_5 = cMM_("scaleX", 1.0f, 1.0f, 800L);
        ObjectAnimator cMM_6 = cMM_("scaleY", 1.0f, 1.0f, 800L);
        AnimatorSet animatorSet = new AnimatorSet();
        this.b = animatorSet;
        animatorSet.play(cMM_).with(cMM_2).with(cMM_3);
        this.b.play(cMM_4).with(cMM_5).with(cMM_6).after(cMM_);
        this.b.addListener(getAnimatorListener());
    }

    private ObjectAnimator cMM_(String str, float f, float f2, long j) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, str, f, f2);
        ofFloat.setDuration(j);
        return ofFloat;
    }

    private Animator.AnimatorListener getAnimatorListener() {
        return new Animator.AnimatorListener() { // from class: com.huawei.ui.commonui.view.KakaClaimAnimation.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtil.a("KakaClaimAnimation", "showExchangeResultAnimation end");
                KakaClaimAnimation.this.setVisibility(8);
            }
        };
    }

    public void e(int i) {
        setVisibility(0);
        d(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimationDrawable animationDrawable = this.d;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        AnimatorSet animatorSet = this.b;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }
}
