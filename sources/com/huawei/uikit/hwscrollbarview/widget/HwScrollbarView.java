package com.huawei.uikit.hwscrollbarview.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Interpolator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.slb;
import defpackage.smq;
import defpackage.smr;
import defpackage.sms;
import defpackage.smu;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwScrollbarView extends View {
    protected static final float HALF_FACTOR = 0.5f;
    protected static final float REVERSE_FACTOR = -1.0f;
    protected static final String TAG = "HwScrollbarView";
    protected static final int THUMB_TYPE_ARC = 1;
    protected static final int THUMB_TYPE_LINE = 0;

    /* renamed from: a, reason: collision with root package name */
    private static final int f10735a = 15;
    private static final int b = -1;
    private static final int c = 16777215;
    private static final int d = 1750;
    private static final int e = 1750;
    private static final int f = 250;
    private static final int g = 50;
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 48;
    private static final int l = 16;
    private static final int m = 255;
    private static final int n = 192;
    private static final int o = 48;
    private static final float p = 35.0f;
    private static final float q = 110.0f;
    private static final int r = 0;
    private static final HwScrollBind s = new smq();
    private static Method t;
    private static Method u;
    private static Method v;
    private int A;
    private float B;
    private boolean C;
    private int D;
    private View E;
    private WeakReference<ViewTreeObserver> F;
    private OnFastScrollListener G;
    private boolean H;
    private boolean I;
    private boolean J;
    private HwOverScrollProxy K;
    private boolean L;
    private int M;
    private int N;
    private int O;
    private a P;
    private View.OnTouchListener Q;
    private ViewTreeObserver.OnGlobalLayoutListener R;
    protected int mMinThumbLength;
    protected float mStartAngle;
    protected float mSweepAngle;
    protected int mThumbAlpha;
    protected Drawable mThumbDrawable;
    protected int mThumbLength;
    protected int mThumbOffset;
    protected Rect mThumbRect;
    protected int mThumbTint;
    protected Rect mThumbTouchHotRect;
    protected int mThumbTouchHotWidth;
    protected int mThumbType;
    protected int mThumbWidth;
    protected Drawable mTrackDrawable;
    protected int mTrackLength;
    protected Rect mTrackRect;
    protected int mTrackTint;
    protected int mTrackWidth;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface OnFastScrollListener {
        void onFastScrollChanged(int i, int i2, float f);
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public interface OnTouchOffsetListener {
        void onTouchOffset(int i, int i2, boolean z);
    }

    static class a implements Runnable {
        private float[] b;
        private final Interpolator c;
        private HwScrollbarView d;
        private int i;
        private long j;
        private static final float[] e = {255.0f};

        /* renamed from: a, reason: collision with root package name */
        private static final float[] f10736a = {0.0f};

        private a() {
            this.b = new float[1];
            this.c = new Interpolator(1, 2);
            this.i = 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            if (currentAnimationTimeMillis >= this.j) {
                int i = (int) currentAnimationTimeMillis;
                Interpolator interpolator = this.c;
                interpolator.setKeyFrame(0, i, e);
                interpolator.setKeyFrame(1, i + this.d.w, f10736a);
                this.i = 2;
                this.d.invalidate();
            }
        }

        /* synthetic */ a(d dVar) {
            this();
        }
    }

    class c implements ViewTreeObserver.OnGlobalLayoutListener {
        c() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int scrollableViewVerticalScrollRange = HwScrollbarView.this.getScrollableViewVerticalScrollRange();
            int scrollableViewVerticalScrollExtent = HwScrollbarView.this.getScrollableViewVerticalScrollExtent();
            HwScrollbarView.this.J = scrollableViewVerticalScrollRange > scrollableViewVerticalScrollExtent;
        }
    }

    class d implements View.OnTouchListener {
        d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (HwScrollbarView.this.l()) {
                return true;
            }
            HwScrollbarView.this.a(motionEvent);
            return false;
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwScrollbarView.this.w();
        }
    }

    public HwScrollbarView(Context context) {
        this(context, null);
    }

    private void d() {
        int i2 = this.O;
        if (i2 > 0) {
            d(i2);
        } else {
            w();
        }
    }

    private void e() {
        if (k()) {
            setThumbShow(false);
            invalidate();
        }
    }

    private void f() {
        HwOverScrollProxy hwOverScrollProxy = this.K;
        if (hwOverScrollProxy == null) {
            return;
        }
        if (hwOverScrollProxy.isOverScroll()) {
            if (this.L) {
                return;
            }
            m();
        } else if (this.L) {
            n();
        }
    }

    private void g() {
        u();
        invalidate();
    }

    public static HwScrollBind getHwScrollBindImpl() {
        return s;
    }

    private float getScrollProgress() {
        return (this.mThumbOffset * 1.0f) / (this.mTrackLength - this.mThumbLength);
    }

    private boolean h() {
        String language = Locale.getDefault().getLanguage();
        boolean z = true;
        boolean z2 = language.contains("ar") || language.contains("fa") || language.contains("iw");
        if (!language.contains("ug") && !language.contains(Constants.URDU_LANG)) {
            z = false;
        }
        return z2 | z;
    }

    private boolean i() {
        return this.D != 0;
    }

    public static HwScrollbarView instantiate(Context context) {
        Object e2 = sms.e(context, sms.e(context, (Class<?>) HwScrollbarView.class, sms.c(context, 15, 1)), (Class<?>) HwScrollbarView.class);
        if (e2 instanceof HwScrollbarView) {
            return (HwScrollbarView) e2;
        }
        return null;
    }

    private boolean j() {
        a aVar = this.P;
        return aVar != null && aVar.i == 0;
    }

    private boolean k() {
        return this.H;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        return this.x != 0;
    }

    private void m() {
        this.L = true;
        this.N = this.mThumbRect.height();
    }

    private void n() {
        q();
    }

    private void o() {
        a(1750);
    }

    private void p() {
        ViewTreeObserver viewTreeObserver;
        WeakReference<ViewTreeObserver> weakReference = this.F;
        if (weakReference == null || this.R == null || (viewTreeObserver = weakReference.get()) == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnGlobalLayoutListener(this.R);
    }

    private void q() {
        this.L = false;
    }

    private void r() {
        removeCallbacks(this.P);
        a aVar = this.P;
        if (aVar != null) {
            aVar.i = 1;
        }
        if (!k()) {
            setThumbShow(true);
        }
        g();
    }

    private void s() {
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mThumbDrawable;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    private void setThumbShow(boolean z) {
        this.H = z;
    }

    private void t() {
        updateTrackRect();
        u();
    }

    private void u() {
        if (this.E == null) {
            return;
        }
        a(getScrollableViewVerticalScrollRange(), getScrollableViewVerticalScrollExtent(), getScrollableViewVerticalScrollOffset());
    }

    private void v() {
        int width = getWidth() - getPaddingRight();
        int i2 = width - this.mThumbWidth;
        int paddingTop = getPaddingTop() + this.mThumbOffset;
        int i3 = this.mThumbLength;
        if (isScrollbarLayoutRtl()) {
            i2 = getPaddingLeft();
            width = this.mThumbWidth + i2;
        }
        this.mThumbRect.set(i2, paddingTop, width, i3 + paddingTop);
        updateThumbTouchHotRect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (isHapticFeedbackEnabled()) {
            slb.ebC_(this, 9, 0);
        }
    }

    protected void adjustOverScrollThumbParameters() {
        int overScrollOffset;
        if (isOverScrolling() && (overScrollOffset = this.K.getOverScrollOffset()) != 0) {
            int i2 = this.M;
            int abs = this.N - Math.abs(overScrollOffset);
            this.mThumbLength = abs;
            if (abs < i2) {
                this.mThumbLength = i2;
            }
            if (overScrollOffset > 0) {
                this.mThumbOffset = this.mTrackLength - this.mThumbLength;
            } else {
                this.mThumbOffset = 0;
            }
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        s();
    }

    public int getFadeTime() {
        return this.w;
    }

    public HwOverScrollProxy getHwOverScrollProxy(HwOverScrollProxy hwOverScrollProxy) {
        return this.K;
    }

    public View getScrollableView() {
        return this.E;
    }

    int getScrollableViewVerticalScrollExtent() {
        Method method;
        if (this.E != null && (method = u) != null) {
            try {
                method.setAccessible(true);
                Object invoke = u.invoke(this.E, new Object[0]);
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
            } catch (IllegalAccessException unused) {
                Log.w(TAG, "IllegalAccessException computeVerticalScrollExtent");
            } catch (InvocationTargetException unused2) {
                Log.w(TAG, "InvocationTargetException computeVerticalScrollExtent");
            }
        }
        return -1;
    }

    int getScrollableViewVerticalScrollOffset() {
        Method method;
        if (this.E != null && (method = v) != null) {
            try {
                method.setAccessible(true);
                Object invoke = v.invoke(this.E, new Object[0]);
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
            } catch (IllegalAccessException unused) {
                Log.w(TAG, "IllegalAccessException computeVerticalScrollOffset");
            } catch (InvocationTargetException unused2) {
                Log.w(TAG, "InvocationTargetException computeVerticalScrollOffset");
            }
        }
        return -1;
    }

    int getScrollableViewVerticalScrollRange() {
        Method method;
        if (this.E != null && (method = t) != null) {
            try {
                method.setAccessible(true);
                Object invoke = t.invoke(this.E, new Object[0]);
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
            } catch (IllegalAccessException unused) {
                Log.w(TAG, "IllegalAccessException computeVerticalScrollRange");
            } catch (InvocationTargetException unused2) {
                Log.w(TAG, "InvocationTargetException computeVerticalScrollRange");
            }
        }
        return -1;
    }

    public float getStartAngle() {
        return this.mStartAngle;
    }

    public float getSweepAngle() {
        return this.mSweepAngle;
    }

    protected Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public int getThumbTint() {
        return this.mThumbTint;
    }

    protected boolean isOverScrolling() {
        return this.L;
    }

    protected boolean isScrollbarLayoutRtl() {
        return getLayoutDirection() == 1 || h();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        a(1750);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.J) {
            b(canvas);
            a(canvas);
        }
    }

    protected void onDrawArcThumb(Canvas canvas) {
    }

    protected void onDrawLineThumb(Canvas canvas) {
        Drawable drawable = this.mThumbDrawable;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(this.mThumbRect);
        drawable.mutate().setAlpha(this.mThumbAlpha);
        drawable.draw(canvas);
    }

    protected void onDrawThumb(Canvas canvas) {
        if (this.mThumbType == 1) {
            onDrawArcThumb(canvas);
        } else {
            onDrawLineThumb(canvas);
        }
    }

    protected void onDrawTrack(Canvas canvas) {
        Drawable drawable = this.mTrackDrawable;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(this.mTrackRect);
        drawable.draw(canvas);
    }

    @Override // android.view.View
    public void onHoverChanged(boolean z) {
        if (z) {
            r();
        } else {
            o();
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (z) {
            t();
        }
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            mode = 0;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, mode), i3);
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        b(i2);
        super.onRtlPropertiesChanged(i2);
    }

    public void onScrollChanged() {
        if (getVisibility() != 0 || l()) {
            return;
        }
        if (!this.I) {
            this.I = c();
        }
        if (!this.I) {
            e();
            return;
        }
        f();
        r();
        o();
    }

    public void onScrollableViewTouchEvent(View view, MotionEvent motionEvent) {
        if (this.E == view && !l()) {
            a(motionEvent);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0029, code lost:
    
        if (r7 != 3) goto L36;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            boolean r1 = r6.j()
            if (r1 != 0) goto L81
            boolean r1 = r6.i()
            if (r1 == 0) goto L12
            goto L81
        L12:
            float r1 = r7.getX()
            int r1 = (int) r1
            float r2 = r7.getY()
            int r2 = (int) r2
            int r7 = r7.getAction()
            r3 = 1
            if (r7 == 0) goto L66
            if (r7 == r3) goto L5a
            r1 = 2
            if (r7 == r1) goto L2c
            r1 = 3
            if (r7 == r1) goto L5a
            goto L7c
        L2c:
            int r7 = r6.y
            int r7 = r2 - r7
            int r4 = r6.x
            if (r4 != r3) goto L50
            int r4 = java.lang.Math.abs(r7)
            int r5 = r6.A
            if (r4 < r5) goto L50
            r6.x = r1
            android.view.ViewParent r4 = r6.getParent()
            if (r4 == 0) goto L47
            r4.requestDisallowInterceptTouchEvent(r3)
        L47:
            if (r7 <= 0) goto L4d
            int r4 = r6.A
            int r4 = -r4
            goto L4f
        L4d:
            int r4 = r6.A
        L4f:
            int r7 = r7 + r4
        L50:
            int r4 = r6.x
            if (r4 != r1) goto L7c
            r6.y = r2
            r6.c(r7)
            goto L7c
        L5a:
            r6.setPressed(r0)
            r6.x = r0
            r6.g()
            r6.o()
            goto L7c
        L66:
            boolean r7 = r6.a(r1, r2)
            if (r7 == 0) goto L7c
            r6.x = r3
            r6.y = r2
            r6.q()
            r6.setPressed(r3)
            r6.r()
            r6.d()
        L7c:
            int r7 = r6.x
            if (r7 == 0) goto L81
            r0 = r3
        L81:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setFadeTime(int i2) {
        this.w = i2;
    }

    public void setFastScrollable(boolean z) {
        this.C = z;
    }

    public void setHwOverScrollProxy(HwOverScrollProxy hwOverScrollProxy) {
        this.K = hwOverScrollProxy;
    }

    public void setOnFastScrollListener(OnFastScrollListener onFastScrollListener) {
        this.G = onFastScrollListener;
    }

    @Deprecated
    public void setOnTouchOffsetListener(OnTouchOffsetListener onTouchOffsetListener) {
    }

    public void setScrollableView(View view, boolean z) {
        if (view == null) {
            Log.w(TAG, "setScrollableView: view is null");
            return;
        }
        if (this.E != null) {
            return;
        }
        this.E = view;
        if (z) {
            view.setOnTouchListener(this.Q);
        }
        view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);
        if (this.E.isAttachedToWindow()) {
            a();
        }
    }

    protected void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mThumbDrawable = drawable;
        if (drawable != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            this.mThumbDrawable = wrap;
            int i2 = this.mThumbTint;
            if (i2 != 16777215) {
                DrawableCompat.setTint(wrap, i2);
            }
            this.mThumbDrawable.setCallback(this);
        }
    }

    public void setThumbTint(int i2) {
        this.mThumbTint = i2;
    }

    protected void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mTrackDrawable = drawable;
        if (drawable != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            this.mTrackDrawable = wrap;
            int i2 = this.mTrackTint;
            if (i2 != 16777215) {
                DrawableCompat.setTint(wrap, i2);
            }
            this.mTrackDrawable.setCallback(this);
        }
    }

    protected void updateThumbTouchHotRect() {
        Rect rect = this.mThumbRect;
        int i2 = rect.right;
        int i3 = i2 - this.mThumbTouchHotWidth;
        if (isScrollbarLayoutRtl()) {
            i3 = rect.left;
            i2 = this.mThumbTouchHotWidth + i3;
        }
        this.mThumbTouchHotRect.set(i3, rect.top, i2, rect.bottom);
    }

    protected void updateTrackRect() {
        int i2 = this.mTrackWidth;
        int width = getWidth() - getPaddingRight();
        int i3 = width - i2;
        if (isScrollbarLayoutRtl()) {
            i3 = getPaddingLeft();
            width = i3 + i2;
        }
        this.mTrackLength = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int paddingTop = getPaddingTop();
        this.mTrackRect.set(i3, paddingTop, width, this.mTrackLength + paddingTop);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mThumbDrawable || drawable == this.mTrackDrawable || super.verifyDrawable(drawable);
    }

    static {
        try {
            t = View.class.getDeclaredMethod("computeVerticalScrollRange", new Class[0]);
        } catch (NoSuchMethodException unused) {
            t = null;
            Log.w(TAG, "NoSuchMethodException computeVerticalScrollRange");
        }
        try {
            u = View.class.getDeclaredMethod("computeVerticalScrollExtent", new Class[0]);
        } catch (NoSuchMethodException unused2) {
            u = null;
            Log.w(TAG, "NoSuchMethodException computeVerticalScrollExtent");
        }
        try {
            v = View.class.getDeclaredMethod("computeVerticalScrollOffset", new Class[0]);
        } catch (NoSuchMethodException unused3) {
            v = null;
            Log.w(TAG, "NoSuchMethodException computeVerticalScrollOffset");
        }
    }

    public HwScrollbarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100497_res_0x7f060351);
    }

    private void b(int i2) {
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.setAutoMirrored(true);
            DrawableCompat.setLayoutDirection(this.mThumbDrawable, i2);
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.setAutoMirrored(true);
            DrawableCompat.setLayoutDirection(this.mTrackDrawable, i2);
        }
    }

    private void c(int i2) {
        if (i2 == 0) {
            return;
        }
        int i3 = this.mThumbOffset + i2;
        int i4 = this.mTrackLength - this.mThumbLength;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 <= i4) {
            i4 = i3;
        }
        this.mThumbOffset = i4;
        b();
        v();
        invalidate();
    }

    public HwScrollbarView(Context context, AttributeSet attributeSet, int i2) {
        super(a(context, i2), attributeSet, i2);
        this.mThumbAlpha = 255;
        this.mTrackLength = 0;
        this.mThumbLength = 0;
        this.mThumbOffset = 0;
        this.mThumbWidth = 0;
        this.mTrackWidth = 0;
        this.mThumbTouchHotWidth = 0;
        this.mThumbRect = new Rect();
        this.mTrackRect = new Rect();
        this.mThumbTouchHotRect = new Rect();
        this.mThumbType = 0;
        this.w = 250;
        this.x = 0;
        this.B = 0.0f;
        this.C = true;
        this.D = 0;
        this.H = true;
        this.I = true;
        this.J = true;
        this.L = false;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.Q = new d();
        this.R = new c();
        a(super.getContext(), attributeSet, i2);
    }

    private static Context a(Context context, int i2) {
        return smr.b(context, i2, R.style.Theme_Emui_HwScrollbarView);
    }

    private void d(int i2) {
        postDelayed(new e(), i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100411_res_0x7f0602fb, R.attr._2131100412_res_0x7f0602fc, R.attr._2131100413_res_0x7f0602fd, R.attr._2131100421_res_0x7f060305, R.attr._2131100483_res_0x7f060343, R.attr._2131100484_res_0x7f060344, R.attr._2131100486_res_0x7f060346, R.attr._2131100487_res_0x7f060347, R.attr._2131100535_res_0x7f060377, R.attr._2131100559_res_0x7f06038f, R.attr._2131100577_res_0x7f0603a1, R.attr._2131100578_res_0x7f0603a2, R.attr._2131100579_res_0x7f0603a3, R.attr._2131100591_res_0x7f0603af, R.attr._2131100600_res_0x7f0603b8}, i2, R.style.Widget_Emui_HwScrollbarView);
        this.A = viewConfiguration.getScaledTouchSlop();
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 48);
        this.mMinThumbLength = dimensionPixelSize;
        this.M = obtainStyledAttributes.getDimensionPixelSize(3, dimensionPixelSize);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(2, 16);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(12, dimensionPixelSize2);
        this.mThumbWidth = dimensionPixelSize3;
        if (dimensionPixelSize3 < dimensionPixelSize2) {
            this.mThumbWidth = dimensionPixelSize2;
        }
        this.mTrackWidth = obtainStyledAttributes.getDimensionPixelSize(13, this.mThumbWidth);
        this.mThumbTouchHotWidth = obtainStyledAttributes.getDimensionPixelSize(10, 16);
        this.mThumbTint = obtainStyledAttributes.getColor(5, 16777215);
        this.mTrackTint = obtainStyledAttributes.getColor(7, 16777215);
        Drawable drawable = obtainStyledAttributes.getDrawable(4);
        Drawable drawable2 = drawable;
        if (drawable == null) {
            smu smuVar = new smu();
            smuVar.egH_(context, attributeSet, i2);
            drawable2 = smuVar;
        }
        setThumbDrawable(drawable2);
        setTrackDrawable(obtainStyledAttributes.getDrawable(6));
        this.mThumbType = obtainStyledAttributes.getInt(11, 0);
        this.mStartAngle = obtainStyledAttributes.getFloat(8, p);
        this.mSweepAngle = obtainStyledAttributes.getFloat(9, q);
        this.O = obtainStyledAttributes.getInteger(14, 0);
        obtainStyledAttributes.recycle();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        a aVar = new a(null);
        this.P = aVar;
        aVar.d = this;
        if (isInEditMode()) {
            this.mThumbRect = new Rect(0, 0, 48, n);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(2);
        }
    }

    private void b() {
        if (this.G != null) {
            int scrollableViewVerticalScrollRange = getScrollableViewVerticalScrollRange();
            int scrollableViewVerticalScrollExtent = getScrollableViewVerticalScrollExtent();
            if (scrollableViewVerticalScrollRange == -1 || scrollableViewVerticalScrollExtent == -1) {
                return;
            }
            int scrollableViewVerticalScrollOffset = getScrollableViewVerticalScrollOffset();
            float scrollProgress = getScrollProgress();
            int i2 = ((int) ((scrollableViewVerticalScrollRange - scrollableViewVerticalScrollExtent) * scrollProgress)) - scrollableViewVerticalScrollOffset;
            int compare = Float.compare(scrollProgress - this.B, 0.0f);
            if (compare > 0 && i2 < 0) {
                i2 = 0;
            }
            if (compare < 0 && i2 > 0) {
                i2 = 0;
            }
            if (i2 != 0) {
                this.G.onFastScrollChanged(0, i2, scrollProgress);
            }
            this.B = scrollProgress;
        }
    }

    private boolean c() {
        return this.E != null && getScrollableViewVerticalScrollRange() > getScrollableViewVerticalScrollExtent();
    }

    private void b(Canvas canvas) {
        if (this.mTrackDrawable != null && k() && this.mThumbType == 0) {
            onDrawTrack(canvas);
        }
    }

    private void a(int i2, int i3, int i4) {
        if (i2 > 0 && i2 > i3) {
            int i5 = this.mTrackLength;
            int i6 = (int) (((i3 * 1.0f) / i2) * i5);
            int i7 = this.mMinThumbLength;
            if (i6 < i7) {
                i6 = i7;
            }
            this.mThumbLength = i6;
            int i8 = i5 - i6;
            int i9 = (int) (((i4 * 1.0f) / (i2 - i3)) * i8);
            if (i9 <= i8) {
                i8 = i9;
            }
            this.mThumbOffset = i8;
            adjustOverScrollThumbParameters();
            v();
            this.B = getScrollProgress();
            return;
        }
        this.mThumbLength = 0;
        this.mThumbOffset = 0;
        this.mThumbRect.setEmpty();
        setThumbShow(false);
        this.I = false;
    }

    private void a() {
        ViewTreeObserver viewTreeObserver;
        View view = this.E;
        if (view == null) {
            return;
        }
        if (this.F == null) {
            this.F = new WeakReference<>(view.getViewTreeObserver());
        }
        if (this.R == null || (viewTreeObserver = this.F.get()) == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(this.R);
    }

    private boolean a(int i2, int i3) {
        if (this.C) {
            return this.mThumbTouchHotRect.contains(i2, i3);
        }
        return false;
    }

    private void a(Canvas canvas) {
        a aVar;
        int i2;
        if (k() && (i2 = (aVar = this.P).i) != 0) {
            boolean z = false;
            if (i2 == 2) {
                float[] fArr = aVar.b;
                if (aVar.c.timeToValues(fArr) == Interpolator.Result.FREEZE_END) {
                    setThumbShow(false);
                    aVar.i = 0;
                } else {
                    this.mThumbAlpha = Math.round(fArr[0]);
                }
                z = true;
            } else {
                this.mThumbAlpha = 255;
            }
            onDrawThumb(canvas);
            if (z) {
                postInvalidateDelayed(50L);
            }
        }
    }

    private void a(int i2) {
        if (k()) {
            this.P.j = AnimationUtils.currentAnimationTimeMillis() + this.w;
            this.P.i = 1;
            removeCallbacks(this.P);
            postDelayed(this.P, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return;
        }
        int action = motionEvent.getAction();
        int y = (int) motionEvent.getY();
        if (action != 1) {
            if (action == 2) {
                if (this.D == 0) {
                    this.z = y;
                    this.D = 2;
                    if (c()) {
                        this.I = true;
                        r();
                    } else {
                        this.I = false;
                        setThumbShow(false);
                    }
                }
                if (!k() || this.z == y) {
                    return;
                }
                this.z = y;
                r();
                return;
            }
            if (action != 3) {
                return;
            }
        }
        this.D = 0;
        o();
    }
}
