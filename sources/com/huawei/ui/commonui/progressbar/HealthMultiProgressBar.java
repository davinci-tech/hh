package com.huawei.ui.commonui.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class HealthMultiProgressBar extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f8921a;
    private float b;
    private int[] c;
    private float d;
    private String e;
    private float f;
    private String g;
    private float[] h;
    private Paint i;
    private float j;
    private boolean k;
    private Paint l;
    private float m;
    private boolean n;
    private boolean o;
    private boolean p;
    private float q;
    private RectF r;
    private float s;
    private float t;

    public HealthMultiProgressBar(Context context) {
        super(context);
        float[] fArr = {2.0f, 3.0f, 4.0f, 5.0f, 5.5f};
        this.h = fArr;
        this.j = fArr[4];
        this.f = fArr[0];
        this.c = new int[]{Color.parseColor("#EA8350"), Color.parseColor("#DBB546"), Color.parseColor("#44B644"), Color.parseColor("#54A8EC"), Color.parseColor("#B7BBBF")};
        this.d = 0.0f;
        this.f8921a = 0.0f;
        this.o = false;
        this.p = false;
        this.n = false;
        this.k = true;
        c(context);
    }

    public HealthMultiProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        float[] fArr = {2.0f, 3.0f, 4.0f, 5.0f, 5.5f};
        this.h = fArr;
        this.j = fArr[4];
        this.f = fArr[0];
        this.c = new int[]{Color.parseColor("#EA8350"), Color.parseColor("#DBB546"), Color.parseColor("#44B644"), Color.parseColor("#54A8EC"), Color.parseColor("#B7BBBF")};
        this.d = 0.0f;
        this.f8921a = 0.0f;
        this.o = false;
        this.p = false;
        this.n = false;
        this.k = true;
        c(context);
    }

    public HealthMultiProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        float[] fArr = {2.0f, 3.0f, 4.0f, 5.0f, 5.5f};
        this.h = fArr;
        this.j = fArr[4];
        this.f = fArr[0];
        this.c = new int[]{Color.parseColor("#EA8350"), Color.parseColor("#DBB546"), Color.parseColor("#44B644"), Color.parseColor("#54A8EC"), Color.parseColor("#B7BBBF")};
        this.d = 0.0f;
        this.f8921a = 0.0f;
        this.o = false;
        this.p = false;
        this.n = false;
        this.k = true;
        c(context);
    }

    private void c(Context context) {
        this.r = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.l = new Paint(1);
        this.i = new Paint(1);
        this.l.setTextSize(a(11.0f));
        this.l.setTextAlign(Paint.Align.CENTER);
        this.l.setColor(getResources().getColor(R$color.textColorSecondary));
        this.q = e(6.0f);
        this.m = e(1.0f);
        this.b = e(2.0f);
        this.t = e(4.0f);
        this.s = e(6.0f);
        this.g = getResources().getString(R$string.IDS_motiontrack_detail_current);
        this.e = getResources().getString(R$string.IDS_motiontrack_detail_average);
        this.k = true;
        this.n = LanguageUtil.bc(context);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!this.p) {
            this.r.set(getPaddingStart(), this.q, (i - (this.m * a(this.h))) - getPaddingEnd(), this.q + this.t);
            return;
        }
        this.r.set(getPaddingStart(), this.q + cEy_(this.l) + (this.t * 2.0f), (i - (this.m * a(this.h))) - getPaddingEnd(), this.q + cEy_(this.l) + (this.t * 3.0f));
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        float cEy_ = (this.q * 2.0f) + ((cEy_(this.l) + this.s) * 2.0f) + this.t;
        if (!this.o) {
            cEy_ -= cEy_(this.l) + (this.q == 0.0f ? 0.0f : this.s);
        }
        if (!this.p) {
            cEy_ -= cEy_(this.l) + (this.q != 0.0f ? this.s : 0.0f);
        }
        setMeasuredDimension(size, Math.round(cEy_));
    }

    private int[] e(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[(iArr.length - i) - 1];
        }
        return iArr2;
    }

    private float[] e(float[] fArr) {
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = fArr[(fArr.length - i) - 1];
        }
        return fArr2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.n) {
            cEw_(canvas, e(this.c));
        } else {
            cEw_(canvas, this.c);
        }
        cEv_(canvas);
    }

    private void cEv_(Canvas canvas) {
        float f = this.d;
        float f2 = this.j;
        if (f >= f2 && f2 > 10.0f) {
            this.d = f2 - 1.0f;
        }
        float f3 = this.d;
        float f4 = this.f;
        if (f3 <= f4 && f4 > 10.0f) {
            this.d = 1.0f + f4;
        }
        float f5 = this.d;
        if (f5 < f4) {
            f5 = f4;
        } else if (f5 > f2) {
            f5 = f2;
        }
        this.d = f5;
        float f6 = this.f8921a;
        if (f6 < f4) {
            f2 = f4;
        } else if (f6 <= f2) {
            f2 = f6;
        }
        this.f8921a = f2;
        if (this.p) {
            cEt_(canvas);
        }
        if (this.o) {
            cEs_(canvas);
        }
    }

    private void cEt_(Canvas canvas) {
        float d;
        canvas.save();
        if (this.n) {
            d = d(this.d) - d(this.j);
        } else {
            d = d(this.d);
        }
        canvas.translate(d, 0.0f);
        cEr_(canvas, d, false);
    }

    private void cEs_(Canvas canvas) {
        float d;
        canvas.save();
        if (this.n) {
            d = d(this.f8921a) - d(this.j);
        } else {
            d = d(this.f8921a);
        }
        canvas.translate(d, 0.0f);
        cEr_(canvas, d, true);
    }

    private void cEr_(Canvas canvas, float f, boolean z) {
        String str = z ? this.e : this.g;
        if (f < this.l.measureText(str) / 2.0f) {
            cEz_(canvas, str, z, f);
        } else if (f >= this.r.right - (this.l.measureText(str) / 2.0f)) {
            cEB_(canvas, str, z, f);
        } else {
            cEA_(canvas, str, z);
        }
    }

    private void cEA_(Canvas canvas, String str, boolean z) {
        float f;
        float f2;
        float f3;
        if (z) {
            f = this.r.bottom + this.b;
            f2 = this.t + f;
            f3 = cEy_(this.l) + f2;
        } else {
            f = this.r.top - this.b;
            f2 = f - this.t;
            f3 = f2 - this.l.getFontMetrics().descent;
        }
        cEu_(canvas, new c(this.r.left - 2.0f, f), new c((this.r.left - this.b) - 2.0f, f2), new c((this.r.left + this.b) - 2.0f, f2));
        canvas.drawText(str, this.r.left, f3, this.l);
        canvas.restore();
    }

    private void cEB_(Canvas canvas, String str, boolean z, float f) {
        float f2;
        float f3;
        if (z) {
            float f4 = this.r.bottom + this.b;
            float f5 = this.r.right;
            float measureText = this.l.measureText(this.g) / 2.0f;
            float f6 = this.t;
            f2 = (f5 - measureText) + f6;
            float f7 = f6 + f4;
            f3 = cEy_(this.l) + f7;
            canvas.restore();
            cEu_(canvas, new c(f2, f4), new c(f2 - this.b, f7), new c(this.b + f2, f7));
        } else {
            float f8 = this.r.top - this.b;
            float f9 = this.r.right;
            float measureText2 = this.l.measureText(this.g) / 2.0f;
            float f10 = this.t;
            float f11 = f8 - f10;
            float f12 = f11 - this.l.getFontMetrics().descent;
            canvas.restore();
            cEu_(canvas, new c(f, f8), new c(f - this.b, f11), new c(f + this.b, f11));
            f2 = (f9 - measureText2) + f10;
            f3 = f12;
        }
        canvas.drawText(str, f2, f3, this.l);
    }

    private void cEz_(Canvas canvas, String str, boolean z, float f) {
        float f2;
        float f3;
        if (z) {
            float f4 = this.r.bottom + this.b;
            f3 = (this.l.measureText(this.g) / 2.0f) + this.b;
            float f5 = this.t + f4;
            f2 = cEy_(this.l) + f5;
            canvas.restore();
            cEu_(canvas, new c(f3, f4), new c(this.b + f3, f5), new c(f3 - this.b, f5));
        } else {
            float f6 = this.r.top - this.b;
            float measureText = this.l.measureText(this.g) / 2.0f;
            float f7 = f6 - this.t;
            float f8 = this.l.getFontMetrics().descent;
            canvas.restore();
            cEu_(canvas, new c(f, f6), new c(f - this.b, f7), new c(f + this.b, f7));
            f2 = f7 - f8;
            f3 = measureText;
        }
        canvas.drawText(str, f3, f2, this.l);
    }

    private void cEu_(Canvas canvas, c cVar, c cVar2, c cVar3) {
        Path path = new Path();
        path.moveTo(cVar.c(), cVar.b());
        path.lineTo(cVar2.c(), cVar2.b());
        path.lineTo(cVar3.c(), cVar3.b());
        path.close();
        canvas.drawPath(path, this.l);
    }

    private void cEw_(Canvas canvas, int[] iArr) {
        if (this.n && this.k) {
            this.k = false;
            float[] e = e(this.h);
            this.h = e;
            this.f = e[e.length - 1];
            this.j = e[0];
        }
        for (int i = 0; i < this.h.length - 1; i++) {
            if (iArr.length > i) {
                this.i.setColor(iArr[i]);
                float f = this.r.right - this.r.left;
                float[] fArr = this.h;
                float f2 = (((fArr[i + 1] - fArr[i]) * 1.0f) / (fArr[fArr.length - 1] - fArr[0])) * f;
                if (Math.abs(f2 - f) < 0.1d && i <= this.h.length - 2) {
                    Path path = new Path();
                    path.addRoundRect(new RectF(this.r.left, this.r.top, this.r.left + f2, this.r.bottom), new float[]{(this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f}, Path.Direction.CCW);
                    canvas.drawPath(path, this.i);
                    return;
                } else if (f2 != 0.0f) {
                    cEx_(true, f2, canvas, 0.0f, i);
                }
            }
        }
    }

    private float d(float f) {
        float[] fArr = this.h;
        float f2 = fArr[0];
        return (((f - f2) * 1.0f) / (fArr[fArr.length - 1] - f2)) * ((this.r.right - this.r.left) + (this.m * (this.h.length - 1)));
    }

    public HealthMultiProgressBar d(Integer... numArr) {
        float[] fArr;
        float[] fArr2 = new float[numArr.length + 1];
        this.h = fArr2;
        fArr2[0] = 0.0f;
        int i = 1;
        while (true) {
            fArr = this.h;
            if (i >= fArr.length) {
                break;
            }
            fArr[i] = numArr[r4].intValue() + this.h[i - 1];
            i++;
        }
        float f = fArr[fArr.length - 1];
        this.j = f;
        float f2 = fArr[0];
        this.f = f2;
        if (f >= f2) {
            return this;
        }
        LogUtil.h("HealthMultiProgressBar", "savePersentData input error");
        return null;
    }

    public HealthMultiProgressBar c(int i) {
        this.m = e(i);
        return this;
    }

    private float e(float f) {
        return TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    private float a(float f) {
        return TypedValue.applyDimension(2, f, getContext().getResources().getDisplayMetrics());
    }

    private int a(float[] fArr) {
        int i = 0;
        int i2 = 0;
        while (i < fArr.length - 1) {
            int i3 = i + 1;
            if (Math.abs(fArr[i3] - fArr[i]) > 0.1d) {
                i2++;
            }
            i = i3;
        }
        return i2 - 1;
    }

    private float cEy_(Paint paint) {
        return -paint.getFontMetrics().ascent;
    }

    public HealthMultiProgressBar b(int i) {
        this.q = e(i);
        return this;
    }

    private void cEx_(boolean z, float f, Canvas canvas, float f2, int i) {
        if (z) {
            Path path = new Path();
            path.addRoundRect(new RectF(this.r.left, this.r.top, this.r.left + f, this.r.bottom), new float[]{(this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f}, Path.Direction.CCW);
            canvas.drawPath(path, this.i);
            float f3 = this.r.left;
            return;
        }
        float[] fArr = this.h;
        float f4 = fArr[i + 1];
        float f5 = this.j;
        if ((f4 == f5 && fArr[fArr.length - 1] == f5) || (fArr[fArr.length - 1] == this.f && i == fArr.length - 2)) {
            Path path2 = new Path();
            path2.addRoundRect(new RectF(f2, this.r.top, f2 + f, this.r.bottom), new float[]{0.0f, 0.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, (this.r.bottom - this.r.top) / 2.0f, 0.0f, 0.0f}, Path.Direction.CCW);
            canvas.drawPath(path2, this.i);
            return;
        }
        canvas.drawRect(f2, this.r.top, f2 + f, this.r.bottom, this.i);
    }

    static class c {
        private float b;
        private float d;

        c(float f, float f2) {
            this.d = f;
            this.b = f2;
        }

        public float c() {
            return this.d;
        }

        public float b() {
            return this.b;
        }
    }
}
