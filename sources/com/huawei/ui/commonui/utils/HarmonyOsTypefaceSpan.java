package com.huawei.ui.commonui.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

/* loaded from: classes6.dex */
public class HarmonyOsTypefaceSpan extends TypefaceSpan {
    private final Typeface mTypeface;

    public HarmonyOsTypefaceSpan(Typeface typeface) {
        super(typeface);
        this.mTypeface = typeface;
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        applyHarmonyOsTypeface(textPaint, this.mTypeface);
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        super.updateMeasureState(textPaint);
        applyHarmonyOsTypeface(textPaint, this.mTypeface);
    }

    private void applyHarmonyOsTypeface(Paint paint, Typeface typeface) {
        Typeface typeface2 = paint.getTypeface();
        int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (~typeface.getStyle());
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }
}
