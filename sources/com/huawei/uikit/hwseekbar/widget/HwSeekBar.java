package com.huawei.uikit.hwseekbar.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.math.MathUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import defpackage.bno;
import defpackage.slc;
import defpackage.smp;
import defpackage.smr;
import defpackage.sms;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwSeekBar extends SeekBar {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10740a = "HwSeekBar";
    private static final int b = 5;
    private static final int c = 10;
    private static final int d = 500;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 8;
    private static final int h = 10;
    private static final int i = 10;
    private static final int j = 8;
    private static final float k = 2.0f;
    private static final int l = 2;
    private static final int m = 16;
    private static final int n = 600;
    private static final int o = 49;
    private static final int p = 10000;
    private static final int q = 100;
    private static final int r = 56;
    private static final int s = 60;
    private static final float t = 0.5f;
    private static final float u = 25.0f;
    private static final float v = 28.0f;
    private static final float w = 18.0f;
    private static final float x = 20.0f;
    private float A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private Method G;
    private LayerDrawable H;
    private ScaleDrawable I;
    private GradientDrawable J;
    private float K;
    private TextView L;
    private Drawable M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private float R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int aa;
    private int ba;
    private Paint ca;
    private Paint da;
    private Rect ea;
    private int fa;
    private float ga;
    private boolean ha;
    private OnSeekBarChangeListener ia;
    private boolean ja;
    private HwGenericEventDetector ka;
    private boolean la;
    private float ma;
    private float na;
    private Method oa;
    private Field pa;
    private Interpolator qa;
    private boolean ra;
    private volatile boolean sa;
    private Runnable ta;
    private final Property<HwSeekBar, Float> ua;
    private Context y;
    private PopupWindow z;

    public interface OnSeekBarChangeListener {
        void onProgressChanged(HwSeekBar hwSeekBar, int i, boolean z);

        void onStartTrackingTouch(HwSeekBar hwSeekBar);

        void onStopTrackingTouch(HwSeekBar hwSeekBar);
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwSeekBar.this.o();
            HwSeekBar.this.removeCallbacks(this);
        }
    }

    class c implements HwGenericEventDetector.OnChangeProgressListener {
        c() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnChangeProgressListener
        public boolean onChangeProgress(int i, int i2, MotionEvent motionEvent) {
            int a2 = HwSeekBar.this.a(i, i2);
            HwSeekBar.this.p();
            int round = HwSeekBar.this.B ? Math.round(HwSeekBar.this.R) : 1;
            HwSeekBar.this.n();
            HwSeekBar hwSeekBar = HwSeekBar.this;
            hwSeekBar.d(hwSeekBar.getProgress() + (a2 * round));
            HwSeekBar hwSeekBar2 = HwSeekBar.this;
            hwSeekBar2.postDelayed(hwSeekBar2.ta, 500L);
            return true;
        }
    }

    public HwSeekBar(Context context) {
        this(context, null);
    }

    private float getScale() {
        int max = getMax();
        if (max > 0) {
            return getProgress() / max;
        }
        return 0.0f;
    }

    private void h() {
        if (sms.b(this.y) == 1 && Float.compare(this.na, 1.75f) >= 0) {
            if (Float.compare(this.na, 2.0f) >= 0) {
                this.L.setTextSize(1, v);
            } else {
                this.L.setTextSize(1, 25.0f);
            }
        }
    }

    private void i() {
        if (sms.b(this.y) == 1 && Float.compare(this.na, 1.75f) >= 0) {
            if (Float.compare(this.na, 2.0f) >= 0) {
                this.ca.setTextSize(TypedValue.applyDimension(1, 20.0f, this.y.getResources().getDisplayMetrics()));
            } else {
                this.ca.setTextSize(TypedValue.applyDimension(1, w, this.y.getResources().getDisplayMetrics()));
            }
        }
    }

    public static HwSeekBar instantiate(Context context) {
        Object e2 = sms.e(context, sms.e(context, (Class<?>) HwSeekBar.class, sms.c(context, 5, 1)), (Class<?>) HwSeekBar.class);
        if (e2 instanceof HwSeekBar) {
            return (HwSeekBar) e2;
        }
        return null;
    }

    private void j() {
        if (sms.b(this.y) == 1 && Build.VERSION.SDK_INT >= 29 && Float.compare(this.na, 1.75f) >= 0) {
            if (Float.compare(this.na, 2.0f) >= 0) {
                setMinHeight(b(60));
            } else {
                setMinHeight(b(56));
            }
        }
    }

    private void k() {
        if (this.ha) {
            b();
            setPressed(false);
        }
        invalidate();
    }

    private void l() {
        if (this.E) {
            this.L.setBackgroundResource(this.S);
            q();
            int measuredHeight = getMeasuredHeight();
            this.z.showAsDropDown(this, 0, (0 - measuredHeight) - this.P, 3);
            s();
        }
    }

    private void m() {
        if (this.E) {
            this.z.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        OnSeekBarChangeListener onSeekBarChangeListener = this.ia;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        OnSeekBarChangeListener onSeekBarChangeListener = this.ia;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        Runnable runnable = this.ta;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    private void q() {
        this.L.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.O = this.L.getMeasuredWidth();
        this.P = this.L.getMeasuredHeight();
    }

    private void r() {
        this.H = null;
        this.I = null;
        this.J = null;
        if (getProgressDrawable() instanceof LayerDrawable) {
            this.H = (LayerDrawable) getProgressDrawable();
        }
        LayerDrawable layerDrawable = this.H;
        if (layerDrawable != null) {
            Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.progress);
            if (findDrawableByLayerId instanceof ScaleDrawable) {
                this.I = (ScaleDrawable) findDrawableByLayerId;
            }
        }
        ScaleDrawable scaleDrawable = this.I;
        if (scaleDrawable != null) {
            Drawable drawable = scaleDrawable.getDrawable();
            if (drawable instanceof GradientDrawable) {
                this.J = (GradientDrawable) drawable;
            }
        }
    }

    private void s() {
        q();
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        float scale = g() ? 1.0f - getScale() : getScale();
        int paddingLeft2 = getPaddingLeft();
        int round = Math.round(((width - paddingLeft) - paddingRight) * scale);
        int i2 = this.O / 2;
        int measuredHeight = getMeasuredHeight();
        int i3 = this.P;
        this.z.update(this, (paddingLeft2 + round) - i2, (0 - measuredHeight) - i3, this.O, i3);
    }

    private void setValueFromPlume(Context context) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            return;
        }
        Object c2 = slc.c((Object) null, b2, new Object[]{context, this, "seekBarScrollEnabled", true});
        if (c2 instanceof Boolean) {
            setExtendProgressEnabled(((Boolean) c2).booleanValue());
        }
    }

    protected HwGenericEventDetector createGenericEventDetector() {
        return new HwGenericEventDetector(getContext());
    }

    protected HwGenericEventDetector.OnChangeProgressListener createOnChangeProgressListener() {
        return new c();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setAlpha(isEnabled() ? 255 : 76);
        }
        Drawable drawable = this.M;
        if (drawable != null) {
            drawable.setState(getDrawableState());
        }
    }

    @Override // android.view.View
    public int getLayoutDirection() {
        if (Constants.URDU_LANG.equals(Locale.getDefault().getLanguage())) {
            return 0;
        }
        return super.getLayoutDirection();
    }

    @Override // android.widget.ProgressBar, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
        if (getThumb() == drawable) {
            invalidate();
        }
    }

    public boolean isAdjustCornersEnabled() {
        return this.F;
    }

    public boolean isExtendProgressEnabled() {
        return this.la;
    }

    public boolean isShowTipText() {
        return this.E;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        synchronized (this) {
            if (canvas == null) {
                Log.w(f10740a, "onDraw canvas is null");
                return;
            }
            if (!this.B) {
                b(canvas);
                a(canvas);
            } else if (this.C) {
                int save = canvas.save();
                c(canvas);
                b(canvas);
                a(canvas);
                canvas.restoreToCount(save);
                j();
            } else {
                c(canvas);
                b(canvas);
                a(canvas);
            }
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z || !this.E) {
            return;
        }
        this.z.dismiss();
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.la) {
            return super.onGenericMotionEvent(motionEvent);
        }
        HwGenericEventDetector hwGenericEventDetector = this.ka;
        if (hwGenericEventDetector == null || !hwGenericEventDetector.eis_(motionEvent)) {
            return super.onGenericMotionEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.AbsSeekBar, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        OnSeekBarChangeListener onSeekBarChangeListener;
        int progress = getProgress();
        super.onKeyDown(i2, keyEvent);
        int progress2 = getProgress();
        if (progress != progress2 && (onSeekBarChangeListener = this.ia) != null) {
            onSeekBarChangeListener.onProgressChanged(this, progress2, true);
        }
        if (isEnabled() && ((i2 == 21 || i2 == 22 || i2 == 69 || i2 == 70 || i2 == 81) && this.E)) {
            this.L.setBackgroundResource(this.S);
            q();
            int measuredHeight = getMeasuredHeight();
            int i3 = this.P;
            this.L.setText(String.valueOf(progress2));
            this.z.showAsDropDown(this, 0, (0 - measuredHeight) - i3, 3);
            s();
        }
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        super.onKeyUp(i2, keyEvent);
        if (!isEnabled()) {
            return false;
        }
        if ((i2 != 21 && i2 != 22 && i2 != 69 && i2 != 70 && i2 != 81) || !this.E) {
            return false;
        }
        this.z.dismiss();
        return false;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        if (motionEvent == null) {
            Log.w(f10740a, "onTouchEvent: motionEvent is null");
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            a(motionEvent);
            this.sa = true;
        } else if (action == 1) {
            c(motionEvent);
            this.sa = false;
        } else if (action == 2) {
            b(motionEvent);
        } else if (action == 3) {
            k();
            this.sa = false;
        }
        return true;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z || !this.E) {
            return;
        }
        this.z.dismiss();
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        OnSeekBarChangeListener onSeekBarChangeListener;
        int progress = getProgress();
        boolean performAccessibilityAction = super.performAccessibilityAction(i2, bundle);
        int progress2 = getProgress();
        if (progress != progress2 && (onSeekBarChangeListener = this.ia) != null) {
            onSeekBarChangeListener.onProgressChanged(this, progress2, true);
        }
        return performAccessibilityAction;
    }

    public void setAdjustCornersEnabled(boolean z) {
        this.F = z;
    }

    public void setExtendProgressEnabled(boolean z) {
        this.la = z;
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.ia = onSeekBarChangeListener;
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i2) {
        OnSeekBarChangeListener onSeekBarChangeListener;
        synchronized (this) {
            if (this.B) {
                float f2 = this.R;
                if (f2 != 0.0f) {
                    i2 = Math.round(f2 * Math.round(i2 / f2));
                }
            }
            float max = getMax() - getMin();
            if (Float.compare(max, 0.0f) == 0) {
                this.ma = 0.0f;
            } else {
                this.ma = i2 / max;
            }
            boolean z = this.N != i2;
            this.N = i2;
            super.setProgress(i2);
            int progress = getProgress();
            this.N = progress;
            if (z && (onSeekBarChangeListener = this.ia) != null) {
                onSeekBarChangeListener.onProgressChanged(this, progress, this.sa);
            }
            if (this.E) {
                if (!this.D) {
                    this.L.setText(String.valueOf(this.N));
                }
                s();
            }
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
        r();
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        super.setScaleX(f2);
        if (this.F && this.ra) {
            a(this.H);
        }
        invalidate();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        super.setScaleY(f2);
        if (this.F && this.ra) {
            a(this.H);
        }
        invalidate();
    }

    public void setShowTipText(boolean z) {
        this.E = z;
        if (!z || this.ja) {
            return;
        }
        e();
    }

    public void setTip(boolean z, int i2, boolean z2) {
        if (i2 != 0) {
            this.B = true;
            this.C = z;
            this.Q = i2;
            this.R = getMax() / this.Q;
            this.S = z2 ? this.T : this.U;
            this.M = ContextCompat.getDrawable(this.y, com.huawei.health.R.drawable._2131429483_res_0x7f0b086b);
            e();
            Paint paint = new Paint();
            this.da = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.ca = paint2;
            paint2.setAntiAlias(true);
            this.ca.setColor(this.ba);
            this.ca.setTextSize(this.aa);
            i();
            this.ca.setTypeface(Typeface.create(Constants.FONT, 0));
            if (this.E || !this.ra || this.Q == 0) {
                setProgress(getProgress());
            } else {
                setProgress(getProgress(), true);
            }
            invalidate();
        }
    }

    public void setTipText(String str) {
        if (this.S != this.T || !this.E || str == null) {
            this.D = false;
            return;
        }
        this.L.setText(str);
        s();
        this.D = true;
    }

    public HwSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.huawei.health.R.attr._2131100510_res_0x7f06035e);
    }

    private static int b(int i2) {
        return (int) TypedValue.applyDimension(1, i2, Resources.getSystem().getDisplayMetrics());
    }

    private void c(MotionEvent motionEvent) {
        if (this.ha) {
            e(motionEvent);
            b();
            setPressed(false);
        } else {
            a();
            e(motionEvent);
            b();
        }
        invalidate();
    }

    private void d(MotionEvent motionEvent) {
        setPressed(true);
        if (getThumb() != null) {
            invalidate(getThumb().getBounds());
        }
        a();
        e(motionEvent);
        c();
    }

    private void e() {
        Drawable drawable;
        TextView textView = new TextView(this.y);
        this.L = textView;
        textView.setTextColor(this.W);
        this.L.setTextSize(0, this.V);
        h();
        this.L.setTypeface(Typeface.SANS_SERIF);
        int i2 = this.S;
        int i3 = this.U;
        if (i2 == i3) {
            try {
                this.S = i3;
                drawable = ContextCompat.getDrawable(this.y, i3);
            } catch (Resources.NotFoundException unused) {
                Log.e(f10740a, "Throws NotFoundException if the mTipBgId ID does not exist.");
                drawable = null;
            }
            if (drawable != null) {
                this.L.setLayoutParams(new ViewGroup.LayoutParams(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()));
            } else {
                this.L.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
        } else {
            this.L.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        }
        this.L.setGravity(17);
        this.L.setSingleLine(true);
        PopupWindow popupWindow = new PopupWindow((View) this.L, -2, -2, false);
        this.z = popupWindow;
        popupWindow.setAnimationStyle(com.huawei.health.R.style.Animation_Emui_HwSeekBar_TipsPopupWindow);
        this.ja = true;
    }

    private boolean f() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    class a extends Property<HwSeekBar, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void set(HwSeekBar hwSeekBar, Float f) {
            if (hwSeekBar == null) {
                Log.w(HwSeekBar.f10740a, "set: object is null");
            } else {
                hwSeekBar.a(R.id.progress, f.floatValue());
                hwSeekBar.ma = f.floatValue();
            }
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(HwSeekBar hwSeekBar) {
            if (hwSeekBar != null) {
                return Float.valueOf(hwSeekBar.ma);
            }
            Log.w(HwSeekBar.f10740a, "get: object is null");
            return Float.valueOf(0.0f);
        }
    }

    public HwSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(a(context, i2), attributeSet, i2);
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = 0.0f;
        this.N = 0;
        this.ea = new Rect();
        this.la = true;
        this.ra = false;
        this.sa = false;
        this.ta = new b();
        this.ua = new a(Float.class, "visual_progress");
        a(super.getContext(), attributeSet, i2);
    }

    private boolean g() {
        return getLayoutDirection() == 1;
    }

    void b() {
        this.ha = false;
        OnSeekBarChangeListener onSeekBarChangeListener = this.ia;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
        m();
    }

    private static Context a(Context context, int i2) {
        return smr.b(context, i2, com.huawei.health.R.style.Theme_Emui_HwSeekBar);
    }

    private static Bitmap a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    void b(Canvas canvas) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null || canvas == null) {
            return;
        }
        int save = canvas.save();
        if (g()) {
            canvas.translate(getWidth() - getPaddingRight(), getPaddingTop());
            canvas.scale(-1.0f, 1.0f);
        } else {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        progressDrawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    private void c() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void c(int i2) {
        int max = getMax() - getMin();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.ua, max > 0 ? (i2 - r0) / max : 0.0f);
        ofFloat.setAutoCancel(true);
        if (this.Q != 0) {
            this.qa = AnimationUtils.loadInterpolator(this.y, com.huawei.health.R.interpolator._2131689481_res_0x7f0f0009);
            ofFloat.setDuration(100L);
        } else {
            bno bnoVar = new bno(600.0f, 49.0f, 1.0f, this.K);
            this.qa = bnoVar;
            this.K = bnoVar.getModel().getVelocity();
            ofFloat.setDuration((long) ((bno) this.qa).getModel().getEstimatedDuration());
        }
        ofFloat.setInterpolator(this.qa);
        ofFloat.start();
        if (this.Q == 0) {
            ofFloat.setCurrentPlayTime(16L);
        }
    }

    private void d() {
        this.oa = null;
        this.pa = null;
    }

    private void b(MotionEvent motionEvent) {
        if (this.ha) {
            e(motionEvent);
        } else if (Math.abs(motionEvent.getX() - this.ga) > this.fa) {
            d(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        if ((getWidth() - getPaddingLeft()) - getPaddingRight() <= 0) {
            setProgress(0);
        } else if (!this.E && this.ra && this.Q != 0) {
            setProgress(i2, true);
        } else {
            setProgress(i2);
        }
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        this.y = context;
        this.na = smp.a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{com.huawei.health.R.attr._2131100221_res_0x7f06023d, com.huawei.health.R.attr._2131100482_res_0x7f060342, com.huawei.health.R.attr._2131100509_res_0x7f06035d, com.huawei.health.R.attr._2131100526_res_0x7f06036e, com.huawei.health.R.attr._2131100527_res_0x7f06036f, com.huawei.health.R.attr._2131100537_res_0x7f060379, com.huawei.health.R.attr._2131100538_res_0x7f06037a, com.huawei.health.R.attr._2131100582_res_0x7f0603a6, com.huawei.health.R.attr._2131100583_res_0x7f0603a7}, i2, com.huawei.health.R.style.Widget_Emui_HwSeekBar);
        this.E = obtainStyledAttributes.getBoolean(3, false);
        this.aa = obtainStyledAttributes.getDimensionPixelSize(6, this.aa);
        this.V = obtainStyledAttributes.getDimensionPixelSize(8, this.V);
        this.T = obtainStyledAttributes.getResourceId(0, 0);
        this.U = obtainStyledAttributes.getResourceId(4, 0);
        this.W = obtainStyledAttributes.getColor(7, this.W);
        this.ba = obtainStyledAttributes.getColor(5, this.ba);
        this.ra = obtainStyledAttributes.getBoolean(2, false);
        obtainStyledAttributes.recycle();
        setDefaultFocusHighlightEnabled(false);
        if (this.E) {
            e();
        }
        this.S = this.T;
        this.fa = ViewConfiguration.get(context).getScaledTouchSlop();
        HwGenericEventDetector createGenericEventDetector = createGenericEventDetector();
        this.ka = createGenericEventDetector;
        if (createGenericEventDetector != null) {
            createGenericEventDetector.a(createOnChangeProgressListener());
        }
        setValueFromPlume(context);
        r();
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i2, boolean z) {
        OnSeekBarChangeListener onSeekBarChangeListener;
        int a2 = a(i2);
        boolean z2 = this.N != a2;
        this.N = a2;
        if (this.E || !this.ra) {
            setProgress(a2);
            return;
        }
        if (z2 && z) {
            super.setProgress(a2, true);
            c(a2);
        }
        if (!z2 || (onSeekBarChangeListener = this.ia) == null) {
            return;
        }
        onSeekBarChangeListener.onProgressChanged(this, this.N, this.sa);
    }

    private void e(MotionEvent motionEvent) {
        float f2;
        float f3;
        float f4;
        int round = Math.round(motionEvent.getX());
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i2 = (width - paddingLeft) - paddingRight;
        if (i2 <= 0) {
            setProgress(0);
            return;
        }
        float f5 = 0.0f;
        if (g()) {
            if (width - paddingRight >= round) {
                if (round >= paddingLeft) {
                    f2 = ((i2 - round) + paddingLeft) / i2;
                    f3 = this.A;
                    float f6 = f2;
                    f5 = f3;
                    f4 = f6;
                }
                f4 = 1.0f;
            }
            f4 = 0.0f;
        } else {
            if (round >= paddingLeft) {
                if (round <= width - paddingRight) {
                    f2 = (round - paddingLeft) / i2;
                    f3 = this.A;
                    float f62 = f2;
                    f5 = f3;
                    f4 = f62;
                }
                f4 = 1.0f;
            }
            f4 = 0.0f;
        }
        float max = f5 + (f4 * getMax());
        if (!this.E && this.ra && this.Q != 0) {
            setProgress(Math.round(max), true);
        } else {
            setProgress(Math.round(max));
        }
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        this.ca.getTextBounds(str, 0, str.length(), this.ea);
        return this.ea.width();
    }

    private void c(Canvas canvas) {
        float paddingStart;
        int paddingLeft;
        int b2;
        int round;
        Drawable drawable = this.M;
        if (drawable == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = this.M.getIntrinsicHeight();
        int width = getWidth();
        if (getPaddingStart() > b(8)) {
            paddingStart = ((width - getPaddingStart()) - getPaddingEnd()) - intrinsicWidth;
            paddingLeft = getPaddingLeft();
            b2 = intrinsicWidth / 2;
        } else {
            paddingStart = (((width - getPaddingStart()) - getPaddingEnd()) - b(10)) - b(10);
            paddingLeft = getPaddingLeft();
            b2 = b(10);
        }
        int i2 = paddingLeft + b2;
        int i3 = this.Q;
        int height = (((getHeight() - getPaddingTop()) - getPaddingBottom()) / 2) - (intrinsicHeight / 2);
        if (i3 <= 1) {
            return;
        }
        float f2 = paddingStart / i3;
        int i4 = 0;
        if (!this.C) {
            Bitmap a2 = a(this.M);
            if (a2 == null) {
                return;
            }
            while (i4 <= i3) {
                canvas.drawBitmap(a2, (i2 + (i4 * f2)) - (intrinsicWidth / 2), height, this.da);
                i4++;
            }
            return;
        }
        while (i4 <= i3) {
            if (g()) {
                round = Math.round(this.R * (i3 - i4));
            } else {
                round = Math.round(this.R * i4);
            }
            int a3 = a(String.valueOf(round));
            canvas.drawText(String.valueOf(round), (i2 + (i4 * f2)) - (b(String.valueOf(round)) / 2), height + intrinsicHeight + b(10) + a3, this.ca);
            i4++;
        }
    }

    void a() {
        this.ha = true;
        OnSeekBarChangeListener onSeekBarChangeListener = this.ia;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
        l();
    }

    void a(Canvas canvas) {
        Drawable thumb = getThumb();
        if (thumb == null || canvas == null) {
            return;
        }
        int save = canvas.save();
        canvas.translate(getPaddingLeft() - getThumbOffset(), getPaddingTop());
        float scaleX = getScaleX();
        float scaleY = getScaleY();
        if (this.F && this.ra && this.I != null && Float.compare(scaleX * scaleY, 0.0f) != 0) {
            Rect bounds = this.I.getBounds();
            int i2 = bounds.left;
            int i3 = bounds.right;
            float f2 = bounds.bottom - bounds.top;
            float f3 = ((f2 * scaleY) / scaleX) / 2.0f;
            float f4 = f2 / 2.0f;
            float max = getMax() - getMin();
            float progress = Float.compare(max, 0.0f) != 0 ? getProgress() / max : 0.0f;
            float f5 = i2;
            float f6 = i3 - i2;
            float f7 = f5 + f4 + ((f6 - (f4 * 2.0f)) * progress);
            float f8 = f5 + f3 + ((f6 - (f3 * 2.0f)) * progress);
            Rect bounds2 = thumb.getBounds();
            int i4 = bounds2.right;
            float f9 = (i4 - r8) / 2.0f;
            float f10 = bounds2.left;
            int i5 = bounds2.bottom;
            float f11 = (i5 - r4) / 2.0f;
            float f12 = bounds2.top;
            if (getLayoutDirection() == 1) {
                canvas.translate(f7 - f8, 0.0f);
            } else {
                canvas.translate(f8 - f7, 0.0f);
            }
            canvas.scale(scaleY / scaleX, 1.0f, f9 + f10, f11 + f12);
        }
        thumb.draw(canvas);
        canvas.restoreToCount(save);
    }

    private void a(MotionEvent motionEvent) {
        if (f()) {
            this.ga = motionEvent.getX();
        } else {
            d(motionEvent);
        }
    }

    private void a(LayerDrawable layerDrawable) {
        if (layerDrawable == null) {
            return;
        }
        for (int i2 = 0; i2 < layerDrawable.getNumberOfLayers(); i2++) {
            Drawable drawable = layerDrawable.getDrawable(i2);
            if (drawable instanceof GradientDrawable) {
                a((GradientDrawable) drawable);
            } else if (drawable instanceof ScaleDrawable) {
                Drawable drawable2 = ((ScaleDrawable) drawable).getDrawable();
                if (drawable2 instanceof GradientDrawable) {
                    a((GradientDrawable) drawable2);
                } else if (drawable2 instanceof LayerDrawable) {
                    a((LayerDrawable) drawable2);
                } else {
                    Log.i(f10740a, "preProcessDrawableRadius: Neither a GradientDrawable nor a ScaleDrawable");
                }
            } else if (drawable instanceof LayerDrawable) {
                a((LayerDrawable) drawable);
            } else {
                Log.i(f10740a, "preProcessDrawableRadius: not belongs to GradientDrawable and ScaleDrawable and LayerDrawable");
            }
        }
    }

    private void a(GradientDrawable gradientDrawable) {
        if (gradientDrawable == null) {
            return;
        }
        float scaleX = getScaleX();
        float scaleY = getScaleY();
        if (Float.compare(scaleX, 0.0f) == 0 || Float.compare(scaleY, 0.0f) == 0) {
            return;
        }
        gradientDrawable.mutate();
        float cornerRadius = gradientDrawable.getCornerRadius();
        float[] fArr = new float[8];
        if (Float.compare(cornerRadius, 0.0f) == 0) {
            try {
                fArr = gradientDrawable.getCornerRadii();
                if (fArr == null) {
                    return;
                }
            } catch (NullPointerException unused) {
                Log.w(f10740a, "processDrawableRadius: corner radius is not set");
                return;
            }
        } else {
            Arrays.fill(fArr, cornerRadius);
        }
        for (int i2 = 0; i2 < fArr.length - 1; i2++) {
            if (i2 % 2 == 0) {
                fArr[i2] = (fArr[i2 + 1] * scaleY) / scaleX;
            }
        }
        float f2 = gradientDrawable.getBounds().bottom - gradientDrawable.getBounds().top;
        if (gradientDrawable.equals(this.J)) {
            int i3 = (int) ((f2 * scaleY) / scaleX);
            gradientDrawable.setSize(i3, i3);
            ScaleDrawable scaleDrawable = this.I;
            a(scaleDrawable, scaleDrawable.getBounds());
        }
        gradientDrawable.setCornerRadii(fArr);
    }

    private void a(Object obj, Rect rect) {
        if (this.G == null) {
            this.G = slc.b("onBoundsChange", new Class[]{Rect.class}, "android.graphics.drawable.ScaleDrawable");
        }
        Method method = this.G;
        if (method == null) {
            return;
        }
        slc.c(obj, method, new Object[]{rect});
    }

    private void a(int i2, int i3, boolean z) {
        OnSeekBarChangeListener onSeekBarChangeListener;
        if (isIndeterminate()) {
            return;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 > getMax()) {
            i3 = getMax();
        }
        if (i3 != getProgress()) {
            if (this.pa == null && this.oa == null) {
                d();
            }
            Field field = this.pa;
            if (field != null) {
                try {
                    field.set(this, Integer.valueOf(i3));
                } catch (IllegalAccessException unused) {
                    Log.e(f10740a, "Field IllegalAccessException");
                }
            }
            Method method = this.oa;
            if (method != null) {
                slc.c(this, method, new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), false});
            }
            if (z && (onSeekBarChangeListener = this.ia) != null) {
                onSeekBarChangeListener.onProgressChanged(this, this.N, this.sa);
            }
            if (z && this.E) {
                if (!this.D) {
                    this.L.setText(String.valueOf(this.N));
                }
                s();
            }
        }
    }

    private int a(int i2) {
        int clamp = MathUtils.clamp(i2, getMin(), getMax());
        if (!this.B) {
            return clamp;
        }
        float f2 = this.R;
        return f2 != 0.0f ? Math.round(f2 * Math.round(clamp / f2)) : clamp;
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        this.ca.getTextBounds(str, 0, str.length(), this.ea);
        return this.ea.height();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i2, int i3) {
        return Float.compare((float) i2, 0.0f) == 0 ? i3 : g() ? -i2 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, float f2) {
        this.ma = f2;
        Drawable progressDrawable = getProgressDrawable();
        if ((progressDrawable instanceof LayerDrawable) && (progressDrawable = ((LayerDrawable) progressDrawable).findDrawableByLayerId(i2)) == null) {
            progressDrawable = getProgressDrawable();
        }
        if (progressDrawable != null) {
            progressDrawable.setLevel((int) (10000.0f * f2));
        } else {
            invalidate();
        }
        a(f2);
        invalidate();
    }

    private void a(float f2) {
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        Drawable thumb = getThumb();
        if (thumb == null) {
            return;
        }
        int intrinsicWidth = thumb.getIntrinsicWidth();
        int thumbOffset = (((width - paddingLeft) - paddingRight) - intrinsicWidth) + (getThumbOffset() * 2);
        int i2 = (int) ((f2 * thumbOffset) + 0.5f);
        Rect bounds = thumb.getBounds();
        int i3 = bounds.top;
        int i4 = bounds.bottom;
        if (g()) {
            i2 = thumbOffset - i2;
        }
        thumb.setBounds(i2, i3, intrinsicWidth + i2, i4);
    }
}
