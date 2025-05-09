package com.huawei.ui.commonui.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class PaceRangeDividerView extends View {

    /* renamed from: a, reason: collision with root package name */
    private RectF f8826a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private RectF f;
    private int g;
    private int h;
    private Paint i;
    private int j;
    private int k;
    private int o;

    public PaceRangeDividerView(Context context) {
        this(context, null);
    }

    public PaceRangeDividerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PaceRangeDividerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int applyDimension = (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics());
        this.g = applyDimension;
        this.j = applyDimension;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362361_res_0x7f0a0239);
        this.h = dimensionPixelSize;
        this.e = dimensionPixelSize + dimensionPixelSize;
        Paint paint = new Paint();
        this.i = paint;
        paint.setAntiAlias(true);
        this.i.setColor(ContextCompat.getColor(context, R$color.listDivider));
        this.i.setStrokeWidth(this.g);
        this.d = LanguageUtil.bc(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null) {
            LogUtil.h("PaceRangeDividerView", "onDraw Canvas is null");
            return;
        }
        b();
        canvas.save();
        if (this.d) {
            canvas.rotate(180.0f, this.b, this.c);
        }
        this.i.setStyle(Paint.Style.FILL_AND_STROKE);
        cAe_(canvas);
        cAb_(canvas);
        cAc_(canvas);
        cAa_(canvas);
        this.i.setStyle(Paint.Style.STROKE);
        cAd_(canvas);
        czZ_(canvas);
        canvas.restore();
    }

    private void b() {
        this.k = getWidth();
        int height = getHeight();
        this.o = height;
        int i = this.k / 2;
        this.b = i;
        this.c = height / 2;
        int i2 = this.j;
        int i3 = this.e;
        this.f = new RectF(i, i2, i + i3, i2 + i3);
        int i4 = this.b;
        int i5 = this.o;
        int i6 = this.e;
        int i7 = this.j;
        this.f8826a = new RectF(i4, (i5 - i6) - i7, i4 + i6, i5 - i7);
    }

    private void cAe_(Canvas canvas) {
        float f = this.b + this.h;
        float f2 = this.j;
        canvas.drawLine(f, f2, this.k, f2, this.i);
    }

    private void cAb_(Canvas canvas) {
        float f = this.c;
        canvas.drawLine(0.0f, f, this.b, f, this.i);
    }

    private void cAc_(Canvas canvas) {
        float f = this.b;
        int i = this.h;
        int i2 = this.j;
        canvas.drawLine(f, i + i2, f, (this.o - i) - i2, this.i);
    }

    private void cAa_(Canvas canvas) {
        float f = this.b + this.h;
        float f2 = this.o - this.j;
        canvas.drawLine(f, f2, this.k, f2, this.i);
    }

    private void cAd_(Canvas canvas) {
        canvas.drawArc(this.f, 180.0f, 90.0f, false, this.i);
    }

    private void czZ_(Canvas canvas) {
        canvas.drawArc(this.f8826a, 90.0f, 90.0f, false, this.i);
    }
}
