package com.huawei.ui.commonui.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nsn;
import defpackage.ntg;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class HealthBodyBarView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f8972a;
    private int b;
    private Context c;
    private float d;
    private float e;
    private boolean f;
    private boolean g;
    private float h;
    private Paint i;
    private int j;
    private int l;

    private float a(float f, float f2, float f3) {
        float f4 = f3 - f2;
        if (f4 <= 0.0f) {
            return 0.0f;
        }
        return (f - f2) / f4;
    }

    public HealthBodyBarView(Context context) {
        this(context, null);
    }

    public HealthBodyBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthBodyBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = 0;
        this.l = 0;
        this.c = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthBodyBarView);
        try {
            this.f8972a = obtainStyledAttributes.getColor(R$styleable.HealthBodyBarView_bar_color, ContextCompat.getColor(BaseApplication.e(), R$color.indicate_low_color));
            this.d = obtainStyledAttributes.getDimension(R$styleable.HealthBodyBarView_body_bar_text_size, nsn.c(getContext(), 10.0f));
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthBodyBarView", "Resources NotFoundException.");
        }
        obtainStyledAttributes.recycle();
        Paint paint = new Paint(1);
        this.i = paint;
        paint.setAntiAlias(true);
        setWillNotDraw(false);
        this.f = LanguageUtil.b(this.c);
    }

    public void setBarData(ntg ntgVar, int i) {
        if (ntgVar == null) {
            return;
        }
        if (i == 0) {
            this.e = ntgVar.c();
        } else {
            this.e = ntgVar.b();
        }
        this.b = e(ntgVar, this.e);
        float f = this.e;
        if (f <= 0.0f) {
            return;
        }
        if (f >= ntgVar.e()) {
            f = ntgVar.e();
        }
        int i2 = this.b;
        if (i2 == 0) {
            this.h = a(f, ntgVar.h(), ntgVar.g());
        } else if (i2 == 1) {
            this.h = a(f, ntgVar.g(), ntgVar.a());
        } else {
            this.h = a(f, ntgVar.a(), ntgVar.e());
        }
        this.f8972a = c(ntgVar, i);
        invalidate();
    }

    private int e(ntg ntgVar, float f) {
        if (ntgVar == null) {
            return 0;
        }
        int i = f >= ntgVar.g() ? f < ntgVar.a() ? 1 : 2 : 0;
        LogUtil.a("HealthBodyBarView", "getIndicatorState()  ", Integer.valueOf(i));
        return i;
    }

    private int c(ntg ntgVar, int i) {
        int color = ContextCompat.getColor(BaseApplication.e(), R$color.indicate_gray_color);
        return (ntgVar != null && i == 0) ? ContextCompat.getColor(BaseApplication.e(), R$color.indicate_low_color) : color;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.j = View.MeasureSpec.getSize(i2);
        int size = View.MeasureSpec.getSize(i);
        this.l = size;
        setMeasuredDimension(size, this.j);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cMF_(canvas);
    }

    private void cMF_(Canvas canvas) {
        float f;
        float f2;
        float f3;
        if (this.e <= 0.0f) {
            return;
        }
        Paint paint = new Paint(this.i);
        paint.setColor(this.f8972a);
        Paint textPaint = getTextPaint();
        String e = UnitUtil.e(this.e, 1, 1);
        if (this.g && this.f) {
            e = UnitUtil.e(this.e, 2, 1);
        }
        String str = e;
        float measureText = textPaint.measureText(str);
        float f4 = this.j / 2.0f;
        float a2 = a(getIndicatorLength(), f4);
        float cMH_ = cMH_(textPaint, measureText, a2, f4);
        if (LanguageUtil.bc(this.c.getApplicationContext())) {
            float f5 = this.l;
            f = f5 - cMH_;
            f2 = f5 - a2;
            f3 = f5 - 0.0f;
        } else {
            f = cMH_;
            f2 = a2;
            f3 = 0.0f;
        }
        canvas.drawRect(f3, 0.0f, f2, this.j + 0.0f, paint);
        canvas.drawCircle(f2, f4, f4, paint);
        canvas.drawText(str, f, f4 + (cMG_(str, textPaint) / 2.0f), textPaint);
    }

    private float a(float f, float f2) {
        float f3 = (-f2) / 2.0f;
        LogUtil.a("HealthBodyBarView", "getLineEndPosition indicatorLength ", Float.valueOf(f), " minEndPosition ", Float.valueOf(f3));
        return Math.max(f - f2, f3);
    }

    private float cMH_(Paint paint, float f, float f2, float f3) {
        float f4 = f / 2.0f;
        float f5 = f2 - f4;
        if (f <= f2) {
            return f5;
        }
        if (f < f2 + f3) {
            return f4 + 3.0f;
        }
        paint.setColor(this.f8972a);
        return f2 + (f3 * 2.0f) + f4;
    }

    private Paint getTextPaint() {
        Paint paint = new Paint(this.i);
        paint.setColor(this.c.getResources().getColor(R$color.common_color_white));
        paint.setTextSize(this.d);
        paint.setTypeface(Typeface.create(this.c.getResources().getString(R$string.textFontFamilyRegular), 0));
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    private float getIndicatorLength() {
        float f;
        float f2;
        float f3;
        float f4;
        int i = this.b;
        if (i == 0) {
            f = this.l * 0.33333334f;
            f4 = this.h;
        } else {
            if (i == 1) {
                f = this.l * 0.33333334f;
                f2 = this.h;
                f3 = 1.0f;
            } else {
                f = this.l * 0.33333334f;
                f2 = this.h;
                f3 = 2.0f;
            }
            f4 = f2 + f3;
        }
        float f5 = f * f4;
        if (f5 < 0.0f) {
            return 0.0f;
        }
        return f5;
    }

    private int cMG_(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public void setFatData(boolean z) {
        this.g = z;
    }
}
