package com.huawei.ui.device.activity.selectcontact;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

/* loaded from: classes9.dex */
public class FitWindowsRelativeLayout extends RelativeLayout {
    public FitWindowsRelativeLayout(Context context) {
        super(context);
    }

    public FitWindowsRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FitWindowsRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        return super.fitSystemWindows(rect);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        return super.onApplyWindowInsets(windowInsets.replaceSystemWindowInsets(0, 0, 0, windowInsets.getSystemWindowInsetBottom()));
    }
}
