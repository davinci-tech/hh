package com.huawei.uikit.hwprogressindicator.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.health.R;
import com.huawei.uikit.hwprogressindicator.graphics.drawable.HwLoadingDrawable;
import defpackage.slx;
import defpackage.smr;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwProgressIndicator extends View {
    private static final DecelerateInterpolator b = new DecelerateInterpolator(0.8f);

    /* renamed from: a, reason: collision with root package name */
    private HwLoadingDrawable f10687a;
    private ArgbEvaluator aa;
    private int ab;
    private int ac;
    private int ad;
    private boolean ae;
    private float af;
    private int ag;
    private Paint ah;
    private Paint ai;
    private int aj;
    private boolean ak;
    private boolean al;
    private slx am;
    private boolean an;
    private int c;
    private int d;
    private boolean e;
    private OnSmoothProgressDurationListener f;
    private boolean g;
    private float h;
    private long i;
    private ObjectAnimator j;
    private RectF k;
    private int l;
    private float m;
    private RectF n;
    private final Property<HwProgressIndicator, Float> o;
    private int[] p;
    private float[] q;
    private int r;
    private int s;
    private int t;
    private int u;
    private Paint v;
    private int w;
    private int x;
    private boolean y;
    private int z;

    public interface OnSmoothProgressDurationListener {
        long getAnimationDuration(float f);
    }

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwProgressIndicator.this.k();
        }
    }

    class d implements HwLoadingDrawable.OnLoadingListener {
        d() {
        }

        @Override // com.huawei.uikit.hwprogressindicator.graphics.drawable.HwLoadingDrawable.OnLoadingListener
        public void onLoadingFinish() {
            HwProgressIndicator.this.ak = true;
            if (HwProgressIndicator.this.am != null && HwProgressIndicator.this.a()) {
                HwProgressIndicator.this.am.b();
            }
            HwProgressIndicator.this.d();
        }

        @Override // com.huawei.uikit.hwprogressindicator.graphics.drawable.HwLoadingDrawable.OnLoadingListener
        public void onLoadingStart() {
            HwProgressIndicator.this.ak = false;
        }
    }

    public HwProgressIndicator(Context context) {
        this(context, null);
    }

    private void eev_(Resources resources) {
        int eel_ = eel_(resources);
        if (eel_ == 0) {
            return;
        }
        int eeq_ = (int) ((this.x * eeq_(resources)) / eel_);
        setPadding(eeq_, eeq_, eeq_, eeq_);
    }

    private void eex_(Resources resources) {
        eev_(resources);
        int height = getHeight();
        int width = getWidth();
        int min = Math.min((height - getPaddingBottom()) - getPaddingTop(), (width - getPaddingLeft()) - getPaddingRight());
        this.k.set((width - min) / 2, (height - min) / 2, r1 + min, r0 + min);
        this.v.setStrokeWidth(this.x);
        this.ai.setStrokeWidth(this.x);
        float f = this.x / 2.0f;
        HwLoadingDrawable hwLoadingDrawable = this.f10687a;
        if (hwLoadingDrawable != null) {
            hwLoadingDrawable.d(f);
        }
        slx slxVar = this.am;
        if (slxVar != null) {
            slxVar.c(this.x);
        }
        if (this.r != 0) {
            return;
        }
        eey_(resources);
        f();
    }

    private void eey_(Resources resources) {
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131364292_res_0x7f0a09c4);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen._2131364294_res_0x7f0a09c6);
        int i = this.x;
        if ((i != dimensionPixelSize || this.z != dimensionPixelSize2) && dimensionPixelSize != 0) {
            float f = dimensionPixelSize;
            this.z = (int) ((i * dimensionPixelSize2) / f);
            this.ab = (int) ((this.x * resources.getDimensionPixelSize(R.dimen._2131364293_res_0x7f0a09c5)) / f);
            this.ac = (int) ((this.x * resources.getDimensionPixelSize(R.dimen._2131364290_res_0x7f0a09c2)) / f);
            this.ad = (int) ((this.x * resources.getDimensionPixelSize(R.dimen._2131364291_res_0x7f0a09c3)) / f);
            return;
        }
        if (this.z == -1) {
            this.z = dimensionPixelSize2;
        }
        if (this.ab <= 0) {
            this.ab = resources.getDimensionPixelSize(R.dimen._2131364293_res_0x7f0a09c5);
        }
        if (this.ac == -1) {
            this.ac = resources.getDimensionPixelSize(R.dimen._2131364290_res_0x7f0a09c2);
        }
        if (this.ad == -1) {
            this.ad = resources.getDimensionPixelSize(R.dimen._2131364291_res_0x7f0a09c3);
        }
    }

    private void h() {
        slx slxVar = this.am;
        if (slxVar == null || !slxVar.a()) {
            return;
        }
        this.am.setLevel((int) (this.h * 10000.0f));
    }

    private void j() {
        r();
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.g) {
            this.al = false;
            n();
            this.g = false;
        }
    }

    private void l() {
        slx slxVar = this.am;
        if (slxVar == null || !slxVar.a()) {
            return;
        }
        this.am.j();
    }

    private void m() {
        HwLoadingDrawable hwLoadingDrawable = this.f10687a;
        if (hwLoadingDrawable == null || !hwLoadingDrawable.a()) {
            return;
        }
        this.f10687a.e();
    }

    private void n() {
        if (this.am == null) {
            return;
        }
        if (a()) {
            this.am.c();
        } else {
            this.am.e();
        }
    }

    private void o() {
        m();
        l();
    }

    private void p() {
        if (this.l == 0) {
            ObjectAnimator objectAnimator = this.j;
            if (objectAnimator != null && objectAnimator.isRunning()) {
                this.j.cancel();
            }
            this.h = 0.0f;
        }
    }

    private void q() {
        this.m = this.h * 360.0f;
    }

    private void r() {
        HwLoadingDrawable hwLoadingDrawable;
        if (this.l != 0) {
            m();
        } else {
            if (!c() || (hwLoadingDrawable = this.f10687a) == null) {
                return;
            }
            hwLoadingDrawable.c();
        }
    }

    private void s() {
        int b2 = b(this.m, 135.0f);
        int b3 = b(this.m, 225.0f);
        int[] iArr = this.p;
        if (iArr == null) {
            int i = this.w;
            this.p = new int[]{b2, b3, i, i};
        } else {
            iArr[0] = b2;
            iArr[1] = b3;
        }
        float f = ((this.m - 225.0f) / 360.0f) + 0.25f;
        float[] fArr = this.q;
        if (fArr == null) {
            this.q = new float[]{0.0f, 0.25f, f, 1.0f};
        } else {
            fArr[2] = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisualProgress(float f) {
        this.h = f;
        q();
        invalidate();
    }

    private void t() {
        slx slxVar = this.am;
        if (slxVar == null) {
            return;
        }
        if (this.l == 0) {
            slxVar.j();
        } else if (this.ak && a()) {
            this.am.b();
        }
    }

    public boolean a() {
        return this.al;
    }

    public boolean c() {
        return this.an;
    }

    public boolean e() {
        return this.e;
    }

    public int getBackGroundColor() {
        return this.ag;
    }

    public int[] getIndicatorColors() {
        return new int[]{this.u, this.w};
    }

    public int getProgress() {
        return this.l;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        super.invalidateDrawable(drawable);
        if (drawable == this.am || drawable == this.f10687a) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        j();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null) {
            return;
        }
        i();
        g();
        b();
        canvas.rotate(-90.0f, this.k.centerX(), this.k.centerY());
        eeu_(canvas);
        if (this.l <= 0 || !this.ak) {
            eew_(canvas);
        } else {
            eeo_(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setContentDescription(String.format(Locale.ROOT, "%d%%", Integer.valueOf(this.l)));
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return;
        }
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String format = String.format(Locale.ROOT, "%d%%", Integer.valueOf(this.l));
        accessibilityNodeInfo.setHintText(accessibilityNodeInfo.getContentDescription());
        accessibilityNodeInfo.setContentDescription(format);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(View.resolveSizeAndState(this.s, i, 0), View.resolveSizeAndState(this.t, i2, 0));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int eet_;
        Resources resources = getResources();
        if (resources == null || (eet_ = eet_(resources)) == 0) {
            return;
        }
        int eel_ = eel_(resources);
        if (this.y) {
            this.x = (int) ((eel_ * Math.min(i, i2)) / eet_);
            this.x = Math.min(this.x, (int) (resources.getDisplayMetrics().density * 32.0f));
        }
        eex_(resources);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            j();
        } else {
            o();
        }
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int i) {
        super.sendAccessibilityEvent(i);
    }

    public void setBackGroundColor(int i) {
        this.ag = i;
        Paint paint = this.ai;
        if (paint != null) {
            paint.setColor(i);
        }
        invalidate();
    }

    public void setBlurEnabled(boolean z) {
        if (this.r != 0) {
            Log.w("HwProgressIndicator", "setBlurEnabled: in consideration of the style, do not support to change.");
        } else {
            this.ae = z;
            invalidate();
        }
    }

    public void setFlickerAnimationEnabled(boolean z) {
        ObjectAnimator objectAnimator;
        if (this.al == z) {
            return;
        }
        if (this.l == 100) {
            this.al = z;
            return;
        }
        if (this.am == null) {
            this.al = z;
            invalidate();
            return;
        }
        if (z || (objectAnimator = this.j) == null || !objectAnimator.isRunning()) {
            this.al = z;
            n();
        } else {
            this.g = true;
        }
        invalidate();
    }

    public void setIndicatorColors(int[] iArr) {
        if (iArr == null || iArr.length != 2) {
            Log.e("HwProgressIndicator", "setIndicatorColors: input parameters is wrong.");
            return;
        }
        this.u = iArr[0];
        int i = iArr[1];
        this.w = i;
        int[] iArr2 = this.p;
        if (iArr2 != null && iArr2.length > 3) {
            iArr2[2] = i;
            iArr2[3] = i;
        }
        invalidate();
    }

    public void setProgress(int i) {
        if (this.l == i) {
            return;
        }
        this.l = Math.max(0, Math.min(100, i));
        r();
        t();
        p();
        if (this.ak) {
            d();
        }
        if (!e()) {
            this.h = this.l / 100.0f;
        }
        q();
        this.i = this.l == 100 ? 0L : System.currentTimeMillis();
        invalidate();
    }

    public void setSmoothProgressDurationListener(OnSmoothProgressDurationListener onSmoothProgressDurationListener) {
        this.f = onSmoothProgressDurationListener;
    }

    public void setSmoothProgressEnabled(boolean z) {
        if (this.e == z) {
            return;
        }
        if (!z) {
            this.h = this.l / 100.0f;
            q();
        }
        this.e = z;
        slx slxVar = this.am;
        if (slxVar != null) {
            slxVar.a(z);
        }
    }

    public void setWaitingAnimationEnabled(boolean z) {
        if (this.an == z) {
            return;
        }
        HwLoadingDrawable hwLoadingDrawable = this.f10687a;
        if (hwLoadingDrawable == null) {
            this.an = z;
            if (this.l <= 0) {
                invalidate();
                return;
            }
            return;
        }
        if (!z) {
            hwLoadingDrawable.e();
        } else if (this.l <= 0) {
            hwLoadingDrawable.c();
        }
        this.an = z;
        invalidate();
    }

    class c extends Property<HwProgressIndicator, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(HwProgressIndicator hwProgressIndicator, Float f) {
            if (hwProgressIndicator == null) {
                return;
            }
            hwProgressIndicator.setVisualProgress(f.floatValue());
            hwProgressIndicator.h = f.floatValue();
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Float get(HwProgressIndicator hwProgressIndicator) {
            return Float.valueOf(hwProgressIndicator.h);
        }
    }

    public HwProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100472_res_0x7f060338);
    }

    private void d(Resources resources) {
        int i = this.r;
        if (i == 0) {
            this.t = resources.getDimensionPixelSize(R.dimen._2131364295_res_0x7f0a09c7);
            this.s = resources.getDimensionPixelSize(R.dimen._2131364297_res_0x7f0a09c9);
        } else if (i == 1) {
            this.t = resources.getDimensionPixelSize(R.dimen._2131364299_res_0x7f0a09cb);
            this.s = resources.getDimensionPixelSize(R.dimen._2131364301_res_0x7f0a09cd);
        } else {
            this.t = resources.getDimensionPixelSize(R.dimen._2131364305_res_0x7f0a09d1);
            this.s = resources.getDimensionPixelSize(R.dimen._2131364307_res_0x7f0a09d3);
        }
    }

    private int eet_(Resources resources) {
        int i = this.r;
        return i != 1 ? i != 2 ? resources.getDimensionPixelSize(R.dimen._2131364297_res_0x7f0a09c9) : resources.getDimensionPixelSize(R.dimen._2131364307_res_0x7f0a09d3) : resources.getDimensionPixelSize(R.dimen._2131364301_res_0x7f0a09cd);
    }

    public HwProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.l = 0;
        this.k = new RectF();
        this.n = new RectF();
        this.r = 0;
        this.s = -1;
        this.t = -1;
        this.v = new Paint();
        this.aa = null;
        this.ae = false;
        this.ai = new Paint();
        this.am = null;
        this.ak = true;
        this.f10687a = null;
        this.h = 0.0f;
        this.g = false;
        this.i = 0L;
        this.o = new c(Float.class, "visual_progress");
        eem_(getContext(), attributeSet, i);
    }

    private int eeq_(Resources resources) {
        int i = this.r;
        if (i == 1) {
            return resources.getDimensionPixelSize(R.dimen._2131364300_res_0x7f0a09cc);
        }
        if (i != 2) {
            return resources.getDimensionPixelSize(R.dimen._2131364296_res_0x7f0a09c8);
        }
        return resources.getDimensionPixelSize(R.dimen._2131364306_res_0x7f0a09d2);
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwProgressIndicator);
    }

    private void eem_(Context context, AttributeSet attributeSet, int i) {
        Resources resources = getResources();
        if (resources == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100195_res_0x7f060223, R.attr._2131100196_res_0x7f060224, R.attr._2131100198_res_0x7f060226, R.attr._2131100204_res_0x7f06022c, R.attr._2131100206_res_0x7f06022e, R.attr._2131100207_res_0x7f06022f, R.attr._2131100208_res_0x7f060230, R.attr._2131100209_res_0x7f060231, R.attr._2131100210_res_0x7f060232, R.attr._2131100324_res_0x7f0602a4, R.attr._2131100325_res_0x7f0602a5, R.attr._2131100469_res_0x7f060335, R.attr._2131100470_res_0x7f060336, R.attr._2131100471_res_0x7f060337, R.attr._2131100473_res_0x7f060339, R.attr._2131100474_res_0x7f06033a, R.attr._2131100475_res_0x7f06033b, R.attr._2131100528_res_0x7f060370}, i, R.style.Widget_Emui_HwProgressIndicator);
        this.r = obtainStyledAttributes.getInt(17, 0);
        d(resources);
        a(obtainStyledAttributes, resources);
        ees_(obtainStyledAttributes, resources);
        eer_(obtainStyledAttributes);
        een_(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void f() {
        this.ah.setMaskFilter(null);
        int i = this.ab;
        if (i <= 0) {
            return;
        }
        try {
            this.ah.setMaskFilter(new BlurMaskFilter(i, BlurMaskFilter.Blur.NORMAL));
            this.ah.setStrokeWidth(this.z);
        } catch (IllegalArgumentException unused) {
            Log.w("HwProgressIndicator", "IllegalArgumentException");
        }
    }

    private void eeu_(Canvas canvas) {
        if (!this.ak) {
            canvas.drawArc(this.k, 0.0f, 360.0f, false, this.ai);
            return;
        }
        RectF rectF = this.k;
        float f = this.m;
        canvas.drawArc(rectF, f, 360.0f - f, false, this.ai);
    }

    private void ees_(TypedArray typedArray, Resources resources) {
        if (this.r != 0) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28) {
            setLayerType(1, null);
        }
        this.ae = typedArray.getBoolean(4, true);
        this.z = typedArray.getDimensionPixelSize(8, -1);
        this.ab = typedArray.getDimensionPixelSize(7, -1);
        this.ac = typedArray.getDimensionPixelSize(5, -1);
        this.ad = typedArray.getDimensionPixelSize(6, -1);
        this.af = typedArray.getFloat(3, R.dimen._2131364289_res_0x7f0a09c1);
        eey_(resources);
        Paint paint = new Paint();
        this.ah = paint;
        paint.setAntiAlias(true);
        this.ah.setMaskFilter(new BlurMaskFilter(this.ab, BlurMaskFilter.Blur.NORMAL));
        this.ah.setStrokeWidth(this.z);
        this.ah.setStrokeCap(Paint.Cap.ROUND);
        this.ah.setStyle(Paint.Style.STROKE);
        this.ah.setAlpha((int) (this.af * 255.0f));
    }

    private void g() {
        if (c() && this.f10687a == null) {
            HwLoadingDrawable hwLoadingDrawable = new HwLoadingDrawable(this.k, this.c, this.d, this.x / 2.0f);
            this.f10687a = hwLoadingDrawable;
            hwLoadingDrawable.setCallback(this);
            this.f10687a.e(new d());
            if (this.l <= 0) {
                this.f10687a.c();
            }
        }
    }

    private void eew_(Canvas canvas) {
        HwLoadingDrawable hwLoadingDrawable = this.f10687a;
        if (hwLoadingDrawable == null || this.ak) {
            return;
        }
        hwLoadingDrawable.draw(canvas);
    }

    private void d(Canvas canvas) {
        h();
        slx slxVar = this.am;
        if (slxVar != null) {
            slxVar.draw(canvas);
        }
    }

    private int eel_(Resources resources) {
        int i = this.r;
        if (i == 1) {
            return resources.getDimensionPixelSize(R.dimen._2131364298_res_0x7f0a09ca);
        }
        if (i != 2) {
            return resources.getDimensionPixelSize(R.dimen._2131364292_res_0x7f0a09c4);
        }
        return resources.getDimensionPixelSize(R.dimen._2131364304_res_0x7f0a09d0);
    }

    private void i() {
        int b2;
        float f;
        if (this.l <= 0) {
            return;
        }
        if (Float.compare(this.m, 225.0f) <= 0) {
            b2 = this.w;
            f = this.m / 360.0f;
        } else {
            b2 = b(this.m, 225.0f);
            f = 0.625f;
        }
        int i = this.u;
        if (Float.compare(this.m, 90.0f) < 0) {
            i = b(90.0f, 90.0f - this.m);
        }
        int[] iArr = {i, b2, i};
        float[] fArr = {0.0f, f, 1.0f};
        this.v.setShader(new SweepGradient(this.k.centerX(), this.k.centerY(), iArr, fArr));
        if (this.ae) {
            this.n.set(this.k);
            this.n.offset(-this.ad, this.ac);
            this.ah.setShader(new SweepGradient(this.n.centerX(), this.n.centerY(), iArr, fArr));
        }
    }

    private void a(TypedArray typedArray, Resources resources) {
        this.x = typedArray.getDimensionPixelSize(1, -1);
        this.u = typedArray.getColor(11, -7945729);
        this.w = typedArray.getColor(12, -14331913);
        this.y = typedArray.getBoolean(0, true);
        if (this.x == -1) {
            this.x = eel_(resources);
        }
        this.v.setAntiAlias(true);
        this.v.setStrokeWidth(this.x);
        this.v.setStrokeCap(Paint.Cap.ROUND);
        this.v.setStyle(Paint.Style.STROKE);
    }

    private void eer_(TypedArray typedArray) {
        this.ag = typedArray.getColor(2, AMapEngineUtils.HALF_MAX_P20_WIDTH);
        this.ai.setAntiAlias(true);
        this.ai.setStyle(Paint.Style.STROKE);
        this.ai.setStrokeWidth(this.x);
        this.ai.setStrokeCap(Paint.Cap.ROUND);
        this.ai.setColor(this.ag);
    }

    private void b() {
        if (a() && this.am == null) {
            slx slxVar = new slx(this.k, this.x, this.v);
            this.am = slxVar;
            slxVar.d(this.aj);
            this.am.a(this.e);
            this.am.setCallback(this);
            if (this.ak) {
                this.am.b();
            }
        }
    }

    private void een_(TypedArray typedArray) {
        this.al = typedArray.getBoolean(10, true);
        this.aj = typedArray.getColor(9, 872415231);
        this.an = typedArray.getBoolean(15, true);
        this.c = typedArray.getColor(14, 8831487);
        this.d = typedArray.getColor(16, -14331913);
        this.e = typedArray.getBoolean(13, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.l == 0 || !e()) {
            return;
        }
        float f = this.l / 100.0f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.o, f);
        this.j = ofFloat;
        ofFloat.setAutoCancel(true);
        this.j.setDuration(b(f));
        this.j.setInterpolator(b);
        this.j.addListener(new a());
        this.j.start();
    }

    private long b(float f) {
        OnSmoothProgressDurationListener onSmoothProgressDurationListener = this.f;
        if (onSmoothProgressDurationListener != null) {
            return onSmoothProgressDurationListener.getAnimationDuration(f);
        }
        long abs = Float.compare(f, this.h) != 0 ? (long) (Math.abs(f - this.h) * 2000.0f) : 0L;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.l == 100) {
            return Math.min(500L, abs);
        }
        long j = this.i;
        return j != 0 ? Math.min(5000L, Math.max(abs, currentTimeMillis - j)) : abs;
    }

    private void eeo_(Canvas canvas) {
        if (Float.compare(this.m, 225.0f) <= 0) {
            if (this.ae) {
                canvas.drawArc(this.n, 0.0f, this.m, false, this.ah);
            }
            canvas.drawArc(this.k, 0.0f, this.m, false, this.v);
            d(canvas);
            return;
        }
        s();
        if (this.ae) {
            eep_(this.n, this.ah, canvas, false);
        }
        eep_(this.k, this.v, canvas, true);
    }

    private void eep_(RectF rectF, Paint paint, Canvas canvas, boolean z) {
        canvas.drawArc(rectF, 0.0f, 225.0f, false, paint);
        slx slxVar = this.am;
        float d2 = slxVar != null ? slxVar.d() : 0.0f;
        if (z && Float.compare(d2, 90.0f) < 0) {
            d(canvas);
        }
        canvas.save();
        canvas.rotate(-225.0f, rectF.centerX(), rectF.centerY());
        paint.setShader(new SweepGradient(rectF.centerX(), rectF.centerY(), this.p, this.q));
        canvas.drawArc(rectF, 90.0f, this.m - 225.0f, false, paint);
        canvas.restore();
        if (!z || Float.compare(d2, 90.0f) < 0) {
            return;
        }
        d(canvas);
    }

    private int b(float f, float f2) {
        if (this.aa == null) {
            this.aa = new ArgbEvaluator();
        }
        if (Float.compare(f, 0.0f) == 0) {
            return this.u;
        }
        return ((Integer) this.aa.evaluate(f2 / f, Integer.valueOf(this.u), Integer.valueOf(this.w))).intValue();
    }
}
