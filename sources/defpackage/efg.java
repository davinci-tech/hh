package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* loaded from: classes3.dex */
public class efg extends ImageSpan {
    public efg(Drawable drawable) {
        super(drawable);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int i6 = (((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2;
        int i7 = drawable.getBounds().bottom / 2;
        canvas.save();
        canvas.translate(f, i6 - i7);
        drawable.draw(canvas);
        canvas.restore();
    }
}
