package com.huawei.health.suggestion.ui.plan.util;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.viewpager.pagetransformer.OverlayLayerPageTransformer;
import health.compact.a.LogUtil;

/* loaded from: classes4.dex */
public class RecommendFoodTransformer extends OverlayLayerPageTransformer {
    private final int d;

    public RecommendFoodTransformer(OverlayLayerPageTransformer.b bVar, int i) {
        super(bVar);
        this.d = i;
    }

    @Override // com.huawei.ui.commonui.viewpager.pagetransformer.OverlayLayerPageTransformer, androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        super.transformPage(view, f);
        View view2 = null;
        for (int i = 0; i < this.d; i++) {
            if (view2 == null) {
                view2 = aIO_(view);
            } else {
                view2 = aIO_(view2);
            }
        }
        if (view2 == null) {
            LogUtil.a("RecommendFoodTransformer", "transformPage childView is null");
            return;
        }
        if (view2.getVisibility() == 8) {
            view2.setVisibility(0);
        }
        view2.setAlpha(f);
    }

    private View aIO_(View view) {
        if (view instanceof ViewGroup) {
            return ((ViewGroup) view).getChildAt(r3.getChildCount() - 1);
        }
        LogUtil.a("RecommendFoodTransformer", "getChildView view ", view);
        return view;
    }
}
