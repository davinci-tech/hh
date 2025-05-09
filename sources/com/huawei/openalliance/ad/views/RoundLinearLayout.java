package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class RoundLinearLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private final RectF f8009a;
    private float b;
    private Path c;

    public void setRectCornerRadius(float f) {
        this.b = f;
        a();
        postInvalidate();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f8009a.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        a();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.b > 0.01f) {
            canvas.clipPath(this.c);
        }
        super.draw(canvas);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100157_res_0x7f0601fd})) != null) {
            try {
                this.b = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        setWillNotDraw(false);
        this.c = new Path();
    }

    private void a() {
        this.c.reset();
        Path path = this.c;
        RectF rectF = this.f8009a;
        float f = this.b;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
    }

    public RoundLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f8009a = new RectF();
        this.b = 0.0f;
        a(context, attributeSet);
    }

    public RoundLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8009a = new RectF();
        this.b = 0.0f;
        a(context, attributeSet);
    }

    public RoundLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8009a = new RectF();
        this.b = 0.0f;
        a(context, attributeSet);
    }

    public RoundLinearLayout(Context context) {
        super(context);
        this.f8009a = new RectF();
        this.b = 0.0f;
        a(context, null);
    }
}
