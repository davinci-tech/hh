package com.huawei.ui.commonui.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$styleable;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class HealthTrendBar extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f8789a;
    private boolean b;
    private Paint d;
    private int e;

    public HealthTrendBar(Context context) {
        super(context);
        this.b = true;
        a();
    }

    public HealthTrendBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = true;
        cxF_(context, attributeSet);
    }

    public HealthTrendBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = true;
        cxF_(context, attributeSet);
    }

    private void a() {
        setWillNotDraw(false);
        this.d = new Paint();
    }

    private void cxF_(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthTrendBar);
        int color = obtainStyledAttributes.getColor(R$styleable.HealthTrendBar_barColor, 0);
        this.e = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthTrendBar_barStrokeWidth, 0);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.d = paint;
        paint.setColor(color);
        this.d.setStrokeWidth(this.e);
        this.d.setAntiAlias(true);
    }

    public void setLength(int i, boolean z) {
        if (Math.abs(i) > 1.0E-5d) {
            this.b = false;
            this.d.setStrokeCap(Paint.Cap.ROUND);
            if (z) {
                i = (int) (i - (this.e / 2.0f));
            }
            this.f8789a = i;
        } else {
            this.b = true;
            this.d.setStrokeCap(Paint.Cap.BUTT);
            this.f8789a = getContext().getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
        }
        requestLayout();
        invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = getHeight() / 2.0f;
        boolean bc = LanguageUtil.bc(getContext());
        if (bc) {
            canvas.save();
            canvas.rotate(180.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        canvas.drawLine(-100.0f, height, this.f8789a, height, this.d);
        if (bc) {
            canvas.restore();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = (int) (getPaddingLeft() + this.f8789a + getPaddingRight() + (this.e / 2.0f));
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                i3 += childAt.getMeasuredWidth() + layoutParams.getMarginStart() + layoutParams.getMarginEnd();
            }
            if (!this.b && i3 > size) {
                this.f8789a = (int) (((i3 - getPaddingLeft()) - getPaddingRight()) - (this.e / 2.0f));
                invalidate();
                size = i3;
            }
        }
        if (mode2 != 1073741824) {
            size2 = getPaddingTop() + this.e + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }
}
