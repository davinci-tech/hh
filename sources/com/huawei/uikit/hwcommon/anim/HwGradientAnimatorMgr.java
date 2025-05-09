package com.huawei.uikit.hwcommon.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Pair;
import com.huawei.uikit.hwcommon.drawable.HwDrawableWrapper;
import defpackage.sld;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public class HwGradientAnimatorMgr implements HwDrawableWrapper.OnStateChangedListener {
    private Animator b;
    private OnAnimatorListener d;
    private Map<String, Pair<Integer, Integer>> e = new HashMap(16);

    public interface OnAnimatorListener {
        void onAnimationCancel(Animator animator, String str);

        void onAnimationEnd(Animator animator, String str);

        void onAnimationStart(Animator animator, String str);

        void onAnimationUpdate(Animator animator, String str, int i);

        boolean onPrepareAnimation(int[] iArr, int[] iArr2, int i, int i2, Map<String, Pair<Integer, Integer>> map);
    }

    class e implements Animator.AnimatorListener {
        e() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (HwGradientAnimatorMgr.this.d != null) {
                Iterator it = HwGradientAnimatorMgr.this.e.keySet().iterator();
                while (it.hasNext()) {
                    HwGradientAnimatorMgr.this.d.onAnimationCancel(animator, (String) it.next());
                }
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (HwGradientAnimatorMgr.this.d != null) {
                Iterator it = HwGradientAnimatorMgr.this.e.keySet().iterator();
                while (it.hasNext()) {
                    HwGradientAnimatorMgr.this.d.onAnimationEnd(animator, (String) it.next());
                }
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (HwGradientAnimatorMgr.this.d != null) {
                Iterator it = HwGradientAnimatorMgr.this.e.keySet().iterator();
                while (it.hasNext()) {
                    HwGradientAnimatorMgr.this.d.onAnimationStart(animator, (String) it.next());
                }
            }
        }
    }

    public Animator ebB_(Map<String, Pair<Integer, Integer>> map) {
        this.e = map;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "GradientValue", 0.0f, 1.0f);
        ofFloat.setInterpolator(sld.b());
        ofFloat.setDuration(50L);
        ofFloat.addListener(new e());
        return ofFloat;
    }

    @Override // com.huawei.uikit.hwcommon.drawable.HwDrawableWrapper.OnStateChangedListener
    public void onStateChanged(int[] iArr, int[] iArr2, int i, int i2) {
        if (this.d == null || iArr == null || iArr2 == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        if (this.d.onPrepareAnimation(iArr, iArr2, i, i2, hashMap)) {
            Animator animator = this.b;
            if (animator != null && animator.isRunning()) {
                this.b.cancel();
            }
            Animator ebB_ = ebB_(hashMap);
            this.b = ebB_;
            ebB_.start();
        }
    }
}
