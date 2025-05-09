package com.huawei.ui.commonui.progressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nsn;
import health.compact.a.util.LogUtil;

/* loaded from: classes9.dex */
public class HealthHwProgressView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f8920a;
    private Context b;
    private int c;
    private float d;
    private Paint e;
    private Paint f;
    private float g;
    private RectF h;
    private float i;
    private int j;
    private int n;

    public HealthHwProgressView(Context context) {
        this(context, null);
        this.b = context;
    }

    public HealthHwProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.b = context;
    }

    public HealthHwProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 7.0f;
        this.n = Color.parseColor("#FFFCA077");
        this.c = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.b = context;
        cEo_(attributeSet);
        d();
    }

    private void d() {
        Paint paint = new Paint();
        this.f = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.f.setAntiAlias(true);
        this.h = new RectF();
        Paint paint2 = new Paint();
        this.f8920a = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.f8920a.setAntiAlias(true);
        this.f8920a.setColor(Color.parseColor("#77999999"));
    }

    private void cEo_(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        Context context = this.b;
        if (context == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthHwProgressView)) == null) {
            return;
        }
        try {
            this.n = obtainStyledAttributes.getColor(R$styleable.HealthHwProgressView_health_progress_view_start_color, Color.parseColor("#FFFCA077"));
            this.c = obtainStyledAttributes.getColor(R$styleable.HealthHwProgressView_health_progress_view_end_color, Color.parseColor(Constants.CHOOSE_TEXT_COLOR));
            this.g = obtainStyledAttributes.getDimension(R$styleable.HealthHwProgressView_health_progress_view_radius, nsn.c(this.b, 7.0f));
        } catch (Resources.NotFoundException unused) {
            LogUtil.e("CommonUI_HealthHwProgressView", "initAttrs NotFoundException");
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(c(i), d(i2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        if (width == 0) {
            width = nsn.c(this.b, 116.0f);
        }
        if (height == 0) {
            height = nsn.c(this.b, 7.0f);
        }
        float f = height / 2.0f;
        cEp_(canvas, width, f);
        float f2 = width;
        this.d = (nsn.c(this.b, 7.0f) * 100.0f) / f2;
        float f3 = this.i;
        if (f3 <= 0.0f) {
            LogUtil.d("CommonUI_HealthHwProgressView", "mRealPercent = 0 do nothing for progress view");
            return;
        }
        LogUtil.d("CommonUI_HealthHwProgressView", "mRealPercent = ", Float.valueOf(f3), ", mMinPercent = ", Float.valueOf(this.d));
        if (this.i <= this.d) {
            cEq_(canvas);
            return;
        }
        if (this.f == null) {
            Paint paint = new Paint();
            this.f = paint;
            paint.setStyle(Paint.Style.STROKE);
            this.f.setAntiAlias(true);
        }
        float f4 = (this.j / 100.0f) * f2;
        this.f.setShader(new LinearGradient(0.0f, f, f4, f, this.n, this.c, Shader.TileMode.CLAMP));
        this.f.setStyle(Paint.Style.FILL);
        if (this.h == null) {
            this.h = new RectF();
        }
        this.h.set(0.0f, 0.0f, f4, getHeight());
        RectF rectF = this.h;
        float f5 = this.g;
        canvas.drawRoundRect(rectF, f5, f5, this.f);
    }

    private void cEq_(Canvas canvas) {
        if (this.e == null) {
            this.e = new Paint();
        }
        float c = nsn.c(this.b, 3.5f);
        this.e.setShader(new LinearGradient(0.0f, c, nsn.c(this.b, 7.0f), c, this.n, this.c, Shader.TileMode.CLAMP));
        canvas.drawCircle(c, c, c, this.e);
    }

    private void cEp_(Canvas canvas, int i, float f) {
        if (this.f8920a == null) {
            Paint paint = new Paint();
            this.f8920a = paint;
            paint.setStyle(Paint.Style.FILL);
            this.f8920a.setAntiAlias(true);
            this.f8920a.setColor(Color.parseColor("#77999999"));
        }
        int c = (i - nsn.c(this.b, 5.5f)) / nsn.c(this.b, 4.0f);
        float f2 = 0.0f;
        for (int i2 = 0; i2 < c + 2; i2++) {
            canvas.drawCircle(nsn.c(this.b, (i2 * 4) + 0.75f), f, nsn.c(this.b, 0.75f), this.f8920a);
            if (i2 == 0) {
                f2 = nsn.c(this.b, 1.5f);
            } else {
                f2 += nsn.c(this.b, 4.0f);
            }
            if (f2 >= i) {
                return;
            }
        }
    }

    protected int c(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int c = nsn.c(this.b, 116.0f);
        return mode == Integer.MIN_VALUE ? Math.min(c, size) : c;
    }

    protected int d(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int c = nsn.c(this.b, 7.0f);
        return mode == Integer.MIN_VALUE ? Math.min(c, size) : c;
    }
}
