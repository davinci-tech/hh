package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes7.dex */
public class sly extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private int f17118a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private Paint g;
    private RectF h;
    private ColorStateList i;
    private Paint j;
    private ColorStateList l;

    public sly() {
        Paint paint = new Paint(1);
        this.j = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.j.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint(1);
        this.g = paint2;
        paint2.setStrokeCap(Paint.Cap.ROUND);
        this.h = new RectF();
    }

    private boolean e() {
        boolean z;
        int colorForState;
        int colorForState2;
        int[] state = getState();
        ColorStateList colorStateList = this.i;
        boolean z2 = true;
        if (colorStateList == null || (colorForState2 = colorStateList.getColorForState(state, this.b)) == this.b) {
            z = false;
        } else {
            this.b = colorForState2;
            z = true;
        }
        ColorStateList colorStateList2 = this.l;
        if (colorStateList2 == null || (colorForState = colorStateList2.getColorForState(state, this.d)) == this.d) {
            z2 = z;
        } else {
            this.d = colorForState;
        }
        if (z2) {
            invalidateSelf();
        }
        return z2;
    }

    public void a(int i) {
        if (i != this.c) {
            this.c = i;
            this.j.setStrokeWidth(i);
            invalidateSelf();
        }
    }

    public void b(int i) {
        this.i = ColorStateList.valueOf(i);
        e();
    }

    public void c(float f) {
        if (Float.floatToIntBits(f) != Float.floatToIntBits(this.f)) {
            this.f = f;
            invalidateSelf();
        }
    }

    public void c(int i) {
        if (i != this.e) {
            this.e = i;
            this.g.setStrokeWidth(i);
            invalidateSelf();
        }
    }

    public void d(int i) {
        this.l = ColorStateList.valueOf(i);
        e();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.h == null) {
            return;
        }
        canvas.save();
        int i = this.f17118a;
        if (i == 1) {
            Paint paint = this.j;
            if (paint != null) {
                float f = this.f * 360.0f;
                paint.setColor(this.d);
                canvas.drawArc(this.h, f - 90.0f, 360.0f - f, false, this.j);
                this.j.setColor(this.b);
                canvas.drawArc(this.h, -90.0f, f, false, this.j);
            }
        } else if (i == 2 && this.g != null) {
            int round = Math.round(this.f * 120.0f);
            RectF rectF = this.h;
            float f2 = (rectF.left + rectF.right) * 0.5f;
            float f3 = rectF.top;
            float f4 = rectF.bottom;
            for (int i2 = 0; i2 < 120; i2++) {
                if (i2 < round) {
                    this.g.setColor(this.b);
                } else {
                    this.g.setColor(this.d);
                }
                float f5 = this.e * 0.5f;
                canvas.drawLine(f2, this.c + f5, f2, f5, this.g);
                canvas.rotate(3.0f, f2, (f3 + f4) * 0.5f);
            }
        }
        canvas.restore();
    }

    public void e(int i) {
        if (i != this.f17118a) {
            this.f17118a = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        RectF rectF = this.h;
        float f = this.c * 0.5f;
        rectF.left = rect.left + f;
        rectF.top = rect.top + f;
        rectF.right = rect.right - f;
        rectF.bottom = rect.bottom - f;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        return e();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
