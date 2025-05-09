package com.huawei.ui.commonui.progressbar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwprogressbar.widget.HwProgressBar;

/* loaded from: classes6.dex */
public class HealthProgressBar extends HwProgressBar {
    public HealthProgressBar(Context context) {
        super(context);
        b();
    }

    public HealthProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public HealthProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    public void d(Context context, int i, int i2) {
        d(context, i, -1, i2);
    }

    private void b() {
        if (Build.VERSION.SDK_INT >= 28) {
            setLayerType(1, null);
        }
    }

    private void d(Context context, int i, int i2, int i3) {
        if (i != -1) {
            setBackgroundTintList(context.getResources().getColorStateList(i));
        }
        if (i2 != -1) {
            setSecondaryProgressTintList(context.getResources().getColorStateList(i2));
        }
        setProgressTintList(context.getResources().getColorStateList(i3));
    }
}
