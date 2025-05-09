package com.huawei.uikit.hwcardview.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import com.huawei.health.R;
import defpackage.smr;

/* loaded from: classes9.dex */
public class HwCardView extends CardView {
    public HwCardView(Context context) {
        this(context, null);
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwCardView);
    }

    public HwCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100231_res_0x7f060247);
    }

    public HwCardView(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
    }
}
