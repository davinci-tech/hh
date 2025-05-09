package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

/* loaded from: classes6.dex */
public class nmi extends ReplacementSpan {

    /* renamed from: a, reason: collision with root package name */
    private int f15388a;
    private int b;
    private int c;
    private Paint d;
    int e;
    private int f;
    private int g;
    private float h;
    private Paint i;
    private int j;
    private int k;

    private nmi(c cVar) {
        this.b = cVar.d;
        this.h = cVar.g;
        this.f = cVar.b;
        this.f15388a = cVar.f15389a;
        this.j = cVar.c;
        this.k = cVar.h;
        this.g = cVar.j;
        this.c = cVar.e;
        c();
        d();
    }

    private void d() {
        Paint paint = new Paint();
        this.d = paint;
        paint.setColor(this.b);
        this.d.setAntiAlias(true);
        this.d.setStrokeWidth(this.h);
        this.d.setStyle(Paint.Style.STROKE);
    }

    private void c() {
        TextPaint textPaint = new TextPaint();
        this.i = textPaint;
        textPaint.setColor(this.g);
        this.i.setTextSize(this.k);
        this.i.setAntiAlias(true);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return d(charSequence, i, i2);
    }

    private int c(CharSequence charSequence, int i, int i2) {
        if (charSequence.length() <= 0) {
            return 0;
        }
        Rect rect = new Rect();
        this.i.getTextBounds(charSequence.toString(), i, i2, rect);
        int width = rect.width() + (this.f15388a * 2);
        this.e = width;
        return width;
    }

    public int d(CharSequence charSequence, int i, int i2) {
        return c(charSequence, i, i2) + this.c;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f2 = (i4 + fontMetrics.descent) - ((fontMetrics.descent - fontMetrics.ascent) / 2.0f);
        Paint.FontMetrics fontMetrics2 = this.i.getFontMetrics();
        float f3 = fontMetrics2.descent - fontMetrics2.ascent;
        float f4 = f3 / 2.0f;
        float f5 = (f2 - f4) - this.j;
        float f6 = (this.h / 2.0f) + f;
        RectF rectF = new RectF(f6, f5, this.e + f6, f3 + f5 + (r1 * 2));
        float f7 = this.f;
        canvas.drawRoundRect(rectF, f7, f7, this.d);
        canvas.drawText(charSequence.subSequence(i, i2).toString(), f + this.f15388a, f2 + (f4 - fontMetrics2.descent), this.i);
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private int f15389a;
        private int b;
        private int c;
        private int d;
        private int e;
        private float g;
        private int h;
        private int j;

        public c d(int i) {
            this.d = i;
            return this;
        }

        public c f(int i) {
            this.j = i;
            return this;
        }

        public c i(int i) {
            this.h = i;
            return this;
        }

        public c a(float f) {
            this.g = f;
            return this;
        }

        public c e(int i) {
            this.b = i;
            return this;
        }

        public c c(int i) {
            this.c = i;
            return this;
        }

        public c b(int i) {
            this.f15389a = i;
            return this;
        }

        public c a(int i) {
            this.e = i;
            return this;
        }

        public nmi c() {
            return new nmi(this);
        }
    }
}
