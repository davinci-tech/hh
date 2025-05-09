package com.huawei.openalliance.ad.views;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes9.dex */
public class PPSCircleProgressBar extends View {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f7858a;
    private int b;
    private float c;
    private int d;
    private int e;
    private int f;
    private float g;
    private float h;
    private int i;
    private float j;
    private int k;
    private Paint l;
    private String m;
    private Rect n;
    private int o;
    private ValueAnimator p;

    public void setTextSize(float f) {
        synchronized (this.f7858a) {
            this.g = f;
        }
    }

    public void setTextColor(int i) {
        synchronized (this.f7858a) {
            this.f = i;
        }
    }

    public void setStartPoint(int i) {
        synchronized (this.f7858a) {
            this.k = i;
        }
    }

    public void setProgressWidth(float f) {
        synchronized (this.f7858a) {
            this.h = f;
        }
    }

    public void setProgress(float f) {
        synchronized (this.f7858a) {
            if (f < 0.0f) {
                f = 0.0f;
            }
            float f2 = this.i;
            if (f > f2) {
                f = f2;
            }
            b(f);
        }
    }

    public void setOuterRadius(float f) {
        synchronized (this.f7858a) {
            this.c = f;
        }
    }

    public void setOuterColor(int i) {
        synchronized (this.f7858a) {
            this.b = i;
        }
    }

    public void setMaxProgress(int i) {
        synchronized (this.f7858a) {
            this.i = i;
        }
    }

    public void setInnerColor(int i) {
        synchronized (this.f7858a) {
            this.d = i;
        }
    }

    public void setCurrentText(String str) {
        synchronized (this.f7858a) {
            this.m = str;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        synchronized (this.f7858a) {
            try {
                int size = View.MeasureSpec.getSize(i);
                if (View.MeasureSpec.getMode(i) != 1073741824) {
                    size = (int) ((this.c * 2.0f) + this.h);
                }
                int size2 = View.MeasureSpec.getSize(i2);
                if (View.MeasureSpec.getMode(i2) != 1073741824) {
                    size2 = (int) ((this.c * 2.0f) + this.h);
                }
                setMeasuredDimension(size, size2);
            } catch (Throwable unused) {
                ho.c("PPSCircleProgressBar", "onMeasure error.");
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        synchronized (this.f7858a) {
            try {
                super.onDraw(canvas);
                int width = getWidth() / 2;
                this.l.setColor(this.e);
                this.l.setStyle(Paint.Style.FILL);
                this.l.setAntiAlias(true);
                float f = width;
                canvas.drawCircle(f, f, this.c, this.l);
                this.l.setColor(this.d);
                this.l.setStyle(Paint.Style.STROKE);
                this.l.setStrokeWidth(this.h);
                this.l.setAntiAlias(true);
                canvas.drawCircle(f, f, this.c, this.l);
                this.l.setColor(this.b);
                float f2 = this.c;
                float f3 = f - f2;
                float f4 = f + f2;
                canvas.drawArc(new RectF(f3, f3, f4, f4), a.c(this.k), (this.j / this.i) * 360.0f, false, this.l);
                this.n = new Rect();
                this.l.setColor(this.f);
                this.l.setStyle(Paint.Style.FILL);
                this.l.setTextSize(a(this.m, this.g));
                this.l.setStrokeWidth(0.0f);
                String currentText = getCurrentText();
                this.m = currentText;
                this.l.getTextBounds(currentText, 0, currentText.length(), this.n);
                this.l.setTextAlign(Paint.Align.LEFT);
                Paint.FontMetricsInt fontMetricsInt = this.l.getFontMetricsInt();
                int measuredHeight = ((getMeasuredHeight() - fontMetricsInt.bottom) + fontMetricsInt.top) / 2;
                int i = fontMetricsInt.top;
                canvas.drawText(this.m, (getMeasuredWidth() / 2) - (this.n.width() / 2), measuredHeight - i, this.l);
            } catch (Throwable unused) {
                ho.c("PPSCircleProgressBar", "onDraw error.");
            }
        }
    }

    public float getTextSize() {
        float f;
        synchronized (this.f7858a) {
            f = this.g;
        }
        return f;
    }

    public int getTextColor() {
        int i;
        synchronized (this.f7858a) {
            i = this.f;
        }
        return i;
    }

    public int getStartPoint() {
        int i;
        synchronized (this.f7858a) {
            i = this.k;
        }
        return i;
    }

    public float getProgressWidth() {
        float f;
        synchronized (this.f7858a) {
            f = this.h;
        }
        return f;
    }

    public float getProgress() {
        float f;
        synchronized (this.f7858a) {
            f = this.j;
        }
        return f;
    }

    public float getOuterRadius() {
        float f;
        synchronized (this.f7858a) {
            f = this.c;
        }
        return f;
    }

    public int getOuterColor() {
        int i;
        synchronized (this.f7858a) {
            i = this.b;
        }
        return i;
    }

    public int getMaxProgress() {
        int i;
        synchronized (this.f7858a) {
            i = this.i;
        }
        return i;
    }

    public int getInnerColor() {
        int i;
        synchronized (this.f7858a) {
            i = this.d;
        }
        return i;
    }

    public String getCurrentText() {
        String str;
        synchronized (this.f7858a) {
            str = TextUtils.isEmpty(this.m) ? "" : this.m;
        }
        return str;
    }

    public void a(float f, String str) {
        setCurrentText(str);
        setProgress(f);
    }

    private int getProgressBarSize() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int width = getWidth();
        return width <= 0 ? layoutParams.width : width;
    }

    private int getPaddingSize() {
        int paddingStart = getPaddingStart();
        if (paddingStart <= 0) {
            paddingStart = getPaddingLeft();
        }
        int paddingEnd = getPaddingEnd();
        if (paddingEnd <= 0) {
            paddingEnd = getPaddingRight();
        }
        return paddingStart + paddingEnd;
    }

    private void c(float f) {
        synchronized (this.f7858a) {
            ValueAnimator ofFloat = ObjectAnimator.ofFloat(this.j, f);
            this.p = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.openalliance.ad.views.PPSCircleProgressBar.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PPSCircleProgressBar.this.j = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    PPSCircleProgressBar.this.postInvalidate();
                }
            });
            this.p.setDuration(1000L);
            this.p.setInterpolator(new LinearInterpolator());
            this.p.start();
        }
    }

    private void b(float f) {
        synchronized (this.f7858a) {
            c(f);
        }
    }

    private boolean a(CharSequence charSequence, int i, int i2, int i3) {
        float c = ao.c(getContext(), i);
        if (i3 < 0) {
            return true;
        }
        this.l.setTextSize(c);
        this.l.getTextBounds(charSequence.toString(), 0, charSequence.length(), this.n);
        return this.n.width() + i2 <= i3;
    }

    enum a {
        LEFT(0, 180.0f),
        TOP(1, 270.0f),
        RIGHT(2, 0.0f),
        BOTTOM(3, 90.0f);

        private final int e;
        private final float f;

        public float b() {
            return this.f;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public int a() {
            return this.e;
        }

        public static float c(int i) {
            a b = b(i);
            if (b == null) {
                return 0.0f;
            }
            return b.b();
        }

        public static a b(int i) {
            for (a aVar : values()) {
                if (aVar.a(i)) {
                    return aVar;
                }
            }
            return RIGHT;
        }

        a(int i, float f) {
            this.e = i;
            this.f = f;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [android.content.res.TypedArray] */
    private void a(Context context, AttributeSet attributeSet) {
        String str;
        String str2;
        Resources resources;
        synchronized (this.f7858a) {
            if (attributeSet != 0) {
                try {
                    attributeSet = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100971_res_0x7f06052b, R.attr._2131100972_res_0x7f06052c, R.attr._2131100973_res_0x7f06052d, R.attr._2131100974_res_0x7f06052e, R.attr._2131100975_res_0x7f06052f, R.attr._2131100976_res_0x7f060530, R.attr._2131100977_res_0x7f060531, R.attr._2131100978_res_0x7f060532, R.attr._2131100979_res_0x7f060533, R.attr._2131100980_res_0x7f060534});
                    try {
                        resources = getResources();
                    } catch (UnsupportedOperationException unused) {
                        str = "PPSCircleProgressBar";
                        str2 = "initButtonAttr UnsupportedOperationException";
                        ho.c(str, str2);
                        attributeSet.recycle();
                        this.l = new Paint();
                    } catch (RuntimeException unused2) {
                        str = "PPSCircleProgressBar";
                        str2 = "initButtonAttr RuntimeException";
                        ho.c(str, str2);
                        attributeSet.recycle();
                        this.l = new Paint();
                    } catch (Throwable th) {
                        ho.c("PPSCircleProgressBar", "initButtonAttr error: " + th.getClass().getSimpleName());
                        attributeSet.recycle();
                        this.l = new Paint();
                    }
                    if (resources == null) {
                        ho.c("PPSCircleProgressBar", "init attr, resource is null");
                        return;
                    }
                    this.b = attributeSet.getColor(3, resources.getColor(R.color._2131297931_res_0x7f09068b));
                    this.c = attributeSet.getDimension(4, resources.getDimension(R.dimen._2131363283_res_0x7f0a05d3));
                    this.d = attributeSet.getColor(1, resources.getColor(R.color._2131297930_res_0x7f09068a));
                    this.f = attributeSet.getColor(8, resources.getColor(R.color._2131297932_res_0x7f09068c));
                    this.e = attributeSet.getColor(0, resources.getColor(R.color._2131297929_res_0x7f090689));
                    this.g = attributeSet.getDimension(9, ao.c(context, 18.0f));
                    this.h = attributeSet.getDimension(6, ao.a(context, 2.0f));
                    this.j = attributeSet.getFloat(5, 0.0f);
                    this.i = attributeSet.getInt(2, 100);
                    this.k = attributeSet.getInt(7, a.BOTTOM.a());
                    attributeSet.recycle();
                    this.l = new Paint();
                } finally {
                    attributeSet.recycle();
                }
            }
        }
    }

    private void a(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        Rect rect = new Rect();
        paint.getTextBounds("...", 0, 3, rect);
        this.o = rect.width();
    }

    private void a() {
        a(this.g);
    }

    private CharSequence a(CharSequence charSequence, int i, int i2) {
        int length = getCurrentText().length();
        int ceil = (int) Math.ceil(((i - i2) / this.n.width()) * length);
        int ceil2 = (int) Math.ceil((this.o * length) / this.n.width());
        int i3 = length - ceil;
        if (i3 - ceil2 <= 0) {
            return i3 > 0 ? charSequence.toString().substring(0, i3) : charSequence;
        }
        return charSequence.toString().substring(0, length - (ceil + ceil2)) + "...";
    }

    private float a(CharSequence charSequence, float f) {
        int paddingSize = getPaddingSize();
        int progressBarSize = getProgressBarSize();
        int d = ao.d(getContext(), f);
        while (d > 10 && !a(charSequence, d, paddingSize, progressBarSize)) {
            d--;
        }
        if (d <= 10 && !a(charSequence, d, paddingSize, progressBarSize)) {
            this.m = (String) a(this.m, this.n.width() + getPaddingSize(), getProgressBarSize());
            this.l.getTextBounds(charSequence.toString(), 0, charSequence.length(), this.n);
        }
        float c = ao.c(getContext(), d);
        a(c);
        ho.a("PPSCircleProgressBar", "getRightTextSize, currentSpSize: %s ", Float.valueOf(c));
        return c;
    }

    public PPSCircleProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet);
        a(context, attributeSet);
        a();
    }

    public PPSCircleProgressBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
        a(context, attributeSet);
        a();
    }

    public PPSCircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7858a = new byte[0];
        a(context, attributeSet);
        a();
    }

    public PPSCircleProgressBar(Context context) {
        this(context, null);
    }
}
