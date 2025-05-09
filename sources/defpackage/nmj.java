package defpackage;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes6.dex */
public class nmj extends MetricAffectingSpan {
    private final Typeface b;

    public nmj(Typeface typeface) {
        this.b = typeface;
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        cAN_(textPaint);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        cAN_(textPaint);
    }

    private void cAN_(Paint paint) {
        if (paint == null || this.b == null) {
            return;
        }
        Typeface typeface = paint.getTypeface();
        int style = (typeface != null ? typeface.getStyle() : 0) & (~this.b.getStyle());
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(this.b);
    }
}
