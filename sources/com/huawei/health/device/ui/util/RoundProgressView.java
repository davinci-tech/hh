package com.huawei.health.device.ui.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;

/* loaded from: classes3.dex */
public class RoundProgressView extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f2267a;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private Handler j;
    private int l;
    private Paint m;

    static /* synthetic */ float b(RoundProgressView roundProgressView, float f) {
        float f2 = roundProgressView.f2267a + f;
        roundProgressView.f2267a = f2;
        return f2;
    }

    public RoundProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2267a = 0.0f;
        this.h = false;
        this.j = new Handler() { // from class: com.huawei.health.device.ui.util.RoundProgressView.5
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (RoundProgressView.this.h) {
                    RoundProgressView.b(RoundProgressView.this, 10.138f);
                    RoundProgressView.this.postInvalidate();
                    sendEmptyMessageDelayed(0, 50L);
                }
            }
        };
        e();
    }

    private void e() {
        LogUtil.a("ProgressBarView", "init");
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setDither(true);
        this.b.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.m = paint2;
        paint2.setAntiAlias(true);
        this.m.setDither(true);
        this.m.setStrokeCap(Paint.Cap.ROUND);
        this.c = nsn.c(getContext(), 8.0f);
        this.e = nsn.c(getContext(), 1.0f);
    }

    private void getCircleCenter() {
        if (this.l == 0 || this.i == 0) {
            this.l = getWidth();
            this.i = getHeight();
            this.g = (Math.min(this.l, r0) / 2) - 10;
            this.d = this.l / 2;
            this.f = this.i / 2;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null) {
            LogUtil.a("ProgressBarView", "onDraw convas is null");
            return;
        }
        getCircleCenter();
        if (this.h) {
            Ke_(this.f2267a - 50.0f, "#0cfb6522", canvas);
            Ke_(this.f2267a - 30.0f, "#33fb6522", canvas);
            Ke_(this.f2267a - 10.0f, "#66fb6522", canvas);
            Ke_(this.f2267a + 10.0f, "#92fb6522", canvas);
            Ke_(this.f2267a + 30.0f, "#CCfb6522", canvas);
            Ke_(this.f2267a + 50.0f, "#FFfb6522", canvas);
            Ke_(this.f2267a + 70.0f, "#ffffff", canvas);
            return;
        }
        Kd_(canvas);
        a();
    }

    private void Ke_(float f, String str, Canvas canvas) {
        this.m.setColor(Color.parseColor(str));
        this.m.setStrokeWidth(this.e);
        for (float f2 = f; f2 <= 17.0f + f; f2 += 3.6363637f) {
            Kf_(((1.6f + f2) / 180.0f) * 3.141592653589793d, canvas);
        }
    }

    private void Kd_(Canvas canvas) {
        this.b.setColor(ContextCompat.getColor(getContext(), R.color._2131297331_res_0x7f090433));
        this.b.setStrokeWidth(this.e);
        this.b.setStrokeCap(Paint.Cap.ROUND);
        for (float f = 1.6f; f <= 360.0f; f += 3.6363637f) {
            double d = ((0.0f + f) / 180.0f) * 3.141592653589793d;
            float sin = this.d - (this.g * ((float) Math.sin(d)));
            float cos = this.f + (this.g * ((float) Math.cos(d)));
            canvas.drawLine(sin, cos, sin + (this.c * ((float) Math.sin(d))), cos - (this.c * ((float) Math.cos(d))), this.b);
        }
    }

    private void a() {
        this.m.setColor(Color.parseColor("#FFfb6522"));
        this.m.setStrokeWidth(this.e);
        this.m.setStrokeCap(Paint.Cap.ROUND);
    }

    private void Kf_(double d, Canvas canvas) {
        float sin = this.d + (this.g * ((float) Math.sin(d)));
        float cos = this.f - (this.g * ((float) Math.cos(d)));
        canvas.drawLine(sin, cos, sin - (this.c * ((float) Math.sin(d))), cos + (this.c * ((float) Math.cos(d))), this.m);
    }

    public void b() {
        if (this.h) {
            return;
        }
        this.h = true;
        this.j.sendEmptyMessageDelayed(0, 50L);
    }

    public void c() {
        this.h = false;
        this.f2267a = 0.0f;
        this.j.removeCallbacksAndMessages(null);
        postInvalidate();
    }
}
