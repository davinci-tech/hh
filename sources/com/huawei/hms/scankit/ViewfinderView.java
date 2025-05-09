package com.huawei.hms.scankit;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.view.ViewCompat;
import com.huawei.health.R;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.scankit.p.u6;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes9.dex */
public final class ViewfinderView extends View {
    private int[] A;
    private float[] B;
    private Rect C;
    private boolean D;
    Point E;
    private boolean F;

    /* renamed from: a, reason: collision with root package name */
    private Paint f5696a;
    private TextPaint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private float h;
    private c i;
    private String j;
    private int k;
    private float l;
    public int m;
    public int n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private b t;
    private int u;
    private int v;
    private Rect w;
    private int x;
    private ValueAnimator y;
    Paint z;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ViewfinderView.this.m = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            ViewfinderView.this.invalidate();
        }
    }

    public enum b {
        NONE(0),
        LINE(1),
        GRID(2);


        /* renamed from: a, reason: collision with root package name */
        private int f5698a;

        b(int i) {
            this.f5698a = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static b b(int i) {
            for (b bVar : values()) {
                if (bVar.f5698a == i) {
                    return bVar;
                }
            }
            return LINE;
        }
    }

    public enum c {
        TOP(0),
        BOTTOM(1);


        /* renamed from: a, reason: collision with root package name */
        private int f5699a;

        c(int i) {
            this.f5699a = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static c b(int i) {
            for (c cVar : values()) {
                if (cVar.f5699a == i) {
                    return cVar;
                }
            }
            return TOP;
        }
    }

    public ViewfinderView(Context context) {
        this(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131101041_res_0x7f060571, R.attr._2131101042_res_0x7f060572, R.attr._2131101043_res_0x7f060573, R.attr._2131101044_res_0x7f060574, R.attr._2131101045_res_0x7f060575, R.attr._2131101046_res_0x7f060576, R.attr._2131101047_res_0x7f060577, R.attr._2131101048_res_0x7f060578, R.attr._2131101049_res_0x7f060579, R.attr._2131101050_res_0x7f06057a, R.attr._2131101051_res_0x7f06057b, R.attr._2131101052_res_0x7f06057c, R.attr._2131101053_res_0x7f06057d, R.attr._2131101054_res_0x7f06057e, R.attr._2131101055_res_0x7f06057f, R.attr._2131101056_res_0x7f060580, R.attr._2131101057_res_0x7f060581, R.attr._2131101058_res_0x7f060582, R.attr._2131101059_res_0x7f060583});
        this.c = obtainStyledAttributes.getColor(14, b(context, R.color._2131299034_res_0x7f090ada));
        this.d = obtainStyledAttributes.getColor(1, b(context, R.color._2131299032_res_0x7f090ad8));
        this.f = obtainStyledAttributes.getColor(0, b(context, R.color._2131299031_res_0x7f090ad7));
        this.e = obtainStyledAttributes.getColor(11, b(context, R.color._2131299033_res_0x7f090ad9));
        this.g = obtainStyledAttributes.getColor(15, b(context, R.color._2131299035_res_0x7f090adb));
        this.j = obtainStyledAttributes.getString(6);
        this.k = obtainStyledAttributes.getColor(7, b(context, R.color._2131299036_res_0x7f090adc));
        this.l = obtainStyledAttributes.getDimension(10, TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics()));
        this.h = obtainStyledAttributes.getDimension(9, TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.i = c.b(obtainStyledAttributes.getInt(8, 0));
        this.o = obtainStyledAttributes.getBoolean(16, false);
        this.r = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.s = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.t = b.b(obtainStyledAttributes.getInt(12, b.LINE.f5698a));
        this.u = obtainStyledAttributes.getInt(4, 20);
        this.v = (int) obtainStyledAttributes.getDimension(5, TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.F = obtainStyledAttributes.getBoolean(13, true);
        obtainStyledAttributes.recycle();
        this.f5696a = new Paint(1);
        this.b = new TextPaint(1);
        this.x = a(context, 136);
        this.q = getDisplayMetrics().heightPixels;
        this.p = getDisplayMetrics().widthPixels;
    }

    public static int b(Context context, int i) {
        try {
            return context.getColor(i);
        } catch (Resources.NotFoundException unused) {
            Log.e("ViewfinderView", "getColor: Resources.NotFoundException");
            return ViewCompat.MEASURED_SIZE_MASK;
        } catch (Exception unused2) {
            Log.e("ViewfinderView", "getColor: Exception");
            return ViewCompat.MEASURED_SIZE_MASK;
        }
    }

    private DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    public void a(u6 u6Var) {
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.y;
        if (valueAnimator != null) {
            valueAnimator.pause();
            this.y.removeAllListeners();
            this.y.cancel();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.F) {
            canvas.save();
            String str = Build.DEVICE;
            a(canvas, "HWTAH".equals(str) || str.equals("HWTAH-C"));
            canvas.restore();
        }
        a(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.p = i;
        this.q = i2;
        a();
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewfinderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = 0;
        this.n = 0;
        this.w = new Rect();
        this.z = new Paint();
        this.A = new int[]{Color.parseColor("#FFFFFFFF"), Color.parseColor("#72FFFFFF"), Color.parseColor("#58FFFFFF"), Color.parseColor("#40FFFFFF"), Color.parseColor("#28FFFFFF"), Color.parseColor("#13FFFFFF"), Color.parseColor("#00FFFFFF")};
        this.B = new float[]{0.0f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
        this.D = true;
        this.F = true;
        a(context, attributeSet);
    }

    private void a(Canvas canvas, boolean z) {
        this.f5696a.setStyle(Paint.Style.FILL);
        this.f5696a.setColor(this.e);
        if (!e.z && !z) {
            Rect rect = this.w;
            rect.left = 0;
            int i = this.m;
            rect.top = i;
            rect.bottom = i + this.x;
            rect.right = this.p;
        } else {
            Rect rect2 = this.w;
            int i2 = this.p / 2;
            rect2.left = i2 - 540;
            int i3 = this.m;
            rect2.top = i3;
            rect2.bottom = i3 + this.x;
            rect2.right = i2 + HwExerciseConstants.NINE_MINUTES_PACE;
        }
        float f = this.p / 2;
        float f2 = this.w.bottom + 500;
        this.f5696a.setShader(new RadialGradient(f, f2, 690, this.A, this.B, Shader.TileMode.CLAMP));
        this.f5696a.setStrokeWidth(10.0f);
        Rect rect3 = this.w;
        float f3 = rect3.left;
        float f4 = rect3.bottom;
        canvas.drawLine(f3, f4, rect3.right, f4, this.f5696a);
        canvas.clipRect(this.w);
        canvas.drawCircle(f, f2, r8 + 200, this.f5696a);
    }

    public void a() {
        if (e.z) {
            this.n = this.q;
        } else {
            this.n = this.q - a(getContext(), OldToNewMotionPath.SPORT_TYPE_AEROBICS);
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(0, this.n - this.x);
        this.y = ofInt;
        ofInt.setDuration(3000L);
        this.y.setInterpolator(new AccelerateDecelerateInterpolator());
        this.y.setRepeatMode(1);
        this.y.setRepeatCount(-1);
        this.y.addUpdateListener(new a());
        this.y.start();
    }

    public void a(Rect rect, boolean z, Point point) {
        this.D = z;
        this.E = point;
        if (this.C == null) {
            this.C = rect;
            invalidate();
        }
    }

    public static int a(Context context, int i) {
        return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void a(Canvas canvas) {
        Point point;
        int i;
        int i2;
        int i3;
        if (this.C == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (this.D) {
            Point point2 = this.E;
            point = new Point(point2.y, point2.x);
        } else {
            Point point3 = this.E;
            point = new Point(point3.x, point3.y);
        }
        int i4 = point.x;
        float f = width / i4;
        int i5 = point.y;
        float f2 = height / i5;
        int i6 = (int) (i5 * 0.1d);
        int i7 = (int) ((i4 * 0.15d) / 2.0d);
        RectF rectF = new RectF();
        if (this.D) {
            if (f > f2) {
                i2 = (int) (point.y * f);
                canvas.translate(0.0f, (height / 2) - (i2 / 2));
                i3 = i2;
                i = width;
            } else {
                i = (int) (point.x * f2);
                canvas.translate((width / 2) - (i / 2), 0.0f);
                i3 = height;
            }
        } else if (f > f2) {
            i2 = (int) (point.y * f);
            canvas.translate(0.0f, (height / 2) - (i2 / 2));
            i3 = i2;
            i = width;
        } else {
            i = (int) (point.x * f2);
            canvas.translate((width / 2) - (i / 2), 0.0f);
            i3 = height;
        }
        Rect rect = this.C;
        float f3 = rect.left + i7;
        float f4 = point.x;
        float f5 = rect.top + i6;
        float f6 = point.y;
        float f7 = f5 / f6;
        float f8 = (rect.bottom + i6) / f6;
        float f9 = i;
        float f10 = (f3 / f4) * f9;
        rectF.left = f10;
        float f11 = ((rect.right + i7) / f4) * f9;
        rectF.right = f11;
        float f12 = i3;
        float f13 = f7 * f12;
        rectF.top = f13;
        float f14 = f8 * f12;
        rectF.bottom = f14;
        float f15 = (f10 + f11) / 2.0f;
        float f16 = (f13 + f14) / 2.0f;
        this.z.setStyle(Paint.Style.FILL);
        this.z.setColor(-1);
        canvas.drawCircle(f15, f16, ((int) ((getDisplayMetrics().density * 24.0f) + 0.5d)) / 2, this.z);
        this.z.setColor(Color.parseColor("#007DFF"));
        canvas.drawCircle(f15, f16, ((int) ((getDisplayMetrics().density * 22.0f) + 0.5d)) / 2, this.z);
        if (this.D) {
            if (f > f2) {
                canvas.translate(0.0f, (i3 / 2) - (height / 2));
                return;
            } else {
                canvas.translate((i / 2) - (width / 2), 0.0f);
                return;
            }
        }
        if (f > f2) {
            canvas.translate(0.0f, (i3 / 2) - (height / 2));
        } else {
            canvas.translate((i / 2) - (width / 2), 0.0f);
        }
    }
}
