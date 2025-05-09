package com.huawei.ui.commonui.reporthorizontalpercentageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes8.dex */
public class DottedPercentageView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f8936a;
    private Paint b;
    private float c;
    private float d;
    private int e;
    private float f;
    private float g;

    public DottedPercentageView(Context context) {
        super(context);
        this.f8936a = Color.parseColor("#F5F6FA");
        this.e = Color.parseColor("#B0B7DB");
        cFh_(context, null);
    }

    public DottedPercentageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8936a = Color.parseColor("#F5F6FA");
        this.e = Color.parseColor("#B0B7DB");
        cFh_(context, attributeSet);
    }

    public DottedPercentageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8936a = Color.parseColor("#F5F6FA");
        this.e = Color.parseColor("#B0B7DB");
        cFh_(context, attributeSet);
    }

    private void cFh_(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.dotted_percentage_view, 0, 0);
        this.e = obtainStyledAttributes.getColor(R$styleable.dotted_percentage_view_schedule_color, this.e);
        this.f8936a = obtainStyledAttributes.getColor(R$styleable.dotted_percentage_view_default_color, this.f8936a);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setColor(this.f8936a);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f = i;
        this.d = i2;
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.b.setColor(this.f8936a);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, this.f, this.d), 15.0f, 15.0f, this.b);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, 15.0f, this.d), 0.0f, 0.0f, this.b);
        if (this.g > 0.0f) {
            this.b.setColor(this.e);
            float f = (this.g * this.f) / this.c;
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, f, this.d), 15.0f, 15.0f, this.b);
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, f / 2.0f, this.d), 0.0f, 15.0f, this.b);
        }
    }

    public void setData(int i, int i2) {
        this.g = i;
        this.c = i2;
        invalidate();
    }
}
