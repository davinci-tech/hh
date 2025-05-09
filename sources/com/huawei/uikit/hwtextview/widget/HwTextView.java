package com.huawei.uikit.hwtextview.widget;

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
import android.widget.TextView;
import com.huawei.health.R;
import defpackage.smr;
import defpackage.sms;

/* loaded from: classes7.dex */
public class HwTextView extends TextView {

    /* renamed from: a, reason: collision with root package name */
    private StaticLayout f10764a;
    private float b;
    private boolean c;
    private float d;
    private float e;
    private TextPaint f;

    public HwTextView(Context context) {
        this(context, null);
    }

    private static Context b(Context context, int i) {
        return smr.b(context, i, 2131952166);
    }

    private void b(int i, int i2, int i3) {
        int maxLines;
        if (i3 != 0 && (maxLines = getMaxLines()) > 1) {
            int totalPaddingLeft = getTotalPaddingLeft();
            int totalPaddingRight = getTotalPaddingRight();
            int extendedPaddingBottom = (i - getExtendedPaddingBottom()) - getExtendedPaddingTop();
            if (extendedPaddingBottom <= 0) {
                return;
            }
            StaticLayout staticLayout = new StaticLayout(getText(), this.f, (i2 - totalPaddingLeft) - totalPaddingRight, Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
            this.f10764a = staticLayout;
            int lineCount = staticLayout.getLineCount();
            if (this.f10764a.getHeight() <= extendedPaddingBottom || lineCount <= 1 || lineCount > maxLines + 1) {
                return;
            }
            setMaxLines(lineCount - 1);
        }
    }

    public static HwTextView c(Context context) {
        Object e = sms.e(context, sms.e(context, (Class<?>) HwTextView.class, sms.c(context, 15, 1)), (Class<?>) HwTextView.class);
        if (e instanceof HwTextView) {
            return (HwTextView) e;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        a(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2), View.MeasureSpec.getMode(i2));
        super.onMeasure(i, i2);
    }

    public void setAutoTextInfo(int i, int i2, int i3) {
        Context context = getContext();
        Resources system = context == null ? Resources.getSystem() : context.getResources();
        this.d = TypedValue.applyDimension(i3, i, system.getDisplayMetrics());
        this.e = TypedValue.applyDimension(i3, i2, system.getDisplayMetrics());
    }

    public void setAutoTextSize(float f) {
        setAutoTextSize(2, f);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        if (this.d <= 0.0f || this.e <= 0.0f) {
            return;
        }
        requestLayout();
    }

    public HwTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100574_res_0x7f06039e);
    }

    public void setAutoTextSize(int i, float f) {
        Context context = getContext();
        this.b = TypedValue.applyDimension(i, f, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        super.setTextSize(i, f);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
    }

    public HwTextView(Context context, AttributeSet attributeSet, int i) {
        super(b(context, i), attributeSet, i);
        ehW_(super.getContext(), attributeSet, i);
    }

    private void ehW_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100186_res_0x7f06021a, R.attr._2131100187_res_0x7f06021b, R.attr._2131100188_res_0x7f06021c}, i, 2131952651);
        this.d = obtainStyledAttributes.getDimension(0, 0.0f);
        this.e = obtainStyledAttributes.getDimension(1, 0.0f);
        this.c = obtainStyledAttributes.getInt(2, 0) == 1;
        obtainStyledAttributes.recycle();
        if (this.d == 0.0f && this.e == 0.0f) {
            this.d = getAutoSizeMinTextSize();
            this.e = getAutoSizeStepGranularity();
            setAutoSizeTextTypeWithDefaults(0);
        }
        TextPaint textPaint = new TextPaint();
        this.f = textPaint;
        textPaint.set(getPaint());
        this.b = getTextSize();
    }

    private void a(int i, int i2, int i3) {
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
        if (this.f == null) {
            this.f = new TextPaint();
        }
        this.f.set(getPaint());
        d(i, i2, totalPaddingLeft, i3);
    }

    private void d(int i, int i2, int i3, int i4) {
        if (!(this.c && getMaxLines() == Integer.MAX_VALUE) && this.d > 0.0f && this.e > 0.0f) {
            float f = this.b;
            CharSequence text = getText();
            TransformationMethod transformationMethod = getTransformationMethod();
            if (transformationMethod != null) {
                text = transformationMethod.getTransformation(text, this);
            }
            this.f.setTextSize(f);
            float measureText = this.f.measureText(text.toString());
            while (b(measureText, i3, f)) {
                f -= this.e;
                this.f.setTextSize(f);
                measureText = this.f.measureText(text.toString());
            }
            float f2 = this.d;
            if (f < f2) {
                f = f2;
            }
            setTextSize(0, f);
            b(i2, i, i4);
        }
    }

    private boolean b(float f, int i, float f2) {
        return (!this.c || getMaxLines() == 1) ? f > ((float) i) && f2 > this.d : c(i) > getMaxLines() && f2 > this.d;
    }

    private int c(int i) {
        CharSequence text = getText();
        if (text == null) {
            return 0;
        }
        return StaticLayout.Builder.obtain(text, 0, text.length(), this.f, i).build().getLineCount();
    }
}
