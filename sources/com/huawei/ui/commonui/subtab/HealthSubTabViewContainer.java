package com.huawei.ui.commonui.subtab;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer;

/* loaded from: classes9.dex */
public class HealthSubTabViewContainer extends HwSubTabViewContainer {
    public HealthSubTabViewContainer(Context context) {
        super(context);
    }

    public HealthSubTabViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthSubTabViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer, android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer, android.widget.HorizontalScrollView, android.view.View
    public float getRightFadingEdgeStrength() {
        return super.getRightFadingEdgeStrength();
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer, android.widget.HorizontalScrollView, android.view.View
    public float getLeftFadingEdgeStrength() {
        return super.getLeftFadingEdgeStrength();
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer
    public HwSubTabViewContainer.SlidingTabStrip getTabStrip() {
        return super.getTabStrip();
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer
    public void a(int i) {
        super.a(i);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer
    public void setScrollPosition(int i, float f) {
        super.setScrollPosition(i, f);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer
    public void setScrollPosition(int i, float f, boolean z) {
        super.setScrollPosition(i, f, z);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer
    public boolean e() {
        return super.e();
    }
}
