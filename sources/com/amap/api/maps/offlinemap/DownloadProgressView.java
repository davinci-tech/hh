package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class DownloadProgressView extends View {

    /* renamed from: a, reason: collision with root package name */
    private String f1444a;
    private int b;
    private int c;
    private float d;
    private float e;
    private TextPaint f;
    private TextPaint g;
    private float h;
    private float i;

    public DownloadProgressView(Context context) {
        super(context);
        this.b = SupportMenu.CATEGORY_MASK;
        this.c = SupportMenu.CATEGORY_MASK;
        this.d = 0.0f;
        this.e = 0.6f;
        a(null, 0);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = SupportMenu.CATEGORY_MASK;
        this.c = SupportMenu.CATEGORY_MASK;
        this.d = 0.0f;
        this.e = 0.6f;
        a(attributeSet, 0);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = SupportMenu.CATEGORY_MASK;
        this.c = SupportMenu.CATEGORY_MASK;
        this.d = 0.0f;
        this.e = 0.6f;
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.anim._2130771968_res_0x7f010000, R.anim._2130771969_res_0x7f010001, R.anim._2130771970_res_0x7f010002, R.anim._2130771971_res_0x7f010003}, i, 0);
        this.f1444a = obtainStyledAttributes.getString(0);
        this.b = obtainStyledAttributes.getColor(3, this.b);
        this.d = obtainStyledAttributes.getDimension(1, this.d);
        this.c = obtainStyledAttributes.getColor(2, this.c);
        obtainStyledAttributes.recycle();
        TextPaint textPaint = new TextPaint();
        this.f = textPaint;
        textPaint.setFlags(1);
        this.f.setTextAlign(Paint.Align.RIGHT);
        TextPaint textPaint2 = new TextPaint();
        this.g = textPaint2;
        textPaint2.setStyle(Paint.Style.FILL);
        a();
    }

    private void a() {
        this.f.setTextSize(this.d);
        this.f.setColor(this.b);
        this.g.setColor(this.c);
        this.h = this.f.measureText(this.f1444a);
        this.i = this.f.getFontMetrics().bottom;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();
        int height = (getHeight() - paddingTop) - paddingBottom;
        float f = (width - paddingLeft) - paddingRight;
        double d = this.e * f;
        String str = String.valueOf((int) (this.e * 100.0f)) + "%";
        canvas.drawRect(new Rect(0, (int) (height * 0.8f), (int) (f * this.e), height), this.g);
        canvas.drawText(str, (int) d, (int) (r6 - 3.0d), this.f);
    }

    public void setProgress(int i) {
        if (i > 100 || i < 0) {
            return;
        }
        this.e = i / 100.0f;
        invalidate();
    }
}
