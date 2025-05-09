package com.huawei.ui.commonui.viewpager.carouselsviewpager;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes6.dex */
public class CarouselsTransformer implements ViewPager.PageTransformer {
    private int c;
    private ViewPager d;

    public CarouselsTransformer(ViewPager viewPager) {
        this.d = viewPager;
        this.c = viewPager.getOffscreenPageLimit() + 1;
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        view.setVisibility(0);
        float width = this.d.getWidth();
        view.setTranslationX((int) ((((((width - (width * 0.6982f)) / 2.0f) / this.c) - view.getWidth()) * f * 0.7395f) + 342.0f));
        if (f > -3.0f && f < -2.0f) {
            view.setAlpha(f + 3.0f);
        } else if (f <= -3.0f) {
            view.setAlpha(0.0f);
            view.setVisibility(8);
        } else {
            view.setAlpha(1.0f);
            view.setVisibility(0);
        }
        float f2 = (f <= -3.0f || f >= -2.0f) ? 0.6982f - (0.1509f * f) : (((3.0f + f) - 1.0f) * 0.1509f) + 1.0f;
        view.setPivotX(0.0f);
        view.setPivotY(view.getTop());
        view.setScaleX(f2);
        view.setScaleY(f2);
        ViewCompat.setElevation(view, (this.c - f) * 15.0f);
    }
}
