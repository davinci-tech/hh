package com.huawei.ui.commonui.dotspageindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import com.huawei.uikit.phone.hwdotspageindicator.widget.HwDotsPageIndicator;

/* loaded from: classes6.dex */
public class HealthDotsPageIndicator extends HwDotsPageIndicator {
    public HealthDotsPageIndicator(Context context) {
        super(context);
    }

    public HealthDotsPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthDotsPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator
    public void setViewPager(HwViewPager hwViewPager) {
        super.setViewPager(hwViewPager);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator
    public void c() {
        super.c();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator
    public void setOnPageChangeListener(HwViewPager.OnPageChangeListener onPageChangeListener) {
        super.setOnPageChangeListener(onPageChangeListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator
    public void setRtlEnable(boolean z) {
        super.setRtlEnable(z);
    }
}
