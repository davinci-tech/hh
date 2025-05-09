package com.huawei.ui.main.stories.settings.activity.heartrate;

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
import defpackage.jed;
import defpackage.rve;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HeartRateZoneSeekBar extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f10452a;
    private float b;
    private rve c;
    private boolean d;
    private float e;
    private Paint f;
    private Path g;
    private boolean h;
    private int i;
    private float j;
    private int k;
    private float m;
    private float n;

    public HeartRateZoneSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.h = false;
        this.f = new Paint();
        this.g = new Path();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100015_res_0x7f06016f, R.attr._2131100016_res_0x7f060170, R.attr._2131100817_res_0x7f060491, R.attr._2131100896_res_0x7f0604e0, R.attr._2131101082_res_0x7f06059a, R.attr._2131101273_res_0x7f060659, R.attr._2131101274_res_0x7f06065a, R.attr._2131101303_res_0x7f060677});
        this.e = obtainStyledAttributes.getDimension(4, getResources().getDimension(R.dimen._2131362035_res_0x7f0a00f3));
        this.n = obtainStyledAttributes.getDimension(3, getResources().getDimension(R.dimen._2131365066_res_0x7f0a0cca));
        this.h = obtainStyledAttributes.getBoolean(7, false);
        this.k = obtainStyledAttributes.getColor(6, getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.i = obtainStyledAttributes.getColor(5, getResources().getColor(R.color._2131298738_res_0x7f0909b2));
        this.b = obtainStyledAttributes.getDimension(0, 0.0f);
        this.f10452a = obtainStyledAttributes.getDimension(1, 0.0f);
        float dimension = obtainStyledAttributes.getDimension(2, 0.0f);
        this.j = dimension;
        this.m = this.e + this.n + this.b + dimension;
        this.d = LanguageUtil.bc(getContext());
        obtainStyledAttributes.recycle();
    }

    public void e() {
        postInvalidate();
    }

    public void a(rve rveVar) {
        this.c = rveVar;
        e();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.c == null) {
            return;
        }
        this.f.setStyle(Paint.Style.FILL);
        if (this.d) {
            dRE_(canvas);
        } else {
            dRD_(canvas);
        }
        if (!this.h || this.c.c() == null || this.c.c().length <= 0) {
            return;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(this.n);
        textPaint.setAntiAlias(true);
        textPaint.setColor(this.k);
        dRG_(canvas, textPaint);
    }

    private void dRE_(Canvas canvas) {
        float measuredWidth = getMeasuredWidth();
        float f = this.e / 2.0f;
        this.f.setColor(this.c.a());
        if (!this.c.f()) {
            dRC_(canvas, measuredWidth, f);
            return;
        }
        rve.c cVar = this.c.b().get(0);
        rve.c cVar2 = this.c.b().get(this.c.b().size() - 1);
        float e = (measuredWidth - this.e) / (this.c.e() - this.c.d());
        if (this.c.e() - cVar2.c() < 0.01f) {
            this.f.setColor(cVar2.a());
            float f2 = this.e;
            canvas.drawArc(0.0f, 0.0f, f2, f2, -90.0f, -180.0f, true, this.f);
        } else {
            this.f.setColor(this.c.a());
            float f3 = this.e;
            canvas.drawArc(0.0f, 0.0f, f3, f3, -90.0f, -180.0f, true, this.f);
            dRF_(canvas, f, ((this.c.e() - cVar2.c()) * e) + f, this.c.a());
        }
        List<rve.c> b = this.c.b();
        for (int size = b.size() - 1; size >= 0; size--) {
            rve.c cVar3 = b.get(size);
            float e2 = ((this.c.e() - cVar3.c()) * e) + f;
            dRF_(canvas, e2, ((cVar3.c() - cVar3.e()) * e) + e2, cVar3.a());
        }
        if (cVar.e() - this.c.d() < 0.01f) {
            this.f.setColor(cVar.a());
            float f4 = this.e;
            canvas.drawArc(measuredWidth - f4, 0.0f, measuredWidth, f4, 270.0f, 180.0f, true, this.f);
        } else {
            dRF_(canvas, ((this.c.e() - cVar.e()) * e) + f, measuredWidth - f, this.c.a());
            float f5 = this.e;
            canvas.drawArc(measuredWidth - f5, 0.0f, measuredWidth, f5, 270.0f, 180.0f, true, this.f);
        }
    }

    private void dRF_(Canvas canvas, float f, float f2, int i) {
        this.f.setColor(i);
        this.g.reset();
        this.g.moveTo(f, 0.0f);
        this.g.lineTo(f2, 0.0f);
        this.g.lineTo(f2, this.e);
        this.g.lineTo(f, this.e);
        this.g.lineTo(f, 0.0f);
        canvas.drawPath(this.g, this.f);
    }

    private void dRC_(Canvas canvas, float f, float f2) {
        dRF_(canvas, f2, f - f2, this.c.a());
        float f3 = this.e;
        canvas.drawArc(0.0f, 0.0f, f3, f3, -90.0f, -180.0f, true, this.f);
        float f4 = this.e;
        canvas.drawArc(f - f4, 0.0f, f, f4, 270.0f, 180.0f, true, this.f);
    }

    private void dRG_(Canvas canvas, TextPaint textPaint) {
        float d;
        float width = (getWidth() - this.e) / (this.c.e() - this.c.d());
        Rect rect = new Rect();
        for (float f : this.c.c()) {
            if (this.d) {
                d = this.c.e() - f;
            } else {
                d = f - this.c.d();
            }
            float f2 = d * width;
            this.f.setColor(this.i);
            this.f.setStrokeWidth(this.f10452a);
            float f3 = this.e;
            float f4 = f2 + (f3 / 2.0f);
            canvas.drawLine(f4, f3 + 0.1f, f4, f3 + this.b, this.f);
            String b = jed.b(f, 2, 0);
            textPaint.getTextBounds(b, 0, b.length(), rect);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(b, ((float) rect.width()) + f2 < ((float) getWidth()) ? f2 + (rect.width() / 2.0f) : (getWidth() - (rect.width() / 2)) - 2, this.e + rect.height() + this.b + this.j, textPaint);
        }
    }

    private void dRD_(Canvas canvas) {
        float measuredWidth = getMeasuredWidth();
        float f = this.e / 2.0f;
        this.f.setColor(this.c.a());
        if (!this.c.f()) {
            dRC_(canvas, measuredWidth, f);
            return;
        }
        rve.c cVar = this.c.b().get(0);
        rve.c cVar2 = this.c.b().get(this.c.b().size() - 1);
        float e = (measuredWidth - this.e) / (this.c.e() - this.c.d());
        if (cVar.e() - this.c.d() < 0.01f) {
            this.f.setColor(cVar.a());
            float f2 = this.e;
            canvas.drawArc(0.0f, 0.0f, f2, f2, -90.0f, -180.0f, true, this.f);
        } else {
            this.f.setColor(this.c.a());
            float f3 = this.e;
            canvas.drawArc(0.0f, 0.0f, f3, f3, -90.0f, -180.0f, true, this.f);
            dRF_(canvas, f, ((cVar.e() - this.c.d()) * e) + f, this.c.a());
        }
        for (rve.c cVar3 : this.c.b()) {
            float e2 = ((cVar3.e() - this.c.d()) * e) + f;
            dRF_(canvas, e2, ((cVar3.c() - cVar3.e()) * e) + e2, cVar3.a());
        }
        if (this.c.e() - cVar2.c() < 0.01f) {
            this.f.setColor(cVar2.a());
            float f4 = this.e;
            canvas.drawArc(measuredWidth - f4, 0.0f, measuredWidth, f4, 270.0f, 180.0f, true, this.f);
        } else {
            dRF_(canvas, ((cVar2.c() - this.c.d()) * e) + f, measuredWidth - f, this.c.a());
            float f5 = this.e;
            canvas.drawArc(measuredWidth - f5, 0.0f, measuredWidth, f5, 270.0f, 180.0f, true, this.f);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        float f;
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            f = this.m;
        } else {
            if (mode != Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
                f = this.m;
            }
            setMeasuredDimension(size, size2);
        }
        size2 = (int) f;
        setMeasuredDimension(size, size2);
    }
}
