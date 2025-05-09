package com.huawei.ui.device.views.device;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;
import defpackage.obv;

/* loaded from: classes6.dex */
public class RoundProgressImageView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f9312a;
    private int b;
    private float c;
    private Paint d;
    private int e;
    private int f;
    private boolean g;
    private float h;
    private int i;
    private int j;
    private int l;
    private float n;
    private Paint o;

    public RoundProgressImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 100.0f;
        this.c = 0.0f;
        this.g = false;
        c();
    }

    private void c() {
        LogUtil.a("ProgressBarView", "init");
        Paint paint = new Paint();
        this.d = paint;
        paint.setAntiAlias(true);
        this.d.setDither(true);
        this.d.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.o = paint2;
        paint2.setAntiAlias(true);
        this.o.setDither(true);
        this.o.setStrokeCap(Paint.Cap.ROUND);
    }

    private void getCircleCenter() {
        LogUtil.a("ProgressBarView", "getCircleCenter getWidth() : ", Integer.valueOf(getWidth()), " ; getHeight() : ", Integer.valueOf(getHeight()));
        if (this.l == 0 || this.j == 0) {
            this.l = getWidth();
            int height = getHeight();
            this.j = height;
            int min = (Math.min(this.l, height) / 2) - 10;
            this.f = min;
            int i = min / 15;
            this.e = i;
            this.f9312a = i / 6;
            this.b = this.l / 2;
            this.i = this.j / 2;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        LogUtil.a("ProgressBarView", "onDraw");
        getCircleCenter();
        float f2 = (this.n / this.h) * 360.0f;
        LogUtil.a("ProgressBarView", "onDraw sweep : ", Float.valueOf(f2));
        if (this.g) {
            cVd_(canvas);
            return;
        }
        if (nsn.v(BaseApplication.getContext()) && Build.VERSION.SDK_INT >= 29) {
            this.d.setColor(BaseApplication.getContext().getResources().getColor(R.color._2131297331_res_0x7f090433));
        } else {
            this.d.setColor(Color.parseColor("#33000000"));
        }
        this.d.setStrokeWidth(this.f9312a);
        this.d.setStrokeCap(Paint.Cap.ROUND);
        float f3 = 1.6f;
        while (true) {
            if (f3 > 360.0f) {
                break;
            }
            double d = ((0.0f + f3) / 180.0f) * 3.141592653589793d;
            float sin = this.b - (this.f * ((float) Math.sin(d)));
            float cos = this.i + (this.f * ((float) Math.cos(d)));
            canvas.drawLine(sin, cos, sin + (this.e * ((float) Math.sin(d))), cos - (this.e * ((float) Math.cos(d))), this.d);
            f3 += 5.0f;
        }
        this.o.setColor(Color.parseColor("#FFfb6522"));
        this.o.setStrokeWidth(this.f9312a);
        this.o.setStrokeCap(Paint.Cap.ROUND);
        for (f = 0.0f; f < f2; f += 5.0f) {
            double d2 = ((f + 1.6f) / 180.0f) * 3.141592653589793d;
            float sin2 = this.b + (this.f * ((float) Math.sin(d2)));
            float cos2 = this.i - (this.f * ((float) Math.cos(d2)));
            canvas.drawLine(sin2, cos2, sin2 - (this.e * ((float) Math.sin(d2))), cos2 + (this.e * ((float) Math.cos(d2))), this.o);
        }
    }

    private void cVd_(Canvas canvas) {
        float f;
        double d;
        float f2;
        LogUtil.a("ProgressBarView", "onDraw mArcTo :", Float.valueOf(this.c));
        cUZ_(canvas);
        cVb_(canvas);
        cVc_(canvas);
        cVa_(canvas);
        float f3 = this.c + 30.0f;
        this.o.setColor(Color.parseColor("#CCfb6522"));
        this.o.setStrokeWidth(this.f9312a);
        while (true) {
            f = this.c;
            d = 3.141592653589793d;
            if (f3 > f + 30.0f + 17.0f) {
                break;
            }
            double d2 = ((1.6f + f3) / 180.0f) * 3.141592653589793d;
            float sin = this.b + (this.f * ((float) Math.sin(d2)));
            float cos = this.i - (this.f * ((float) Math.cos(d2)));
            canvas.drawLine(sin, cos, sin - (this.e * ((float) Math.sin(d2))), cos + (this.e * ((float) Math.cos(d2))), this.o);
            f3 += 5.0f;
        }
        float f4 = f + 50.0f;
        this.o.setColor(Color.parseColor("#FFfb6522"));
        this.o.setStrokeWidth(this.f9312a);
        while (true) {
            f2 = this.c;
            if (f4 > f2 + 50.0f + 17.0f) {
                break;
            }
            double d3 = ((f4 + 1.6f) / 180.0f) * d;
            float sin2 = this.b + (this.f * ((float) Math.sin(d3)));
            float cos2 = this.i - (this.f * ((float) Math.cos(d3)));
            canvas.drawLine(sin2, cos2, sin2 - (this.e * ((float) Math.sin(d3))), cos2 + (this.e * ((float) Math.cos(d3))), this.o);
            f4 += 5.0f;
            d = 3.141592653589793d;
        }
        this.o.setColor(Color.parseColor("#ffffff"));
        this.o.setStrokeWidth(this.f9312a);
        for (float f5 = f2 + 70.0f; f5 <= this.c + 70.0f + 17.0f; f5 += 5.0f) {
            double d4 = ((f5 + 1.6f) / 180.0f) * 3.141592653589793d;
            float sin3 = this.b + (this.f * ((float) Math.sin(d4)));
            float cos3 = this.i - (this.f * ((float) Math.cos(d4)));
            canvas.drawLine(sin3, cos3, sin3 - (this.e * ((float) Math.sin(d4))), cos3 + (this.e * ((float) Math.cos(d4))), this.o);
        }
    }

    private void cVa_(Canvas canvas) {
        this.o.setColor(Color.parseColor("#92fb6522"));
        this.o.setStrokeWidth(this.f9312a);
        for (float f = this.c + 10.0f; f <= this.c + 10.0f + 17.0f; f += 5.0f) {
            double d = ((1.6f + f) / 180.0f) * 3.141592653589793d;
            float sin = this.b + (this.f * ((float) Math.sin(d)));
            float cos = this.i - (this.f * ((float) Math.cos(d)));
            canvas.drawLine(sin, cos, sin - (this.e * ((float) Math.sin(d))), cos + (this.e * ((float) Math.cos(d))), this.o);
        }
    }

    private void cVc_(Canvas canvas) {
        this.o.setColor(Color.parseColor("#66fb6522"));
        this.o.setStrokeWidth(this.f9312a);
        for (float f = this.c - 10.0f; f <= (this.c - 10.0f) + 17.0f; f += 5.0f) {
            double d = ((1.6f + f) / 180.0f) * 3.141592653589793d;
            float sin = this.b + (this.f * ((float) Math.sin(d)));
            float cos = this.i - (this.f * ((float) Math.cos(d)));
            canvas.drawLine(sin, cos, sin - (this.e * ((float) Math.sin(d))), cos + (this.e * ((float) Math.cos(d))), this.o);
        }
    }

    private void cVb_(Canvas canvas) {
        this.o.setColor(Color.parseColor("#33fb6522"));
        this.o.setStrokeWidth(this.f9312a);
        for (float f = this.c - 30.0f; f <= (this.c - 30.0f) + 17.0f; f += 5.0f) {
            double d = ((1.6f + f) / 180.0f) * 3.141592653589793d;
            float sin = this.b + (this.f * ((float) Math.sin(d)));
            float cos = this.i - (this.f * ((float) Math.cos(d)));
            canvas.drawLine(sin, cos, sin - (this.e * ((float) Math.sin(d))), cos + (this.e * ((float) Math.cos(d))), this.o);
        }
    }

    private void cUZ_(Canvas canvas) {
        this.o.setColor(Color.parseColor("#0cfb6522"));
        this.o.setStrokeWidth(this.f9312a);
        for (float f = this.c - 50.0f; f <= (this.c - 50.0f) + 17.0f; f += 5.0f) {
            double d = ((1.6f + f) / 180.0f) * 3.141592653589793d;
            float sin = this.b + (this.f * ((float) Math.sin(d)));
            float cos = this.i - (this.f * ((float) Math.cos(d)));
            canvas.drawLine(sin, cos, sin - (this.e * ((float) Math.sin(d))), cos + (this.e * ((float) Math.cos(d))), this.o);
        }
    }

    public void a(float f) {
        this.n = f;
        invalidate();
    }

    public void d() {
        this.g = true;
        LogUtil.a("ProgressBarView", "start");
        postInvalidate();
        postDelayed(new obv(this), 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.g) {
            this.c += 10.138f;
            postInvalidate();
            postDelayed(new obv(this), 50L);
        }
    }

    public void e() {
        this.g = false;
        this.c = 0.0f;
        postInvalidate();
    }

    public boolean getIsRunning() {
        return this.g;
    }
}
