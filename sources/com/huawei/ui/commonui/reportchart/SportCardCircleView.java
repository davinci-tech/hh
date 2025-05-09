package com.huawei.ui.commonui.reportchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes8.dex */
public class SportCardCircleView extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f8934a;
    private Paint b;
    private Paint c;
    private float d;
    private Paint e;

    public SportCardCircleView(Context context) {
        this(context, null);
    }

    public SportCardCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cFg_(context, attributeSet);
    }

    private void cFg_(final Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.incomplete_circle_view, 0, 0);
        this.f8934a = obtainStyledAttributes.getDimension(R$styleable.incomplete_circle_view_offsetX, nsn.c(context, 23.0f));
        this.d = obtainStyledAttributes.getDimension(R$styleable.incomplete_circle_view_offsetY, nsn.c(context, 30.0f));
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.c = paint;
        paint.setAntiAlias(true);
        this.c.setStyle(Paint.Style.FILL);
        this.c.setColor(-1);
        this.c.setAlpha(10);
        Paint paint2 = new Paint();
        this.e = paint2;
        paint2.setAntiAlias(true);
        this.e.setStyle(Paint.Style.FILL);
        this.e.setColor(-1);
        this.e.setAlpha(8);
        Paint paint3 = new Paint();
        this.b = paint3;
        paint3.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setColor(-1);
        this.b.setAlpha(5);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.huawei.ui.commonui.reportchart.SportCardCircleView.4
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (view == null || outline == null) {
                    return;
                }
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), nsn.c(context, 10.0f));
            }
        });
        setClipToOutline(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float width;
        if (LanguageUtil.bc(BaseApplication.e())) {
            width = this.f8934a;
        } else {
            width = getWidth() - this.f8934a;
        }
        float f = this.d;
        canvas.drawCircle(width, f, (getWidth() * 4.0f) / 5.0f, this.b);
        canvas.drawCircle(width, f, (getWidth() * 3.0f) / 5.0f, this.e);
        canvas.drawCircle(width, f, (getWidth() * 2.0f) / 5.0f, this.c);
    }

    public void setCircle(int i) {
        this.c.setColor(i);
        this.e.setColor(i);
        this.b.setColor(i);
        this.c.setAlpha(10);
        this.e.setAlpha(8);
        this.b.setAlpha(5);
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }
}
