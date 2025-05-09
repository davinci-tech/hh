package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.huawei.health.R;

/* loaded from: classes4.dex */
public class ShowProgress extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f3792a;
    private float b;
    private String c;
    private int[] d;
    private float e;
    private float f;
    private Paint g;
    private float[] h;
    private float i;
    private String j;
    private float k;
    private Paint l;
    private boolean m;
    private boolean n;
    private boolean o;
    private RectF p;
    private float r;
    private float s;
    private float t;

    public ShowProgress(Context context) {
        super(context);
        float[] fArr = {0.0f, 2.0f, 3.0f, 4.0f, 5.0f, 5.5f};
        this.h = fArr;
        this.i = fArr[5];
        this.f = fArr[0];
        this.d = new int[]{Color.parseColor("#1F8DFF"), Color.parseColor("#75DF3E"), Color.parseColor("#E6D420"), Color.parseColor("#F5A623"), Color.parseColor("#FF3320")};
        this.e = 0.0f;
        this.b = 0.0f;
        this.o = true;
        this.m = false;
        this.n = true;
        c();
    }

    public ShowProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        float[] fArr = {0.0f, 2.0f, 3.0f, 4.0f, 5.0f, 5.5f};
        this.h = fArr;
        this.i = fArr[5];
        this.f = fArr[0];
        this.d = new int[]{Color.parseColor("#1F8DFF"), Color.parseColor("#75DF3E"), Color.parseColor("#E6D420"), Color.parseColor("#F5A623"), Color.parseColor("#FF3320")};
        this.e = 0.0f;
        this.b = 0.0f;
        this.o = true;
        this.m = false;
        this.n = true;
        c();
    }

    public ShowProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        float[] fArr = {0.0f, 2.0f, 3.0f, 4.0f, 5.0f, 5.5f};
        this.h = fArr;
        this.i = fArr[5];
        this.f = fArr[0];
        this.d = new int[]{Color.parseColor("#1F8DFF"), Color.parseColor("#75DF3E"), Color.parseColor("#E6D420"), Color.parseColor("#F5A623"), Color.parseColor("#FF3320")};
        this.e = 0.0f;
        this.b = 0.0f;
        this.o = true;
        this.m = false;
        this.n = true;
        c();
    }

    private void c() {
        this.p = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.l = new Paint(1);
        this.g = new Paint(1);
        this.l.setTextSize(e(11.0f));
        this.l.setTextAlign(Paint.Align.CENTER);
        this.l.setColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.t = d(6.0f);
        this.f3792a = d(2.0f);
        this.s = d(4.0f);
        this.r = d(6.0f);
        this.j = getResources().getString(R.string._2130839791_res_0x7f0208ef);
        this.c = getResources().getString(R.string._2130840046_res_0x7f0209ee);
        this.n = true;
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
        this.k = d(1.0f);
        this.p.set(getPaddingStart(), this.t + bjc_(this.l) + (this.s * 2.0f), (i - (this.k * (this.h.length - 2))) - getPaddingEnd(), this.t + bjc_(this.l) + (this.s * 3.0f));
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        float bjc_ = (this.t * 2.0f) + ((bjc_(this.l) + this.r) * 2.0f) + this.s;
        if (!this.o) {
            bjc_ -= bjc_(this.l) + (this.t != 0.0f ? this.r : 0.0f);
        }
        setMeasuredDimension(size, Math.round(bjc_));
    }

    private int[] b(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[(iArr.length - i) - 1];
        }
        return iArr2;
    }

    private float[] a(float[] fArr) {
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = fArr[(fArr.length - i) - 1];
        }
        return fArr2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m) {
            bjb_(canvas, b(this.d));
        } else {
            bjb_(canvas, this.d);
        }
        bja_(canvas);
    }

    private void bja_(Canvas canvas) {
        float f = this.e;
        float f2 = this.i;
        if (f >= f2 && f2 > 10.0f) {
            this.e = f2 - 1.0f;
        }
        float f3 = this.e;
        float f4 = this.f;
        if (f3 <= f4 && f4 > 10.0f) {
            this.e = 1.0f + f4;
        }
        float f5 = this.e;
        if (f5 < f4) {
            f5 = f4;
        } else if (f5 > f2) {
            f5 = f2;
        }
        this.e = f5;
        float f6 = this.b;
        if (f6 < f4) {
            f2 = f4;
        } else if (f6 <= f2) {
            f2 = f6;
        }
        this.b = f2;
        biY_(canvas);
        if (this.o) {
            biX_(canvas);
        }
    }

    private void biY_(Canvas canvas) {
        float a2;
        canvas.save();
        if (this.m) {
            a2 = a(this.e) - a(this.i);
        } else {
            a2 = a(this.e);
        }
        canvas.translate(a2, 0.0f);
        biW_(canvas, a2, false);
    }

    private void biX_(Canvas canvas) {
        float a2;
        canvas.save();
        if (this.m) {
            a2 = a(this.b) - a(this.i);
        } else {
            a2 = a(this.b);
        }
        canvas.translate(a2, 0.0f);
        biW_(canvas, a2, true);
    }

    private void biW_(Canvas canvas, float f, boolean z) {
        String str = z ? this.c : this.j;
        if (f < this.l.measureText(str) / 2.0f) {
            bjd_(canvas, str, z, f);
        } else if (f >= this.p.right - (this.l.measureText(str) / 2.0f)) {
            bjf_(canvas, str, z, f);
        } else {
            bje_(canvas, str, z);
        }
    }

    private void bje_(Canvas canvas, String str, boolean z) {
        float f;
        float f2;
        float f3;
        if (z) {
            f = this.p.bottom + this.f3792a;
            f2 = this.s + f;
            f3 = bjc_(this.l) + f2;
        } else {
            f = this.p.top - this.f3792a;
            f2 = f - this.s;
            f3 = f2 - this.l.getFontMetrics().descent;
        }
        biZ_(canvas, new e(this.p.left - 2.0f, f), new e((this.p.left - this.f3792a) - 2.0f, f2), new e((this.p.left + this.f3792a) - 2.0f, f2));
        canvas.drawText(str, this.p.left, f3, this.l);
        canvas.restore();
    }

    private void bjf_(Canvas canvas, String str, boolean z, float f) {
        float f2;
        float f3;
        if (z) {
            float f4 = this.p.bottom + this.f3792a;
            float f5 = this.p.right;
            float measureText = this.l.measureText(this.j) / 2.0f;
            float f6 = this.s;
            f2 = (f5 - measureText) + f6;
            float f7 = f6 + f4;
            f3 = bjc_(this.l) + f7;
            canvas.restore();
            biZ_(canvas, new e(f2, f4), new e(f2 - this.f3792a, f7), new e(this.f3792a + f2, f7));
        } else {
            float f8 = this.p.top - this.f3792a;
            float f9 = this.p.right;
            float measureText2 = this.l.measureText(this.j) / 2.0f;
            float f10 = this.s;
            float f11 = f8 - f10;
            float f12 = f11 - this.l.getFontMetrics().descent;
            canvas.restore();
            biZ_(canvas, new e(f, f8), new e(f - this.f3792a, f11), new e(f + this.f3792a, f11));
            f2 = (f9 - measureText2) + f10;
            f3 = f12;
        }
        canvas.drawText(str, f2, f3, this.l);
    }

    private void bjd_(Canvas canvas, String str, boolean z, float f) {
        float f2;
        float f3;
        if (z) {
            float f4 = this.p.bottom + this.f3792a;
            f3 = (this.l.measureText(this.j) / 2.0f) + this.f3792a;
            float f5 = this.s + f4;
            f2 = bjc_(this.l) + f5;
            canvas.restore();
            biZ_(canvas, new e(f3, f4), new e(this.f3792a + f3, f5), new e(f3 - this.f3792a, f5));
        } else {
            float f6 = this.p.top - this.f3792a;
            float measureText = this.l.measureText(this.j) / 2.0f;
            float f7 = f6 - this.s;
            float f8 = this.l.getFontMetrics().descent;
            canvas.restore();
            biZ_(canvas, new e(f, f6), new e(f - this.f3792a, f7), new e(f + this.f3792a, f7));
            f2 = f7 - f8;
            f3 = measureText;
        }
        canvas.drawText(str, f3, f2, this.l);
    }

    private void biZ_(Canvas canvas, e eVar, e eVar2, e eVar3) {
        Path path = new Path();
        path.moveTo(eVar.e(), eVar.d());
        path.lineTo(eVar2.e(), eVar2.d());
        path.lineTo(eVar3.e(), eVar3.d());
        path.close();
        canvas.drawPath(path, this.l);
    }

    private void bjb_(Canvas canvas, int[] iArr) {
        if (this.m && this.n) {
            this.n = false;
            float[] a2 = a(this.h);
            this.h = a2;
            this.f = a2[a2.length - 1];
            this.i = a2[0];
        }
        float f = 0.0f;
        for (int i = 0; i < this.h.length - 1; i++) {
            if (iArr.length > i) {
                this.g.setColor(iArr[i]);
                float[] fArr = this.h;
                float f2 = (((fArr[i + 1] - fArr[i]) * 1.0f) / (fArr[fArr.length - 1] - fArr[0])) * (this.p.right - this.p.left);
                if (i == 0) {
                    Path path = new Path();
                    path.addRoundRect(new RectF(this.p.left, this.p.top, this.p.left + f2, this.p.bottom), new float[]{(this.p.bottom - this.p.top) / 2.0f, (this.p.bottom - this.p.top) / 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, (this.p.bottom - this.p.top) / 2.0f, (this.p.bottom - this.p.top) / 2.0f}, Path.Direction.CCW);
                    canvas.drawPath(path, this.g);
                    f = this.p.left + f2 + this.k;
                } else {
                    if (i == this.h.length - 2) {
                        Path path2 = new Path();
                        path2.addRoundRect(new RectF(f, this.p.top, f2 + f, this.p.bottom), new float[]{0.0f, 0.0f, (this.p.bottom - this.p.top) / 2.0f, (this.p.bottom - this.p.top) / 2.0f, (this.p.bottom - this.p.top) / 2.0f, (this.p.bottom - this.p.top) / 2.0f, 0.0f, 0.0f}, Path.Direction.CCW);
                        canvas.drawPath(path2, this.g);
                        f = 0.0f;
                    } else {
                        canvas.drawRect(f, this.p.top, f + f2, this.p.bottom, this.g);
                        f += f2 + this.k;
                    }
                }
            }
        }
    }

    private float a(float f) {
        float[] fArr = this.h;
        float f2 = fArr[0];
        return (((f - f2) * 1.0f) / (fArr[fArr.length - 1] - f2)) * ((this.p.right - this.p.left) + (this.k * (this.h.length - 1)));
    }

    public ShowProgress d(float... fArr) {
        this.h = fArr;
        return this;
    }

    public ShowProgress d(Integer... numArr) {
        this.h = new float[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            this.h[i] = numArr[i].intValue();
        }
        float[] fArr = this.h;
        this.i = fArr[fArr.length - 1];
        this.f = fArr[0];
        return this;
    }

    public ShowProgress d(int... iArr) {
        this.d = iArr;
        return this;
    }

    private float d(float f) {
        return TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    private float e(float f) {
        return TypedValue.applyDimension(2, f, getContext().getResources().getDisplayMetrics());
    }

    private float bjc_(Paint paint) {
        return -paint.getFontMetrics().ascent;
    }

    public ShowProgress b(float f) {
        this.e = f;
        return this;
    }

    public ShowProgress c(float f) {
        this.b = f;
        return this;
    }

    public ShowProgress c(boolean z) {
        this.o = z;
        return this;
    }

    public ShowProgress b(int i) {
        this.t = i;
        return this;
    }

    public ShowProgress c(int i) {
        this.l.setColor(i);
        return this;
    }

    public ShowProgress a(boolean z) {
        this.m = z;
        invalidate();
        return this;
    }

    public ShowProgress a(String str) {
        if (str != null) {
            this.j = str;
        }
        return this;
    }

    static class e {
        private float b;
        private float c;

        e(float f, float f2) {
            this.b = f;
            this.c = f2;
        }

        public float e() {
            return this.b;
        }

        public float d() {
            return this.c;
        }
    }
}
