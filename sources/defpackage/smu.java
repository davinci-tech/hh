package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.huawei.health.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class smu extends Drawable {
    private int g;
    private int i;
    private Interpolator m;
    private Animator n;
    private int d = 0;
    private Paint e = new Paint(1);

    /* renamed from: a, reason: collision with root package name */
    private RectF f17125a = new RectF();
    private int b = 8;
    private int c = 8;
    private boolean h = false;
    private int f = 0;
    private int j = 0;
    private int o = 255;
    private float k = 0.0f;
    private boolean l = false;
    private long r = 150;
    private long s = 150;

    public smu() {
        this.e.setStyle(Paint.Style.FILL);
    }

    private boolean a(boolean z) {
        if (this.l == z) {
            return false;
        }
        this.l = z;
        return true;
    }

    private int b(int i, int i2) {
        return ((((i2 >>> 24) * (i + (i >> 7))) >> 8) << 24) | ((i2 << 8) >>> 8);
    }

    public void a(int i) {
        this.j = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.c = i;
    }

    public void c(long j) {
        this.s = j;
    }

    public void d(int i) {
        if (this.g != i) {
            this.g = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i = this.j;
        int i2 = this.f;
        if (i2 != 0) {
            i = i2;
        }
        this.e.setColor(b(this.o, i));
        RectF rectF = this.f17125a;
        Rect bounds = getBounds();
        if (a()) {
            rectF.left = bounds.left + this.d;
            rectF.right = rectF.left + this.g;
        } else {
            rectF.right = bounds.right - this.d;
            rectF.left = rectF.right - this.g;
        }
        rectF.top = bounds.top;
        rectF.bottom = bounds.bottom;
        float f = this.k;
        if (Float.compare(f, 0.0f) <= 0) {
            f = rectF.width() * 0.5f;
        }
        canvas.drawRoundRect(rectF, f, f, this.e);
    }

    public void e(int i) {
        this.d = i;
    }

    public void e(long j) {
        this.r = j;
    }

    public void egI_(Interpolator interpolator) {
        this.m = interpolator;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return (this.f >>> 24) == 255 ? -1 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws IOException, XmlPullParserException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray egF_ = egF_(resources, theme, attributeSet, new int[]{R.attr._2131100490_res_0x7f06034a, R.attr._2131100491_res_0x7f06034b, R.attr._2131100492_res_0x7f06034c, R.attr._2131100493_res_0x7f06034d, R.attr._2131100494_res_0x7f06034e, R.attr._2131100495_res_0x7f06034f, R.attr._2131100496_res_0x7f060350});
        egG_(null, egF_);
        egF_.recycle();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        if (e(iArr)) {
            this.i = this.c;
            z = true;
        } else {
            this.i = this.b;
            z = false;
        }
        if (!a(z)) {
            return false;
        }
        Animator animator = this.n;
        if (animator != null && animator.isRunning()) {
            this.n.cancel();
        }
        Animator egE_ = egE_(z);
        this.n = egE_;
        egE_.start();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.o != i) {
            this.o = i;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        this.h = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        this.f = i;
        invalidateSelf();
    }

    private boolean a() {
        return isAutoMirrored() && getLayoutDirection() == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
    }

    private boolean e(int[] iArr) {
        return b(iArr, android.R.attr.state_pressed);
    }

    private Animator egE_(boolean z) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "scrollbarWidth", this.g, this.i);
        Interpolator interpolator = this.m;
        if (interpolator != null) {
            ofInt.setInterpolator(interpolator);
        }
        if (z) {
            ofInt.setDuration(this.r);
        } else {
            ofInt.setDuration(this.s);
        }
        return ofInt;
    }

    public void egH_(Context context, AttributeSet attributeSet, int i) {
        if (context == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100490_res_0x7f06034a, R.attr._2131100491_res_0x7f06034b, R.attr._2131100492_res_0x7f06034c, R.attr._2131100493_res_0x7f06034d, R.attr._2131100494_res_0x7f06034e, R.attr._2131100495_res_0x7f06034f, R.attr._2131100496_res_0x7f060350}, i, R.style.Widget_Emui_HwScrollbarDrawable);
        egG_(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private boolean b(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        int length = iArr.length;
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private void egG_(Context context, TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(4, 8);
        b(dimensionPixelSize);
        d(dimensionPixelSize);
        c(typedArray.getDimensionPixelSize(0, 8));
        e(typedArray.getDimensionPixelSize(2, 8));
        a(typedArray.getColor(1, 0));
        int resourceId = typedArray.getResourceId(3, android.R.anim.linear_interpolator);
        if (resourceId > 0 && context != null) {
            egI_(AnimationUtils.loadInterpolator(context, resourceId));
        }
        e(typedArray.getInt(5, 150));
        c(typedArray.getInt(6, 150));
    }

    private TypedArray egF_(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
