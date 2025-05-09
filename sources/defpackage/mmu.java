package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class mmu extends ImageSpan {
    public mmu(Context context, int i) {
        super(context, i);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Drawable drawable = getDrawable();
        if (drawable == null || paint == null) {
            return 0;
        }
        Rect bounds = drawable.getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i3 = fontMetricsInt2.bottom;
            int i4 = fontMetricsInt2.top;
            int i5 = (bounds.bottom - bounds.top) / 2;
            int i6 = (i3 - i4) / 4;
            int i7 = i5 - i6;
            int i8 = -(i5 + i6);
            fontMetricsInt.ascent = i8;
            fontMetricsInt.top = i8;
            fontMetricsInt.bottom = i7;
            fontMetricsInt.descent = i7;
        }
        return bounds.right;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        if (canvas == null) {
            LogUtil.b("Suggestion_VerticalImageSpan", "draw canvas == null");
            return;
        }
        Drawable drawable = getDrawable();
        if (drawable != null) {
            canvas.save();
            canvas.translate(f, (((i5 - i3) - drawable.getBounds().bottom) / 2) + i3);
            drawable.draw(canvas);
            canvas.restore();
        }
    }
}
