package com.huawei.uikit.hwspinner.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class HwSpinnerTextView extends CheckedTextView {

    /* renamed from: a, reason: collision with root package name */
    private float f10748a;
    private TextPaint b;
    private float c;
    private float d;

    public HwSpinnerTextView(Context context) {
        this(context, null);
    }

    private void e(int i, int i2, int i3) {
        int maxLines;
        if (i3 != 0 && (maxLines = getMaxLines()) > 1) {
            int totalPaddingLeft = getTotalPaddingLeft();
            int totalPaddingRight = getTotalPaddingRight();
            int extendedPaddingBottom = (i - getExtendedPaddingBottom()) - getExtendedPaddingTop();
            if (extendedPaddingBottom <= 0) {
                return;
            }
            StaticLayout staticLayout = new StaticLayout(getText(), getPaint(), (i2 - totalPaddingLeft) - totalPaddingRight, Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
            int lineCount = staticLayout.getLineCount();
            if (staticLayout.getHeight() <= extendedPaddingBottom || lineCount <= 1 || lineCount > maxLines + 1) {
                return;
            }
            setMaxLines(lineCount - 1);
        }
    }

    private void ehk_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100186_res_0x7f06021a, R.attr._2131100187_res_0x7f06021b}, i, 0);
        this.d = obtainStyledAttributes.getDimension(0, 0.0f);
        this.c = obtainStyledAttributes.getDimension(1, 0.0f);
        obtainStyledAttributes.recycle();
        if (this.d == 0.0f && this.c == 0.0f) {
            this.d = getAutoSizeMinTextSize();
            this.c = getAutoSizeStepGranularity();
            setAutoSizeTextTypeWithDefaults(0);
        }
        TextPaint textPaint = new TextPaint();
        this.b = textPaint;
        textPaint.set(getPaint());
        this.f10748a = getTextSize();
    }

    @Override // android.view.View
    public boolean isClickable() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        return !(accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) && super.isClickable();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        b(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2), View.MeasureSpec.getMode(i2));
        super.onMeasure(i, i2);
    }

    public void setAutoTextInfo(int i, int i2, int i3) {
        Context context = getContext();
        Resources system = context == null ? Resources.getSystem() : context.getResources();
        this.d = TypedValue.applyDimension(i3, i, system.getDisplayMetrics());
        this.c = TypedValue.applyDimension(i3, i2, system.getDisplayMetrics());
    }

    public void setAutoTextSize(float f) {
        setAutoTextSize(2, f);
    }

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean z) {
        boolean isPressed = isPressed();
        super.setSelected(z);
        if (z || isPressed == isPressed()) {
            return;
        }
        setPressed(isPressed);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        if (this.d <= 0.0f || this.c <= 0.0f) {
            return;
        }
        requestLayout();
    }

    public HwSpinnerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ehk_(context, attributeSet, 0);
    }

    public void setAutoTextSize(int i, float f) {
        Context context = getContext();
        this.f10748a = TypedValue.applyDimension(i, f, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        super.setTextSize(i, f);
    }

    public HwSpinnerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ehk_(context, attributeSet, i);
    }

    private void b(int i, int i2, int i3) {
        int maxWidth = getMaxWidth();
        int maxHeight = getMaxHeight();
        if (maxWidth != -1 && maxWidth < i) {
            i = maxWidth;
        }
        if (maxHeight != -1 && maxHeight < i2) {
            i2 = maxHeight;
        }
        int totalPaddingLeft = (i - getTotalPaddingLeft()) - getTotalPaddingRight();
        if (totalPaddingLeft < 0) {
            return;
        }
        if (this.b == null) {
            this.b = new TextPaint();
        }
        this.b.set(getPaint());
        if (this.d <= 0.0f || this.c <= 0.0f) {
            return;
        }
        float f = this.f10748a;
        CharSequence text = getText();
        TransformationMethod transformationMethod = getTransformationMethod();
        if (transformationMethod != null) {
            text = transformationMethod.getTransformation(text, this);
        }
        this.b.setTextSize(f);
        float measureText = this.b.measureText(text.toString());
        while (measureText > totalPaddingLeft && f > this.d) {
            f -= this.c;
            this.b.setTextSize(f);
            measureText = this.b.measureText(text.toString());
        }
        float f2 = this.d;
        if (f < f2) {
            f = f2;
        }
        setTextSize(0, f);
        e(i2, i, i3);
    }
}
