package com.huawei.uikit.hwimagebutton.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.huawei.health.R;
import defpackage.smr;

/* loaded from: classes9.dex */
public class HwImageButton extends ImageButton {
    private int d;

    public HwImageButton(Context context) {
        this(context, null);
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwImageButton);
    }

    public int getFocusPathColor() {
        return this.d;
    }

    public void setClickAnimationEnabled(boolean z) {
    }

    public void setFocusPathColor(int i) {
        this.d = i;
    }

    public void setWaitingEnable(boolean z) {
    }

    public HwImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100370_res_0x7f0602d2);
    }

    private void edF_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100255_res_0x7f06025f, R.attr._2131100335_res_0x7f0602af, R.attr._2131100336_res_0x7f0602b0, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100341_res_0x7f0602b5, R.attr._2131100371_res_0x7f0602d3}, i, 0);
        this.d = obtainStyledAttributes.getColor(3, 0);
        obtainStyledAttributes.recycle();
    }

    public HwImageButton(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        edF_(super.getContext(), attributeSet, i);
    }
}
