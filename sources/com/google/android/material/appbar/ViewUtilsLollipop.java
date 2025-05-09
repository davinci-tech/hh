package com.google.android.material.appbar;

import android.R;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes8.dex */
class ViewUtilsLollipop {
    private static final int[] STATE_LIST_ANIM_ATTRS = {R.attr.stateListAnimator};

    ViewUtilsLollipop() {
    }

    static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    static void setStateListAnimatorFromAttrs(View view, AttributeSet attributeSet, int i, int i2) {
        Context context = view.getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, STATE_LIST_ANIM_ATTRS, i, i2, new int[0]);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, obtainStyledAttributes.getResourceId(0, 0)));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    static void setDefaultAppBarLayoutStateListAnimator(View view, float f) {
        int integer = view.getResources().getInteger(com.huawei.health.R.integer._2131623940_res_0x7f0e0004);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j = integer;
        stateListAnimator.addState(new int[]{R.attr.enabled, com.huawei.health.R.attr._2131101144_res_0x7f0605d8, -2131101145}, ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(j));
        stateListAnimator.addState(new int[]{R.attr.enabled}, ObjectAnimator.ofFloat(view, "elevation", f).setDuration(j));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(0L));
        view.setStateListAnimator(stateListAnimator);
    }
}
