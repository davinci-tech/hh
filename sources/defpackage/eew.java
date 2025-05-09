package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* loaded from: classes3.dex */
public class eew extends ReplacementSpan {
    private float c;

    public eew(int i) {
        this.c = i;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        canvas.drawText(charSequence, i, i2, f, i4, paint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return Math.round(paint.measureText(charSequence, i, i2) + this.c);
    }
}
