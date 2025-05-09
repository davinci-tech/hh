package com.huawei.uikit.hwchart.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import defpackage.bno;
import defpackage.smr;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HwChart extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f10615a;
    private Paint aa;
    private int ab;
    private boolean ac;
    private RectF ad;
    private Paint ae;
    private float af;
    private float ag;
    private float ah;
    private float ai;
    private int b;
    private List<c> c;
    private int d;
    private float e;
    private bno f;
    private int g;
    private RectF h;
    private boolean i;
    private RectF j;
    private int k;
    private boolean l;
    private int m;
    private int n;
    private long o;
    private RectF p;
    private float q;
    private int[] r;
    private int[] s;
    private ArgbEvaluator t;
    private float u;
    private int v;
    private int w;
    private int x;
    private int y;
    private AnimationListener z;

    public interface AnimationListener {
        void onAnimationEnd();
    }

    class c {

        /* renamed from: a, reason: collision with root package name */
        private float f10616a;
        private int b;
        private float d;
        private int[] f;
        private RectF g;
        private float[] h;
        private int j;
        private float e = 0.0f;
        private Paint i = new Paint();

        c(float f, int i, int i2, RectF rectF, int i3) {
            this.d = f;
            this.b = i;
            this.j = i2;
            this.g = rectF;
            e(i3);
        }

        private int f() {
            return Float.compare(this.f10616a, 36.0f) <= 0 ? HwChart.this.b(45.0f, this.f10616a + 9.0f, this.b, this.j) : this.j;
        }

        private void g() {
            int f = f();
            int[] iArr = this.f;
            if (iArr == null || iArr.length != 3) {
                int i = this.b;
                this.f = new int[]{i, f, i};
            } else {
                iArr[1] = f;
            }
            float[] fArr = this.h;
            if (fArr == null || fArr.length != 3) {
                this.h = new float[]{0.0f, this.f10616a / 360.0f, 1.0f};
            } else {
                fArr[1] = (this.f10616a - HwChart.this.ai) / 360.0f;
            }
            j();
        }

        private void h() {
            int b = HwChart.this.b(this.f10616a, 225.0f, this.b, this.j);
            int[] iArr = this.f;
            if (iArr == null || iArr.length != 3) {
                int i = this.b;
                this.f = new int[]{i, b, i};
            } else {
                iArr[1] = b;
            }
            float[] fArr = this.h;
            if (fArr == null || fArr.length != 3) {
                this.h = new float[]{0.0f, 0.625f, 1.0f};
            } else {
                fArr[1] = 0.625f;
            }
            j();
        }

        private void i() {
            int b = HwChart.this.b(this.f10616a, 135.0f, this.b, this.j);
            int b2 = HwChart.this.b(this.f10616a, 225.0f, this.b, this.j);
            int[] iArr = this.f;
            if (iArr == null || iArr.length != 4) {
                int i = this.j;
                this.f = new int[]{b, b2, i, i};
            } else {
                iArr[0] = b;
                iArr[1] = b2;
            }
            float f = ((this.f10616a - 225.0f) / 360.0f) + 0.25f;
            float[] fArr = this.h;
            if (fArr == null || fArr.length != 4) {
                this.h = new float[]{0.0f, 0.25f, f, 1.0f};
            } else {
                fArr[2] = f;
            }
            j();
        }

        private void j() {
            RectF rectF = this.g;
            if (rectF == null) {
                return;
            }
            this.i.setShader(new SweepGradient(rectF.centerX(), this.g.centerY(), this.f, this.h));
        }

        public int a() {
            return this.b;
        }

        public float b() {
            return this.f10616a;
        }

        public int c() {
            return f();
        }

        public float d() {
            return this.e;
        }

        public float e() {
            return this.d;
        }

        public void b(float f) {
            this.f10616a = f;
        }

        public void d(float f) {
            this.e = f;
        }

        public void b(Canvas canvas) {
            if (canvas == null || this.g == null) {
                return;
            }
            h();
            canvas.save();
            canvas.rotate(HwChart.this.m, this.g.centerX(), this.g.centerY());
            canvas.drawArc(this.g, 0.0f, 225.0f, false, this.i);
            canvas.rotate(135.0f, this.g.centerX(), this.g.centerY());
            i();
            canvas.drawArc(this.g, 90.0f, this.f10616a - 225.0f, false, this.i);
            canvas.restore();
        }

        public void c(int i) {
            Paint paint = this.i;
            if (paint != null) {
                paint.setStrokeWidth(i);
            }
        }

        public void ebn_(Canvas canvas) {
            if (canvas == null || this.g == null) {
                return;
            }
            g();
            canvas.save();
            canvas.rotate(this.e, this.g.centerX(), this.g.centerY());
            canvas.drawArc(this.g, HwChart.this.ag, this.f10616a - HwChart.this.ag, false, this.i);
            canvas.restore();
        }

        private void e(int i) {
            this.i.setAntiAlias(true);
            this.i.setStrokeWidth(i);
            this.i.setStrokeCap(Paint.Cap.ROUND);
            this.i.setStyle(Paint.Style.STROKE);
        }
    }

    public HwChart(Context context) {
        this(context, null);
    }

    private void e(Resources resources) {
        int eaZ_ = eaZ_(resources);
        if (eaZ_ == 0) {
            return;
        }
        int ebd_ = (int) ((this.k * ebd_(resources)) / eaZ_);
        setPadding(ebd_, ebd_, ebd_, ebd_);
    }

    private void ebk_(Resources resources) {
        int i = this.b;
        if (i == 2) {
            this.g = resources.getDimensionPixelSize(R.dimen._2131364056_res_0x7f0a08d8);
            this.d = resources.getDimensionPixelSize(R.dimen._2131364058_res_0x7f0a08da);
        } else if (i == 1) {
            this.g = resources.getDimensionPixelSize(R.dimen._2131364052_res_0x7f0a08d4);
            this.d = resources.getDimensionPixelSize(R.dimen._2131364054_res_0x7f0a08d6);
        } else {
            this.g = resources.getDimensionPixelSize(R.dimen._2131364048_res_0x7f0a08d0);
            this.d = resources.getDimensionPixelSize(R.dimen._2131364050_res_0x7f0a08d2);
        }
    }

    private void ebm_(Resources resources) {
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131364045_res_0x7f0a08cd);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen._2131364047_res_0x7f0a08cf);
        int i = this.k;
        if ((i != dimensionPixelSize || this.w != dimensionPixelSize2) && dimensionPixelSize != 0) {
            float f = dimensionPixelSize;
            this.w = (int) ((i * dimensionPixelSize2) / f);
            this.y = (int) ((this.k * resources.getDimensionPixelSize(R.dimen._2131364046_res_0x7f0a08ce)) / f);
            this.v = (int) ((this.k * resources.getDimensionPixelSize(R.dimen._2131364043_res_0x7f0a08cb)) / f);
            this.x = (int) ((this.k * resources.getDimensionPixelSize(R.dimen._2131364044_res_0x7f0a08cc)) / f);
            return;
        }
        if (this.w == -1) {
            this.w = dimensionPixelSize2;
        }
        if (this.y <= 0) {
            this.y = resources.getDimensionPixelSize(R.dimen._2131364046_res_0x7f0a08ce);
        }
        if (this.v == -1) {
            this.v = resources.getDimensionPixelSize(R.dimen._2131364043_res_0x7f0a08cb);
        }
        if (this.x == -1) {
            this.x = resources.getDimensionPixelSize(R.dimen._2131364044_res_0x7f0a08cc);
        }
    }

    private void f() {
        float f = 0.0f;
        float f2 = 0.0f;
        for (c cVar : this.c) {
            f += cVar.e();
            float a2 = a(d(f) - f2);
            cVar.d(this.m + f2);
            cVar.b(a2);
            f2 += a2;
        }
    }

    private float getAnimationProgress() {
        return (AnimationUtils.currentAnimationTimeMillis() - this.o) / 1200.0f;
    }

    private int[] getDefaultBeginColors() {
        return this.s;
    }

    private int[] getDefaultEndColors() {
        return this.r;
    }

    private int getSectionIndex() {
        float f = 0.0f;
        int i = 0;
        while (Float.compare(f, 225.0f) < 0 && i < this.c.size()) {
            f += this.c.get(i).b();
            i++;
        }
        return Math.max(0, i - 1);
    }

    private int getSplitIndex() {
        for (int i = 0; i < this.c.size(); i++) {
            if (c(i)) {
                return i;
            }
        }
        return this.c.size() - 1;
    }

    public boolean b() {
        return this.i;
    }

    public boolean d() {
        return this.ac;
    }

    public AnimationListener getAnimationListener() {
        return this.z;
    }

    public int getBackGroundColor() {
        return this.ab;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        canvas.rotate(-90.0f, this.p.centerX(), this.p.centerY());
        float f = this.e;
        if (b() && a()) {
            f = d(this.e);
            f();
        } else {
            h();
        }
        ebb_(canvas, f);
        ebg_(canvas, f);
        ebj_(canvas, f);
        if (b()) {
            if (a()) {
                invalidate();
                return;
            }
            AnimationListener animationListener = this.z;
            if (animationListener != null) {
                animationListener.onAnimationEnd();
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(View.resolveSizeAndState(this.d, i, 0), View.resolveSizeAndState(this.g, i2, 0));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int ebi_;
        Resources resources = getResources();
        if (resources == null || (ebi_ = ebi_(resources)) == 0) {
            return;
        }
        int eaZ_ = eaZ_(resources);
        if (this.l) {
            this.k = (int) ((eaZ_ * Math.min(i, i2)) / ebi_);
            this.k = Math.min(this.k, (int) (resources.getDisplayMetrics().density * 32.0f));
        }
        ebl_(resources);
    }

    public void setAnimationEnabled(boolean z) {
        this.i = z;
        if (z) {
            g();
        }
        invalidate();
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.z = animationListener;
    }

    public void setBackGroundColor(int i) {
        this.ab = i;
        Paint paint = this.ae;
        if (paint != null) {
            paint.setColor(i);
        }
        invalidate();
    }

    public void setBlurEnabled(boolean z) {
        if (this.b != 0) {
            Log.w("HwChart", "setBlurEnabled: blur is supported only in large mode.");
        } else {
            this.ac = z;
            invalidate();
        }
    }

    public void setChartData(long j, int[] iArr) {
        setChartData(j, iArr, getDefaultBeginColors(), getDefaultEndColors());
    }

    public HwChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100253_res_0x7f06025d);
    }

    private int ebi_(Resources resources) {
        int i = this.b;
        return i != 1 ? i != 2 ? resources.getDimensionPixelSize(R.dimen._2131364050_res_0x7f0a08d2) : resources.getDimensionPixelSize(R.dimen._2131364058_res_0x7f0a08da) : resources.getDimensionPixelSize(R.dimen._2131364054_res_0x7f0a08d6);
    }

    public void setChartData(long j, int[] iArr, int[] iArr2, int[] iArr3) {
        if (iArr == null || iArr2 == null || iArr3 == null) {
            Log.e("HwChart", "setChartData: data or color is null.");
            return;
        }
        if (iArr.length == 0) {
            Log.w("HwChart", "setChartData: data is empty.");
            return;
        }
        if (iArr2.length != iArr3.length || iArr2.length == 0) {
            iArr2 = getDefaultBeginColors();
            iArr3 = getDefaultEndColors();
            Log.w("HwChart", "setChartData: number of colors is invalid.");
        }
        a(j, iArr, iArr2, iArr3);
        if (b()) {
            g();
        }
        invalidate();
    }

    public HwChart(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
        this.f10615a = 360.0f;
        this.e = 0.0f;
        this.c = new ArrayList();
        this.d = -1;
        this.g = -1;
        this.h = new RectF();
        this.j = new RectF();
        this.f = null;
        this.m = 0;
        this.n = 360;
        this.q = 0.0f;
        this.p = new RectF();
        this.t = null;
        this.ac = false;
        this.ad = new RectF();
        this.ae = new Paint();
        a(super.getContext(), attributeSet, i);
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwChart);
    }

    private void ebe_(TypedArray typedArray) {
        this.s = new int[]{typedArray.getColor(10, ContextCompat.getColor(getContext(), R.color._2131298288_res_0x7f0907f0)), typedArray.getColor(11, ContextCompat.getColor(getContext(), R.color._2131298290_res_0x7f0907f2)), typedArray.getColor(12, ContextCompat.getColor(getContext(), R.color._2131298292_res_0x7f0907f4)), typedArray.getColor(13, ContextCompat.getColor(getContext(), R.color._2131298294_res_0x7f0907f6)), typedArray.getColor(14, ContextCompat.getColor(getContext(), R.color._2131298296_res_0x7f0907f8)), typedArray.getColor(15, ContextCompat.getColor(getContext(), R.color._2131298298_res_0x7f0907fa)), typedArray.getColor(16, ContextCompat.getColor(getContext(), R.color._2131298300_res_0x7f0907fc)), typedArray.getColor(17, ContextCompat.getColor(getContext(), R.color._2131298302_res_0x7f0907fe)), typedArray.getColor(18, ContextCompat.getColor(getContext(), R.color._2131298304_res_0x7f090800))};
        this.r = new int[]{typedArray.getColor(20, ContextCompat.getColor(getContext(), R.color._2131298306_res_0x7f090802)), typedArray.getColor(21, ContextCompat.getColor(getContext(), R.color._2131298308_res_0x7f090804)), typedArray.getColor(22, ContextCompat.getColor(getContext(), R.color._2131298310_res_0x7f090806)), typedArray.getColor(23, ContextCompat.getColor(getContext(), R.color._2131298312_res_0x7f090808)), typedArray.getColor(24, ContextCompat.getColor(getContext(), R.color._2131298314_res_0x7f09080a)), typedArray.getColor(25, ContextCompat.getColor(getContext(), R.color._2131298316_res_0x7f09080c)), typedArray.getColor(26, ContextCompat.getColor(getContext(), R.color._2131298318_res_0x7f09080e)), typedArray.getColor(27, ContextCompat.getColor(getContext(), R.color._2131298320_res_0x7f090810)), typedArray.getColor(28, ContextCompat.getColor(getContext(), R.color._2131298322_res_0x7f090812))};
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        Resources resources = getResources();
        if (resources == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28) {
            setLayerType(1, null);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100195_res_0x7f060223, R.attr._2131100196_res_0x7f060224, R.attr._2131100198_res_0x7f060226, R.attr._2131100204_res_0x7f06022c, R.attr._2131100207_res_0x7f06022f, R.attr._2131100208_res_0x7f060230, R.attr._2131100209_res_0x7f060231, R.attr._2131100210_res_0x7f060232, R.attr._2131100232_res_0x7f060248, R.attr._2131100233_res_0x7f060249, R.attr._2131100234_res_0x7f06024a, R.attr._2131100235_res_0x7f06024b, R.attr._2131100236_res_0x7f06024c, R.attr._2131100237_res_0x7f06024d, R.attr._2131100238_res_0x7f06024e, R.attr._2131100239_res_0x7f06024f, R.attr._2131100240_res_0x7f060250, R.attr._2131100241_res_0x7f060251, R.attr._2131100242_res_0x7f060252, R.attr._2131100243_res_0x7f060253, R.attr._2131100244_res_0x7f060254, R.attr._2131100245_res_0x7f060255, R.attr._2131100246_res_0x7f060256, R.attr._2131100247_res_0x7f060257, R.attr._2131100248_res_0x7f060258, R.attr._2131100249_res_0x7f060259, R.attr._2131100250_res_0x7f06025a, R.attr._2131100251_res_0x7f06025b, R.attr._2131100252_res_0x7f06025c, R.attr._2131100528_res_0x7f060370}, i, R.style.Widget_Emui_HwChart);
        this.b = obtainStyledAttributes.getInt(29, 0);
        this.m = obtainStyledAttributes.getInt(9, 0);
        this.n = obtainStyledAttributes.getInt(19, 360);
        this.m = Math.min(360, Math.max(0, this.m));
        int min = Math.min(360, Math.max(0, this.n));
        this.n = min;
        this.f10615a = this.m < min ? min - r7 : 360 - (r7 - min);
        this.i = obtainStyledAttributes.getBoolean(8, true);
        ebe_(obtainStyledAttributes);
        ebk_(resources);
        a(obtainStyledAttributes, resources);
        ebf_(obtainStyledAttributes, resources);
        a(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void h() {
        float f = 0.0f;
        for (c cVar : this.c) {
            float e = cVar.e();
            cVar.d(this.m + f);
            cVar.b(e);
            f += e;
        }
    }

    private void c() {
        int height = getHeight();
        int width = getWidth();
        int min = Math.min((height - getPaddingBottom()) - getPaddingTop(), (width - getPaddingLeft()) - getPaddingRight());
        int i = (width - min) / 2;
        int i2 = (height - min) / 2;
        if (min % 2 != 0) {
            min++;
        }
        this.p.set(i, i2, i + min, i2 + min);
        this.h.set(this.p);
        RectF rectF = this.h;
        float f = -this.k;
        rectF.inset(f, f);
        this.q = this.p.width() != 0.0f ? (this.k * 360) / (this.p.width() * 3.1415927f) : 0.0f;
    }

    private void ebl_(Resources resources) {
        e(resources);
        c();
        j();
        this.ae.setStrokeWidth(this.k);
        if (this.b != 0) {
            return;
        }
        ebm_(resources);
        e();
    }

    private void g() {
        if (this.f == null) {
            this.f = new bno(110.0f, 17.0f);
        }
        this.o = AnimationUtils.currentAnimationTimeMillis();
    }

    private boolean b(int i, int i2) {
        return i > i2 || i < 0 || i2 >= this.c.size();
    }

    private int e(int i, int i2) {
        if (b(i, i2)) {
            return 0;
        }
        float e = this.c.get(i).e();
        int i3 = i;
        while (c(e) && i3 < i2) {
            i3++;
            e = this.c.get(i3).e();
        }
        return (i3 - i) + 1;
    }

    private float e(int i) {
        float f = 0.0f;
        for (int i2 = 0; i2 <= i; i2++) {
            f += this.c.get(i2).b();
        }
        return f;
    }

    private boolean c(float f) {
        return Float.compare(f, 5.0f) <= 0;
    }

    private float[] a(int i, int i2) {
        int i3 = 0;
        if (b(i, i2)) {
            return new float[0];
        }
        int i4 = ((i2 - i) + 1) * 2;
        float[] fArr = new float[i4 + 1];
        float f = 0.0f;
        while (i3 < i4 - 1 && i <= i2) {
            float e = this.c.get(i).e();
            float b = this.c.get(i).b() / 360.0f;
            while (c(e) && i < i2) {
                i++;
                b += this.c.get(i).b() / 360.0f;
                e = this.c.get(i).e();
            }
            float min = Math.min(0.4f * b, 0.04f);
            int i5 = i3 + 1;
            fArr[i3] = f;
            i3 += 2;
            f += b;
            fArr[i5] = f - min;
            i++;
        }
        fArr[i3] = 1.0f;
        return Arrays.copyOf(fArr, i3 + 1);
    }

    private void j() {
        Iterator<c> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().c(this.k);
        }
    }

    private void a(TypedArray typedArray, Resources resources) {
        this.k = typedArray.getDimensionPixelSize(1, -1);
        this.l = typedArray.getBoolean(0, true);
        if (this.k == -1) {
            this.k = eaZ_(resources);
        }
    }

    private void a(TypedArray typedArray) {
        int color = typedArray.getColor(2, -1);
        this.ab = color;
        if (color == -1) {
            this.ab = ContextCompat.getColor(getContext(), R.color._2131298286_res_0x7f0907ee);
        }
        this.ae.setAntiAlias(true);
        this.ae.setStyle(Paint.Style.STROKE);
        this.ae.setStrokeWidth(this.k);
        this.ae.setStrokeCap(Paint.Cap.ROUND);
        this.ae.setColor(this.ab);
    }

    private void ebj_(Canvas canvas, float f) {
        List<c> list = this.c;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.c.size();
        if (size == 1 && Float.compare(this.c.get(0).b(), 225.0f) > 0) {
            this.c.get(0).b(canvas);
            return;
        }
        if (Float.compare(f, 330.0f) >= 0) {
            eba_(canvas);
            return;
        }
        d(0.0f, 0.0f, 0.0f);
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                this.c.get(size).ebn_(canvas);
            }
        }
    }

    private int eaZ_(Resources resources) {
        int i = this.b;
        if (i == 1) {
            return resources.getDimensionPixelSize(R.dimen._2131364051_res_0x7f0a08d3);
        }
        if (i != 2) {
            return resources.getDimensionPixelSize(R.dimen._2131364045_res_0x7f0a08cd);
        }
        return resources.getDimensionPixelSize(R.dimen._2131364055_res_0x7f0a08d7);
    }

    private void a(long j, int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = iArr2;
        long d = d(iArr);
        long max = Math.max(d, j);
        if (max <= 0) {
            Log.w("HwChart", "onDataChanged: chartTotalData is less than or equals to 0. " + max);
            return;
        }
        float f = max;
        this.e = (this.f10615a * d) / f;
        this.ah = 0.0f;
        this.c.clear();
        float f2 = 0.0f;
        int i = 0;
        while (i < iArr.length) {
            int i2 = iArr[i];
            if (i2 <= 0) {
                Log.w("HwChart", "onDataChanged: data " + i + " is invalid. " + iArr[i]);
            } else {
                float a2 = i == iArr.length + (-1) ? a(this.e - f2) : a((this.f10615a * i2) / f);
                c cVar = new c(a2, iArr4[i % iArr4.length], iArr3[i % iArr3.length], this.p, this.k);
                f2 += a2;
                if (i < iArr.length - 1) {
                    this.ah += a2;
                }
                this.c.add(cVar);
            }
            i++;
            iArr4 = iArr2;
        }
    }

    private float d(float f) {
        if (this.f == null) {
            return f;
        }
        return this.f.getInterpolation(getAnimationProgress()) * f;
    }

    private boolean a() {
        return Float.compare(getAnimationProgress(), 1.0f) <= 0;
    }

    private void ebf_(TypedArray typedArray, Resources resources) {
        if (this.b != 0) {
            return;
        }
        this.ac = true;
        this.w = typedArray.getDimensionPixelSize(7, -1);
        this.y = typedArray.getDimensionPixelSize(6, -1);
        this.v = typedArray.getDimensionPixelSize(4, -1);
        this.x = typedArray.getDimensionPixelSize(5, -1);
        this.u = typedArray.getFloat(3, R.dimen._2131364042_res_0x7f0a08ca);
        ebm_(resources);
        Paint paint = new Paint();
        this.aa = paint;
        paint.setAntiAlias(true);
        this.aa.setMaskFilter(new BlurMaskFilter(this.y, BlurMaskFilter.Blur.NORMAL));
        this.aa.setStrokeWidth(this.w);
        this.aa.setStrokeCap(Paint.Cap.ROUND);
        this.aa.setStyle(Paint.Style.STROKE);
        this.aa.setAlpha((int) (this.u * 255.0f));
    }

    private long d(int[] iArr) {
        long j = 0;
        for (int i : iArr) {
            if (i > 0) {
                j += i;
            }
        }
        return j;
    }

    private void ebb_(Canvas canvas, float f) {
        if (Float.compare(f, this.f10615a) == 0) {
            return;
        }
        canvas.save();
        canvas.rotate(this.m, this.p.centerX(), this.p.centerY());
        canvas.drawArc(this.p, f, this.f10615a - f, false, this.ae);
        canvas.restore();
    }

    private float a(int i) {
        float e = e(i - 1);
        float e2 = e(i);
        if (Float.compare(225.0f - e, this.q) <= 0) {
            return e + (this.c.get(i).b() / 2.0f);
        }
        if (Float.compare(e2 - 225.0f, this.q) <= 0) {
            return e2 - (this.c.get(i).b() / 2.0f);
        }
        return 225.0f;
    }

    private void ebc_(Canvas canvas, int i, float f) {
        c cVar = this.c.get(i);
        int[] c2 = c(0, i);
        if (c2.length <= 2) {
            return;
        }
        float e = f - e(i - 1);
        c2[c2.length - 2] = b(cVar.b(), e, cVar.a(), cVar.c());
        float[] a2 = a(0, i);
        if (a2.length <= 2) {
            return;
        }
        a2[a2.length - 2] = (e / 360.0f) + a2[a2.length - 3];
        b(c2, a2);
        canvas.save();
        canvas.rotate(this.m, this.ad.centerX(), this.ad.centerY());
        Path path = new Path();
        path.moveTo(this.j.centerX(), this.j.centerY());
        RectF rectF = this.j;
        float f2 = this.q;
        path.arcTo(rectF, -f2, f2 + f);
        path.close();
        canvas.clipPath(path);
        canvas.drawArc(this.ad, 0.0f, f, false, this.aa);
        canvas.restore();
    }

    private int ebd_(Resources resources) {
        int i = this.b;
        if (i == 1) {
            return resources.getDimensionPixelSize(R.dimen._2131364053_res_0x7f0a08d5);
        }
        if (i != 2) {
            return resources.getDimensionPixelSize(R.dimen._2131364049_res_0x7f0a08d1);
        }
        return resources.getDimensionPixelSize(R.dimen._2131364057_res_0x7f0a08d9);
    }

    private float a(float f) {
        try {
            return Float.parseFloat(new DecimalFormat("#.00").format(f));
        } catch (NumberFormatException unused) {
            Log.e("HwChart", "formatAngle fail, angle: " + f);
            return f;
        }
    }

    private void e() {
        this.aa.setMaskFilter(null);
        int i = this.y;
        if (i <= 0) {
            return;
        }
        try {
            this.aa.setMaskFilter(new BlurMaskFilter(i, BlurMaskFilter.Blur.NORMAL));
            this.aa.setStrokeWidth(this.w);
        } catch (IllegalArgumentException unused) {
            Log.w("HwChart", "IllegalArgumentException");
        }
    }

    private void ebg_(Canvas canvas, float f) {
        List<c> list;
        if (!d() || (list = this.c) == null || list.isEmpty()) {
            return;
        }
        this.ad.set(this.p);
        this.ad.offset(-this.x, this.v);
        this.j.set(this.ad);
        RectF rectF = this.j;
        float f2 = -this.w;
        rectF.inset(f2, f2);
        if (Float.compare(f, 225.0f) <= 0) {
            b(c(0, this.c.size() - 1), a(0, this.c.size() - 1));
            canvas.save();
            canvas.rotate(this.m, this.ad.centerX(), this.ad.centerY());
            canvas.drawArc(this.ad, 0.0f, f, false, this.aa);
            canvas.restore();
            return;
        }
        int sectionIndex = getSectionIndex();
        float a2 = a(sectionIndex);
        ebh_(canvas, sectionIndex, a2, f);
        ebc_(canvas, sectionIndex, a2);
    }

    private void b(int[] iArr, float[] fArr) {
        if (iArr != null && fArr != null && iArr.length == fArr.length) {
            this.aa.setShader(new SweepGradient(this.ad.centerX(), this.ad.centerY(), iArr, fArr));
        } else {
            Log.e("HwChart", "updateBlurPaint: length of colors and positions is not match.");
        }
    }

    private void a(int i, int i2, int i3, int[] iArr, int i4) {
        if (!b(i, i2) && iArr != null && i3 >= 0 && i3 < iArr.length) {
            c cVar = this.c.get(i);
            int[] d = d(i, i2);
            if (d.length != 2) {
                d = new int[]{cVar.a(), cVar.c()};
            }
            if (i == 0) {
                iArr[i3] = cVar.a();
                int i5 = i + i4;
                iArr[i3 + 1] = i5 == this.c.size() ? this.c.get(i5 - 1).c() : d[1];
            } else {
                iArr[i3] = d[0];
                int i6 = (i4 + i) - 1;
                if (i6 < this.c.size()) {
                    i = i6;
                }
                iArr[i3 + 1] = this.c.get(i).c();
            }
        }
    }

    private void ebh_(Canvas canvas, int i, float f, float f2) {
        c cVar = this.c.get(i);
        float e = f - e(i - 1);
        int b = b(cVar.b(), e, cVar.a(), cVar.c());
        int[] c2 = c(i, this.c.size() - 1);
        if (c2.length == 0) {
            return;
        }
        c2[0] = b;
        b(c2, e(i, this.c.size() - 1, (cVar.b() - e) / 360.0f));
        canvas.save();
        canvas.rotate(this.m + f, this.ad.centerX(), this.ad.centerY());
        Path path = new Path();
        path.moveTo(this.j.centerX(), this.j.centerY());
        float f3 = f2 - f;
        path.arcTo(this.j, 0.0f, this.q + f3);
        path.close();
        canvas.clipPath(path);
        canvas.drawArc(this.ad, 0.0f, f3, false, this.aa);
        canvas.restore();
    }

    private int[] d(int i, int i2) {
        if (b(i, i2)) {
            return new int[0];
        }
        c cVar = this.c.get(i);
        float e = cVar.e();
        float b = cVar.b();
        int a2 = cVar.a();
        int a3 = cVar.a();
        while (c(e) && i < i2) {
            i++;
            c cVar2 = this.c.get(i);
            a2 = b(cVar2.b() + b, b, a2, cVar2.c());
            a3 = b(cVar2.b() + b, cVar2.b(), a3, cVar2.c());
            b += cVar2.b();
            e = cVar2.e();
        }
        return new int[]{a2, a3};
    }

    private int[] c(int i, int i2) {
        int i3 = 0;
        if (b(i, i2)) {
            return new int[0];
        }
        int i4 = ((i2 - i) + 1) * 2;
        int[] iArr = new int[i4 + 1];
        int i5 = i;
        while (i5 <= i2 && i3 < i4 - 1) {
            c cVar = this.c.get(i5);
            int e = e(i5, i2);
            if (e > 1) {
                a(i5, i2, i3, iArr, e);
                i3 += 2;
                i5 += e;
            } else {
                int i6 = i3 + 1;
                iArr[i3] = cVar.a();
                i3 += 2;
                iArr[i6] = cVar.c();
                i5++;
            }
        }
        iArr[i3] = this.c.get(i).a();
        return Arrays.copyOf(iArr, i3 + 1);
    }

    private float[] e(int i, int i2, float f) {
        if (b(i, i2)) {
            return new float[0];
        }
        int i3 = 2;
        int i4 = ((i2 - i) + 1) * 2;
        float[] fArr = new float[i4 + 1];
        fArr[0] = 0.0f;
        fArr[1] = f - Math.min(f * 0.4f, 0.04f);
        int i5 = i + 1;
        while (i3 < i4 - 1 && i5 <= i2) {
            float e = this.c.get(i5).e();
            float b = this.c.get(i5).b() / 360.0f;
            while (c(e) && i5 < i2) {
                i5++;
                b += this.c.get(i5).b() / 360.0f;
                e = this.c.get(i5).e();
            }
            float min = Math.min(b * 0.4f, 0.04f);
            int i6 = i3 + 1;
            fArr[i3] = f;
            i3 += 2;
            f += b;
            fArr[i6] = f - min;
            i5++;
        }
        fArr[i3] = 1.0f;
        return Arrays.copyOf(fArr, i3 + 1);
    }

    private boolean c(int i) {
        boolean z;
        if (i < 0 || i >= this.c.size()) {
            return false;
        }
        c cVar = this.c.get(i);
        boolean z2 = Float.compare(cVar.b(), this.q) > 0;
        if (b()) {
            if (Float.compare(e(i - 1) + (cVar.b() / 2.0f), (d(this.e) - 360.0f) + this.q) <= 0) {
                z = false;
                return z2 && z;
            }
        }
        z = true;
        if (z2) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(float f, float f2, int i, int i2) {
        if (this.t == null) {
            this.t = new ArgbEvaluator();
        }
        return Float.compare(f, 0.0f) <= 0 ? i : ((Integer) this.t.evaluate(f2 / f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    private void d(float f, float f2, float f3) {
        this.af = f;
        this.ai = f2;
        this.ag = f3;
    }

    private void eba_(Canvas canvas) {
        int splitIndex = getSplitIndex();
        float d = this.c.get(splitIndex).d() + (this.c.get(splitIndex).e() / 2.0f);
        d(0.0f, 0.0f, 0.0f);
        a(canvas, splitIndex, d, -180.0f);
        for (int i = splitIndex - 1; i >= 0; i--) {
            this.c.get(i).ebn_(canvas);
        }
        for (int size = this.c.size() - 1; size > splitIndex; size--) {
            this.c.get(size).ebn_(canvas);
        }
        if (splitIndex < 6 && this.ah < 18.1f) {
            d(8.0f, 45.0f, 35.0f);
        } else {
            d(0.0f, 0.0f, 0.0f);
        }
        a(canvas, splitIndex, d, 180.0f);
    }

    private void a(Canvas canvas, int i, float f, float f2) {
        Path path = new Path();
        canvas.save();
        path.moveTo(this.h.centerX(), this.h.centerY());
        path.arcTo(this.h, f, f2 + this.af);
        path.close();
        canvas.clipPath(path);
        this.c.get(i).ebn_(canvas);
        canvas.restore();
    }
}
