package com.huawei.ui.commonui.reportrectview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import health.compact.a.LanguageUtil;

/* loaded from: classes8.dex */
public class ReportTotalDataRectView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Context f8939a;
    private Paint b;
    private float c;
    private int[] d;
    private int e;
    private float[] g;
    private float i;
    private float j;

    public ReportTotalDataRectView(Context context) {
        super(context);
        this.e = 0;
        this.f8939a = context;
    }

    public ReportTotalDataRectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0;
        this.f8939a = context;
        this.g = new float[5];
        int[] iArr = new int[5];
        this.d = iArr;
        if (context != null) {
            iArr[0] = context.getResources().getColor(R$color.achieve_report_rect_view_run_color);
            this.d[1] = this.f8939a.getResources().getColor(R$color.achieve_report_rect_view_walk_color);
            this.d[2] = this.f8939a.getResources().getColor(R$color.achieve_report_rect_view_bike_color);
            this.d[3] = this.f8939a.getResources().getColor(R$color.achieve_report_rect_view_climb_color);
            this.d[4] = this.f8939a.getResources().getColor(R$color.achieve_report_rect_view_other_color);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.j = getDefaultSize(getSuggestedMinimumWidth(), i);
        this.c = getDefaultSize(getSuggestedMinimumHeight(), i2);
        LogUtil.c("TotalDataRectView", "onMeasure mWidth = ", Float.valueOf(this.j), " mHeight = ", Float.valueOf(this.c));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float a2;
        super.onDraw(canvas);
        if (this.f8939a == null) {
            LogUtil.h("TotalDataRectView", "onDraw mContext == null.");
            return;
        }
        d();
        float f = this.j;
        if (this.e > 1) {
            f = (f - a(1, (r3 - 1) * 2)) - this.c;
        }
        float f2 = f;
        LogUtil.c("TotalDataRectView", "mWidth = ", Float.valueOf(this.j), " tempWidth = ", Float.valueOf(f2), " recNum = ", Integer.valueOf(this.e));
        this.b.setColor(this.f8939a.getResources().getColor(R.color.transparent));
        float f3 = this.c / 2.0f;
        canvas.drawCircle(f3, f3, f3, this.b);
        float f4 = this.j;
        float f5 = this.c / 2.0f;
        canvas.drawCircle(f4 - f5, f5, f5, this.b);
        float f6 = this.c;
        float f7 = f6 / 2.0f;
        canvas.drawRect(new RectF(f7, 0.0f, this.j - f7, f6), this.b);
        int startColor = getStartColor();
        int endColor = getEndColor();
        float f8 = this.c / 2.0f;
        float f9 = 0.0f;
        for (int i = 0; i < 5; i++) {
            if (this.g[i] > 0.0f) {
                this.b.setColor(this.d[i]);
                if (startColor == this.d[i]) {
                    this.b.setColor(this.f8939a.getResources().getColor(R.color.transparent));
                    canvas.drawRect(new RectF(0.0f, 0.0f, this.j, this.c), this.b);
                    this.b.setColor(this.d[i]);
                    a2 = f9 + f8;
                    canvas.drawCircle(f8, f8, f8, this.b);
                } else {
                    a2 = f9 + a(1, 1.0f);
                }
                float f10 = a2;
                float f11 = f10 + ((this.g[i] / this.i) * f2);
                if (endColor == this.d[i]) {
                    float f12 = this.j;
                    float f13 = f12 - f8;
                    canvas.drawCircle(f13, f8, f8, this.b);
                    canvas.drawRect(f10, 0.0f, f13, this.c, this.b);
                    f9 = f12;
                } else {
                    canvas.drawRect(f10, 0.0f, f11, this.c, this.b);
                    f9 = f11;
                }
            }
        }
    }

    private int getEndColor() {
        int i = this.d[0];
        for (int i2 = 4; i2 >= 0; i2--) {
            if (this.g[i2] > 0.0f) {
                return this.d[i2];
            }
        }
        return i;
    }

    private int getStartColor() {
        int i = this.d[0];
        for (int i2 = 0; i2 < 5; i2++) {
            if (this.g[i2] > 0.0f) {
                return this.d[i2];
            }
        }
        return i;
    }

    private void d() {
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setViewData(float f, float f2, float f3, float f4) {
        this.g = new float[5];
        if (LanguageUtil.bc(this.f8939a)) {
            float[] fArr = this.g;
            fArr[0] = f4;
            fArr[1] = f3;
            fArr[2] = f2;
            fArr[3] = f;
        } else {
            float[] fArr2 = this.g;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[2] = f3;
            fArr2[3] = f4;
        }
        setDate();
    }

    public void setViewData(float f, float f2, float f3, float f4, float f5) {
        this.g = new float[5];
        if (LanguageUtil.bc(this.f8939a)) {
            float[] fArr = this.g;
            fArr[0] = f5;
            fArr[1] = f4;
            fArr[2] = f3;
            fArr[3] = f2;
            fArr[4] = f;
        } else {
            float[] fArr2 = this.g;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[2] = f3;
            fArr2[3] = f4;
            fArr2[4] = f5;
        }
        setDate();
    }

    public void setViewData(float f, float f2, float f3) {
        this.g = new float[5];
        if (LanguageUtil.bc(this.f8939a)) {
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
        setDate();
    }

    public void setViewData(float f, float f2) {
        this.g = new float[5];
        if (LanguageUtil.bc(this.f8939a)) {
            float[] fArr = this.g;
            fArr[0] = f2;
            fArr[1] = f;
        } else {
            float[] fArr2 = this.g;
            fArr2[0] = f;
            fArr2[1] = f2;
        }
        setDate();
    }

    public void setViewData(float f) {
        float[] fArr = new float[5];
        this.g = fArr;
        fArr[0] = f;
        this.i = 0.0f;
        this.e = 0;
        for (int i = 0; i < 5; i++) {
            float f2 = this.g[i];
            if (f2 > 0.0f) {
                this.e++;
                this.i += f2;
            }
        }
    }

    public void setColors(int i) {
        this.d[0] = i;
    }

    public void setColors(int i, int i2) {
        if (LanguageUtil.bc(this.f8939a)) {
            int[] iArr = this.d;
            iArr[0] = i2;
            iArr[1] = i;
        } else {
            int[] iArr2 = this.d;
            iArr2[0] = i;
            iArr2[1] = i2;
        }
    }

    public void setColors(int i, int i2, int i3) {
        if (LanguageUtil.bc(this.f8939a)) {
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

    public void setColors(int i, int i2, int i3, int i4) {
        if (LanguageUtil.bc(this.f8939a)) {
            int[] iArr = this.d;
            iArr[0] = i4;
            iArr[1] = i3;
            iArr[2] = i2;
            iArr[3] = i;
            return;
        }
        int[] iArr2 = this.d;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
        iArr2[3] = i4;
    }

    public void setColors(int i, int i2, int i3, int i4, int i5) {
        if (LanguageUtil.bc(this.f8939a)) {
            int[] iArr = this.d;
            iArr[0] = i5;
            iArr[1] = i4;
            iArr[2] = i3;
            iArr[3] = i2;
            iArr[4] = i;
            return;
        }
        int[] iArr2 = this.d;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
        iArr2[3] = i4;
        iArr2[4] = i5;
    }

    public static float a(int i, float f) {
        return TypedValue.applyDimension(i, f, Resources.getSystem().getDisplayMetrics());
    }

    public void setDate() {
        this.i = 0.0f;
        this.e = 0;
        for (int i = 0; i < 5; i++) {
            float f = this.g[i];
            if (f > 0.0f) {
                this.e++;
                this.i += f;
            }
        }
    }
}
