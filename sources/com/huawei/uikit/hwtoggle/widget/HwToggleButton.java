package com.huawei.uikit.hwtoggle.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ToggleButton;
import com.huawei.health.R;
import defpackage.sjx;
import defpackage.smr;

/* loaded from: classes7.dex */
public class HwToggleButton extends ToggleButton {
    private float c;
    private float d;
    private Drawable e;

    public HwToggleButton(Context context) {
        this(context, null);
    }

    private void d(boolean z) {
        sjx.dYl_(this, z ? this.d : this.c).start();
    }

    public Drawable getFocusedDrawable() {
        return this.e;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
    }

    @Override // android.view.View
    public void onHoverChanged(boolean z) {
        super.onHoverChanged(z);
        if (this.d == 1.0f) {
            return;
        }
        d(z);
    }

    public void setFocusedDrawable(Drawable drawable) {
        this.e = drawable;
    }

    public HwToggleButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100589_res_0x7f0603ad);
    }

    public HwToggleButton(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        setDefaultFocusHighlightEnabled(false);
        eil_(getContext(), attributeSet, i);
    }

    private void eil_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100334_res_0x7f0602ae, R.attr._2131100335_res_0x7f0602af, R.attr._2131100336_res_0x7f0602b0, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100341_res_0x7f0602b5, R.attr._2131100588_res_0x7f0603ac}, i, R.style.Widget_Emui_HwToggle);
        this.e = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen._2131362695_res_0x7f0a0387, typedValue, true);
        this.d = typedValue.getFloat();
        getResources().getValue(R.dimen._2131364523_res_0x7f0a0aab, typedValue, true);
        this.c = typedValue.getFloat();
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwToggle);
    }
}
