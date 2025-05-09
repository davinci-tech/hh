package com.huawei.ui.commonui.spinner;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.uikit.phone.hwspinner.widget.HwSpinner;

/* loaded from: classes6.dex */
public class HealthSpinner extends HwSpinner {
    public HealthSpinner(Context context) {
        super(context);
        b();
    }

    public HealthSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public HealthSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        if (SystemInfo.b()) {
            return;
        }
        setPopupBackgroundResource(R$drawable.spinner_shadow_background);
    }
}
