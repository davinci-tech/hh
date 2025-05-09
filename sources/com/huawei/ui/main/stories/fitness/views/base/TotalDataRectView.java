package com.huawei.ui.main.stories.fitness.views.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.pyb;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class TotalDataRectView extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f9930a;
    private int[] b;
    private float[] c;
    private Context d;
    private float e;
    private int f;
    private Paint g;
    private float i;

    public TotalDataRectView(Context context) {
        super(context);
        this.f = 0;
        this.d = context;
    }

    public TotalDataRectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 0;
        this.d = context;
        this.c = new float[5];
        int[] iArr = new int[5];
        this.b = iArr;
        iArr[0] = context.getResources().getColor(R.color._2131297717_res_0x7f0905b5);
        this.b[1] = this.d.getResources().getColor(R.color._2131297715_res_0x7f0905b3);
        this.b[2] = this.d.getResources().getColor(R.color._2131297711_res_0x7f0905af);
        this.b[3] = this.d.getResources().getColor(R.color._2131297712_res_0x7f0905b0);
        this.b[4] = this.d.getResources().getColor(R.color._2131297713_res_0x7f0905b1);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f9930a = getDefaultSize(getSuggestedMinimumWidth(), i);
        this.e = getDefaultSize(getSuggestedMinimumHeight(), i2);
        LogUtil.c("TotalDataRectView", "onMesure mWidth = " + this.f9930a + " mHeight = " + this.e);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        float b;
        super.onDraw(canvas);
        Paint paint = new Paint();
        this.g = paint;
        paint.setAntiAlias(true);
        this.g.setStyle(Paint.Style.FILL);
        float f = this.f9930a;
        if (this.f > 1) {
            f = (f - pyb.b(1, (r2 - 1) * 2)) - this.e;
        }
        float f2 = f;
        LogUtil.c("TotalDataRectView", "mWidth = " + this.f9930a + " tmpWidth = " + f2 + " recNum = " + this.f);
        int i4 = this.b[0];
        this.g.setColor(i4);
        float f3 = this.e / 2.0f;
        canvas.drawCircle(f3, f3, f3, this.g);
        float f4 = this.f9930a;
        float f5 = this.e / 2.0f;
        canvas.drawCircle(f4 - f5, f5, f5, this.g);
        float f6 = this.e;
        float f7 = f6 / 2.0f;
        canvas.drawRect(new RectF(f7, 0.0f, this.f9930a - f7, f6), this.g);
        int i5 = 0;
        while (true) {
            if (i5 >= 5) {
                i2 = i4;
                break;
            } else {
                if (this.c[i5] > 0.0f) {
                    i2 = this.b[i5];
                    break;
                }
                i5++;
            }
        }
        int i6 = 4;
        while (true) {
            if (i6 < 0) {
                break;
            }
            if (this.c[i6] > 0.0f) {
                i4 = this.b[i6];
                break;
            }
            i6--;
        }
        int i7 = i4;
        float f8 = this.e / 2.0f;
        int i8 = 0;
        float f9 = 0.0f;
        for (i = 5; i8 < i; i = 5) {
            if (this.c[i8] > 0.0f) {
                this.g.setColor(this.b[i8]);
                if (i2 == this.b[i8]) {
                    Context context = this.d;
                    if (context != null) {
                        this.g.setColor(context.getResources().getColor(R.color._2131296657_res_0x7f090191));
                    }
                    canvas.drawRect(new RectF(0.0f, 0.0f, this.f9930a, this.e), this.g);
                    this.g.setColor(this.b[i8]);
                    b = f9 + f8;
                    canvas.drawCircle(f8, f8, f8, this.g);
                } else {
                    b = pyb.b(1, 1.0f) + f9;
                    LogUtil.c("TotalDataRectView", "In value" + i8 + " after add startX = " + b + " mWidth = " + this.f9930a + " circleR = " + f8);
                }
                LogUtil.c("TotalDataRectView", "In value" + i8 + "endX = " + f9 + " mWidth = " + this.f9930a + " circleR = " + f8);
                float f10 = b + ((this.c[i8] / this.i) * f2);
                LogUtil.c("TotalDataRectView", "In value" + i8 + " after add endX = " + f10 + " mWidth = " + this.f9930a + " circleR = " + f8);
                if (i7 == this.b[i8]) {
                    f10 = this.f9930a;
                    LogUtil.c("TotalDataRectView", "In value" + i8 + " endColor == mColors[" + i8 + "] endX = " + f10);
                    float f11 = f10 - f8;
                    canvas.drawCircle(f11, f8, f8, this.g);
                    i3 = i8;
                    canvas.drawRect(b, 0.0f, f11, this.e, this.g);
                } else {
                    i3 = i8;
                    canvas.drawRect(b, 0.0f, f10, this.e, this.g);
                }
                f9 = f10;
            } else {
                i3 = i8;
            }
            i8 = i3 + 1;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setViewData(float f, float f2, float f3, float f4) {
        this.c = new float[5];
        if (LanguageUtil.bc(this.d)) {
            float[] fArr = this.c;
            fArr[0] = f4;
            fArr[1] = f3;
            fArr[2] = f2;
            fArr[3] = f;
        } else {
            float[] fArr2 = this.c;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[2] = f3;
            fArr2[3] = f4;
        }
        this.i = 0.0f;
        this.f = 0;
        for (int i = 0; i < 5; i++) {
            float f5 = this.c[i];
            if (f5 > 0.0f) {
                this.f++;
                this.i += f5;
            }
        }
        LogUtil.a("TotalDataRectView", "value1 = " + this.c[0] + " value2 = " + this.c[1] + " value3 = " + this.c[2] + " value4 = " + this.c[3] + " value5 = " + this.c[4]);
    }

    public void setViewData(float f, float f2, float f3, float f4, float f5) {
        this.c = new float[5];
        if (LanguageUtil.bc(this.d)) {
            float[] fArr = this.c;
            fArr[0] = f5;
            fArr[1] = f4;
            fArr[2] = f3;
            fArr[3] = f2;
            fArr[4] = f;
        } else {
            float[] fArr2 = this.c;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[2] = f3;
            fArr2[3] = f4;
            fArr2[4] = f5;
        }
        this.i = 0.0f;
        this.f = 0;
        for (int i = 0; i < 5; i++) {
            float f6 = this.c[i];
            if (f6 > 0.0f) {
                this.f++;
                this.i += f6;
            }
        }
        LogUtil.a("TotalDataRectView", "value1 = " + this.c[0] + " value2 = " + this.c[1] + " value3 = " + this.c[2] + " value4 = " + this.c[3] + " value5 = " + this.c[4]);
    }

    public void setViewData(float f, float f2, float f3) {
        this.c = new float[5];
        if (LanguageUtil.bc(this.d)) {
            float[] fArr = this.c;
            fArr[0] = f3;
            fArr[1] = f2;
            fArr[2] = f;
        } else {
            float[] fArr2 = this.c;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[2] = f3;
        }
        this.i = 0.0f;
        this.f = 0;
        for (int i = 0; i < 5; i++) {
            float f4 = this.c[i];
            if (f4 > 0.0f) {
                this.f++;
                this.i += f4;
            }
        }
        LogUtil.a("TotalDataRectView", "value1 = " + this.c[0] + " value2 = " + this.c[1] + " value3 = " + this.c[2]);
    }

    public void setColors(int i, int i2, int i3) {
        if (LanguageUtil.bc(this.d)) {
            int[] iArr = this.b;
            iArr[0] = i3;
            iArr[1] = i2;
            iArr[2] = i;
            return;
        }
        int[] iArr2 = this.b;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
    }

    public void setColors(int i, int i2, int i3, int i4) {
        if (LanguageUtil.bc(this.d)) {
            int[] iArr = this.b;
            iArr[0] = i4;
            iArr[1] = i3;
            iArr[2] = i2;
            iArr[3] = i;
            return;
        }
        int[] iArr2 = this.b;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
        iArr2[3] = i4;
    }

    public void setColors(int i, int i2, int i3, int i4, int i5) {
        if (LanguageUtil.bc(this.d)) {
            int[] iArr = this.b;
            iArr[0] = i5;
            iArr[1] = i4;
            iArr[2] = i3;
            iArr[3] = i2;
            iArr[4] = i;
            return;
        }
        int[] iArr2 = this.b;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
        iArr2[3] = i4;
        iArr2[4] = i5;
    }
}
