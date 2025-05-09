package com.huawei.uikit.phone.hwbutton.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.health.R;
import com.huawei.uikit.hwbutton.R$dimen;
import defpackage.smp;

/* loaded from: classes7.dex */
public class HwButton extends com.huawei.uikit.hwbutton.widget.HwButton {
    public HwButton(Context context) {
        this(context, null);
    }

    private void ejh_(Canvas canvas) {
        Drawable focusedDrawable;
        if (isFocusable() && hasWindowFocus() && (focusedDrawable = getFocusedDrawable()) != null && isFocused()) {
            canvas.translate(getScrollX(), getScrollY());
            focusedDrawable.setBounds((-getFocusedPathPadding()) - getFocusedPathWidth(), (-getFocusedPathPadding()) - getFocusedPathWidth(), getWidth() + getFocusedPathPadding() + getFocusedPathWidth(), getHeight() + getFocusedPathPadding() + getFocusedPathWidth());
            focusedDrawable.draw(canvas);
            canvas.translate(-getScrollX(), -getScrollY());
        }
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

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        ejh_(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (getFocusedDrawable() != null) {
            invalidate();
        }
    }

    public HwButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100223_res_0x7f06023f);
    }

    public HwButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100190_res_0x7f06021e, R.attr._2131100222_res_0x7f06023e, R.attr._2131100224_res_0x7f060240, R.attr._2131100225_res_0x7f060241, R.attr._2131100226_res_0x7f060242, R.attr._2131100227_res_0x7f060243, R.attr._2131100228_res_0x7f060244, R.attr._2131100229_res_0x7f060245, R.attr._2131100255_res_0x7f06025f, R.attr._2131100334_res_0x7f0602ae, R.attr._2131100335_res_0x7f0602af, R.attr._2131100336_res_0x7f0602b0, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100339_res_0x7f0602b3, R.attr._2131100341_res_0x7f0602b5, R.attr._2131100362_res_0x7f0602ca}, i, 0);
        if (obtainStyledAttributes.getBoolean(0, true) && Float.compare(smp.a(context), 1.75f) >= 0) {
            setMaxLines(2);
            int i2 = obtainStyledAttributes.getInt(2, 0);
            if (i2 == 0) {
                setAutoTextInfo(13, 1, 2);
                int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.hwbutton_big_padding_top_or_bottom);
                setPadding(getPaddingLeft(), dimensionPixelSize, getPaddingRight(), dimensionPixelSize);
            }
            if (i2 == 1) {
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.hwbutton_small_padding_top_or_bottom);
                setPadding(getPaddingLeft(), dimensionPixelSize2, getPaddingRight(), dimensionPixelSize2);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
