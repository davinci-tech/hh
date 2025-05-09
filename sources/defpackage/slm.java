package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import java.util.Locale;

/* loaded from: classes7.dex */
public class slm extends Drawable {
    private TextPaint c;
    private Paint e;
    private float f;
    private int g;
    private int h;
    private float i;
    private float j;
    private int k;
    private int l;
    private Context n;
    private int o;
    private int d = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f17105a = 99;
    private String b = "";
    private int m = 2;

    public slm() {
        j();
    }

    private void c(Context context) {
        float f = context.getResources().getConfiguration().fontScale;
        this.j = f;
        if (Float.compare(f, 1.75f) < 0 || !smp.b(this.n)) {
            return;
        }
        this.g = context.getResources().getDimensionPixelSize(R.dimen._2131364246_res_0x7f0a0996);
        this.c.setTextSize(context.getResources().getDimensionPixelSize(R.dimen._2131362710_res_0x7f0a0396));
        this.h = context.getResources().getDimensionPixelSize(R.dimen._2131364246_res_0x7f0a0996);
    }

    public void a(int i) {
        this.e.setColor(i);
        invalidateSelf();
    }

    public int b() {
        return this.f17105a;
    }

    public void b(int i) {
        e(i, this.f17105a);
    }

    public int c() {
        return this.m;
    }

    public void c(int i) {
        if (this.m != i) {
            this.m = i;
            if (Float.compare(this.j, 1.75f) >= 0 && this.m == 2 && smp.b(this.n)) {
                this.g = this.n.getResources().getDimensionPixelSize(R.dimen._2131364246_res_0x7f0a0996);
                this.c.setTextSize(this.n.getResources().getDimensionPixelSize(R.dimen._2131362710_res_0x7f0a0396));
                this.h = this.n.getResources().getDimensionPixelSize(R.dimen._2131364246_res_0x7f0a0996);
            } else {
                this.g = this.n.getResources().getDimensionPixelSize(R.dimen._2131364244_res_0x7f0a0994);
                this.c.setTextSize(this.n.getResources().getDimensionPixelSize(R.dimen._2131362708_res_0x7f0a0394));
                this.h = this.n.getResources().getDimensionPixelSize(R.dimen._2131364245_res_0x7f0a0995);
            }
            e();
        }
    }

    public int d() {
        return this.d;
    }

    public void d(int i) {
        if (this.c.getColor() != i) {
            this.c.setColor(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds;
        if (canvas != null && (bounds = getBounds()) != null && bounds.right > bounds.left && bounds.bottom > bounds.top && this.d > 0) {
            canvas.save();
            int i = this.m;
            if (i == 1) {
                canvas.drawCircle(bounds.left + (bounds.width() / 2.0f), bounds.top + (bounds.height() / 2.0f), this.o / 2.0f, this.e);
            } else if (i == 2) {
                canvas.translate((bounds.width() - this.l) / 2.0f, (bounds.height() - this.o) / 2.0f);
                RectF rectF = new RectF(bounds.left, bounds.top, r1 + this.l, r4 + this.o);
                float f = this.o / 2.0f;
                canvas.drawRoundRect(rectF, f, f, this.e);
                canvas.drawText(this.b, bounds.left + this.f, bounds.top + this.i, this.c);
            } else {
                Log.e("HwEventBadgeDrawable", "invalid badge mode");
            }
            canvas.restore();
        }
    }

    public void e() {
        int i = this.h;
        this.l = i;
        this.o = i;
        int i2 = this.m;
        if (i2 == 1) {
            int i3 = this.g;
            this.l = i3;
            this.o = i3;
        } else if (i2 == 2) {
            float measureText = this.c.measureText(this.b);
            float descent = this.c.descent();
            float ascent = this.c.ascent();
            int i4 = this.d;
            if (i4 > 0 && i4 < 10) {
                int i5 = this.h;
                this.l = i5;
                this.o = i5;
            } else if (i4 >= 10) {
                this.l = Math.round((this.k * 2.0f) + measureText);
                this.o = this.h;
            } else {
                Log.e("HwEventBadgeDrawable", "invalid badge count");
            }
            this.f = (this.l - measureText) / 2.0f;
            this.i = ((this.o - (descent - ascent)) / 2.0f) - this.c.ascent();
        } else {
            Log.e("HwEventBadgeDrawable", "invalid badge mode");
        }
        invalidateSelf();
    }

    public TextPaint edp_() {
        return this.c;
    }

    public void edq_(Context context, AttributeSet attributeSet, int i) {
        edr_(context, attributeSet, i, R.style.Widget_Emui_HwEventBadge);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.e.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.o;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return getAlpha() == 0 ? -2 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.e.setAlpha(i);
        this.c.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        super.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        this.c.setColorFilter(colorFilter);
    }

    public void e(int i, int i2) {
        if (i < 0) {
            Log.w("HwEventBadgeDrawable", "setBadgeCount method: param count must be equals or bigger than zero");
            return;
        }
        if (this.d != i) {
            this.d = i;
        }
        if (this.f17105a != i2) {
            this.f17105a = i2;
        }
        if (this.d <= i2) {
            this.b = String.format(Locale.getDefault(), "%d", Integer.valueOf(this.d));
        } else {
            this.b = String.format(Locale.getDefault(), "%d+", Integer.valueOf(i2));
        }
        e();
    }

    public void edr_(Context context, AttributeSet attributeSet, int i, int i2) {
        if (context == null) {
            return;
        }
        this.n = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100297_res_0x7f060289, R.attr._2131100298_res_0x7f06028a, R.attr._2131100299_res_0x7f06028b, R.attr._2131100300_res_0x7f06028c, R.attr._2131100302_res_0x7f06028e, R.attr._2131100303_res_0x7f06028f, R.attr._2131100304_res_0x7f060290}, i, i2);
        this.g = obtainStyledAttributes.getDimensionPixelSize(1, context.getResources().getDimensionPixelSize(R.dimen._2131364244_res_0x7f0a0994));
        this.m = obtainStyledAttributes.getInt(3, 2);
        this.h = obtainStyledAttributes.getDimensionPixelSize(2, context.getResources().getDimensionPixelSize(R.dimen._2131364245_res_0x7f0a0995));
        this.k = obtainStyledAttributes.getDimensionPixelSize(5, context.getResources().getDimensionPixelSize(R.dimen._2131364247_res_0x7f0a0997));
        this.c.setColor(obtainStyledAttributes.getColor(4, ContextCompat.getColor(context, R.color._2131297592_res_0x7f090538)));
        this.c.setTextSize(obtainStyledAttributes.getDimensionPixelSize(6, context.getResources().getDimensionPixelSize(R.dimen._2131362708_res_0x7f0a0394)));
        this.e.setColor(obtainStyledAttributes.getColor(0, ContextCompat.getColor(context, R.color._2131297449_res_0x7f0904a9)));
        obtainStyledAttributes.recycle();
        e();
        c(context);
    }

    private void j() {
        TextPaint textPaint = new TextPaint();
        this.c = textPaint;
        textPaint.setAntiAlias(true);
        this.c.setFilterBitmap(true);
        Paint paint = new Paint();
        this.e = paint;
        paint.setAntiAlias(true);
    }
}
