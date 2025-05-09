package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: classes8.dex */
public class FitnessDummyViewHolder extends AbsFitnessViewHolder<View> {
    private ViewGroup e;

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: aFz_, reason: merged with bridge method [inline-methods] */
    public void init(View view) {
        ViewParent parent;
        if (view == null || this.e == null || (parent = view.getParent()) == this.e) {
            return;
        }
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        this.e.addView(view);
    }
}
