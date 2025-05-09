package com.huawei.ui.main.stories.health.views.healthdata;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class VerticalTextView extends View {

    /* renamed from: a, reason: collision with root package name */
    private String f10274a;
    private int c;
    private Rect d;
    private TextPaint e;

    public VerticalTextView(Context context) {
        super(context);
        this.d = new Rect();
        e();
    }

    public VerticalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new Rect();
        e();
        if (context == null) {
            LogUtil.a("VerticalTextView", "context is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100004_res_0x7f060164, R.attr._2131101346_res_0x7f0606a2, R.attr._2131101347_res_0x7f0606a3, R.attr._2131101348_res_0x7f0606a4});
        String string = obtainStyledAttributes.getString(1);
        if (string != null) {
            this.f10274a = string.toString();
        }
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(3, 15);
        if (dimensionPixelOffset > 0) {
            this.e.setTextSize(dimensionPixelOffset);
        }
        this.e.setColor(obtainStyledAttributes.getColor(2, -16777216));
        this.c = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        requestLayout();
        invalidate();
    }

    private void e() {
        TextPaint textPaint = new TextPaint();
        this.e = textPaint;
        textPaint.setAntiAlias(true);
        this.e.setColor(-16777216);
        this.e.setTextSize(15.0f);
        this.e.setTextAlign(Paint.Align.CENTER);
    }

    public void setDirection(int i) {
        this.c = i;
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        TextPaint textPaint = this.e;
        String str = this.f10274a;
        textPaint.getTextBounds(str, 0, str.length(), this.d);
        setMeasuredDimension(c(i), a(i2));
    }

    private int c(int i) {
        LogUtil.c("VerticalTextView", "measureWidth, widtheSpec = ", Integer.valueOf(i));
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int height = this.d.height();
        return mode == Integer.MIN_VALUE ? Math.min(height, size) : height;
    }

    private int a(int i) {
        LogUtil.c("VerticalTextView", "measureWidth, heightSpec = ", Integer.valueOf(i));
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int width = this.d.width();
        return mode == Integer.MIN_VALUE ? Math.min(width, size) : width;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        Path path = new Path();
        if (this.c == 0) {
            float width = (getWidth() / 2) - (this.d.height() / 2);
            path.moveTo(width, 0);
            path.lineTo(width, height);
        } else {
            float width2 = (getWidth() / 2) + (this.d.height() / 2);
            path.moveTo(width2, height);
            path.lineTo(width2, 0);
        }
        canvas.drawTextOnPath(this.f10274a, path, 0.0f, 0.0f, this.e);
    }
}
