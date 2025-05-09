package com.huawei.uikit.hwprogressbar.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import com.huawei.health.R;
import com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;
import defpackage.skf;
import defpackage.skg;
import defpackage.slc;
import defpackage.slw;
import defpackage.sly;
import defpackage.slz;
import defpackage.smr;
import defpackage.sms;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwProgressBar extends ProgressBar {
    private static Method d;

    /* renamed from: a, reason: collision with root package name */
    protected int f10679a;
    private int b;
    private boolean f;
    private slz g;
    private sly h;
    private int i;
    private int j;
    private ObjectAnimator k;
    private float l;
    private Field m;
    private HwGravitationalLoadingDrawable.a n;
    private Method o;
    private int p;
    private long q;
    private OnVisualProgressChangedListener r;
    private final Property<HwProgressBar, Float> s;
    private OnSetProgressAnimationDurationListener t;
    private int u;
    private int x;
    private static final int e = a();
    private static final DecelerateInterpolator c = new DecelerateInterpolator(0.8f);

    public interface OnSetProgressAnimationDurationListener {
        long getAnimationDuration(float f);
    }

    public interface OnVisualProgressChangedListener {
        void onVisualProgressChanged(HwProgressBar hwProgressBar, float f);
    }

    public HwProgressBar(Context context) {
        this(context, null);
    }

    protected static int a() {
        if (d == null) {
            d = slc.b("getInt", new Class[]{String.class, Integer.TYPE}, "android.os.SystemProperties");
        }
        Method method = d;
        if (method == null) {
            return 200;
        }
        Object c2 = slc.c((Object) null, method, new Object[]{"hw_sc.platform.ux_level", 200});
        if (c2 instanceof Integer) {
            return ((Integer) c2).intValue();
        }
        return 200;
    }

    public static HwProgressBar b(Context context) {
        Object e2 = sms.e(context, sms.e(context, (Class<?>) HwProgressBar.class, sms.c(context, 15, 1)), (Class<?>) HwProgressBar.class);
        if (e2 instanceof HwProgressBar) {
            return (HwProgressBar) e2;
        }
        return null;
    }

    private void edR_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100281_res_0x7f060279, R.attr._2131100402_res_0x7f0602f2}, i, 2131952544);
        int integer = obtainStyledAttributes.getInteger(1, 600);
        int integer2 = obtainStyledAttributes.getInteger(0, 50);
        obtainStyledAttributes.recycle();
        setIndeterminateDrawable(new skf(getResources(), integer2, integer));
    }

    private void f() {
        Drawable progressLayerDrawable;
        if (this.g == null || (progressLayerDrawable = getProgressLayerDrawable()) == null) {
            return;
        }
        Rect bounds = progressLayerDrawable.getBounds();
        int edN_ = edN_(progressLayerDrawable);
        int i = ((bounds.top + bounds.bottom) - edN_) / 2;
        this.g.setBounds(bounds.left, i, bounds.right, edN_ + i);
    }

    private int getBuildSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    private Drawable getProgressLayerDrawable() {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null || !(progressDrawable instanceof LayerDrawable)) {
            return null;
        }
        return ((LayerDrawable) progressDrawable).findDrawableByLayerId(android.R.id.progress);
    }

    private int getVisualProgress() {
        Drawable progressLayerDrawable = getProgressLayerDrawable();
        if (progressLayerDrawable != null) {
            return progressLayerDrawable.getLevel();
        }
        return 0;
    }

    private boolean h() {
        return getLayoutDirection() == 1;
    }

    private void j() {
        int max = getMax() - (getBuildSdkVersion() >= 26 ? getMin() : 0);
        float progress = max > 0 ? (getProgress() - r0) / max : 0.0f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.s, progress);
        this.k = ofFloat;
        ofFloat.setAutoCancel(true);
        this.k.setDuration(a(progress));
        this.k.setInterpolator(c);
        this.k.start();
    }

    private void l() {
        slz slzVar;
        if (c() && (slzVar = this.g) != null) {
            slzVar.d();
            setFlickerLevel(getVisualProgress());
        }
    }

    private void m() {
        slz slzVar;
        if (c() && (slzVar = this.g) != null) {
            slzVar.c();
        }
    }

    private void setFlickerLevel(int i) {
        slz slzVar = this.g;
        if (slzVar == null || !slzVar.a()) {
            return;
        }
        this.g.setLevel(i);
    }

    public boolean c() {
        return this.f;
    }

    protected void d() {
        if (e >= 200) {
            setIndeterminateDrawable(skg.dYN_(this.f10679a, this.n, getResources().getDisplayMetrics(), this.p));
        } else {
            setIndeterminateDrawable(new slw(getResources(), getMinimumWidth() > getMinimumHeight() ? getMinimumHeight() : getMinimumWidth(), this.f10679a));
        }
        setInterpolator(new HwCubicBezierInterpolator(0.38f, 0.1f, 0.0f, 0.93f));
    }

    protected void edS_(Context context, AttributeSet attributeSet, int i) {
        synchronized (this) {
            if (edP_(context, attributeSet, i)) {
                edR_(context, attributeSet, i);
                return;
            }
            b(context, attributeSet, i);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100323_res_0x7f0602a3, R.attr._2131100324_res_0x7f0602a4, R.attr._2131100325_res_0x7f0602a5, R.attr._2131100401_res_0x7f0602f1, R.attr._2131100450_res_0x7f060322, R.attr._2131100451_res_0x7f060323, R.attr._2131100452_res_0x7f060324, R.attr._2131100454_res_0x7f060326}, i, 2131952544);
            try {
                try {
                    this.u = obtainStyledAttributes.getInt(5, 0);
                    this.f10679a = obtainStyledAttributes.getColor(0, -11711155);
                    this.j = obtainStyledAttributes.getColor(4, getResources().getColor(R.color._2131297401_res_0x7f090479));
                    this.x = obtainStyledAttributes.getDimensionPixelSize(6, 0);
                    this.b = obtainStyledAttributes.getDimensionPixelSize(7, 0);
                    this.i = obtainStyledAttributes.getColor(1, 1728053247);
                    this.f = obtainStyledAttributes.getBoolean(2, false);
                    d();
                    g();
                    e();
                    if (this.m == null && this.o == null) {
                        i();
                    }
                    obtainStyledAttributes.recycle();
                } catch (Resources.NotFoundException unused) {
                    Log.e("HwProgressBar", "Resource not found in initialize.");
                }
                if (getImportantForAccessibility() == 0) {
                    setImportantForAccessibility(1);
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public CharSequence getAccessibilityClassName() {
        return !isIndeterminate() ? ProgressBar.class.getName() : "";
    }

    public int getFlickerColor() {
        return this.i;
    }

    @Override // android.widget.ProgressBar, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
        if (drawable == this.g) {
            invalidate();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas canvas) {
        synchronized (this) {
            if (canvas == null) {
                return;
            }
            if (this.h != null) {
                b();
            } else {
                super.onDraw(canvas);
                edO_(canvas);
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (isIndeterminate()) {
                return;
            }
            accessibilityNodeInfo.setFocusable(true);
            accessibilityNodeInfo.setContentDescription(String.format(Locale.ROOT, "%d%%", Integer.valueOf(getProgress())));
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        f();
    }

    public void setFillColor(int i) {
        synchronized (this) {
            this.f10679a = i;
            sly slyVar = this.h;
            if (slyVar != null) {
                slyVar.b(i);
            }
        }
    }

    public void setFlickerColor(int i) {
        this.i = i;
        slz slzVar = this.g;
        if (slzVar != null) {
            slzVar.d(i);
        }
    }

    public void setFlickerEnable(boolean z) {
        setFlickerEnable(z, false);
    }

    public void setOnSetProgressAnimationDurationListener(OnSetProgressAnimationDurationListener onSetProgressAnimationDurationListener) {
        this.t = onSetProgressAnimationDurationListener;
    }

    public void setOnVisualProgressChangedListener(OnVisualProgressChangedListener onVisualProgressChangedListener) {
        this.r = onVisualProgressChangedListener;
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i) {
        synchronized (this) {
            super.setProgress(i);
            a(i, false);
        }
    }

    public void setRingTrackColor(int i) {
        synchronized (this) {
            this.j = i;
            sly slyVar = this.h;
            if (slyVar != null) {
                slyVar.d(i);
            }
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.g;
    }

    class c extends Property<HwProgressBar, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void set(HwProgressBar hwProgressBar, Float f) {
            if (hwProgressBar == null) {
                return;
            }
            hwProgressBar.b(android.R.id.progress, f.floatValue());
            hwProgressBar.l = f.floatValue();
        }

        @Override // android.util.Property
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Float get(HwProgressBar hwProgressBar) {
            return Float.valueOf(hwProgressBar.l);
        }
    }

    public HwProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100453_res_0x7f060325);
    }

    private void b(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100435_res_0x7f060313, R.attr._2131100436_res_0x7f060314, R.attr._2131100437_res_0x7f060315, R.attr._2131100438_res_0x7f060316, R.attr._2131100439_res_0x7f060317, R.attr._2131100440_res_0x7f060318, R.attr._2131100441_res_0x7f060319, R.attr._2131100442_res_0x7f06031a, R.attr._2131100443_res_0x7f06031b, R.attr._2131100444_res_0x7f06031c, R.attr._2131100445_res_0x7f06031d, R.attr._2131100446_res_0x7f06031e, R.attr._2131100447_res_0x7f06031f, R.attr._2131100448_res_0x7f060320, R.attr._2131100449_res_0x7f060321}, i, 2131952544);
        this.p = obtainStyledAttributes.getInteger(8, 1200);
        this.n = obtainStyledAttributes.getBoolean(9, false) ? HwGravitationalLoadingDrawable.a.dYp_(context, attributeSet, i, 2131952544) : HwGravitationalLoadingDrawable.a.dYo_(context, attributeSet, i, 2131952544);
        obtainStyledAttributes.recycle();
    }

    public void setFlickerEnable(boolean z, boolean z2) {
        if (this.f == z) {
            return;
        }
        this.f = z;
        slz slzVar = this.g;
        if (slzVar == null) {
            return;
        }
        if (z) {
            l();
        } else if (z2) {
            slzVar.b();
        } else {
            slzVar.c();
        }
    }

    public HwProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
        this.f = false;
        this.q = 0L;
        this.s = new c(Float.class, "visual_progress");
        edQ_(super.getContext(), attributeSet, i);
    }

    private void a(int i, boolean z) {
        int max;
        if (this.h != null) {
            b();
        }
        int min = getBuildSdkVersion() >= 26 ? getMin() : 0;
        if (i > min && i < getMax()) {
            l();
        } else {
            m();
        }
        if (z || !c() || (max = getMax() - min) == 0) {
            return;
        }
        setFlickerLevel((int) (((i - min) / max) * 10000.0f));
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i, boolean z) {
        super.setProgress(i, z);
        if (z) {
            j();
        }
        a(i, z);
    }

    private void g() {
        int i = this.u;
        if (i == 1 || i == 2) {
            int max = getMax();
            if (max == 0) {
                Log.e("HwProgressBar", "The max is 0 in initRingDrawable.");
                return;
            }
            sly slyVar = new sly();
            this.h = slyVar;
            slyVar.e(this.u);
            this.h.b(this.f10679a);
            this.h.d(this.j);
            this.h.a(this.x);
            this.h.c(this.b);
            this.h.c(getProgress() / max);
            setBackground(this.h);
        }
    }

    private void i() {
        this.o = null;
        this.m = null;
    }

    private void e() {
        if (e < 200) {
            return;
        }
        slz slzVar = new slz();
        this.g = slzVar;
        slzVar.d(this.i);
        this.g.setCallback(this);
        f();
        if (getProgress() <= (getBuildSdkVersion() >= 26 ? getMin() : 0) || getProgress() >= getMax() || !c()) {
            return;
        }
        this.g.setLevel(getVisualProgress());
        this.g.d();
    }

    private void edQ_(Context context, AttributeSet attributeSet, int i) {
        edS_(context, attributeSet, i);
    }

    private long a(float f) {
        OnSetProgressAnimationDurationListener onSetProgressAnimationDurationListener = this.t;
        if (onSetProgressAnimationDurationListener != null) {
            return onSetProgressAnimationDurationListener.getAnimationDuration(f);
        }
        long abs = Float.compare(f, this.l) != 0 ? (long) Math.abs((f - this.l) * 2000.0f) : 0L;
        long currentTimeMillis = System.currentTimeMillis();
        if (Float.compare(f, 1.0f) == 0) {
            abs = Math.min(300L, abs);
        } else if (this.q != 0 && Float.compare(f, 0.0f) > 0) {
            abs = Math.min(Math.max(abs, currentTimeMillis - this.q), 5000L);
        }
        this.q = currentTimeMillis;
        return abs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, float f) {
        this.l = f;
        Drawable progressDrawable = getProgressDrawable();
        if ((progressDrawable instanceof LayerDrawable) && (progressDrawable = ((LayerDrawable) progressDrawable).findDrawableByLayerId(i)) == null) {
            progressDrawable = getProgressDrawable();
        }
        int i2 = (int) (10000.0f * f);
        setFlickerLevel(i2);
        if (progressDrawable != null) {
            progressDrawable.setLevel(i2);
        } else {
            invalidate();
        }
        OnVisualProgressChangedListener onVisualProgressChangedListener = this.r;
        if (onVisualProgressChangedListener != null) {
            onVisualProgressChangedListener.onVisualProgressChanged(this, f);
        }
    }

    private void edO_(Canvas canvas) {
        if ((c() || this.g != null) && !isIndeterminate()) {
            if (this.g == null) {
                e();
            }
            int save = canvas.save();
            if (h()) {
                canvas.translate(getWidth() - getPaddingRight(), getPaddingTop());
                canvas.scale(-1.0f, 1.0f);
            } else {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            slz slzVar = this.g;
            if (slzVar != null) {
                slzVar.draw(canvas);
            }
            canvas.restoreToCount(save);
        }
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, 2131952151);
    }

    private boolean edP_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100323_res_0x7f0602a3, R.attr._2131100324_res_0x7f0602a4, R.attr._2131100325_res_0x7f0602a5, R.attr._2131100401_res_0x7f0602f1, R.attr._2131100450_res_0x7f060322, R.attr._2131100451_res_0x7f060323, R.attr._2131100452_res_0x7f060324, R.attr._2131100454_res_0x7f060326}, i, 2131952544);
        boolean z = obtainStyledAttributes.getBoolean(3, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    private void b() {
        int max = getMax();
        if (max == 0) {
            Log.e("HwProgressBar", "The max is 0 in setProgress.");
        } else {
            this.h.c(getProgress() / max);
        }
    }

    private int edN_(Drawable drawable) {
        if (drawable == null) {
            return 0;
        }
        Rect bounds = drawable.getBounds();
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            return 0;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bounds.right, bounds.bottom, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int level = drawable.getLevel();
        drawable.setLevel(10000);
        drawable.draw(canvas);
        drawable.setLevel(level);
        int i = 0;
        for (int i2 = 0; i2 < createBitmap.getHeight(); i2++) {
            if (createBitmap.getPixel(bounds.right / 2, i2) != 0) {
                i++;
            }
        }
        return i;
    }
}
