package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes3.dex */
public class StepView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f2603a;
    private float b;
    private Paint c;
    private float d;
    private float e;
    private int f;
    private Context g;
    private float h;
    private float i;
    private float j;
    private float k;
    private RectF l;
    private float m;
    private float n;
    private RectF o;
    private float p;
    private Paint q;
    private Paint r;
    private float s;
    private OnStepViewClickListener t;
    private float u;
    private float v;
    private Paint x;
    private float y;

    public interface OnStepViewClickListener {
        void onStepViewRightCircleClick();
    }

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StepView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.y = nsn.c(getContext(), 15.0f);
        this.v = nsn.c(getContext(), 53.5f);
        this.u = nsn.c(getContext(), 7.5f);
        this.e = nsn.c(getContext(), 2.0f);
        this.h = nsn.c(getContext(), 1.0f);
        this.i = nsn.c(getContext(), 1.25f);
        this.d = nsn.c(getContext(), 0.9f);
        this.f = 0;
        this.b = nsn.c(getContext(), 1.0f);
        this.g = context;
        this.f = new HealthColumnSystem(this.g, 1).f();
        this.s = 0.0f;
        this.p = 0.0f;
        setLayerType(1, null);
        Context context2 = getContext();
        if (c()) {
            this.y = nsn.c(context2, 15.0f);
            this.v = nsn.c(context2, 40.0f);
            this.u = nsn.c(context2, 7.5f);
        }
        if (nsn.r()) {
            this.v = nsn.c(context2, 60.0f);
        }
        b();
    }

    private boolean c() {
        return this.f >= 8;
    }

    private void b() {
        Paint paint = new Paint();
        this.q = paint;
        paint.setAntiAlias(true);
        this.q.setStyle(Paint.Style.STROKE);
        this.q.setColor(872415231);
        this.q.setStrokeWidth(this.y);
        Paint paint2 = new Paint();
        this.x = paint2;
        paint2.setAntiAlias(true);
        this.x.setStyle(Paint.Style.STROKE);
        this.x.setStrokeJoin(Paint.Join.ROUND);
        this.x.setStrokeCap(Paint.Cap.ROUND);
        this.x.setColor(-1);
        this.x.setStrokeWidth(this.y);
        Paint paint3 = new Paint();
        this.r = paint3;
        paint3.setAntiAlias(true);
        this.r.setStrokeWidth(this.y);
        this.r.setStyle(Paint.Style.FILL);
        this.r.setColor(-1);
        if (nsn.ag(this.g)) {
            this.r.setShadowLayer(5.5f, 9.0f, 0.0f, 1308588059);
        } else {
            this.r.setShadowLayer(7.5f, 11.0f, 0.0f, 1308588059);
        }
        Paint paint4 = new Paint();
        this.f2603a = paint4;
        paint4.setAntiAlias(true);
        this.f2603a.setFilterBitmap(true);
        this.f2603a.setStyle(Paint.Style.FILL);
        this.f2603a.setStrokeCap(Paint.Cap.ROUND);
        this.f2603a.setStrokeWidth(this.d);
        Paint paint5 = new Paint();
        this.c = paint5;
        paint5.setAntiAlias(true);
        this.c.setColor(-301790);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeCap(Paint.Cap.ROUND);
        this.c.setStrokeWidth(this.b);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (nsn.ag(this.g)) {
            this.j = nsn.c(this.g, 40.0f) + (this.y / 2.0f);
            this.n = nsn.c(this.g, 40.0f) + (this.y / 2.0f);
        } else if (nsn.r()) {
            this.j = nsn.c(this.g, 60.0f) + (this.y / 2.0f);
            this.n = nsn.c(this.g, 60.0f) + (this.y / 2.0f);
        } else {
            this.j = nsn.c(this.g, 53.5f) + (this.y / 2.0f);
            this.n = nsn.c(this.g, 53.5f) + (this.y / 2.0f);
        }
        float f = this.y / 2.0f;
        float f2 = this.j;
        float f3 = this.v;
        this.l = new RectF(f, f, f2 + f3, this.n + f3);
        if (nsn.ag(this.g)) {
            this.m = i - (nsn.c(this.g, 40.0f) + (this.y / 2.0f));
            this.k = nsn.c(this.g, 40.0f) + (this.y / 2.0f);
        } else if (nsn.r()) {
            this.m = i - (nsn.c(this.g, 60.0f) + (this.y / 2.0f));
            this.k = nsn.c(this.g, 60.0f) + (this.y / 2.0f);
        } else {
            this.m = i - (nsn.c(this.g, 53.5f) + (this.y / 2.0f));
            this.k = nsn.c(this.g, 53.5f) + (this.y / 2.0f);
        }
        float f4 = this.m;
        float f5 = this.v;
        float f6 = this.k;
        this.o = new RectF(f4 - f5, f6 - f5, f4 + f5, f6 + f5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000a, code lost:
    
        if (r0 != 2) goto L12;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto Le
            if (r0 == r1) goto Ld
            r2 = 2
            if (r0 == r2) goto Le
            goto L19
        Ld:
            return r1
        Le:
            float r0 = r4.getX()
            boolean r0 = r3.d(r0)
            if (r0 == 0) goto L19
            return r1
        L19:
            boolean r4 = super.onTouchEvent(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.knit.section.chart.StepView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean d(float f) {
        OnStepViewClickListener onStepViewClickListener;
        if (LanguageUtil.bc(this.g)) {
            if (f <= this.y / 2.0f || f >= this.j + this.v || (onStepViewClickListener = this.t) == null) {
                return false;
            }
            onStepViewClickListener.onStepViewRightCircleClick();
            return true;
        }
        if (f <= this.m - this.v) {
            return false;
        }
        OnStepViewClickListener onStepViewClickListener2 = this.t;
        if (onStepViewClickListener2 != null) {
            onStepViewClickListener2.onStepViewRightCircleClick();
        }
        return true;
    }

    public void setOnStepViewListener(OnStepViewClickListener onStepViewClickListener) {
        this.t = onStepViewClickListener;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        agl_(canvas);
        agm_(canvas);
        Context context = this.g;
        if (context != null && LanguageUtil.bc(context)) {
            agn_(canvas, this.s, this.j, this.n);
        } else {
            agn_(canvas, this.p, this.m, this.k);
        }
    }

    private void agn_(Canvas canvas, float f, float f2, float f3) {
        for (int i = 0; i < 60; i++) {
            if (i % 20 == 0) {
                if ((i * 6.0f) / 360.0f <= 0.016666668f + f && f > 0.0f) {
                    this.f2603a.setColor(-1390924);
                } else {
                    this.f2603a.setColor(-1073741825);
                }
                this.f2603a.setStrokeWidth(this.i);
                float f4 = this.v;
                float f5 = this.e;
                float f6 = f3 - f4;
                canvas.drawLine(f2, f6 - f5, f2, f6 + f5, this.f2603a);
            } else {
                if ((i * 6.0f) / 360.0f <= 0.015277778f + f && f > 0.0f) {
                    this.f2603a.setColor(-1121058);
                } else {
                    this.f2603a.setColor(1660944383);
                }
                this.f2603a.setStrokeWidth(this.h);
                float f7 = this.v;
                float f8 = this.e;
                float f9 = f3 - f7;
                canvas.drawLine(f2, f9 - f8, f2, f9 + f8, this.f2603a);
            }
            canvas.rotate(6.0f, f2, f3);
        }
    }

    private void agm_(Canvas canvas) {
        LogUtil.a("StepView", "drawCircle left ", Float.valueOf(this.s), " right ", Float.valueOf(this.p));
        agk_(canvas, this.m, this.k, this.p, this.o);
        agk_(canvas, this.j, this.n, this.s, this.l);
    }

    private void agk_(Canvas canvas, float f, float f2, float f3, RectF rectF) {
        if (f3 >= 1.0f) {
            canvas.drawCircle(f, f2, this.v, this.x);
            float a2 = a(f3);
            canvas.save();
            canvas.rotate(a2 * 360.0f, f, f2);
            canvas.drawCircle(f, f2 - this.v, this.u, this.r);
            canvas.restore();
            return;
        }
        canvas.drawArc(rectF, -90.0f, f3 * 360.0f, false, this.x);
    }

    private void agl_(Canvas canvas) {
        canvas.drawCircle(this.j, this.n, this.v, this.q);
        canvas.drawCircle(this.m, this.k, this.v, this.q);
    }

    public void setLeftProgress(float f) {
        if (f > 1.0f) {
            f = a(f) + 1.0f;
        }
        Context context = this.g;
        if (context != null && LanguageUtil.bc(context)) {
            setProgress(this.s, f);
        } else {
            setProgress(f, this.p);
        }
    }

    public void setRightProgress(float f) {
        if (f > 1.0f) {
            f = a(f) + 1.0f;
        }
        Context context = this.g;
        if (context != null && LanguageUtil.bc(context)) {
            setProgress(f, this.p);
        } else {
            setProgress(this.s, f);
        }
    }

    public void setProgress(float f, float f2) {
        this.s = f;
        this.p = f2;
        postInvalidate();
    }

    private float a(float f) {
        return f - ((float) Math.floor(f));
    }
}
