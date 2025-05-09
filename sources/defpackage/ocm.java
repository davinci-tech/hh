package defpackage;

import android.graphics.Paint;
import android.text.TextPaint;

/* loaded from: classes6.dex */
public class ocm {
    public static float cVH_(float f, TextPaint textPaint, int i) {
        textPaint.setTextSize(i);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        return (f + ((fontMetrics.bottom - fontMetrics.top) / 2.0f)) - fontMetrics.bottom;
    }
}
