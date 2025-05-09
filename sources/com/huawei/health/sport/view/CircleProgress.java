package com.huawei.health.sport.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.sport.view.CircleProgress;
import health.compact.a.util.LogUtil;

/* loaded from: classes8.dex */
public class CircleProgress extends View {
    private static final String b = "CircleProgress";

    /* renamed from: a, reason: collision with root package name */
    private long f3000a;
    private boolean c;
    private float d;
    private ValueAnimator e;
    private Paint f;
    private float g;
    private int[] h;
    private Paint i;
    private int[] j;
    private int k;
    private Context l;
    private float m;
    private Point n;
    private ProgressUpdate o;
    private RectF p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float x;

    public interface ProgressUpdate {
        void onUpdate(int i, int i2);
    }

    public CircleProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new int[]{-1, -1};
        this.h = new int[]{-1, -1};
        axd_(context, attributeSet);
    }

    private void axd_(Context context, AttributeSet attributeSet) {
        this.l = context;
        this.k = c(context, 0.0f);
        this.e = new ValueAnimator();
        this.p = new RectF();
        this.n = new Point();
        axe_(attributeSet);
        c();
    }

    private void axe_(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.l.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099729_res_0x7f060051, R.attr._2131099732_res_0x7f060054, R.attr._2131099734_res_0x7f060056, R.attr._2131099736_res_0x7f060058, R.attr._2131099777_res_0x7f060081, R.attr._2131101138_res_0x7f0605d2, R.attr._2131101179_res_0x7f0605fb});
        this.c = obtainStyledAttributes.getBoolean(1, true);
        this.g = obtainStyledAttributes.getDimension(3, 8.0f);
        this.t = obtainStyledAttributes.getFloat(5, 135.0f);
        this.s = obtainStyledAttributes.getFloat(6, 270.0f);
        this.f3000a = obtainStyledAttributes.getInt(0, 1000);
        int resourceId = obtainStyledAttributes.getResourceId(2, 0);
        if (resourceId != 0) {
            try {
                int[] intArray = getResources().getIntArray(resourceId);
                if (intArray.length == 0) {
                    int color = getResources().getColor(resourceId);
                    this.j = new int[]{color, color};
                } else if (intArray.length == 1) {
                    this.j = new int[]{intArray[0], intArray[0]};
                } else {
                    this.j = intArray;
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.e(b, "the give resource not found.");
            }
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(4, 0);
        if (resourceId2 != 0) {
            try {
                int[] intArray2 = getResources().getIntArray(resourceId2);
                if (intArray2.length == 0) {
                    int color2 = getResources().getColor(resourceId2);
                    this.h = new int[]{color2, color2};
                } else if (intArray2.length == 1) {
                    this.h = new int[]{intArray2[0], intArray2[0]};
                } else {
                    this.h = intArray2;
                }
            } catch (Resources.NotFoundException unused2) {
                LogUtil.e(b, "the give resource not found.");
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void c() {
        Paint paint = new Paint();
        this.f = paint;
        paint.setAntiAlias(this.c);
        this.f.setStyle(Paint.Style.STROKE);
        this.f.setStrokeWidth(this.g);
        this.f.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.i = paint2;
        paint2.setAntiAlias(this.c);
        this.i.setStyle(Paint.Style.STROKE);
        this.i.setStrokeWidth(this.g);
        this.i.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(e(i, this.k), e(i2, this.k));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i5 = (int) this.g;
        int paddingTop = getPaddingTop();
        this.r = Math.min(((i - paddingLeft) - paddingRight) - (i5 * 2), ((i2 - paddingTop) - getPaddingBottom()) - (((int) this.g) * 2)) / 2.0f;
        this.n.x = i / 2;
        this.n.y = i2 / 2;
        this.p.left = (this.n.x - this.r) - (this.g / 2.0f);
        this.p.top = (this.n.y - this.r) - (this.g / 2.0f);
        this.p.right = this.n.x + this.r + (this.g / 2.0f);
        this.p.bottom = this.n.y + this.r + (this.g / 2.0f);
        d();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        axc_(canvas);
    }

    private void axc_(Canvas canvas) {
        float degrees = (float) Math.toDegrees(((this.g / 2.0f) * Math.sqrt(2.0d)) / this.r);
        canvas.save();
        canvas.rotate(this.t - degrees, this.n.x, this.n.y);
        canvas.drawArc(this.p, degrees, this.s, false, this.i);
        canvas.drawArc(this.p, degrees, Math.min(this.q, 1.0f) * this.s, false, this.f);
        canvas.rotate(-this.t, this.n.x, this.n.y);
        canvas.restore();
    }

    private void d() {
        this.f.setShader(new SweepGradient(this.n.x, this.n.y, this.j, (float[]) null));
        this.i.setShader(new SweepGradient(this.n.x, this.n.y, this.h, (float[]) null));
    }

    public void setValue(int i, int i2, boolean z, ProgressUpdate progressUpdate) {
        this.o = progressUpdate;
        float f = i2;
        this.m = f;
        float f2 = i;
        this.d = f2;
        float f3 = z ? this.q : 0.0f;
        float f4 = i2 > 0 ? f2 / f : 1.0f;
        LogUtil.d(b, "setValue mMaxValue ", Float.valueOf(f), " inputValue ", Float.valueOf(this.d), " start ", Float.valueOf(f3), " end ", Float.valueOf(f4));
        e(f3, f4, 1000L);
    }

    private void e(float f, float f2, long j) {
        ValueAnimator valueAnimator = this.e;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.e.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.e = ofFloat;
        ofFloat.setDuration(j);
        this.e.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: fgg
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                CircleProgress.this.axf_(valueAnimator2);
            }
        });
        this.e.start();
    }

    public /* synthetic */ void axf_(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.q = floatValue;
        ProgressUpdate progressUpdate = this.o;
        if (progressUpdate != null) {
            float f = this.m;
            if (f > 0.0f) {
                progressUpdate.onUpdate(Math.round(floatValue * f), (int) this.m);
            } else {
                this.q = 0.0f;
                progressUpdate.onUpdate(Math.round(this.d), (int) this.m);
            }
        }
        this.x = this.q * this.m;
        invalidate();
    }

    private int c(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + ((f >= 0.0f ? 1 : -1) * 0.5f));
    }

    private int e(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            i2 = size;
        }
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }
}
