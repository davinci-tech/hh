package com.huawei.health.suggestion.ui.plan.view;

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
import com.huawei.health.suggestion.ui.plan.view.CircleProgress;
import health.compact.a.util.LogUtil;

/* loaded from: classes8.dex */
public class CircleProgress extends View {
    private static final String c = "CircleProgress";

    /* renamed from: a, reason: collision with root package name */
    private float f3281a;
    private boolean b;
    private ValueAnimator d;
    private long e;
    private Paint f;
    private float g;
    private int[] h;
    private int[] i;
    private Paint j;
    private Context k;
    private float l;
    private ProgressUpdate m;
    private Point n;
    private int o;
    private float p;
    private float q;
    private float r;
    private RectF s;
    private float t;
    private float y;

    public interface ProgressUpdate {
        void onUpdate(int i, int i2);
    }

    public CircleProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new int[]{-1, -1};
        this.h = new int[]{-1, -1};
        aIU_(context, attributeSet);
    }

    private void aIU_(Context context, AttributeSet attributeSet) {
        this.k = context;
        this.o = a(context, 0.0f);
        this.d = new ValueAnimator();
        this.s = new RectF();
        this.n = new Point();
        aIV_(attributeSet);
        b();
    }

    private void aIV_(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.k.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099729_res_0x7f060051, R.attr._2131099732_res_0x7f060054, R.attr._2131099734_res_0x7f060056, R.attr._2131099736_res_0x7f060058, R.attr._2131099777_res_0x7f060081, R.attr._2131101138_res_0x7f0605d2, R.attr._2131101179_res_0x7f0605fb});
        this.b = obtainStyledAttributes.getBoolean(1, true);
        this.g = obtainStyledAttributes.getDimension(3, 8.0f);
        this.r = obtainStyledAttributes.getFloat(5, 135.0f);
        this.q = obtainStyledAttributes.getFloat(6, 270.0f);
        this.e = obtainStyledAttributes.getInt(0, 1000);
        int resourceId = obtainStyledAttributes.getResourceId(2, 0);
        if (resourceId != 0) {
            try {
                int[] intArray = getResources().getIntArray(resourceId);
                if (intArray.length == 0) {
                    int color = getResources().getColor(resourceId);
                    this.i = new int[]{color, color};
                } else if (intArray.length == 1) {
                    this.i = new int[]{intArray[0], intArray[0]};
                } else {
                    this.i = intArray;
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.e(c, "the give resource not found.");
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
                LogUtil.e(c, "the give resource not found.");
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void b() {
        Paint paint = new Paint();
        this.f = paint;
        paint.setAntiAlias(this.b);
        this.f.setStyle(Paint.Style.STROKE);
        this.f.setStrokeWidth(this.g);
        this.f.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.j = paint2;
        paint2.setAntiAlias(this.b);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setStrokeWidth(this.g);
        this.j.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(d(i, this.o), d(i2, this.o));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i5 = (int) this.g;
        int paddingTop = getPaddingTop();
        this.t = Math.min(((i - paddingLeft) - paddingRight) - (i5 * 2), ((i2 - paddingTop) - getPaddingBottom()) - (((int) this.g) * 2)) / 2.0f;
        this.n.x = i / 2;
        this.n.y = i2 / 2;
        this.s.left = (this.n.x - this.t) - (this.g / 2.0f);
        this.s.top = (this.n.y - this.t) - (this.g / 2.0f);
        this.s.right = this.n.x + this.t + (this.g / 2.0f);
        this.s.bottom = this.n.y + this.t + (this.g / 2.0f);
        c();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        aIT_(canvas);
    }

    private void aIT_(Canvas canvas) {
        float degrees = (float) Math.toDegrees(((this.g / 2.0f) * Math.sqrt(2.0d)) / this.t);
        canvas.save();
        canvas.rotate(this.r - degrees, this.n.x, this.n.y);
        canvas.drawArc(this.s, degrees, this.q, false, this.j);
        canvas.drawArc(this.s, degrees, Math.min(this.p, 1.0f) * this.q, false, this.f);
        canvas.restore();
    }

    private void c() {
        this.f.setShader(new SweepGradient(this.n.x, this.n.y, this.i, (float[]) null));
        this.j.setShader(new SweepGradient(this.n.x, this.n.y, this.h, (float[]) null));
    }

    public void setValue(int i, int i2, boolean z) {
        float f = i2;
        this.l = f;
        float f2 = i;
        this.f3281a = f2;
        float f3 = z ? this.p : 0.0f;
        float f4 = i2 > 0 ? f2 / f : 1.0f;
        LogUtil.d(c, "setValueWithOutCallback mMaxValue ", Float.valueOf(f), " inputValue ", Float.valueOf(this.f3281a), " start ", Float.valueOf(f3), " end ", Float.valueOf(f4));
        d(f3, f4, 0L);
    }

    public void setValue(int i, int i2, boolean z, ProgressUpdate progressUpdate) {
        this.m = progressUpdate;
        float f = i2;
        this.l = f;
        float f2 = i;
        this.f3281a = f2;
        float f3 = z ? this.p : 0.0f;
        float f4 = i2 > 0 ? f2 / f : 1.0f;
        LogUtil.d(c, "setValue mMaxValue ", Float.valueOf(f), " inputValue ", Float.valueOf(this.f3281a), " start ", Float.valueOf(f3), " end ", Float.valueOf(f4));
        d(f3, f4, 1000L);
    }

    private void d(float f, float f2, long j) {
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.d.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.d = ofFloat;
        ofFloat.setDuration(j);
        this.d.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: fzs
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                CircleProgress.this.aIW_(valueAnimator2);
            }
        });
        this.d.start();
    }

    public /* synthetic */ void aIW_(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.p = floatValue;
        ProgressUpdate progressUpdate = this.m;
        if (progressUpdate != null) {
            float f = this.l;
            if (f > 0.0f) {
                progressUpdate.onUpdate(Math.round(floatValue * f), (int) this.l);
            } else {
                this.p = 0.0f;
                progressUpdate.onUpdate(Math.round(this.f3281a), (int) this.l);
            }
        }
        this.y = this.p * this.l;
        invalidate();
    }

    private int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + ((f >= 0.0f ? 1 : -1) * 0.5f));
    }

    private int d(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            i2 = size;
        }
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }
}
