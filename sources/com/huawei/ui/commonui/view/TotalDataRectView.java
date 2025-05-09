package com.huawei.ui.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import defpackage.nrf;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class TotalDataRectView extends View {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8978a;
    private Paint b;
    private Context c;
    private int[] d;
    private float e;
    private int f;
    private float[] g;
    private float h;
    private float i;
    private int j;

    public TotalDataRectView(Context context) {
        super(context);
        this.j = 0;
        this.f8978a = true;
        this.c = context;
    }

    public TotalDataRectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = 0;
        this.f8978a = true;
        this.c = context;
        this.g = new float[5];
        int[] iArr = new int[5];
        this.d = iArr;
        iArr[0] = context.getResources().getColor(R$color.fitness_detail_calorie_total_data_walk_color_2);
        this.d[1] = this.c.getResources().getColor(R$color.fitness_detail_calorie_total_data_run_color_2);
        this.d[2] = this.c.getResources().getColor(R$color.fitness_detail_calorie_total_data_climb_color);
        this.d[3] = this.c.getResources().getColor(R$color.fitness_detail_calorie_total_data_climb_color_2);
        this.d[4] = this.c.getResources().getColor(R$color.fitness_detail_calorie_total_data_other_color_2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.i = getDefaultSize(getSuggestedMinimumWidth(), i);
        this.e = getDefaultSize(getSuggestedMinimumHeight(), i2);
        LogUtil.c("TotalDataRectView", "onMesure mWidth = ", Float.valueOf(this.i), " mHeight = ", Float.valueOf(this.e));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL);
        int i2 = 0;
        int i3 = this.d[0];
        this.b.setColor(i3);
        float f = this.e / 2.0f;
        canvas.drawCircle(f, f, f, this.b);
        canvas.drawCircle(this.i - f, f, f, this.b);
        canvas.drawRect(new RectF(f, 0.0f, this.i - f, this.e), this.b);
        while (true) {
            if (i2 >= 5) {
                i = i3;
                break;
            } else {
                if (this.g[i2] > 0.0f) {
                    i = this.d[i2];
                    break;
                }
                i2++;
            }
        }
        int i4 = 4;
        while (true) {
            if (i4 < 0) {
                break;
            }
            if (this.g[i4] > 0.0f) {
                i3 = this.d[i4];
                break;
            }
            i4--;
        }
        cNb_(i, i3, canvas, f);
    }

    private void cNb_(int i, int i2, Canvas canvas, float f) {
        float e;
        float f2 = this.i;
        if (this.j > 1) {
            f2 = (f2 - nrf.e(1, (r2 - 1) * 2)) - this.e;
        }
        float f3 = f2;
        LogUtil.c("TotalDataRectView", "mWidth = ", Float.valueOf(this.i), " tmpWidth = ", Float.valueOf(f3), " mRectNum = ", Integer.valueOf(this.j));
        float f4 = 0.0f;
        for (int i3 = 0; i3 < 5; i3++) {
            if (this.g[i3] > 0.0f) {
                this.b.setColor(this.d[i3]);
                if (i == this.d[i3]) {
                    if (this.f8978a) {
                        this.b.setColor(getResources().getColor(R$color.colorBackground));
                    } else {
                        this.b.setColor(this.f);
                    }
                    canvas.drawRect(new RectF(0.0f, 0.0f, this.i, this.e), this.b);
                    this.b.setColor(this.d[i3]);
                    e = f4 + f;
                    canvas.drawCircle(f, f, f, this.b);
                } else {
                    e = nrf.e(1, 1.0f) + f4;
                    LogUtil.c("TotalDataRectView", "In value", Integer.valueOf(i3), " after add startX = ", Float.valueOf(e), " mWidth = ", Float.valueOf(this.i), " ", "circleR = ", Float.valueOf(f));
                }
                LogUtil.c("TotalDataRectView", "In value", Integer.valueOf(i3), "endX = ", Float.valueOf(f4), " mWidth = ", Float.valueOf(this.i), " circleR = ", Float.valueOf(f));
                float f5 = e + ((this.g[i3] / this.h) * f3);
                LogUtil.c("TotalDataRectView", "In value", Integer.valueOf(i3), " after add endX = ", Float.valueOf(f5), " mWidth = ", Float.valueOf(this.i), " circleR = ", Float.valueOf(f));
                if (i2 == this.d[i3]) {
                    f5 = this.i;
                    LogUtil.c("TotalDataRectView", "In value", Integer.valueOf(i3), " endColor == mColors[", Integer.valueOf(i3), "] endX = ", Float.valueOf(f5));
                    float f6 = f5 - f;
                    canvas.drawCircle(f6, f, f, this.b);
                    canvas.drawRect(e, 0.0f, f6, this.e, this.b);
                } else {
                    canvas.drawRect(e, 0.0f, f5, this.e, this.b);
                }
                f4 = f5;
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setViewData(float f, float f2, float f3) {
        this.g = new float[5];
        if (LanguageUtil.bc(this.c)) {
            float[] fArr = this.g;
            fArr[0] = f3;
            fArr[1] = f2;
            fArr[2] = f;
        } else {
            float[] fArr2 = this.g;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[2] = f3;
        }
        this.h = 0.0f;
        this.j = 0;
        for (int i = 0; i < 5; i++) {
            float f4 = this.g[i];
            if (f4 > 0.0f) {
                this.j++;
                this.h += f4;
            }
        }
        LogUtil.a("TotalDataRectView", "value1 = ", Float.valueOf(this.g[0]), " value2 = ", Float.valueOf(this.g[1]), " value3 = ", Float.valueOf(this.g[2]));
    }

    public void setColors(int i, int i2, int i3) {
        if (LanguageUtil.bc(this.c)) {
            int[] iArr = this.d;
            iArr[0] = i3;
            iArr[1] = i2;
            iArr[2] = i;
            return;
        }
        int[] iArr2 = this.d;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
    }

    public void setPaintBackgroundColor(boolean z, int i) {
        this.f8978a = z;
        this.f = i;
        invalidate();
    }
}
