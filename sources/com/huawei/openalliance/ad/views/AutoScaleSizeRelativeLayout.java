package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class AutoScaleSizeRelativeLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Float f7791a;
    private final RectF b;
    private float c;
    private Path d;
    private boolean e;

    protected boolean p() {
        return false;
    }

    public void setUseRatioInMatchParentMode(boolean z) {
        this.e = z;
    }

    public void setRectCornerRadius(float f) {
        this.c = f;
        a();
        postInvalidate();
    }

    public void setRatio(Float f) {
        this.f7791a = f;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        float floatValue;
        try {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (!this.e && layoutParams != null && layoutParams.width == -1 && layoutParams.height == -1) {
                super.onMeasure(i, i2);
                return;
            }
            Float f = this.f7791a;
            if (f != null && f.floatValue() > 0.01f) {
                int size = View.MeasureSpec.getSize(i);
                int size2 = View.MeasureSpec.getSize(i2);
                if (View.MeasureSpec.getMode(i2) != 1073741824 && (size <= 0 || size2 <= 0)) {
                    floatValue = this.f7791a.floatValue();
                    size2 = a(size, floatValue);
                    i = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                    i2 = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
                }
                if ((size * 1.0f) / size2 > this.f7791a.floatValue()) {
                    size = b(size2, this.f7791a.floatValue());
                    i = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                    i2 = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
                } else {
                    floatValue = this.f7791a.floatValue();
                    size2 = a(size, floatValue);
                    i = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                    i2 = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
                }
            }
            super.onMeasure(i, i2);
        } catch (Throwable th) {
            ho.c("AutoScaleSizeRl", "onMeasure err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            super.onLayout(z, i, i2, i3, i4);
            this.b.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            a();
        } catch (Throwable th) {
            ho.c("AutoScaleSizeRl", "onLayout err: %s", th.getClass().getSimpleName());
        }
    }

    public float getRatio() {
        Float f = this.f7791a;
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        try {
            if (canvas == null) {
                ho.c("AutoScaleSizeRl", "canvas null");
                return;
            }
            if (this.c > 0.01f) {
                canvas.clipPath(this.d);
            }
            super.draw(canvas);
        } catch (Throwable th) {
            ho.c("AutoScaleSizeRl", "draw err: %s", th.getClass().getSimpleName());
        }
    }

    private int b(int i, float f) {
        float f2 = f * i;
        return p() ? (int) Math.ceil(f2) : (int) f2;
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100157_res_0x7f0601fd})) != null) {
            try {
                this.c = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        setWillNotDraw(false);
        this.d = new Path();
    }

    private void a() {
        this.d.reset();
        Path path = this.d;
        RectF rectF = this.b;
        float f = this.c;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
    }

    private int a(int i, float f) {
        if (f <= 0.0f) {
            return 0;
        }
        float f2 = (i * 1.0f) / f;
        return p() ? (int) Math.ceil(f2) : (int) f2;
    }

    public AutoScaleSizeRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.b = new RectF();
        this.c = 0.0f;
        this.e = true;
        a(context, attributeSet);
    }

    public AutoScaleSizeRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new RectF();
        this.c = 0.0f;
        this.e = true;
        a(context, attributeSet);
    }

    public AutoScaleSizeRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new RectF();
        this.c = 0.0f;
        this.e = true;
        a(context, attributeSet);
    }

    public AutoScaleSizeRelativeLayout(Context context) {
        super(context);
        this.b = new RectF();
        this.c = 0.0f;
        this.e = true;
        a(context, (AttributeSet) null);
    }
}
