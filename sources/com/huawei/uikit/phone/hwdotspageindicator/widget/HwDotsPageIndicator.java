package com.huawei.uikit.phone.hwdotspageindicator.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.huawei.health.R;

/* loaded from: classes7.dex */
public class HwDotsPageIndicator extends com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator {
    public HwDotsPageIndicator(Context context) {
        this(context, null);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator, android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        ecl_(canvas, getCurrentBgColor());
        if (d()) {
            eck_(canvas, null);
        }
    }

    public HwDotsPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100278_res_0x7f060276);
    }

    public HwDotsPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
