package com.huawei.ui.commonui.progressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BaseInterpolator;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nrf;

/* loaded from: classes6.dex */
public class CircleProgressBar extends View {

    /* renamed from: a, reason: collision with root package name */
    private int[] f8919a;
    private float[] aa;
    private Paint ab;
    private boolean ac;
    private float ad;
    private int ae;
    private Matrix af;
    private float ag;
    private SweepGradient ah;
    private float ai;
    private float ak;
    private Matrix b;
    private float c;
    private int d;
    private ValueAnimator e;
    private float f;
    private float[] g;
    private float h;
    private float i;
    private SweepGradient j;
    private int k;
    private Paint l;
    private float m;
    private float n;
    private int[] o;
    private float p;
    private int q;
    private Rect r;
    private float s;
    private Bitmap t;
    private boolean u;
    private int v;
    private boolean w;
    private boolean x;
    private int y;
    private RectF z;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Matrix();
        this.af = new Matrix();
        this.w = true;
        this.x = true;
        this.ac = true;
        this.m = 0.0f;
        this.ag = 0.0f;
        this.k = 0;
        this.d = -3355444;
        this.ae = -16776961;
        this.n = TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics());
        this.ai = 1.0f;
        this.e = new ValueAnimator();
        this.ak = 0.0f;
        this.i = 0.0f;
        this.y = 0;
        this.q = 0;
        this.p = 0.0f;
        this.s = 0.0f;
        this.f = 0.0f;
        this.h = 0.0f;
        this.ad = 0.0f;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.circleProgressBar, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R$styleable.circleProgressBar_bottomCircleColor) {
                this.d = obtainStyledAttributes.getColor(index, -3355444);
            } else if (index == R$styleable.circleProgressBar_processColor) {
                this.ae = obtainStyledAttributes.getColor(index, -16776961);
            } else if (index == R$styleable.circleProgressBar_circleWidth) {
                this.n = obtainStyledAttributes.getDimensionPixelSize(index, (int) TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics()));
            }
        }
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.l = paint;
        paint.setAntiAlias(true);
        this.l.setDither(true);
        Paint paint2 = new Paint();
        this.ab = paint2;
        paint2.setAntiAlias(true);
        this.ab.setDither(true);
    }

    public void setCircleAngle(float f, float f2, float f3) {
        this.f = f;
        this.h = f3;
        this.ad = f2;
    }

    public void setStartIconRes(int i, float f, float f2, float f3, float f4) {
        this.v = i;
        this.y = (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
        this.q = (int) TypedValue.applyDimension(1, f2, getResources().getDisplayMetrics());
        this.p = f3;
        this.s = f4;
    }

    public void setCircleWidth(float f) {
        float applyDimension = TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
        this.n = applyDimension;
        this.l.setStrokeWidth(applyDimension * this.ai);
        LogUtil.a("CircleProgressBar", "setCircleWidth circleWidth = ", Float.valueOf(this.n * this.ai));
        invalidate();
    }

    public void setCircleScale(float f) {
        this.ai = f;
    }

    public void setBottomCircleColor(int i) {
        this.x = true;
        this.d = i;
        this.l.setColor(i);
        invalidate();
    }

    public void setBottomCircleGradientColor(int[] iArr, float[] fArr, int i) {
        if (iArr == null || iArr.length == 0) {
            Log.i("CircleProgressBar", "GradientColor colorArray cannot be null");
            return;
        }
        this.x = false;
        if (fArr != null && iArr.length != fArr.length) {
            throw new IllegalArgumentException("positionArray length must be same as colorArray");
        }
        int[] iArr2 = new int[iArr.length];
        this.f8919a = iArr2;
        try {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        } catch (IndexOutOfBoundsException unused) {
            health.compact.a.util.LogUtil.e("CircleProgressBar", "index exception");
            int i2 = this.d;
            this.f8919a = new int[]{i2, i2};
        }
        if (fArr != null) {
            for (int i3 = 0; i3 < fArr.length; i3++) {
                this.g[i3] = fArr[i3];
            }
        }
        this.i = i;
    }

    public void setCircleOverlap(boolean z) {
        this.u = z;
    }

    public void setIsIconVisible(boolean z) {
        this.ac = z;
    }

    public void setProgressColor(int i) {
        this.w = true;
        this.ae = i;
        this.l.setColor(i);
        invalidate();
    }

    public void setProgressGradientColor(int[] iArr, float[] fArr, float f) {
        if (iArr == null || iArr.length == 0) {
            Log.i("CircleProgressBar", "GradientColor colorArray cannot be null");
            return;
        }
        this.w = false;
        if (fArr != null && iArr.length != fArr.length) {
            throw new IllegalArgumentException("positionArray length must be same as colorArray");
        }
        int[] iArr2 = new int[iArr.length];
        this.o = iArr2;
        try {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        } catch (IndexOutOfBoundsException unused) {
            health.compact.a.util.LogUtil.e("CircleProgressBar", "index exception");
            int i = this.ae;
            this.o = new int[]{i, i};
        }
        if (fArr != null) {
            for (int i2 = 0; i2 < fArr.length; i2++) {
                this.aa[i2] = fArr[i2];
            }
        }
        this.ak = f;
    }

    public void a() {
        cEn_(0.0f, null, 0);
    }

    public void b(float f) {
        cEn_(f, null, 0);
    }

    public void cEn_(float f, BaseInterpolator baseInterpolator, int i) {
        health.compact.a.util.LogUtil.d("CircleProgressBar", "showProcess targetPercent = ", Float.valueOf(f));
        float f2 = f < 0.0f ? 0.0f : f;
        if (f > 100.0f) {
            f2 = Math.max(f % 100.0f, 0.3f) + 100.0f;
        }
        float f3 = (this.u || f <= 100.0f) ? f2 : 100.0f;
        if (baseInterpolator == null) {
            this.m = f3;
            invalidate();
            return;
        }
        if (i <= 0) {
            this.m = f3;
            invalidate();
            return;
        }
        health.compact.a.util.LogUtil.d("CircleProgressBar", "showProcess true settingPercent = ", Float.valueOf(f3));
        ValueAnimator valueAnimator = this.e;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, f3);
        this.e = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.commonui.progressbar.CircleProgressBar.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                CircleProgressBar.this.m = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                CircleProgressBar.this.invalidate();
            }
        });
        this.e.setInterpolator(baseInterpolator);
        this.e.setDuration(i);
        this.e.start();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(Math.max(size, size2), Math.max(size, size2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        this.k = width;
        float f = width - ((this.n * this.ai) / 2.0f);
        this.ag = f;
        if (this.z == null) {
            float f2 = width;
            float f3 = f2 - f;
            float f4 = f2 + f;
            this.z = new RectF(f3, f3, f4, f4);
        }
        this.l.setStrokeWidth(this.n * this.ai);
        cEj_(canvas, this.z);
        cEl_(canvas, this.z);
        cEk_(canvas, this.z);
        cEm_(canvas);
    }

    private void cEl_(Canvas canvas, RectF rectF) {
        if (!this.u || this.m <= 100.0f) {
            return;
        }
        this.ab.setColor(BaseApplication.e().getColor(R$color.common_black_3alpha));
        this.ab.setStrokeWidth((this.n - 6.0f) * this.ai);
        this.ab.setShader(null);
        this.ab.setStyle(Paint.Style.STROKE);
        this.ab.setStrokeCap(Paint.Cap.ROUND);
        this.ab.setShadowLayer(7.0f, 0.0f, 0.0f, BaseApplication.e().getColor(R$color.common_black_30alpha));
        canvas.drawArc(rectF, this.ad, (((this.m - 100.0f) / 100.0f) * Math.abs(this.h)) + ((3.0f / (getWidth() / TypedValue.applyDimension(1, 158.0f, BaseApplication.e().getResources().getDisplayMetrics()))) * this.ai), false, this.ab);
    }

    private void cEm_(Canvas canvas) {
        int i;
        if (this.ac && (i = this.v) != 0) {
            if (this.t == null) {
                this.t = nrf.cHR_(i);
            }
            if (this.t == null) {
                Log.i("CircleProgressBar", "decodeResource bitmap res failed, resId: " + this.v);
            } else {
                if (this.r == null) {
                    this.r = getIconRect();
                }
                canvas.drawBitmap(this.t, (Rect) null, this.r, (Paint) null);
            }
        }
    }

    private Rect getIconRect() {
        double sin = Math.sin(Math.toRadians(this.f));
        double cos = Math.cos(Math.toRadians(this.f));
        double d = this.ag;
        float f = this.p;
        float f2 = this.ai;
        float f3 = (float) ((cos * d) + d + (f * f2));
        float f4 = (float) (d + (sin * d) + (this.s * f2));
        float f5 = (this.y * f2) / 2.0f;
        float f6 = (this.q * f2) / 2.0f;
        return new Rect((int) (f3 - f5), (int) (f4 - f6), (int) (f3 + f5), (int) (f4 + f6));
    }

    private void cEj_(Canvas canvas, RectF rectF) {
        if (this.x) {
            this.l.setColor(this.d);
        } else if (this.m > 100.0f) {
            c(this.o);
        } else {
            c(this.f8919a);
        }
        this.l.setShader(null);
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setShader(this.j);
        this.l.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, this.f, this.h, false, this.l);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cEk_(android.graphics.Canvas r9, android.graphics.RectF r10) {
        /*
            r8 = this;
            boolean r0 = r8.u
            r1 = 1120403456(0x42c80000, float:100.0)
            if (r0 == 0) goto L18
            float r0 = r8.m
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L18
            float r0 = r0 - r1
            float r0 = r0 / r1
            float r1 = r8.h
            float r1 = java.lang.Math.abs(r1)
            float r0 = r0 * r1
            r8.c = r0
            goto L24
        L18:
            float r0 = r8.m
            float r0 = r0 / r1
            float r1 = r8.h
            float r1 = java.lang.Math.abs(r1)
            float r0 = r0 * r1
            r8.c = r0
        L24:
            boolean r0 = r8.w
            if (r0 == 0) goto L30
            android.graphics.Paint r0 = r8.l
            int r1 = r8.ae
            r0.setColor(r1)
            goto L33
        L30:
            r8.b()
        L33:
            android.graphics.Paint r0 = r8.l
            r1 = 0
            r0.setShader(r1)
            android.graphics.Paint r0 = r8.l
            android.graphics.Paint$Style r1 = android.graphics.Paint.Style.STROKE
            r0.setStyle(r1)
            android.graphics.Paint r0 = r8.l
            android.graphics.SweepGradient r1 = r8.ah
            r0.setShader(r1)
            android.graphics.Paint r0 = r8.l
            android.graphics.Paint$Cap r1 = android.graphics.Paint.Cap.ROUND
            r0.setStrokeCap(r1)
            float r4 = r8.ad
            float r5 = r8.c
            r6 = 0
            android.graphics.Paint r7 = r8.l
            r2 = r9
            r3 = r10
            r2.drawArc(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.progressbar.CircleProgressBar.cEk_(android.graphics.Canvas, android.graphics.RectF):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0044 A[LOOP:0: B:12:0x003f->B:14:0x0044, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0052 A[EDGE_INSN: B:15:0x0052->B:16:0x0052 BREAK  A[LOOP:0: B:12:0x003f->B:14:0x0044], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b() {
        /*
            r5 = this;
            boolean r0 = r5.u
            r1 = 1120403456(0x42c80000, float:100.0)
            if (r0 == 0) goto L18
            float r0 = r5.m
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L18
            float r0 = r0 - r1
            float r0 = r0 / r1
            float r2 = r5.h
            float r2 = java.lang.Math.abs(r2)
            float r0 = r0 * r2
            r5.c = r0
            goto L24
        L18:
            float r0 = r5.m
            float r0 = r0 / r1
            float r2 = r5.h
            float r2 = java.lang.Math.abs(r2)
            float r0 = r0 * r2
            r5.c = r0
        L24:
            boolean r0 = r5.u
            if (r0 == 0) goto L32
            float r0 = r5.m
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L2f
            goto L32
        L2f:
            float r0 = r5.c
            goto L34
        L32:
            float r0 = r5.h
        L34:
            r1 = 1135869952(0x43b40000, float:360.0)
            float r0 = r0 / r1
            int[] r1 = r5.o
            int r1 = r1.length
            float[] r1 = new float[r1]
            r5.aa = r1
            r1 = 0
        L3f:
            int[] r2 = r5.o
            int r3 = r2.length
            if (r1 >= r3) goto L52
            float[] r3 = r5.aa
            float r4 = (float) r1
            float r4 = r4 * r0
            int r2 = r2.length
            int r2 = r2 + (-1)
            float r2 = (float) r2
            float r4 = r4 / r2
            r3[r1] = r4
            int r1 = r1 + 1
            goto L3f
        L52:
            int r0 = r5.k
            android.graphics.SweepGradient r1 = new android.graphics.SweepGradient
            float r0 = (float) r0
            int[] r2 = r5.o
            float[] r3 = r5.aa
            r1.<init>(r0, r0, r2, r3)
            r5.ah = r1
            android.graphics.Matrix r0 = r5.af
            if (r0 != 0) goto L6b
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            r5.af = r0
        L6b:
            android.graphics.Matrix r0 = r5.af
            float r1 = r5.ad
            float r2 = r5.ak
            int r3 = r5.k
            float r1 = r1 - r2
            float r2 = (float) r3
            r0.setRotate(r1, r2, r2)
            android.graphics.SweepGradient r0 = r5.ah
            android.graphics.Matrix r1 = r5.af
            r0.setLocalMatrix(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.progressbar.CircleProgressBar.b():void");
    }

    private void c(int[] iArr) {
        float f = this.h / 360.0f;
        this.g = new float[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            this.g[i] = (i * f) / (iArr.length - 1);
        }
        float f2 = this.k;
        this.j = new SweepGradient(f2, f2, iArr, this.g);
        if (this.b == null) {
            this.b = new Matrix();
        }
        Matrix matrix = this.b;
        float f3 = this.f - this.i;
        float f4 = this.k;
        matrix.setRotate(f3, f4, f4);
        this.j.setLocalMatrix(this.b);
    }
}
