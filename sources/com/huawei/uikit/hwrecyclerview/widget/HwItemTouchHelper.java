package com.huawei.uikit.hwrecyclerview.widget;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.huawei.health.R;
import com.huawei.uikit.hwrecyclerview.widget.HwItemTouchHelperEx;
import defpackage.slq;

/* loaded from: classes9.dex */
public class HwItemTouchHelper extends HwItemTouchHelperEx {

    class e implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ HwItemTouchHelperEx.Callback b;
        final /* synthetic */ View d;
        final /* synthetic */ slq e;

        e(HwItemTouchHelperEx.Callback callback, View view, slq slqVar) {
            this.b = callback;
            this.d = view;
            this.e = slqVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = (((Float) valueAnimator.getAnimatedValue()).floatValue() * 0.05f) + 1.0f;
            this.b.updateSelectedScale(floatValue);
            this.d.setScaleX(floatValue);
            this.d.setScaleY(floatValue);
            slq slqVar = this.e;
            if (slqVar != null) {
                slqVar.a(true);
            }
        }
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwItemTouchHelperEx
    public void eff_(View view, HwItemTouchHelperEx.Callback callback, slq slqVar) {
        super.eff_(view, callback, slqVar);
        if (view == null || callback == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), R.interpolator._2131689478_res_0x7f0f0006));
        ofFloat.addUpdateListener(new e(callback, view, slqVar));
        ofFloat.setDuration(150L);
        ofFloat.start();
    }
}
